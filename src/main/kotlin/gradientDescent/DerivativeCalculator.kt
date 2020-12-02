package gradientDescent

import org.ejml.simple.SimpleMatrix
import utility.*
import kotlin.math.*

open class DerivativeCalculator(private val meanCalculator: MonteCarloMeanCalculator, modelFunction: (SimpleMatrix) -> SimpleMatrix, private val transformer: InvertibleTransformation = IdentityTransformer()) : ModelSpecificCost(modelFunction) {
    companion object {
        private const val h = 1.0E-8
    }

    fun derivative(m: SimpleMatrix, e: Int): Double {
        val h1 = (m[e] + h) - m[e]
        val h2 = (m[e] - h) - m[e]

        val mp = m.withSet(e, m[e] + h1)
        val mpbarmp = mp.rightInverse().mult(mp)

        val mn = m.withSet(e, m[e] + h2)
        val mnbarmn = mn.rightInverse().mult(mn)

        val mean = meanCalculator.calculate { x ->
            val slope = (specificCost(mp, mpbarmp, x).pow(2.0) - specificCost(mn, mnbarmn, x).pow(2.0)) / (h1 - h2)
            transformer.transform(slope)
        }

        return transformer.inverse(mean)
    }
}
