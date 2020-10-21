import PSO.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import org.ejml.simple.SimpleMatrix
import kotlin.random.Random


val toyF = { x: SimpleMatrix ->
    val y = SimpleMatrix(x.numRows(), x.numCols())
    y[0,0] = x[0,0] * x[2,0] + x[2,0]
    y[1,0] = x[1,0] * x[2,0] + x[2,0]
    y[2,0] = -x[2,0]
    y
}

val randomP = Random(0)

fun main() {
    //Random(System.currentTimeMillis())
    val sir = RandomSIR(8)

    val pso = ParticleSwarmOptimization(sir.function, sir.functionsCount, sir.functionsCount-1, 10)
    var x = randMatrix(23,24,-1.0,1.0)
    for(i in 0..100)
        println(cost(x,sir.function))

    //println(cost(pso.run(20), sir.function))
}