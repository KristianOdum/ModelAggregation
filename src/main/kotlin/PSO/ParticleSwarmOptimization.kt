package PSO

import cost
import hadamard
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import org.ejml.simple.SimpleMatrix
import randMatrix
import rowNorm
import java.lang.Math.abs

var lb = 0.0
var ub = 1.0
var maxV = (ub - lb) / 100.0
var maxOmega = 0.8
var minOmega = 0.5
var phi_p = 1.6
var phi_g = 1.6

class ParticleSwarmOptimization(val function: (SimpleMatrix) -> SimpleMatrix, val dimensions: Int, val reducedDimensions: Int, val particleCount: Int = 10) {
    class Particle(n: Int, nHat: Int, bounds: ClosedFloatingPointRange<Double>, var id: Int) {
        var x: SimpleMatrix = randMatrix(nHat, n, bounds.start, bounds.endInclusive)
        var v: SimpleMatrix = run {
            val b = bounds.endInclusive - bounds.start
            randMatrix(nHat, n, -abs(b), abs(b))
        }

        fun update(globalBest: SimpleMatrix, t: Int, tmax: Int) {
            val r_p = randMatrix(x.numRows(), x.numCols(), 0.0, 1.0)
            val r_g = randMatrix(x.numRows(), x.numCols(), 0.0, 1.0)

            v = v.scale(maxOmega - (maxOmega - minOmega) / tmax * t) + (bestX.minus(x)).hadamard(r_p).scale(phi_p) + (globalBest.minus(x)).hadamard(r_g).scale(phi_g)

            for(i in 0 until v.numElements){
                if (v[i] > maxV) v[i] = maxV
                else if (v[i] < -maxV) v[i] = -maxV
            }
            x += v
        }

        var bestX = x
        var bestCost = Double.MAX_VALUE
    }

    class Swarm(val particles: List<Particle>, val function: (SimpleMatrix) -> SimpleMatrix, val tmax: Int) {
        var globalBestX: SimpleMatrix
        var globalBestCost: Double
        var t: Int = 1

        init {
            //particles.forEach{it.x = it.x.rowNorm(); it.bestCost = cost(it.x, function); println("Particle ${it.id} start value: ${it.x}")}
            particles.forEach{it.x = it.x.rowNorm(); it.bestCost = cost(it.x, function)}
            val (x, c) = particles.map{ Pair(it.x, it.bestCost) }.minByOrNull { it.second }!!
            globalBestX = x
            globalBestCost = c
            //println("Starting global best! Cost: $globalBestCost")
            //println("x: $x")
        }

        fun iterate() {
            val mutex = Mutex()
            runBlocking {
                val jobs = particles.map { p ->
                    GlobalScope.launch {
                        p.update(globalBestX, t, tmax)
                        p.x = p.x.rowNorm()

                        val xCost = cost(p.x, function)
                        if (xCost < p.bestCost) {
                            p.bestX = p.x
                            p.bestCost = xCost

                            mutex.lock()
                            if (xCost < globalBestCost) {
                                globalBestCost = xCost
                                globalBestX = p.x
                                //println("New global best! Found by ${p.id}! Cost: $globalBestCost")
                                //println("X: $globalBestX")
                            }
                            mutex.unlock()
                        }
                    }
                }

                jobs.joinAll()
            }
            t++
        }
    }

    fun run(epochs: Int): SimpleMatrix {
        var i = 1
        val swarm = Swarm(List(particleCount) { Particle(dimensions, reducedDimensions, lb.rangeTo(ub), i++)}, function, epochs)
        print("[0,${swarm.globalBestCost}],")
        repeat(epochs) {
            //println("--- Iteration ${swarm.t} ---")
            swarm.iterate()
            print("[${swarm.t - 1},${swarm.globalBestCost}]")
            if(swarm.t <= epochs)
                print(",")
        }

        return swarm.globalBestX
    }
}