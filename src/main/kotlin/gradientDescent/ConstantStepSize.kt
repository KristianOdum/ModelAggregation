package gradientDescent

import org.ejml.simple.SimpleMatrix

class SimpleStep(val learningRate: Double) : GradientDescentStep {
    override fun step(gd: GradientDescent): SimpleMatrix {
        return gd.lumpingMatrix + gd.gradient.scale(-learningRate)
    }
}