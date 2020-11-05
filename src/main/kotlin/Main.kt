import PSO.ParticleSwarmOptimization
import org.ejml.simple.SimpleMatrix
import java.awt.Color
import java.io.File
import kotlin.random.Random


val toyF = { x: SimpleMatrix ->
    val y = SimpleMatrix(x.numRows(), x.numCols())
        y[0,0] = x[0,0] * x[2,0] + x[2,0]
        y[1,0] = x[1,0] * x[2,0] + x[2,0]
        y[2,0] = -x[2,0]
        y
}

val randomP = Random(System.currentTimeMillis())
val global_sir = RandomSIR(1)
var global_m = randMatrix(global_sir.functionsCount - 2, global_sir.functionsCount, 0.9, 1.0)

fun main() {

    val t = global_m.rowVector(0)
    global_m = global_m.setRow(0, global_m.rowVector(1))
    global_m = global_m.setRow(1, t).MGSON()


    println(cost(global_m, global_sir.function))
}