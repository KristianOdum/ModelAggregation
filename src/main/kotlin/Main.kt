import gradientDescent.*
import utility.*
import java.awt.Color
import kotlin.math.log

fun main() {
    val mi = SIRModelCreator().random(3,1)
    val epochs = 500
    val learningRate = 0.3

    val constant = SimpleGD(mi, learningRate)
    val dynamic = DynamicGD(mi, learningRate)
    val rms = RMSPropGD(mi, learningRate)
    val adam = ADAMGD(mi, learningRate)
    val momentum = MomentumGD(mi, learningRate, epochs)
    val runThese = listOf(
            //constant,
            dynamic,
            //rms,
            //adam,
            //momentum
    )

    val plotter = Plotter()
    plotter.plot.yAxis("Log_10(cost)", Plot.axisOpts().range(-22.0, 12.0))
    val constantData = plotter.addSeries("Constant", Color.BLUE)
    val dynamicData = plotter.addSeries("Dynamic", Color.RED)
    val rmsData = plotter.addSeries("RMSProp", Color.GREEN)
    val adamData = plotter.addSeries("ADAM", Color.MAGENTA)
    val momentumData = plotter.addSeries("Momentum", Color.BLACK)
    val dataSeries = arrayOf(constantData, dynamicData, rmsData, adamData, momentumData)

    for (method in runThese.withIndex())
        for (i in 0..epochs) {
            method.value.iterate()
            val c = method.value.cost

            dataSeries[method.index].xy(i, log(c, 10.0))

            print("\r${method.value.name} -> $i : $c")
        }

    plotter.save()
}