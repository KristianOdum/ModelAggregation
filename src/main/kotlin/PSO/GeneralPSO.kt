package PSO

import org.ejml.simple.SimpleMatrix
import utility.*

class GeneralPSO(modelInfo: ModelInfo, val swarmCount: Int, particleCount: Int, val psoInfo: PSOInfo) : ParticleSwarmOptimization<VelocityParticle>(modelInfo) {

    override var swarms = MutableList(swarmCount) { Swarm(
            MutableList(particleCount) { VelocityParticle(modelInfo.lumpingMatrix.numCols(), modelInfo.lumpingMatrix.numRows(), psoInfo.bounds) }
    ) }

    override fun updateParticle(swarm: Swarm<VelocityParticle>, particle: VelocityParticle) {
        val randNumsParticle = randMatrix(particle.position.numRows(), particle.position.numCols(), 0.0 until 1.0)
        val randNumsGlobal = randMatrix(particle.position.numRows(), particle.position.numCols(), 0.0 until 1.0)
        val randNumsLocal = randMatrix(particle.position.numRows(), particle.position.numCols(), 0.0 until 1.0)

        val newVelocity = particle.velocity.scale(psoInfo.omegaMax - (psoInfo.omegaMax - psoInfo.omegaMin) / psoInfo.maxIterations * swarm.currentIteration) +
                particle.bestPosition.minus(particle.position).hadamard(randNumsParticle).scale(psoInfo.accelParticle) +
                calcLocalBestPosition(swarm, particle, psoInfo.neighborhoodSize).minus(particle.position).hadamard(randNumsLocal).scale(psoInfo.accelLocal) +
                swarm.globalBestPosition.minus(particle.position).hadamard(randNumsGlobal).scale(psoInfo.accelGlobal)

        particle.velocity = checkVelocityBound(newVelocity, psoInfo)
        particle.position = (particle.position + particle.velocity).MGSON()
    }

    private fun calcLocalBestPosition(swarm: Swarm<VelocityParticle>, particle: VelocityParticle, neighborhoodSize: Int): SimpleMatrix {
        return particlesNear(swarm, particle, neighborhoodSize).minByOrNull { it.bestCost }!!.bestPosition
    }

    private fun particlesNear(swarm: Swarm<VelocityParticle>, particle: VelocityParticle, neighborhoodSize: Int): List<Particle> {
        val distances = { it: Particle -> (it.position - particle.position).allElements().map { it * it }.sum() }
        return swarm.particles.toList().map { Pair(it, distances(it)) }.sortedBy { it.second }.take(neighborhoodSize).map { it.first }
    }

    private fun checkVelocityBound(velocity: SimpleMatrix, psoInfo: PSOInfo): SimpleMatrix {
        val vMax = psoInfo.bounds.size / psoInfo.vMaxN

        val rVelocity = SimpleMatrix(velocity)

        for (i in 0 until rVelocity.numElements) {
            if (rVelocity[i] > vMax) rVelocity[i] = vMax
            if (rVelocity[i] < -vMax) rVelocity[i] = -vMax
        }

        return rVelocity
    }

    override val bestCost: Double
        get() = swarms.map { it.globalBestCost }.minOrNull()!!
}