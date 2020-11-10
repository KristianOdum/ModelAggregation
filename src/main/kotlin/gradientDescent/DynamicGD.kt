package gradientDescent

import org.ejml.simple.SimpleMatrix
import utility.ModelInfo
import utility.create

open class DynamicGD(modelInfo: ModelInfo, var learningRate: Double = 0.003) : GradientDescent(modelInfo) {
    private var learningRates = lumpingMatrix.create { _ -> learningRate }
    private var previousGradient = lumpingMatrix.create { _ -> 0.0 }

    init {
        costCalculator.tolerance = 1.0E-2
    }

    override fun step(): SimpleMatrix {
        val delta = lumpingMatrix.create { _ -> 0.0 }

        for (i in 0 until lumpingMatrix.numElements) {
            if (gradient[i] * previousGradient[i] < 0) {
                learningRates[i] *= 0.5
            } else
                learningRates[i] *= 1.2

            if (gradient[i] > 0)
                delta[i] = -learningRates[i]
            else
                delta[i] = learningRates[i]
        }

        previousGradient = gradient
        return delta
    }
}