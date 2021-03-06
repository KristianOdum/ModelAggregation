package PSO

import CostFunctionOptimizer
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import org.ejml.simple.SimpleMatrix
import utility.*

abstract class ParticleSwarmOptimization<T>(modelInfo: ModelInfo, val costCalculator: CostCalculator,
                                            override var lockedRowCount: Int) : CostFunctionOptimizer where T : Particle {
    abstract var swarms: MutableList<Swarm<T>>

    val particleCount get() = swarms.sumOf { it.particles.size }

    override val bestCost get() = swarms.maxByOrNull { it.globalBestCost }!!.globalBestCost

    override fun iterate() {
        swarms.forEach { it.currentIteration++; updateSwarm(it) }
    }

    private fun updateSwarm(swarm: Swarm<T>) {
        var swarmImproved = false
        for (particle in swarm.particles) {
            updateParticle(swarm, particle)

            val cost = costCalculator.cost(particle.position)
            if (cost < particle.bestCost) {
                particle.bestPosition = particle.position
                particle.bestCost = cost

                if (cost < swarm.globalBestCost) {
                    swarm.globalBestPosition = particle.position
                    swarm.globalBestCost = cost
                    swarmImproved = true
                }
            }
        }
        if(swarmImproved)
            swarm.stagnationCount = 0
        else
            swarm.stagnationCount++
    }

    abstract fun updateParticle(swarm: Swarm<T>, particle: T)

}

class Swarm<T>(val particles: MutableList<T>) where T : Particle {
    var globalBestPosition: SimpleMatrix
    var globalBestCost: Double
    var currentIteration = 0
    var stagnationCount = 0

    init {
        val (bestStartPosition, bestStartCost) = particles.map { Pair(it.position, it.bestCost) }.minByOrNull { it.second }!!
        globalBestPosition = bestStartPosition
        globalBestCost = bestStartCost
    }

    fun averageDistance(): Double = particles.flatMap { o -> particles.map { i -> (o.position - i.position).normF() } }.average()
}

open class Particle(columns: Int, rows: Int, bounds: OpenEndDoubleRange) {
    var position = randMatrix(rows, columns, bounds).MGSON()
    open var bestPosition = position
    open var bestCost = Double.MAX_VALUE
}

class VelocityParticle(columns: Int, rows: Int, bounds: OpenEndDoubleRange) : Particle(columns, rows, bounds) {
    var velocity: SimpleMatrix = randMatrix(rows, columns, -bounds.size until bounds.size)
}