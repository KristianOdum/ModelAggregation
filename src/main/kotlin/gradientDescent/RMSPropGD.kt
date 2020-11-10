package gradientDescent

import org.ejml.simple.SimpleMatrix
import utility.ModelInfo
import utility.create
import kotlin.math.*

class RMSPropGD(modelInfo: ModelInfo, val learningRate: Double = 0.003) : GradientDescent(modelInfo = modelInfo) {
    private val prevGradSquared = lumpingMatrix.create { _ -> 0.0 }
    private val gradSquared = lumpingMatrix.create { _ -> 0.0 }
    private val beta = 0.98
    private val epsilon = 1.0E-8

    override fun step(): SimpleMatrix {
        val delta = lumpingMatrix.create { _ -> 0.0 }

        for (i in 0 until lumpingMatrix.numElements) {
            gradSquared[i] = (beta * prevGradSquared[i]) + ((1.0 - beta) * (gradient[i].pow(2.0)))
            delta[i] = -(learningRate / sqrt(gradSquared[i] + epsilon)) * gradient[i]
        }

        return delta
    }
}