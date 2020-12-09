

interface CostFunctionOptimizer {

    var lockedRowCount: Int
    val bestCost: Double
    fun iterate()
}