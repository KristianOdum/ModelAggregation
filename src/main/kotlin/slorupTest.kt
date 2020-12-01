import PSO.GeneralPSO
import PSO.PSOInfo

fun main() {
    val cfot = CostFunctionOptimizerTester(3000, 1, 300) {
        val mi = T9ModelCreator().createModel(1)
        GeneralPSO(mi,1,20,PSOInfo(3000))
    }

    cfot.run()
}