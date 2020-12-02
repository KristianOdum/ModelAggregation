package gradientDescent

import org.ejml.simple.SimpleMatrix
import utility.CostCalculator
import utility.ModelInfo
import utility.create
import kotlin.math.*

class MomentumGD(modelInfo: ModelInfo, private val learningRate: Double = 0.003, private val epochs: Int, derivativeCalculator: DerivativeCalculator, costCalculator: CostCalculator) : GradientDescent(modelInfo, derivativeCalculator, costCalculator) {
    private val velocities = lumpingMatrix.create { _ -> 0.0 }
    private var beta = 0.5
    private var betaMax = 0.9
    private var t = 1
    private val coefStep = (betaMax - beta) / epochs.toDouble()

    override fun step(): SimpleMatrix {
        val delta = lumpingMatrix.create { _ -> 0.0 }

        beta += coefStep
        for (i in 0 until lumpingMatrix.numElements) {
            velocities[i] = beta * velocities[i] + (1 - beta) * gradient[i]
            velocities[i] = velocities[i] / (1 - beta.pow(t.toDouble()))

            delta[i] = -(learningRate * velocities[i])
        }

        t++
        return delta
    }
}