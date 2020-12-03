import PSO.GeneralPSO
import PSO.PSOInfo
import utility.CostCalculator
import utility.MonteCarloMeanCalculator
import utility.SIRModelCreator

fun main() {
    val cfot = CostFunctionOptimizerTester(3000, 1, 300) {
        val mi = SIRModelCreator().random(1,2)
        val costCalculator = CostCalculator(MonteCarloMeanCalculator(mi.lumpingMatrix.numCols(),0.05), mi.function)
        GeneralPSO(mi, 1, 20, PSOInfo(3000), costCalculator)
    }

    cfot.run()
}