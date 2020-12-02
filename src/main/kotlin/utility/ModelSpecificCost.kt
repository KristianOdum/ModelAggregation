package utility

import org.ejml.simple.SimpleMatrix

abstract class ModelSpecificCost(val modelFunction: (SimpleMatrix) -> SimpleMatrix) {
    protected fun specificCost(m: SimpleMatrix, mbarm: SimpleMatrix, x: SimpleMatrix) =
            m.mult(modelFunction(x).minus(modelFunction(mbarm.mult(x)))).normF()
}