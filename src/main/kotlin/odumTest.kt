import PSO.GeneralPSO
import PSO.PSOInfo
import gradientDescent.DerivativeCalculator
import gradientDescent.GoldenSectionGD
import gradientDescent.MMomentumGD
import utility.*

fun main() {
    val model = T7ModelCreator().createModel(1)
    val iterations = 5
    val tolerance = 0.05
    val derTolerance = 0.5
    val i = 0

    val pso = CostFunctionOptimizerTester(3000, iterations, 300, "PSO$i.txt") {
        val costCalculator = CostCalculator(MonteCarloMeanCalculator(model.lumpingMatrix.numCols(), tolerance), model.function)
        GeneralPSO(model, 1, 20, PSOInfo(3000), costCalculator)
    }

    val line_search = CostFunctionOptimizerTester(1000, iterations, 10, "line_search$i.txt") {
        val derCalculator = DerivativeCalculator(MonteCarloMeanCalculator(model.lumpingMatrix.numCols(), derTolerance), model.function)
        val costCalculator = CostCalculator(MonteCarloMeanCalculator(model.lumpingMatrix.numCols(), tolerance), model.function)
        GoldenSectionGD(model, derCalculator, costCalculator)
    }

    val mmomentum = CostFunctionOptimizerTester(2000, iterations, 50, "mmomentum$i.txt") {
        val derCalculator = DerivativeCalculator(MonteCarloMeanCalculator(model.lumpingMatrix.numCols(), derTolerance), model.function)
        val costCalculator = CostCalculator(MonteCarloMeanCalculator(model.lumpingMatrix.numCols(), tolerance), model.function)
        MMomentumGD(model, 0.003, 2000, derCalculator, costCalculator)
    }

    println("Starting PSO")
    pso.run()

    println("Starting Line Search")
    line_search.run()

    println("Starting MMomentum")
    mmomentum.run()
}