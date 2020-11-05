import utility.Plotter

fun main() {
    val plotter = Plotter()

    plotter.plot.xAxis("This axis", Plot.axisOpts())

    val d = plotter.addSeries("Test")

    d.xy(0.0, 1.4)
    d.xy(2.4, 8.5)

    plotter.save()

}