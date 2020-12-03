package utility.vegas

import Average
import org.ejml.simple.SimpleMatrix
import utility.MeanCalculator
import utility.create
import utility.nextDouble
import utility.rightInverse
import kotlin.math.abs
import kotlin.math.log2
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.random.Random

class VegasMeanCalculator(dimensionCount: Int, tolerance: Double) : MeanCalculator(dimensionCount, tolerance) {

    private val incrementCount = 50
    private val subdivisionCount = 1000
    private val convergenceRate = 0.5

    private val increments = Array(dimensionCount) {
        IncrementPartition(incrementCount, xRange)
    }

    override fun calculate(evalpoint: (x: SimpleMatrix) -> Double): Double {
        subpartition(evalpoint)

        return calcIntegralWithPartitions(evalpoint)
    }

    private fun subpartition(evalpoint: (x: SimpleMatrix) -> Double) {
        // Calculate the subdivisionCount to be divisible by increment count
        val K = (subdivisionCount.toDouble() / incrementCount).roundToInt() * incrementCount

        val unfinishedDimensions = increments.indices.toMutableSet()

        partitioning@while (unfinishedDimensions.isNotEmpty()) {
            // The function evaluations for each partition
            val fs = Array(dimensionCount) { index -> Array(increments[index].size) { Average() } }

            for (j in 0 until 100000) {
                // Choose random partition from each dimension
                val partitions = increments.map { it.randomIndex() }

                // Create a random vector in these partitions
                val x = SimpleMatrix(dimensionCount, 1)
                    .create { it -> Random.nextDouble(increments[it][partitions[it]]) }

                val c = evalpoint(x)

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
                val damppart = K * (1 - convergenceRate) / incrementCount
                val ms = incrementArea.map {
                    val r = it / totalArea
                    (K * convergenceRate * r + damppart).roundToInt() + 1
                    //(K * ((r-1)/log2(r)).pow(convergenceRate) / 10.0).roundToInt() + 1
                }

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
    }

    private fun calcIntegralWithPartitions(evalpoint: (x: SimpleMatrix) -> Double): Double {
        var integral = 1.0
        var space = 0.0
        // Actually calculate the integral
        for (i in 0 until 1000) {
            // Choose random partition from each dimension
            val partitions = increments.map { it.randomIndex() }

            // Create a random vector in these partitions
            val x = SimpleMatrix(dimensionCount, 1)
                .create { it -> Random.nextDouble(increments[it][partitions[it]]) }

            val c = evalpoint(x)
            var sp = 1.0
            for (k in partitions.indices)
                sp *= increments[k][partitions[k]].size

            integral += c * sp
            space += sp
        }

        return integral / space
    }
}