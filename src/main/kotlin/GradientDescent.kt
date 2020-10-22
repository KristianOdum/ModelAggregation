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
const val gss_tolerance = 1.0E-20
var gss_b = 0.5

fun gradientDescent(f: (SimpleMatrix) -> SimpleMatrix, cost: (SimpleMatrix, (SimpleMatrix) -> SimpleMatrix) -> Double, n: Int, nhat: Int, epochs: Int): SimpleMatrix {
    var m = randMatrix(nhat, n, 0.0, 1.0)

    println("m: $m")
    for (i in 0 until epochs) {
        var g = gradient(m, f)
        g = g.divide(g.normF())

        gss_b /= 10

        /*
        val dataPoints = mutableListOf<Pair<Double, Double>>()

        runBlocking {
            val dataPointMutex = Mutex()
            val jobs = (0 until 100).map {
                GlobalScope.launch {
                    val d = gss_b * it.toDouble() / 100
                    val c = cost(m.plus(g.scale(-d)), f)

                    dataPointMutex.lock()
                    dataPoints.add(Pair(d, c))
                    dataPointMutex.unlock()
                }
            }
            jobs.joinAll()
        }
        dataPoints.sortBy { it.first }

        val alphaPlot = Plot.data()
*/
        val alpha = alpha { scaling ->
            cost(m.plus(g.scale(-scaling)), f)
        }

        println("alpha: $alpha")

        m = m.minus(g.scale(alpha)).colNorm()
        println("m: $m")
        println("Gradient: $g")
        //val cost = cost(m, f)
        println("Cost: " + cost)
/*
        val plot = Plot.plot(Plot.plotOpts()
                .title("Cost function"))
                .series("data", Plot.data().xy(dataPoints.map { it.first }, dataPoints.map { it.second }), Plot.seriesOpts())
                .series("alpha", Plot.data().xy(alpha, cost), Plot.seriesOpts().color(Color.RED).marker(Plot.Marker.DIAMOND))

        plot.save("plot$i", "png") */
    }
    return m
}

fun alpha(cost: (Double) -> Double): Double {
    var a = 0.0
    var b = gss_b
    var c = b - (b - a) / gr
    var d = a + (b - a) / gr

    var fc = cost(c)
    var fd = cost(d)

    while(abs(c - d) > gss_tolerance) {
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
                print("\r${i.toDouble() / m.numElements}")
                mutex.unlock()
            }
        }

        jobs.joinAll()
    }

    return gradient
}

fun derivative(m: SimpleMatrix, f: (SimpleMatrix) -> SimpleMatrix, e: Int): Double {
    var x: SimpleMatrix
    val mp = m.withSet(e, m[e] + h).colNorm()
    val mpbar = mp.rightInverse()
    val mn = m.withSet(e, m[e] - h).colNorm()
    val mnbar = mn.rightInverse()

    return untilAverageTolerance {
        x = randMatrix(m.numCols(), 1, MIN_X, MAX_X)

        (specificCost(mp, mpbar, f, x) - specificCost(mn, mnbar, f, x)) / (2 * h)
    }
}

