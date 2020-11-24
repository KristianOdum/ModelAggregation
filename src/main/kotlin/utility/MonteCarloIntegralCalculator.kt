package utility

import Average
import org.ejml.simple.SimpleMatrix
import kotlin.math.abs
import kotlin.math.log10
import kotlin.math.pow
import kotlin.math.sqrt

abstract class MonteCarloIntegralCalculator {
    var tolerance: Double = 0.1
    var clusterSize: Int = 50
    var xRange = 0.0 until 1000.0

    var plotter = MapleExporter()
    var plotdata = plotter.addSeries("Integration")

    protected fun integral(action: () -> Double): Double {
        var integral = Average()
        var integral2 = Average()
        var i = 0L
        var sigmaSquared = Double.MAX_VALUE
        val tolerance2 = tolerance.pow(2)

        while (sigmaSquared > tolerance2) {
            val a = action()

            integral.add(a)
            integral2.add(a.pow(2))

            i++

            if (i > 1000)
                sigmaSquared = sigmaSquared(integral.value, integral2.value, i)

            if (i % 1_000_000 == 0L) {
                plotdata.xy((i / 1_000_000L).toInt(), integral.value)
                print("\r${i.toDouble() / 10_000_000_000L}")
            }

            if (i > 10_000_000_000L) {
                plotter.export()
                return integral.value
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