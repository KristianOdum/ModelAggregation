import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import org.ejml.simple.SimpleMatrix
import java.awt.Color
import kotlin.math.abs
import kotlin.math.sqrt

const val h = 0.00001 // small number..
val gr = (sqrt(5.0) + 1.0) / 2.0
const val gss_tolerance = 1.0E-5
var gss_b = 1.0

val plotAlpha = false

fun gradientDescent(f: (SimpleMatrix) -> SimpleMatrix, cost: (SimpleMatrix, (SimpleMatrix) -> SimpleMatrix) -> Double, n: Int, nhat: Int, epochs: Int): SimpleMatrix {
    var m = randMatrix(nhat, n, 0.0, 1.0)

    println("m: $m")
    for (i in 0 until epochs) {
        var g = gradient(m, f)
        g = g.divide(g.normF())

        val alpha = alpha { scaling ->
            cost(m.plus(g.scale(-scaling)), f)
        }
        gss_b = 0.6 * (gss_b - (2 * alpha)) + (2 * alpha)

        if (plotAlpha) {
            val plot = Plot.plot(Plot.plotOpts()
                    .title("Cost function"))
                    .series("data", plotAlpha(m, g, f), Plot.seriesOpts())
                    .series("alpha", Plot.data().xy(alpha, cost(m.minus(g.scale(alpha)), f)), Plot.seriesOpts().color(Color.RED).marker(Plot.Marker.DIAMOND))
                    .save("plot$i", "png")
        }

        m = m.minus(g.scale(alpha)).colNorm()

        val cost = cost(m, f)
        println("Cost: " + cost)

    }
    return m
}

fun plotAlpha(m: SimpleMatrix, g: SimpleMatrix, f: (SimpleMatrix) -> SimpleMatrix): Plot.Data {
    val dataPoints = mutableListOf<Pair<Double, Double>>()
    val plotCount = 50
    runBlocking {
        val dataPointMutex = Mutex()
        val jobs = (0 until plotCount).map {
            GlobalScope.launch {
                val d = gss_b * it.toDouble() / plotCount
                val c = cost(m.plus(g.scale(-d)), f)

                dataPointMutex.lock()
                dataPoints.add(Pair(d, c))
                dataPointMutex.unlock()
            }
        }
        jobs.joinAll()
    }
    dataPoints.sortBy { it.first }
    return  Plot.data().xy(dataPoints.map { it.first }, dataPoints.map { it.second })
}

fun alpha(cost: (Double) -> Double): Double {
    var a = 0.0
    var b = gss_b
    var c = b - (b - a) / gr
    var d = a + (b - a) / gr

    var fc = cost(c)
    var fd = cost(d)

    while(abs(c - d) > gss_b * gss_tolerance) {
        if (fc < fd) {
            b = d
            d = c
            fd = fc
            c = b - (b - a) / gr
            fc = cost(c)
        } else {
            a = c
            c = d
            fc = fd
            d = a + (b - a) / gr
            fd = cost(d)
        }
    }

    return (a + b) / 2
}

fun gradient(m: SimpleMatrix, f: (SimpleMatrix) -> SimpleMatrix): SimpleMatrix {
    val gradient = SimpleMatrix(m.numRows(), m.numCols())
    val mutex = Mutex()
    var i = 0

    runBlocking {
        val jobs = (0 until m.numElements).map {elem ->
            GlobalScope.launch {
                val g = derivative(m, f, elem)

                mutex.lock()
                i++
                gradient[elem] = g
                //print("\r${i.toDouble() / m.numElements}")
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
    val mpbarmp = mp.rightInverse().mult(mp)
    val mn = m.withSet(e, m[e] - h)
    val mnbarmn = mn.rightInverse().mult(mp)

    return untilAverageTolerance(1.0E-1) {
        x = randMatrix(m.numCols(), 1, MIN_X, MAX_X)

        (specificCost(mp, mpbarmp, f, x) - specificCost(mn, mnbarmn, f, x)) / (2 * h)
    }
}

