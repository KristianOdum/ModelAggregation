package gradientDescent

import org.ejml.simple.SimpleMatrix
import utility.ModelInfo

class ExtendedSimpleDynamicGD(modelInfo: ModelInfo, val learningRate: Double = 0.003) : GradientDescent(modelInfo) {
    override fun step(): SimpleMatrix {
        TODO("Not yet implemented")
    }

}