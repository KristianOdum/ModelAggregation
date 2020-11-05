package gradientDescent

import org.ejml.simple.SimpleMatrix
import utility.ModelInfo

class SimpleGD(val modelInfo: ModelInfo, val learningRate: Double) : GradientDescent(modelInfo) {
    override fun step(): SimpleMatrix {
        return lumpingMatrix + gradient.scale(-learningRate)
    }
}