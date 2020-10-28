import PSO.ParticleSwarmOptimization
import org.ejml.simple.SimpleMatrix
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

fun main() {
    val sir = RandomSIR(3)

    val m = gradientDescent(sir.function, ::cost, sir.functionsCount, sir.functionsCount - 1, 100)

    println(m)
}