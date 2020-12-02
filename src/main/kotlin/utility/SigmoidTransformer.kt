package utility

import kotlin.math.E
import kotlin.math.exp
import kotlin.math.log

interface InvertibleTransformation {
    fun transform(v: Double): Double
    fun inverse(v: Double): Double
}

class IdentityTransformer : InvertibleTransformation {
    override fun transform(v: Double): Double = v
    override fun inverse(v: Double): Double = v
}

class SigmoidoidTransformer(val sigmoidScaling: Double) : InvertibleTransformation {
    override fun transform(v: Double) = 2.0 /  (1 + exp(-v / sigmoidScaling)) - 1.0
    override fun inverse(v: Double) = -log(2.0 / (v + 1.0) - 1.0, E) * sigmoidScaling
}