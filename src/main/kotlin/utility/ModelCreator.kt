package utility

import org.ejml.simple.SimpleMatrix

interface ModelCreator {
    fun random(size: Int, reduction: Int): ModelInfo
}

class ToyModelCreator: ModelCreator {
    override fun random(size: Int, reduction: Int): ModelInfo {
        val toyF = { x: SimpleMatrix ->
            val y = SimpleMatrix(x.numRows(), x.numCols())
            y[0,0] = x[0,0] * x[2,0] + x[2,0]
            y[1,0] = x[1,0] * x[2,0] + x[2,0]
            y[2,0] = -x[2,0]
            y
        }

        return ModelInfo(randMatrix(3 - reduction, 3, 0.0 until 1.0), toyF)
    }
}

class SIRModelCreator: ModelCreator {

    fun fromValues(m: SimpleMatrix, recoveryRates: SimpleMatrix, contactRates: SimpleMatrix): ModelInfo {
        if (recoveryRates.numCols() != 1 || recoveryRates.numRows() != contactRates.numCols() || contactRates.numCols() != contactRates.numRows())
            throw IllegalArgumentException("contactRates must be square and have same count as recoveryRates which must be column vector")

        val count = recoveryRates.numElements
        val f = { x: SimpleMatrix ->
            val res = SimpleMatrix(count * 3, 1)

            for (k in 0 until recoveryRates.numElements) {
                val infectionRate = x[3*k] * (0 until count).sumByDouble { l -> contactRates[k, l] * x[3*k+1] }
                val recoveryRate = recoveryRates[k] * x[3*k+1]

                res[3*k] = -infectionRate
                res[3*k+1] = -recoveryRate + infectionRate
                res[3*k+2] = recoveryRate
            }
            res
        }
        return ModelInfo(m, f)
    }

    override fun random(size: Int, reduction: Int): ModelInfo {
        val fc = 3 * size
        return fromValues(randMatrix(fc - reduction, fc, -1.0 until 1.0), randMatrix(fc, 1, 0.0 until 1.0), randMatrix(fc, fc, 0.0 until 1.0))
    }

}