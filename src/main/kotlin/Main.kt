import org.ejml.simple.SimpleMatrix
import java.awt.Color
import kotlin.math.absoluteValue
import kotlin.math.log
import kotlin.random.Random
import kotlin.time.toDuration


val toyF = { x: SimpleMatrix ->
    val y = SimpleMatrix(x.numRows(), x.numCols())
        y[0,0] = x[0,0] * x[2,0] + x[2,0]
        y[1,0] = x[1,0] * x[2,0] + x[2,0]
        y[2,0] = -x[2,0]
        y
}

val randomP = Random(System.currentTimeMillis())
val sirCount = 3
val global_sir = RandomSIR(sirCount)
val sirF = global_sir.function
var sirM = randMatrix(global_sir.functionsCount-1, global_sir.functionsCount, 0.4, 0.6)
var toyM = randMatrix(2,3,0.0,1.0)
const val epochs = 1000

var global_i = 0

fun main() { }

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
}