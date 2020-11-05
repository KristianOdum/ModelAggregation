import org.ejml.simple.SimpleMatrix

data class ModelInfo(val lumpingMatrix: SimpleMatrix, val function: (SimpleMatrix) -> SimpleMatrix)