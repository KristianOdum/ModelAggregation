package utility

class IncrementPartitioning(initialIncrements: Int): Iterable<OpenEndDoubleRange> {

    private val increments = MutableList(initialIncrements) { it.toDouble() / initialIncrements }

    operator fun get(i: Int): OpenEndDoubleRange = increments[i] until (increments.getOrNull(i +1) ?: 1.0)

    val size get() = increments.size

    fun partitionWith(x: Double): Int {
        return 0
    }

    fun subdivide(i: Int, n: Int) {
        val interval = this[i]
        val step = interval.size / n
        increments.removeAt(i)
        increments.addAll(i, (0 until n).map { interval.from + step * it })
    }


    override fun iterator(): Iterator<OpenEndDoubleRange> = (increments + listOf(1.0)).zipWithNext().map { it.first until it.second }.listIterator()


}