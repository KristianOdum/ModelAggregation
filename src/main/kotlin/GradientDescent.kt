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

val plotAlpha = false

const val averagesCount = 200

//triangle
var useTriangle = false
val learningRateInterval = 21230420343 / 50 // Hvad fuck er dette??? der stod: epochs / 50
var maxLearningRate = 8.0E-4
val learningRateScale = 0.5
var learningRateStep = maxLearningRate / learningRateInterval.toDouble()
var learningRateSign = 1
var currentLearningRate = 0.001

fun gradientDescentADAM(f: (SimpleMatrix) -> SimpleMatrix, epochs: Int) : Plot.Data {
    var m = global_m.copy().MGSON()
    val costData = Plot.data()

    var tolerance = 1.0E5
    var best = Pair(0, Double.MAX_VALUE)

    val firstCost = cost(m, f, 1.0E-2)
    val averages = Array(averagesCount) { firstCost }
    val averageData = Plot.data()

    // Adam
    val beta1 = 0.9
    val beta2 = 0.999
    val epsilon = 1.0E-12
    var adam_m = m.create { _ -> 0.0 }
    var adam_v = m.create { _ -> 0.0 }

    val m_elems = Array<Plot.Data>(m.numElements) { Plot.data() }
    m.allElements().forEach { Plot.data().xy(it, 0.0) }

    for (i in 1 until epochs) {
        if  (useTriangle) {
            if (i % learningRateInterval == 0) {
                maxLearningRate *= learningRateScale
                learningRateStep = maxLearningRate / learningRateInterval.toDouble()
            }
            if (i % (learningRateInterval / 2.0).toInt() == 0)
                learningRateSign *= -1
            currentLearningRate += learningRateStep * learningRateSign
            if (currentLearningRate < 0)
                currentLearningRate = learningRateStep
        }
        val g = gradient(m, f)

        for (j in 0 until m.numElements) {
            adam_m[j] = beta1 * adam_m[j] + (1 - beta1) * g[j]
            adam_v[j] = beta2 * adam_v[j] + (1 - beta2) * g[j].pow(2.0)

            val mhat = adam_m[j] / (1 - beta1.pow(i.toDouble()))
            val vhat = adam_v[j] / (1 - beta2.pow(i.toDouble()))

            m[j] = m[j] - (currentLearningRate * mhat) / (sqrt(vhat) + epsilon)
        }

        val c = cost(m, f, tolerance)
        if (c < best.second)
            best = Pair(i, c)

        averages[i % averagesCount] = c
        averageData.xy(i.toDouble(), averages.average())

        m = m.MGSON()
        m.allElements().withIndex().forEach { m_elems[it.index].xy(i.toDouble(), it.value) }
    }

    /*Plot.plot(Plot.plotOpts()
            .title("RMSProp"))
            .series("Cost Function", costData, Plot.seriesOpts())
            //.series("Mean-average", averageData, Plot.seriesOpts().color(Color.GREEN))
            .series("Best", Plot.data().xy(best.first.toDouble(), best.second), Plot.seriesOpts().marker(Plot.Marker.COLUMN).color(Color.MAGENTA))
            .saveWithExt()*/
    println("ADAM -> Best: ${best.second}")
    return averageData
}
fun gradientDescentRMSProp(f: (SimpleMatrix) -> SimpleMatrix, epochs: Int) : Plot.Data {
    var m = global_m.copy().MGSON()
    val costData = Plot.data()

    val prevGradSquared = m.create { _ -> 0.0 }
    val gradSquared = m.create { _ -> 0.0 }
    val beta = 0.9
    val epsilon = 1.0E-8

    var tolerance = 1.0E5
    var best = Pair(0, Double.MAX_VALUE)

    val firstCost = cost(m, f, 1.0E-2)
    val averages = Array(averagesCount) { firstCost }
    val averageData = Plot.data()

    for (i in 1 until epochs) {
        if (useTriangle) {
            if (i % learningRateInterval == 0) {
                maxLearningRate *= learningRateScale
                learningRateStep = maxLearningRate / learningRateInterval.toDouble()
            }
            if (i % (learningRateInterval / 2.0).toInt() == 0)
                learningRateSign *= -1
            currentLearningRate += learningRateStep * learningRateSign
            if (currentLearningRate < 0)
                currentLearningRate = learningRateStep
        }
        val g = gradient(m, f)

        for (j in 0 until m.numElements) {
            gradSquared[j] = (beta * prevGradSquared[j]) + ((1.0 - beta) * (g[j].pow(2.0)))
            m[j] += -(currentLearningRate / sqrt(gradSquared[j] + epsilon)) * g[j]
        }

        val c = cost(m, f, tolerance)
        if (c < best.second)
            best = Pair(i, c)

        averages[i % averagesCount] = c
        averageData.xy(i.toDouble(), averages.average())

        m = m.MGSON()
    }

    /*Plot.plot(Plot.plotOpts()
            .title("RMSProp"))
            .series("Cost Function", costData, Plot.seriesOpts())
            //.series("Mean-average", averageData, Plot.seriesOpts().color(Color.GREEN))
            .series("Best", Plot.data().xy(best.first.toDouble(), best.second), Plot.seriesOpts().marker(Plot.Marker.COLUMN).color(Color.MAGENTA))
            .saveWithExt()*/
    println("RMS -> Best: ${best.second}")
    return averageData
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

