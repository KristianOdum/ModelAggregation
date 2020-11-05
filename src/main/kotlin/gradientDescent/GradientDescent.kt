package gradientDescent

import org.ejml.simple.SimpleMatrix
import utility.*

abstract class GradientDescent(modelInfo: ModelInfo) {
    var lumpingMatrix = modelInfo.lumpingMatrix
        private set(value) {
            hasLumpingMatrixChanged = true
            field = value
        }
    var gradient = SimpleMatrix()
        private set

    private val modelFunction = modelInfo.function
    private val costCalculator = CostCalculator(modelFunction)
    private val derivativeCalculator = DerivativeCalculator(modelFunction)

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

    fun iterate() {
        gradient = gradient().normalize()

        val delta = step()

        lumpingMatrix = (lumpingMatrix + delta).MGSON()
    }

    protected abstract fun step(): SimpleMatrix

    private fun gradient(): SimpleMatrix {
        return SimpleMatrix(lumpingMatrix.numRows(), lumpingMatrix.numCols()).create {
            e -> derivativeCalculator.derivative(lumpingMatrix, e)
        }
    }
}