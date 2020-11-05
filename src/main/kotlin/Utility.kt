import org.ejml.simple.SimpleMatrix
import java.io.File
import java.lang.Integer.max
import kotlin.math.*

const val MAX_X = 1000.0
const val MIN_X = 0.0

fun cost(m: SimpleMatrix, f: (SimpleMatrix) -> SimpleMatrix, tolerance: Double = 1.0E-2, clusterSize: Int = 50): Double {
    var x: SimpleMatrix
    val mbarm = m.rightInverse().mult(m)

    return untilAverageTolerance(tolerance, clusterSize) {
        x = randMatrix(m.numCols(), 1, MIN_X, MAX_X)

        specificCost(m, mbarm, f, x).pow(2.0)
    }
}

fun untilAverageTolerance(tolerance: Double, clusterSize: Int = 50, action: () -> Double): Double {
    val averages = mutableListOf<Double>()
    var i = 0

    while (averages.size < 5 || averages.dropLast(1).map { abs(it - averages.last()) }.maxOrNull()!! > abs(averages.last() * tolerance)) {

        var clusterAverage = 0.0
        repeat(clusterSize) {
            val e = action()
            clusterAverage = clusterAverage * (it.toDouble() / (it + 1)) + e / (it + 1)
        }

        if (averages.size >= 5) {
            for (j in 0 until averages.size - 1)
                averages[j] = averages[j + 1]
            averages[4] = averages[3] * i.toDouble() / (i + 1) + clusterAverage / (i + 1)
        } else {
            val na = if (averages.size > 1) averages.last() * i.toDouble() / (i + 1) + clusterAverage / (i + 1) else clusterAverage
            averages.add(na)
        }

        i++
    }
    return averages.last()
}

fun specificCost(m: SimpleMatrix, mbarm: SimpleMatrix, f: (SimpleMatrix) -> SimpleMatrix, x: SimpleMatrix) =
        m.mult(f(x).minus(f(mbarm.mult(x)))).normF()

fun SimpleMatrix.withSet(i: Int, value: Double): SimpleMatrix {
    val new = SimpleMatrix(this)
    new[i] = value
    return new
}

fun SimpleMatrix.rightInverse() = this.transpose().mult(this.mult(this.transpose()).invert())!!

fun randMatrix(numRows: Int, numCols: Int, from: Double, to: Double): SimpleMatrix {
    val m = SimpleMatrix(numRows, numCols)

    for (i in 0 until m.numElements) {
        m[i] = randomP.nextDouble(from, to)
    }

    return m
}

fun SimpleMatrix.create(initializer: (Int, Int) -> Double): SimpleMatrix {
    val s = SimpleMatrix(this.numRows(), this.numCols())
    for (i in 0 until s.numRows()) {
        for (j in 0 until s.numCols()) {
            s[i, j] = initializer(i, j)
        }
    }
    return s
}

fun SimpleMatrix.create(initializer: (Int) -> Double): SimpleMatrix {
    val s = SimpleMatrix(this.numRows(), this.numCols())
    for (i in 0 until s.numElements) {
        s[i] = initializer(i)
    }
    return s
}

fun SimpleMatrix.allElements(): Iterable<Double> {
    val list = mutableListOf<Double>()

    for (i in 0 until this.numElements)
        list.add(this[i])

    return list
}

fun SimpleMatrix.hadamard(other: SimpleMatrix): SimpleMatrix {
    return this.create { i ->
        this[i] * other[i]
    }
}

fun ClosedFloatingPointRange<Double>.iterator(step: (Double) -> Double): Iterator<Double> {
    return object : Iterator<Double> {
        private var value = this@iterator.start
        private val range = this@iterator

        override fun hasNext(): Boolean = step(value) in range

        override fun next(): Double {
            value = step(value)
            return value
        }

    }
}

fun SimpleMatrix.normalize(): SimpleMatrix = this.create { i, j -> this[i, j] / this.normF() }

fun SimpleMatrix.rowNorm(): SimpleMatrix {
    val n = SimpleMatrix(this)
    for (i in 0 until this.numRows()) {
        val magnitude = sqrt(rowVector(i).allElements().map { it * it }.sum())
        for (j in 0 until this.numCols()) {
            n[i, j] = n[i, j] / magnitude
        }
    }
    return n
}

fun SimpleMatrix.colNorm(): SimpleMatrix {
    val n = SimpleMatrix(this)
    for (j in 0 until numCols()) {
        val magnitude = sqrt(colVector(j).allElements().map { it * it }.sum())
        for (i in 0 until numRows()) {
            n[i, j] = n[i, j] / magnitude
        }
    }
    return n
}

fun SimpleMatrix.rowVector(r: Int): SimpleMatrix = SimpleMatrix(1, numCols()).create { i -> this[r, i] }
fun SimpleMatrix.colVector(c: Int): SimpleMatrix = SimpleMatrix(numRows(), 1).create { i -> this[i, c] }


fun Plot.saveWithExt(fileName: String = "plot") {
    for (ext in (0..50).withIndex()) {
        val path = "plots/" + fileName + ext.value.toString() + ".png"
        val file = File(path)
        val exists = file.exists()

        if (!exists) {
            this.save(path.removeSuffix(".png"), "png")
            return
        }
    }
}

data class LUPair(val L: SimpleMatrix, val U: SimpleMatrix)

fun SimpleMatrix.LUDecomposition(): LUPair {
    val upper = SimpleMatrix(numRows(), numCols())
    val lower = SimpleMatrix(numRows(), numRows())

    for (i in 0 until numRows()) {
        for (k in i until numCols()) {
            var sum = 0.0
            for (j in 0 until i) {
                sum += lower[i, j] * upper[j, k]
            }
            upper[i, k] = this[i, k] - sum
        }

        for (k in i until numRows()) {
            if (i == k) {
                lower[i, i] = 1.0
            } else {
                var sum = 0.0
                for (j in 0 until i)
                    sum += lower[k, j] * upper[j, i]
                lower[k, i] = (this[k, i] - sum) / upper[i, i]
            }
        }
    }

    return LUPair(lower, upper)
}

// Modified Gram-Schmidt OrthoNormalization
fun SimpleMatrix.MGSON(): SimpleMatrix {
    var u = SimpleMatrix(this)

    u = u.setRow(0, u.rowVector(0).normalize())
    for (r in 1 until numRows()) {
        var w = this.rowVector(r) - this.rowVector(r).project(u.rowVector(0))
        for (k in 1 until r) {
            w -= w.project(u.rowVector(k))
        }
        u = u.setRow(r, w.normalize())
    }
    return u
}

fun SimpleMatrix.project(u: SimpleMatrix): SimpleMatrix {
    if (u.allElements().all { it == 0.0 })
        return u
    return u.scale(u.dot2(this) / u.dot2(u))
}

fun SimpleMatrix.dot2(v: SimpleMatrix): Double {
    return allElements().zip(v.allElements()).sumByDouble { it.first * it.second }
}

fun SimpleMatrix.setRow(row: Int, vector: SimpleMatrix): SimpleMatrix {
    val final = SimpleMatrix(this)
    if (vector.numCols() > vector.numRows()) {
        for (col in 0 until numCols()) {
            final[row, col] = vector[0, col]
        }
    } else {
        val col = row
        for (row in 0 until numRows()) {
            final[row, col] = vector[row, 0]
        }
    }
    return final
}

fun SimpleMatrix.withValues(vararg values: Double): SimpleMatrix {
    if (values.size > numElements)
        throw IllegalArgumentException("Cannot set more values than indices in matrix!")

    val m = SimpleMatrix(this)
    for (i in values.indices) {
        m[i] = values[i]
    }
    return m
}


class Average {
    var i = 0
    var value = 0.0
    fun add(value: Double) {
        this.value = this.value * (i/(i+1)) + value * (1/(i+1))
    }
}

data class OpenEndDoubleRange(val from: Double, val to: Double) {
    val size = to - from
}

infix fun Double.until(to: Double) = OpenEndDoubleRange(this, to)
operator fun OpenEndDoubleRange.contains(value: Double) = value < to && value >= from

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
}