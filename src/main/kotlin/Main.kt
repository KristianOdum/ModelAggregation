import PSO.ParticleSwarmOptimization
import org.ejml.simple.SimpleMatrix
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
    //Random(System.currentTimeMillis())
    val sir = RandomSIR(3)

    val pso = ParticleSwarmOptimization(sir.function, sir.functionsCount, sir.functionsCount-1, 20)

    for(i in 0..50){
        print("[")
        pso.run(2500)
        println("],")
    }
    //println(cost(pso.run(1000), sir.function))
}