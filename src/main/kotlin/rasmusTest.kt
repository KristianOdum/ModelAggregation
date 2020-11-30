import PSO.GeneralPSO
import PSO.PSOInfo
import gradientDescent.DerivativeCalculator
import gradientDescent.GoldenSectionGD
import gradientDescent.GradientDescent
import gradientDescent.MomentumGD
import org.ejml.simple.SimpleMatrix
import utility.*
import utility.vegas.IncrementPartition
import java.lang.Integer.max
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.abs
import kotlin.math.log2
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.random.Random

fun main() {

    val mia = T3ModelCreator().createModel(1)

    val cfot = CostFunctionOptimizerTester(200, 1) {
        val mi = T3ModelCreator().createModel(1)
        GoldenSectionGD(mi)
    }

    cfot.run()
}

fun Double.strWidth(width: Int) = this.toString().take(max(3, width - 3)) + this.toString().takeLast(3)

class Average {
    var i = 0L
    var value = 0.0
    fun add(value: Double) {
        this.value = tempAdd(value)
        i++
    }

    fun tempAdd(value: Double) = this.value * (i.toDouble()/(i+1)) + value * (1.0/(i+1))
}

const val incrementCount = 50
const val subdivisionCount = 1000
const val convergenceRate = 0.8
fun cost_vegas(m: SimpleMatrix, f: (SimpleMatrix) -> SimpleMatrix): Double {
    val increments = Array(m.numCols()) {
        IncrementPartition(incrementCount, 1000.0)
    }
    val mbarm = m.rightInverse().mult(m)
    val lastSubdivisionCounts = Array(m.numCols()) {
        Array(incrementCount) { 0 }
    }
    // Calculate the subdivisionCount to be divisible by increment count
    val K = (subdivisionCount.toDouble() / incrementCount).roundToInt() * incrementCount

    val unfinishedDimensions = increments.indices.toMutableSet()

    partitioning@while (unfinishedDimensions.isNotEmpty()) {
        // The function evaluations for each partition
        val fs = Array(m.numCols()) { index -> Array(increments[index].size) { Average() } }

        for (j in 0 until 1000000) {
            // Choose random partition from each dimension
            val partitions = increments.map { it.randomIndex() }

            // Create a random vector in these partitions
            val x = SimpleMatrix(m.numCols(), 1)
                    .create { it -> Random.nextDouble(increments[it][partitions[it]]) }

            val c = specificCost(m, mbarm, x, f).pow(2.0)

            for ((dim, pi) in partitions.withIndex()) {
                fs[dim][pi].add(c)
            }
        }

        // Update intervals for each dimension
        subdivision@for (dim in unfinishedDimensions.toList()) {
            // Calculate estimated area in each partition
            val incrementArea = (0 until increments[dim].size).map {
                fs[dim][it].value * increments[dim][it].size
            }

            // Calculate the total area
            val totalArea = incrementArea.sum()

            // Calculate subdivisions
            val ms = incrementArea.map {
                val r = it / totalArea
                (K * ((r-1)/log2(r)).pow(convergenceRate) / 10.0).roundToInt() + 1
            }

            val g = incrementArea.map { abs(it - totalArea / incrementArea.size) }.average()
            if (dim == 1)
                println(g)

            // If all the partitions have equal partitioning we are done
            val e = (K + incrementCount) / incrementCount // Expected
            if (ms.all { abs(it - e) <= 1 }) {
                unfinishedDimensions.remove(dim)
                continue@subdivision
            }

            // Subdivide and merge back
            increments[dim].subdivideAll(ms)
            increments[dim].mergeToInitialSize()
        }
    }

    return calcIntegralWithPartitions(m, mbarm, f, increments)
}

fun calcIntegralWithPartitions(m: SimpleMatrix, mbarm: SimpleMatrix, f: (SimpleMatrix) -> SimpleMatrix, increments: Array<IncrementPartition>): Double {
    var integral = 1.0
    var space = 0.0
    // Actually calculate the integral
    for (i in 0 until 100000) {
        // Choose random partition from each dimension
        val partitions = increments.map { it.randomIndex() }

        // Create a random vector in these partitions
        val x = SimpleMatrix(m.numCols(), 1)
                .create { it -> Random.nextDouble(increments[it][partitions[it]]) }

        val c = specificCost(m, mbarm, x, f).pow(2.0)
        var sp = 1.0
        for (k in partitions.indices)
            sp *= increments[k][partitions[k]].size

        integral += c * sp
        space += sp
    }

    return integral / space
}


private fun specificCost(m: SimpleMatrix, mbarm: SimpleMatrix, x: SimpleMatrix, f: (SimpleMatrix) -> SimpleMatrix) =
        m.mult(f(x).minus(f(mbarm.mult(x)))).normF()