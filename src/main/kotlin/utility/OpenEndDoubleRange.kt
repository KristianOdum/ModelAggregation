package utility


data class OpenEndDoubleRange(val from: Double, val to: Double) {
    val size = to - from

    override fun toString(): String = "[$from; $to)"
}

infix fun Double.until(to: Double) = OpenEndDoubleRange(this, to)
operator fun OpenEndDoubleRange.contains(value: Double) = value < to && value >= from