import org.ejml.simple.SimpleMatrix
import kotlin.math.abs
import kotlin.math.pow
import kotlin.random.Random

const val MAX_X = 1000.0
const val MIN_X = 0.0

fun cost(m: SimpleMatrix, f: (SimpleMatrix) -> SimpleMatrix): Double {
    var x: SimpleMatrix
    val mbar = m.rightInverse()

    return untilAverageTolerance {
        x = randMatrix(m.numCols(), 1, MIN_X, MAX_X)

        specificCost(m, mbar, f, x).pow(2.0)
    }
}

fun untilAverageTolerance(tolerance: Double = 1.0E-5, clusterSize: Int = 50, action: () -> Double): Double {
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
                averages[j] = averages[j+1]
            averages[4] = averages[3] * (i * clusterSize).toDouble() / (i * clusterSize + 1)
                          + clusterAverage / (i * clusterSize + 1)
        } else {
            averages.add(clusterAverage)
        }

        i++
    }

    return averages.last()
}

fun specificCost(m: SimpleMatrix, mbar: SimpleMatrix, f: (SimpleMatrix) -> SimpleMatrix, x: SimpleMatrix) =
        m.mult(f(x)).minus(m.mult(f(mbar.mult(m.mult(x))))).normF()

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

fun SimpleMatrix.create(initializer: (Int, Int) -> Double) : SimpleMatrix {
    val s = SimpleMatrix(this.numRows(), this.numCols())
    for(i in 0 until s.numRows()) {
        for(j in 0 until s.numCols()) {
            s[i, j] = initializer(i ,j)
        }
    }
    return s
}

fun SimpleMatrix.create(initializer: (Int) -> Double) : SimpleMatrix {
    val s = SimpleMatrix(this.numRows(), this.numCols())
    for(i in 0 until s.numElements) {
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

fun SimpleMatrix.hadamard(other: SimpleMatrix) : SimpleMatrix {
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

