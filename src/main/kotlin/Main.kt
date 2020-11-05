import PSO.GeneralPSO
import PSO.PSOInfo
import org.ejml.simple.SimpleMatrix
import utility.ModelInfo
import utility.Plotter
import utility.SIRModelCreator

fun main() {
    val pso = GeneralPSO(SIRModelCreator().random(3,1), 1, 20, PSOInfo(3000))
    repeat(3000){
        pso.iterate()
        println("$it: ${pso.swarms[0].globalBestCost}")
    }
    println("We done, best cost: ${pso.swarms[0].globalBestCost}")
}