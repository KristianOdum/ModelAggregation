package utility

import org.ejml.simple.SimpleMatrix
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.random.Random

fun SimpleMatrix.withSet(i: Int, value: Double): SimpleMatrix {
    val new = SimpleMatrix(this)
    new[i] = value
    return new
}

private val randomP = Random(System.currentTimeMillis())
fun Random.nextDouble(range: OpenEndDoubleRange) = nextDouble(range.from, range.to)

fun SimpleMatrix.rightInverse() = this.transpose().mult(this.mult(this.transpose()).invert())!!

fun randMatrix(numRows: Int, numCols: Int, range: OpenEndDoubleRange): SimpleMatrix {
    val m = SimpleMatrix(numRows, numCols)

    for (i in 0 until m.numElements) {
        m[i] = randomP.nextDouble(range)
    }

    return m
}

fun SimpleMatrix.create(initializer: (Int, Int) -> Double): SimpleMatrix {
    val s = SimpleMatrix(this.numRows(), this.numCols())
    for (i in 0 until s.numRows()) {
        for (j in 0 until s.numCols()) {
            s[i, j] = initializer(i, j)
        }
    }
    return s
}

fun SimpleMatrix.create(initializer: (Int) -> Double): SimpleMatrix {
    val s = SimpleMatrix(this.numRows(), this.numCols())
    for (i in 0 until s.numElements) {
        s[i] = initializer(i)
    }
    return s
}

fun SimpleMatrix.allElements(): Iterable<Double> {
    val list = mutableListOf<Double>()

    for (i in 0 until this.numElements)
        list.add(this[i])

    return list
}

fun SimpleMatrix.hadamard(other: SimpleMatrix): SimpleMatrix {
    return this.create { i ->
        this[i] * other[i]
    }
}

fun SimpleMatrix.normalize(): SimpleMatrix = this.create { i, j -> this[i, j] / this.normF() }

fun SimpleMatrix.rowNorm(): SimpleMatrix {
    val n = SimpleMatrix(this)
    for (i in 0 until this.numRows()) {
        val magnitude = sqrt(rowVector(i).allElements().map { it * it }.sum())
        for (j in 0 until this.numCols()) {
            n[i, j] = n[i, j] / magnitude
        }
    }
    return n
}

fun SimpleMatrix.colNorm(): SimpleMatrix {
    val n = SimpleMatrix(this)
    for (j in 0 until numCols()) {
        val magnitude = sqrt(colVector(j).allElements().map { it * it }.sum())
        for (i in 0 until numRows()) {
            n[i, j] = n[i, j] / magnitude
        }
    }
    return n
}

fun SimpleMatrix.rowVector(r: Int): SimpleMatrix = SimpleMatrix(1, numCols()).create { i -> this[r, i] }
fun SimpleMatrix.colVector(c: Int): SimpleMatrix = SimpleMatrix(numRows(), 1).create { i -> this[i, c] }

data class LUPair(val L: SimpleMatrix, val U: SimpleMatrix)

fun SimpleMatrix.LUDecomposition(): LUPair {
    val upper = SimpleMatrix(numRows(), numCols())
    val lower = SimpleMatrix(numRows(), numRows())

    for (i in 0 until numRows()) {
        for (k in i until numCols()) {
            var sum = 0.0
            for (j in 0 until i) {
                sum += lower[i, j] * upper[j, k]
            }
            upper[i, k] = this[i, k] - sum
        }

        for (k in i until numRows()) {
            if (i == k) {
                lower[i, i] = 1.0
            } else {
                var sum = 0.0
                for (j in 0 until i)
                    sum += lower[k, j] * upper[j, i]
                lower[k, i] = (this[k, i] - sum) / upper[i, i]
            }
        }
    }

    return LUPair(lower, upper)
}

// Modified Gram-Schmidt OrthoNormalization
fun SimpleMatrix.MGSON(): SimpleMatrix {
    var u = SimpleMatrix(this)

    u = u.setRow(0, u.rowVector(0).normalize())
    for (r in 1 until numRows()) {
        var w = this.rowVector(r) - this.rowVector(r).project(u.rowVector(0))
        for (k in 1 until r) {
            w -= w.project(u.rowVector(k))
        }
        u = u.setRow(r, w.normalize())
    }
    return u
}

fun SimpleMatrix.project(u: SimpleMatrix): SimpleMatrix {
    if (u.allElements().all { it == 0.0 })
        return u
    return u.scale(u.dot2(this) / u.dot2(u))
}

fun SimpleMatrix.dot2(v: SimpleMatrix): Double {
    return allElements().zip(v.allElements()).sumByDouble { it.first * it.second }
}

fun SimpleMatrix.setRow(row: Int, vector: SimpleMatrix): SimpleMatrix {
    val final = SimpleMatrix(this)
    val tvector = if (vector.numCols() > vector.numRows()) vector else vector.transpose()

    for (col in 0 until numCols()) {
        final[row, col] = tvector[0, col]
    }
    return final
}

fun SimpleMatrix.withValues(vararg values: Double): SimpleMatrix {
    if (values.size > numElements)
        throw IllegalArgumentException("Cannot set more values than indices in matrix!")

    val m = SimpleMatrix(this)
    for (i in values.indices) {
        m[i] = values[i]
    }
    return m
}

fun SimpleMatrix.multiplyRow(row: Int, value: Double){
    for(i in 0 until numCols()){
        this[row,i] *= value
    }
}
fun SimpleMatrix.subtractRowByRow(row1: Int, row2: Int, value: Double = 1.0){
    for(i in 0 until numCols()){
        this[row1,i] -= this[row2,i] * value
    }
}

fun SimpleMatrix.swapRows(row1: Int, row2:Int){
    if(row1 >= numRows() || row2 >= numRows())
        throw IllegalArgumentException("Index of row is out of bound")

    for(i in 0 until numCols()){
        val temp = this[row1,i]
        this[row1,i] = this[row2,i]
        this[row2,i] = temp
    }
}

// Reduced Row Echelon Form (Gauss-Jordan Elimination)
fun SimpleMatrix.RREF() : SimpleMatrix{
    val v = SimpleMatrix(this)

    for(i in 0 until v.numRows()){
        var max = i

        for(j in i+1 until v.numRows()){
            if(abs(v[j,i]) > abs(v[max,i])){
                max = j
            }
        }
        v.swapRows(i,max)

        v.multiplyRow(i,v[i,i].pow(-1))
        for(j in i+1 until v.numRows()){
            v.subtractRowByRow(j, i, v[j,i] / v[i,i])
        }
    }
    for(i in 1 until v.numRows()){
        for(j in i-1 downTo 0){
            v.subtractRowByRow(j,i,v[j,i])
        }
    }
    return v
}