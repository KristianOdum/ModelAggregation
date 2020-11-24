package gradientDescent

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import utility.Plotter
import utility.SIRModelCreator
import java.io.File
import kotlin.math.log

class ProbabilityDistributionTester {
    fun run() {
        val iterations = 500
        val epochs = 3000
        val minEpochs = 500
        val learningRate = 3.0E-8
        val sirCount = 3

        var bestCostData = ""

        runBlocking {
            val mutex = Mutex()
            var t = 0

            val jobs = (0..iterations).map {
                GlobalScope.launch {
                    val mi = SIRModelCreator().random(sirCount, 1)
                    val method = DynamicGD(mi, learningRate)
                    var best = Double.MAX_VALUE

                    val plotter = Plotter()
                    plotter.plot.yAxis("Log_10(cost)", Plot.axisOpts().range(-20.0, 12.0))
                    val series = plotter.addSeries("Method")

                    var lastBest = Double.MAX_VALUE
                    var plateauCounter = 0

                    for (j in 0..epochs) {
                        method.iterate()
                        val c = method.cost

                        series.xy(j.toDouble(), log(c, 10.0))
                        if (c < best)
                            best = c

                        if(best == lastBest && j >= minEpochs) {
                            plateauCounter++
                            if (plateauCounter >= 250)
                                break
                        }
                        if(best < lastBest) {
                            lastBest = best
                            plateauCounter = 0
                        }
                    }

                    mutex.lock()
                    print("\r${((t++.toDouble() / (iterations) * 100.0).toInt())}% -> best: $best")
                    if(best > 1.0E7)
                        println("\n$best")
                    bestCostData += "$best "
                    mutex.unlock()
                    plotter.save("Constant")
                }
            }
            jobs.joinAll()
        }
        val file = File("maple/data.txt")
        file.writeText(bestCostData)
    }
}