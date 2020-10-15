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

fun main() {
    val sir = RandomSIR(4)

    Random(System.currentTimeMillis())

    val m = gradientDescent(toyF, ::cost, 3, 2, 3)

    println(cost(m, sir.function))
}