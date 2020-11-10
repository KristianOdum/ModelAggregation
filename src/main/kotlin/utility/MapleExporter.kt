package utility

import java.io.File

class MapleExporter {
    class Series(val name: String) {
        val points = mutableListOf<Pair<Double, Double>>()

        fun xy(x: Double, y: Double) {
            points.add(Pair(x, y))
        }

        fun xy(x: Int, y: Double) {
            points.add(Pair(x.toDouble(), y))
        }
    }

    val series  = mutableMapOf<String, Series>()

    fun addSeries(name: String): Series {
        series[name] = Series(name)
        return series[name]!!
    }

    private var count = 0
    fun export(fileName: String = "Maple") {
        for (s in series.values) {
            val fn = s.name + "_" + fileName
            val fileData: String = s.points.joinToString("") { "${it.first}\t${it.second}\n" } // Important to use \t here, since it is maple default delimiter

            while (true) {
                val path = "maple/$fn$count.txt"
                val file = File(path)
                val exists = file.exists()
                count++

                if (!exists) {
                    file.writeText(fileData)
                    return
                }
            }
        }
    }
}