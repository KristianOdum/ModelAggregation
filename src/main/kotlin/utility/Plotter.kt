package utility

import java.awt.Color
import java.io.File

class Plotter {
    private data class Series(val name: String, val color: Color, val marker: Plot.Marker)

    val plot = Plot.plot(Plot.plotOpts())

    private val series = mutableMapOf<Series, Plot.Data>()

    fun addSeries(name: String, color: Color = Color.BLUE, marker: Plot.Marker = Plot.Marker.NONE): Plot.Data {
        val d = Plot.data()
        val s = Series(name, color, marker)
        series[s] = d
        return d
    }

    private var count = 0
    fun save(fileName: String = "plot") {

        for ((s, d) in series) {
            plot.series(s.name, d, Plot.seriesOpts().color(s.color).marker(s.marker))
        }

        while (true) {
            val path = "plots/$fileName$count.png"
            val file = File(path)
            val exists = file.exists()
            count++

            if (!exists) {
                plot.save(path.removeSuffix(".png"), "png")
                return
            }
        }
    }

}