package gradientDescent

import org.ejml.simple.SimpleMatrix
import utility.*

abstract class GradientDescent(modelInfo: ModelInfo) {
    var lumpingMatrix = modelInfo.lumpingMatrix
        set(value) {
            hasLumpingMatrixChanged = true
            field = value
        }

    var gradient = SimpleMatrix()

    protected val modelFunction = modelInfo.function
    protected val costCalculator = CostCalculator(modelFunction)
    protected val derivativeCalculator = DerivativeCalculator(modelFunction)

    private var hasLumpingMatrixChanged = true
    var cost = Double.MAX_VALUE
        get() {
            if (hasLumpingMatrixChanged) {
                hasLumpingMatrixChanged = false
                field = costCalculator.cost(lumpingMatrix)
            }
            return field
    }
        private set;

    fun iterate(updateGradient: Boolean = true) {
        if (updateGradient)
            updateGradient()

        val delta = step()

        lumpingMatrix = (lumpingMatrix + delta).MGSON()
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