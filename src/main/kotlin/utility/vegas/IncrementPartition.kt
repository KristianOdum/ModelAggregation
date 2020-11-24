package utility.vegas

import utility.OpenEndDoubleRange
import utility.until
import kotlin.math.roundToInt
import kotlin.random.Random

fun <T> MutableList<T>.removeRange(range: IntRange) {
    range.forEach { this.removeAt(range.first) }
}

open class IncrementPartition(val initialIncrements: Int, private val maxValue: Double): Iterable<OpenEndDoubleRange> {

    private val increments = MutableList(initialIncrements) { maxValue * it / initialIncrements }

    operator fun get(i: Int): OpenEndDoubleRange = increments[i] until (increments.getOrNull(i +1) ?: maxValue)

    val size get() = increments.size

    fun indexWith(x: Double): Int {
        val i = increments.binarySearch(x)
        if (i >= 0)
            return i
        else
            // Use the inverse insertion point and then the point before that
            return -i - 2
    }

    fun randomIndex(): Int = Random.nextInt(size)

    fun partitionWith(x: Double): OpenEndDoubleRange = this[indexWith(x)]

    fun subdivide(i: Int, n: Int) {
        if (n <= 1)
            return

        val interval = this[i]
        val step = interval.size / n
        increments.removeAt(i)
        increments.addAll(i, (0 until n).map { interval.from + step * it })
    }

    fun subdivideAll(counts: List<Int>) {
        var offset = 0
        for ((i,c) in counts.withIndex().filter { it.value > 1 }) {
            subdivide(i + offset, c)
            offset += c - 1
        }
    }

    fun mergeToInitialSize() {
        // Reduction factor, how many to include in each new increment
        val r = size.toDouble() / initialIncrements

        // Reduction Offset, the amount that is currently under/over accounted for
        var ro = 0.0

        for (i in 0 until initialIncrements) {
            // Number of increments in this merge
            val n = (r - ro).roundToInt()
            ro += n - r

            increments.removeRange(i + 1 until i + n)
        }
    }


    override fun iterator(): Iterator<OpenEndDoubleRange> = (increments + listOf(maxValue)).zipWithNext().map { it.first until it.second }.listIterator()

    override fun toString(): String = iterator().asSequence().joinToString(", ")
}