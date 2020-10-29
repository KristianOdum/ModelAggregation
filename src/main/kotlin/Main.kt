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
val global_sir = RandomSIR(3)
val global_m = randMatrix(global_sir.functionsCount -1, global_sir.functionsCount, 0.0, 1.0)

fun main() {

    var data = ""

    for (i in 0 until 5) {
        val sir = RandomSIR(3)
        data += cost(gradientDescent(sir.function, ::cost, sir.functionsCount, sir.functionsCount - 1, 50), sir.function).toString() + " "
        print("\r$i")
    }

    println(data)
}