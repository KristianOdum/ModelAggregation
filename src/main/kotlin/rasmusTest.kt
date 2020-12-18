import PSO.GeneralPSO
import PSO.PSOInfo
import gradientDescent.DerivativeCalculator
import gradientDescent.GoldenSectionGD
import gradientDescent.GradientDescent
import gradientDescent.MomentumGD
import org.ejml.simple.SimpleMatrix
import utility.*
import utility.vegas.IncrementPartition
import utility.vegas.VegasMeanCalculator
import java.io.File
import java.lang.Integer.max
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.*
import kotlin.random.Random
import kotlin.system.measureTimeMillis

val yeef = { y: SimpleMatrix ->
    val dy = SimpleMatrix(4, 1)

    dy[0] = y[1].pow(2.0) + y[1] / (y[0] + 1000)
    dy[1] = y[0].pow(y[1] / 1000.0) + y[3] * y[2]
    dy[2] = y[2]*y[3]*y[0]/y[1]
    dy[3] = y[0]*log(y[3], 2.0)+y[1]

    dy
}

fun main() {
    val modelInfo = T101ModelCreator().createModel(8)

    val costCalculator = CostCalculator(MonteCarloMeanCalculator( modelInfo.lumpingMatrix.numCols(), 0.05), modelInfo.function)

    val pso = GeneralPSO(modelInfo, 1, 200, PSOInfo(10000), costCalculator)

    val file = File("Big.txt")

    val startTime = System.currentTimeMillis()

    while (true) {
        pso.iterate()

        file.appendText("${System.currentTimeMillis() - startTime} ${pso.bestCost}\n")
    }
}

fun Double.strWidth(width: Int) = this.toString().take(max(3, width - 3)) + this.toString().takeLast(3)

class Average {
    var i = 0L
    var value = 0.0
    fun add(value: Double) {
        this.value = tempAdd(value)
        i++
    }

    fun tempAdd(value: Double) = this.value * (i.toDouble()/(i+1)) + value * (1.0/(i+1))
}

const val incrementCount = 50
const val subdivisionCount = 1000
const val convergenceRate = 0.8

private fun specificCost(m: SimpleMatrix, mbarm: SimpleMatrix, x: SimpleMatrix, f: (SimpleMatrix) -> SimpleMatrix) =
        m.mult(f(x).minus(f(mbarm.mult(x)))).normF()