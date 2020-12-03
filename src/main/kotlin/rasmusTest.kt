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
import java.lang.Integer.max
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.*
import kotlin.random.Random
import kotlin.system.measureTimeMillis


fun main() {
    val modelInfo = T6ModelCreator().createModel(1)

    val costCalculator = CostCalculator(VegasMeanCalculator(modelInfo.lumpingMatrix.numCols(), 0.05), modelInfo.function)

    repeat(25) {
        var cost: Double
        val time = measureTimeMillis {
            cost = costCalculator.cost(modelInfo.lumpingMatrix)
        }
        println("$time $cost")
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