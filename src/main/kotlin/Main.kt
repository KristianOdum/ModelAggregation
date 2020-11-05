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
val global_sir = RandomSIR(3)
val global_m = randMatrix(global_sir.functionsCount -1, global_sir.functionsCount, 0.0, 1.0)

fun main() {

    val m = gradientDescent(global_sir.function, ::cost, global_sir.functionsCount, global_sir.functionsCount - 1, 1000)

    println("-----------------")
    println(cost(m, global_sir.function))
    println(cost(m.rowNorm(), global_sir.function))
}