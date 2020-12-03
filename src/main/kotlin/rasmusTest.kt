import PSO.GeneralPSO
import PSO.PSOInfo
import gradientDescent.DerivativeCalculator
import gradientDescent.GoldenSectionGD
import gradientDescent.GradientDescent
import gradientDescent.MomentumGD
import org.ejml.simple.SimpleMatrix
import utility.*
import utility.vegas.IncrementPartition
import java.lang.Integer.max
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.*
import kotlin.random.Random


fun main() {
    val cfo = CostFunctionOptimizerTester(300, 10, 10, "TCMQ") {
        val mi = TCMQModelCreator().createModel(1)
        val costCalculator = CostCalculator(MonteCarloMeanCalculator(3, 0.05), mi.function)
        val derivativeCalculator = DerivativeCalculator(MonteCarloMeanCalculator(3, 0.5), mi.function)

        GoldenSectionGD(mi, derivativeCalculator, costCalculator)
    }

    cfo.run()
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