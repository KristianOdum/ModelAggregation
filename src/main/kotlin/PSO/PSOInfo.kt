package PSO

import utility.OpenEndDoubleRange
import utility.until

data class PSOInfo(
        val maxIterations: Int,
        val vMaxN: Int = 10,
        val omegaMax: Double = 0.7,
        val omegaMin: Double = 0.4,
        val accelParticle: Double = 1.6,
        val accelGlobal: Double = 1.6,
        val accelLocal: Double = 1.6,
        val bounds: OpenEndDoubleRange = -1.0 until 1.0,
        val neighborhoodSize: Int = 5,
        val tLocal: Int = 100,
        val tGlobal: Int = 100
)
