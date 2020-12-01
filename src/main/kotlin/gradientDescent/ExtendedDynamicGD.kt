package gradientDescent

import org.ejml.simple.SimpleMatrix
import utility.ModelInfo
import utility.allElements
import utility.create

class ExtendedDynamicGD(modelInfo: ModelInfo, private val learningRate: Double = 0.003) : AbstractDynamicGD(modelInfo, learningRate) {
    private var t = 0
    private val maxLearningRate: Double
        get() { return learningRate / t.toDouble() }

    private val momentum = lumpingMatrix.create { _ -> 0.0 }
    private val decayRate = 0.90

    override fun delta(): SimpleMatrix {
        val delta = lumpingMatrix.create { _ -> 0.0 }

        if(t % 100 == 0) {
            val smallest = learningRates.allElements().minOrNull()!!

            for (i in 0 until lumpingMatrix.numElements) {
                learningRates[i] = smallest
            }
        }

        for (i in 0 until lumpingMatrix.numElements) {
            if (gradient[i] > 0)
                delta[i] = -learningRates[i]
            else
                delta[i] = learningRates[i]

            momentum[i] = decayRate * momentum[i] + (1 - decayRate) * delta[i]
        }

        t++
        return momentum
    }
}