package utility

import org.ejml.simple.SimpleMatrix
import strWidth
import kotlin.math.pow

var globaldata = ""

class CostCalculator(modelFunction: (SimpleMatrix) -> SimpleMatrix) : ModelIntegralCalculator(modelFunction) {

    fun cost(m: SimpleMatrix): Double {
        val mbarm = m.rightInverse().mult(m)

        return integral {
            val x = randMatrix(m.numCols(), 1, xRange)

            val c = specificCost(m, mbarm, x).pow(2.0)

            val fmbarmx = modelFunction(mbarm.mult(x)).normF()
            val fx = modelFunction(x).normF()

            globaldata = "${fx.strWidth(12)}\t${fmbarmx.strWidth(12)}"

            c
        }
    }

}