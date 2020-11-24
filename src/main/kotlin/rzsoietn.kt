
import org.ejml.simple.SimpleMatrix
import utility.MGSON
import utility.ModelInfo
import utility.randMatrix
import utility.until
import kotlin.math.pow

class Test1ModelCreator  {
    fun createModel(reduction: Int) : ModelInfo {
        return ModelInfo(
                randMatrix(29 - reduction, 29, -1.0 until 1.0).MGSON())
        { y: SimpleMatrix ->
            val dy = SimpleMatrix(y)

            val Light_on = 1.0


            dy[1- 1]=-Light_on*340*y[2- 1]*1/(y[2- 1]+0.02*(1+y[3- 1]/0.84+y[10- 1]/0.04+y[14- 1]/0.075+y[8- 1]/0.9+y[6- 1]/0.07))
            dy[2- 1]=-Light_on*340*y[2- 1]*1/(y[2- 1]+0.02*(1+y[3- 1]/0.84+y[10- 1]/0.04+y[14- 1]/0.075+y[8- 1]/0.9+y[6- 1]/0.07))+Light_on*10000*y[17- 1]*1*y[4- 1]/((y[17- 1]+0.05*(1+y[3- 1]/2+y[2- 1]/0.7+y[8- 1]/4))*(y[4- 1]*(1+y[23- 1]/2.5)+0.05*(1+y[23- 1]/0.4)))
            dy[3- 1]=2*Light_on*340*y[2- 1]*1/(y[2- 1]+0.02*(1+y[3- 1]/0.84+y[10- 1]/0.04+y[14- 1]/0.075+y[8- 1]/0.9+y[6- 1]/0.07))+(-Light_on*5E8*1*(y[3- 1]*y[4- 1]-y[5- 1]*y[23- 1]/3.1E-4))+(-0.75*250*y[3- 1]*1/(0.25*(1+(1+0.74/y[18- 1])*(y[8- 1]/0.63+y[3- 1]/0.25+y[9- 1]/0.077+y[7- 1]/0.075))))
            dy[4- 1]=-Light_on*5E8*1*(y[3- 1]*y[4- 1]-y[5- 1]*y[23- 1]/3.1E-4)+(-Light_on*10000*y[17- 1]*1*y[4- 1]/((y[17- 1]+0.05*(1+y[3- 1]/2+y[2- 1]/0.7+y[8- 1]/4))*(y[4- 1]*(1+y[23- 1]/2.5)+0.05*(1+y[23- 1]/0.4))))+Light_on*3500*y[23- 1]*y[8- 1]*1/((y[23- 1]+0.014)*(y[8- 1]+0.3))+(-40*y[26- 1]*y[4- 1]*1/((y[26- 1]+0.08)*(1+y[23- 1]/10)*(y[4- 1]+0.08)+0.08*y[8- 1]/(0.1*y[3- 1])+0.02*y[11- 1]+0.02*y[10- 1]))
            dy[5- 1]=Light_on*5E8*1*(y[3- 1]*y[4- 1]-y[5- 1]*y[23- 1]/3.1E-4)+(-Light_on*5E8*1*(y[5- 1]*y[6- 1]*y[25- 1]-y[21- 1]*y[7- 1]*y[8- 1]/1.6E7))
            dy[6- 1]=-Light_on*5E8*1*(y[5- 1]*y[6- 1]*y[25- 1]-y[21- 1]*y[7- 1]*y[8- 1]/1.6E7)
            dy[7- 1]=Light_on*5E8*1*(y[5- 1]*y[6- 1]*y[25- 1]-y[21- 1]*y[7- 1]*y[8- 1]/1.6E7)+(-1*5E8*(y[7- 1]-y[9- 1]/22))+(-5E8*1*(y[9- 1]*y[7- 1]-y[10- 1]/7.1))+(-1*5E8*(y[11- 1]*y[7- 1]-y[12- 1]*y[13- 1]/0.084))+(-1*5E8*(y[7- 1]*y[15- 1]-y[13- 1]*y[16- 1]/0.85))+(-250*y[7- 1]*1/(0.075*(1+(1+0.74/y[18- 1])*(y[8- 1]/0.63+y[3- 1]/0.25+y[9- 1]/0.077+y[7- 1]/0.075))))
            dy[8- 1]=Light_on*5E8*1*(y[5- 1]*y[6- 1]*y[25- 1]-y[21- 1]*y[7- 1]*y[8- 1]/1.6E7)+Light_on*200*y[10- 1]*1/(y[10- 1]+0.03*(1+y[11- 1]/0.7+y[8- 1]/12))+Light_on*40*y[14- 1]*1/(y[14- 1]+0.013*(1+y[8- 1]/12))+(-Light_on*3500*y[23- 1]*y[8- 1]*1/((y[23- 1]+0.014)*(y[8- 1]+0.3)))+2*40*y[26- 1]*y[4- 1]*1/((y[26- 1]+0.08)*(1+y[23- 1]/10)*(y[4- 1]+0.08)+0.08*y[8- 1]/(0.1*y[3- 1])+0.02*y[11- 1]+0.02*y[10- 1])+(-40*y[8- 1]*1/(y[8- 1]+0.1*(1+y[26- 1]/0.05)))+250*y[9- 1]*1/(0.077*(1+(1+0.74/y[18- 1])*(y[8- 1]/0.63+y[3- 1]/0.25+y[9- 1]/0.077+y[7- 1]/0.075)))+0.75*250*y[3- 1]*1/(0.25*(1+(1+0.74/y[18- 1])*(y[8- 1]/0.63+y[3- 1]/0.25+y[9- 1]/0.077+y[7- 1]/0.075)))+250*y[7- 1]*1/(0.075*(1+(1+0.74/y[18- 1])*(y[8- 1]/0.63+y[3- 1]/0.25+y[9- 1]/0.077+y[7- 1]/0.075)))
            dy[9- 1]=1*5E8*(y[7- 1]-y[9- 1]/22)+(-5E8*1*(y[9- 1]*y[7- 1]-y[10- 1]/7.1))+(-1*5E8*(y[12- 1]*y[9- 1]-y[14- 1]/13))+(-250*y[9- 1]*1/(0.077*(1+(1+0.74/y[18- 1])*(y[8- 1]/0.63+y[3- 1]/0.25+y[9- 1]/0.077+y[7- 1]/0.075))))
            dy[10- 1]=5E8*1*(y[9- 1]*y[7- 1]-y[10- 1]/7.1)+(-Light_on*200*y[10- 1]*1/(y[10- 1]+0.03*(1+y[11- 1]/0.7+y[8- 1]/12)))
            dy[11- 1]=Light_on*200*y[10- 1]*1/(y[10- 1]+0.03*(1+y[11- 1]/0.7+y[8- 1]/12))+(-1*5E8*(y[11- 1]*y[7- 1]-y[12- 1]*y[13- 1]/0.084))+(-5E8*1*(y[11- 1]-y[20- 1]/2.3))
            dy[12- 1]=1*5E8*(y[11- 1]*y[7- 1]-y[12- 1]*y[13- 1]/0.084)+(-1*5E8*(y[12- 1]*y[9- 1]-y[14- 1]/13))
            dy[13- 1]=1*5E8*(y[11- 1]*y[7- 1]-y[12- 1]*y[13- 1]/0.084)+1*5E8*(y[7- 1]*y[15- 1]-y[13- 1]*y[16- 1]/0.85)+(-1*5E8*(y[13- 1]-y[17- 1]/0.67))
            dy[14- 1]=1*5E8*(y[12- 1]*y[9- 1]-y[14- 1]/13)+(-Light_on*40*y[14- 1]*1/(y[14- 1]+0.013*(1+y[8- 1]/12)))
            dy[15- 1]=Light_on*40*y[14- 1]*1/(y[14- 1]+0.013*(1+y[8- 1]/12))+(-1*5E8*(y[7- 1]*y[15- 1]-y[13- 1]*y[16- 1]/0.85))
            dy[16- 1]=1*5E8*(y[7- 1]*y[15- 1]-y[13- 1]*y[16- 1]/0.85)+(-5E8*1*(y[16- 1]-y[17- 1]/0.4))
            dy[17- 1]=5E8*1*(y[16- 1]-y[17- 1]/0.4)+1*5E8*(y[13- 1]-y[17- 1]/0.67)+(-Light_on*10000*y[17- 1]*1*y[4- 1]/((y[17- 1]+0.05*(1+y[3- 1]/2+y[2- 1]/0.7+y[8- 1]/4))*(y[4- 1]*(1+y[23- 1]/2.5)+0.05*(1+y[23- 1]/0.4))))
            dy[18- 1]=-250*y[9- 1]*1/(0.077*(1+(1+0.74/y[18- 1])*(y[8- 1]/0.63+y[3- 1]/0.25+y[9- 1]/0.077+y[7- 1]/0.075)))+(-0.75*250*y[3- 1]*1/(0.25*(1+(1+0.74/y[18- 1])*(y[8- 1]/0.63+y[3- 1]/0.25+y[9- 1]/0.077+y[7- 1]/0.075))))+(-250*y[7- 1]*1/(0.075*(1+(1+0.74/y[18- 1])*(y[8- 1]/0.63+y[3- 1]/0.25+y[9- 1]/0.077+y[7- 1]/0.075))))
            dy[19- 1]=250*y[7- 1]*1/(0.075*(1+(1+0.74/y[18- 1])*(y[8- 1]/0.63+y[3- 1]/0.25+y[9- 1]/0.077+y[7- 1]/0.075)))
            dy[20- 1]=5E8*1*(y[11- 1]-y[20- 1]/2.3)+(-5E8*1*(y[20- 1]-y[26- 1]/0.058))
            dy[21- 1]=Light_on*5E8*1*(y[5- 1]*y[6- 1]*y[25- 1]-y[21- 1]*y[7- 1]*y[8- 1]/1.6E7)
            dy[22- 1]=0.75*250*y[3- 1]*1/(0.25*(1+(1+0.74/y[18- 1])*(y[8- 1]/0.63+y[3- 1]/0.25+y[9- 1]/0.077+y[7- 1]/0.075)))
            dy[23- 1]=Light_on*5E8*1*(y[3- 1]*y[4- 1]-y[5- 1]*y[23- 1]/3.1E-4)+Light_on*10000*y[17- 1]*1*y[4- 1]/((y[17- 1]+0.05*(1+y[3- 1]/2+y[2- 1]/0.7+y[8- 1]/4))*(y[4- 1]*(1+y[23- 1]/2.5)+0.05*(1+y[23- 1]/0.4)))+(-Light_on*3500*y[23- 1]*y[8- 1]*1/((y[23- 1]+0.014)*(y[8- 1]+0.3)))+40*y[26- 1]*y[4- 1]*1/((y[26- 1]+0.08)*(1+y[23- 1]/10)*(y[4- 1]+0.08)+0.08*y[8- 1]/(0.1*y[3- 1])+0.02*y[11- 1]+0.02*y[10- 1])
            dy[24- 1]=250*y[9- 1]*1/(0.077*(1+(1+0.74/y[18- 1])*(y[8- 1]/0.63+y[3- 1]/0.25+y[9- 1]/0.077+y[7- 1]/0.075)))
            dy[25- 1]=-Light_on*5E8*1*(y[5- 1]*y[6- 1]*y[25- 1]-y[21- 1]*y[7- 1]*y[8- 1]/1.6E7)
            dy[26- 1]=5E8*1*(y[20- 1]-y[26- 1]/0.058)+(-40*y[26- 1]*y[4- 1]*1/((y[26- 1]+0.08)*(1+y[23- 1]/10)*(y[4- 1]+0.08)+0.08*y[8- 1]/(0.1*y[3- 1])+0.02*y[11- 1]+0.02*y[10- 1]))+40*y[8- 1]*1/(y[8- 1]+0.1*(1+y[26- 1]/0.05))
            dy[27- 1]=40*y[26- 1]*y[4- 1]*1/((y[26- 1]+0.08)*(1+y[23- 1]/10)*(y[4- 1]+0.08)+0.08*y[8- 1]/(0.1*y[3- 1])+0.02*y[11- 1]+0.02*y[10- 1])+(-40*y[8- 1]*1/(y[8- 1]+0.1*(1+y[26- 1]/0.05)))

            dy
        }
    }
}
