package gradientDescent

import org.ejml.simple.SimpleMatrix
import utility.CostCalculator
import utility.ModelInfo
import utility.create
import kotlin.math.pow
import kotlin.math.sqrt

class BiasCorrectedADAMGD(modelInfo: ModelInfo, private val learningRate: Double = 0.003, derivativeCalculator: DerivativeCalculator, costCalculator: CostCalculator) : GradientDescent(modelInfo, derivativeCalculator, costCalculator) {
    private val beta1 = 0.9
    private val beta2 = 0.999
    private val epsilon = 1.0E-12
    private var adamM = lumpingMatrix.create { _ -> 0.0 }
    private var adamV = lumpingMatrix.create { _ -> 0.0 }
    private var t = 1

    override fun step(): SimpleMatrix {
        val delta = lumpingMatrix.create { _ -> 0.0 }

        for (i in 0 until lumpingMatrix.numElements) {
            adamM[i] = beta1 * adamM[i] + (1.0 - beta1) * gradient[i]
            adamV[i] = beta2 * adamV[i] + (1.0 - beta2) * gradient[i].pow(2.0)

            val mHat = adamM[i] / (1.0 - beta1.pow(t.toDouble()))
            val vHat = adamV[i] / (1.0 - beta2.pow(t.toDouble()))

            delta[i] = -(learningRate * mHat) / (sqrt(vHat) + epsilon)
        }

        t++
        return delta
    }
}