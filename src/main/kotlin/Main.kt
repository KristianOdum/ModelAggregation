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

