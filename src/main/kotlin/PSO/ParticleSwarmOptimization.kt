package PSO

import org.ejml.simple.SimpleMatrix
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import utility.*

class ParticleSwarmOptimization(val modelInfo: ModelInfo) {
    fun run(psoInfo: PSOInfo, particleCount: Int = 20, swarmCount: Int = 1): SimpleMatrix{
        val createSwarm = { i: Int -> Swarm(List(particleCount) { Particle(modelInfo.lumpingMatrix.numCols(), modelInfo.lumpingMatrix.numRows(), psoInfo.lowerBound, psoInfo.upperBound) }, modelInfo.function, psoInfo) }
        val swarms = List( swarmCount, createSwarm)

        repeat(psoInfo.maxIterations){
            swarms.forEach { it.update() }
        }

        return swarms.minByOrNull { it.globalBestCost }!!.globalBestPosition
    }
}

private class Swarm(val particles: List<Particle>, val modelFunction: (SimpleMatrix) -> SimpleMatrix, val psoInfo: PSOInfo){
    var globalBestPosition: SimpleMatrix
    var globalBestCost: Double
    var currentIteration = 0
    val costCalculator = CostCalculator(modelFunction)

    init{
        particles.forEach { it.position = it.position.MGSON(); it.bestCost = costCalculator.cost(it.position) }
        val (bestStartPosition, bestStartCost) = particles.map { Pair(it.position, it.bestCost) }.minByOrNull { it.second }!!
        globalBestPosition = bestStartPosition
        globalBestCost = bestStartCost
    }

    fun update(){
        val mutex = Mutex()
        runBlocking {
            val jobs = particles.map { particle ->
                GlobalScope.launch {
                    currentIteration++
                    particle.update(psoInfo, globalBestPosition, calcLocalBestPosition(particle, 5), currentIteration)

                    val cost = costCalculator.cost(particle.position)
                    if(cost < particle.bestCost){
                        particle.bestPosition = particle.position
                        particle.bestCost = cost

                        mutex.lock()
                        if(cost < globalBestCost){
                            globalBestPosition = particle.position
                            globalBestCost = cost
                        }
                        mutex.unlock()
                    }
                }
            }
            jobs.joinAll()
        }
    }

    fun calcLocalBestPosition(particle: Particle, neighborhoodSize: Int): SimpleMatrix {
        return particlesNear(particle, neighborhoodSize).minByOrNull { it.bestCost }!!.bestPosition
    }

    fun particlesNear(particle: Particle, neighborhoodSize: Int): List<Particle>{
        val distances = { it: Particle -> (it.position - particle.position).allElements().map { it * it }.sum() }
        return particles.toList().map { Pair(it, distances(it)) }.sortedBy { it.second }.take(neighborhoodSize).map { it.first }
    }
}


private class Particle(columns: Int, rows: Int, lowerBound: Double, upperBound: Double){
    var position = randMatrix(rows, columns, lowerBound until upperBound)
    var velocity: SimpleMatrix = randMatrix(rows, columns, -Math.abs(upperBound - lowerBound) until Math.abs(upperBound - lowerBound))
    var bestPosition = position
    var bestCost = Double.MAX_VALUE

    fun update(psoInfo: PSOInfo, globalBestPos: SimpleMatrix, localBestPos: SimpleMatrix, currentIteration: Int){
        val randNumsParticle = randMatrix(position.numRows(), position.numCols(), 0.0 until 1.0)
        val randNumsGlobal = randMatrix(position.numRows(), position.numCols(), 0.0 until 1.0)
        val randNumsLocal = randMatrix(position.numRows(), position.numCols(), 0.0 until 1.0)

        val newVelocity = velocity.scale(psoInfo.omegaMax - (psoInfo.omegaMax - psoInfo.omegaMin) / psoInfo.maxIterations * currentIteration) +
                bestPosition.minus(position).hadamard(randNumsParticle).scale(psoInfo.accelParticle) +
                localBestPos.minus(position).hadamard(randNumsLocal).scale(psoInfo.accelLocal) +
                globalBestPos.minus(position).hadamard(randNumsGlobal).scale(psoInfo.accelGlobal)

        velocity = checkVelocityBound(newVelocity, psoInfo)
        position = (position + velocity).MGSON()
    }

    private fun checkVelocityBound(velocity: SimpleMatrix, psoInfo: PSOInfo) : SimpleMatrix {
        val vMax = (psoInfo.upperBound - psoInfo.lowerBound) / psoInfo.vMaxN

        for (i in 0 until velocity.numElements){
            if(velocity[i] > vMax) velocity[i] = vMax
            if(velocity[i] < -vMax) velocity[i] = -vMax
        }

        return velocity
    }
}