import org.ejml.simple.SimpleMatrix
import kotlin.math.pow
import kotlin.random.Random

const val MAX_X = 100.0
const val MIN_X = 0.0
const val N = 100000
const val h = 0.000000000000001 // small number..

fun main() {
    var m = randMatrix(2, 3, -2.0, 2.0)
    val f = { x: SimpleMatrix ->
        val y = SimpleMatrix(x.numRows(), x.numCols())
        y[0,0] = x[0,0] * x[2,0] + x[2,0]
        y[1,0] = x[1,0] * x[2,0] + x[2,0]
        y[2,0] = -x[2,0]
        y
    }

    val learningRate = 0.01

    m[0,0] = 54.0
    m[0,1] = 1.8
    m[0,2] = 5.0
    m[1,0] = 7.9
    m[1,1] = 16.2
    m[1,2] = 0.0

    println("m: $m")
    for (i in 0..200) {
        val g = gradient(m, f)
        m = m.minus(g.scale(learningRate))
        println("m: $m")
        println("Gradient: $g")
        println("Cost: " + cost(m, f))
    }
    println(m)
}

fun gradient(m: SimpleMatrix, f: (SimpleMatrix) -> SimpleMatrix): SimpleMatrix {
    val gradient = SimpleMatrix(m.numRows(), m.numCols())

    for (elem in 0 until m.numElements) {
        gradient[elem] = derivative(m, f, elem)
    }

    return gradient
}

fun derivative(m: SimpleMatrix, f: (SimpleMatrix) -> SimpleMatrix, e: Int): Double {
    var x: SimpleMatrix
    val mp = m.withSet(e, m[e] + h)
    val mn = m.withSet(e, m[e] - h)
    var slope = 0.0

    for (i in 0..N) {
        x = randMatrix(m.numCols(), 1, MIN_X, MAX_X)

        val positiveSlope = (specificCost(mp, f, x) - specificCost(m, f, x)) / h
        val negativeSlope = (specificCost(mn, f, x) - specificCost(m, f, x)) / h

        slope += if (positiveSlope * negativeSlope < 0.0) positiveSlope else 0.0
    }

    return slope / N
}

fun cost(m: SimpleMatrix, f: (SimpleMatrix) -> SimpleMatrix): Double {
    var x: SimpleMatrix
    var average = 0.0

    for (i in 0..N) {
        x = randMatrix(m.numCols(), 1, 0.0, MAX_X)

        average += specificCost(m, f, x).pow(2.0)
    }

    return average / N
}

fun specificCost(m: SimpleMatrix, f: (SimpleMatrix) -> SimpleMatrix, x: SimpleMatrix) =
    m.mult(f(x)).minus(m.mult(f(m.rightInverse().mult(m.mult(x))))).normF()

fun SimpleMatrix.withSet(i: Int, value: Double): SimpleMatrix {
    val new = SimpleMatrix(this)
    new[i] = value
    return new
}

fun SimpleMatrix.rightInverse() = this.transpose().mult(this.mult(this.transpose()).invert())!!

fun randMatrix(numRows: Int, numCols: Int, from: Double, to: Double): SimpleMatrix {
    val m = SimpleMatrix(numRows, numCols)

    for (i in 0 until m.numElements) {
        m[i] = Random.nextDouble(from, to)
    }

    return m
}