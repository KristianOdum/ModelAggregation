package gradientDescent

import org.ejml.simple.SimpleMatrix
import utility.CostCalculator
import utility.ModelInfo
import utility.create

abstract class AbstractDynamicGD(modelInfo: ModelInfo, val learningRate: Double = 0.003, derivativeCalculator: DerivativeCalculator, costCalculator: CostCalculator) : GradientDescent(modelInfo, derivativeCalculator, costCalculator) {
    val learningRates = lumpingMatrix.create { _ -> learningRate }
    private var previousGradient: SimpleMatrix

    init {
        updateGradient()
        previousGradient = gradient.copy()
    }

    override fun step(): SimpleMatrix {
        for (i in 0 until lumpingMatrix.numElements) {
            if (gradient[i] * previousGradient[i] < 0)
                learningRates[i] *= 0.5
            else
                learningRates[i] *= 1.2
        }

        previousGradient = gradient.copy()

        return delta()
    }

    abstract fun delta() : SimpleMatrix
}