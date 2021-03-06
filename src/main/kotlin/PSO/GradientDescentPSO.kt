package PSO

import gradientDescent.GradientDescent
import utility.*

class GradientDescentParticle<GDType>(columns: Int, rows: Int, bounds: OpenEndDoubleRange, factory: () -> GDType) : Particle(columns, rows, bounds) where GDType : GradientDescent {
    val gd = factory().apply { updateGradient = false }
    var plateauCount = 0
}

class GradientDescentPSO<GDType>(modelInfo: ModelInfo, swarmCount: Int, particleCount: Int, gdFactory: () -> GDType, costCalculator: CostCalculator,
                                 lockedRowCount: Int
) : ParticleSwarmOptimization<GradientDescentParticle<GDType>>(modelInfo, costCalculator, lockedRowCount) where GDType: GradientDescent {
    override var swarms = MutableList(swarmCount) { Swarm(MutableList(particleCount) { GradientDescentParticle(modelInfo.lumpingMatrix.numCols(), modelInfo.lumpingMatrix.numRows(), -1.0 until 1.0, gdFactory)} ) }

    companion object {
        private val plateauLimit = 5
    }

    override fun updateParticle(swarm: Swarm<GradientDescentParticle<GDType>>, particle: GradientDescentParticle<GDType>) {
        particle.apply {
            if (plateauCount >= plateauLimit) {
                swarm.particles.remove(this)
                return
            }

            gd.apply {
                updateGradient()
                gradient = (gradient - (swarm.globalBestPosition - lumpingMatrix).scale(0.2)).normalize()
                iterate()
            }
            position = gd.lumpingMatrix
        }
    }

}