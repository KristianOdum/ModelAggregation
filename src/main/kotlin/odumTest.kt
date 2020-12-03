import PSO.GeneralPSO
import PSO.PSOInfo
import gradientDescent.DerivativeCalculator
import gradientDescent.GoldenSectionGD
import gradientDescent.MMomentumGD
import utility.*

fun main() {
    val model = T12ModelCreator().createModel(1)
    val iterations = 100
    val tolerance = 0.05
    val derTolerance = 0.8
    val i = 12
    val dims = model.lumpingMatrix.numCols()


    val costCalculator = SigmoidoidTransformer.CreateCostFromPrediction(model, MonteCarloMeanCalculator(dims, tolerance))
    val derCalculator = SigmoidoidTransformer.CreateDerivativeFromPrediction(model, MonteCarloMeanCalculator(dims, derTolerance))


    val pso = CostFunctionOptimizerTester(3000, iterations, 300, "PSO$i.txt") {
        GeneralPSO(model, 1, 20, PSOInfo(3000), costCalculator)
    }

    val line_search = CostFunctionOptimizerTester(1000, iterations, 10, "line_search$i.txt") {
        GoldenSectionGD(model, derCalculator, costCalculator)
    }

    val mmomentum = CostFunctionOptimizerTester(2000, iterations, 50, "mmomentum$i.txt") {
        MMomentumGD(model, 0.003, 2000, derCalculator, costCalculator)
    }

    println("Starting PSO")
    pso.run()

    println("Starting Line Search")
    line_search.run()

    println("Starting MMomentum")
    mmomentum.run()
}