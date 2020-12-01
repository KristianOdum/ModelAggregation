
import org.ejml.simple.SimpleMatrix
import utility.MGSON
import utility.ModelInfo
import utility.randMatrix
import utility.until
import kotlin.math.pow

class T3ModelCreator  {
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

//BIOMD0000000013
class T10ModelCreator  {
    fun createModel(reduction: Int) : ModelInfo {
        return ModelInfo(
                randMatrix(27 - reduction, 27, -1.0 until 1.0).MGSON())
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

//BIOMD0000000090
class T11ModelCreator  {
    fun createModel(reduction: Int) : ModelInfo {
        return ModelInfo(
                randMatrix(27 - reduction, 27, -1.0 until 1.0).MGSON())
        { y: SimpleMatrix ->
            val dy = SimpleMatrix(y)

            val k_v0 = 1.6

            val k2 = 0.2

            val k3 = 0.2

            val k4 = 0.2

            val k5 = 0.1

            val k6 = 0.12

            val k7 = 10.0

            val k8 = 10.0

            val k9 = 10.0

            val k_v10 = 80.0

            val k11 = 10.0

            val k12 = 5.0

            val k_v13 = 4.0

            val k14 = 10.0

            val k15 = 5.0

            val k16 = 10.0

            val k17 = 0.02

            val k18 = 1.0

            val n = 4.0

            val m = 4.0

            val Ka = 1.0

            val Kc = 0.1

            val a = 0.1

            val Ac = 2.0

            val Am = 2.0

            val S = 2.0

            val N = 2.0

            val Kh = 0.5


            dy[1- 1]=0.0
            dy[2- 1]=0.0
            dy[3- 1]=0.0
            dy[4- 1]=1*k_v10+(-1*k14)*y[4- 1]+(-1*k11*y[15- 1]*y[4- 1]/((a*y[15- 1]+y[4- 1])*(1+(y[13- 1]/Kh).pow(m))))
            dy[5- 1]=0.0
            dy[6- 1]=(-1*k2*y[10- 1])*y[6- 1]+(-1*k3*y[7- 1])*y[6- 1]+1*k16*y[24- 1]*(Ac-y[6- 1])+(-1*k12)*y[6- 1]
            dy[7- 1]=1*k2*y[10- 1]*y[6- 1]+(-1*k3*y[7- 1])*y[6- 1]
            dy[8- 1]=0.0
            dy[9- 1]=1*k3*y[7- 1]*y[6- 1]+(-1*k4*y[9- 1])*y[15- 1]
            dy[10- 1]=1*k_v0/(1+(y[14- 1]/Kc).pow(n))+(-1*k2*y[10- 1])*y[6- 1]
            dy[11- 1]=1*k_v13+(-1*k7*y[11- 1]*(N-y[15- 1]))
            dy[12- 1]=1*k3*y[7- 1]*y[6- 1]+(-1*k16*y[24- 1]*(Ac-y[6- 1]))+1*k12*y[6- 1]
            dy[13- 1]=1*k4*y[9- 1]*y[15- 1]+(-1*k5*y[13- 1])*y[18- 1]+1*k17*y[13- 1]
            dy[14- 1]=1*k5*y[13- 1]*y[18- 1]+1*k6*y[14- 1]
            dy[15- 1]=(-3)*1*k4*y[9- 1]*y[15- 1]+2*1*k7*y[11- 1]*(N-y[15- 1])+4*1*k9*y[19- 1]*(N-y[15- 1])+(-1*k11*y[15- 1]*y[4- 1]/((a*y[15- 1]+y[4- 1])*(1+(y[13- 1]/Kh).pow(m))))
            dy[16- 1]=3*1*k4*y[9- 1]*y[15- 1]+(-2)*1*k7*y[11- 1]*(N-y[15- 1])+(-4)*1*k9*y[19- 1]*(N-y[15- 1])+1*k11*y[15- 1]*y[4- 1]/((a*y[15- 1]+y[4- 1])*(1+(y[13- 1]/Kh).pow(m)))
            dy[17- 1]=1*k7*y[11- 1]*(N-y[15- 1])+(-1*k15)*y[17- 1]+(-1*k8*y[17- 1]*(S-y[19- 1]))
            dy[18- 1]=(-1*k5*y[13- 1])*y[18- 1]+1*k15*y[17- 1]+1*k18*y[18- 1]
            dy[19- 1]=1*k8*y[17- 1]*(S-y[19- 1])+(-1*k9*y[19- 1]*(N-y[15- 1]))
            dy[20- 1]=-1*k8*y[17- 1]*(S-y[19- 1])+1*k9*y[19- 1]*(N-y[15- 1])
            dy[21- 1]=0.0
            dy[22- 1]=0.0
            dy[23- 1]=1*k16*y[24- 1]*(Ac-y[6- 1])+(-1*3*k11*y[15- 1]*y[4- 1]/((a*y[15- 1]+y[4- 1])*(1+(y[13- 1]/Kh).pow(m)))*(Am-y[24- 1])/(Ka+(Am-y[24- 1])))
            dy[24- 1]=-1*k16*y[24- 1]*(Ac-y[6- 1])+1*3*k11*y[15- 1]*y[4- 1]/((a*y[15- 1]+y[4- 1])*(1+(y[13- 1]/Kh).pow(m)))*(Am-y[24- 1])/(Ka+(Am-y[24- 1]))
            dy[25- 1]=0.0
            dy[26- 1]=0.0
            dy[27- 1]=(-1*k6)*y[14- 1]+(-1*k17)*y[13- 1]+(-1*k18)*y[18- 1]

            dy
        }
    }
}

//BIOMD0000000107
class T12ModelCreator  {
    fun createModel(reduction: Int) : ModelInfo {
        return ModelInfo(
                randMatrix(16 - reduction, 16, -1.0 until 1.0).MGSON())
        { y: SimpleMatrix ->
            val dy = SimpleMatrix(y)

            val total_cdc2 = 100.0

            val total_cdc25 = 1.0

            val total_wee1 = 1.0

            val total_IE = 1.0

            val total_UbE = 1.0

            val k25 = 0.0

            val V25_prime = 0.1

            val V25_double_prime = 2.0

            val kwee = 0.0

            val Vwee_prime = 0.1

            val Vwee_double_prime = 1.0

            val k2 = 0.0

            val V2_prime = 0.015

            val V2_double_prime = 1.0

            val k1AA = 1.0

            val k3 = 0.01

            val kinh = 0.025

            val kcak = 0.25

            val ka = 0.01

            val K_a = 0.1

            val kbPPase = 0.125

            val K_b = 0.1

            val ke = 0.0133

            val K_e = 0.3

            val kfPPase = 0.1

            val K_f = 0.3

            val kg = 0.0065

            val K_g = 0.01

            val khPPAse = 0.087

            val K_h = 0.01

            val kc = 0.1

            val K_c = 0.01

            val kd_anti_IE = 0.095

            val K_d = 0.01

            val total_cyclin = 0.0

            val Y15P = 0.0


            dy[1- 1]=y[16- 1]*k1AA+(V2_prime*(total_UbE-y[9- 1])+V2_double_prime*y[9- 1])*y[1- 1]+(-k3*y[1- 1]*(total_cdc2-(y[2- 1]+y[4- 1]+y[5- 1]+y[3- 1])))
            dy[2- 1]=k3*y[1- 1]*(total_cdc2-(y[2- 1]+y[4- 1]+y[5- 1]+y[3- 1]))+kinh*y[3- 1]+(-(Vwee_prime*y[7- 1]+Vwee_double_prime*(total_wee1-y[7- 1]))*y[2- 1])+(-kcak)*y[2- 1]+(V2_prime*(total_UbE-y[9- 1])+V2_double_prime*y[9- 1])*y[2- 1]+(V25_prime*(total_cdc25-y[6- 1])+V25_double_prime*y[6- 1])*y[4- 1]
            dy[3- 1]=(-kinh)*y[3- 1]+kcak*y[2- 1]+(-(Vwee_prime*y[7- 1]+Vwee_double_prime*(total_wee1-y[7- 1]))*y[3- 1])+(V25_prime*(total_cdc25-y[6- 1])+V25_double_prime*y[6- 1])*y[5- 1]+(V2_prime*(total_UbE-y[9- 1])+V2_double_prime*y[9- 1])*y[3- 1]
            dy[4- 1]=(Vwee_prime*y[7- 1]+Vwee_double_prime*(total_wee1-y[7- 1]))*y[2- 1]+(-(V25_prime*(total_cdc25-y[6- 1])+V25_double_prime*y[6- 1])*y[4- 1])+(-kcak)*y[4- 1]+(V2_prime*(total_UbE-y[9- 1])+V2_double_prime*y[9- 1])*y[4- 1]+kinh*y[5- 1]
            dy[5- 1]=kcak*y[4- 1]+(-kinh)*y[5- 1]+(Vwee_prime*y[7- 1]+Vwee_double_prime*(total_wee1-y[7- 1]))*y[3- 1]+(-(V25_prime*(total_cdc25-y[6- 1])+V25_double_prime*y[6- 1])*y[5- 1])+(V2_prime*(total_UbE-y[9- 1])+V2_double_prime*y[9- 1])*y[5- 1]
            dy[6- 1]=ka*y[3- 1]*(total_cdc25-y[6- 1])/(K_a+(total_cdc25-y[6- 1]))+(-kbPPase*y[6- 1]/(K_b+y[6- 1]))
            dy[7- 1]=ke*y[3- 1]*(total_wee1-y[7- 1])/(K_e+(total_wee1-y[7- 1]))+(-kfPPase*y[7- 1]/(K_f+y[7- 1]))
            dy[8- 1]=kg*y[3- 1]*(total_IE-y[8- 1])/(K_g+(total_IE-y[8- 1]))+(-khPPAse*y[8- 1]/(K_h+y[8- 1]))
            dy[9- 1]=kc*y[8- 1]*(total_UbE-y[9- 1])/(K_c+(total_UbE-y[9- 1]))+(-kd_anti_IE*y[9- 1]/(K_d+y[9- 1]))
            dy[10- 1]=-k3*y[1- 1]*(total_cdc2-(y[2- 1]+y[4- 1]+y[5- 1]+y[3- 1]))
            dy[11- 1]=-ka*y[3- 1]*(total_cdc25-y[6- 1])/(K_a+(total_cdc25-y[6- 1]))+kbPPase*y[6- 1]/(K_b+y[6- 1])
            dy[12- 1]=-ke*y[3- 1]*(total_wee1-y[7- 1])/(K_e+(total_wee1-y[7- 1]))+kfPPase*y[7- 1]/(K_f+y[7- 1])
            dy[13- 1]=-kg*y[3- 1]*(total_IE-y[8- 1])/(K_g+(total_IE-y[8- 1]))+khPPAse*y[8- 1]/(K_h+y[8- 1])
            dy[14- 1]=-kc*y[8- 1]*(total_UbE-y[9- 1])/(K_c+(total_UbE-y[9- 1]))+kd_anti_IE*y[9- 1]/(K_d+y[9- 1])
            dy[15- 1]=-(V2_prime*(total_UbE-y[9- 1])+V2_double_prime*y[9- 1])*y[1- 1]+(-(V2_prime*(total_UbE-y[9- 1])+V2_double_prime*y[9- 1])*y[2- 1])+(-(V2_prime*(total_UbE-y[9- 1])+V2_double_prime*y[9- 1])*y[4- 1])+(-(V2_prime*(total_UbE-y[9- 1])+V2_double_prime*y[9- 1])*y[5- 1])+(-(V2_prime*(total_UbE-y[9- 1])+V2_double_prime*y[9- 1])*y[3- 1])
            dy[16- 1]=0.0

            dy
        }
    }
}

//BIOMD0000000143
class T13ModelCreator  {
    fun createModel(reduction: Int) : ModelInfo {
        return ModelInfo(
                randMatrix(22 - reduction, 22, -1.0 until 1.0).MGSON())
        { y: SimpleMatrix ->
            val dy = SimpleMatrix(y)

            val Knadph = 60.0

            val k1 = 50.0

            val kminus1 = 58.0

            val k2 = 10.0

            val k3 = 0.004

            val k4 = 20.0

            val k5 = 10.0

            val k6 = 0.1

            val k7 = 1.0E-6

            val k8 = 50.0

            val k9 = 500.0

            val k10 = 10.0

            val k11 = 60.0

            val k12 = 25.0

            val k13 = 12.5

            val kminus13 = 0.045

            val k14 = 30.0

            val k15 = 30.0

            val k16 = 10.0

            val k17 = 10.0

            val k18 = 2.0

            val V = 288.0

            val L = 550.0

            val Ko = 1.5


            dy[1- 1]=-1*(k1*y[1- 1]*y[2- 1]-kminus1*y[3- 1])+1*k5*y[7- 1].pow(2)+(-1*(k15*y[1- 1]-k15*y[13- 1]))
            dy[2- 1]=-1*(k1*y[1- 1]*y[2- 1]-kminus1*y[3- 1])+1*k3*y[5- 1]*y[4- 1]+(-1*k4*y[2- 1])*y[7- 1]
            dy[3- 1]=1*(k1*y[1- 1]*y[2- 1]-kminus1*y[3- 1])+(-1*k2*y[3- 1])*y[4- 1]+1*k6*y[19- 1]*y[7- 1]
            dy[4- 1]=(-1*k2*y[3- 1])*y[4- 1]+(-1*k3*y[5- 1])*y[4- 1]+(-1*(k16*y[4- 1]-k16*y[18- 1]))
            dy[5- 1]=1*k2*y[3- 1]*y[4- 1]+(-1*k3*y[5- 1])*y[4- 1]
            dy[6- 1]=1*k2*y[3- 1]*y[4- 1]+1*k3*y[5- 1]*y[4- 1]+(-1*(k17*y[6- 1]-k17*y[17- 1]))
            dy[7- 1]=(-1*k4*y[2- 1])*y[7- 1]+(-2)*1*k5*y[7- 1].pow(2)+(-1*k6*y[19- 1])*y[7- 1]+(-1*(k18*y[7- 1]-k18*y[15- 1]))+2*1*V*y[10- 1]/Knadph*(1+y[10- 1]/Knadph)*y[9- 1]/((L+(1+y[10- 1]/Knadph).pow(2))*(Ko+y[9- 1]))
            dy[8- 1]=(-2)*1*k5*y[7- 1].pow(2)
            dy[9- 1]=1*k5*y[7- 1].pow(2)+1*k6*y[19- 1]*y[7- 1]+(-1*(k14*y[9- 1]-k14*y[11- 1]))+(-2)*1*V*y[10- 1]/Knadph*(1+y[10- 1]/Knadph)*y[9- 1]/((L+(1+y[10- 1]/Knadph).pow(2))*(Ko+y[9- 1]))
            dy[10- 1]=(-10*k7*y[10- 1])*y[11- 1]+(-10*k10*y[17- 1])*y[10- 1]+y[22- 1]*10*k12+(-1*V*y[10- 1]/Knadph*(1+y[10- 1]/Knadph)*y[9- 1]/((L+(1+y[10- 1]/Knadph).pow(2))*(Ko+y[9- 1])))
            dy[11- 1]=(-10*k7*y[10- 1])*y[11- 1]+(-10*k8*y[14- 1])*y[11- 1]+10*k9*y[15- 1].pow(2)+y[22- 1]*10*k13+10*kminus13*y[11- 1]+1*(k14*y[9- 1]-k14*y[11- 1])
            dy[12- 1]=10*k7*y[10- 1]*y[11- 1]+10*k8*y[14- 1]*y[11- 1]+1*V*y[10- 1]/Knadph*(1+y[10- 1]/Knadph)*y[9- 1]/((L+(1+y[10- 1]/Knadph).pow(2))*(Ko+y[9- 1]))
            dy[13- 1]=10*k7*y[10- 1]*y[11- 1]+10*k9*y[15- 1].pow(2)+1*(k15*y[1- 1]-k15*y[13- 1])
            dy[14- 1]=(-10*k8*y[14- 1])*y[11- 1]+10*k10*y[17- 1]*y[10- 1]+(-2)*10*k11*y[14- 1].pow(2)
            dy[15- 1]=10*k8*y[14- 1]*y[11- 1]+(-2)*10*k9*y[15- 1].pow(2)+1*(k18*y[7- 1]-k18*y[15- 1])
            dy[16- 1]=(-2)*10*k9*y[15- 1].pow(2)
            dy[17- 1]=(-10*k10*y[17- 1])*y[10- 1]+1*(k17*y[6- 1]-k17*y[17- 1])
            dy[18- 1]=10*k10*y[17- 1]*y[10- 1]+1*(k16*y[4- 1]-k16*y[18- 1])
            dy[19- 1]=1*k4*y[2- 1]*y[7- 1]+(-1*k6*y[19- 1])*y[7- 1]
            dy[20- 1]=10*k11*y[14- 1].pow(2)
            dy[21- 1]=(-10*kminus13)*y[11- 1]
            dy[22- 1]=0.0

            dy
        }
    }
}

//BIOMD0000000005
class T14ModelCreator  {
    fun createModel(reduction: Int) : ModelInfo {
        return ModelInfo(
                randMatrix(9 - reduction, 9, -1.0 until 1.0).MGSON())
        { y: SimpleMatrix ->
            val dy = SimpleMatrix(y)

            dy[1- 1]=(-1)*0.015+1*0*y[6- 1]+1*0.6*y[7- 1]
            dy[2- 1]=1*1*y[4- 1]+(-1*y[2- 1])*1000000+1*y[3- 1]*1000
            dy[3- 1]=1*y[2- 1]*1000000+(-1*y[3- 1])*1000+(-1*y[3- 1]*200)*y[6- 1]
            dy[4- 1]=(-1*1)*y[4- 1]+(-1*0)*y[4- 1]+1*y[5- 1]*(0.018+180*(y[4- 1]/(y[2- 1]+y[3- 1]+y[4- 1]+y[5- 1])).pow(2))
            dy[5- 1]=1*y[3- 1]*200*y[6- 1]+1*0*y[4- 1]+(-1*y[5- 1]*(0.018+180*(y[4- 1]/(y[2- 1]+y[3- 1]+y[4- 1]+y[5- 1])).pow(2)))
            dy[6- 1]=(-1*y[3- 1]*200)*y[6- 1]+1*0.015+(-1*0)*y[6- 1]
            dy[7- 1]=1*1*y[4- 1]+(-1*0.6)*y[7- 1]
            dy[8- 1]=0.0
            dy[9- 1]=0.0

            dy
        }
    }
}

//BIOMD0000000077
class T4ModelCreator  {
    fun createModel(reduction: Int) : ModelInfo {
        return ModelInfo(
            randMatrix(9 - reduction, 9, -1.0 until 1.0).MGSON())
        { y: SimpleMatrix ->
            val dy = SimpleMatrix(y)

            val alpha = 2.0

            val beta = 4.0


            dy[0]=((-1.0)*(1.0*2.5))*y[0]*y[2]+((-1.0)*(1.0*(-5.0)))*y[1]
            dy[1]=1.0*2.5*y[0]*y[2]+(1.0*(-5.0))*y[1]+((-2.0)*(1.0*2500.0))*y[1]*y[1]+((-2.0)*(1.0*(-5.0)))*y[3]
            dy[2]=((-1.0)*(1.0*2.5))*y[0]*y[2]+((-1.0)*(1.0*(-5.0)))*y[1]
            dy[3]=1.0*2500.0*y[1]*y[1]+(1.0*(-5.0))*y[3]+((-1.0)*(1.0*4000.0))*y[3]*y[5]+((-1.0)*(1.0*(-200.0)))*y[4]
            dy[4]=1.0*4000.0*y[3]*y[5]+(1.0*(-200.0))*y[4]
            dy[5]=((-1.0)*(1.0*4000.0))*y[3]*y[5]+((-1.0)*(1.0*(-200.0)))*y[4]
            dy[6]=1.0*2.0E7*y[4]+1.0*10.0*y[6]
            dy[7]=0.0
            dy[8]=((-1.0)*(1.0*10.0))*y[6]

            dy
        }
    }
}

//BIOMD0000000104
class T5ModelCreator  {
    fun createModel(reduction: Int) : ModelInfo {
        return ModelInfo(
            randMatrix(6 - reduction, 6, -1.0 until 1.0).MGSON())
        { y: SimpleMatrix ->
            val dy = SimpleMatrix(y)



            dy[0]=((-1.0)*(1.0*1.0))*y[0]*y[2]
            dy[1]=1.0*1.0*y[0]*y[2]+((-1.0)*((1.0*1.0)*(-1.0)))*y[1]*y[2]+((-1.0)*(1.0*1.0))*y[1]*y[5]
            dy[2]=0.0
            dy[3]=0.0
            dy[4]=((1.0*1.0)*(-1.0))*y[1]*y[2]+1.0*1.0*y[1]*y[5]
            dy[5]=0.0

            dy
        }
    }
}

//BIOMD0000000108
class T6ModelCreator  {
    fun createModel(reduction: Int) : ModelInfo {
        return ModelInfo(
            randMatrix(12 - reduction, 12, -1.0 until 1.0).MGSON())
        { y: SimpleMatrix ->
            val dy = SimpleMatrix(y)

            val k1 = 6.6E-7

            val k2 = 1.6E9

            val k3 = 1.6E9

            val k4 = 100000.0

            val k5 = 20000.0

            val k6 = 1.0

            val k7 = 3.4E7

            val k9 = 1000000.0

            val k10 = 1000.0

            val k11 = 2.5E8

            val k12 = 0.38

            val k13a = 0.0087

            val k13b = 0.0087

            val k17 = 30000.0

            val k18 = 7.0

            val k19 = 88000.0

            val HO2star = 0.0

            val Cu_I_ZnSOD = 0.0


            dy[0]=((1.0*k2)+((-1.0)*((1.0*k3)*(-1.0))))*y[0]*y[1]+((-1.0)*(1.0*k5))*y[0]*y[2]+((-1.0)*(1.0*k4))*y[0]*y[3]+((-1.0)*(1.0*k3))*y[0]*y[7]+1.0*k1*y[10]+(k10*(1.0/100.0))*1.0*y[11]
            dy[1]=(((1.0*k3)*(-1.0))+(1.0*k2))*y[0]*y[1]+1.0*k3*y[0]*y[7]+1.0*k13b*y[1]+((1.0*k13a)*(-1.0))*y[1]*y[10]+1.0*k13a*y[7]*y[10]
            dy[2]=((1.0*k3)*(-1.0))*y[0]*y[1]+((-1.0)*(1.0*k5))*y[0]*y[2]+1.0*k3*y[0]*y[7]+((-1.0)*(1.0*k6))*y[1]*y[2]+((-1.0)*(1.0*k7))*y[2]*y[8]+(1.0*k10)*(1.0/100.0)*y[10]
            dy[3]=((-1.0)*(1.0*k4))*y[0]*y[3]+((-1.0)*(1.0*k18))*y[3]+2.0*(1.0*k19)*y[3]*y[3]+1.0*k17*y[6]
            dy[4]=2.0*(1.0*k5)*y[0]*y[2]+2.0*(1.0*k6)*y[1]*y[2]+(((-1.0)*(1.0*k11))+(1.0*k9))*y[4]
            dy[5]=1.0*k4*y[0]*y[3]+1.0*k18*y[3]+1.0*k12*y[5]
            dy[6]=1.0*k18*y[3]+1.0*k11*y[4]+((-1.0)*(1.0*k17))*y[6]+(1.0*k10)*(1.0/100.0)*y[10]
            dy[7]=0.0
            dy[8]=0.0
            dy[9]=((-1.0)*(1.0*k2))*y[0]*y[1]+((-1.0)*(1.0*k13b))*y[1]+((-1.0)*(1.0*k19))*y[3]*y[3]+((-1.0)*(1.0*k9))*y[4]+((-1.0)*(1.0*k12))*y[5]+((-1.0)*((k10*(1.0/100.0))*1.0))*y[11]
            dy[10]=0.0
            dy[11]=0.0

            dy
        }
    }
}

//BIOMD0000000125
class T7ModelCreator  {
    fun createModel(reduction: Int) : ModelInfo {
        return ModelInfo(
            randMatrix(6 - reduction, 6, -1.0 until 1.0).MGSON())
        { y: SimpleMatrix ->
            val dy = SimpleMatrix(y)

            val a1 = 2.0

            val a2 = 2.0

            val b1 = 1.0

            val b2 = 1.0

            val d1 = 1.0

            val d2x = 1.0

            val d2y = 1.0


            dy[0]=1.0*d1*y[0]+1.0*a1*y[3]+1.0*b1*y[4]
            dy[1]=1.0*a2*y[0]+1.0*d2x*y[1]
            dy[2]=1.0*b2*y[0]+1.0*d2y*y[2]
            dy[3]=0.0
            dy[4]=0.0
            dy[5]=((-1.0)*(1.0*d1))*y[0]+((-1.0)*(1.0*d2x))*y[1]+((-1.0)*(1.0*d2y))*y[2]

            dy
        }
    }
}

//BIOMD0000000188
class T8ModelCreator  {
    fun createModel(reduction: Int) : ModelInfo {
        return ModelInfo(
            randMatrix(21 - reduction, 21, -1.0 until 1.0).MGSON())
        { y: SimpleMatrix ->
            val dy = SimpleMatrix(y)

            val IR = 0.0

            val ksynMdm2 = 4.95E-4

            val kdegMdm2 = 4.33E-4

            val ksynp53 = 0.006

            val kdegp53 = 8.25E-4

            val kbinMdm2p53 = 0.001155

            val krelMdm2p53 = 1.155E-5

            val ksynMdm2mRNA = 1.0E-4

            val kdegMdm2mRNA = 1.0E-4

            val kactATM = 1.0E-4

            val kdegATMMdm2 = 4.0E-4

            val kinactATM = 5.0E-4

            val kphosp53 = 5.0E-4

            val kdephosp53 = 0.5

            val kphosMdm2 = 2.0

            val kdephosMdm2 = 0.5

            val kdam = 0.08

            val krepair = 2.0E-5

            val kproteff = 1.0

            val ksynp53mRNA = 0.001

            val kdegp53mRNA = 1.0E-4


            dy[0]=((-1.0)*(kdegMdm2*kproteff))*y[0]+((-1.0)*kbinMdm2p53)*y[0]*y[1]+((-1.0)*kphosMdm2)*y[0]*y[5]+(krelMdm2p53+(kdegp53*kproteff))*y[2]+ksynMdm2*y[3]+kdephosMdm2*y[8]
            dy[1]=((-1.0)*kbinMdm2p53)*y[0]*y[1]+((-1.0)*kphosp53)*y[1]*y[5]+krelMdm2p53*y[2]+ksynp53*y[4]+kdephosp53*y[7]
            dy[2]=kbinMdm2p53*y[0]*y[1]+(((-1.0)*(kdegp53*kproteff))+((-1.0)*krelMdm2p53))*y[2]
            dy[3]=ksynMdm2mRNA*y[1]+((-1.0)*kdegMdm2mRNA)*y[3]+ksynMdm2mRNA*y[7]
            dy[4]=((-1.0)*kdegp53mRNA)*y[4]+ksynp53mRNA*y[11]
            dy[5]=((-1.0)*kinactATM)*y[5]+kactATM*y[6]*y[9]
            dy[6]=kinactATM*y[5]+((-1.0)*kactATM)*y[6]*y[9]
            dy[7]=kphosp53*y[1]*y[5]+((-1.0)*kdephosp53)*y[7]
            dy[8]=kphosMdm2*y[0]*y[5]+(((-1.0)*kdephosMdm2)+((-1.0)*kdegATMMdm2))*y[8]
            dy[9]=((-1.0)*krepair)*y[9]+kdam*IR*y[20]
            dy[10]=0.0
            dy[11]=0.0
            dy[12]=kdegp53*kproteff*y[2]
            dy[13]=ksynp53*y[4]
            dy[14]=kdegMdm2*kproteff*y[0]+kdegATMMdm2*y[8]
            dy[15]=ksynMdm2*y[3]
            dy[16]=kdegMdm2mRNA*y[3]
            dy[17]=ksynMdm2mRNA*y[1]+ksynMdm2mRNA*y[7]
            dy[18]=0.0
            dy[19]=0.0
            dy[20]=0.0

            dy
        }
    }
}

//BIOMD0000000189
class T9ModelCreator  {
    fun createModel(reduction: Int) : ModelInfo {
        return ModelInfo(
            randMatrix(19 - reduction, 19, -1.0 until 1.0).MGSON())
        { y: SimpleMatrix ->
            val dy = SimpleMatrix(y)

            val IR = 0.0

            val ksynMdm2 = 4.95E-4

            val kdegMdm2 = 4.33E-4

            val ksynp53 = 0.078

            val kdegp53 = 8.25E-4

            val kbinMdm2p53 = 0.001155

            val krelMdm2p53 = 1.155E-5

            val ksynMdm2mRNA = 1.0E-4

            val kdegMdm2mRNA = 1.0E-4

            val kbinARFMdm2 = 0.01

            val kdegARFMdm2 = 0.001

            val kdegARF = 1.0E-4

            val kactARF = 3.3E-5

            val kdam = 0.08

            val krepair = 2.0E-5

            val kproteff = 1.0


            dy[0]=((-1.0)*(kdegMdm2*kproteff))*y[0]+((-1.0)*kbinMdm2p53)*y[0]*y[1]+((-1.0)*kbinARFMdm2)*y[0]*y[4]+(krelMdm2p53+(kdegp53*kproteff))*y[2]+ksynMdm2*y[3]
            dy[1]=((-1.0)*kbinMdm2p53)*y[0]*y[1]+krelMdm2p53*y[2]+ksynp53*y[8]
            dy[2]=kbinMdm2p53*y[0]*y[1]+(((-1.0)*(kdegp53*kproteff))+((-1.0)*krelMdm2p53))*y[2]
            dy[3]=ksynMdm2mRNA*y[1]+((-1.0)*kdegMdm2mRNA)*y[3]
            dy[4]=((-1.0)*kbinARFMdm2)*y[0]*y[4]+((-1.0)*(kdegARF*kproteff))*y[4]+kdegARFMdm2*kproteff*y[5]+kactARF*y[6]
            dy[5]=kbinARFMdm2*y[0]*y[4]+((-1.0)*(kdegARFMdm2*kproteff))*y[5]
            dy[6]=((-1.0)*krepair)*y[6]+kdam*IR*y[18]
            dy[7]=0.0
            dy[8]=0.0
            dy[9]=kdegp53*kproteff*y[2]
            dy[10]=ksynp53*y[8]
            dy[11]=kdegMdm2*kproteff*y[0]+kdegARFMdm2*kproteff*y[5]
            dy[12]=ksynMdm2*y[3]
            dy[13]=kdegMdm2mRNA*y[3]
            dy[14]=ksynMdm2mRNA*y[1]
            dy[15]=kdam*IR*y[18]
            dy[16]=0.0
            dy[17]=0.0
            dy[18]=0.0

            dy
        }
    }
}