package gradientDescent

import utility.*
import java.awt.Color
import kotlin.math.log

class GradientDescentTester {
    fun run() {
        val sirCount = 3
        val mi = SIRModelCreator().random(sirCount, 1)
        val epochs = 10
        val learningRate = 0.003
        val learningRateForDynamic = learningRate

        val constant = Pair(SimpleGD(mi, learningRate), "Const")
        val dynamic = Pair(DynamicGD(mi, learningRateForDynamic), "Dyna")
        val extendedDynamic = Pair(ExtendedDynamicGD(mi, learningRateForDynamic), "ExtDyna")
        val rms = Pair(RMSPropGD(mi, learningRate), "RMS")
        val adam = Pair(ADAMGD(mi, learningRate), "ADAM")
        val bAdam = Pair(BiasCorrectedADAMGD(mi, learningRate), "BADAM")
        val momentum = Pair(MomentumGD(mi, learningRate, epochs), "Momentum")
        val plotter = Plotter()
        plotter.plot.yAxis("Log_10(cost)", Plot.axisOpts().range(-20.0, 12.0))
        val mapleExporter = MapleExporter()
        val constantData = Pair(plotter.addSeries(constant.second, Color.BLUE), "Blue")
        val dynamicData = Pair(plotter.addSeries(dynamic.second, Color.RED), "Red")
        val extendedDynamicData = Pair(plotter.addSeries(extendedDynamic.second, Color.CYAN), "Cyan")
        val rmsData = Pair(plotter.addSeries(rms.second, Color.GREEN), "Green")
        val adamData = Pair(plotter.addSeries(adam.second, Color.MAGENTA), "Magenta")
        val badamData = Pair(plotter.addSeries(bAdam.second, Color.LIGHT_GRAY), "Gray")
        val momentumData = Pair(plotter.addSeries(momentum.second, Color.BLACK), "Black")


        // out-commenting decides what methods to test
        val runThese = listOf(
                //Pair(constant, constantData),
                //Pair(dynamic, dynamicData),
                //Pair(rms, rmsData),
                //Pair(bAdam, badamData),
                Pair(momentum, momentumData),
        )

        runThese.forEach { mapleExporter.addSeries(it.first.second) }

        val plotTitle = plotTitle(runThese)
        plotter.plot.opts().title("$plotTitle | sir($sirCount) | lr: $learningRate")

        var goodLumpings = 0
        println("RUN: $plotTitle")
        for ((t, method) in runThese.withIndex().withIndex()) {
            val mapleSeries = mapleExporter.series[method.value.first.second]!!
            val plotterSeries = method.value.second.first

            for (i in 0..epochs) {
                method.value.first.first.iterate()
                val c = method.value.first.first.cost

                mapleSeries.xy(i, c)
                plotterSeries.xy(i, log(c, 10.0))
                print("\r${method.value.first.second} -> ${((i.toDouble() / (epochs.toDouble())) * 100.0).toInt()}% : $c")
                if (c < 1.0E-15) {
                    goodLumpings++
                    break
                }
                if ((i + 1) % 5000 == 0) {
                    plotter.save()
                }
            }
            println("\n${((goodLumpings.toDouble() / t.toDouble()) * 100.0).toInt()}%")
            println(method.value.second.first.x(1))
            plotter.save()
        }
        plotter.save()
        mapleExporter.export()
    }
}

fun plotTitle(runThese: List<Pair<Pair<GradientDescent, String>, Pair<Plot.Data, String>>>) : String {
    var res = ""

    for (i in runThese) {
        val methodAndColor = "${i.first.second}(${i.second.second})"

        res += when {
            i == runThese.first() -> {
                methodAndColor
            }
            i == runThese.last() -> {
                " and $methodAndColor"
            }
            i != runThese.first() -> {
                ", $methodAndColor"
            }
            else -> ""
        }

    }

    return res
}