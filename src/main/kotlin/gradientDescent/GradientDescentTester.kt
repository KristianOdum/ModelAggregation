/*
package gradientDescent

import utility.*
import java.awt.Color
import kotlin.math.log

class GradientDescentTester {
    fun run() {
        val sirCount = 3
        var mi = SIRModelCreator().random(sirCount, 1)
        val iterations = 1
        val epochs = 500
        val learningRate = 0.003
        val learningRateForDynamic = learningRate
        val averageCount = 10

        val constant = Pair(SimpleGD(mi, learningRate), "Const")
        val dynamic = Pair(DynamicGD(mi, learningRateForDynamic), "Dyna")
        val rms = Pair(RMSPropGD(mi, learningRate), "RMS")
        val mrms = Pair(MRMSPropGD(mi, learningRate), "mRMS")
        val adam = Pair(BiasCorrectedADAMGD(mi, learningRate), "adam")
        val madam = Pair(MAdamGD(mi, learningRate), "madam")
        val lineSearch = Pair(GoldenSectionGD(mi), "Line Search")
        val momentum = Pair(MomentumGD(mi, learningRate, epochs), "Momentum")
        val mmomentum = Pair(MMomentumGD(mi, learningRate, epochs), "mMomentum")
        val plotter = Plotter()
        plotter.plot.yAxis("Log_10(cost)", Plot.axisOpts().range(-20.0, 12.0))
        val mapleExporter = MapleExporter()
        val constantData = Pair(plotter.addSeries(constant.second, Color.BLUE), "Blue")
        val dynamicData = Pair(plotter.addSeries(dynamic.second, Color.RED), "Red")
        val extendedDynamicData = Pair(plotter.addSeries(extendedDynamic.second, Color.GRAY), "Gray")
        val rmsData = Pair(plotter.addSeries(rms.second, Color.GREEN), "Green")
        val mrmsData = Pair(plotter.addSeries(mrms.second, Color.ORANGE), "Orange")
        val adamData = Pair(plotter.addSeries(adam.second, Color.CYAN), "Cyan")
        val madamData = Pair(plotter.addSeries(madam.second, Color.CYAN), "Cyan")
        val momentumData = Pair(plotter.addSeries(momentum.second, Color.BLACK), "Black")
        val mmomentumData = Pair(plotter.addSeries(mmomentum.second, Color.BLACK), "Black")
        val lineSearchData = Pair(plotter.addSeries(lineSearch.second, Color.MAGENTA), "Magenta")

        // out-commenting decides what methods to test
        val runThese = listOf(
                //Pair(constant, constantData),
                //Pair(lineSearch, lineSearchData),
                //Pair(dynamic, dynamicData),
                //Pair(extendedDynamic, extendedDynamicData),
                //Pair(rms, rmsData),
                //Pair(mrms, mrmsData),
                //Pair(adam, adamData),
                //Pair(madam, madamData),
                //Pair(momentum, momentumData),
                Pair(mmomentum, mmomentumData),

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
                var plateauCounter = 0
                var lastBest = Double.MAX_VALUE

                val averages = Array(averageCount) { method.value.first.first.cost }

                val startTime = System.currentTimeMillis()

                for (i in 0..epochs) {
                    method.value.first.first.iterate()
                    val c = method.value.first.first.cost
                    averages[i % averageCount] = c
                    val average = averages.average()

                    if (c < best)
                        best = c

                    if(best >= 0.95 * lastBest) {
                        plateauCounter++
                        if (plateauCounter >= 50)
                            break
                    }
                    if(best < lastBest) {
                        lastBest = best
                        plateauCounter = 0
                    }

                    val elapsedSeconds = (System.currentTimeMillis() - startTime).toDouble() / 1000.0

                    mapleSeries.xy(i, average)
                    plotterSeries.xy(elapsedSeconds, log(average, 10.0))
                    print("\r${method.value.first.second} -> ${((i.toDouble() / (epochs.toDouble())) * 100.0).toInt()}% : $c | pc = $plateauCounter")

                    if (c < 1.0E-8)
                        break
                }
                println()
            }
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
*/
