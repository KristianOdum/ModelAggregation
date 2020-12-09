package utility

import gradientDescent.DerivativeCalculator
import org.ejml.simple.SimpleMatrix
import kotlin.math.E
import kotlin.math.exp
import kotlin.math.log
import kotlin.math.pow

interface InvertibleTransformation {
    fun transform(v: Double): Double
    fun inverse(v: Double): Double
}

class IdentityTransformer : InvertibleTransformation {
    override fun transform(v: Double): Double = v
    override fun inverse(v: Double): Double = v
}

open class SigmoidoidTransformer(val sigmoidScaling: Double) : InvertibleTransformation {
    override fun transform(v: Double) = 2.0 /  (1 + exp(-v / sigmoidScaling)) - 1.0
    override fun inverse(v: Double) = -log(2.0 / (v + 1.0) - 1.0, E) * sigmoidScaling

    companion object {
        fun CreateCostFromPrediction(modelInfo: ModelInfo, meanCalculator: MeanCalculator) : CostCalculator {
            return CostCalculator(meanCalculator, modelInfo.function, SigmoidoidTransformer(predictScaling(modelInfo, meanCalculator)))
        }

        fun CreateDerivativeFromPrediction(modelInfo: ModelInfo, meanCalculator: MeanCalculator) : DerivativeCalculator {
            return DerivativeCalculator(meanCalculator, modelInfo.function, NonInvertingSigmoidoidTransformer(predictScaling(modelInfo, meanCalculator)))
        }

        private fun predictScaling(modelInfo: ModelInfo, meanCalculator: MeanCalculator): Double {
            val trivDer = DerivativeCalculator(meanCalculator, modelInfo.function)

            val m = modelInfo.lumpingMatrix
            val mbarm = m.rightInverse().mult(m)

            return trimmedMean {
                val x = randMatrix(m.numCols(), 1, meanCalculator.xRange)
                trivDer.specificCost(m, mbarm, x).pow(2.0)
            }
        }

        private fun trimmedMean(evalPoint: () -> Double): Double {
            val values = List(1000) {
                evalPoint()
            }

            return values.sorted().drop(values.size / 4).dropLast(values.size / 4).average()
        }
    }
}

class NonInvertingSigmoidoidTransformer(sigmoidScaling: Double) : SigmoidoidTransformer(sigmoidScaling) {
    override fun inverse(v: Double): Double = v
}