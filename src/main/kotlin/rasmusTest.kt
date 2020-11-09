import org.ejml.simple.SimpleMatrix
import utility.*
import java.lang.Integer.max
import kotlin.math.roundToInt
import kotlin.random.Random

fun main() {

    val mi = ToyModelCreator().random(0, 1)
    println(CostCalculator(mi.function).cost(mi.lumpingMatrix))
    println(cost_vegas(mi.lumpingMatrix, mi.function))

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
        MutableList(4) { it.toDouble() / 4.0 until (it + 1).toDouble() / 4.0 }
    }
    val mbarm = m.rightInverse().mult(m)

    for (i in 0 until 10) {
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

                    val spc = specificCost(m, mbarm, x, f)
                    // Add to all partitions that this x is in
                    for ((index, inc) in increments.withIndex()) {
                        val p = inc.indexOfFirst { x[index, 0] / 1000.0 in it }
                        fs[index][p].add(spc)
                    }
                }
            }
        }

        // Update intervals for each dimension
        for (dim in increments.indices) {
            val total = fs[dim].sumOf { it.value }
            var listOffset = 0
            for ((index, p) in increments[dim].withIndex().toList()) {
                // Calculate number of subdivisions
                val s = (1000 * fs[dim][index].value / total).roundToInt()

                // Create new subpartitions
                if (s > 1) {
                    val from = p.from
                    val step = p.size / s
                    increments[dim].removeAt(index)
                    for (z in 0 until s)
                        increments[dim].add(index + z + listOffset, (from + step * z) until (from + step * (z+1)))
                    listOffset += s - 1
                }
            }
        }
    }

    var integral = 0.0
    var space = 0.0

    // Actually calculate the integrand
    val ipp = max(1, 1000 / increments.sumOf { it.size }) // Iterations per partition
    for ((dim, inc) in increments.withIndex()) {
        for ((index, partition) in inc.withIndex()) {
            // Evaluate in this partition
            for (j in 0 until ipp) {
                val x = randMatrix(m.numCols(), 1, 0.0 until 1000.0)

                // Fix one dimension within this partition
                x[dim, 0] = Random.nextDouble(partition.from, partition.to)

                val c = specificCost(m, mbarm, x, f)
                // Calculate the size of this space
                var sp = 1.0
                for ((index, inc) in increments.withIndex()) {
                    sp *= inc.first { x[index, 0] in it }.size
                }
                space += sp
                integral += c * sp
            }
        }
    }

    return integral / space
}

private fun specificCost(m: SimpleMatrix, mbarm: SimpleMatrix, x: SimpleMatrix, f: (SimpleMatrix) -> SimpleMatrix) =
        m.mult(f(x).minus(f(mbarm.mult(x)))).normF()