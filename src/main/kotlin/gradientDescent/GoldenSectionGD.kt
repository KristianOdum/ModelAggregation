package gradientDescent

import org.ejml.simple.SimpleMatrix
import utility.CostCalculator
import utility.ModelInfo
import kotlin.math.abs

class GoldenSectionGD(modelInfo: ModelInfo, var beta: Double = 1.0): GradientDescent(modelInfo) {
    var tolerance = 1.0E-3
    var alpha = Double.NaN
        private set

    companion object {
        private const val goldenRatio = 1.61803398875
    }

    private val GSScostCalculator = CostCalculator(modelFunction)

    override fun step(): SimpleMatrix {
        alpha = gss { s ->  GSScostCalculator.cost(lumpingMatrix + gradient.scale(-s)) }

        beta = 0.6 * (beta - 2*alpha) + 2*alpha

        return gradient.scale(-alpha)
    }

    private fun gss(cost: (Double) -> Double): Double {
        var a = 0.0
        var b = beta
        var c = b - (b - a) / goldenRatio
        var d = a + (b - a) / goldenRatio

        var fc = cost(c)
        var fd = cost(d)

        while (abs(c - d) > beta * tolerance) {
            if (fc < fd) {
                b = d
                d = c
                fd = fc
                c = b - (b - a) / goldenRatio
                fc = cost(c)
            } else {
                a = c
                c = d
                fc = fd
                d = a + (b - a) / goldenRatio
                fd = cost(d)
            }
        }

        return (a + b) / 2
    }

}