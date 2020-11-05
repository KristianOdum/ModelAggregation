package PSO

import MGSON
import allElements
import cost
import hadamard
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import normalize
import org.ejml.simple.SimpleMatrix
import randMatrix
import rowNorm
import java.io.File
import java.lang.Math.abs
import java.lang.Math.pow
import java.lang.StringBuilder

var lb = -1.0
var ub = 1.0
var maxV = (ub - lb) / 10.0
var maxOmega = 0.7
var minOmega = 0.4
var phi_p = 1.6
var phi_g = 1.6
var phi_l = 1.6

class ParticleSwarmOptimization(val function: (SimpleMatrix) -> SimpleMatrix, val dimensions: Int, val reducedDimensions: Int, val particleCount: Int = 10) {
    class Particle(n: Int, nHat: Int, bounds: ClosedFloatingPointRange<Double>) {
        var x: SimpleMatrix = randMatrix(nHat, n, bounds.start, bounds.endInclusive)
        var v: SimpleMatrix = run {
            val b = bounds.endInclusive - bounds.start
            randMatrix(nHat, n, -abs(b), abs(b))
        }

        fun update(globalBest: SimpleMatrix, localBest: SimpleMatrix, t: Int, tmax: Int) {
            val r_p = randMatrix(x.numRows(), x.numCols(), 0.0, 1.0)
            val r_g = randMatrix(x.numRows(), x.numCols(), 0.0, 1.0)
            val r_l = randMatrix(x.numRows(), x.numCols(), 0.0, 1.0)

            v = v.scale(maxOmega - (maxOmega - minOmega) / tmax * t) + (localBest.minus(x)).hadamard(r_l).scale(phi_l) + (bestX.minus(x)).hadamard(r_p).scale(phi_p) + (globalBest.minus(x)).hadamard(r_g).scale(phi_g)

            for (i in 0 until v.numElements) {
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
            particles.forEach { it.x = it.x.MGSON(); it.bestCost = cost(it.x, function) }
            val (x, c) = particles.map { Pair(it.x, it.bestCost) }.minByOrNull { it.second }!!
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
                        p.update(globalBestX, localBest(p), t, tmax)
                        p.x = p.x.MGSON()

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

        fun localBest(p: Particle): SimpleMatrix {
            return particlesNear(p, 5).minByOrNull { it.bestCost }!!.bestX
        }

        fun particlesNear(p: Particle, neighborhoodsize: Int): List<Particle> {
            val q = { it: Particle -> (it.x - p.x).allElements().map { it * it }.sum() }
            val r = particles.toList()
            return r.map { Pair(it, q(it)) }.sortedBy { it.second }.take(neighborhoodsize).map { it.first }
        }
    }

    fun run(epochs: Int): SimpleMatrix {
        var bestbestcost = Double.MAX_VALUE
        var bestbestx = SimpleMatrix()
        val s = StringBuilder()
        //val createSwarm = { i: Int -> Swarm(List(particleCount) { Particle(dimensions, reducedDimensions, lb.rangeTo(ub))}, function, epochs) }
        //var swarms = List( 1, createSwarm)
        //while (bestbestcost > 1.0E-16){
        val swarm = Swarm(List(particleCount) { Particle(dimensions, reducedDimensions, lb.rangeTo(ub)) }, function, epochs)
        s.clear()

        s.append("${swarm.globalBestCost} ")
        //s.append("${swarm.globalBestCost} ")
        repeat(epochs) {
            //println("--Iteration: ${it}--")
            //if (it % 1500 == 0){
            //    //swarms = swarms.sortedBy { it.globalBestCost }.take(5) + List(5, createSwarm)
            //    swarms.forEach { it.globalBestCost = Double.MAX_VALUE; it.particles.forEach { it.bestCost = Double.MAX_VALUE; it.x = randMatrix(it.x.numRows(), it.x.numCols(), lb, ub).MGSON() } }
            //}
            //swarms.forEach { it.iterate() }
            //println(swarms.map { it.globalBestCost }.min())
            swarm.iterate()
            s.append("${swarm.globalBestCost} ")
            //var pDist = 0.0
            //for(i in swarm.particles){
            //    for(j in swarm.particles){
            //        pDist += (i.x - j.x).normF()
            //    }
            //}
            //s.append("${pDist} ")
        }
        //println("Best X: ${swarm.globalBestX}")
        println("Best cost: ${swarm.globalBestCost}")
        if (swarm.globalBestCost < bestbestcost) {
            bestbestcost = swarm.globalBestCost
            bestbestx = swarm.globalBestX
        }
        //swarm.particles.forEach { println(it.x) }
        //return swarms.minBy { it.globalBestCost }!!.globalBestX
        //}

        s.appendLine()
        File("datax.txt").appendText(s.toString())
        return bestbestx
    }
}