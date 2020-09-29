import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import org.ejml.simple.SimpleMatrix
import kotlin.math.pow
import kotlin.random.Random

const val MAX_X = 1000.0
const val MIN_X = 0.0
const val N = 100000
const val h = 0.00000001 // small number..
const val learningRateRate = 1.1
const val epochs = 2000

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

    var learningRate = 0.0001
    var prevG = SimpleMatrix(m.numElements,1)

    println("m: $m")
    for (i in 0 until epochs) {
        val g = gradient(m, f)
        val gvec = SimpleMatrix().create(g.numElements, 1) { e -> g[e] }
        if (prevG.dot(gvec) < 0)
            learningRate *= 1.0 / learningRateRate
        prevG = gvec
        m = m.minus(g.scale(learningRate))
        m = m.divide(m.normF())
        println("m: $m")
        println("Gradient: $g")
        println("Cost: " + cost(m, f))
        println("Learning rate: $learningRate")
    }
    println(m)
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

fun SimpleMatrix.create(rows: Int, columns: Int, initializer: (Int, Int) -> Double) : SimpleMatrix {
    val s = SimpleMatrix(rows, columns)
    for(i in 0 until s.numRows()) {
        for(j in 0 until s.numCols()) {
            s[i, j] = initializer(i ,j)
        }
    }
    return s
}

fun SimpleMatrix.create(rows: Int, columns: Int, initializer: (Int) -> Double) : SimpleMatrix {
    val s = SimpleMatrix(rows, columns)
    for(i in 0 until s.numElements) {
        s[i] = initializer(i)
    }
    return s
}