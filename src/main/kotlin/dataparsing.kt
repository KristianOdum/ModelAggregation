import java.io.File
import kotlin.math.log10
import kotlin.math.pow

data class IterationData(val epoch: Int, val cost: Double, val time: Int, val startCost: Double)

fun main() {
    val data = File("line_search_T6.txt").readText().split('\n').filter { it.isNotBlank() }.map {
        val v = it.split(' ')
        IterationData(v[0].toInt(), v[1].toDouble(), v[2].toInt(), v[3].toDouble())
    }

    val logcost = 10.0.pow(data.map { log10(it.cost) }.average())
    val startCost = data.map { it.startCost }.average()
    val time = data.map { it.time }.average()
    val goodRate = data.count { it.cost < 1E-8 }.toDouble() / data.count()

    println("$logcost $startCost $time $goodRate")
}