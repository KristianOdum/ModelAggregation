package gradientDescent

import org.ejml.simple.SimpleMatrix
import utility.ModelInfo
import utility.create
import kotlin.math.min

class ExtendedDynamicGD(modelInfo: ModelInfo, var learningRate: Double = 0.003) : GradientDescent(modelInfo) {
    private var learningRates = lumpingMatrix.create { _ -> learningRate }
    private var previousGradient = lumpingMatrix.create { _ -> 0.0 }

    // Extended
    private var t = 0


    init {
        costCalculator.tolerance = 1.0E-2
    }

    override fun step(): SimpleMatrix {
        val delta = lumpingMatrix.create { _ -> 0.0 }
        for (i in 0 until lumpingMatrix.numElements) {
            if (gradient[i] * previousGradient[i] < 0) {
                learningRates[i] *= 0.7
            } else {
                learningRates[i] *= 1.2
                learningRates[i] = min(learningRates[i], 1.0)
            }

            if (gradient[i] > 0)
                delta[i] = -learningRates[i]
            else
                delta[i] = learningRates[i]
        }

        previousGradient = gradient
        t++
        return delta
    }
}