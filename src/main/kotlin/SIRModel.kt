import org.ejml.simple.SimpleMatrix
import kotlin.random.Random

class RandomSIR(count: Int) : SIRModel(
        List(count) { Random.nextDouble(1.0) },
        randMatrix(count, count, 0.0, 1.0)
)

open class SIRModel(private val recoveryRates: List<Double>, private val contactRates: SimpleMatrix) {
    val count = recoveryRates.size
    val functionsCount get() = count * 3

    private fun S(k: Int) = 3 * k
    private fun I(k: Int) = 3 * k + 1
    private fun R(k: Int) = 3 * k + 2

    private fun onlyI(m: SimpleMatrix) = m.allElements().withIndex().filter { it.index % 3 == 1 }.map { it.value }

    val function get() = { x: SimpleMatrix ->
        val res = SimpleMatrix(count * 3, 1)

        for (k in recoveryRates.indices) {

            val infectionRate = x[S(k)] * (0 until count).sumByDouble { l -> contactRates[k, l] * x[I(k)] }
            val recoveryRate = recoveryRates[k] * x[I(k)]

            res[S(k)] = -infectionRate
            res[I(k)] = -recoveryRate + infectionRate
            res[R(k)] = recoveryRate
        }
        res
    }

    init {
        if (recoveryRates.size != contactRates.numCols() || contactRates.numCols() != contactRates.numRows())
            throw IllegalArgumentException("contactRates must be square and have same count as recoveryRates")
    }
}