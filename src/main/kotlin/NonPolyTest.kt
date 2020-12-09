import PSO.GeneralPSO
import PSO.PSOInfo
import gradientDescent.DerivativeCalculator
import gradientDescent.GoldenSectionGD
import gradientDescent.MMomentumGD
import utility.*

fun main() {
    val mc = T13ModelCreator()
    val model = mc.createModel(1)
    val iterations = 100
    val tolerance = 0.05
    val derTolerance = 0.5

    val i = mc::class.java.name.takeWhile { it != 'M' }

    val costCalculator = SigmoidoidTransformer.CreateCostFromPrediction(model, MonteCarloMeanCalculator(model.lumpingMatrix.numCols(), tolerance))
    val derCalculator = SigmoidoidTransformer.CreateDerivativeFromPrediction(model, MonteCarloMeanCalculator(model.lumpingMatrix.numCols(), derTolerance))

    val pso = CostFunctionOptimizerTester(3000, iterations, 300, "PSO_$i.txt") {
        val modelinfo = ModelInfo(randMatrix(model.lumpingMatrix.numRows(), model.lumpingMatrix.numCols(), -1.0 until 1.0), model.function)
        GeneralPSO(modelinfo, 1, 20, PSOInfo(3000), costCalculator)
    }

    val line_search = CostFunctionOptimizerTester(1000, iterations, 10, "line_search_$i.txt") {
        val modelinfo = ModelInfo(randMatrix(model.lumpingMatrix.numRows(), model.lumpingMatrix.numCols(), -1.0 until 1.0), model.function)
        GoldenSectionGD(modelinfo, derCalculator, costCalculator)
    }

    val mmomentum = CostFunctionOptimizerTester(5000, iterations, 50, "mmomentum_$i.txt") {
        val modelinfo = ModelInfo(randMatrix(model.lumpingMatrix.numRows(), model.lumpingMatrix.numCols(), -1.0 until 1.0), model.function)
        MMomentumGD(modelinfo, 0.003, 5000, derCalculator, costCalculator)
    }

    println("Starting PSO")
    pso.run()

    println("\nStarting Line Search")
    line_search.run()

    println("\nStarting MMomentum")
    mmomentum.run()
}