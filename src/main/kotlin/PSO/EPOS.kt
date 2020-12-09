package PSO

import org.ejml.simple.SimpleMatrix
import utility.*
import kotlin.math.absoluteValue
import kotlin.math.pow
import kotlin.math.sqrt

class StagnationParticle(columns: Int, rows: Int, bounds: OpenEndDoubleRange) : Particle(columns, rows, bounds){
    var stagnationCount = 0

    override var bestCost: Double = Double.MAX_VALUE
        get() = field
        set(value) {
            field = value
            stagnationCount = 0
        }
}

class EPSO(modelInfo: ModelInfo, swarmCount: Int, particleCount: Int, val psoInfo: PSOInfo, costCalculator: CostCalculator,
           lockedRowCount: Int
) : ParticleSwarmOptimization<StagnationParticle>(modelInfo, costCalculator, lockedRowCount) {
    override var swarms = MutableList(swarmCount) { Swarm(
            MutableList(particleCount) { StagnationParticle(modelInfo.lumpingMatrix.numCols(), modelInfo.lumpingMatrix.numRows(), -1.0 until 1.0) }
    ) }

    override fun updateParticle(swarm: Swarm<StagnationParticle>, particle: StagnationParticle) {
        particle.apply {
            var bestPositionPrime = SimpleMatrix(position).create { i -> 1.0 }
            var globalBestPositionPrime = SimpleMatrix(position).create { i -> 1.0 }

            if (stagnationCount <= psoInfo.tLocal)
                bestPositionPrime = randMatrix(bestPosition.numRows(), bestPosition.numCols(), 0.0 until 1.0).hadamard(bestPosition)
            if(swarm.stagnationCount <= psoInfo.tGlobal)
                globalBestPositionPrime = randMatrix(swarm.globalBestPosition.numRows(), swarm.globalBestPosition.numCols(), 0.0 until 1.0).hadamard(swarm.globalBestPosition)

            var n = 0.0
            for (i in 0 until position.numElements)
                n += (position[i] - swarm.globalBestPosition[i]).pow(2)
            if(n.absoluteValue < 0.0000001)
                return
            val nil = (position - swarm.globalBestPosition).divide(sqrt(n))

            val omega = psoInfo.omegaMax - (psoInfo.omegaMax - psoInfo.omegaMin) / psoInfo.maxIterations * swarm.currentIteration
            val randNumsParticle = randMatrix(particle.position.numRows(), particle.position.numCols(), 0.0 until 1.0)
            val randNumsGlobal = randMatrix(particle.position.numRows(), particle.position.numCols(), 0.0 until 1.0)

            position = position.hadamard(nil).scale(omega) +
                    globalBestPositionPrime.minus(particle.position).hadamard(randNumsGlobal).scale(psoInfo.accelGlobal) +
                    bestPositionPrime.minus(particle.position).hadamard(randNumsParticle).scale(psoInfo.accelParticle)
        }
    }

    override var lockedRowCount: Int
        get() = TODO("Not yet implemented")
        set(value) {}
}