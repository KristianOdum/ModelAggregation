import gradientDescent.GoldenSectionGD
import gradientDescent.GradientDescent
import org.ejml.simple.SimpleMatrix
import utility.*
import utility.vegas.IncrementPartition
import java.lang.Integer.max
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.random.Random

fun main() {
    val cfot = CostFunctionOptimizerTester(200, 10) {
        val mi = SIRModelCreator().random(3, 1)
        GoldenSectionGD(mi)
    }

    cfot.run()
}

class Average {
    var i = 0
    var value = 0.0
    fun add(value: Double) {
        this.value = this.value * (i/(i+1)) + value * (1/(i+1))
    }
}

fun cost_vegas(m: SimpleMatrix, f: (SimpleMatrix) -> SimpleMatrix): Double {
    val increments = Array(m.numCols()) {
        IncrementPartition(4, 1000.0)
    }
    val mbarm = m.rightInverse().mult(m)

    for (i in 0 until 4) {
        // The function evaluations for each partition
        val fs = Array(m.numCols()) { index -> Array(increments[index].size) { Average() } }
        val ipp = max(1, 1000 / increments.sumOf { it.size }) // Iterations per partition

        // Evaluate the specific cost at each partition ipp times
        for ((dim, inc) in increments.withIndex()) {
            for ((index, partition) in inc.withIndex()) {

                // Evaluate in this partition
                for (j in 0 until ipp) {
                    val x = randMatrix(m.numCols(), 1, 0.0 until 1000.0)

                    // Fix one dimension within this partition
                    x[dim, 0] = Random.nextDouble(partition.from, partition.to)

                    val spc = specificCost(m, mbarm, x, f).pow(2.0)
                    // Add to all partitions that this x is in
                    for ((index, inc) in increments.withIndex()) {
                        val p = inc.indexWith(x[index, 0])
                        fs[index][p].add(spc)
                    }
                }
            }
        }

        // Update intervals for each dimension
        for (dim in increments.indices) {
            val total = fs[dim].sumOf { it.value }
            increments[dim].subdivideAll(
                    (0 until increments[dim].size).map {
                        // Calculate each partitions relative function size
                        (10 * fs[dim][it].value / total).roundToInt()
                    }
            )
        }
    }

    var integral = BigDecimal(0.0)
    var space = BigDecimal(0.0)

    // Actually calculate the integral
    val ipp = max(1, 1000000 / increments.sumOf { it.size }) // Iterations per partition
    for ((dim, inc) in increments.withIndex()) {
        for ((index, partition) in inc.withIndex()) {
            // Evaluate in this partition
            for (j in 0 until ipp) {
                val x = randMatrix(m.numCols(), 1, 0.0 until 1000.0)

                // Fix one dimension within this partition
                x[dim, 0] = Random.nextDouble(partition.from, partition.to)

                val c = specificCost(m, mbarm, x, f).pow(2.0)
                // Calculate the size of this space
                var sp = 1.0
                for ((index, inc) in increments.withIndex()) {
                    sp *= inc.partitionWith(x[index, 0]).size
                }
                space += BigDecimal(sp)
                integral += BigDecimal(c * sp)
            }
        }
    }

    return integral.divide(space, RoundingMode.HALF_UP).toDouble()

}

private fun specificCost(m: SimpleMatrix, mbarm: SimpleMatrix, x: SimpleMatrix, f: (SimpleMatrix) -> SimpleMatrix) =
        m.mult(f(x).minus(f(mbarm.mult(x)))).normF()