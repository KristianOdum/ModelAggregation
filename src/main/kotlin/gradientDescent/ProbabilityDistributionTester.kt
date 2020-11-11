package gradientDescent

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import utility.CostCalculator
import utility.Plotter
import utility.SIRModelCreator
import java.io.File
import kotlin.math.log

class ProbabilityDistributionTester {
    fun run() {
        val iterations = 100
        val epochs = 3000
        val learningRate = 1.0E-8
        val sirCount = 3
        val averageCount = 20

        var bestCostData = ""

        runBlocking {
            val mutex = Mutex()
            var t = 0

            val jobs = (0..iterations).map {
                GlobalScope.launch {

                    val mi = SIRModelCreator().random(sirCount, 1)
                    val method = SimpleGD(mi, learningRate)
                    var best = Double.MAX_VALUE

                    val plotter = Plotter()
                    plotter.plot.yAxis("Log_10(cost)", Plot.axisOpts().range(-20.0, 12.0))
                    val series = plotter.addSeries("Dynamic")

                    val averages = Array(averageCount) { method.cost }

                    for (j in 0..epochs) {
                        method.iterate()
                        val c = method.cost
                        averages[j % averageCount] = c
                        val average = averages.average()

                        series.xy(j.toDouble(), log(average, 10.0))
                        if (c < best)
                            best = c
                    }

                    mutex.lock()
                    print("\r${((t++.toDouble() / (iterations) * 100.0).toInt())}% -> best: $best")
                    bestCostData += "$best "
                    mutex.unlock()
                    plotter.save("SimpleGD")
                }
            }
            jobs.joinAll()
        }
        val file = File("maple/data.txt")
        file.writeText(bestCostData)
    }
}