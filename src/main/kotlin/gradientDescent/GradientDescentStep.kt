package gradientDescent

import org.ejml.simple.SimpleMatrix

interface GradientDescentStep {
    fun step(gd: GradientDescent): SimpleMatrix
}