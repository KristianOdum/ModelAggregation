package utility

import org.ejml.simple.SimpleMatrix

open class CostCalculator(private val meanCalculator: MonteCarloMeanCalculator, modelFunction: (SimpleMatrix) -> SimpleMatrix, private val transformer: InvertibleTransformation = IdentityTransformer()) : ModelSpecificCost(modelFunction), Cloneable {
    fun cost(m: SimpleMatrix): Double {
        val mbarm = m.rightInverse().mult(m)

        val mean = meanCalculator.calculate { x ->
            transformer.transform(specificCost(m, mbarm, x))
        }

        return transformer.inverse(mean)
    }

    public override fun clone() = super.clone()
}