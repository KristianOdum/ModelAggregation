package gradientDescent

import CostFunctionOptimizer
import org.ejml.simple.SimpleMatrix
import utility.*

abstract class GradientDescent(modelInfo: ModelInfo, protected val derivativeCalculator: DerivativeCalculator, protected val costCalculator: CostCalculator, var updateGradient: Boolean = true) : CostFunctionOptimizer {
    var lumpingMatrix = modelInfo.lumpingMatrix.MGSON()
        set(value) {
            hasLumpingMatrixChanged = true
            field = value
        }

    var gradient = SimpleMatrix()

    protected val modelFunction = modelInfo.function

    private var hasLumpingMatrixChanged = true
    var cost = Double.MAX_VALUE
        get() {
            if (hasLumpingMatrixChanged) {
                hasLumpingMatrixChanged = false
                field = costCalculator.cost(lumpingMatrix)
                if (field < bestCost) {
                    bestCost = field
                }
            }
            return field
    }
        private set
    override var bestCost = Double.MAX_VALUE

    override fun iterate() {
        if (updateGradient)
            updateGradient()

        val delta = step()

        lumpingMatrix = (lumpingMatrix + delta).MGSON()
        cost
    }

    fun updateGradient() {
        gradient = gradient().normalize()
    }

    protected abstract fun step(): SimpleMatrix

    private fun gradient(): SimpleMatrix {
        return SimpleMatrix(lumpingMatrix.numRows(), lumpingMatrix.numCols()).create {
            e -> derivativeCalculator.derivative(lumpingMatrix, e)
        }
    }
}