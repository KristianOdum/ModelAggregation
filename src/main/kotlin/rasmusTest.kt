@file:Suppress("DuplicatedCode")

import PSO.GeneralPSO
import PSO.GradientDescentPSO
import PSO.PSOInfo
import PSO.ParticleSwarmOptimization
import gradientDescent.DynamicGD
import gradientDescent.GoldenSectionGD
import gradientDescent.GradientDescent
import gradientDescent.SimpleGD
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

    val mi = SIRModelCreator().random(12, 1)

    repeat(10) {
        println(cost_vegas(mi.lumpingMatrix, mi.function) )
    }
    repeat(10) {
        println(CostCalculator(mi.function).cost(mi.lumpingMatrix))
    }

}

class Average {
    var i = 0
    var value = 0.0
    fun add(value: Double) {
        this.value = this.value * (i.toDouble()/(i+1)) + value * (1.0/(i+1))
        i++
    }
}

fun cost_vegas(m: SimpleMatrix, f: (SimpleMatrix) -> SimpleMatrix): Double {
    val increments = Array(m.numCols()) {
        IncrementPartition(4, 1000.0)
    }
    val mbarm = m.rightInverse().mult(m)

    for (i in 0 until 20) {
        // The function evaluations for each partition
        val fs = Array(m.numCols()) { index -> Array(increments[index].size) { Average() } }

        for (j in 0 until 1000) {
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
        for (dim in increments.indices) {
            val total = fs[dim].sumOf { it.value }
            increments[dim].subdivideAll(
                    (0 until increments[dim].size).map {
                        // Calculate each partitions relative function size
                        (10.0 * fs[dim][it].value / total).roundToInt()
                    }
            )
        }
    }

    return calcIntegralWithPartitions(m, mbarm, f, increments)
}

fun calcIntegralWithPartitions(m: SimpleMatrix, mbarm: SimpleMatrix, f: (SimpleMatrix) -> SimpleMatrix, increments: Array<IncrementPartition>): Double {
    var integral = BigDecimal.ONE
    var space = BigDecimal.ZERO
    // Actually calculate the integral
    for (i in 0 until 100000) {
        // Choose random partition from each dimension
        val partitions = increments.map { it.randomIndex() }

        // Create a random vector in these partitions
        val x = SimpleMatrix(m.numCols(), 1)
                .create { it -> Random.nextDouble(increments[it][partitions[it]]) }

        val c = specificCost(m, mbarm, x, f).pow(2.0)
        var sp = BigDecimal.ONE
        for (k in partitions.indices)
            sp *= increments[k][partitions[k]].size.toBigDecimal()

        integral = integral.plus(c.toBigDecimal() * sp)
        space = space.plus(sp)
    }

    return integral.divide(space, RoundingMode.HALF_UP).toDouble()
}


private fun specificCost(m: SimpleMatrix, mbarm: SimpleMatrix, x: SimpleMatrix, f: (SimpleMatrix) -> SimpleMatrix) =
        m.mult(f(x).minus(f(mbarm.mult(x)))).normF()