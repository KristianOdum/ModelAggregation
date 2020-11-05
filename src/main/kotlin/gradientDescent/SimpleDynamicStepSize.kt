package gradientDescent

import org.ejml.simple.SimpleMatrix

class SimpleDynamicStepSize() : GradientDescentStep {
    val prevG = SimpleMatrix()

    override fun step(gd: GradientDescent): SimpleMatrix {
        TODO("Not yet implemented")
    }
}