import PSO.ParticleSwarmOptimization
import org.ejml.simple.SimpleMatrix
import java.awt.Color
import java.io.File
import kotlin.random.Random


val toyF = { x: SimpleMatrix ->
    val y = SimpleMatrix(x.numRows(), x.numCols())
        y[0,0] = x[0,0] * x[2,0] + x[2,0]
        y[1,0] = x[1,0] * x[2,0] + x[2,0]
        y[2,0] = -x[2,0]
        y
}

val randomP = Random(System.currentTimeMillis())
val sirCount = 3
val global_sir = RandomSIR(sirCount)
var global_m = randMatrix(global_sir.functionsCount-1, global_sir.functionsCount, 0.0, 1.0)
const val epochs = 100000

fun main() {
    /*for (i in 0..10) {

        val rms = gradientDescentRMSProp(global_sir.function, epochs)
        val adam = gradientDescentADAM(global_sir.function, epochs)
        Plot.plot(Plot.plotOpts().title("RMS (Blue) and ADAM (red) | sir count = $sirCount | epocs = $epochs"))
                .series("RMS", rms, Plot.seriesOpts().color(Color.BLUE))
                .series("ADAM1", adam, Plot.seriesOpts().color(Color.RED)).saveWithExt()
    }*/
    //Random(System.currentTimeMillis())
    val sir = RandomSIR(3)

    val pso = ParticleSwarmOptimization(sir.function, sir.functionsCount, sir.functionsCount-1, 20)

    File("datax.txt").delete()

    for(i in 0..49){
        println("$i")
        pso.run(3000)
    }
}