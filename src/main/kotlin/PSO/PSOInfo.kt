package PSO

data class PSOInfo(val maxIterations: Int, val vMaxN: Int = 10, val omegaMax: Double = 0.7, val omegaMin: Double = 0.4, val accelParticle: Double = 1.6, val accelGlobal: Double = 1.6, val accelLocal: Double = 1.6, val lowerBound: Double = -1.0, val upperBound: Double = 1.0,)
