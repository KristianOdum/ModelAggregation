/*

import org.ejml.simple.SimpleMatrix
import java.io.File
import java.lang.Integer.max
import kotlin.math.*

const val MAX_X = 1000.0
const val MIN_X = 0.0





fun averageNoise(m: SimpleMatrix, f: (SimpleMatrix) -> SimpleMatrix, epochs: Int, tolerance: Double) {
    val start = Array(epochs) { 0.0 }
    val average: Double
    var averageNoiseSum = 0.0
    val averageNoise: Double

    for (i in 0 until epochs) {
        start[i] = cost(m, f, tolerance, 50)
        print("\r${((i.toDouble()/epochs.toDouble())*100).toInt()}%")
    }

    average = start.average()
    println("Start cost: $average")

    for (i in start)
        averageNoiseSum += (average - i).absoluteValue

    averageNoise = averageNoiseSum / epochs

    println("\nCost is ${(averageNoise / start.average()) * 100}% off on average.")
}*/