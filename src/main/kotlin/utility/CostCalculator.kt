package utility

import org.ejml.simple.SimpleMatrix

class CostCalculator(modelFunction: (SimpleMatrix) -> SimpleMatrix): ModelIntegralCalculator(modelFunction) {

    fun cost(m: SimpleMatrix): Double {
        val mbarm = m.rightInverse().mult(m)

        return integral {
            val x = randMatrix(m.numCols(), 0, xRange)

            specificCost(m, mbarm, x)
        }
    }

}