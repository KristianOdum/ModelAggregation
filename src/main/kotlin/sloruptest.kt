import gradientDescent.DerivativeCalculator
import gradientDescent.MMomentumGD
import org.ejml.simple.SimpleMatrix
import utility.*
import java.io.File

fun main(){
    val mc = T9ModelCreator()
    val model = mc.createModel(1)
    val tolerance = 0.05
    val derTolerance = 0.5
    val costCalculator = CostCalculator(MonteCarloMeanCalculator(model.lumpingMatrix.numCols(), tolerance), model.function)
    val derCalculator = DerivativeCalculator(MonteCarloMeanCalculator(model.lumpingMatrix.numCols(), derTolerance), model.function)

    val modelinfo = ModelInfo(randMatrix(model.lumpingMatrix.numRows(), model.lumpingMatrix.numCols(), -1.0 until 1.0).setRow(0, SimpleMatrix(1,model.lumpingMatrix.numCols()).withValues(1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)), model.function)
    val mm = MMomentumGD(modelinfo, 0.003, 5000, derCalculator, costCalculator)

    repeat(150){
        mm.iterate()
    }
    println(mm.bestCost)

    println(mm.lumpingMatrix)

    val f = File("ree2.txt")
    for (i in 0 until mm.lumpingMatrix.numRows()){
        for (j in 0 until mm.lumpingMatrix.numCols()){
            f.appendText("${mm.lumpingMatrix[i,j]} ")
        }
        f.appendText("\n")
    }
}