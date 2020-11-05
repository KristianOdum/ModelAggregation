/*

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import org.ejml.simple.SimpleMatrix
import java.awt.Color
import kotlin.math.abs
import kotlin.math.log
import kotlin.math.pow
import kotlin.math.sqrt

const val h = 0.00000001 // small number..
val gr = (sqrt(5.0) + 1.0) / 2.0
const val gss_tolerance = 1.0E-2
var gss_b = 1.0

val plotAlpha = false

const val averagesCount = 20

// plateau check
val intervalFactor = 0.5
var plateauCheckInterval = 50
val plateauAverageCount = 3
val plateauLastAverages = Array(plateauAverageCount) { Double.MAX_VALUE }

// heavy plateau check
val intervalFactorHeavy = 0.05
var lastPlateauAverage = Double.MAX_VALUE / 2.0

// absolute plateau
var lastLastPlateauAverage = Double.MAX_VALUE / 2.0

// min epochs
val minimumEpochs = 1000

fun gradientDescentSimpleDynamic(_m: SimpleMatrix, f: (SimpleMatrix) -> SimpleMatrix, epochs: Int, _tolerance: Double = 1.0E-2, _learningRate: Double = 0.003, _clusterSize: Int) : Pair<Plot.Data, SimpleMatrix> {
    var m = _m.MGSON()
    var tolerance = _tolerance
    var learningRate = _learningRate
    val clusterSize = _clusterSize
    val costData = Plot.data()
    var best = Pair(0, Double.MAX_VALUE)
    var bestM = m.copy()

    val firstCost = cost(m, f, tolerance, clusterSize)
    val averages = Array(averagesCount) { firstCost }
    val averageData = Plot.data()
    var average = averages.average()

    // Dynamic
    var prevG = m.create { _ -> 0.0 }
    val learningRates = m.create { _ -> learningRate }

    for (i in 1 until epochs) {
        val g = gradient(m, f, tolerance).normalize()
        for (j in 0 until m.numElements) {
            if (g[j] * prevG[j] < 0) {
                learningRates[j] *= 0.0001
            }
            else
                learningRates[j] *= 1.2

            if (g[j] > 0)
                m[j] -= learningRates[j]
            else
                m[j] += learningRates[j]
        }
        prevG = g.copy()

        val c = cost(m, f, tolerance, clusterSize)
        averages[i % averagesCount] = c
        average = averages.average()
        if (c < best.second) {
            val precise_c = cost(m, f, 1.0E-5, clusterSize)
            if (precise_c < best.second) {
                best = Pair(i, precise_c)
                bestM = m.copy()
            }
        }
        averageData.xy(i.toDouble(), log(average, 10.0))
        costData.xy(i.toDouble(), c)

        print("\r${epochs-i} -> $average")
        m = m.MGSON()
    }
    println("\nSimple dynamic -> Best: ${cost(bestM, f, 1.0E-5, 50)}")
    return Pair(averageData, bestM)
}

fun gradientDescentConstant(_m: SimpleMatrix, f: (SimpleMatrix) -> SimpleMatrix, epochs: Int, _tolerance: Double = 1.0E-2, _learningRate: Double = 0.003, _clusterSize: Int) : Pair<Plot.Data, SimpleMatrix> {
    var m = _m.MGSON()
    var tolerance = _tolerance
    var learningRate = _learningRate
    var clusterSize = _clusterSize
    val costData = Plot.data()
    var best = Pair(0, Double.MAX_VALUE)
    var bestM = m.copy()

    val firstCost = cost(m, f, tolerance, clusterSize)
    val averages = Array(averagesCount) { firstCost }
    val averageData = Plot.data()
    var average = averages.average()

    for (i in 1 until epochs) {
        val g = gradient(m, f, tolerance).normalize()
        for (j in 0 until m.numElements) {
            if (g[j] > 0)
                m[j] -= learningRate
            else
                m[j] += learningRate
        }

        val c = cost(m, f, tolerance, clusterSize)
        averages[i % averagesCount] = c
        average = averages.average()
        if (c < best.second) {
            val precise_c = cost(m,f, 1.0E-5, clusterSize)
            if (precise_c < best.second) {
                best = Pair(i, precise_c)
                bestM = m.copy()
            }
        }
        averageData.xy(i.toDouble(), log(average, 10.0))
        costData.xy(i.toDouble(), c)

        print("\r${epochs-i} -> $average")
        m = m.MGSON()
    }
    println("\nConstant -> Best: ${cost(bestM, f, 1.0E-5, 50)}")
    return Pair(averageData, bestM)
}

fun gradientDescentMomentum(_m: SimpleMatrix, f: (SimpleMatrix) -> SimpleMatrix, epochs: Int, _tolerance: Double = 1.0E-2, _learningRate: Double = 0.003, _clusterSize: Int = 1) : Pair<Plot.Data, SimpleMatrix> {
    var learningRate = _learningRate
    var tolerance = _tolerance
    var clusterSize = _clusterSize
    var m = _m.MGSON()
    val costData = Plot.data()
    var best = Pair(0, Double.MAX_VALUE)
    var bestM = m.copy()

    val firstCost = cost(m, f, 1.0E-2, clusterSize)
    val averages = Array(averagesCount) { firstCost }
    val averageData = Plot.data()
    var average = averages.average()

    // momentum
    val velocities = m.create { _ -> 0.0 }
    var beta = 0.5
    var betaMax = 0.9
    val coefStep = (betaMax - beta) / epochs.toDouble()


    for (i in 1..epochs) {
        // Check if we have plateaued
        /*if (i % plateauCheckInterval == 0 && i >= plateauAverageCount * plateauCheckInterval) {
            var hitPlateau = true

            for (p in plateauLastAverages)
                if (average < p * (1.0-intervalFactor))
                    hitPlateau = false

            if (hitPlateau) {
                val plateauAverage = plateauLastAverages.average()

                if (plateauAverage > lastPlateauAverage * (1.0-intervalFactorHeavy) && plateauAverage < lastPlateauAverage * (1.0+2*intervalFactorHeavy)) {
                    //println("\n\nHeavy plateau -> Making cost more precise")
                    //tolerance *= 0.5
                    //clusterSize *= 2

                    if (plateauAverage > lastLastPlateauAverage * (1.0-intervalFactorHeavy) && plateauAverage < lastLastPlateauAverage * (1.0+2*intervalFactorHeavy)) {
                        println("\n\nMaking cost function more presice dit not help -> terminating")
                        if (i >= minimumEpochs)
                            break
                    }
                    lastLastPlateauAverage = lastPlateauAverage

                    for (j in 0 until plateauLastAverages.size)
                        plateauLastAverages[j] = Double.MAX_VALUE / 2.0
                }

                println("\nLocal plateau.. Last average = $lastPlateauAverage Current average $average -> Downscaling learning rate to ${learningRate * 0.5}")
                learningRate *= 0.1
                lastPlateauAverage = plateauAverage
                //m = bestM.copy()
            }
            plateauLastAverages[(i / plateauCheckInterval) % plateauAverageCount] = average
        }*/

        val g = gradient(m, f, tolerance, clusterSize = clusterSize).normalize()
        beta += coefStep
        for (j in 0 until m.numElements) {
            velocities[j] = beta * velocities[j] + (1 - beta) * g[j]
            velocities[j] = velocities[j] / (1 - beta.pow(epochs))

            m[j] += -(learningRate * velocities[j])
        }

        val c = cost(m, f, tolerance, clusterSize)
        averages[i % averagesCount] = c
        average = averages.average()
        if (c < best.second) {
            val precise_c = cost(m, f, 1.0E-5, clusterSize = 1000)
            if (precise_c < best.second) {
                best = Pair(i, precise_c)
                bestM = m.copy()
            }
        }
        averageData.xy(i.toDouble(), log(average, 10.0))
        costData.xy(i.toDouble(), c)

        print("\r${epochs - i} -> $average by lr: $learningRate")
        m = m.MGSON()
    }
    println("\nMomentum -> Best: ${cost(bestM, f, 1.0E-5, clusterSize)}")
    return Pair(averageData, m)
}

fun gradientDescentADAM(_m: SimpleMatrix, f: (SimpleMatrix) -> SimpleMatrix, epochs: Int, _tolerance: Double = 1.0E-2, _learningRate: Double = 0.003, _clusterSize: Int): Pair<Plot.Data, SimpleMatrix> {
    var learningRate = _learningRate
    var tolerance = _tolerance
    var clusterSize = _clusterSize
    var m = _m.MGSON()
    val costData = Plot.data()
    var best = Pair(0, Double.MAX_VALUE)
    var bestM = m.copy()

    val firstCost = cost(m, f, 1.0E-2, clusterSize)
    val averages = Array(averagesCount) { firstCost }
    val averageData = Plot.data()
    var average = averages.average()

    // Adam
    val beta1 = 0.9
    val beta2 = 0.999
    val epsilon = 1.0E-12
    var adam_m = m.create { _ -> 0.0 }
    var adam_v = m.create { _ -> 0.0 }

    for (i in 1..epochs) {
        // Check if we have plateaued
        /*if (i % plateauCheckInterval == 0 && i >= plateauAverageCount * plateauCheckInterval) {
            var hitPlateau = true

            for (p in plateauLastAverages)
                if (average < p * (1.0 - intervalFactor))
                    hitPlateau = false

            if (hitPlateau) {
                val plateauAverage = plateauLastAverages.average()

                if (plateauAverage > lastPlateauAverage * (1.0 - intervalFactorHeavy) && plateauAverage < lastPlateauAverage * (1.0+2*intervalFactorHeavy)) {
                    //println("\n\nHeavy plateau -> Making cost more precise")
                    //tolerance *= 0.5
                    //clusterSize *= 2

                    if (plateauAverage > lastLastPlateauAverage * (1.0 - intervalFactorHeavy) && plateauAverage < lastPlateauAverage * (1.0+2*intervalFactorHeavy)) {
                        println("\n\nMaking cost function more presice dit not help -> terminating")
                        if (i >= minimumEpochs)
                            break
                    }
                    lastLastPlateauAverage = lastPlateauAverage

                    for (j in 0 until plateauLastAverages.count())
                        plateauLastAverages[j] = Double.MAX_VALUE / 2.0
                }

                println("\nLocal plateau.. Last average = $lastPlateauAverage Current average $average -> Downscaling learning rate to ${learningRate * 0.5}")
                learningRate *= 0.1
                lastPlateauAverage = plateauAverage
                m = bestM.copy()
            }
            plateauLastAverages[(i / plateauCheckInterval) % plateauAverageCount] = average
        }*/
		
        val g = gradient(m, f, tolerance, clusterSize)
        for (j in 0 until m.numElements) {
            adam_m[j] = beta1 * adam_m[j] + (1 - beta1) * g[j]
            adam_v[j] = beta2 * adam_v[j] + (1 - beta2) * g[j].pow(2.0)

            val mhat = adam_m[j] / (1 - beta1.pow(i.toDouble()))
            val vhat = adam_v[j] / (1 - beta2.pow(i.toDouble()))

            m[j] = m[j] - (learningRate * mhat) / (sqrt(vhat) + epsilon)
        }
        val c = cost(m, f, tolerance, clusterSize)
        averages[i % averagesCount] = c
        average = averages.average()
        if (c < best.second) {
            val precise_c = cost(m, f, 1.0E-5, clusterSize = 50)
            if (precise_c < best.second) {
                best = Pair(i, precise_c)
                bestM = m.copy()
            }
        }
        averageData.xy(i.toDouble(), log(average, 10.0))
        costData.xy(i.toDouble(), c)

        print("\r${epochs - i} -> $average by lr: $learningRate")
        m = m.MGSON()
        global_i++
    }
    println("\nADAM -> Best: ${cost(bestM, f, 1.0E-5, clusterSize)}")
    return Pair(averageData, m)
}

fun gradientDescentRMSProp(_m: SimpleMatrix, f: (SimpleMatrix) -> SimpleMatrix, epochs: Int, _tolerance: Double = 1.0E-2, _learningRate: Double = 0.003, _clusterSize: Int): Pair<Plot.Data, SimpleMatrix> {
    var learningRate = _learningRate
    var tolerance = _tolerance
    var clusterSize = _clusterSize
    var m = _m.MGSON()
    val costData = Plot.data()
    var best = Pair(0, Double.MAX_VALUE)
    var bestM = m.copy()

    val firstCost = cost(m, f, 1.0E-2, clusterSize)
    val averages = Array(averagesCount) { firstCost }
    val averageData = Plot.data()
    var average = averages.average()

    val prevGradSquared = m.create { _ -> 0.0 }
    val gradSquared = m.create { _ -> 0.0 }
    val beta = 0.9
    val epsilon = 1.0E-8

    for (i in 1..epochs) {
        // Check if we have plateaued
        /*if (i % plateauCheckInterval == 0 && i >= plateauAverageCount * plateauCheckInterval) {
            var hitPlateau = true

            for (p in plateauLastAverages)
                if (average < p * (1.0-intervalFactor))
                    hitPlateau = false

            if (hitPlateau) {
                val plateauAverage = plateauLastAverages.average()

                if (plateauAverage > lastPlateauAverage * (1.0-intervalFactorHeavy)) {
                    //println("\n\nHeavy plateau -> Making cost more precise")
                    //tolerance *= 0.5
                    //clusterSize *= 2

                    if (plateauAverage > lastLastPlateauAverage * (1.0-intervalFactorHeavy)) {
                        println("\n\nMaking cost function more presice dit not help -> terminating")
                        if (i >= minimumEpochs)
                            break
                    }
                    lastLastPlateauAverage = lastPlateauAverage

                    for (j in 0 until plateauLastAverages.count())
                        plateauLastAverages[j] = Double.MAX_VALUE / 2.0
                }

                println("\nLocal plateau.. Last average = $lastPlateauAverage Current average $average -> Downscaling learning rate to ${learningRate * 0.5}")
                learningRate *= 0.1
                lastPlateauAverage = plateauAverage
                m = bestM.copy()
            }
            plateauLastAverages[(i / plateauCheckInterval) % plateauAverageCount] = average
        }*/
		
        val g = gradient(m, f, tolerance, clusterSize)
        for (j in 0 until m.numElements) {
            gradSquared[j] = (beta * prevGradSquared[j]) + ((1.0 - beta) * (g[j].pow(2.0)))
            m[j] += -(learningRate / sqrt(gradSquared[j] + epsilon)) * g[j]
        }

        val c = cost(m, f, tolerance, clusterSize)
        averages[i % averagesCount] = c
        average = averages.average()
        if (c < best.second) {
            val precise_c = cost(m, f, 1.0E-5, clusterSize = 50)
            if (precise_c < best.second) {
                best = Pair(i, precise_c)
                bestM = m.copy()
            }
        }
        averageData.xy(i.toDouble(), log(average, 10.0))
        costData.xy(i.toDouble(), c)

        print("\r${epochs - i} -> $average by lr: $learningRate")
        m = m.MGSON()
        global_i++
    }
    println("\nRMSProp -> Best: ${cost(bestM, f, 1.0E-5, clusterSize)}")
    return Pair(averageData, m)
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
    return Plot.data().xy(dataPoints.map { it.first }, dataPoints.map { it.second })
}

fun gss(cost: (Double) -> Double): Double {
    var a = 0.0
    var b = gss_b
    var c = b - (b - a) / gr
    var d = a + (b - a) / gr

    var fc = cost(c)
    var fd = cost(d)

    while (abs(c - d) > gss_b * gss_tolerance) {
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
*/



