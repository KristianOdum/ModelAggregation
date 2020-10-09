import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import org.ejml.simple.SimpleMatrix
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.random.Random

const val MAX_X = 100000000.0
const val MIN_X = 0.0
const val N = 100000
const val h = 0.00000001 // small number..
const val learningRateRate = 1.1
const val epochs = 2000
val gr = (sqrt(5.0) + 1.0) / 2.0
const val tolerance = 0.000000000000001
var gss_b = 10.0

fun main() {
    var m = randMatrix(2, 3, 0.0, 1.0)
    val f = { x: SimpleMatrix ->
        val y = SimpleMatrix(x.numRows(), x.numCols())
        y[0,0] = x[0,0] * x[2,0] + x[2,0]
        y[1,0] = x[1,0] * x[2,0] + x[2,0]
        y[2,0] = -x[2,0]
        y
    }

    Random(System.currentTimeMillis())

    println("m: $m")
    for (i in 0 until epochs) {
        var g = gradient(m, f)
        g = g.divide(g.normF())
        val alpha = alpha { scaling -> cost(m.plus(g.scale(-scaling)), f) }

        m = m.minus(g.scale(alpha))
        m = m.divide(m.normF())
        println("m: $m")
        println("Gradient: $g")
        println("Cost: " + cost(m, f))
    }
    println(m)
}

fun alpha(cost: (Double) -> Double): Double {
    var a = 0.0
    var b = gss_b
    var c = b - (b - a) / gr
    var d = a + (b - a) / gr

    while(abs(c - d) > tolerance) {
        if (cost(c) < cost(d))
            b = d
        else
            a = c

        c = b - (b - a) / gr
        d = a + (b - a) / gr
    }

    return (a + b) / 2
}

fun gradient(m: SimpleMatrix, f: (SimpleMatrix) -> SimpleMatrix): SimpleMatrix {
    val gradient = SimpleMatrix(m.numRows(), m.numCols())
    val mutex = Mutex()

    runBlocking {
        val jobs = (0 until m.numElements).map {elem ->
            GlobalScope.launch {
                val g = derivative(m, f, elem)

                mutex.lock()
                gradient[elem] = g
                mutex.unlock()
            }
        }

        jobs.joinAll()
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
        x = randMatrix(m.numCols(), 1, MIN_X, MAX_X)

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