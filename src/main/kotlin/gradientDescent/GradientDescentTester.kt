package gradientDescent

import utility.*
import java.awt.Color
import java.io.File
import kotlin.math.log

class GradientDescentTester {
    fun run() {
        val sirCount = 3
        var mi = SIRModelCreator().random(sirCount, 1)
        val iterations = 1
        val epochs = 10000
        val learningRate = 1.0E-8
        val learningRateForDynamic = learningRate
        val averageCount = 20

        repeat(1000) {
            if (CostCalculator(mi.function).cost(mi.lumpingMatrix) < 1.0E12)
                mi = SIRModelCreator().random(sirCount, 1)
        }

        val constant = Pair(SimpleGD(mi, learningRate), "Const")
        val dynamic = Pair(DynamicGD(mi, learningRateForDynamic), "Dyna")
        val extendedDynamic = Pair(ExtendedDynamicGD(mi, learningRateForDynamic), "ExtDyna")
        val rms = Pair(RMSPropGD(mi, learningRate), "RMS")
        val adam = Pair(BiasCorrectedADAMGD(mi, learningRate), "ADAM")
        val momentum = Pair(MomentumGD(mi, learningRate, epochs), "Momentum")
        val plotter = Plotter()
        plotter.plot.yAxis("Log_10(cost)", Plot.axisOpts().range(-20.0, 12.0))
        val mapleExporter = MapleExporter()
        val constantData = Pair(plotter.addSeries(constant.second, Color.BLUE), "Blue")
        val dynamicData = Pair(plotter.addSeries(dynamic.second, Color.RED), "Red")
        val extendedDynamicData = Pair(plotter.addSeries(extendedDynamic.second, Color.GRAY), "Gray")
        val rmsData = Pair(plotter.addSeries(rms.second, Color.GREEN), "Green")
        val adamData = Pair(plotter.addSeries(adam.second, Color.CYAN), "Cyan")
        val momentumData = Pair(plotter.addSeries(momentum.second, Color.BLACK), "Black")


        // out-commenting decides what methods to test
        val runThese = listOf(
                //Pair(constant, constantData),
                Pair(dynamic, dynamicData),
                //Pair(rms, rmsData),
                //Pair(adam, adamData),
                //Pair(momentum, momentumData),
        )

        runThese.forEach { mapleExporter.addSeries(it.first.second) }

        val plotTitle = plotTitle(runThese)
        plotter.plot.opts().title("$plotTitle | sir($sirCount) | lr: $learningRate")



        println("RUN: $plotTitle")
        for (j in 1..iterations) {
            for (method in runThese.withIndex()) {
                val mapleSeries = mapleExporter.series[method.value.first.second]!!
                val plotterSeries = method.value.second.first
                var best = Double.MAX_VALUE

                val averages = Array(averageCount) { method.value.first.first.cost }

                for (i in 0..epochs) {
                    method.value.first.first.iterate()
                    val c = method.value.first.first.cost
                    averages[i % averageCount] = c
                    val average = averages.average()

                    if (c < best)
                        best = c

                    mapleSeries.xy(i, average)
                    plotterSeries.xy(i, log(average, 10.0))
                    print("\r${method.value.first.second} -> ${((i.toDouble() / (epochs.toDouble())) * 100.0).toInt()}% : $c")
                }
                println()
                plotter.save()
            }
        }
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