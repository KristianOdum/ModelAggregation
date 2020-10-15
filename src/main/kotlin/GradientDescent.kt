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

const val h = 0.00000001 // small number..
val gr = (sqrt(5.0) + 1.0) / 2.0
const val tolerance = 0.000000000001
var gss_b = 0.1

fun gradientDescent(f: (SimpleMatrix) -> SimpleMatrix, cost: (SimpleMatrix, (SimpleMatrix) -> SimpleMatrix) -> Double, n: Int, nhat: Int, epochs: Int): SimpleMatrix {
    var m = randMatrix(nhat, n, 0.0, 1.0)

    println("m: $m")
    for (i in 0 until epochs) {
        var g = gradient(m, f)
        g = g.divide(g.normF())


        for (d in (0.0..0.1).iterator { it + 0.001 })
            print("($d, ${cost(m.plus(g.scale(-d)), f)}),")
        println()

        val alpha = alpha { scaling -> cost(m.plus(g.scale(-scaling)), f) }

        println("alpha: $alpha")

        m = m.minus(g.scale(alpha))
        m = m.divide(m.normF())
        println("m: $m")
        println("Gradient: $g")
        println("Cost: " + cost(m, f))
    }
    return m
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

    return untilAverageTolerance(0.1) {
        x = randMatrix(m.numCols(), 1, MIN_X, MAX_X)

        (specificCost(mp, f, x) - specificCost(mn, f, x)) / (2 * h)
    }
}

