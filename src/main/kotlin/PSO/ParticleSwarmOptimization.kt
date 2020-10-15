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
import java.lang.Math.abs

var omega = 0.2
var phi_p = 0.2
var phi_g = 0.6

var lr = 0.5

class ParticleSwarmOptimization(val function: (SimpleMatrix) -> SimpleMatrix, val dimensions: Int, val reducedDimensions: Int, val particleCount: Int = 10) {
    class Particle(n: Int, nHat: Int, bounds: ClosedFloatingPointRange<Double>) {
        var x: SimpleMatrix = randMatrix(nHat, n, bounds.start, bounds.endInclusive)
        var v: SimpleMatrix = run {
            val b = bounds.endInclusive - bounds.start
            randMatrix(nHat, n, -abs(b), abs(b))
        }

        fun update(globalBest: SimpleMatrix) {
            val r_p = randMatrix(x.numRows(), x.numCols(), 0.0, 1.0)
            val r_g = randMatrix(x.numRows(), x.numCols(), 0.0, 1.0)

            v = v.scale(omega) + (bestX.minus(x)).hadamard(r_p).scale(phi_p) + (globalBest.minus(x)).hadamard(r_g).scale(phi_g)
            x += v.scale(lr)
        }

        var bestX = x
        var bestCost = Double.MAX_VALUE
    }

    class Swarm(val particles: List<Particle>, val function: (SimpleMatrix) -> SimpleMatrix) {
        var globalBestX: SimpleMatrix
        var globalBestCost: Double

        init {
            val (x, c) = particles.map { Pair(it.x, cost(it.x, function)) }.minByOrNull { it.second }!!
            globalBestX = x
            globalBestCost = c

            particles.forEach { it.bestCost = cost(it.x, function) }
        }

        fun iterate() {
            val mutex = Mutex()
            runBlocking {
                val jobs = particles.map { p ->
                    GlobalScope.launch {
                        p.update(globalBestX)

                        val xCost = cost(p.x, function)
                        if (xCost < p.bestCost) {
                            p.bestX = p.x
                            p.bestCost = xCost

                            mutex.lock()
                            if (xCost < globalBestCost) {
                                globalBestCost = xCost
                                globalBestX = p.x
                            }
                            mutex.unlock()
                        }
                    }
                }

                jobs.joinAll()
            }
        }
    }

    fun run(epochs: Int): SimpleMatrix {
        val swarm = Swarm(List(particleCount) { Particle(dimensions, reducedDimensions, (-1.0).rangeTo(1.0))}, function)
        repeat(epochs) {
            swarm.iterate()
        }

        return swarm.globalBestX
    }
}