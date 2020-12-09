package utility

class IntegralEstimation() {
    var accintegral = 0.0
        private set
    var accintegral2 = 0.0
        private set
    var space = 0.0
        private set

    val integral get() = accintegral / i

    private var i = 0L

    fun add(value: Double, space: Double) {
        accintegral += value
        accintegral2 += value * value
        this.space += space
        i++
    }

    val sigma2 get() = MeanCalculator.sigmaSquared(accintegral / i, accintegral2 / i, i)
}