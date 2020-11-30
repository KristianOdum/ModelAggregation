package utility

import Average
import org.ejml.simple.SimpleMatrix
import strWidth
import java.awt.Color
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

abstract class MonteCarloIntegralCalculator {
    var tolerance: Double = 0.05
    var clusterSize: Int = 50
    var xRange = 1.0 until 100.0

    var largest = 0.0


    protected fun integral(action: () -> Double): Double {
        val integral = Average()
        val integral2 = Average()
        var i = 0L
        var sigma2 = Double.MAX_VALUE
        val tolerance2 = tolerance.pow(2)

        var skipped = 0
        val skipRate = { skipped.toDouble() / (skipped + i) }

        while (i < 1000 || sigma2 > tolerance2 * integral.value.pow(2)) {
            val a = action()

            if (abs(integral.value - integral.tempAdd(a)) > 100 * integral.value / i && skipRate() < 0.5) {
                skipped++
                //println("SKIPPED\t${skipRate().strWidth(12)}\t${a.strWidth(12)}\t${integral.value.strWidth(12)}\t(f(x), f(mbarmx)): $globaldata")
                continue
            }

            integral.add(a)
            integral2.add(a.pow(2))

            i++

            sigma2 = sigmaSquared(integral.value, integral2.value, i)

        }

        //println("Estimation: ${integral.value.strWidth(12)}\tSkipRate: ${(skipped.toDouble() / (skipped + i)).strWidth(12)}")

        return integral.value
    }

    private fun sigmaSquared(e: Double, es: Double, N: Long) = (es - e.pow(2.0)) / (N - 1)
}

abstract class ModelIntegralCalculator(val modelFunction: (SimpleMatrix) -> SimpleMatrix) : MonteCarloIntegralCalculator() {
    fun specificCost(m: SimpleMatrix, mbarm: SimpleMatrix, x: SimpleMatrix) =
            m.mult(modelFunction(x).minus(modelFunction(mbarm.mult(x)))).normF()
}