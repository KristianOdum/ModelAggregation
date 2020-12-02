package utility

import org.ejml.simple.SimpleMatrix

abstract class MeanCalculator(protected val dimensionCount: Int, var tolerance: Double = 0.05) {
    protected var xRange = 1.0 until 100.0

    abstract fun calculate(evalpoint: (x: SimpleMatrix) -> Double): Double
}