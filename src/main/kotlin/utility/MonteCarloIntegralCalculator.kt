package utility

import Average
import org.ejml.simple.SimpleMatrix
import kotlin.math.pow

abstract class MonteCarloIntegralCalculator {
    var tolerance: Double = 0.01
    var clusterSize: Int = 50
    var xRange = 1.0 until 100.0

    var plotter = MapleExporter()
    var plotdata = plotter.addSeries("Integration")

    protected fun integral(action: () -> Double): Double {
        var integral = Average()
        var integral2 = Average()
        var i = 0L
        var sigma2 = Double.MAX_VALUE
        val tolerance2 = tolerance.pow(2)

        while (sigma2 > tolerance2 * integral.value.pow(2)) {
            val a = action()

            integral.add(a)
            integral2.add(a.pow(2))

            i++

            if (i > 10) {
                sigma2 = sigmaSquared(integral.value, integral2.value, i)
            }

        }

        return integral.value
    }

    private fun sigmaSquared(e: Double, es: Double, N: Long) = (es - e.pow(2.0)) / (N - 1)
}

abstract class ModelIntegralCalculator(val modelFunction: (SimpleMatrix) -> SimpleMatrix) : MonteCarloIntegralCalculator() {
    fun specificCost(m: SimpleMatrix, mbarm: SimpleMatrix, x: SimpleMatrix) =
            m.mult(modelFunction(x).minus(modelFunction(mbarm.mult(x)))).normF()
}