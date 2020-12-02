package gradientDescent

import org.ejml.simple.SimpleMatrix
import utility.CostCalculator
import utility.ModelInfo

class SimpleGD(modelInfo: ModelInfo, val learningRate: Double, derivativeCalculator: DerivativeCalculator, costCalculator: CostCalculator) : GradientDescent(modelInfo, derivativeCalculator, costCalculator) {
    override fun step(): SimpleMatrix {
        return lumpingMatrix + gradient.scale(-learningRate)
    }
}