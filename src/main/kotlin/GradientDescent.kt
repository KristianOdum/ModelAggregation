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

const val h = 0.0001 // small number..
val gr = (sqrt(5.0) + 1.0) / 2.0
const val gss_tolerance = 1.0E-5
var gss_b = 1.0

val plotAlpha = false

fun rms(average: Double, new: Double, epsilon: Double = 0.001, gamma:Double = 0.9)
        = sqrt((gamma * average + (1 - gamma) * new.pow(2.0)) + epsilon)

fun gradientDescentNesterovLearningRate(f: (SimpleMatrix) -> SimpleMatrix, cost: (SimpleMatrix, (SimpleMatrix) -> SimpleMatrix) -> Double, n: Int, nhat: Int, epochs: Int): SimpleMatrix {
    val m = randMatrix(nhat, n, 0.0, 1.0)
    var best = Double.MAX_VALUE
    val velocities = m.create { _ -> .0 }
    val alpha = .0
    val learningRate = .0001
    var lastGradient = m.create { _ -> .0 }

    repeat (epochs) {
        val g_nag = gradient(m + velocities.scale(alpha), f)
        val deltas = g_nag.create { _ -> 0.0 }

        for (i in 0 until g_nag.numElements) {
            deltas[i] = (lastGradient[i] - deltas[i])
        }

        val g = deltas.elementSum()

        for (i in 0 until m.numElements) {
            velocities[i] = alpha * velocities[i] + learningRate * g

            m[i] += velocities[i]
        }
        lastGradient = g_nag

        val c = cost(m, f)
        if(c < best)
            best = c
        print("\r" + c)
    }

    println("\nBest: $best")
    return m
}

fun gradientDescentDynamicLearningRate(f: (SimpleMatrix) -> SimpleMatrix, cost: (SimpleMatrix, (SimpleMatrix) -> SimpleMatrix) -> Double, n: Int, nhat: Int, epochs: Int): SimpleMatrix {
    val m = randMatrix(nhat, n, 0.0, 1.0)
    var best = Double.MAX_VALUE
    var best_m = m.create { _ -> .0 }
    val upscaleFactor = 1.2
    val downscaleFactor = 0.5
    val prevGradient = m.create { _ -> 0.0 }
    val learningRates = m.create { _ -> 0.001 }
    val minimaFound = m.create { _ -> -1.0}

    repeat (epochs) {
        val g = gradient(m, f)

        for (i in 0 until m.numElements) {
            if (g[i] > 0)
                m[i] -= learningRates[i]
            else
                m[i] += learningRates[i]

            if (prevGradient[i] * g[i] > 0)
                learningRates[i] *= upscaleFactor
            else {
                learningRates[i] *= downscaleFactor
                minimaFound[i] = 1.0
            }

            prevGradient[i] = g[i]
        }
        val c = cost(m, f)
        if(c < best) {
            best = c
            best_m = m
        }
        print("\r" + c)
    }

    println("\nBest: $best")
    return best_m
}
fun gradientDescentConstantLearningRate(f: (SimpleMatrix) -> SimpleMatrix, cost: (SimpleMatrix, (SimpleMatrix) -> SimpleMatrix) -> Double, n: Int, nhat: Int, epochs: Int): SimpleMatrix {
    val m = randMatrix(nhat, n, 0.0, 1.0)
    val stepSize = 0.0001
    var best = Double.MAX_VALUE

    repeat (epochs) {
        val g = gradient(m, f)

        for (i in 0 until m.numElements) {
            if (g[i] > 0)
                m[i] -= stepSize
            else
                m[i] += stepSize
        }
        val c = cost(m, f)
        if(c < best)
            best = c
        print("\r" + c)
    }

    println("\nBest: $best")
    return m
}

fun gradientDescentADADelta(f: (SimpleMatrix) -> SimpleMatrix, cost: (SimpleMatrix, (SimpleMatrix) -> SimpleMatrix) -> Double, n: Int, nhat: Int, epochs: Int): SimpleMatrix {
    val m = randMatrix(nhat, n, 0.0, 1.0)
    var best = Double.MAX_VALUE
    val averageGradients = m.create { _ -> 0.0 }
    val averageDeltas = m.create {_ -> 0.0}
    val previousDeltas = m.create {_ -> 0.0}
    val previousAverageDeltas = m.create { _ -> 0.0 }
    val gamma = 0.9

    repeat (epochs) {
        val g = gradient(m, f)

        for (i in 0 until m.numElements) {
            // Calculate new delta
            val delta = -(rms(previousAverageDeltas[i], previousDeltas[i]) / rms(averageDeltas[i], g[i]) * g[i])

            averageGradients[i] = gamma * averageGradients[i] + (1 - gamma) * g[i].pow(2.0)

            m[i] += delta
            // calculate average delta
            // Update previous deltas
            previousAverageDeltas[i] = averageDeltas[i]
            previousDeltas[i] = delta
            averageDeltas[i] = gamma * averageDeltas[i] + (1 - gamma) * delta.pow(2.0)
        }
        val c = cost(m, f)
        if(c < best)
            best = c
        print("\r" + c)
    }
    println("\nBest: $best")
    return m
}

fun gradientDescentRMSProp(f: (SimpleMatrix) -> SimpleMatrix, cost: (SimpleMatrix, (SimpleMatrix) -> SimpleMatrix) -> Double, n: Int, nhat: Int, epochs: Int): SimpleMatrix {
    val m = randMatrix(nhat, n, 0.0, 1.0)
    val averageGradients  = m.create { _ -> .0 }
    var best = Double.MAX_VALUE

    val learningRate = 0.001
    val eps = 1e-8

    repeat (epochs) {
        val g = gradient(m, f)

        for (i in 0 until m.numElements) {
            averageGradients[i] += rms(averageGradients[i], g[i])
            val delta = -(learningRate / sqrt(averageGradients[i] + eps)) * g[i]

            m[i] += delta
        }
        val c = cost(m, f)
        if(c < best)
            best = c
        print("\r" + c)
    }
    println("\nBest: $best")

    return m
}

fun gradientDescent(f: (SimpleMatrix) -> SimpleMatrix, cost: (SimpleMatrix, (SimpleMatrix) -> SimpleMatrix) -> Double, n: Int, nhat: Int, epochs: Int): SimpleMatrix {
    var m = randMatrix(nhat, n, 0.0, 1.0).rowNorm()
    gss_b = 1.0
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

        m = m.minus(g.scale(alpha)).rowNorm()

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
    val mp = m.withSet(e, m[e] + h).rowNorm()
    val mpbarmp = mp.rightInverse().mult(mp)
    val mn = m.withSet(e, m[e] - h).rowNorm()
    val mnbarmn = mn.rightInverse().mult(mp)

    return untilAverageTolerance(1.0E-1) {
        x = randMatrix(m.numCols(), 1, MIN_X, MAX_X)

        (specificCost(mp, mpbarmp, f, x) - specificCost(mn, mnbarmn, f, x)) / (2 * h)
    }
}

