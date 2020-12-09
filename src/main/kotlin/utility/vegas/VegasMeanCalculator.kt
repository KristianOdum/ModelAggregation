package utility.vegas

import Average
import org.ejml.simple.SimpleMatrix
import utility.*
import kotlin.math.abs
import kotlin.math.log2
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.random.Random

class VegasMeanCalculator(dimensionCount: Int, tolerance: Double) : MeanCalculator(dimensionCount, tolerance) {

    private val incrementCount = 50
    private val subdivisionCount = incrementCount * 20
    private val convergenceRate = 0.5

    private val increments = Array(dimensionCount) {
        IncrementPartition(incrementCount, xRange)
    }

    override fun calculate(evalpoint: (x: SimpleMatrix) -> Double): Double {
        val (integral, invsigma) = subpartition(evalpoint)

        return calcIntegralWithPartitions(evalpoint, integral, invsigma)
    }

    private fun subpartition(evalpoint: (x: SimpleMatrix) -> Double): Pair<Double, Double> {
        val ies = mutableListOf<IntegralEstimation>()

        // Calculate the subdivisionCount to be divisible by increment count
        val K = (subdivisionCount.toDouble() / incrementCount).roundToInt() * incrementCount

        val unfinishedDimensions = increments.indices.toMutableSet()

        partitioning@while (unfinishedDimensions.isNotEmpty()) {
            // The function evaluations for each partition
            val fs = Array(dimensionCount) { index -> Array(increments[index].size) { Average() } }

            val ie = IntegralEstimation()
            ies.add(ie)

            for (j in 0 until 10000) {
                // Choose random partition from each dimension
                val partitions = increments.map { it.randomIndex() }

                // Create a random vector in these partitions
                val x = SimpleMatrix(dimensionCount, 1)
                    .create { it -> Random.nextDouble(increments[it][partitions[it]]) }

                val c = evalpoint(x)
                var sp = 1.0

                for ((dim, pi) in partitions.withIndex()) {
                    fs[dim][pi].add(c)
                    sp *= increments[dim][pi].size
                }

                ie.add(c * sp, sp)
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
                if (ms.all { abs(it - e) <= 2 }) {
                    unfinishedDimensions.remove(dim)
                    continue@subdivision
                }

                // Subdivide and merge back
                increments[dim].subdivideAll(ms)
                increments[dim].mergeToInitialSize()
            }
        }

        val invsigma2sum = ies.map { 1 / it.sigma2 }.sum()
        val integralsum = ies.map { it.integral / it.sigma2 }.sum()

        return Pair(integralsum, invsigma2sum)
    }

    private fun calcIntegralWithPartitions(evalpoint: (x: SimpleMatrix) -> Double, previntegral: Double, previnvsigma: Double): Double {
        val ie = IntegralEstimation()

        val sigma2 = { 1 / (previnvsigma + 1 / ie.sigma2) }
        val integral = { sigma2() * (previntegral + ie.integral / ie.sigma2) }

        val tolerance2 = tolerance * tolerance
        var i = 0L

        // Actually calculate the integral
        while (i < 10 || sigma2() > tolerance2 * integral().pow(2)) {
            // Choose random partition from each dimension
            val partitions = increments.map { it.randomIndex() }

            // Create a random vector in these partitions
            val x = SimpleMatrix(dimensionCount, 1)
                .create { it -> Random.nextDouble(increments[it][partitions[it]]) }

            val c = evalpoint(x)
            var sp = 1.0
            for (k in partitions.indices)
                sp *= increments[k][partitions[k]].size

            ie.add(c, sp)

            i++
        }

        println("vegas $i")

        return integral()
    }
}