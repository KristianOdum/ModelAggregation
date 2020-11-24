import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import java.io.File
import java.util.*
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime

class CostFunctionOptimizerTester(private val maxEpochs: Int, private val iterations: Int, val factory: () -> CostFunctionOptimizer)  {

    companion object {
        private const val MAX_PLATEAU = 25
    }

    fun run() {
        val data = File("CostFunctionOptimizerTesterOutput.txt")
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
                val time = measureTimeMillis {
                    while (plateauCounter < MAX_PLATEAU && lastBestCost >= 1.0E-8 && i < maxEpochs) {
                        cfo.iterate()

                        if (cfo.bestCost >= lastBestCost)
                            plateauCounter++
                        else {
                            plateauCounter = 0
                            lastBestCost = cfo.bestCost
                        }
                        i++

                        println("${i.toDouble() /  maxEpochs}")
                    }
                }

                dataMutex.lock()
                completed++

                dataBuffer += "$i ${cfo.bestCost} $time\n"
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