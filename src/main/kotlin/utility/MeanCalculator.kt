package utility

import org.ejml.simple.SimpleMatrix
import kotlin.math.pow

abstract class MeanCalculator(protected val dimensionCount: Int, var tolerance: Double) {
    val xRange = 1.0 until 100.0

    abstract fun calculate(evalpoint: (x: SimpleMatrix) -> Double): Double

    companion object {
        fun sigmaSquared(e: Double, es: Double, N: Long) = (es - e.pow(2.0)) / (N - 1)
    }
}