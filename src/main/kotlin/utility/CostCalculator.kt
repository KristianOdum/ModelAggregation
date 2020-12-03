package utility

import org.ejml.simple.SimpleMatrix
import kotlin.math.pow

open class CostCalculator(private val meanCalculator: MeanCalculator, modelFunction: (SimpleMatrix) -> SimpleMatrix, private val transformer: InvertibleTransformation = IdentityTransformer()) : ModelSpecificCost(modelFunction), Cloneable {
    fun cost(m: SimpleMatrix): Double {
        val mbarm = m.rightInverse().mult(m)

        val mean = meanCalculator.calculate { x ->
            transformer.transform(specificCost(m, mbarm, x).pow(2.0))
        }

        return transformer.inverse(mean)
    }

    public override fun clone() = super.clone()
}