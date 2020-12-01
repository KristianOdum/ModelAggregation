package gradientDescent

import org.ejml.simple.SimpleMatrix
import utility.ModelInfo
import utility.create
import kotlin.math.pow
import kotlin.math.sqrt

class MRMSPropGD(modelInfo: ModelInfo, learningRate: Double = 0.003) : AbstractDynamicGD(modelInfo = modelInfo, learningRate) {
    private var prevGradSquared = lumpingMatrix.create { _ -> 0.0 }
    private val gradSquared = lumpingMatrix.create { _ -> 0.0 }
    private val beta = 0.90
    private val epsilon = 1.0E-8
    private var t = 0

    override fun delta(): SimpleMatrix {
        val delta = lumpingMatrix.create { _ -> 0.0 }

        for (i in 0 until lumpingMatrix.numElements) {
            gradSquared[i] = (beta * prevGradSquared[i]) + ((1.0 - beta) * (gradient[i].pow(2.0)))
            delta[i] = -(learningRates[i] / sqrt(gradSquared[i] + epsilon)) * gradient[i]
        }
        prevGradSquared = gradSquared.copy()
        t++
        return delta
    }
}