package gradientDescent

import utility.*
import java.awt.Color
import kotlin.math.log

class GradientDescentTester {
    fun run() {
        val sirCount = 3
        val mi = ToyModelCreator().random(sirCount, 1)
        val epochs = 1000
        val learningRate = 0.000000000000001

        val constant = Pair(SimpleGD(mi, learningRate), "Constant")
        val dynamic = Pair(DynamicGD(mi, learningRate), "Dynamic")
        val extendedDynamic = Pair(ExtendedDynamicGD(mi, learningRate), "Extended Dynamic")
        val rms = Pair(RMSPropGD(mi, learningRate), "RMSProp")
        val adam = Pair(ADAMGD(mi, learningRate), "ADAM")
        val momentum = Pair(MomentumGD(mi, learningRate, epochs), "Momentum")

        // out-commenting decides what methods to test
        val runThese = listOf(
                //constant,
                //dynamic,
                extendedDynamic,
                //rms,
                //adam,
                //momentum
        )

        val plotter = Plotter()
        plotter.plot.yAxis("Log_10(cost)", Plot.axisOpts().range(-20.0, 12.0))
        val constantData = plotter.addSeries(constant.second, Color.BLUE)
        val dynamicData = plotter.addSeries(dynamic.second, Color.RED)
        val extendedDynamicData = plotter.addSeries(extendedDynamic.second, Color.BLUE)
        val rmsData = plotter.addSeries(rms.second, Color.GREEN)
        val adamData = plotter.addSeries(adam.second, Color.MAGENTA)
        val momentumData = plotter.addSeries(momentum.second, Color.BLACK)
        val dataSeries = arrayOf(
                //constantData,
                dynamicData,
                extendedDynamicData,
                //rmsData,
                //adamData,
                //momentumData
        )

        for (method in runThese.withIndex())
            for (i in 0..epochs) {
                method.value.first.iterate()
                val c = method.value.first.cost

                dataSeries[method.index].xy(i, log(c, 10.0))
                print("\r${method.value.second} -> $i : $c")
                if (c < 1.0E-15)
                    break
            }
        plotter.plot.opts().title("Normal (RED) and extended (BLUE) | sir($sirCount)")
        plotter.save()

        println("\n" + extendedDynamic.first.lumpingMatrix)
        val ccalc = CostCalculator(mi.function)
        ccalc.tolerance = 1.0E-4
        ccalc.clusterSize = 100
        println(ccalc.cost(extendedDynamic.first.lumpingMatrix))
    }
}