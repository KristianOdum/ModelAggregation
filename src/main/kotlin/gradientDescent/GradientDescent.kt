package gradientDescent

import CostFunctionOptimizer
import org.ejml.simple.SimpleMatrix
import utility.*

abstract class GradientDescent(modelInfo: ModelInfo, protected val derivativeCalculator: DerivativeCalculator,
                               protected val costCalculator: CostCalculator, var updateGradient: Boolean = true,
                               override var lockedRowCount: Int = 0) : CostFunctionOptimizer {
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
    final override var bestCost: Double

    override fun iterate() {
        if (updateGradient)
            updateGradient()

        val delta = step()

        lumpingMatrix = (lumpingMatrix + delta).MGSON()
        cost
    }

    init {
        bestCost = costCalculator.cost(lumpingMatrix)
    }

    fun updateGradient() {
        gradient = gradient().normalize()
    }

    protected abstract fun step(): SimpleMatrix

    private fun gradient(): SimpleMatrix {
        var g = SimpleMatrix(lumpingMatrix.numRows(), lumpingMatrix.numCols()).create {
                e -> derivativeCalculator.derivative(lumpingMatrix, e)
        }

        for (i in 0 until lockedRowCount)
            g = g.setRow(i, SimpleMatrix(g.numCols(), 1).create { _ -> 0.0 })

        return g
    }
}