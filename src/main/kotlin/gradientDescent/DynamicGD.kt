package gradientDescent

import org.ejml.simple.SimpleMatrix
import utility.CostCalculator
import utility.ModelInfo
import utility.create

open class DynamicGD(modelInfo: ModelInfo, var learningRate: Double = 0.003, derivativeCalculator: DerivativeCalculator, costCalculator: CostCalculator) : GradientDescent(modelInfo, derivativeCalculator, costCalculator) {
    private var learningRates = lumpingMatrix.create { _ -> learningRate }
    private var previousGradient = lumpingMatrix.create { _ -> 0.0 }

    override fun step(): SimpleMatrix {
        val delta = lumpingMatrix.create { _ -> 0.0 }

        for (i in 0 until lumpingMatrix.numElements) {
            if (gradient[i] > 0)
                delta[i] = -learningRates[i]
            else
                delta[i] = learningRates[i]
        }

        return delta
    }
}