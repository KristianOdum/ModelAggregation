import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import org.ejml.simple.SimpleMatrix
import java.awt.Color
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

const val h = 0.00000001 // small number..
val gr = (sqrt(5.0) + 1.0) / 2.0
const val gss_tolerance = 1.0E-2
var gss_b = 1.0

val plotAlpha = true

fun gradientDescentRMSProp(f: (SimpleMatrix) -> SimpleMatrix, epochs: Int): Plot.Data {
    var m = global_m.copy()
    val plotData = Plot.data()

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
        val g = gradient(m, f).normalize()

        lRate *= 0.99

        if ((epochs - i) == sprint) {
            m = bestM
        }

        for (j in 0 until m.numElements) {
            gradSquared[j] = (beta * prevGradSquared[j]) + ((1.0 - beta) * (g[j].pow(2.0)))
            m[j] += -(lRate / sqrt(gradSquared[j])) * g[j]
        }

        val c = cost(m, f)
        if (c < bestC) {
            bestC = c
            bestM = m
        }
        print("\r${epochs - i} - $c")
        if (i > 1000)
            plotData.xy(i.toDouble(), c)
        m = m.rowNorm()
    }

    println("\rBest: $bestC")
    return plotData
}

fun gradientDescent(f: (SimpleMatrix) -> SimpleMatrix, cost: (SimpleMatrix, (SimpleMatrix) -> SimpleMatrix) -> Double, n: Int, nhat: Int, epochs: Int): SimpleMatrix {
    var m = randMatrix(nhat, n, 0.0, 1.0).rowNorm()
    gss_b = 1.0
    for (i in 0 until epochs) {
        var g = gradient(m, f)
        g = g.divide(g.normF())


        val alpha = /* gss { scaling -> cost(m.plus(g.scale(-scaling)), f)} */bis_derivative { scaling ->
            oneDimensionalDerivative(m, g.negative(), scaling, f)
        }
        gss_b = 0.6 * (gss_b - (2 * alpha)) + (2 * alpha)

        if (plotAlpha) {
            val plot = Plot.plot(Plot.plotOpts()
                    .title("Cost function"))
                    .series("data", plotAlpha(m, g, f), Plot.seriesOpts())
                    .series("alpha", Plot.data().xy(alpha, 0.0), Plot.seriesOpts().color(Color.RED).marker(Plot.Marker.DIAMOND))
                    .save("plot$i", "png")
        }

        m = m.minus(g.scale(alpha)).MGSON()
        println("${cost(m, f)} $alpha")
    }
    return m
}

fun plotAlpha(m: SimpleMatrix, g: SimpleMatrix, f: (SimpleMatrix) -> SimpleMatrix): Plot.Data {
    val dataPoints = mutableListOf<Pair<Double, Double>>()
    val plotCount = 100
    runBlocking {
        val dataPointMutex = Mutex()
        val jobs = (0 until plotCount).map {
            GlobalScope.launch {
                val d = gss_b * it.toDouble() / plotCount
                val c = oneDimensionalDerivative(m, g.negative(), d, f)

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

fun gss(cost: (Double) -> Double): Double {
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

fun bis_derivative(derivative: (Double) -> Double): Double {
    var a = 0.0

    // Identify upper bound that has positive slope
    var b = gss_b
    while (derivative(b) < 0) {
        b *= gr
    }

    var m = a + (b - a) / 2
    while(b - a > gss_b * gss_tolerance) {
        if (derivative(m) < 0) {
            // Minimum lies after m
            a = m
        } else {
            // Minimum lies before m
            b = m
        }
        m = a + (b - a) / 2
    }

    return m
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
    val h1 = (m[e] + h) - m[e]
    val h2 = -(m[e] - h) + m[e]

    val mp = m.withSet(e, m[e] + h1)
    val mpbarmp = mp.rightInverse().mult(mp)

    val tempn = m[e] - h
    val mn = m.withSet(e,  m[e] - h2)
    val mnbarmn = mn.rightInverse().mult(mn)

    return untilAverageTolerance(1.0E-3) {
        val x = randMatrix(m.numCols(), 1, MIN_X, MAX_X)

        (specificCost(mp, mpbarmp, f, x) - specificCost(mn, mnbarmn, f, x)) / (h1 + h2)
    }
}

// Calculates d/dalpha C(m + d*alpha)
fun oneDimensionalDerivative(m: SimpleMatrix, d: SimpleMatrix, alpha: Double, f: (SimpleMatrix) -> SimpleMatrix): Double {
    val h1 = (alpha + h) - alpha
    val h2 = -(alpha - h) + alpha

    val mp = m.plus(d.scale(alpha + h1))
    val mpbarmp = mp.rightInverse().mult(mp)

    val mn = m.plus(d.scale(alpha - h2))
    val mnbarmn = mn.rightInverse().mult(mn)

    return untilAverageTolerance(1.0E-5) {
        val x = randMatrix(m.numCols(), 1, MIN_X, MAX_X)

        (specificCost(mp, mpbarmp, f, x) - specificCost(mn, mnbarmn, f, x)) / (h1 + h2)
    }
}

