/*

import org.ejml.simple.SimpleMatrix
import java.io.File
import java.lang.Integer.max
import kotlin.math.*

const val MAX_X = 1000.0
const val MIN_X = 0.0

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
                    val x = randMatrix(m.numCols(), 1, MIN_X, MAX_X)
                    x[dim, 0] = randomP.nextDouble(partition.from, partition.to)

                    val spc = specificCost(m, mbarm, f, x)
                    // Add to all partitions that this x is in
                    for ((index, inc) in increments.withIndex()) {
                        val p = inc.indexOfFirst { x[index, 0] in it }
                        fs[index][p].add(spc)
                    }
                }
            }
        }

        // Update intervals for each dimension
        for (dim in increments.indices) {
            val total = fs[dim].sumOf { it.value }
            for ((index, p) in increments[dim].withIndex()) {
                // Calculate number of subdivisions
                val s = (1000 * fs[dim][index].value / total).roundToInt()

                // Create new subpartitions
                if (s > 1) {
                    val from = p.from
                    val step = p.size / s
                    increments[dim].removeAt(index)
                    for (z in 0 until s)
                        increments[dim].add(index + z, (from + step * z) until (from + step * (z+1)))
                }
            }

        }
    }
    return 0.0
}

fun averageNoise(m: SimpleMatrix, f: (SimpleMatrix) -> SimpleMatrix, epochs: Int, tolerance: Double) {
    val start = Array(epochs) { 0.0 }
    val average: Double
    var averageNoiseSum = 0.0
    val averageNoise: Double

    for (i in 0 until epochs) {
        start[i] = cost(m, f, tolerance, 50)
        print("\r${((i.toDouble()/epochs.toDouble())*100).toInt()}%")
    }

    average = start.average()
    println("Start cost: $average")

    for (i in start)
        averageNoiseSum += (average - i).absoluteValue

    averageNoise = averageNoiseSum / epochs

    println("\nCost is ${(averageNoise / start.average()) * 100}% off on average.")
}*/