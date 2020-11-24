package gradientDescent

import org.ejml.simple.SimpleMatrix
import utility.*
import kotlin.math.pow

class DerivativeCalculator(modelFunction: (SimpleMatrix) -> SimpleMatrix) : ModelIntegralCalculator(modelFunction) {
    companion object {
        private const val h = 1.0E-8
    }

    fun derivative(m: SimpleMatrix, e: Int): Double {
        val h1 = (m[e] + h) - m[e]
        val h2 = (m[e] - h) - m[e]

        val mp = m.withSet(e, m[e] + h1).rowNorm()
        val mpbarmp = mp.rightInverse().mult(mp)

        val mn = m.withSet(e, m[e] + h2).rowNorm()
        val mnbarmn = mn.rightInverse().mult(mn)

        return integral {
            val x = randMatrix(m.numCols(), 1, xRange)
            (specificCost(mp, mpbarmp, x).pow(2.0) - specificCost(mn, mnbarmn, x).pow(2.0)) / (h1 - h2)
        }
    }


    // Calculates d/dalpha C(m + d*alpha)
    fun oneDimensionalDerivative(m: SimpleMatrix, d: SimpleMatrix, alpha: Double): Double {
        val h1 = (alpha + h) - alpha
        val h2 = -(alpha - h) + alpha

        val mp = m.plus(d.scale(alpha + h1))
        val mpbarmp = mp.rightInverse().mult(mp)

        val mn = m.plus(d.scale(alpha - h2))
        val mnbarmn = mn.rightInverse().mult(mn)

        return integral {
            val x = randMatrix(m.numCols(), 1, xRange)
            (specificCost(mp, mpbarmp, x) - specificCost(mn, mnbarmn, x)) / (h1 + h2)
        }
    }

}