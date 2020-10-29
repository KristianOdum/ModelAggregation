import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import org.ejml.simple.SimpleMatrix
import kotlin.math.abs
import kotlin.math.absoluteValue
import kotlin.math.pow
import kotlin.math.sqrt

const val h = 0.0001 // small number..
val gr = (sqrt(5.0) + 1.0) / 2.0
const val gss_tolerance = 1.0E-20
var gss_b = 1.0


fun gradientDescentRMSProp(f: (SimpleMatrix) -> SimpleMatrix, epochs: Int, learningScale: Double = 0.8): Pair<Plot.Data, Plot.Data> {
    var m = global_m.copy()
    val plotData = Plot.data()
    val learningRateData = Plot.data()

    val prevGradSquared = m.create { _ -> 0.0 }
    val gradSquared = m.create { _ -> 0.0 }
    val beta = 0.9

    var tolerance = 1.0E5
    var lRate = 1.0E-3
    var interval = Int.MAX_VALUE
    var bestC = Double.MAX_VALUE
    var bestM = m.copy()
    var sprint = (epochs.toDouble() / 5.0).toInt()

    for (i in 1 until epochs) {
        val g = gradient(m, f, tolerance).normalize()

        lRate *= 0.99

        if ((epocs - i) == sprint) {
            m = bestM
        }

        for (j in 0 until m.numElements) {
            gradSquared[j] = (beta * prevGradSquared[j]) + ((1.0 - beta) * (g[j].pow(2.0)))
            m[j] += -(lRate / sqrt(gradSquared[j])) * g[j]
        }

        val c = cost(m, f, tolerance)
        if (c < bestC) {
            bestC = c
            bestM = m
        }
        print("\r${epocs - i} - $c")
        if (i > 1000)
            plotData.xy(i.toDouble(), c)
        m = m.rowNorm()
    }

    println("\rBest: $bestC")
    return Pair(plotData, learningRateData)
}

fun gradientDescent(f: (SimpleMatrix) -> SimpleMatrix, cost: (SimpleMatrix, (SimpleMatrix) -> SimpleMatrix) -> Double, n: Int, nhat: Int, epochs: Int): SimpleMatrix {
    var m = randMatrix(nhat, n, 0.0, 1.0)

    println("m: $m")
    for (i in 0 until epochs) {
        var g = gradient(m, f)
        g = g.divide(g.normF())

        gss_b /= 2

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
        val cost = cost(m, f)
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

fun gradient(m: SimpleMatrix, f: (SimpleMatrix) -> SimpleMatrix, tolerance: Double = 1.0E-1): SimpleMatrix {
    val gradient = SimpleMatrix(m.numRows(), m.numCols())
    val mutex = Mutex()
    var i = 0

    runBlocking {
        val jobs = (0 until m.numElements).map {elem ->
            GlobalScope.launch {
                val g = derivative(m, f, elem, tolerance)

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

fun derivative(m: SimpleMatrix, f: (SimpleMatrix) -> SimpleMatrix, e: Int, tolerance: Double): Double {
    var x: SimpleMatrix
    val mp = m.withSet(e, m[e] + h).rowNorm()
    val mpbar = mp.rightInverse()
    val mn = m.withSet(e, m[e] - h).rowNorm()
    val mnbar = mn.rightInverse()

    return untilAverageTolerance(tolerance) {
        x = randMatrix(m.numCols(), 1, MIN_X, MAX_X)

        (specificCost(mp, mpbar, f, x) - specificCost(mn, mnbar, f, x)) / (2 * h)
    }
}

