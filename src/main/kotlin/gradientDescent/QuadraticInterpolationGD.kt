package gradientDescent

import org.ejml.simple.SimpleMatrix
import utility.*
import kotlin.math.abs
import kotlin.math.pow

class QuadraticInterpolationGD(modelInfo: ModelInfo, derivativeCalculator: DerivativeCalculator, costCalculator: CostCalculator, private val epochs: Int = 50, private var beta: Double = 1.0) : GradientDescent(modelInfo, derivativeCalculator, costCalculator) {
    var alpha = Double.NaN
    private val tolerance = 1E-5
    private val RREF = false // true = Reduced Row Echelon Form instead of Quadratic Interpolation in the Lagrange Form

    private val x = MutableList(3){ 0.0 }
    private val fx = MutableList(3){ 0.0 }

    private fun determineRange(cost: (Double) -> Double){
        x[2] = beta
        x[1] = x[2] * (3..7).shuffled().last() / 10.0

        for(i in 0 until fx.size){
            fx[i] = cost(x[i])
        }
    }

    private fun correctReturnValue() : Double{
        // Returns the lowest value of x
        return x[fx.indexOf(fx.minByOrNull { it }!!)]
    }

    private fun correctInterval(x0: Double) : Boolean {
        if(x0 in x[0]..x[2]){
            return true
        }
        return false
    }

    private fun x0Calculator(): Double{
        val a = (fx[0]*(x[1]-x[2]) + fx[1]*(x[2]-x[0]) + fx[2]*(x[0]-x[1]))

        if(a == 0.0){
            return x[1]
        }
        val b = ( fx[0]*(x[1].pow(2)-x[2].pow(2))
                + fx[1]*(x[2].pow(2)-x[0].pow(2))
                + fx[2]*(x[0].pow(2)-x[1].pow(2))
                )
        return b/(2*a)
    }

    private fun x0RREF() : Double{
        val f = SimpleMatrix(3, 4)

        for(i in (x.size-1) downTo 0){
            f[i,0] = x[i].pow(2)
            f[i,1] = x[i]
            f[i,2] = 1.0
            f[i,3] = fx[i]
        }
        val rf = f.RREF()
        // Column 3 is the coefficients of the quadratic function, where row 0 = a, 1 = b and 2 = c
        return -(rf[1,3]/2*rf[0,3])
    }

    private fun sort(x0: Double, fx0: Double){
        if (fx0 < fx[1]) {
            if (x0 < x[1]) {
                x[2] = x[1]
                fx[2] = fx[1]
                x[1] = x0
                fx[1] = fx0
            } else {
                x[0] = x[1]
                fx[0] = fx[1]
                x[1] = x0
                fx[1] = fx0
            }
        } else {
            if (x0 < x[1]) {
                x[0] = x0
                fx[0] = fx0
            } else {
                x[2] = x0
                fx[2] = fx0
            }
        }
    }

    private fun check(x0: Double) : Boolean{
        when(abs(x0-x[1]) > tolerance && correctInterval(x0)){
            true -> return true
        }
        return false
    }

    private fun interpolate(cost: (Double) -> Double) : Double{
        determineRange(cost)
        var count = 0
        var condition = true

        while(count < epochs && condition){
            when (RREF){
                false -> {
                    val x0 = x0Calculator()
                    condition = check(x0)
                    sort(x0, cost(x0))
                }
                true -> {
                    val x0 = x0RREF()
                    if(!check(x0)){
                        return correctReturnValue()
                    }
                    sort(x0, cost(x0))
                }
            }
            count++
        }
        return correctReturnValue()
    }

    private val QIcostCalculator = costCalculator.clone() as CostCalculator

    override fun step(): SimpleMatrix {
        alpha = interpolate { s -> QIcostCalculator.cost((lumpingMatrix + gradient.scale(-s)).rowNorm()) }

        beta = 0.6 * (beta - 2 * alpha) + 2 * alpha

        return gradient.scale(-alpha)
    }
}