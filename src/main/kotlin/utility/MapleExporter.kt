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

    fun export() {
        for (s in series.values) {
            var count = 0
            val fn = s.name
            val fileData: String = s.points.joinToString("") { "${it.first}\t${it.second}\n" } // Important to use \t here, since it is maple default delimiter

            while (true) {
                val path = "maple/$fn$count.txt"
                val file = File(path)
                val exists = file.exists()
                count++

                file.mkdirs()

                if (!exists) {
                    file.writeText(fileData)
                    break
                }
            }
        }
    }
}