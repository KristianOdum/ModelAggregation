import gradientDescent.GoldenSectionGD
import gradientDescent.GradientDescent
import utility.Plotter
import utility.SIRModelCreator
import utility.plotAlpha

fun main() {
    val mi = SIRModelCreator().random(3, 1)

    val gd = GoldenSectionGD(mi)

    println(gd.cost)
    while (true) {
        gd.iterate()

        plotAlpha(gd.lumpingMatrix, gd.gradient, mi.function, gd.beta, gd.alpha)

        println(gd.cost)
    }

}