package utility

import org.ejml.simple.SimpleMatrix
import kotlin.math.pow

class CostCalculator(modelFunction: (SimpleMatrix) -> SimpleMatrix): ModelIntegralCalculator(modelFunction) {

    fun cost(m: SimpleMatrix): Double {
        val mbarm = m.rightInverse().mult(m)

        return integral {
            val x = randMatrix(m.numCols(), 1, xRange)

            specificCost(m, mbarm, x).pow(2.0)
        }
    }

}