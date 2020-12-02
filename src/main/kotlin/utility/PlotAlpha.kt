package utility

import gradientDescent.DerivativeCalculator
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import org.ejml.simple.SimpleMatrix
import java.awt.Color

fun plotAlpha(m: SimpleMatrix, g: SimpleMatrix, f: (SimpleMatrix) -> SimpleMatrix, beta: Double, alpha: Double) {
    val dataPoints = mutableListOf<Pair<Double, Double>>()
    val plotCount = 100
    val costCalculator = CostCalculator(MonteCarloMeanCalculator(m.numCols(), 0.05), f)
    runBlocking {
        val dataPointMutex = Mutex()
        val jobs = (0 until plotCount).map {
            GlobalScope.launch {
                val d = beta * it.toDouble() / plotCount
                val c = costCalculator.cost((m + g.scale(-d)).rowNorm())

                dataPointMutex.lock()
                dataPoints.add(Pair(d, c))
                dataPointMutex.unlock()
            }
        }
        jobs.joinAll()
    }
    dataPoints.sortBy { it.first }
    val plotter = Plotter()
    val d = plotter.addSeries("Cost")
    dataPoints.forEach { d.xy(it.first, it.second) }
    plotter.addSeries("Alpha", Color.RED, Plot.Marker.DIAMOND).xy(alpha, costCalculator.cost((m + g.scale(-alpha)).rowNorm()))
    plotter.save()
}