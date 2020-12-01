package gradientDescent

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import utility.Plotter
import utility.SIRModelCreator
import utility.ToyModelCreator
import java.io.File
import java.util.*
import kotlin.math.log

class ProbabilityDistributionTester {
    fun run() {
        val iterations = 500
        val maxEpochs = 3000
        val minEpochs = 100
        val learningRate = 0.03
        val sirCount = 7

        var bestCostData = ""

        val startTime = System.currentTimeMillis()

        runBlocking {
            val mutex = Mutex()
            var t = 0
            var goodLumpings = 0

            val jobs = (0..iterations).map {
                GlobalScope.launch {
                    val mi = SIRModelCreator().random(sirCount, 1)
                    val method = GoldenSectionGD(mi, learningRate)
                    var best = Double.MAX_VALUE

                    val plotter = Plotter()
                    plotter.plot.yAxis("Log_10(cost)", Plot.axisOpts().range(-20.0, 12.0))
                    val series = plotter.addSeries("Method")

                    var lastBest = Double.MAX_VALUE
                    var plateauCounter = 0

                    for (j in 0..maxEpochs) {
                        method.iterate()
                        val c = method.cost

                        series.xy(j.toDouble(), log(c, 10.0))
                        if (c < best)
                            best = c

                        if(best == lastBest && j >= minEpochs) {
                            plateauCounter++
                            if (plateauCounter >= 100)
                                break
                            if(c < 1.0E-15 && plateauCounter >= 5)
                                break
                        }
                        if(best < lastBest) {
                            lastBest = best
                            plateauCounter = 0
                        }
                    }

                    mutex.lock()
                    if (best < 10E-8)
                        goodLumpings++

                    val timePerEpoch = (((System.currentTimeMillis() - startTime).toDouble()) / t.toDouble()).toLong()
                    val timeLeft = (iterations-t) * timePerEpoch
                    val secondsLeft = (timeLeft / 1000) % 60
                    val minsLeft = (timeLeft / 60000) % 60
                    val hoursLeft = timeLeft / 3600000

                    print("\r${((t++.toDouble() / iterations * 100.0).toInt())}% -> best: $best | Ratio: ${goodLumpings.toDouble() / t.toDouble()} | Time left: [$hoursLeft:$minsLeft:$secondsLeft]")
                    bestCostData += "$best "
                    mutex.unlock()
                    plotter.save("Line Search Sir7")
                }
            }
            jobs.joinAll()
        }
        val file = File("maple/data.txt")
        file.writeText(bestCostData)
    }
}