import PSO.EPSO
import PSO.GeneralPSO
import PSO.GradientDescentPSO
import PSO.PSOInfo
import gradientDescent.DynamicGD
import utility.CostCalculator
import utility.SIRModelCreator

fun main(){
    val mi = T11ModelCreator().createModel(1)
    val cc = CostCalculator(mi.function)
    val pso = GeneralPSO(mi, 1, 10, PSOInfo(3000))

    repeat(3000){
        pso.iterate()
    }
    println(pso.swarms.first().globalBestCost)
}

