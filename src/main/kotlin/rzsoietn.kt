import org.ejml.simple.SimpleMatrix
import utility.MGSON
import utility.ModelInfo
import utility.randMatrix
import utility.until
import kotlin.math.pow

class Test3ModelCreator  {
    fun createModel(reduction: Int) : ModelInfo {
        return ModelInfo(
                randMatrix(8 - reduction, 8, -1.0 until 1.0).MGSON())
        { y: SimpleMatrix ->
            val dy = SimpleMatrix(y)

            dy[1-1]=-1.0*2.5*y[1-1]/((1.0+y[8-1]/9.0)*(10.0+y[1-1]))+1.0*0.25*y[2-1]/(8.0+y[2-1])
            dy[2-1]=1.0*2.5*y[1-1]/((1.0+y[8-1]/9.0)*(10.0+y[1-1]))+(-1.0*0.25*y[2-1]/(8.0+y[2-1]))
            dy[3-1]=-1.0*0.025*y[2-1]*y[3-1]/(15.0+y[3-1])+1.0*0.75*y[4-1]/(15.0+y[4-1])
            dy[4-1]=1.0*0.025*y[2-1]*y[3-1]/(15.0+y[3-1])+(-1.0*0.025*y[2-1]*y[4-1]/(15.0+y[4-1]))+1.0*0.75*y[5-1]/(15.0+y[5-1])+(-1.0*0.75*y[4-1]/(15.0+y[4-1]))
            dy[5-1]=1.0*0.025*y[2-1]*y[4-1]/(15.0+y[4-1])+(-1.0*0.75*y[5-1]/(15.0+y[5-1]))
            dy[6-1]=-1.0*0.025*y[5-1]*y[6-1]/(15.0+y[6-1])+1.0*0.5*y[7-1]/(15.0+y[7-1])
            dy[7-1]=1.0*0.025*y[5-1]*y[6-1]/(15.0+y[6-1])+(-1.0*0.025*y[5-1]*y[7-1]/(15.0+y[7-1]))+1.0*0.5*y[8-1]/(15.0+y[8-1])+(-1.0*0.5*y[7-1]/(15.0+y[7-1]))
            dy[8-1]=1.0*0.025*y[5-1]*y[7-1]/(15.0+y[7-1])+(-1.0*0.5*y[8-1]/(15.0+y[8-1]))


            dy
        }
    }
}
