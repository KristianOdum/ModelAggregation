import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import utility.CostCalculator
import java.io.File
import java.util.*
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime

class CostFunctionOptimizerTester(private val maxEpochs: Int, private val iterations: Int, private val maxPlateau : Int, private val filePath: String = "CostFunctionOptimizerTesterOutput.txt",val factory: () -> CostFunctionOptimizer)  {
    fun run() {
        val data = File(filePath)
        val dataMutex = Mutex()
        var dataBuffer = ""
        data.createNewFile()
        var completed = 0

        runBlocking {
            val jobs = (0 until iterations).map { threadID -> GlobalScope.launch {
                var plateauCounter = 0
                var lastBestCost = Double.MAX_VALUE
                val cfo = factory()
                var i = 0
                val startTime = System.currentTimeMillis()
                var bestTime = startTime
                var bestIteration = 0
                val startCost = cfo.bestCost

                while (plateauCounter < maxPlateau && lastBestCost >= 1.0E-8 && i < maxEpochs) {
                    cfo.iterate()

                    if (cfo.bestCost >= lastBestCost)
                        plateauCounter++
                    else {
                        plateauCounter = 0
                        lastBestCost = cfo.bestCost
                        bestTime = System.currentTimeMillis() - startTime
                        bestIteration = i
                    }
                    i++
                    //print("\r${(i.toDouble() /  maxEpochs)}\t${cfo.bestCost}")
                }

                dataMutex.lock()
                completed++

                dataBuffer += "$bestIteration ${cfo.bestCost} $bestTime $startCost\n"
                if (dataBuffer.length > 100) {
                    data.appendText(dataBuffer)
                    dataBuffer = ""
                }

                print("\r${completed.toDouble() / iterations}")
                dataMutex.unlock()
            } }

            jobs.joinAll()
        }
        data.appendText(dataBuffer)
    }
}