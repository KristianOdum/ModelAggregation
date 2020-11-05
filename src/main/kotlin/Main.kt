import gradientDescent.GradientDescent
import gradientDescent.SimpleStep
import utility.Plotter
import utility.SIRModelCreator

fun main() {
    val mi = SIRModelCreator().random(3, 1)

    val gd = GradientDescent(mi, SimpleStep(0.1))

    println(gd.cost)
    while (true) {
        gd.iterate()

        println(gd.cost)
    }

}