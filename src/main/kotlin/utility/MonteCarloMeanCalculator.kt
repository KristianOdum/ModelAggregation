package utility

import Average
import org.ejml.simple.SimpleMatrix
import kotlin.math.*

class MonteCarloMeanCalculator(dimensionCount: Int) : MeanCalculator(dimensionCount) {

    override fun calculate(evalpoint: (x: SimpleMatrix) -> Double): Double {
        val integral = Average()
        val integral2 = Average()
        var i = 0L
        var sigma2 = Double.MAX_VALUE
        val tolerance2 = tolerance.pow(2)

        val maxi = 100_000 / tolerance

        while (i < maxi && (i < 1000 || sigma2 > tolerance2 * integral.value.pow(2))) {
            val x = randMatrix(dimensionCount, 1, xRange)
            val a = evalpoint(x)

            integral.add(a)
            integral2.add(a.pow(2))

            i++

            sigma2 = sigmaSquared(integral.value, integral2.value, i)
        }

        //println("${this.javaClass.name}\tIterations: $i\tEstimation: ${integral.value.strWidth(12)}\tSkipRate: ${(skipped.toDouble() / (skipped + i)).strWidth(12)}")

        return integral.value
    }

    private fun sigmaSquared(e: Double, es: Double, N: Long) = (es - e.pow(2.0)) / (N - 1)
}