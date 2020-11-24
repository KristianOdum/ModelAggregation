import PSO.GeneralPSO
import PSO.PSOInfo

fun main() {
    val cfot = CostFunctionOptimizerTester(3000, 4) {
        val mi = T12ModelCreator().createModel(1)
        GeneralPSO(mi,1,20,PSOInfo(3000))
    }

    cfot.run()
}

