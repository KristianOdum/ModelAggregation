import PSO.GeneralPSO
import PSO.PSOInfo
import gradientDescent.DerivativeCalculator
import gradientDescent.GoldenSectionGD
import gradientDescent.MMomentumGD
import utility.*

fun main() {
    val model = T9ModelCreator().createModel(1)
    val iterations = 100
    val tolerance = 0.05
    val derTolerance = 1.0
    val i = 9

    val costCalculator = CostCalculator(MonteCarloMeanCalculator(model.lumpingMatrix.numCols(), tolerance), model.function)
    val derCalculator = DerivativeCalculator(MonteCarloMeanCalculator(model.lumpingMatrix.numCols(), derTolerance), model.function)

    val pso = CostFunctionOptimizerTester(3000, iterations, 300, "PSO$i.txt") {
        val modelinfo = ModelInfo(randMatrix(model.lumpingMatrix.numRows(), model.lumpingMatrix.numCols(), -1.0 until 1.0), model.function)
        GeneralPSO(modelinfo, 1, 20, PSOInfo(3000), costCalculator)
    }

    val line_search = CostFunctionOptimizerTester(1000, iterations, 10, "line_search$i.txt") {
        val modelinfo = ModelInfo(randMatrix(model.lumpingMatrix.numRows(), model.lumpingMatrix.numCols(), -1.0 until 1.0), model.function)
        GoldenSectionGD(modelinfo, derCalculator, costCalculator)
    }

    val mmomentum = CostFunctionOptimizerTester(5000, iterations, 50, "mmomentum$i.txt") {
        val modelinfo = ModelInfo(randMatrix(model.lumpingMatrix.numRows(), model.lumpingMatrix.numCols(), -1.0 until 1.0), model.function)
        MMomentumGD(modelinfo, 0.003, 5000, derCalculator, costCalculator)
    }

    println("Starting PSO")
    pso.run()

    println("Starting Line Search")
    line_search.run()

    println("Starting MMomentum")
    mmomentum.run()
}