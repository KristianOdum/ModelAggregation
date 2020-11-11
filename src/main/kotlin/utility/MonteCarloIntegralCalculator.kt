package utility

import org.ejml.simple.SimpleMatrix
import kotlin.math.abs

abstract class MonteCarloIntegralCalculator {
    var tolerance: Double = 1.0E-3
    var clusterSize: Int = 50
    var xRange = 0.0 until 1000.0

    protected fun integral(action: () -> Double): Double {
        val averages = mutableListOf<Double>()
        var i = 0

        while (averages.size < 5 || averages.dropLast(1).map { abs(it - averages.last()) }.maxOrNull()!! > abs(averages.last() * tolerance)) {

            var clusterAverage = 0.0
            repeat(clusterSize) {
                val e = action()
                clusterAverage = clusterAverage * (it.toDouble() / (it + 1)) + e / (it + 1)
            }

            if (averages.size >= 5) {
                for (j in 0 until averages.size - 1)
                    averages[j] = averages[j + 1]
                averages[4] = averages[3] * i.toDouble() / (i + 1) + clusterAverage / (i + 1)
            } else {
                val na = if (averages.size > 1) averages.last() * i.toDouble() / (i + 1) + clusterAverage / (i + 1) else clusterAverage
                averages.add(na)
            }

            i++
        }
        return averages.last()
    }
}

abstract class ModelIntegralCalculator(val modelFunction: (SimpleMatrix) -> SimpleMatrix) : MonteCarloIntegralCalculator() {
    fun specificCost(m: SimpleMatrix, mbarm: SimpleMatrix, x: SimpleMatrix) =
            m.mult(modelFunction(x).minus(modelFunction(mbarm.mult(x)))).normF()
}