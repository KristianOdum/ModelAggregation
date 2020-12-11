
import org.ejml.simple.SimpleMatrix
import utility.MGSON
import utility.ModelInfo
import utility.randMatrix
import utility.until
import kotlin.math.pow

//BIOMD0000000010
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

//BIOMD0000000559
class T100ModelCreator  {
    fun createModel(reduction: Int) : ModelInfo {
        return ModelInfo(
                randMatrix(195 - reduction, 195, -1.0 until 1.0).MGSON())
        { y: SimpleMatrix ->
            val dy = SimpleMatrix(y)

            val lC = 8616.61

            val lN = 322686.0

            val Kd_rbp_TT = 6.0E-4

            val kon_tbp = 1.0E8

            val Kd_rbp_RT = 6.0E-4

            val kon_AT = 1.0E9

            val kon_AR = 1.0E9

            val kon_CR = 1.0E7

            val cN = 2.15E-4

            val k_R2T_C = 10000.0

            val cC = 3.17E-4

            val kon_CT = 1.0E7

            val k_R2T_N = 10000.0

            val Kd_tbp_RT = 1.0

            val KDT = 6.242E-5

            val conc_rbp = 1.0E-6

            val KBT = 9.192E-5

            val Kd_rbp_RR = 5.0E-11

            val Kd_rbp_TR = 7.0E-8

            val Kd_tbp_TT = 1.0

            val Kd_tbp_TR = 1.0

            val conc_cam = 1.0E-6

            val Kd_tbp_RR = 1.0E-9

            val conc_tbp = 1.0E-6

            val kon_rbp = 1.0E8

            val KCT = 6.242E-5

            val KAT = 9.192E-5

            val kon_DR = 1.0E7

            val kon_BR = 1.0E9

            val koff_tbp_RR = 0.1

            val k_T2R_N2 = 670413.817319951

            val k_T2R_N1 = 144.13897072379

            val koff_AT = 91920.0

            val koff_tbp_TR = 1.0E8

            val kon_BT = 1.0E9

            val koff_tbp_TT = 1.0E8

            val koff_DR = 0.1978714

            val k_R2T_C1 = 10000.0

            val k_R2T_C2 = 10000.0

            val koff_rbp_RT = 60000.0

            val koff_DT = 624.2

            val kon_DT = 1.0E7

            val koff_rbp_RR = 0.005

            val k_T2R_C = 1.16054921831207

            val koff_tbp_RT = 1.0E8

            val koff_CT = 624.2

            val koff_BT = 91920.0

            val k_T2R_C1 = 3661.03854357121

            val k_T2R_C2 = 1.15490174876063E7

            val koff_AR = 19.7628

            val koff_CR = 0.1978714

            val koff_BR = 19.7628

            val KAR = 1.97628E-8

            val koff_rbp_TR = 7.0

            val k_R2T_N1 = 10000.0

            val KCR = 1.978714E-8

            val KBR = 1.97628E-8

            val k_T2R_N = 0.0309898787056147

            val k_R2T_N2 = 10000.0

            val KDR = 1.978714E-8

            val koff_rbp_TT = 60000.0

            val cam_tbp_tot = 0.0

            val ybarN_0 = 0.0

            val ybar_rbp = 0.0

            val cam_tot = 3.3E-5

            val ybar_tot = 0.0

            val cam_tbp_bound_fraction = 0.0

            val ybar_tbp = 0.0

            val cam_0_tot = 3.3E-5

            val ybarN_tot = 0.0

            val cam_0_bound_fraction = 1.0

            val ybar_0 = 0.0

            val cam_rbp_bound_fraction = 0.0

            val ybarC_0 = 0.0

            val ybarN_rbp = 0.0

            val ybarC_rbp = 0.0

            val ybarN_tbp = 0.0

            val ybarC_tbp = 0.0

            val ybarC_tot = 0.0

            val cam_rbp_tot = 0.0


            dy[0]=((1.0*(-k_R2T_C))+(1.0*(-k_R2T_N)))*y[0]+(((((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_AR)))+((-1.0)*(1.0*kon_CR)))+((-1.0)*(1.0*kon_CR)))*y[0]*y[192]+((-1.0)*(1.0*kon_rbp))*y[0]*y[193]+((-1.0)*(1.0*kon_tbp))*y[0]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_RR*kon_rbp))))*y[1]+((-1.0)*(1.0*(-(Kd_tbp_RR*kon_tbp))))*y[2]+((-1.0)*(1.0*(-((KAT*cN)*kon_AR))))*y[3]+((-1.0)*(1.0*(-((KBT*cN)*kon_AR))))*y[6]+((-1.0)*(1.0*(-((KCT*cC)*kon_CR))))*y[9]+((-1.0)*(1.0*(-((KDT*cC)*kon_CR))))*y[12]+1.0*(k_R2T_C/lC)*y[48]+1.0*(k_R2T_N/lN)*y[96]
            dy[1]=1.0*kon_rbp*y[0]*y[193]+(1.0*(-(Kd_rbp_RR*kon_rbp)))*y[1]+(((((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_AR)))+((-1.0)*(1.0*kon_CR)))+((-1.0)*(1.0*kon_CR)))*y[1]*y[192]+((-1.0)*(1.0*(-((KAT*cN)*kon_AR))))*y[4]+((-1.0)*(1.0*(-((KBT*cN)*kon_AR))))*y[7]+((-1.0)*(1.0*(-((KCT*cC)*kon_CR))))*y[10]+((-1.0)*(1.0*(-((KDT*cC)*kon_CR))))*y[13]
            dy[2]=1.0*kon_tbp*y[0]*y[194]+(1.0*(-(Kd_tbp_RR*kon_tbp)))*y[2]+(((((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_AR)))+((-1.0)*(1.0*kon_CR)))+((-1.0)*(1.0*kon_CR)))*y[2]*y[192]+((-1.0)*(1.0*(-((KAT*cN)*kon_AR))))*y[5]+((-1.0)*(1.0*(-((KBT*cN)*kon_AR))))*y[8]+((-1.0)*(1.0*(-((KCT*cC)*kon_CR))))*y[11]+((-1.0)*(1.0*(-((KDT*cC)*kon_CR))))*y[14]
            dy[3]=1.0*kon_AR*y[0]*y[192]+(((1.0*(-k_R2T_C))+(1.0*(-k_R2T_N)))+(1.0*(-((KAT*cN)*kon_AR))))*y[3]+((((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_CR)))+((-1.0)*(1.0*kon_CR)))*y[3]*y[192]+((-1.0)*(1.0*kon_rbp))*y[3]*y[193]+((-1.0)*(1.0*kon_tbp))*y[3]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_RR*kon_rbp))))*y[4]+((-1.0)*(1.0*(-(Kd_tbp_RR*kon_tbp))))*y[5]+((-1.0)*(1.0*(-((KBT*cN)*kon_AR))))*y[15]+((-1.0)*(1.0*(-((KCT*cC)*kon_CR))))*y[18]+((-1.0)*(1.0*(-((KDT*cC)*kon_CR))))*y[21]+1.0*(k_R2T_C/lC)*y[51]+1.0*(k_R2T_N/(lN*cN))*y[99]
            dy[4]=1.0*kon_AR*y[1]*y[192]+1.0*kon_rbp*y[3]*y[193]+((1.0*(-((KAT*cN)*kon_AR)))+(1.0*(-(Kd_rbp_RR*kon_rbp))))*y[4]+((((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_CR)))+((-1.0)*(1.0*kon_CR)))*y[4]*y[192]+((-1.0)*(1.0*(-((KBT*cN)*kon_AR))))*y[16]+((-1.0)*(1.0*(-((KCT*cC)*kon_CR))))*y[19]+((-1.0)*(1.0*(-((KDT*cC)*kon_CR))))*y[22]
            dy[5]=1.0*kon_AR*y[2]*y[192]+1.0*kon_tbp*y[3]*y[194]+((1.0*(-((KAT*cN)*kon_AR)))+(1.0*(-(Kd_tbp_RR*kon_tbp))))*y[5]+((((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_CR)))+((-1.0)*(1.0*kon_CR)))*y[5]*y[192]+((-1.0)*(1.0*(-((KBT*cN)*kon_AR))))*y[17]+((-1.0)*(1.0*(-((KCT*cC)*kon_CR))))*y[20]+((-1.0)*(1.0*(-((KDT*cC)*kon_CR))))*y[23]
            dy[6]=1.0*kon_AR*y[0]*y[192]+(((1.0*(-k_R2T_C))+(1.0*(-k_R2T_N)))+(1.0*(-((KBT*cN)*kon_AR))))*y[6]+((((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_CR)))+((-1.0)*(1.0*kon_CR)))*y[6]*y[192]+((-1.0)*(1.0*kon_rbp))*y[6]*y[193]+((-1.0)*(1.0*kon_tbp))*y[6]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_RR*kon_rbp))))*y[7]+((-1.0)*(1.0*(-(Kd_tbp_RR*kon_tbp))))*y[8]+((-1.0)*(1.0*(-((KAT*cN)*kon_AR))))*y[15]+((-1.0)*(1.0*(-((KCT*cC)*kon_CR))))*y[24]+((-1.0)*(1.0*(-((KDT*cC)*kon_CR))))*y[27]+1.0*(k_R2T_C/lC)*y[54]+1.0*(k_R2T_N/(lN*cN))*y[102]
            dy[7]=1.0*kon_AR*y[1]*y[192]+1.0*kon_rbp*y[6]*y[193]+((1.0*(-((KBT*cN)*kon_AR)))+(1.0*(-(Kd_rbp_RR*kon_rbp))))*y[7]+((((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_CR)))+((-1.0)*(1.0*kon_CR)))*y[7]*y[192]+((-1.0)*(1.0*(-((KAT*cN)*kon_AR))))*y[16]+((-1.0)*(1.0*(-((KCT*cC)*kon_CR))))*y[25]+((-1.0)*(1.0*(-((KDT*cC)*kon_CR))))*y[28]
            dy[8]=1.0*kon_AR*y[2]*y[192]+1.0*kon_tbp*y[6]*y[194]+((1.0*(-((KBT*cN)*kon_AR)))+(1.0*(-(Kd_tbp_RR*kon_tbp))))*y[8]+((((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_CR)))+((-1.0)*(1.0*kon_CR)))*y[8]*y[192]+((-1.0)*(1.0*(-((KAT*cN)*kon_AR))))*y[17]+((-1.0)*(1.0*(-((KCT*cC)*kon_CR))))*y[26]+((-1.0)*(1.0*(-((KDT*cC)*kon_CR))))*y[29]
            dy[9]=1.0*kon_CR*y[0]*y[192]+(((1.0*(-k_R2T_C))+(1.0*(-k_R2T_N)))+(1.0*(-((KCT*cC)*kon_CR))))*y[9]+((((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_AR)))+((-1.0)*(1.0*kon_CR)))*y[9]*y[192]+((-1.0)*(1.0*kon_rbp))*y[9]*y[193]+((-1.0)*(1.0*kon_tbp))*y[9]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_RR*kon_rbp))))*y[10]+((-1.0)*(1.0*(-(Kd_tbp_RR*kon_tbp))))*y[11]+((-1.0)*(1.0*(-((KAT*cN)*kon_AR))))*y[18]+((-1.0)*(1.0*(-((KBT*cN)*kon_AR))))*y[24]+((-1.0)*(1.0*(-((KDT*cC)*kon_CR))))*y[30]+1.0*(k_R2T_C/(lC*cC))*y[57]+1.0*(k_R2T_N/lN)*y[105]
            dy[10]=1.0*kon_CR*y[1]*y[192]+1.0*kon_rbp*y[9]*y[193]+((1.0*(-((KCT*cC)*kon_CR)))+(1.0*(-(Kd_rbp_RR*kon_rbp))))*y[10]+((((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_AR)))+((-1.0)*(1.0*kon_CR)))*y[10]*y[192]+((-1.0)*(1.0*(-((KAT*cN)*kon_AR))))*y[19]+((-1.0)*(1.0*(-((KBT*cN)*kon_AR))))*y[25]+((-1.0)*(1.0*(-((KDT*cC)*kon_CR))))*y[31]
            dy[11]=1.0*kon_CR*y[2]*y[192]+1.0*kon_tbp*y[9]*y[194]+((1.0*(-((KCT*cC)*kon_CR)))+(1.0*(-(Kd_tbp_RR*kon_tbp))))*y[11]+((((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_AR)))+((-1.0)*(1.0*kon_CR)))*y[11]*y[192]+((-1.0)*(1.0*(-((KAT*cN)*kon_AR))))*y[20]+((-1.0)*(1.0*(-((KBT*cN)*kon_AR))))*y[26]+((-1.0)*(1.0*(-((KDT*cC)*kon_CR))))*y[32]
            dy[12]=1.0*kon_CR*y[0]*y[192]+(((1.0*(-k_R2T_C))+(1.0*(-k_R2T_N)))+(1.0*(-((KDT*cC)*kon_CR))))*y[12]+((((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_AR)))+((-1.0)*(1.0*kon_CR)))*y[12]*y[192]+((-1.0)*(1.0*kon_rbp))*y[12]*y[193]+((-1.0)*(1.0*kon_tbp))*y[12]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_RR*kon_rbp))))*y[13]+((-1.0)*(1.0*(-(Kd_tbp_RR*kon_tbp))))*y[14]+((-1.0)*(1.0*(-((KAT*cN)*kon_AR))))*y[21]+((-1.0)*(1.0*(-((KBT*cN)*kon_AR))))*y[27]+((-1.0)*(1.0*(-((KCT*cC)*kon_CR))))*y[30]+1.0*(k_R2T_C/(lC*cC))*y[60]+1.0*(k_R2T_N/lN)*y[108]
            dy[13]=1.0*kon_CR*y[1]*y[192]+1.0*kon_rbp*y[12]*y[193]+((1.0*(-((KDT*cC)*kon_CR)))+(1.0*(-(Kd_rbp_RR*kon_rbp))))*y[13]+((((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_AR)))+((-1.0)*(1.0*kon_CR)))*y[13]*y[192]+((-1.0)*(1.0*(-((KAT*cN)*kon_AR))))*y[22]+((-1.0)*(1.0*(-((KBT*cN)*kon_AR))))*y[28]+((-1.0)*(1.0*(-((KCT*cC)*kon_CR))))*y[31]
            dy[14]=1.0*kon_CR*y[2]*y[192]+1.0*kon_tbp*y[12]*y[194]+((1.0*(-((KDT*cC)*kon_CR)))+(1.0*(-(Kd_tbp_RR*kon_tbp))))*y[14]+((((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_AR)))+((-1.0)*(1.0*kon_CR)))*y[14]*y[192]+((-1.0)*(1.0*(-((KAT*cN)*kon_AR))))*y[23]+((-1.0)*(1.0*(-((KBT*cN)*kon_AR))))*y[29]+((-1.0)*(1.0*(-((KCT*cC)*kon_CR))))*y[32]
            dy[15]=1.0*kon_AR*y[3]*y[192]+1.0*kon_AR*y[6]*y[192]+((((1.0*(-k_R2T_C))+(1.0*(-k_R2T_N)))+(1.0*(-((KAT*cN)*kon_AR))))+(1.0*(-((KBT*cN)*kon_AR))))*y[15]+(((-1.0)*(1.0*kon_CR))+((-1.0)*(1.0*kon_CR)))*y[15]*y[192]+((-1.0)*(1.0*kon_rbp))*y[15]*y[193]+((-1.0)*(1.0*kon_tbp))*y[15]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_RR*kon_rbp))))*y[16]+((-1.0)*(1.0*(-(Kd_tbp_RR*kon_tbp))))*y[17]+((-1.0)*(1.0*(-((KCT*cC)*kon_CR))))*y[33]+((-1.0)*(1.0*(-((KDT*cC)*kon_CR))))*y[36]+1.0*(k_R2T_C/lC)*y[63]+1.0*(k_R2T_N/((lN*cN)*cN))*y[111]
            dy[16]=1.0*kon_AR*y[4]*y[192]+1.0*kon_AR*y[7]*y[192]+1.0*kon_rbp*y[15]*y[193]+(((1.0*(-((KAT*cN)*kon_AR)))+(1.0*(-((KBT*cN)*kon_AR))))+(1.0*(-(Kd_rbp_RR*kon_rbp))))*y[16]+(((-1.0)*(1.0*kon_CR))+((-1.0)*(1.0*kon_CR)))*y[16]*y[192]+((-1.0)*(1.0*(-((KCT*cC)*kon_CR))))*y[34]+((-1.0)*(1.0*(-((KDT*cC)*kon_CR))))*y[37]
            dy[17]=1.0*kon_AR*y[5]*y[192]+1.0*kon_AR*y[8]*y[192]+1.0*kon_tbp*y[15]*y[194]+(((1.0*(-((KAT*cN)*kon_AR)))+(1.0*(-((KBT*cN)*kon_AR))))+(1.0*(-(Kd_tbp_RR*kon_tbp))))*y[17]+(((-1.0)*(1.0*kon_CR))+((-1.0)*(1.0*kon_CR)))*y[17]*y[192]+((-1.0)*(1.0*(-((KCT*cC)*kon_CR))))*y[35]+((-1.0)*(1.0*(-((KDT*cC)*kon_CR))))*y[38]
            dy[18]=1.0*kon_CR*y[3]*y[192]+1.0*kon_AR*y[9]*y[192]+((((1.0*(-k_R2T_C))+(1.0*(-k_R2T_N)))+(1.0*(-((KAT*cN)*kon_AR))))+(1.0*(-((KCT*cC)*kon_CR))))*y[18]+(((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_CR)))*y[18]*y[192]+((-1.0)*(1.0*kon_rbp))*y[18]*y[193]+((-1.0)*(1.0*kon_tbp))*y[18]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_RR*kon_rbp))))*y[19]+((-1.0)*(1.0*(-(Kd_tbp_RR*kon_tbp))))*y[20]+((-1.0)*(1.0*(-((KBT*cN)*kon_AR))))*y[33]+((-1.0)*(1.0*(-((KDT*cC)*kon_CR))))*y[39]+1.0*(k_R2T_C/(lC*cC))*y[66]+1.0*(k_R2T_N/(lN*cN))*y[114]
            dy[19]=1.0*kon_CR*y[4]*y[192]+1.0*kon_AR*y[10]*y[192]+1.0*kon_rbp*y[18]*y[193]+(((1.0*(-((KAT*cN)*kon_AR)))+(1.0*(-((KCT*cC)*kon_CR))))+(1.0*(-(Kd_rbp_RR*kon_rbp))))*y[19]+(((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_CR)))*y[19]*y[192]+((-1.0)*(1.0*(-((KBT*cN)*kon_AR))))*y[34]+((-1.0)*(1.0*(-((KDT*cC)*kon_CR))))*y[40]
            dy[20]=1.0*kon_CR*y[5]*y[192]+1.0*kon_AR*y[11]*y[192]+1.0*kon_tbp*y[18]*y[194]+(((1.0*(-((KAT*cN)*kon_AR)))+(1.0*(-((KCT*cC)*kon_CR))))+(1.0*(-(Kd_tbp_RR*kon_tbp))))*y[20]+(((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_CR)))*y[20]*y[192]+((-1.0)*(1.0*(-((KBT*cN)*kon_AR))))*y[35]+((-1.0)*(1.0*(-((KDT*cC)*kon_CR))))*y[41]
            dy[21]=1.0*kon_CR*y[3]*y[192]+1.0*kon_AR*y[12]*y[192]+((((1.0*(-k_R2T_C))+(1.0*(-k_R2T_N)))+(1.0*(-((KAT*cN)*kon_AR))))+(1.0*(-((KDT*cC)*kon_CR))))*y[21]+(((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_CR)))*y[21]*y[192]+((-1.0)*(1.0*kon_rbp))*y[21]*y[193]+((-1.0)*(1.0*kon_tbp))*y[21]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_RR*kon_rbp))))*y[22]+((-1.0)*(1.0*(-(Kd_tbp_RR*kon_tbp))))*y[23]+((-1.0)*(1.0*(-((KBT*cN)*kon_AR))))*y[36]+((-1.0)*(1.0*(-((KCT*cC)*kon_CR))))*y[39]+1.0*(k_R2T_C/(lC*cC))*y[69]+1.0*(k_R2T_N/(lN*cN))*y[117]
            dy[22]=1.0*kon_CR*y[4]*y[192]+1.0*kon_AR*y[13]*y[192]+1.0*kon_rbp*y[21]*y[193]+(((1.0*(-((KAT*cN)*kon_AR)))+(1.0*(-((KDT*cC)*kon_CR))))+(1.0*(-(Kd_rbp_RR*kon_rbp))))*y[22]+(((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_CR)))*y[22]*y[192]+((-1.0)*(1.0*(-((KBT*cN)*kon_AR))))*y[37]+((-1.0)*(1.0*(-((KCT*cC)*kon_CR))))*y[40]
            dy[23]=1.0*kon_CR*y[5]*y[192]+1.0*kon_AR*y[14]*y[192]+1.0*kon_tbp*y[21]*y[194]+(((1.0*(-((KAT*cN)*kon_AR)))+(1.0*(-((KDT*cC)*kon_CR))))+(1.0*(-(Kd_tbp_RR*kon_tbp))))*y[23]+(((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_CR)))*y[23]*y[192]+((-1.0)*(1.0*(-((KBT*cN)*kon_AR))))*y[38]+((-1.0)*(1.0*(-((KCT*cC)*kon_CR))))*y[41]
            dy[24]=1.0*kon_CR*y[6]*y[192]+1.0*kon_AR*y[9]*y[192]+((((1.0*(-k_R2T_C))+(1.0*(-k_R2T_N)))+(1.0*(-((KBT*cN)*kon_AR))))+(1.0*(-((KCT*cC)*kon_CR))))*y[24]+(((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_CR)))*y[24]*y[192]+((-1.0)*(1.0*kon_rbp))*y[24]*y[193]+((-1.0)*(1.0*kon_tbp))*y[24]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_RR*kon_rbp))))*y[25]+((-1.0)*(1.0*(-(Kd_tbp_RR*kon_tbp))))*y[26]+((-1.0)*(1.0*(-((KAT*cN)*kon_AR))))*y[33]+((-1.0)*(1.0*(-((KDT*cC)*kon_CR))))*y[42]+1.0*(k_R2T_C/(lC*cC))*y[72]+1.0*(k_R2T_N/(lN*cN))*y[120]
            dy[25]=1.0*kon_CR*y[7]*y[192]+1.0*kon_AR*y[10]*y[192]+1.0*kon_rbp*y[24]*y[193]+(((1.0*(-((KBT*cN)*kon_AR)))+(1.0*(-((KCT*cC)*kon_CR))))+(1.0*(-(Kd_rbp_RR*kon_rbp))))*y[25]+(((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_CR)))*y[25]*y[192]+((-1.0)*(1.0*(-((KAT*cN)*kon_AR))))*y[34]+((-1.0)*(1.0*(-((KDT*cC)*kon_CR))))*y[43]
            dy[26]=1.0*kon_CR*y[8]*y[192]+1.0*kon_AR*y[11]*y[192]+1.0*kon_tbp*y[24]*y[194]+(((1.0*(-((KBT*cN)*kon_AR)))+(1.0*(-((KCT*cC)*kon_CR))))+(1.0*(-(Kd_tbp_RR*kon_tbp))))*y[26]+(((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_CR)))*y[26]*y[192]+((-1.0)*(1.0*(-((KAT*cN)*kon_AR))))*y[35]+((-1.0)*(1.0*(-((KDT*cC)*kon_CR))))*y[44]
            dy[27]=1.0*kon_CR*y[6]*y[192]+1.0*kon_AR*y[12]*y[192]+((((1.0*(-k_R2T_C))+(1.0*(-k_R2T_N)))+(1.0*(-((KBT*cN)*kon_AR))))+(1.0*(-((KDT*cC)*kon_CR))))*y[27]+(((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_CR)))*y[27]*y[192]+((-1.0)*(1.0*kon_rbp))*y[27]*y[193]+((-1.0)*(1.0*kon_tbp))*y[27]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_RR*kon_rbp))))*y[28]+((-1.0)*(1.0*(-(Kd_tbp_RR*kon_tbp))))*y[29]+((-1.0)*(1.0*(-((KAT*cN)*kon_AR))))*y[36]+((-1.0)*(1.0*(-((KCT*cC)*kon_CR))))*y[42]+1.0*(k_R2T_C/(lC*cC))*y[75]+1.0*(k_R2T_N/(lN*cN))*y[123]
            dy[28]=1.0*kon_CR*y[7]*y[192]+1.0*kon_AR*y[13]*y[192]+1.0*kon_rbp*y[27]*y[193]+(((1.0*(-((KBT*cN)*kon_AR)))+(1.0*(-((KDT*cC)*kon_CR))))+(1.0*(-(Kd_rbp_RR*kon_rbp))))*y[28]+(((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_CR)))*y[28]*y[192]+((-1.0)*(1.0*(-((KAT*cN)*kon_AR))))*y[37]+((-1.0)*(1.0*(-((KCT*cC)*kon_CR))))*y[43]
            dy[29]=1.0*kon_CR*y[8]*y[192]+1.0*kon_AR*y[14]*y[192]+1.0*kon_tbp*y[27]*y[194]+(((1.0*(-((KBT*cN)*kon_AR)))+(1.0*(-((KDT*cC)*kon_CR))))+(1.0*(-(Kd_tbp_RR*kon_tbp))))*y[29]+(((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_CR)))*y[29]*y[192]+((-1.0)*(1.0*(-((KAT*cN)*kon_AR))))*y[38]+((-1.0)*(1.0*(-((KCT*cC)*kon_CR))))*y[44]
            dy[30]=1.0*kon_CR*y[9]*y[192]+1.0*kon_CR*y[12]*y[192]+((((1.0*(-k_R2T_C))+(1.0*(-k_R2T_N)))+(1.0*(-((KCT*cC)*kon_CR))))+(1.0*(-((KDT*cC)*kon_CR))))*y[30]+(((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_AR)))*y[30]*y[192]+((-1.0)*(1.0*kon_rbp))*y[30]*y[193]+((-1.0)*(1.0*kon_tbp))*y[30]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_RR*kon_rbp))))*y[31]+((-1.0)*(1.0*(-(Kd_tbp_RR*kon_tbp))))*y[32]+((-1.0)*(1.0*(-((KAT*cN)*kon_AR))))*y[39]+((-1.0)*(1.0*(-((KBT*cN)*kon_AR))))*y[42]+1.0*(k_R2T_C/((lC*cC)*cC))*y[78]+1.0*(k_R2T_N/lN)*y[126]
            dy[31]=1.0*kon_CR*y[10]*y[192]+1.0*kon_CR*y[13]*y[192]+1.0*kon_rbp*y[30]*y[193]+(((1.0*(-((KCT*cC)*kon_CR)))+(1.0*(-((KDT*cC)*kon_CR))))+(1.0*(-(Kd_rbp_RR*kon_rbp))))*y[31]+(((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_AR)))*y[31]*y[192]+((-1.0)*(1.0*(-((KAT*cN)*kon_AR))))*y[40]+((-1.0)*(1.0*(-((KBT*cN)*kon_AR))))*y[43]
            dy[32]=1.0*kon_CR*y[11]*y[192]+1.0*kon_CR*y[14]*y[192]+1.0*kon_tbp*y[30]*y[194]+(((1.0*(-((KCT*cC)*kon_CR)))+(1.0*(-((KDT*cC)*kon_CR))))+(1.0*(-(Kd_tbp_RR*kon_tbp))))*y[32]+(((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_AR)))*y[32]*y[192]+((-1.0)*(1.0*(-((KAT*cN)*kon_AR))))*y[41]+((-1.0)*(1.0*(-((KBT*cN)*kon_AR))))*y[44]
            dy[33]=1.0*kon_CR*y[15]*y[192]+1.0*kon_AR*y[18]*y[192]+1.0*kon_AR*y[24]*y[192]+(((((1.0*(-k_R2T_C))+(1.0*(-k_R2T_N)))+(1.0*(-((KAT*cN)*kon_AR))))+(1.0*(-((KBT*cN)*kon_AR))))+(1.0*(-((KCT*cC)*kon_CR))))*y[33]+((-1.0)*(1.0*kon_CR))*y[33]*y[192]+((-1.0)*(1.0*kon_rbp))*y[33]*y[193]+((-1.0)*(1.0*kon_tbp))*y[33]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_RR*kon_rbp))))*y[34]+((-1.0)*(1.0*(-(Kd_tbp_RR*kon_tbp))))*y[35]+((-1.0)*(1.0*(-((KDT*cC)*kon_CR))))*y[45]+1.0*(k_R2T_C/(lC*cC))*y[81]+1.0*(k_R2T_N/((lN*cN)*cN))*y[129]
            dy[34]=1.0*kon_CR*y[16]*y[192]+1.0*kon_AR*y[19]*y[192]+1.0*kon_AR*y[25]*y[192]+1.0*kon_rbp*y[33]*y[193]+((((1.0*(-((KAT*cN)*kon_AR)))+(1.0*(-((KBT*cN)*kon_AR))))+(1.0*(-((KCT*cC)*kon_CR))))+(1.0*(-(Kd_rbp_RR*kon_rbp))))*y[34]+((-1.0)*(1.0*kon_CR))*y[34]*y[192]+((-1.0)*(1.0*(-((KDT*cC)*kon_CR))))*y[46]
            dy[35]=1.0*kon_CR*y[17]*y[192]+1.0*kon_AR*y[20]*y[192]+1.0*kon_AR*y[26]*y[192]+1.0*kon_tbp*y[33]*y[194]+((((1.0*(-((KAT*cN)*kon_AR)))+(1.0*(-((KBT*cN)*kon_AR))))+(1.0*(-((KCT*cC)*kon_CR))))+(1.0*(-(Kd_tbp_RR*kon_tbp))))*y[35]+((-1.0)*(1.0*kon_CR))*y[35]*y[192]+((-1.0)*(1.0*(-((KDT*cC)*kon_CR))))*y[47]
            dy[36]=1.0*kon_CR*y[15]*y[192]+1.0*kon_AR*y[21]*y[192]+1.0*kon_AR*y[27]*y[192]+(((((1.0*(-k_R2T_C))+(1.0*(-k_R2T_N)))+(1.0*(-((KAT*cN)*kon_AR))))+(1.0*(-((KBT*cN)*kon_AR))))+(1.0*(-((KDT*cC)*kon_CR))))*y[36]+((-1.0)*(1.0*kon_CR))*y[36]*y[192]+((-1.0)*(1.0*kon_rbp))*y[36]*y[193]+((-1.0)*(1.0*kon_tbp))*y[36]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_RR*kon_rbp))))*y[37]+((-1.0)*(1.0*(-(Kd_tbp_RR*kon_tbp))))*y[38]+((-1.0)*(1.0*(-((KCT*cC)*kon_CR))))*y[45]+1.0*(k_R2T_C/(lC*cC))*y[84]+1.0*(k_R2T_N/((lN*cN)*cN))*y[132]
            dy[37]=1.0*kon_CR*y[16]*y[192]+1.0*kon_AR*y[22]*y[192]+1.0*kon_AR*y[28]*y[192]+1.0*kon_rbp*y[36]*y[193]+((((1.0*(-((KAT*cN)*kon_AR)))+(1.0*(-((KBT*cN)*kon_AR))))+(1.0*(-((KDT*cC)*kon_CR))))+(1.0*(-(Kd_rbp_RR*kon_rbp))))*y[37]+((-1.0)*(1.0*kon_CR))*y[37]*y[192]+((-1.0)*(1.0*(-((KCT*cC)*kon_CR))))*y[46]
            dy[38]=1.0*kon_CR*y[17]*y[192]+1.0*kon_AR*y[23]*y[192]+1.0*kon_AR*y[29]*y[192]+1.0*kon_tbp*y[36]*y[194]+((((1.0*(-((KAT*cN)*kon_AR)))+(1.0*(-((KBT*cN)*kon_AR))))+(1.0*(-((KDT*cC)*kon_CR))))+(1.0*(-(Kd_tbp_RR*kon_tbp))))*y[38]+((-1.0)*(1.0*kon_CR))*y[38]*y[192]+((-1.0)*(1.0*(-((KCT*cC)*kon_CR))))*y[47]
            dy[39]=1.0*kon_CR*y[18]*y[192]+1.0*kon_CR*y[21]*y[192]+1.0*kon_AR*y[30]*y[192]+(((((1.0*(-k_R2T_C))+(1.0*(-k_R2T_N)))+(1.0*(-((KAT*cN)*kon_AR))))+(1.0*(-((KCT*cC)*kon_CR))))+(1.0*(-((KDT*cC)*kon_CR))))*y[39]+((-1.0)*(1.0*kon_AR))*y[39]*y[192]+((-1.0)*(1.0*kon_rbp))*y[39]*y[193]+((-1.0)*(1.0*kon_tbp))*y[39]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_RR*kon_rbp))))*y[40]+((-1.0)*(1.0*(-(Kd_tbp_RR*kon_tbp))))*y[41]+((-1.0)*(1.0*(-((KBT*cN)*kon_AR))))*y[45]+1.0*(k_R2T_C/((lC*cC)*cC))*y[87]+1.0*(k_R2T_N/(lN*cN))*y[135]
            dy[40]=1.0*kon_CR*y[19]*y[192]+1.0*kon_CR*y[22]*y[192]+1.0*kon_AR*y[31]*y[192]+1.0*kon_rbp*y[39]*y[193]+((((1.0*(-((KAT*cN)*kon_AR)))+(1.0*(-((KCT*cC)*kon_CR))))+(1.0*(-((KDT*cC)*kon_CR))))+(1.0*(-(Kd_rbp_RR*kon_rbp))))*y[40]+((-1.0)*(1.0*kon_AR))*y[40]*y[192]+((-1.0)*(1.0*(-((KBT*cN)*kon_AR))))*y[46]
            dy[41]=1.0*kon_CR*y[20]*y[192]+1.0*kon_CR*y[23]*y[192]+1.0*kon_AR*y[32]*y[192]+1.0*kon_tbp*y[39]*y[194]+((((1.0*(-((KAT*cN)*kon_AR)))+(1.0*(-((KCT*cC)*kon_CR))))+(1.0*(-((KDT*cC)*kon_CR))))+(1.0*(-(Kd_tbp_RR*kon_tbp))))*y[41]+((-1.0)*(1.0*kon_AR))*y[41]*y[192]+((-1.0)*(1.0*(-((KBT*cN)*kon_AR))))*y[47]
            dy[42]=1.0*kon_CR*y[24]*y[192]+1.0*kon_CR*y[27]*y[192]+1.0*kon_AR*y[30]*y[192]+(((((1.0*(-k_R2T_C))+(1.0*(-k_R2T_N)))+(1.0*(-((KBT*cN)*kon_AR))))+(1.0*(-((KCT*cC)*kon_CR))))+(1.0*(-((KDT*cC)*kon_CR))))*y[42]+((-1.0)*(1.0*kon_AR))*y[42]*y[192]+((-1.0)*(1.0*kon_rbp))*y[42]*y[193]+((-1.0)*(1.0*kon_tbp))*y[42]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_RR*kon_rbp))))*y[43]+((-1.0)*(1.0*(-(Kd_tbp_RR*kon_tbp))))*y[44]+((-1.0)*(1.0*(-((KAT*cN)*kon_AR))))*y[45]+1.0*(k_R2T_C/((lC*cC)*cC))*y[90]+1.0*(k_R2T_N/(lN*cN))*y[138]
            dy[43]=1.0*kon_CR*y[25]*y[192]+1.0*kon_CR*y[28]*y[192]+1.0*kon_AR*y[31]*y[192]+1.0*kon_rbp*y[42]*y[193]+((((1.0*(-((KBT*cN)*kon_AR)))+(1.0*(-((KCT*cC)*kon_CR))))+(1.0*(-((KDT*cC)*kon_CR))))+(1.0*(-(Kd_rbp_RR*kon_rbp))))*y[43]+((-1.0)*(1.0*kon_AR))*y[43]*y[192]+((-1.0)*(1.0*(-((KAT*cN)*kon_AR))))*y[46]
            dy[44]=1.0*kon_CR*y[26]*y[192]+1.0*kon_CR*y[29]*y[192]+1.0*kon_AR*y[32]*y[192]+1.0*kon_tbp*y[42]*y[194]+((((1.0*(-((KBT*cN)*kon_AR)))+(1.0*(-((KCT*cC)*kon_CR))))+(1.0*(-((KDT*cC)*kon_CR))))+(1.0*(-(Kd_tbp_RR*kon_tbp))))*y[44]+((-1.0)*(1.0*kon_AR))*y[44]*y[192]+((-1.0)*(1.0*(-((KAT*cN)*kon_AR))))*y[47]
            dy[45]=1.0*kon_CR*y[33]*y[192]+1.0*kon_CR*y[36]*y[192]+1.0*kon_AR*y[39]*y[192]+1.0*kon_AR*y[42]*y[192]+((((((1.0*(-k_R2T_C))+(1.0*(-k_R2T_N)))+(1.0*(-((KAT*cN)*kon_AR))))+(1.0*(-((KBT*cN)*kon_AR))))+(1.0*(-((KCT*cC)*kon_CR))))+(1.0*(-((KDT*cC)*kon_CR))))*y[45]+((-1.0)*(1.0*kon_rbp))*y[45]*y[193]+((-1.0)*(1.0*kon_tbp))*y[45]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_RR*kon_rbp))))*y[46]+((-1.0)*(1.0*(-(Kd_tbp_RR*kon_tbp))))*y[47]+1.0*(k_R2T_C/((lC*cC)*cC))*y[93]+1.0*(k_R2T_N/((lN*cN)*cN))*y[141]
            dy[46]=1.0*kon_CR*y[34]*y[192]+1.0*kon_CR*y[37]*y[192]+1.0*kon_AR*y[40]*y[192]+1.0*kon_AR*y[43]*y[192]+1.0*kon_rbp*y[45]*y[193]+(((((1.0*(-((KAT*cN)*kon_AR)))+(1.0*(-((KBT*cN)*kon_AR))))+(1.0*(-((KCT*cC)*kon_CR))))+(1.0*(-((KDT*cC)*kon_CR))))+(1.0*(-(Kd_rbp_RR*kon_rbp))))*y[46]
            dy[47]=1.0*kon_CR*y[35]*y[192]+1.0*kon_CR*y[38]*y[192]+1.0*kon_AR*y[41]*y[192]+1.0*kon_AR*y[44]*y[192]+1.0*kon_tbp*y[45]*y[194]+(((((1.0*(-((KAT*cN)*kon_AR)))+(1.0*(-((KBT*cN)*kon_AR))))+(1.0*(-((KCT*cC)*kon_CR))))+(1.0*(-((KDT*cC)*kon_CR))))+(1.0*(-(Kd_tbp_RR*kon_tbp))))*y[47]
            dy[48]=((-1.0)*(1.0*(-k_R2T_C)))*y[0]+((1.0*(-k_R2T_N))+((-1.0)*(1.0*(k_R2T_C/lC))))*y[48]+(((((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_AR)))+((-1.0)*(1.0*kon_CT)))+((-1.0)*(1.0*kon_CT)))*y[48]*y[192]+((-1.0)*(1.0*kon_rbp))*y[48]*y[193]+((-1.0)*(1.0*kon_tbp))*y[48]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_RT*kon_rbp))))*y[49]+((-1.0)*(1.0*(-(Kd_tbp_RT*kon_tbp))))*y[50]+((-1.0)*(1.0*(-((KAT*cN)*kon_AR))))*y[51]+((-1.0)*(1.0*(-((KBT*cN)*kon_AR))))*y[54]+((-1.0)*(1.0*(-(KCT*kon_CT))))*y[57]+((-1.0)*(1.0*(-(KDT*kon_CT))))*y[60]+1.0*(k_R2T_N/lN)*y[144]
            dy[49]=1.0*kon_rbp*y[48]*y[193]+(1.0*(-(Kd_rbp_RT*kon_rbp)))*y[49]+(((((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_AR)))+((-1.0)*(1.0*kon_CT)))+((-1.0)*(1.0*kon_CT)))*y[49]*y[192]+((-1.0)*(1.0*(-((KAT*cN)*kon_AR))))*y[52]+((-1.0)*(1.0*(-((KBT*cN)*kon_AR))))*y[55]+((-1.0)*(1.0*(-(KCT*kon_CT))))*y[58]+((-1.0)*(1.0*(-(KDT*kon_CT))))*y[61]
            dy[50]=1.0*kon_tbp*y[48]*y[194]+(1.0*(-(Kd_tbp_RT*kon_tbp)))*y[50]+(((((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_AR)))+((-1.0)*(1.0*kon_CT)))+((-1.0)*(1.0*kon_CT)))*y[50]*y[192]+((-1.0)*(1.0*(-((KAT*cN)*kon_AR))))*y[53]+((-1.0)*(1.0*(-((KBT*cN)*kon_AR))))*y[56]+((-1.0)*(1.0*(-(KCT*kon_CT))))*y[59]+((-1.0)*(1.0*(-(KDT*kon_CT))))*y[62]
            dy[51]=((-1.0)*(1.0*(-k_R2T_C)))*y[3]+1.0*kon_AR*y[48]*y[192]+(((1.0*(-k_R2T_N))+(1.0*(-((KAT*cN)*kon_AR))))+((-1.0)*(1.0*(k_R2T_C/lC))))*y[51]+((((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_CT)))+((-1.0)*(1.0*kon_CT)))*y[51]*y[192]+((-1.0)*(1.0*kon_rbp))*y[51]*y[193]+((-1.0)*(1.0*kon_tbp))*y[51]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_RT*kon_rbp))))*y[52]+((-1.0)*(1.0*(-(Kd_tbp_RT*kon_tbp))))*y[53]+((-1.0)*(1.0*(-((KBT*cN)*kon_AR))))*y[63]+((-1.0)*(1.0*(-(KCT*kon_CT))))*y[66]+((-1.0)*(1.0*(-(KDT*kon_CT))))*y[69]+1.0*(k_R2T_N/(lN*cN))*y[147]
            dy[52]=1.0*kon_AR*y[49]*y[192]+1.0*kon_rbp*y[51]*y[193]+((1.0*(-(Kd_rbp_RT*kon_rbp)))+(1.0*(-((KAT*cN)*kon_AR))))*y[52]+((((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_CT)))+((-1.0)*(1.0*kon_CT)))*y[52]*y[192]+((-1.0)*(1.0*(-((KBT*cN)*kon_AR))))*y[64]+((-1.0)*(1.0*(-(KCT*kon_CT))))*y[67]+((-1.0)*(1.0*(-(KDT*kon_CT))))*y[70]
            dy[53]=1.0*kon_AR*y[50]*y[192]+1.0*kon_tbp*y[51]*y[194]+((1.0*(-(Kd_tbp_RT*kon_tbp)))+(1.0*(-((KAT*cN)*kon_AR))))*y[53]+((((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_CT)))+((-1.0)*(1.0*kon_CT)))*y[53]*y[192]+((-1.0)*(1.0*(-((KBT*cN)*kon_AR))))*y[65]+((-1.0)*(1.0*(-(KCT*kon_CT))))*y[68]+((-1.0)*(1.0*(-(KDT*kon_CT))))*y[71]
            dy[54]=((-1.0)*(1.0*(-k_R2T_C)))*y[6]+1.0*kon_AR*y[48]*y[192]+(((1.0*(-k_R2T_N))+(1.0*(-((KBT*cN)*kon_AR))))+((-1.0)*(1.0*(k_R2T_C/lC))))*y[54]+((((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_CT)))+((-1.0)*(1.0*kon_CT)))*y[54]*y[192]+((-1.0)*(1.0*kon_rbp))*y[54]*y[193]+((-1.0)*(1.0*kon_tbp))*y[54]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_RT*kon_rbp))))*y[55]+((-1.0)*(1.0*(-(Kd_tbp_RT*kon_tbp))))*y[56]+((-1.0)*(1.0*(-((KAT*cN)*kon_AR))))*y[63]+((-1.0)*(1.0*(-(KCT*kon_CT))))*y[72]+((-1.0)*(1.0*(-(KDT*kon_CT))))*y[75]+1.0*(k_R2T_N/(lN*cN))*y[150]
            dy[55]=1.0*kon_AR*y[49]*y[192]+1.0*kon_rbp*y[54]*y[193]+((1.0*(-(Kd_rbp_RT*kon_rbp)))+(1.0*(-((KBT*cN)*kon_AR))))*y[55]+((((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_CT)))+((-1.0)*(1.0*kon_CT)))*y[55]*y[192]+((-1.0)*(1.0*(-((KAT*cN)*kon_AR))))*y[64]+((-1.0)*(1.0*(-(KCT*kon_CT))))*y[73]+((-1.0)*(1.0*(-(KDT*kon_CT))))*y[76]
            dy[56]=1.0*kon_AR*y[50]*y[192]+1.0*kon_tbp*y[54]*y[194]+((1.0*(-(Kd_tbp_RT*kon_tbp)))+(1.0*(-((KBT*cN)*kon_AR))))*y[56]+((((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_CT)))+((-1.0)*(1.0*kon_CT)))*y[56]*y[192]+((-1.0)*(1.0*(-((KAT*cN)*kon_AR))))*y[65]+((-1.0)*(1.0*(-(KCT*kon_CT))))*y[74]+((-1.0)*(1.0*(-(KDT*kon_CT))))*y[77]
            dy[57]=((-1.0)*(1.0*(-k_R2T_C)))*y[9]+1.0*kon_CT*y[48]*y[192]+(((1.0*(-k_R2T_N))+((-1.0)*(1.0*(k_R2T_C/(lC*cC)))))+(1.0*(-(KCT*kon_CT))))*y[57]+((((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_AR)))+((-1.0)*(1.0*kon_CT)))*y[57]*y[192]+((-1.0)*(1.0*kon_rbp))*y[57]*y[193]+((-1.0)*(1.0*kon_tbp))*y[57]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_RT*kon_rbp))))*y[58]+((-1.0)*(1.0*(-(Kd_tbp_RT*kon_tbp))))*y[59]+((-1.0)*(1.0*(-((KAT*cN)*kon_AR))))*y[66]+((-1.0)*(1.0*(-((KBT*cN)*kon_AR))))*y[72]+((-1.0)*(1.0*(-(KDT*kon_CT))))*y[78]+1.0*(k_R2T_N/lN)*y[153]
            dy[58]=1.0*kon_CT*y[49]*y[192]+1.0*kon_rbp*y[57]*y[193]+((1.0*(-(Kd_rbp_RT*kon_rbp)))+(1.0*(-(KCT*kon_CT))))*y[58]+((((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_AR)))+((-1.0)*(1.0*kon_CT)))*y[58]*y[192]+((-1.0)*(1.0*(-((KAT*cN)*kon_AR))))*y[67]+((-1.0)*(1.0*(-((KBT*cN)*kon_AR))))*y[73]+((-1.0)*(1.0*(-(KDT*kon_CT))))*y[79]
            dy[59]=1.0*kon_CT*y[50]*y[192]+1.0*kon_tbp*y[57]*y[194]+((1.0*(-(Kd_tbp_RT*kon_tbp)))+(1.0*(-(KCT*kon_CT))))*y[59]+((((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_AR)))+((-1.0)*(1.0*kon_CT)))*y[59]*y[192]+((-1.0)*(1.0*(-((KAT*cN)*kon_AR))))*y[68]+((-1.0)*(1.0*(-((KBT*cN)*kon_AR))))*y[74]+((-1.0)*(1.0*(-(KDT*kon_CT))))*y[80]
            dy[60]=((-1.0)*(1.0*(-k_R2T_C)))*y[12]+1.0*kon_CT*y[48]*y[192]+(((1.0*(-k_R2T_N))+((-1.0)*(1.0*(k_R2T_C/(lC*cC)))))+(1.0*(-(KDT*kon_CT))))*y[60]+((((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_AR)))+((-1.0)*(1.0*kon_CT)))*y[60]*y[192]+((-1.0)*(1.0*kon_rbp))*y[60]*y[193]+((-1.0)*(1.0*kon_tbp))*y[60]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_RT*kon_rbp))))*y[61]+((-1.0)*(1.0*(-(Kd_tbp_RT*kon_tbp))))*y[62]+((-1.0)*(1.0*(-((KAT*cN)*kon_AR))))*y[69]+((-1.0)*(1.0*(-((KBT*cN)*kon_AR))))*y[75]+((-1.0)*(1.0*(-(KCT*kon_CT))))*y[78]+1.0*(k_R2T_N/lN)*y[156]
            dy[61]=1.0*kon_CT*y[49]*y[192]+1.0*kon_rbp*y[60]*y[193]+((1.0*(-(Kd_rbp_RT*kon_rbp)))+(1.0*(-(KDT*kon_CT))))*y[61]+((((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_AR)))+((-1.0)*(1.0*kon_CT)))*y[61]*y[192]+((-1.0)*(1.0*(-((KAT*cN)*kon_AR))))*y[70]+((-1.0)*(1.0*(-((KBT*cN)*kon_AR))))*y[76]+((-1.0)*(1.0*(-(KCT*kon_CT))))*y[79]
            dy[62]=1.0*kon_CT*y[50]*y[192]+1.0*kon_tbp*y[60]*y[194]+((1.0*(-(Kd_tbp_RT*kon_tbp)))+(1.0*(-(KDT*kon_CT))))*y[62]+((((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_AR)))+((-1.0)*(1.0*kon_CT)))*y[62]*y[192]+((-1.0)*(1.0*(-((KAT*cN)*kon_AR))))*y[71]+((-1.0)*(1.0*(-((KBT*cN)*kon_AR))))*y[77]+((-1.0)*(1.0*(-(KCT*kon_CT))))*y[80]
            dy[63]=((-1.0)*(1.0*(-k_R2T_C)))*y[15]+1.0*kon_AR*y[51]*y[192]+1.0*kon_AR*y[54]*y[192]+((((1.0*(-k_R2T_N))+(1.0*(-((KAT*cN)*kon_AR))))+(1.0*(-((KBT*cN)*kon_AR))))+((-1.0)*(1.0*(k_R2T_C/lC))))*y[63]+(((-1.0)*(1.0*kon_CT))+((-1.0)*(1.0*kon_CT)))*y[63]*y[192]+((-1.0)*(1.0*kon_rbp))*y[63]*y[193]+((-1.0)*(1.0*kon_tbp))*y[63]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_RT*kon_rbp))))*y[64]+((-1.0)*(1.0*(-(Kd_tbp_RT*kon_tbp))))*y[65]+((-1.0)*(1.0*(-(KCT*kon_CT))))*y[81]+((-1.0)*(1.0*(-(KDT*kon_CT))))*y[84]+1.0*(k_R2T_N/((lN*cN)*cN))*y[159]
            dy[64]=1.0*kon_AR*y[52]*y[192]+1.0*kon_AR*y[55]*y[192]+1.0*kon_rbp*y[63]*y[193]+(((1.0*(-(Kd_rbp_RT*kon_rbp)))+(1.0*(-((KAT*cN)*kon_AR))))+(1.0*(-((KBT*cN)*kon_AR))))*y[64]+(((-1.0)*(1.0*kon_CT))+((-1.0)*(1.0*kon_CT)))*y[64]*y[192]+((-1.0)*(1.0*(-(KCT*kon_CT))))*y[82]+((-1.0)*(1.0*(-(KDT*kon_CT))))*y[85]
            dy[65]=1.0*kon_AR*y[53]*y[192]+1.0*kon_AR*y[56]*y[192]+1.0*kon_tbp*y[63]*y[194]+(((1.0*(-(Kd_tbp_RT*kon_tbp)))+(1.0*(-((KAT*cN)*kon_AR))))+(1.0*(-((KBT*cN)*kon_AR))))*y[65]+(((-1.0)*(1.0*kon_CT))+((-1.0)*(1.0*kon_CT)))*y[65]*y[192]+((-1.0)*(1.0*(-(KCT*kon_CT))))*y[83]+((-1.0)*(1.0*(-(KDT*kon_CT))))*y[86]
            dy[66]=((-1.0)*(1.0*(-k_R2T_C)))*y[18]+1.0*kon_CT*y[51]*y[192]+1.0*kon_AR*y[57]*y[192]+((((1.0*(-k_R2T_N))+((-1.0)*(1.0*(k_R2T_C/(lC*cC)))))+(1.0*(-(KCT*kon_CT))))+(1.0*(-((KAT*cN)*kon_AR))))*y[66]+(((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_CT)))*y[66]*y[192]+((-1.0)*(1.0*kon_rbp))*y[66]*y[193]+((-1.0)*(1.0*kon_tbp))*y[66]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_RT*kon_rbp))))*y[67]+((-1.0)*(1.0*(-(Kd_tbp_RT*kon_tbp))))*y[68]+((-1.0)*(1.0*(-((KBT*cN)*kon_AR))))*y[81]+((-1.0)*(1.0*(-(KDT*kon_CT))))*y[87]+1.0*(k_R2T_N/(lN*cN))*y[162]
            dy[67]=1.0*kon_CT*y[52]*y[192]+1.0*kon_AR*y[58]*y[192]+1.0*kon_rbp*y[66]*y[193]+(((1.0*(-(Kd_rbp_RT*kon_rbp)))+(1.0*(-(KCT*kon_CT))))+(1.0*(-((KAT*cN)*kon_AR))))*y[67]+(((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_CT)))*y[67]*y[192]+((-1.0)*(1.0*(-((KBT*cN)*kon_AR))))*y[82]+((-1.0)*(1.0*(-(KDT*kon_CT))))*y[88]
            dy[68]=1.0*kon_CT*y[53]*y[192]+1.0*kon_AR*y[59]*y[192]+1.0*kon_tbp*y[66]*y[194]+(((1.0*(-(Kd_tbp_RT*kon_tbp)))+(1.0*(-(KCT*kon_CT))))+(1.0*(-((KAT*cN)*kon_AR))))*y[68]+(((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_CT)))*y[68]*y[192]+((-1.0)*(1.0*(-((KBT*cN)*kon_AR))))*y[83]+((-1.0)*(1.0*(-(KDT*kon_CT))))*y[89]
            dy[69]=((-1.0)*(1.0*(-k_R2T_C)))*y[21]+1.0*kon_CT*y[51]*y[192]+1.0*kon_AR*y[60]*y[192]+((((1.0*(-k_R2T_N))+((-1.0)*(1.0*(k_R2T_C/(lC*cC)))))+(1.0*(-(KDT*kon_CT))))+(1.0*(-((KAT*cN)*kon_AR))))*y[69]+(((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_CT)))*y[69]*y[192]+((-1.0)*(1.0*kon_rbp))*y[69]*y[193]+((-1.0)*(1.0*kon_tbp))*y[69]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_RT*kon_rbp))))*y[70]+((-1.0)*(1.0*(-(Kd_tbp_RT*kon_tbp))))*y[71]+((-1.0)*(1.0*(-((KBT*cN)*kon_AR))))*y[84]+((-1.0)*(1.0*(-(KCT*kon_CT))))*y[87]+1.0*(k_R2T_N/(lN*cN))*y[165]
            dy[70]=1.0*kon_CT*y[52]*y[192]+1.0*kon_AR*y[61]*y[192]+1.0*kon_rbp*y[69]*y[193]+(((1.0*(-(Kd_rbp_RT*kon_rbp)))+(1.0*(-(KDT*kon_CT))))+(1.0*(-((KAT*cN)*kon_AR))))*y[70]+(((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_CT)))*y[70]*y[192]+((-1.0)*(1.0*(-((KBT*cN)*kon_AR))))*y[85]+((-1.0)*(1.0*(-(KCT*kon_CT))))*y[88]
            dy[71]=1.0*kon_CT*y[53]*y[192]+1.0*kon_AR*y[62]*y[192]+1.0*kon_tbp*y[69]*y[194]+(((1.0*(-(Kd_tbp_RT*kon_tbp)))+(1.0*(-(KDT*kon_CT))))+(1.0*(-((KAT*cN)*kon_AR))))*y[71]+(((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_CT)))*y[71]*y[192]+((-1.0)*(1.0*(-((KBT*cN)*kon_AR))))*y[86]+((-1.0)*(1.0*(-(KCT*kon_CT))))*y[89]
            dy[72]=((-1.0)*(1.0*(-k_R2T_C)))*y[24]+1.0*kon_CT*y[54]*y[192]+1.0*kon_AR*y[57]*y[192]+((((1.0*(-k_R2T_N))+((-1.0)*(1.0*(k_R2T_C/(lC*cC)))))+(1.0*(-(KCT*kon_CT))))+(1.0*(-((KBT*cN)*kon_AR))))*y[72]+(((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_CT)))*y[72]*y[192]+((-1.0)*(1.0*kon_rbp))*y[72]*y[193]+((-1.0)*(1.0*kon_tbp))*y[72]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_RT*kon_rbp))))*y[73]+((-1.0)*(1.0*(-(Kd_tbp_RT*kon_tbp))))*y[74]+((-1.0)*(1.0*(-((KAT*cN)*kon_AR))))*y[81]+((-1.0)*(1.0*(-(KDT*kon_CT))))*y[90]+1.0*(k_R2T_N/(lN*cN))*y[168]
            dy[73]=1.0*kon_CT*y[55]*y[192]+1.0*kon_AR*y[58]*y[192]+1.0*kon_rbp*y[72]*y[193]+(((1.0*(-(Kd_rbp_RT*kon_rbp)))+(1.0*(-(KCT*kon_CT))))+(1.0*(-((KBT*cN)*kon_AR))))*y[73]+(((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_CT)))*y[73]*y[192]+((-1.0)*(1.0*(-((KAT*cN)*kon_AR))))*y[82]+((-1.0)*(1.0*(-(KDT*kon_CT))))*y[91]
            dy[74]=1.0*kon_CT*y[56]*y[192]+1.0*kon_AR*y[59]*y[192]+1.0*kon_tbp*y[72]*y[194]+(((1.0*(-(Kd_tbp_RT*kon_tbp)))+(1.0*(-(KCT*kon_CT))))+(1.0*(-((KBT*cN)*kon_AR))))*y[74]+(((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_CT)))*y[74]*y[192]+((-1.0)*(1.0*(-((KAT*cN)*kon_AR))))*y[83]+((-1.0)*(1.0*(-(KDT*kon_CT))))*y[92]
            dy[75]=((-1.0)*(1.0*(-k_R2T_C)))*y[27]+1.0*kon_CT*y[54]*y[192]+1.0*kon_AR*y[60]*y[192]+((((1.0*(-k_R2T_N))+((-1.0)*(1.0*(k_R2T_C/(lC*cC)))))+(1.0*(-(KDT*kon_CT))))+(1.0*(-((KBT*cN)*kon_AR))))*y[75]+(((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_CT)))*y[75]*y[192]+((-1.0)*(1.0*kon_rbp))*y[75]*y[193]+((-1.0)*(1.0*kon_tbp))*y[75]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_RT*kon_rbp))))*y[76]+((-1.0)*(1.0*(-(Kd_tbp_RT*kon_tbp))))*y[77]+((-1.0)*(1.0*(-((KAT*cN)*kon_AR))))*y[84]+((-1.0)*(1.0*(-(KCT*kon_CT))))*y[90]+1.0*(k_R2T_N/(lN*cN))*y[171]
            dy[76]=1.0*kon_CT*y[55]*y[192]+1.0*kon_AR*y[61]*y[192]+1.0*kon_rbp*y[75]*y[193]+(((1.0*(-(Kd_rbp_RT*kon_rbp)))+(1.0*(-(KDT*kon_CT))))+(1.0*(-((KBT*cN)*kon_AR))))*y[76]+(((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_CT)))*y[76]*y[192]+((-1.0)*(1.0*(-((KAT*cN)*kon_AR))))*y[85]+((-1.0)*(1.0*(-(KCT*kon_CT))))*y[91]
            dy[77]=1.0*kon_CT*y[56]*y[192]+1.0*kon_AR*y[62]*y[192]+1.0*kon_tbp*y[75]*y[194]+(((1.0*(-(Kd_tbp_RT*kon_tbp)))+(1.0*(-(KDT*kon_CT))))+(1.0*(-((KBT*cN)*kon_AR))))*y[77]+(((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_CT)))*y[77]*y[192]+((-1.0)*(1.0*(-((KAT*cN)*kon_AR))))*y[86]+((-1.0)*(1.0*(-(KCT*kon_CT))))*y[92]
            dy[78]=((-1.0)*(1.0*(-k_R2T_C)))*y[30]+1.0*kon_CT*y[57]*y[192]+1.0*kon_CT*y[60]*y[192]+(((((-1.0)*(1.0*(k_R2T_C/((lC*cC)*cC))))+(1.0*(-k_R2T_N)))+(1.0*(-(KCT*kon_CT))))+(1.0*(-(KDT*kon_CT))))*y[78]+(((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_AR)))*y[78]*y[192]+((-1.0)*(1.0*kon_rbp))*y[78]*y[193]+((-1.0)*(1.0*kon_tbp))*y[78]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_RT*kon_rbp))))*y[79]+((-1.0)*(1.0*(-(Kd_tbp_RT*kon_tbp))))*y[80]+((-1.0)*(1.0*(-((KAT*cN)*kon_AR))))*y[87]+((-1.0)*(1.0*(-((KBT*cN)*kon_AR))))*y[90]+1.0*(k_R2T_N/lN)*y[174]
            dy[79]=1.0*kon_CT*y[58]*y[192]+1.0*kon_CT*y[61]*y[192]+1.0*kon_rbp*y[78]*y[193]+(((1.0*(-(Kd_rbp_RT*kon_rbp)))+(1.0*(-(KCT*kon_CT))))+(1.0*(-(KDT*kon_CT))))*y[79]+(((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_AR)))*y[79]*y[192]+((-1.0)*(1.0*(-((KAT*cN)*kon_AR))))*y[88]+((-1.0)*(1.0*(-((KBT*cN)*kon_AR))))*y[91]
            dy[80]=1.0*kon_CT*y[59]*y[192]+1.0*kon_CT*y[62]*y[192]+1.0*kon_tbp*y[78]*y[194]+(((1.0*(-(Kd_tbp_RT*kon_tbp)))+(1.0*(-(KCT*kon_CT))))+(1.0*(-(KDT*kon_CT))))*y[80]+(((-1.0)*(1.0*kon_AR))+((-1.0)*(1.0*kon_AR)))*y[80]*y[192]+((-1.0)*(1.0*(-((KAT*cN)*kon_AR))))*y[89]+((-1.0)*(1.0*(-((KBT*cN)*kon_AR))))*y[92]
            dy[81]=((-1.0)*(1.0*(-k_R2T_C)))*y[33]+1.0*kon_CT*y[63]*y[192]+1.0*kon_AR*y[66]*y[192]+1.0*kon_AR*y[72]*y[192]+(((((1.0*(-k_R2T_N))+((-1.0)*(1.0*(k_R2T_C/(lC*cC)))))+(1.0*(-(KCT*kon_CT))))+(1.0*(-((KAT*cN)*kon_AR))))+(1.0*(-((KBT*cN)*kon_AR))))*y[81]+((-1.0)*(1.0*kon_CT))*y[81]*y[192]+((-1.0)*(1.0*kon_rbp))*y[81]*y[193]+((-1.0)*(1.0*kon_tbp))*y[81]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_RT*kon_rbp))))*y[82]+((-1.0)*(1.0*(-(Kd_tbp_RT*kon_tbp))))*y[83]+((-1.0)*(1.0*(-(KDT*kon_CT))))*y[93]+1.0*(k_R2T_N/((lN*cN)*cN))*y[177]
            dy[82]=1.0*kon_CT*y[64]*y[192]+1.0*kon_AR*y[67]*y[192]+1.0*kon_AR*y[73]*y[192]+1.0*kon_rbp*y[81]*y[193]+((((1.0*(-(Kd_rbp_RT*kon_rbp)))+(1.0*(-(KCT*kon_CT))))+(1.0*(-((KAT*cN)*kon_AR))))+(1.0*(-((KBT*cN)*kon_AR))))*y[82]+((-1.0)*(1.0*kon_CT))*y[82]*y[192]+((-1.0)*(1.0*(-(KDT*kon_CT))))*y[94]
            dy[83]=1.0*kon_CT*y[65]*y[192]+1.0*kon_AR*y[68]*y[192]+1.0*kon_AR*y[74]*y[192]+1.0*kon_tbp*y[81]*y[194]+((((1.0*(-(Kd_tbp_RT*kon_tbp)))+(1.0*(-(KCT*kon_CT))))+(1.0*(-((KAT*cN)*kon_AR))))+(1.0*(-((KBT*cN)*kon_AR))))*y[83]+((-1.0)*(1.0*kon_CT))*y[83]*y[192]+((-1.0)*(1.0*(-(KDT*kon_CT))))*y[95]
            dy[84]=((-1.0)*(1.0*(-k_R2T_C)))*y[36]+1.0*kon_CT*y[63]*y[192]+1.0*kon_AR*y[69]*y[192]+1.0*kon_AR*y[75]*y[192]+(((((1.0*(-k_R2T_N))+((-1.0)*(1.0*(k_R2T_C/(lC*cC)))))+(1.0*(-(KDT*kon_CT))))+(1.0*(-((KAT*cN)*kon_AR))))+(1.0*(-((KBT*cN)*kon_AR))))*y[84]+((-1.0)*(1.0*kon_CT))*y[84]*y[192]+((-1.0)*(1.0*kon_rbp))*y[84]*y[193]+((-1.0)*(1.0*kon_tbp))*y[84]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_RT*kon_rbp))))*y[85]+((-1.0)*(1.0*(-(Kd_tbp_RT*kon_tbp))))*y[86]+((-1.0)*(1.0*(-(KCT*kon_CT))))*y[93]+1.0*(k_R2T_N/((lN*cN)*cN))*y[180]
            dy[85]=1.0*kon_CT*y[64]*y[192]+1.0*kon_AR*y[70]*y[192]+1.0*kon_AR*y[76]*y[192]+1.0*kon_rbp*y[84]*y[193]+((((1.0*(-(Kd_rbp_RT*kon_rbp)))+(1.0*(-(KDT*kon_CT))))+(1.0*(-((KAT*cN)*kon_AR))))+(1.0*(-((KBT*cN)*kon_AR))))*y[85]+((-1.0)*(1.0*kon_CT))*y[85]*y[192]+((-1.0)*(1.0*(-(KCT*kon_CT))))*y[94]
            dy[86]=1.0*kon_CT*y[65]*y[192]+1.0*kon_AR*y[71]*y[192]+1.0*kon_AR*y[77]*y[192]+1.0*kon_tbp*y[84]*y[194]+((((1.0*(-(Kd_tbp_RT*kon_tbp)))+(1.0*(-(KDT*kon_CT))))+(1.0*(-((KAT*cN)*kon_AR))))+(1.0*(-((KBT*cN)*kon_AR))))*y[86]+((-1.0)*(1.0*kon_CT))*y[86]*y[192]+((-1.0)*(1.0*(-(KCT*kon_CT))))*y[95]
            dy[87]=((-1.0)*(1.0*(-k_R2T_C)))*y[39]+1.0*kon_CT*y[66]*y[192]+1.0*kon_CT*y[69]*y[192]+1.0*kon_AR*y[78]*y[192]+((((((-1.0)*(1.0*(k_R2T_C/((lC*cC)*cC))))+(1.0*(-k_R2T_N)))+(1.0*(-(KCT*kon_CT))))+(1.0*(-(KDT*kon_CT))))+(1.0*(-((KAT*cN)*kon_AR))))*y[87]+((-1.0)*(1.0*kon_AR))*y[87]*y[192]+((-1.0)*(1.0*kon_rbp))*y[87]*y[193]+((-1.0)*(1.0*kon_tbp))*y[87]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_RT*kon_rbp))))*y[88]+((-1.0)*(1.0*(-(Kd_tbp_RT*kon_tbp))))*y[89]+((-1.0)*(1.0*(-((KBT*cN)*kon_AR))))*y[93]+1.0*(k_R2T_N/(lN*cN))*y[183]
            dy[88]=1.0*kon_CT*y[67]*y[192]+1.0*kon_CT*y[70]*y[192]+1.0*kon_AR*y[79]*y[192]+1.0*kon_rbp*y[87]*y[193]+((((1.0*(-(Kd_rbp_RT*kon_rbp)))+(1.0*(-(KCT*kon_CT))))+(1.0*(-(KDT*kon_CT))))+(1.0*(-((KAT*cN)*kon_AR))))*y[88]+((-1.0)*(1.0*kon_AR))*y[88]*y[192]+((-1.0)*(1.0*(-((KBT*cN)*kon_AR))))*y[94]
            dy[89]=1.0*kon_CT*y[68]*y[192]+1.0*kon_CT*y[71]*y[192]+1.0*kon_AR*y[80]*y[192]+1.0*kon_tbp*y[87]*y[194]+((((1.0*(-(Kd_tbp_RT*kon_tbp)))+(1.0*(-(KCT*kon_CT))))+(1.0*(-(KDT*kon_CT))))+(1.0*(-((KAT*cN)*kon_AR))))*y[89]+((-1.0)*(1.0*kon_AR))*y[89]*y[192]+((-1.0)*(1.0*(-((KBT*cN)*kon_AR))))*y[95]
            dy[90]=((-1.0)*(1.0*(-k_R2T_C)))*y[42]+1.0*kon_CT*y[72]*y[192]+1.0*kon_CT*y[75]*y[192]+1.0*kon_AR*y[78]*y[192]+((((((-1.0)*(1.0*(k_R2T_C/((lC*cC)*cC))))+(1.0*(-k_R2T_N)))+(1.0*(-(KCT*kon_CT))))+(1.0*(-(KDT*kon_CT))))+(1.0*(-((KBT*cN)*kon_AR))))*y[90]+((-1.0)*(1.0*kon_AR))*y[90]*y[192]+((-1.0)*(1.0*kon_rbp))*y[90]*y[193]+((-1.0)*(1.0*kon_tbp))*y[90]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_RT*kon_rbp))))*y[91]+((-1.0)*(1.0*(-(Kd_tbp_RT*kon_tbp))))*y[92]+((-1.0)*(1.0*(-((KAT*cN)*kon_AR))))*y[93]+1.0*(k_R2T_N/(lN*cN))*y[186]
            dy[91]=1.0*kon_CT*y[73]*y[192]+1.0*kon_CT*y[76]*y[192]+1.0*kon_AR*y[79]*y[192]+1.0*kon_rbp*y[90]*y[193]+((((1.0*(-(Kd_rbp_RT*kon_rbp)))+(1.0*(-(KCT*kon_CT))))+(1.0*(-(KDT*kon_CT))))+(1.0*(-((KBT*cN)*kon_AR))))*y[91]+((-1.0)*(1.0*kon_AR))*y[91]*y[192]+((-1.0)*(1.0*(-((KAT*cN)*kon_AR))))*y[94]
            dy[92]=1.0*kon_CT*y[74]*y[192]+1.0*kon_CT*y[77]*y[192]+1.0*kon_AR*y[80]*y[192]+1.0*kon_tbp*y[90]*y[194]+((((1.0*(-(Kd_tbp_RT*kon_tbp)))+(1.0*(-(KCT*kon_CT))))+(1.0*(-(KDT*kon_CT))))+(1.0*(-((KBT*cN)*kon_AR))))*y[92]+((-1.0)*(1.0*kon_AR))*y[92]*y[192]+((-1.0)*(1.0*(-((KAT*cN)*kon_AR))))*y[95]
            dy[93]=((-1.0)*(1.0*(-k_R2T_C)))*y[45]+1.0*kon_CT*y[81]*y[192]+1.0*kon_CT*y[84]*y[192]+1.0*kon_AR*y[87]*y[192]+1.0*kon_AR*y[90]*y[192]+(((((((-1.0)*(1.0*(k_R2T_C/((lC*cC)*cC))))+(1.0*(-k_R2T_N)))+(1.0*(-(KCT*kon_CT))))+(1.0*(-(KDT*kon_CT))))+(1.0*(-((KAT*cN)*kon_AR))))+(1.0*(-((KBT*cN)*kon_AR))))*y[93]+((-1.0)*(1.0*kon_rbp))*y[93]*y[193]+((-1.0)*(1.0*kon_tbp))*y[93]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_RT*kon_rbp))))*y[94]+((-1.0)*(1.0*(-(Kd_tbp_RT*kon_tbp))))*y[95]+1.0*(k_R2T_N/((lN*cN)*cN))*y[189]
            dy[94]=1.0*kon_CT*y[82]*y[192]+1.0*kon_CT*y[85]*y[192]+1.0*kon_AR*y[88]*y[192]+1.0*kon_AR*y[91]*y[192]+1.0*kon_rbp*y[93]*y[193]+(((((1.0*(-(Kd_rbp_RT*kon_rbp)))+(1.0*(-(KCT*kon_CT))))+(1.0*(-(KDT*kon_CT))))+(1.0*(-((KAT*cN)*kon_AR))))+(1.0*(-((KBT*cN)*kon_AR))))*y[94]
            dy[95]=1.0*kon_CT*y[83]*y[192]+1.0*kon_CT*y[86]*y[192]+1.0*kon_AR*y[89]*y[192]+1.0*kon_AR*y[92]*y[192]+1.0*kon_tbp*y[93]*y[194]+(((((1.0*(-(Kd_tbp_RT*kon_tbp)))+(1.0*(-(KCT*kon_CT))))+(1.0*(-(KDT*kon_CT))))+(1.0*(-((KAT*cN)*kon_AR))))+(1.0*(-((KBT*cN)*kon_AR))))*y[95]
            dy[96]=((-1.0)*(1.0*(-k_R2T_N)))*y[0]+((1.0*(-k_R2T_C))+((-1.0)*(1.0*(k_R2T_N/lN))))*y[96]+(((((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_AT)))+((-1.0)*(1.0*kon_CR)))+((-1.0)*(1.0*kon_CR)))*y[96]*y[192]+((-1.0)*(1.0*kon_rbp))*y[96]*y[193]+((-1.0)*(1.0*kon_tbp))*y[96]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_TR*kon_rbp))))*y[97]+((-1.0)*(1.0*(-(Kd_tbp_TR*kon_tbp))))*y[98]+((-1.0)*(1.0*(-(KAT*kon_AT))))*y[99]+((-1.0)*(1.0*(-(KBT*kon_AT))))*y[102]+((-1.0)*(1.0*(-((KCT*cC)*kon_CR))))*y[105]+((-1.0)*(1.0*(-((KDT*cC)*kon_CR))))*y[108]+1.0*(k_R2T_C/lC)*y[144]
            dy[97]=1.0*kon_rbp*y[96]*y[193]+(1.0*(-(Kd_rbp_TR*kon_rbp)))*y[97]+(((((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_AT)))+((-1.0)*(1.0*kon_CR)))+((-1.0)*(1.0*kon_CR)))*y[97]*y[192]+((-1.0)*(1.0*(-(KAT*kon_AT))))*y[100]+((-1.0)*(1.0*(-(KBT*kon_AT))))*y[103]+((-1.0)*(1.0*(-((KCT*cC)*kon_CR))))*y[106]+((-1.0)*(1.0*(-((KDT*cC)*kon_CR))))*y[109]
            dy[98]=1.0*kon_tbp*y[96]*y[194]+(1.0*(-(Kd_tbp_TR*kon_tbp)))*y[98]+(((((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_AT)))+((-1.0)*(1.0*kon_CR)))+((-1.0)*(1.0*kon_CR)))*y[98]*y[192]+((-1.0)*(1.0*(-(KAT*kon_AT))))*y[101]+((-1.0)*(1.0*(-(KBT*kon_AT))))*y[104]+((-1.0)*(1.0*(-((KCT*cC)*kon_CR))))*y[107]+((-1.0)*(1.0*(-((KDT*cC)*kon_CR))))*y[110]
            dy[99]=((-1.0)*(1.0*(-k_R2T_N)))*y[3]+1.0*kon_AT*y[96]*y[192]+(((1.0*(-(KAT*kon_AT)))+(1.0*(-k_R2T_C)))+((-1.0)*(1.0*(k_R2T_N/(lN*cN)))))*y[99]+((((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_CR)))+((-1.0)*(1.0*kon_CR)))*y[99]*y[192]+((-1.0)*(1.0*kon_rbp))*y[99]*y[193]+((-1.0)*(1.0*kon_tbp))*y[99]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_TR*kon_rbp))))*y[100]+((-1.0)*(1.0*(-(Kd_tbp_TR*kon_tbp))))*y[101]+((-1.0)*(1.0*(-(KBT*kon_AT))))*y[111]+((-1.0)*(1.0*(-((KCT*cC)*kon_CR))))*y[114]+((-1.0)*(1.0*(-((KDT*cC)*kon_CR))))*y[117]+1.0*(k_R2T_C/lC)*y[147]
            dy[100]=1.0*kon_AT*y[97]*y[192]+1.0*kon_rbp*y[99]*y[193]+((1.0*(-(KAT*kon_AT)))+(1.0*(-(Kd_rbp_TR*kon_rbp))))*y[100]+((((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_CR)))+((-1.0)*(1.0*kon_CR)))*y[100]*y[192]+((-1.0)*(1.0*(-(KBT*kon_AT))))*y[112]+((-1.0)*(1.0*(-((KCT*cC)*kon_CR))))*y[115]+((-1.0)*(1.0*(-((KDT*cC)*kon_CR))))*y[118]
            dy[101]=1.0*kon_AT*y[98]*y[192]+1.0*kon_tbp*y[99]*y[194]+((1.0*(-(Kd_tbp_TR*kon_tbp)))+(1.0*(-(KAT*kon_AT))))*y[101]+((((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_CR)))+((-1.0)*(1.0*kon_CR)))*y[101]*y[192]+((-1.0)*(1.0*(-(KBT*kon_AT))))*y[113]+((-1.0)*(1.0*(-((KCT*cC)*kon_CR))))*y[116]+((-1.0)*(1.0*(-((KDT*cC)*kon_CR))))*y[119]
            dy[102]=((-1.0)*(1.0*(-k_R2T_N)))*y[6]+1.0*kon_AT*y[96]*y[192]+(((1.0*(-(KBT*kon_AT)))+(1.0*(-k_R2T_C)))+((-1.0)*(1.0*(k_R2T_N/(lN*cN)))))*y[102]+((((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_CR)))+((-1.0)*(1.0*kon_CR)))*y[102]*y[192]+((-1.0)*(1.0*kon_rbp))*y[102]*y[193]+((-1.0)*(1.0*kon_tbp))*y[102]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_TR*kon_rbp))))*y[103]+((-1.0)*(1.0*(-(Kd_tbp_TR*kon_tbp))))*y[104]+((-1.0)*(1.0*(-(KAT*kon_AT))))*y[111]+((-1.0)*(1.0*(-((KCT*cC)*kon_CR))))*y[120]+((-1.0)*(1.0*(-((KDT*cC)*kon_CR))))*y[123]+1.0*(k_R2T_C/lC)*y[150]
            dy[103]=1.0*kon_AT*y[97]*y[192]+1.0*kon_rbp*y[102]*y[193]+((1.0*(-(KBT*kon_AT)))+(1.0*(-(Kd_rbp_TR*kon_rbp))))*y[103]+((((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_CR)))+((-1.0)*(1.0*kon_CR)))*y[103]*y[192]+((-1.0)*(1.0*(-(KAT*kon_AT))))*y[112]+((-1.0)*(1.0*(-((KCT*cC)*kon_CR))))*y[121]+((-1.0)*(1.0*(-((KDT*cC)*kon_CR))))*y[124]
            dy[104]=1.0*kon_AT*y[98]*y[192]+1.0*kon_tbp*y[102]*y[194]+((1.0*(-(Kd_tbp_TR*kon_tbp)))+(1.0*(-(KBT*kon_AT))))*y[104]+((((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_CR)))+((-1.0)*(1.0*kon_CR)))*y[104]*y[192]+((-1.0)*(1.0*(-(KAT*kon_AT))))*y[113]+((-1.0)*(1.0*(-((KCT*cC)*kon_CR))))*y[122]+((-1.0)*(1.0*(-((KDT*cC)*kon_CR))))*y[125]
            dy[105]=((-1.0)*(1.0*(-k_R2T_N)))*y[9]+1.0*kon_CR*y[96]*y[192]+(((1.0*(-k_R2T_C))+(1.0*(-((KCT*cC)*kon_CR))))+((-1.0)*(1.0*(k_R2T_N/lN))))*y[105]+((((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_AT)))+((-1.0)*(1.0*kon_CR)))*y[105]*y[192]+((-1.0)*(1.0*kon_rbp))*y[105]*y[193]+((-1.0)*(1.0*kon_tbp))*y[105]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_TR*kon_rbp))))*y[106]+((-1.0)*(1.0*(-(Kd_tbp_TR*kon_tbp))))*y[107]+((-1.0)*(1.0*(-(KAT*kon_AT))))*y[114]+((-1.0)*(1.0*(-(KBT*kon_AT))))*y[120]+((-1.0)*(1.0*(-((KDT*cC)*kon_CR))))*y[126]+1.0*(k_R2T_C/(lC*cC))*y[153]
            dy[106]=1.0*kon_CR*y[97]*y[192]+1.0*kon_rbp*y[105]*y[193]+((1.0*(-(Kd_rbp_TR*kon_rbp)))+(1.0*(-((KCT*cC)*kon_CR))))*y[106]+((((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_AT)))+((-1.0)*(1.0*kon_CR)))*y[106]*y[192]+((-1.0)*(1.0*(-(KAT*kon_AT))))*y[115]+((-1.0)*(1.0*(-(KBT*kon_AT))))*y[121]+((-1.0)*(1.0*(-((KDT*cC)*kon_CR))))*y[127]
            dy[107]=1.0*kon_CR*y[98]*y[192]+1.0*kon_tbp*y[105]*y[194]+((1.0*(-(Kd_tbp_TR*kon_tbp)))+(1.0*(-((KCT*cC)*kon_CR))))*y[107]+((((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_AT)))+((-1.0)*(1.0*kon_CR)))*y[107]*y[192]+((-1.0)*(1.0*(-(KAT*kon_AT))))*y[116]+((-1.0)*(1.0*(-(KBT*kon_AT))))*y[122]+((-1.0)*(1.0*(-((KDT*cC)*kon_CR))))*y[128]
            dy[108]=((-1.0)*(1.0*(-k_R2T_N)))*y[12]+1.0*kon_CR*y[96]*y[192]+(((1.0*(-k_R2T_C))+(1.0*(-((KDT*cC)*kon_CR))))+((-1.0)*(1.0*(k_R2T_N/lN))))*y[108]+((((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_AT)))+((-1.0)*(1.0*kon_CR)))*y[108]*y[192]+((-1.0)*(1.0*kon_rbp))*y[108]*y[193]+((-1.0)*(1.0*kon_tbp))*y[108]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_TR*kon_rbp))))*y[109]+((-1.0)*(1.0*(-(Kd_tbp_TR*kon_tbp))))*y[110]+((-1.0)*(1.0*(-(KAT*kon_AT))))*y[117]+((-1.0)*(1.0*(-(KBT*kon_AT))))*y[123]+((-1.0)*(1.0*(-((KCT*cC)*kon_CR))))*y[126]+1.0*(k_R2T_C/(lC*cC))*y[156]
            dy[109]=1.0*kon_CR*y[97]*y[192]+1.0*kon_rbp*y[108]*y[193]+((1.0*(-(Kd_rbp_TR*kon_rbp)))+(1.0*(-((KDT*cC)*kon_CR))))*y[109]+((((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_AT)))+((-1.0)*(1.0*kon_CR)))*y[109]*y[192]+((-1.0)*(1.0*(-(KAT*kon_AT))))*y[118]+((-1.0)*(1.0*(-(KBT*kon_AT))))*y[124]+((-1.0)*(1.0*(-((KCT*cC)*kon_CR))))*y[127]
            dy[110]=1.0*kon_CR*y[98]*y[192]+1.0*kon_tbp*y[108]*y[194]+((1.0*(-(Kd_tbp_TR*kon_tbp)))+(1.0*(-((KDT*cC)*kon_CR))))*y[110]+((((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_AT)))+((-1.0)*(1.0*kon_CR)))*y[110]*y[192]+((-1.0)*(1.0*(-(KAT*kon_AT))))*y[119]+((-1.0)*(1.0*(-(KBT*kon_AT))))*y[125]+((-1.0)*(1.0*(-((KCT*cC)*kon_CR))))*y[128]
            dy[111]=((-1.0)*(1.0*(-k_R2T_N)))*y[15]+1.0*kon_AT*y[99]*y[192]+1.0*kon_AT*y[102]*y[192]+(((((-1.0)*(1.0*(k_R2T_N/((lN*cN)*cN))))+(1.0*(-(KAT*kon_AT))))+(1.0*(-(KBT*kon_AT))))+(1.0*(-k_R2T_C)))*y[111]+(((-1.0)*(1.0*kon_CR))+((-1.0)*(1.0*kon_CR)))*y[111]*y[192]+((-1.0)*(1.0*kon_rbp))*y[111]*y[193]+((-1.0)*(1.0*kon_tbp))*y[111]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_TR*kon_rbp))))*y[112]+((-1.0)*(1.0*(-(Kd_tbp_TR*kon_tbp))))*y[113]+((-1.0)*(1.0*(-((KCT*cC)*kon_CR))))*y[129]+((-1.0)*(1.0*(-((KDT*cC)*kon_CR))))*y[132]+1.0*(k_R2T_C/lC)*y[159]
            dy[112]=1.0*kon_AT*y[100]*y[192]+1.0*kon_AT*y[103]*y[192]+1.0*kon_rbp*y[111]*y[193]+(((1.0*(-(KAT*kon_AT)))+(1.0*(-(KBT*kon_AT))))+(1.0*(-(Kd_rbp_TR*kon_rbp))))*y[112]+(((-1.0)*(1.0*kon_CR))+((-1.0)*(1.0*kon_CR)))*y[112]*y[192]+((-1.0)*(1.0*(-((KCT*cC)*kon_CR))))*y[130]+((-1.0)*(1.0*(-((KDT*cC)*kon_CR))))*y[133]
            dy[113]=1.0*kon_AT*y[101]*y[192]+1.0*kon_AT*y[104]*y[192]+1.0*kon_tbp*y[111]*y[194]+(((1.0*(-(Kd_tbp_TR*kon_tbp)))+(1.0*(-(KAT*kon_AT))))+(1.0*(-(KBT*kon_AT))))*y[113]+(((-1.0)*(1.0*kon_CR))+((-1.0)*(1.0*kon_CR)))*y[113]*y[192]+((-1.0)*(1.0*(-((KCT*cC)*kon_CR))))*y[131]+((-1.0)*(1.0*(-((KDT*cC)*kon_CR))))*y[134]
            dy[114]=((-1.0)*(1.0*(-k_R2T_N)))*y[18]+1.0*kon_CR*y[99]*y[192]+1.0*kon_AT*y[105]*y[192]+((((1.0*(-(KAT*kon_AT)))+(1.0*(-k_R2T_C)))+((-1.0)*(1.0*(k_R2T_N/(lN*cN)))))+(1.0*(-((KCT*cC)*kon_CR))))*y[114]+(((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_CR)))*y[114]*y[192]+((-1.0)*(1.0*kon_rbp))*y[114]*y[193]+((-1.0)*(1.0*kon_tbp))*y[114]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_TR*kon_rbp))))*y[115]+((-1.0)*(1.0*(-(Kd_tbp_TR*kon_tbp))))*y[116]+((-1.0)*(1.0*(-(KBT*kon_AT))))*y[129]+((-1.0)*(1.0*(-((KDT*cC)*kon_CR))))*y[135]+1.0*(k_R2T_C/(lC*cC))*y[162]
            dy[115]=1.0*kon_CR*y[100]*y[192]+1.0*kon_AT*y[106]*y[192]+1.0*kon_rbp*y[114]*y[193]+(((1.0*(-(KAT*kon_AT)))+(1.0*(-(Kd_rbp_TR*kon_rbp))))+(1.0*(-((KCT*cC)*kon_CR))))*y[115]+(((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_CR)))*y[115]*y[192]+((-1.0)*(1.0*(-(KBT*kon_AT))))*y[130]+((-1.0)*(1.0*(-((KDT*cC)*kon_CR))))*y[136]
            dy[116]=1.0*kon_CR*y[101]*y[192]+1.0*kon_AT*y[107]*y[192]+1.0*kon_tbp*y[114]*y[194]+(((1.0*(-(Kd_tbp_TR*kon_tbp)))+(1.0*(-(KAT*kon_AT))))+(1.0*(-((KCT*cC)*kon_CR))))*y[116]+(((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_CR)))*y[116]*y[192]+((-1.0)*(1.0*(-(KBT*kon_AT))))*y[131]+((-1.0)*(1.0*(-((KDT*cC)*kon_CR))))*y[137]
            dy[117]=((-1.0)*(1.0*(-k_R2T_N)))*y[21]+1.0*kon_CR*y[99]*y[192]+1.0*kon_AT*y[108]*y[192]+((((1.0*(-(KAT*kon_AT)))+(1.0*(-k_R2T_C)))+((-1.0)*(1.0*(k_R2T_N/(lN*cN)))))+(1.0*(-((KDT*cC)*kon_CR))))*y[117]+(((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_CR)))*y[117]*y[192]+((-1.0)*(1.0*kon_rbp))*y[117]*y[193]+((-1.0)*(1.0*kon_tbp))*y[117]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_TR*kon_rbp))))*y[118]+((-1.0)*(1.0*(-(Kd_tbp_TR*kon_tbp))))*y[119]+((-1.0)*(1.0*(-(KBT*kon_AT))))*y[132]+((-1.0)*(1.0*(-((KCT*cC)*kon_CR))))*y[135]+1.0*(k_R2T_C/(lC*cC))*y[165]
            dy[118]=1.0*kon_CR*y[100]*y[192]+1.0*kon_AT*y[109]*y[192]+1.0*kon_rbp*y[117]*y[193]+(((1.0*(-(KAT*kon_AT)))+(1.0*(-(Kd_rbp_TR*kon_rbp))))+(1.0*(-((KDT*cC)*kon_CR))))*y[118]+(((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_CR)))*y[118]*y[192]+((-1.0)*(1.0*(-(KBT*kon_AT))))*y[133]+((-1.0)*(1.0*(-((KCT*cC)*kon_CR))))*y[136]
            dy[119]=1.0*kon_CR*y[101]*y[192]+1.0*kon_AT*y[110]*y[192]+1.0*kon_tbp*y[117]*y[194]+(((1.0*(-(Kd_tbp_TR*kon_tbp)))+(1.0*(-(KAT*kon_AT))))+(1.0*(-((KDT*cC)*kon_CR))))*y[119]+(((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_CR)))*y[119]*y[192]+((-1.0)*(1.0*(-(KBT*kon_AT))))*y[134]+((-1.0)*(1.0*(-((KCT*cC)*kon_CR))))*y[137]
            dy[120]=((-1.0)*(1.0*(-k_R2T_N)))*y[24]+1.0*kon_CR*y[102]*y[192]+1.0*kon_AT*y[105]*y[192]+((((1.0*(-(KBT*kon_AT)))+(1.0*(-k_R2T_C)))+((-1.0)*(1.0*(k_R2T_N/(lN*cN)))))+(1.0*(-((KCT*cC)*kon_CR))))*y[120]+(((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_CR)))*y[120]*y[192]+((-1.0)*(1.0*kon_rbp))*y[120]*y[193]+((-1.0)*(1.0*kon_tbp))*y[120]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_TR*kon_rbp))))*y[121]+((-1.0)*(1.0*(-(Kd_tbp_TR*kon_tbp))))*y[122]+((-1.0)*(1.0*(-(KAT*kon_AT))))*y[129]+((-1.0)*(1.0*(-((KDT*cC)*kon_CR))))*y[138]+1.0*(k_R2T_C/(lC*cC))*y[168]
            dy[121]=1.0*kon_CR*y[103]*y[192]+1.0*kon_AT*y[106]*y[192]+1.0*kon_rbp*y[120]*y[193]+(((1.0*(-(KBT*kon_AT)))+(1.0*(-(Kd_rbp_TR*kon_rbp))))+(1.0*(-((KCT*cC)*kon_CR))))*y[121]+(((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_CR)))*y[121]*y[192]+((-1.0)*(1.0*(-(KAT*kon_AT))))*y[130]+((-1.0)*(1.0*(-((KDT*cC)*kon_CR))))*y[139]
            dy[122]=1.0*kon_CR*y[104]*y[192]+1.0*kon_AT*y[107]*y[192]+1.0*kon_tbp*y[120]*y[194]+(((1.0*(-(Kd_tbp_TR*kon_tbp)))+(1.0*(-(KBT*kon_AT))))+(1.0*(-((KCT*cC)*kon_CR))))*y[122]+(((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_CR)))*y[122]*y[192]+((-1.0)*(1.0*(-(KAT*kon_AT))))*y[131]+((-1.0)*(1.0*(-((KDT*cC)*kon_CR))))*y[140]
            dy[123]=((-1.0)*(1.0*(-k_R2T_N)))*y[27]+1.0*kon_CR*y[102]*y[192]+1.0*kon_AT*y[108]*y[192]+((((1.0*(-(KBT*kon_AT)))+(1.0*(-k_R2T_C)))+((-1.0)*(1.0*(k_R2T_N/(lN*cN)))))+(1.0*(-((KDT*cC)*kon_CR))))*y[123]+(((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_CR)))*y[123]*y[192]+((-1.0)*(1.0*kon_rbp))*y[123]*y[193]+((-1.0)*(1.0*kon_tbp))*y[123]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_TR*kon_rbp))))*y[124]+((-1.0)*(1.0*(-(Kd_tbp_TR*kon_tbp))))*y[125]+((-1.0)*(1.0*(-(KAT*kon_AT))))*y[132]+((-1.0)*(1.0*(-((KCT*cC)*kon_CR))))*y[138]+1.0*(k_R2T_C/(lC*cC))*y[171]
            dy[124]=1.0*kon_CR*y[103]*y[192]+1.0*kon_AT*y[109]*y[192]+1.0*kon_rbp*y[123]*y[193]+(((1.0*(-(KBT*kon_AT)))+(1.0*(-(Kd_rbp_TR*kon_rbp))))+(1.0*(-((KDT*cC)*kon_CR))))*y[124]+(((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_CR)))*y[124]*y[192]+((-1.0)*(1.0*(-(KAT*kon_AT))))*y[133]+((-1.0)*(1.0*(-((KCT*cC)*kon_CR))))*y[139]
            dy[125]=1.0*kon_CR*y[104]*y[192]+1.0*kon_AT*y[110]*y[192]+1.0*kon_tbp*y[123]*y[194]+(((1.0*(-(Kd_tbp_TR*kon_tbp)))+(1.0*(-(KBT*kon_AT))))+(1.0*(-((KDT*cC)*kon_CR))))*y[125]+(((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_CR)))*y[125]*y[192]+((-1.0)*(1.0*(-(KAT*kon_AT))))*y[134]+((-1.0)*(1.0*(-((KCT*cC)*kon_CR))))*y[140]
            dy[126]=((-1.0)*(1.0*(-k_R2T_N)))*y[30]+1.0*kon_CR*y[105]*y[192]+1.0*kon_CR*y[108]*y[192]+((((1.0*(-k_R2T_C))+(1.0*(-((KCT*cC)*kon_CR))))+(1.0*(-((KDT*cC)*kon_CR))))+((-1.0)*(1.0*(k_R2T_N/lN))))*y[126]+(((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_AT)))*y[126]*y[192]+((-1.0)*(1.0*kon_rbp))*y[126]*y[193]+((-1.0)*(1.0*kon_tbp))*y[126]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_TR*kon_rbp))))*y[127]+((-1.0)*(1.0*(-(Kd_tbp_TR*kon_tbp))))*y[128]+((-1.0)*(1.0*(-(KAT*kon_AT))))*y[135]+((-1.0)*(1.0*(-(KBT*kon_AT))))*y[138]+1.0*(k_R2T_C/((lC*cC)*cC))*y[174]
            dy[127]=1.0*kon_CR*y[106]*y[192]+1.0*kon_CR*y[109]*y[192]+1.0*kon_rbp*y[126]*y[193]+(((1.0*(-(Kd_rbp_TR*kon_rbp)))+(1.0*(-((KCT*cC)*kon_CR))))+(1.0*(-((KDT*cC)*kon_CR))))*y[127]+(((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_AT)))*y[127]*y[192]+((-1.0)*(1.0*(-(KAT*kon_AT))))*y[136]+((-1.0)*(1.0*(-(KBT*kon_AT))))*y[139]
            dy[128]=1.0*kon_CR*y[107]*y[192]+1.0*kon_CR*y[110]*y[192]+1.0*kon_tbp*y[126]*y[194]+(((1.0*(-(Kd_tbp_TR*kon_tbp)))+(1.0*(-((KCT*cC)*kon_CR))))+(1.0*(-((KDT*cC)*kon_CR))))*y[128]+(((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_AT)))*y[128]*y[192]+((-1.0)*(1.0*(-(KAT*kon_AT))))*y[137]+((-1.0)*(1.0*(-(KBT*kon_AT))))*y[140]
            dy[129]=((-1.0)*(1.0*(-k_R2T_N)))*y[33]+1.0*kon_CR*y[111]*y[192]+1.0*kon_AT*y[114]*y[192]+1.0*kon_AT*y[120]*y[192]+((((((-1.0)*(1.0*(k_R2T_N/((lN*cN)*cN))))+(1.0*(-(KAT*kon_AT))))+(1.0*(-(KBT*kon_AT))))+(1.0*(-k_R2T_C)))+(1.0*(-((KCT*cC)*kon_CR))))*y[129]+((-1.0)*(1.0*kon_CR))*y[129]*y[192]+((-1.0)*(1.0*kon_rbp))*y[129]*y[193]+((-1.0)*(1.0*kon_tbp))*y[129]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_TR*kon_rbp))))*y[130]+((-1.0)*(1.0*(-(Kd_tbp_TR*kon_tbp))))*y[131]+((-1.0)*(1.0*(-((KDT*cC)*kon_CR))))*y[141]+1.0*(k_R2T_C/(lC*cC))*y[177]
            dy[130]=1.0*kon_CR*y[112]*y[192]+1.0*kon_AT*y[115]*y[192]+1.0*kon_AT*y[121]*y[192]+1.0*kon_rbp*y[129]*y[193]+((((1.0*(-(KAT*kon_AT)))+(1.0*(-(KBT*kon_AT))))+(1.0*(-(Kd_rbp_TR*kon_rbp))))+(1.0*(-((KCT*cC)*kon_CR))))*y[130]+((-1.0)*(1.0*kon_CR))*y[130]*y[192]+((-1.0)*(1.0*(-((KDT*cC)*kon_CR))))*y[142]
            dy[131]=1.0*kon_CR*y[113]*y[192]+1.0*kon_AT*y[116]*y[192]+1.0*kon_AT*y[122]*y[192]+1.0*kon_tbp*y[129]*y[194]+((((1.0*(-(Kd_tbp_TR*kon_tbp)))+(1.0*(-(KAT*kon_AT))))+(1.0*(-(KBT*kon_AT))))+(1.0*(-((KCT*cC)*kon_CR))))*y[131]+((-1.0)*(1.0*kon_CR))*y[131]*y[192]+((-1.0)*(1.0*(-((KDT*cC)*kon_CR))))*y[143]
            dy[132]=((-1.0)*(1.0*(-k_R2T_N)))*y[36]+1.0*kon_CR*y[111]*y[192]+1.0*kon_AT*y[117]*y[192]+1.0*kon_AT*y[123]*y[192]+((((((-1.0)*(1.0*(k_R2T_N/((lN*cN)*cN))))+(1.0*(-(KAT*kon_AT))))+(1.0*(-(KBT*kon_AT))))+(1.0*(-k_R2T_C)))+(1.0*(-((KDT*cC)*kon_CR))))*y[132]+((-1.0)*(1.0*kon_CR))*y[132]*y[192]+((-1.0)*(1.0*kon_rbp))*y[132]*y[193]+((-1.0)*(1.0*kon_tbp))*y[132]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_TR*kon_rbp))))*y[133]+((-1.0)*(1.0*(-(Kd_tbp_TR*kon_tbp))))*y[134]+((-1.0)*(1.0*(-((KCT*cC)*kon_CR))))*y[141]+1.0*(k_R2T_C/(lC*cC))*y[180]
            dy[133]=1.0*kon_CR*y[112]*y[192]+1.0*kon_AT*y[118]*y[192]+1.0*kon_AT*y[124]*y[192]+1.0*kon_rbp*y[132]*y[193]+((((1.0*(-(KAT*kon_AT)))+(1.0*(-(KBT*kon_AT))))+(1.0*(-(Kd_rbp_TR*kon_rbp))))+(1.0*(-((KDT*cC)*kon_CR))))*y[133]+((-1.0)*(1.0*kon_CR))*y[133]*y[192]+((-1.0)*(1.0*(-((KCT*cC)*kon_CR))))*y[142]
            dy[134]=1.0*kon_CR*y[113]*y[192]+1.0*kon_AT*y[119]*y[192]+1.0*kon_AT*y[125]*y[192]+1.0*kon_tbp*y[132]*y[194]+((((1.0*(-(Kd_tbp_TR*kon_tbp)))+(1.0*(-(KAT*kon_AT))))+(1.0*(-(KBT*kon_AT))))+(1.0*(-((KDT*cC)*kon_CR))))*y[134]+((-1.0)*(1.0*kon_CR))*y[134]*y[192]+((-1.0)*(1.0*(-((KCT*cC)*kon_CR))))*y[143]
            dy[135]=((-1.0)*(1.0*(-k_R2T_N)))*y[39]+1.0*kon_CR*y[114]*y[192]+1.0*kon_CR*y[117]*y[192]+1.0*kon_AT*y[126]*y[192]+(((((1.0*(-(KAT*kon_AT)))+(1.0*(-k_R2T_C)))+((-1.0)*(1.0*(k_R2T_N/(lN*cN)))))+(1.0*(-((KCT*cC)*kon_CR))))+(1.0*(-((KDT*cC)*kon_CR))))*y[135]+((-1.0)*(1.0*kon_AT))*y[135]*y[192]+((-1.0)*(1.0*kon_rbp))*y[135]*y[193]+((-1.0)*(1.0*kon_tbp))*y[135]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_TR*kon_rbp))))*y[136]+((-1.0)*(1.0*(-(Kd_tbp_TR*kon_tbp))))*y[137]+((-1.0)*(1.0*(-(KBT*kon_AT))))*y[141]+1.0*(k_R2T_C/((lC*cC)*cC))*y[183]
            dy[136]=1.0*kon_CR*y[115]*y[192]+1.0*kon_CR*y[118]*y[192]+1.0*kon_AT*y[127]*y[192]+1.0*kon_rbp*y[135]*y[193]+((((1.0*(-(KAT*kon_AT)))+(1.0*(-(Kd_rbp_TR*kon_rbp))))+(1.0*(-((KCT*cC)*kon_CR))))+(1.0*(-((KDT*cC)*kon_CR))))*y[136]+((-1.0)*(1.0*kon_AT))*y[136]*y[192]+((-1.0)*(1.0*(-(KBT*kon_AT))))*y[142]
            dy[137]=1.0*kon_CR*y[116]*y[192]+1.0*kon_CR*y[119]*y[192]+1.0*kon_AT*y[128]*y[192]+1.0*kon_tbp*y[135]*y[194]+((((1.0*(-(Kd_tbp_TR*kon_tbp)))+(1.0*(-(KAT*kon_AT))))+(1.0*(-((KCT*cC)*kon_CR))))+(1.0*(-((KDT*cC)*kon_CR))))*y[137]+((-1.0)*(1.0*kon_AT))*y[137]*y[192]+((-1.0)*(1.0*(-(KBT*kon_AT))))*y[143]
            dy[138]=((-1.0)*(1.0*(-k_R2T_N)))*y[42]+1.0*kon_CR*y[120]*y[192]+1.0*kon_CR*y[123]*y[192]+1.0*kon_AT*y[126]*y[192]+(((((1.0*(-(KBT*kon_AT)))+(1.0*(-k_R2T_C)))+((-1.0)*(1.0*(k_R2T_N/(lN*cN)))))+(1.0*(-((KCT*cC)*kon_CR))))+(1.0*(-((KDT*cC)*kon_CR))))*y[138]+((-1.0)*(1.0*kon_AT))*y[138]*y[192]+((-1.0)*(1.0*kon_rbp))*y[138]*y[193]+((-1.0)*(1.0*kon_tbp))*y[138]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_TR*kon_rbp))))*y[139]+((-1.0)*(1.0*(-(Kd_tbp_TR*kon_tbp))))*y[140]+((-1.0)*(1.0*(-(KAT*kon_AT))))*y[141]+1.0*(k_R2T_C/((lC*cC)*cC))*y[186]
            dy[139]=1.0*kon_CR*y[121]*y[192]+1.0*kon_CR*y[124]*y[192]+1.0*kon_AT*y[127]*y[192]+1.0*kon_rbp*y[138]*y[193]+((((1.0*(-(KBT*kon_AT)))+(1.0*(-(Kd_rbp_TR*kon_rbp))))+(1.0*(-((KCT*cC)*kon_CR))))+(1.0*(-((KDT*cC)*kon_CR))))*y[139]+((-1.0)*(1.0*kon_AT))*y[139]*y[192]+((-1.0)*(1.0*(-(KAT*kon_AT))))*y[142]
            dy[140]=1.0*kon_CR*y[122]*y[192]+1.0*kon_CR*y[125]*y[192]+1.0*kon_AT*y[128]*y[192]+1.0*kon_tbp*y[138]*y[194]+((((1.0*(-(Kd_tbp_TR*kon_tbp)))+(1.0*(-(KBT*kon_AT))))+(1.0*(-((KCT*cC)*kon_CR))))+(1.0*(-((KDT*cC)*kon_CR))))*y[140]+((-1.0)*(1.0*kon_AT))*y[140]*y[192]+((-1.0)*(1.0*(-(KAT*kon_AT))))*y[143]
            dy[141]=((-1.0)*(1.0*(-k_R2T_N)))*y[45]+1.0*kon_CR*y[129]*y[192]+1.0*kon_CR*y[132]*y[192]+1.0*kon_AT*y[135]*y[192]+1.0*kon_AT*y[138]*y[192]+(((((((-1.0)*(1.0*(k_R2T_N/((lN*cN)*cN))))+(1.0*(-(KAT*kon_AT))))+(1.0*(-(KBT*kon_AT))))+(1.0*(-k_R2T_C)))+(1.0*(-((KCT*cC)*kon_CR))))+(1.0*(-((KDT*cC)*kon_CR))))*y[141]+((-1.0)*(1.0*kon_rbp))*y[141]*y[193]+((-1.0)*(1.0*kon_tbp))*y[141]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_TR*kon_rbp))))*y[142]+((-1.0)*(1.0*(-(Kd_tbp_TR*kon_tbp))))*y[143]+1.0*(k_R2T_C/((lC*cC)*cC))*y[189]
            dy[142]=1.0*kon_CR*y[130]*y[192]+1.0*kon_CR*y[133]*y[192]+1.0*kon_AT*y[136]*y[192]+1.0*kon_AT*y[139]*y[192]+1.0*kon_rbp*y[141]*y[193]+(((((1.0*(-(KAT*kon_AT)))+(1.0*(-(KBT*kon_AT))))+(1.0*(-(Kd_rbp_TR*kon_rbp))))+(1.0*(-((KCT*cC)*kon_CR))))+(1.0*(-((KDT*cC)*kon_CR))))*y[142]
            dy[143]=1.0*kon_CR*y[131]*y[192]+1.0*kon_CR*y[134]*y[192]+1.0*kon_AT*y[137]*y[192]+1.0*kon_AT*y[140]*y[192]+1.0*kon_tbp*y[141]*y[194]+(((((1.0*(-(Kd_tbp_TR*kon_tbp)))+(1.0*(-(KAT*kon_AT))))+(1.0*(-(KBT*kon_AT))))+(1.0*(-((KCT*cC)*kon_CR))))+(1.0*(-((KDT*cC)*kon_CR))))*y[143]
            dy[144]=((-1.0)*(1.0*(-k_R2T_N)))*y[48]+((-1.0)*(1.0*(-k_R2T_C)))*y[96]+(((-1.0)*(1.0*(k_R2T_C/lC)))+((-1.0)*(1.0*(k_R2T_N/lN))))*y[144]+(((((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_AT)))+((-1.0)*(1.0*kon_CT)))+((-1.0)*(1.0*kon_CT)))*y[144]*y[192]+((-1.0)*(1.0*kon_rbp))*y[144]*y[193]+((-1.0)*(1.0*kon_tbp))*y[144]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_TT*kon_rbp))))*y[145]+((-1.0)*(1.0*(-(Kd_tbp_TT*kon_tbp))))*y[146]+((-1.0)*(1.0*(-(KAT*kon_AT))))*y[147]+((-1.0)*(1.0*(-(KBT*kon_AT))))*y[150]+((-1.0)*(1.0*(-(KCT*kon_CT))))*y[153]+((-1.0)*(1.0*(-(KDT*kon_CT))))*y[156]
            dy[145]=1.0*kon_rbp*y[144]*y[193]+(1.0*(-(Kd_rbp_TT*kon_rbp)))*y[145]+(((((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_AT)))+((-1.0)*(1.0*kon_CT)))+((-1.0)*(1.0*kon_CT)))*y[145]*y[192]+((-1.0)*(1.0*(-(KAT*kon_AT))))*y[148]+((-1.0)*(1.0*(-(KBT*kon_AT))))*y[151]+((-1.0)*(1.0*(-(KCT*kon_CT))))*y[154]+((-1.0)*(1.0*(-(KDT*kon_CT))))*y[157]
            dy[146]=1.0*kon_tbp*y[144]*y[194]+(1.0*(-(Kd_tbp_TT*kon_tbp)))*y[146]+(((((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_AT)))+((-1.0)*(1.0*kon_CT)))+((-1.0)*(1.0*kon_CT)))*y[146]*y[192]+((-1.0)*(1.0*(-(KAT*kon_AT))))*y[149]+((-1.0)*(1.0*(-(KBT*kon_AT))))*y[152]+((-1.0)*(1.0*(-(KCT*kon_CT))))*y[155]+((-1.0)*(1.0*(-(KDT*kon_CT))))*y[158]
            dy[147]=((-1.0)*(1.0*(-k_R2T_N)))*y[51]+((-1.0)*(1.0*(-k_R2T_C)))*y[99]+1.0*kon_AT*y[144]*y[192]+(((1.0*(-(KAT*kon_AT)))+((-1.0)*(1.0*(k_R2T_N/(lN*cN)))))+((-1.0)*(1.0*(k_R2T_C/lC))))*y[147]+((((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_CT)))+((-1.0)*(1.0*kon_CT)))*y[147]*y[192]+((-1.0)*(1.0*kon_rbp))*y[147]*y[193]+((-1.0)*(1.0*kon_tbp))*y[147]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_TT*kon_rbp))))*y[148]+((-1.0)*(1.0*(-(Kd_tbp_TT*kon_tbp))))*y[149]+((-1.0)*(1.0*(-(KBT*kon_AT))))*y[159]+((-1.0)*(1.0*(-(KCT*kon_CT))))*y[162]+((-1.0)*(1.0*(-(KDT*kon_CT))))*y[165]
            dy[148]=1.0*kon_AT*y[145]*y[192]+1.0*kon_rbp*y[147]*y[193]+((1.0*(-(KAT*kon_AT)))+(1.0*(-(Kd_rbp_TT*kon_rbp))))*y[148]+((((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_CT)))+((-1.0)*(1.0*kon_CT)))*y[148]*y[192]+((-1.0)*(1.0*(-(KBT*kon_AT))))*y[160]+((-1.0)*(1.0*(-(KCT*kon_CT))))*y[163]+((-1.0)*(1.0*(-(KDT*kon_CT))))*y[166]
            dy[149]=1.0*kon_AT*y[146]*y[192]+1.0*kon_tbp*y[147]*y[194]+((1.0*(-(Kd_tbp_TT*kon_tbp)))+(1.0*(-(KAT*kon_AT))))*y[149]+((((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_CT)))+((-1.0)*(1.0*kon_CT)))*y[149]*y[192]+((-1.0)*(1.0*(-(KBT*kon_AT))))*y[161]+((-1.0)*(1.0*(-(KCT*kon_CT))))*y[164]+((-1.0)*(1.0*(-(KDT*kon_CT))))*y[167]
            dy[150]=((-1.0)*(1.0*(-k_R2T_N)))*y[54]+((-1.0)*(1.0*(-k_R2T_C)))*y[102]+1.0*kon_AT*y[144]*y[192]+(((1.0*(-(KBT*kon_AT)))+((-1.0)*(1.0*(k_R2T_N/(lN*cN)))))+((-1.0)*(1.0*(k_R2T_C/lC))))*y[150]+((((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_CT)))+((-1.0)*(1.0*kon_CT)))*y[150]*y[192]+((-1.0)*(1.0*kon_rbp))*y[150]*y[193]+((-1.0)*(1.0*kon_tbp))*y[150]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_TT*kon_rbp))))*y[151]+((-1.0)*(1.0*(-(Kd_tbp_TT*kon_tbp))))*y[152]+((-1.0)*(1.0*(-(KAT*kon_AT))))*y[159]+((-1.0)*(1.0*(-(KCT*kon_CT))))*y[168]+((-1.0)*(1.0*(-(KDT*kon_CT))))*y[171]
            dy[151]=1.0*kon_AT*y[145]*y[192]+1.0*kon_rbp*y[150]*y[193]+((1.0*(-(KBT*kon_AT)))+(1.0*(-(Kd_rbp_TT*kon_rbp))))*y[151]+((((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_CT)))+((-1.0)*(1.0*kon_CT)))*y[151]*y[192]+((-1.0)*(1.0*(-(KAT*kon_AT))))*y[160]+((-1.0)*(1.0*(-(KCT*kon_CT))))*y[169]+((-1.0)*(1.0*(-(KDT*kon_CT))))*y[172]
            dy[152]=1.0*kon_AT*y[146]*y[192]+1.0*kon_tbp*y[150]*y[194]+((1.0*(-(Kd_tbp_TT*kon_tbp)))+(1.0*(-(KBT*kon_AT))))*y[152]+((((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_CT)))+((-1.0)*(1.0*kon_CT)))*y[152]*y[192]+((-1.0)*(1.0*(-(KAT*kon_AT))))*y[161]+((-1.0)*(1.0*(-(KCT*kon_CT))))*y[170]+((-1.0)*(1.0*(-(KDT*kon_CT))))*y[173]
            dy[153]=((-1.0)*(1.0*(-k_R2T_N)))*y[57]+((-1.0)*(1.0*(-k_R2T_C)))*y[105]+1.0*kon_CT*y[144]*y[192]+((((-1.0)*(1.0*(k_R2T_C/(lC*cC))))+(1.0*(-(KCT*kon_CT))))+((-1.0)*(1.0*(k_R2T_N/lN))))*y[153]+((((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_AT)))+((-1.0)*(1.0*kon_CT)))*y[153]*y[192]+((-1.0)*(1.0*kon_rbp))*y[153]*y[193]+((-1.0)*(1.0*kon_tbp))*y[153]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_TT*kon_rbp))))*y[154]+((-1.0)*(1.0*(-(Kd_tbp_TT*kon_tbp))))*y[155]+((-1.0)*(1.0*(-(KAT*kon_AT))))*y[162]+((-1.0)*(1.0*(-(KBT*kon_AT))))*y[168]+((-1.0)*(1.0*(-(KDT*kon_CT))))*y[174]
            dy[154]=1.0*kon_CT*y[145]*y[192]+1.0*kon_rbp*y[153]*y[193]+((1.0*(-(Kd_rbp_TT*kon_rbp)))+(1.0*(-(KCT*kon_CT))))*y[154]+((((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_AT)))+((-1.0)*(1.0*kon_CT)))*y[154]*y[192]+((-1.0)*(1.0*(-(KAT*kon_AT))))*y[163]+((-1.0)*(1.0*(-(KBT*kon_AT))))*y[169]+((-1.0)*(1.0*(-(KDT*kon_CT))))*y[175]
            dy[155]=1.0*kon_CT*y[146]*y[192]+1.0*kon_tbp*y[153]*y[194]+((1.0*(-(Kd_tbp_TT*kon_tbp)))+(1.0*(-(KCT*kon_CT))))*y[155]+((((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_AT)))+((-1.0)*(1.0*kon_CT)))*y[155]*y[192]+((-1.0)*(1.0*(-(KAT*kon_AT))))*y[164]+((-1.0)*(1.0*(-(KBT*kon_AT))))*y[170]+((-1.0)*(1.0*(-(KDT*kon_CT))))*y[176]
            dy[156]=((-1.0)*(1.0*(-k_R2T_N)))*y[60]+((-1.0)*(1.0*(-k_R2T_C)))*y[108]+1.0*kon_CT*y[144]*y[192]+((((-1.0)*(1.0*(k_R2T_C/(lC*cC))))+(1.0*(-(KDT*kon_CT))))+((-1.0)*(1.0*(k_R2T_N/lN))))*y[156]+((((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_AT)))+((-1.0)*(1.0*kon_CT)))*y[156]*y[192]+((-1.0)*(1.0*kon_rbp))*y[156]*y[193]+((-1.0)*(1.0*kon_tbp))*y[156]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_TT*kon_rbp))))*y[157]+((-1.0)*(1.0*(-(Kd_tbp_TT*kon_tbp))))*y[158]+((-1.0)*(1.0*(-(KAT*kon_AT))))*y[165]+((-1.0)*(1.0*(-(KBT*kon_AT))))*y[171]+((-1.0)*(1.0*(-(KCT*kon_CT))))*y[174]
            dy[157]=1.0*kon_CT*y[145]*y[192]+1.0*kon_rbp*y[156]*y[193]+((1.0*(-(Kd_rbp_TT*kon_rbp)))+(1.0*(-(KDT*kon_CT))))*y[157]+((((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_AT)))+((-1.0)*(1.0*kon_CT)))*y[157]*y[192]+((-1.0)*(1.0*(-(KAT*kon_AT))))*y[166]+((-1.0)*(1.0*(-(KBT*kon_AT))))*y[172]+((-1.0)*(1.0*(-(KCT*kon_CT))))*y[175]
            dy[158]=1.0*kon_CT*y[146]*y[192]+1.0*kon_tbp*y[156]*y[194]+((1.0*(-(Kd_tbp_TT*kon_tbp)))+(1.0*(-(KDT*kon_CT))))*y[158]+((((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_AT)))+((-1.0)*(1.0*kon_CT)))*y[158]*y[192]+((-1.0)*(1.0*(-(KAT*kon_AT))))*y[167]+((-1.0)*(1.0*(-(KBT*kon_AT))))*y[173]+((-1.0)*(1.0*(-(KCT*kon_CT))))*y[176]
            dy[159]=((-1.0)*(1.0*(-k_R2T_N)))*y[63]+((-1.0)*(1.0*(-k_R2T_C)))*y[111]+1.0*kon_AT*y[147]*y[192]+1.0*kon_AT*y[150]*y[192]+(((((-1.0)*(1.0*(k_R2T_N/((lN*cN)*cN))))+(1.0*(-(KAT*kon_AT))))+(1.0*(-(KBT*kon_AT))))+((-1.0)*(1.0*(k_R2T_C/lC))))*y[159]+(((-1.0)*(1.0*kon_CT))+((-1.0)*(1.0*kon_CT)))*y[159]*y[192]+((-1.0)*(1.0*kon_rbp))*y[159]*y[193]+((-1.0)*(1.0*kon_tbp))*y[159]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_TT*kon_rbp))))*y[160]+((-1.0)*(1.0*(-(Kd_tbp_TT*kon_tbp))))*y[161]+((-1.0)*(1.0*(-(KCT*kon_CT))))*y[177]+((-1.0)*(1.0*(-(KDT*kon_CT))))*y[180]
            dy[160]=1.0*kon_AT*y[148]*y[192]+1.0*kon_AT*y[151]*y[192]+1.0*kon_rbp*y[159]*y[193]+(((1.0*(-(KAT*kon_AT)))+(1.0*(-(KBT*kon_AT))))+(1.0*(-(Kd_rbp_TT*kon_rbp))))*y[160]+(((-1.0)*(1.0*kon_CT))+((-1.0)*(1.0*kon_CT)))*y[160]*y[192]+((-1.0)*(1.0*(-(KCT*kon_CT))))*y[178]+((-1.0)*(1.0*(-(KDT*kon_CT))))*y[181]
            dy[161]=1.0*kon_AT*y[149]*y[192]+1.0*kon_AT*y[152]*y[192]+1.0*kon_tbp*y[159]*y[194]+(((1.0*(-(Kd_tbp_TT*kon_tbp)))+(1.0*(-(KAT*kon_AT))))+(1.0*(-(KBT*kon_AT))))*y[161]+(((-1.0)*(1.0*kon_CT))+((-1.0)*(1.0*kon_CT)))*y[161]*y[192]+((-1.0)*(1.0*(-(KCT*kon_CT))))*y[179]+((-1.0)*(1.0*(-(KDT*kon_CT))))*y[182]
            dy[162]=((-1.0)*(1.0*(-k_R2T_N)))*y[66]+((-1.0)*(1.0*(-k_R2T_C)))*y[114]+1.0*kon_CT*y[147]*y[192]+1.0*kon_AT*y[153]*y[192]+((((1.0*(-(KAT*kon_AT)))+((-1.0)*(1.0*(k_R2T_C/(lC*cC)))))+(1.0*(-(KCT*kon_CT))))+((-1.0)*(1.0*(k_R2T_N/(lN*cN)))))*y[162]+(((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_CT)))*y[162]*y[192]+((-1.0)*(1.0*kon_rbp))*y[162]*y[193]+((-1.0)*(1.0*kon_tbp))*y[162]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_TT*kon_rbp))))*y[163]+((-1.0)*(1.0*(-(Kd_tbp_TT*kon_tbp))))*y[164]+((-1.0)*(1.0*(-(KBT*kon_AT))))*y[177]+((-1.0)*(1.0*(-(KDT*kon_CT))))*y[183]
            dy[163]=1.0*kon_CT*y[148]*y[192]+1.0*kon_AT*y[154]*y[192]+1.0*kon_rbp*y[162]*y[193]+(((1.0*(-(KAT*kon_AT)))+(1.0*(-(Kd_rbp_TT*kon_rbp))))+(1.0*(-(KCT*kon_CT))))*y[163]+(((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_CT)))*y[163]*y[192]+((-1.0)*(1.0*(-(KBT*kon_AT))))*y[178]+((-1.0)*(1.0*(-(KDT*kon_CT))))*y[184]
            dy[164]=1.0*kon_CT*y[149]*y[192]+1.0*kon_AT*y[155]*y[192]+1.0*kon_tbp*y[162]*y[194]+(((1.0*(-(Kd_tbp_TT*kon_tbp)))+(1.0*(-(KAT*kon_AT))))+(1.0*(-(KCT*kon_CT))))*y[164]+(((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_CT)))*y[164]*y[192]+((-1.0)*(1.0*(-(KBT*kon_AT))))*y[179]+((-1.0)*(1.0*(-(KDT*kon_CT))))*y[185]
            dy[165]=((-1.0)*(1.0*(-k_R2T_N)))*y[69]+((-1.0)*(1.0*(-k_R2T_C)))*y[117]+1.0*kon_CT*y[147]*y[192]+1.0*kon_AT*y[156]*y[192]+((((1.0*(-(KAT*kon_AT)))+((-1.0)*(1.0*(k_R2T_C/(lC*cC)))))+(1.0*(-(KDT*kon_CT))))+((-1.0)*(1.0*(k_R2T_N/(lN*cN)))))*y[165]+(((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_CT)))*y[165]*y[192]+((-1.0)*(1.0*kon_rbp))*y[165]*y[193]+((-1.0)*(1.0*kon_tbp))*y[165]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_TT*kon_rbp))))*y[166]+((-1.0)*(1.0*(-(Kd_tbp_TT*kon_tbp))))*y[167]+((-1.0)*(1.0*(-(KBT*kon_AT))))*y[180]+((-1.0)*(1.0*(-(KCT*kon_CT))))*y[183]
            dy[166]=1.0*kon_CT*y[148]*y[192]+1.0*kon_AT*y[157]*y[192]+1.0*kon_rbp*y[165]*y[193]+(((1.0*(-(KAT*kon_AT)))+(1.0*(-(Kd_rbp_TT*kon_rbp))))+(1.0*(-(KDT*kon_CT))))*y[166]+(((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_CT)))*y[166]*y[192]+((-1.0)*(1.0*(-(KBT*kon_AT))))*y[181]+((-1.0)*(1.0*(-(KCT*kon_CT))))*y[184]
            dy[167]=1.0*kon_CT*y[149]*y[192]+1.0*kon_AT*y[158]*y[192]+1.0*kon_tbp*y[165]*y[194]+(((1.0*(-(Kd_tbp_TT*kon_tbp)))+(1.0*(-(KAT*kon_AT))))+(1.0*(-(KDT*kon_CT))))*y[167]+(((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_CT)))*y[167]*y[192]+((-1.0)*(1.0*(-(KBT*kon_AT))))*y[182]+((-1.0)*(1.0*(-(KCT*kon_CT))))*y[185]
            dy[168]=((-1.0)*(1.0*(-k_R2T_N)))*y[72]+((-1.0)*(1.0*(-k_R2T_C)))*y[120]+1.0*kon_CT*y[150]*y[192]+1.0*kon_AT*y[153]*y[192]+((((1.0*(-(KBT*kon_AT)))+((-1.0)*(1.0*(k_R2T_C/(lC*cC)))))+(1.0*(-(KCT*kon_CT))))+((-1.0)*(1.0*(k_R2T_N/(lN*cN)))))*y[168]+(((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_CT)))*y[168]*y[192]+((-1.0)*(1.0*kon_rbp))*y[168]*y[193]+((-1.0)*(1.0*kon_tbp))*y[168]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_TT*kon_rbp))))*y[169]+((-1.0)*(1.0*(-(Kd_tbp_TT*kon_tbp))))*y[170]+((-1.0)*(1.0*(-(KAT*kon_AT))))*y[177]+((-1.0)*(1.0*(-(KDT*kon_CT))))*y[186]
            dy[169]=1.0*kon_CT*y[151]*y[192]+1.0*kon_AT*y[154]*y[192]+1.0*kon_rbp*y[168]*y[193]+(((1.0*(-(KBT*kon_AT)))+(1.0*(-(Kd_rbp_TT*kon_rbp))))+(1.0*(-(KCT*kon_CT))))*y[169]+(((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_CT)))*y[169]*y[192]+((-1.0)*(1.0*(-(KAT*kon_AT))))*y[178]+((-1.0)*(1.0*(-(KDT*kon_CT))))*y[187]
            dy[170]=1.0*kon_CT*y[152]*y[192]+1.0*kon_AT*y[155]*y[192]+1.0*kon_tbp*y[168]*y[194]+(((1.0*(-(Kd_tbp_TT*kon_tbp)))+(1.0*(-(KBT*kon_AT))))+(1.0*(-(KCT*kon_CT))))*y[170]+(((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_CT)))*y[170]*y[192]+((-1.0)*(1.0*(-(KAT*kon_AT))))*y[179]+((-1.0)*(1.0*(-(KDT*kon_CT))))*y[188]
            dy[171]=((-1.0)*(1.0*(-k_R2T_N)))*y[75]+((-1.0)*(1.0*(-k_R2T_C)))*y[123]+1.0*kon_CT*y[150]*y[192]+1.0*kon_AT*y[156]*y[192]+((((1.0*(-(KBT*kon_AT)))+((-1.0)*(1.0*(k_R2T_C/(lC*cC)))))+(1.0*(-(KDT*kon_CT))))+((-1.0)*(1.0*(k_R2T_N/(lN*cN)))))*y[171]+(((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_CT)))*y[171]*y[192]+((-1.0)*(1.0*kon_rbp))*y[171]*y[193]+((-1.0)*(1.0*kon_tbp))*y[171]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_TT*kon_rbp))))*y[172]+((-1.0)*(1.0*(-(Kd_tbp_TT*kon_tbp))))*y[173]+((-1.0)*(1.0*(-(KAT*kon_AT))))*y[180]+((-1.0)*(1.0*(-(KCT*kon_CT))))*y[186]
            dy[172]=1.0*kon_CT*y[151]*y[192]+1.0*kon_AT*y[157]*y[192]+1.0*kon_rbp*y[171]*y[193]+(((1.0*(-(KBT*kon_AT)))+(1.0*(-(Kd_rbp_TT*kon_rbp))))+(1.0*(-(KDT*kon_CT))))*y[172]+(((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_CT)))*y[172]*y[192]+((-1.0)*(1.0*(-(KAT*kon_AT))))*y[181]+((-1.0)*(1.0*(-(KCT*kon_CT))))*y[187]
            dy[173]=1.0*kon_CT*y[152]*y[192]+1.0*kon_AT*y[158]*y[192]+1.0*kon_tbp*y[171]*y[194]+(((1.0*(-(Kd_tbp_TT*kon_tbp)))+(1.0*(-(KBT*kon_AT))))+(1.0*(-(KDT*kon_CT))))*y[173]+(((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_CT)))*y[173]*y[192]+((-1.0)*(1.0*(-(KAT*kon_AT))))*y[182]+((-1.0)*(1.0*(-(KCT*kon_CT))))*y[188]
            dy[174]=((-1.0)*(1.0*(-k_R2T_N)))*y[78]+((-1.0)*(1.0*(-k_R2T_C)))*y[126]+1.0*kon_CT*y[153]*y[192]+1.0*kon_CT*y[156]*y[192]+(((((-1.0)*(1.0*(k_R2T_C/((lC*cC)*cC))))+(1.0*(-(KCT*kon_CT))))+(1.0*(-(KDT*kon_CT))))+((-1.0)*(1.0*(k_R2T_N/lN))))*y[174]+(((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_AT)))*y[174]*y[192]+((-1.0)*(1.0*kon_rbp))*y[174]*y[193]+((-1.0)*(1.0*kon_tbp))*y[174]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_TT*kon_rbp))))*y[175]+((-1.0)*(1.0*(-(Kd_tbp_TT*kon_tbp))))*y[176]+((-1.0)*(1.0*(-(KAT*kon_AT))))*y[183]+((-1.0)*(1.0*(-(KBT*kon_AT))))*y[186]
            dy[175]=1.0*kon_CT*y[154]*y[192]+1.0*kon_CT*y[157]*y[192]+1.0*kon_rbp*y[174]*y[193]+(((1.0*(-(Kd_rbp_TT*kon_rbp)))+(1.0*(-(KCT*kon_CT))))+(1.0*(-(KDT*kon_CT))))*y[175]+(((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_AT)))*y[175]*y[192]+((-1.0)*(1.0*(-(KAT*kon_AT))))*y[184]+((-1.0)*(1.0*(-(KBT*kon_AT))))*y[187]
            dy[176]=1.0*kon_CT*y[155]*y[192]+1.0*kon_CT*y[158]*y[192]+1.0*kon_tbp*y[174]*y[194]+(((1.0*(-(Kd_tbp_TT*kon_tbp)))+(1.0*(-(KCT*kon_CT))))+(1.0*(-(KDT*kon_CT))))*y[176]+(((-1.0)*(1.0*kon_AT))+((-1.0)*(1.0*kon_AT)))*y[176]*y[192]+((-1.0)*(1.0*(-(KAT*kon_AT))))*y[185]+((-1.0)*(1.0*(-(KBT*kon_AT))))*y[188]
            dy[177]=((-1.0)*(1.0*(-k_R2T_N)))*y[81]+((-1.0)*(1.0*(-k_R2T_C)))*y[129]+1.0*kon_CT*y[159]*y[192]+1.0*kon_AT*y[162]*y[192]+1.0*kon_AT*y[168]*y[192]+((((((-1.0)*(1.0*(k_R2T_N/((lN*cN)*cN))))+(1.0*(-(KAT*kon_AT))))+(1.0*(-(KBT*kon_AT))))+((-1.0)*(1.0*(k_R2T_C/(lC*cC)))))+(1.0*(-(KCT*kon_CT))))*y[177]+((-1.0)*(1.0*kon_CT))*y[177]*y[192]+((-1.0)*(1.0*kon_rbp))*y[177]*y[193]+((-1.0)*(1.0*kon_tbp))*y[177]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_TT*kon_rbp))))*y[178]+((-1.0)*(1.0*(-(Kd_tbp_TT*kon_tbp))))*y[179]+((-1.0)*(1.0*(-(KDT*kon_CT))))*y[189]
            dy[178]=1.0*kon_CT*y[160]*y[192]+1.0*kon_AT*y[163]*y[192]+1.0*kon_AT*y[169]*y[192]+1.0*kon_rbp*y[177]*y[193]+((((1.0*(-(KAT*kon_AT)))+(1.0*(-(KBT*kon_AT))))+(1.0*(-(Kd_rbp_TT*kon_rbp))))+(1.0*(-(KCT*kon_CT))))*y[178]+((-1.0)*(1.0*kon_CT))*y[178]*y[192]+((-1.0)*(1.0*(-(KDT*kon_CT))))*y[190]
            dy[179]=1.0*kon_CT*y[161]*y[192]+1.0*kon_AT*y[164]*y[192]+1.0*kon_AT*y[170]*y[192]+1.0*kon_tbp*y[177]*y[194]+((((1.0*(-(Kd_tbp_TT*kon_tbp)))+(1.0*(-(KAT*kon_AT))))+(1.0*(-(KBT*kon_AT))))+(1.0*(-(KCT*kon_CT))))*y[179]+((-1.0)*(1.0*kon_CT))*y[179]*y[192]+((-1.0)*(1.0*(-(KDT*kon_CT))))*y[191]
            dy[180]=((-1.0)*(1.0*(-k_R2T_N)))*y[84]+((-1.0)*(1.0*(-k_R2T_C)))*y[132]+1.0*kon_CT*y[159]*y[192]+1.0*kon_AT*y[165]*y[192]+1.0*kon_AT*y[171]*y[192]+((((((-1.0)*(1.0*(k_R2T_N/((lN*cN)*cN))))+(1.0*(-(KAT*kon_AT))))+(1.0*(-(KBT*kon_AT))))+((-1.0)*(1.0*(k_R2T_C/(lC*cC)))))+(1.0*(-(KDT*kon_CT))))*y[180]+((-1.0)*(1.0*kon_CT))*y[180]*y[192]+((-1.0)*(1.0*kon_rbp))*y[180]*y[193]+((-1.0)*(1.0*kon_tbp))*y[180]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_TT*kon_rbp))))*y[181]+((-1.0)*(1.0*(-(Kd_tbp_TT*kon_tbp))))*y[182]+((-1.0)*(1.0*(-(KCT*kon_CT))))*y[189]
            dy[181]=1.0*kon_CT*y[160]*y[192]+1.0*kon_AT*y[166]*y[192]+1.0*kon_AT*y[172]*y[192]+1.0*kon_rbp*y[180]*y[193]+((((1.0*(-(KAT*kon_AT)))+(1.0*(-(KBT*kon_AT))))+(1.0*(-(Kd_rbp_TT*kon_rbp))))+(1.0*(-(KDT*kon_CT))))*y[181]+((-1.0)*(1.0*kon_CT))*y[181]*y[192]+((-1.0)*(1.0*(-(KCT*kon_CT))))*y[190]
            dy[182]=1.0*kon_CT*y[161]*y[192]+1.0*kon_AT*y[167]*y[192]+1.0*kon_AT*y[173]*y[192]+1.0*kon_tbp*y[180]*y[194]+((((1.0*(-(Kd_tbp_TT*kon_tbp)))+(1.0*(-(KAT*kon_AT))))+(1.0*(-(KBT*kon_AT))))+(1.0*(-(KDT*kon_CT))))*y[182]+((-1.0)*(1.0*kon_CT))*y[182]*y[192]+((-1.0)*(1.0*(-(KCT*kon_CT))))*y[191]
            dy[183]=((-1.0)*(1.0*(-k_R2T_N)))*y[87]+((-1.0)*(1.0*(-k_R2T_C)))*y[135]+1.0*kon_CT*y[162]*y[192]+1.0*kon_CT*y[165]*y[192]+1.0*kon_AT*y[174]*y[192]+((((((-1.0)*(1.0*(k_R2T_C/((lC*cC)*cC))))+(1.0*(-(KAT*kon_AT))))+(1.0*(-(KCT*kon_CT))))+(1.0*(-(KDT*kon_CT))))+((-1.0)*(1.0*(k_R2T_N/(lN*cN)))))*y[183]+((-1.0)*(1.0*kon_AT))*y[183]*y[192]+((-1.0)*(1.0*kon_rbp))*y[183]*y[193]+((-1.0)*(1.0*kon_tbp))*y[183]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_TT*kon_rbp))))*y[184]+((-1.0)*(1.0*(-(Kd_tbp_TT*kon_tbp))))*y[185]+((-1.0)*(1.0*(-(KBT*kon_AT))))*y[189]
            dy[184]=1.0*kon_CT*y[163]*y[192]+1.0*kon_CT*y[166]*y[192]+1.0*kon_AT*y[175]*y[192]+1.0*kon_rbp*y[183]*y[193]+((((1.0*(-(KAT*kon_AT)))+(1.0*(-(Kd_rbp_TT*kon_rbp))))+(1.0*(-(KCT*kon_CT))))+(1.0*(-(KDT*kon_CT))))*y[184]+((-1.0)*(1.0*kon_AT))*y[184]*y[192]+((-1.0)*(1.0*(-(KBT*kon_AT))))*y[190]
            dy[185]=1.0*kon_CT*y[164]*y[192]+1.0*kon_CT*y[167]*y[192]+1.0*kon_AT*y[176]*y[192]+1.0*kon_tbp*y[183]*y[194]+((((1.0*(-(Kd_tbp_TT*kon_tbp)))+(1.0*(-(KAT*kon_AT))))+(1.0*(-(KCT*kon_CT))))+(1.0*(-(KDT*kon_CT))))*y[185]+((-1.0)*(1.0*kon_AT))*y[185]*y[192]+((-1.0)*(1.0*(-(KBT*kon_AT))))*y[191]
            dy[186]=((-1.0)*(1.0*(-k_R2T_N)))*y[90]+((-1.0)*(1.0*(-k_R2T_C)))*y[138]+1.0*kon_CT*y[168]*y[192]+1.0*kon_CT*y[171]*y[192]+1.0*kon_AT*y[174]*y[192]+((((((-1.0)*(1.0*(k_R2T_C/((lC*cC)*cC))))+(1.0*(-(KBT*kon_AT))))+(1.0*(-(KCT*kon_CT))))+(1.0*(-(KDT*kon_CT))))+((-1.0)*(1.0*(k_R2T_N/(lN*cN)))))*y[186]+((-1.0)*(1.0*kon_AT))*y[186]*y[192]+((-1.0)*(1.0*kon_rbp))*y[186]*y[193]+((-1.0)*(1.0*kon_tbp))*y[186]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_TT*kon_rbp))))*y[187]+((-1.0)*(1.0*(-(Kd_tbp_TT*kon_tbp))))*y[188]+((-1.0)*(1.0*(-(KAT*kon_AT))))*y[189]
            dy[187]=1.0*kon_CT*y[169]*y[192]+1.0*kon_CT*y[172]*y[192]+1.0*kon_AT*y[175]*y[192]+1.0*kon_rbp*y[186]*y[193]+((((1.0*(-(KBT*kon_AT)))+(1.0*(-(Kd_rbp_TT*kon_rbp))))+(1.0*(-(KCT*kon_CT))))+(1.0*(-(KDT*kon_CT))))*y[187]+((-1.0)*(1.0*kon_AT))*y[187]*y[192]+((-1.0)*(1.0*(-(KAT*kon_AT))))*y[190]
            dy[188]=1.0*kon_CT*y[170]*y[192]+1.0*kon_CT*y[173]*y[192]+1.0*kon_AT*y[176]*y[192]+1.0*kon_tbp*y[186]*y[194]+((((1.0*(-(Kd_tbp_TT*kon_tbp)))+(1.0*(-(KBT*kon_AT))))+(1.0*(-(KCT*kon_CT))))+(1.0*(-(KDT*kon_CT))))*y[188]+((-1.0)*(1.0*kon_AT))*y[188]*y[192]+((-1.0)*(1.0*(-(KAT*kon_AT))))*y[191]
            dy[189]=((-1.0)*(1.0*(-k_R2T_N)))*y[93]+((-1.0)*(1.0*(-k_R2T_C)))*y[141]+1.0*kon_CT*y[177]*y[192]+1.0*kon_CT*y[180]*y[192]+1.0*kon_AT*y[183]*y[192]+1.0*kon_AT*y[186]*y[192]+(((((((-1.0)*(1.0*(k_R2T_C/((lC*cC)*cC))))+((-1.0)*(1.0*(k_R2T_N/((lN*cN)*cN)))))+(1.0*(-(KAT*kon_AT))))+(1.0*(-(KBT*kon_AT))))+(1.0*(-(KCT*kon_CT))))+(1.0*(-(KDT*kon_CT))))*y[189]+((-1.0)*(1.0*kon_rbp))*y[189]*y[193]+((-1.0)*(1.0*kon_tbp))*y[189]*y[194]+((-1.0)*(1.0*(-(Kd_rbp_TT*kon_rbp))))*y[190]+((-1.0)*(1.0*(-(Kd_tbp_TT*kon_tbp))))*y[191]
            dy[190]=1.0*kon_CT*y[178]*y[192]+1.0*kon_CT*y[181]*y[192]+1.0*kon_AT*y[184]*y[192]+1.0*kon_AT*y[187]*y[192]+1.0*kon_rbp*y[189]*y[193]+(((((1.0*(-(KAT*kon_AT)))+(1.0*(-(KBT*kon_AT))))+(1.0*(-(Kd_rbp_TT*kon_rbp))))+(1.0*(-(KCT*kon_CT))))+(1.0*(-(KDT*kon_CT))))*y[190]
            dy[191]=1.0*kon_CT*y[179]*y[192]+1.0*kon_CT*y[182]*y[192]+1.0*kon_AT*y[185]*y[192]+1.0*kon_AT*y[188]*y[192]+1.0*kon_tbp*y[189]*y[194]+(((((1.0*(-(Kd_tbp_TT*kon_tbp)))+(1.0*(-(KAT*kon_AT))))+(1.0*(-(KBT*kon_AT))))+(1.0*(-(KCT*kon_CT))))+(1.0*(-(KDT*kon_CT))))*y[191]
            dy[192]=0.0
            dy[193]=((-1.0)*(1.0*kon_rbp))*y[0]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_RR*kon_rbp))))*y[1]+((-1.0)*(1.0*kon_rbp))*y[3]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_RR*kon_rbp))))*y[4]+((-1.0)*(1.0*kon_rbp))*y[6]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_RR*kon_rbp))))*y[7]+((-1.0)*(1.0*kon_rbp))*y[9]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_RR*kon_rbp))))*y[10]+((-1.0)*(1.0*kon_rbp))*y[12]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_RR*kon_rbp))))*y[13]+((-1.0)*(1.0*kon_rbp))*y[15]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_RR*kon_rbp))))*y[16]+((-1.0)*(1.0*kon_rbp))*y[18]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_RR*kon_rbp))))*y[19]+((-1.0)*(1.0*kon_rbp))*y[21]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_RR*kon_rbp))))*y[22]+((-1.0)*(1.0*kon_rbp))*y[24]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_RR*kon_rbp))))*y[25]+((-1.0)*(1.0*kon_rbp))*y[27]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_RR*kon_rbp))))*y[28]+((-1.0)*(1.0*kon_rbp))*y[30]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_RR*kon_rbp))))*y[31]+((-1.0)*(1.0*kon_rbp))*y[33]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_RR*kon_rbp))))*y[34]+((-1.0)*(1.0*kon_rbp))*y[36]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_RR*kon_rbp))))*y[37]+((-1.0)*(1.0*kon_rbp))*y[39]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_RR*kon_rbp))))*y[40]+((-1.0)*(1.0*kon_rbp))*y[42]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_RR*kon_rbp))))*y[43]+((-1.0)*(1.0*kon_rbp))*y[45]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_RR*kon_rbp))))*y[46]+((-1.0)*(1.0*kon_rbp))*y[48]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_RT*kon_rbp))))*y[49]+((-1.0)*(1.0*kon_rbp))*y[51]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_RT*kon_rbp))))*y[52]+((-1.0)*(1.0*kon_rbp))*y[54]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_RT*kon_rbp))))*y[55]+((-1.0)*(1.0*kon_rbp))*y[57]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_RT*kon_rbp))))*y[58]+((-1.0)*(1.0*kon_rbp))*y[60]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_RT*kon_rbp))))*y[61]+((-1.0)*(1.0*kon_rbp))*y[63]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_RT*kon_rbp))))*y[64]+((-1.0)*(1.0*kon_rbp))*y[66]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_RT*kon_rbp))))*y[67]+((-1.0)*(1.0*kon_rbp))*y[69]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_RT*kon_rbp))))*y[70]+((-1.0)*(1.0*kon_rbp))*y[72]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_RT*kon_rbp))))*y[73]+((-1.0)*(1.0*kon_rbp))*y[75]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_RT*kon_rbp))))*y[76]+((-1.0)*(1.0*kon_rbp))*y[78]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_RT*kon_rbp))))*y[79]+((-1.0)*(1.0*kon_rbp))*y[81]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_RT*kon_rbp))))*y[82]+((-1.0)*(1.0*kon_rbp))*y[84]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_RT*kon_rbp))))*y[85]+((-1.0)*(1.0*kon_rbp))*y[87]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_RT*kon_rbp))))*y[88]+((-1.0)*(1.0*kon_rbp))*y[90]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_RT*kon_rbp))))*y[91]+((-1.0)*(1.0*kon_rbp))*y[93]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_RT*kon_rbp))))*y[94]+((-1.0)*(1.0*kon_rbp))*y[96]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_TR*kon_rbp))))*y[97]+((-1.0)*(1.0*kon_rbp))*y[99]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_TR*kon_rbp))))*y[100]+((-1.0)*(1.0*kon_rbp))*y[102]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_TR*kon_rbp))))*y[103]+((-1.0)*(1.0*kon_rbp))*y[105]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_TR*kon_rbp))))*y[106]+((-1.0)*(1.0*kon_rbp))*y[108]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_TR*kon_rbp))))*y[109]+((-1.0)*(1.0*kon_rbp))*y[111]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_TR*kon_rbp))))*y[112]+((-1.0)*(1.0*kon_rbp))*y[114]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_TR*kon_rbp))))*y[115]+((-1.0)*(1.0*kon_rbp))*y[117]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_TR*kon_rbp))))*y[118]+((-1.0)*(1.0*kon_rbp))*y[120]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_TR*kon_rbp))))*y[121]+((-1.0)*(1.0*kon_rbp))*y[123]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_TR*kon_rbp))))*y[124]+((-1.0)*(1.0*kon_rbp))*y[126]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_TR*kon_rbp))))*y[127]+((-1.0)*(1.0*kon_rbp))*y[129]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_TR*kon_rbp))))*y[130]+((-1.0)*(1.0*kon_rbp))*y[132]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_TR*kon_rbp))))*y[133]+((-1.0)*(1.0*kon_rbp))*y[135]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_TR*kon_rbp))))*y[136]+((-1.0)*(1.0*kon_rbp))*y[138]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_TR*kon_rbp))))*y[139]+((-1.0)*(1.0*kon_rbp))*y[141]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_TR*kon_rbp))))*y[142]+((-1.0)*(1.0*kon_rbp))*y[144]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_TT*kon_rbp))))*y[145]+((-1.0)*(1.0*kon_rbp))*y[147]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_TT*kon_rbp))))*y[148]+((-1.0)*(1.0*kon_rbp))*y[150]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_TT*kon_rbp))))*y[151]+((-1.0)*(1.0*kon_rbp))*y[153]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_TT*kon_rbp))))*y[154]+((-1.0)*(1.0*kon_rbp))*y[156]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_TT*kon_rbp))))*y[157]+((-1.0)*(1.0*kon_rbp))*y[159]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_TT*kon_rbp))))*y[160]+((-1.0)*(1.0*kon_rbp))*y[162]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_TT*kon_rbp))))*y[163]+((-1.0)*(1.0*kon_rbp))*y[165]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_TT*kon_rbp))))*y[166]+((-1.0)*(1.0*kon_rbp))*y[168]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_TT*kon_rbp))))*y[169]+((-1.0)*(1.0*kon_rbp))*y[171]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_TT*kon_rbp))))*y[172]+((-1.0)*(1.0*kon_rbp))*y[174]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_TT*kon_rbp))))*y[175]+((-1.0)*(1.0*kon_rbp))*y[177]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_TT*kon_rbp))))*y[178]+((-1.0)*(1.0*kon_rbp))*y[180]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_TT*kon_rbp))))*y[181]+((-1.0)*(1.0*kon_rbp))*y[183]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_TT*kon_rbp))))*y[184]+((-1.0)*(1.0*kon_rbp))*y[186]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_TT*kon_rbp))))*y[187]+((-1.0)*(1.0*kon_rbp))*y[189]*y[193]+((-1.0)*(1.0*(-(Kd_rbp_TT*kon_rbp))))*y[190]
            dy[194]=((-1.0)*(1.0*kon_tbp))*y[0]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_RR*kon_tbp))))*y[2]+((-1.0)*(1.0*kon_tbp))*y[3]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_RR*kon_tbp))))*y[5]+((-1.0)*(1.0*kon_tbp))*y[6]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_RR*kon_tbp))))*y[8]+((-1.0)*(1.0*kon_tbp))*y[9]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_RR*kon_tbp))))*y[11]+((-1.0)*(1.0*kon_tbp))*y[12]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_RR*kon_tbp))))*y[14]+((-1.0)*(1.0*kon_tbp))*y[15]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_RR*kon_tbp))))*y[17]+((-1.0)*(1.0*kon_tbp))*y[18]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_RR*kon_tbp))))*y[20]+((-1.0)*(1.0*kon_tbp))*y[21]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_RR*kon_tbp))))*y[23]+((-1.0)*(1.0*kon_tbp))*y[24]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_RR*kon_tbp))))*y[26]+((-1.0)*(1.0*kon_tbp))*y[27]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_RR*kon_tbp))))*y[29]+((-1.0)*(1.0*kon_tbp))*y[30]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_RR*kon_tbp))))*y[32]+((-1.0)*(1.0*kon_tbp))*y[33]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_RR*kon_tbp))))*y[35]+((-1.0)*(1.0*kon_tbp))*y[36]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_RR*kon_tbp))))*y[38]+((-1.0)*(1.0*kon_tbp))*y[39]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_RR*kon_tbp))))*y[41]+((-1.0)*(1.0*kon_tbp))*y[42]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_RR*kon_tbp))))*y[44]+((-1.0)*(1.0*kon_tbp))*y[45]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_RR*kon_tbp))))*y[47]+((-1.0)*(1.0*kon_tbp))*y[48]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_RT*kon_tbp))))*y[50]+((-1.0)*(1.0*kon_tbp))*y[51]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_RT*kon_tbp))))*y[53]+((-1.0)*(1.0*kon_tbp))*y[54]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_RT*kon_tbp))))*y[56]+((-1.0)*(1.0*kon_tbp))*y[57]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_RT*kon_tbp))))*y[59]+((-1.0)*(1.0*kon_tbp))*y[60]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_RT*kon_tbp))))*y[62]+((-1.0)*(1.0*kon_tbp))*y[63]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_RT*kon_tbp))))*y[65]+((-1.0)*(1.0*kon_tbp))*y[66]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_RT*kon_tbp))))*y[68]+((-1.0)*(1.0*kon_tbp))*y[69]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_RT*kon_tbp))))*y[71]+((-1.0)*(1.0*kon_tbp))*y[72]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_RT*kon_tbp))))*y[74]+((-1.0)*(1.0*kon_tbp))*y[75]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_RT*kon_tbp))))*y[77]+((-1.0)*(1.0*kon_tbp))*y[78]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_RT*kon_tbp))))*y[80]+((-1.0)*(1.0*kon_tbp))*y[81]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_RT*kon_tbp))))*y[83]+((-1.0)*(1.0*kon_tbp))*y[84]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_RT*kon_tbp))))*y[86]+((-1.0)*(1.0*kon_tbp))*y[87]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_RT*kon_tbp))))*y[89]+((-1.0)*(1.0*kon_tbp))*y[90]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_RT*kon_tbp))))*y[92]+((-1.0)*(1.0*kon_tbp))*y[93]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_RT*kon_tbp))))*y[95]+((-1.0)*(1.0*kon_tbp))*y[96]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_TR*kon_tbp))))*y[98]+((-1.0)*(1.0*kon_tbp))*y[99]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_TR*kon_tbp))))*y[101]+((-1.0)*(1.0*kon_tbp))*y[102]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_TR*kon_tbp))))*y[104]+((-1.0)*(1.0*kon_tbp))*y[105]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_TR*kon_tbp))))*y[107]+((-1.0)*(1.0*kon_tbp))*y[108]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_TR*kon_tbp))))*y[110]+((-1.0)*(1.0*kon_tbp))*y[111]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_TR*kon_tbp))))*y[113]+((-1.0)*(1.0*kon_tbp))*y[114]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_TR*kon_tbp))))*y[116]+((-1.0)*(1.0*kon_tbp))*y[117]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_TR*kon_tbp))))*y[119]+((-1.0)*(1.0*kon_tbp))*y[120]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_TR*kon_tbp))))*y[122]+((-1.0)*(1.0*kon_tbp))*y[123]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_TR*kon_tbp))))*y[125]+((-1.0)*(1.0*kon_tbp))*y[126]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_TR*kon_tbp))))*y[128]+((-1.0)*(1.0*kon_tbp))*y[129]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_TR*kon_tbp))))*y[131]+((-1.0)*(1.0*kon_tbp))*y[132]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_TR*kon_tbp))))*y[134]+((-1.0)*(1.0*kon_tbp))*y[135]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_TR*kon_tbp))))*y[137]+((-1.0)*(1.0*kon_tbp))*y[138]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_TR*kon_tbp))))*y[140]+((-1.0)*(1.0*kon_tbp))*y[141]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_TR*kon_tbp))))*y[143]+((-1.0)*(1.0*kon_tbp))*y[144]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_TT*kon_tbp))))*y[146]+((-1.0)*(1.0*kon_tbp))*y[147]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_TT*kon_tbp))))*y[149]+((-1.0)*(1.0*kon_tbp))*y[150]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_TT*kon_tbp))))*y[152]+((-1.0)*(1.0*kon_tbp))*y[153]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_TT*kon_tbp))))*y[155]+((-1.0)*(1.0*kon_tbp))*y[156]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_TT*kon_tbp))))*y[158]+((-1.0)*(1.0*kon_tbp))*y[159]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_TT*kon_tbp))))*y[161]+((-1.0)*(1.0*kon_tbp))*y[162]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_TT*kon_tbp))))*y[164]+((-1.0)*(1.0*kon_tbp))*y[165]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_TT*kon_tbp))))*y[167]+((-1.0)*(1.0*kon_tbp))*y[168]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_TT*kon_tbp))))*y[170]+((-1.0)*(1.0*kon_tbp))*y[171]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_TT*kon_tbp))))*y[173]+((-1.0)*(1.0*kon_tbp))*y[174]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_TT*kon_tbp))))*y[176]+((-1.0)*(1.0*kon_tbp))*y[177]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_TT*kon_tbp))))*y[179]+((-1.0)*(1.0*kon_tbp))*y[180]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_TT*kon_tbp))))*y[182]+((-1.0)*(1.0*kon_tbp))*y[183]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_TT*kon_tbp))))*y[185]+((-1.0)*(1.0*kon_tbp))*y[186]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_TT*kon_tbp))))*y[188]+((-1.0)*(1.0*kon_tbp))*y[189]*y[194]+((-1.0)*(1.0*(-(Kd_tbp_TT*kon_tbp))))*y[191]

            dy
        }
    }
}

//BIOMD0000000504
class T101ModelCreator  {
    fun createModel(reduction: Int) : ModelInfo {
        return ModelInfo(
                randMatrix(75 - reduction, 75, -1.0 until 1.0).MGSON())
        { y: SimpleMatrix ->
            val dy = SimpleMatrix(y)

            val kactMMP13mmp3 = 5.0E-8

            val kactMMP1mat = 1.0E-9

            val kactMMP1mmp3 = 1.0E-8

            val kactMMP3mat = 4.0E-6

            val kAP1activity = 1.0

            val kbincFoscJun = 5.0E-5

            val kbinIL1IL1R = 1.0E-4

            val kbinIL1IL1Ra = 1.0E-4

            val kbinIRAK2 = 5.0E-5

            val kbinOSMOSMR = 1.0E-5

            val kbinOSMOSMRa = 1.0E-4

            val kbinSOCS3OSMR = 0.005

            val kbinSP1TIMP1DNA = 1.0E-5

            val kbinTRAF6 = 1.0E-5

            val kcyt2nucSTAT3 = 0.001

            val kdedimercJun = 0.01

            val kdegADAMTS4 = 5.0E-5

            val kdegADAMTS4mRNA = 1.4E-5

            val kdegAggrecan = 3.0E-8

            val kdegcFos = 2.0E-4

            val kdegcFosmRNA = 0.003

            val kdegcJun = 1.3E-4

            val kdegcJunmRNA = 0.003

            val kdegCollagen2mmp1 = 5.0E-12

            val kdegCollagen2mmp13 = 5.0E-11

            val kdegDUSP16 = 1.3E-4

            val kdegIL1 = 2.0E-4

            val kdegMatriptase = 8.0E-6

            val kdegMKP1 = 1.0E-4

            val kdegMMP1 = 1.0E-6

            val kdegMMP13 = 1.0E-6

            val kdegMMP13mRNA = 6.4E-6

            val kdegMMP1mRNA = 6.4E-6

            val kdegMMP3 = 1.0E-6

            val kdegMMP3mRNA = 6.4E-6

            val kdegOSM = 4.8E-5

            val kdegPP4 = 1.0E-4

            val kdegPTPRT = 5.0E-5

            val kdegSOCS3 = 8.0E-4

            val kdegSOCS3mRNA = 4.0E-4

            val kdegSP1 = 2.0E-5

            val kdegTIMP1 = 2.0E-5

            val kdegTIMP1mRNA = 1.4E-5

            val kdegTIMP3 = 2.0E-5

            val kdegTIMP3mRNA = 1.4E-5

            val kdephoscFos = 1.0E-4

            val kdephoscFosDUSP16 = 1.0E-4

            val kdephoscJun = 0.01

            val kdephosJAK1 = 4.0E-4

            val kdephosJAK1PTPRT = 0.004

            val kdephosJNK = 0.001

            val kdephosJNKDUSP16 = 0.001

            val kdephosp38 = 0.001

            val kdephosp38MKP1 = 1.0E-5

            val kdephosSTAT3 = 1.0E-5

            val kdephosSTAT3nuc = 1.0E-7

            val kdephosSTAT3nucPTPRT = 5.0E-4

            val kdephosSTAT3PTPRT = 8.0E-4

            val kdimercJun = 5.0E-5

            val kinhibADAMTS4TIMP1 = 3.0E-6

            val kinhibADAMTS4TIMP3 = 5.0E-4

            val kinhibMMP13TIMP1 = 3.0E-7

            val kinhibMMP13TIMP3 = 1.0E-8

            val kinhibMMP1TIMP1 = 3.0E-7

            val kinhibMMP1TIMP3 = 1.0E-8

            val kinhibMMP3TIMP1 = 3.0E-7

            val kinhibMMP3TIMP3 = 1.0E-8

            val kinhibTRAF6 = 0.5

            val knuc2cytSTAT3 = 0.001

            val kphoscFos = 5.0E-7

            val kphoscJun = 1.0E-4

            val kphosJAK1 = 1.0E-5

            val kphosJNK = 1.0E-4

            val kphosp38 = 1.0E-4

            val kphosSTAT3 = 0.005

            val krelADAMTS4TIMP1 = 0.001

            val krelADAMTS4TIMP3 = 0.001

            val krelcFoscJun = 4.0E-5

            val krelIL1IL1R = 0.001

            val krelIL1IL1Ra = 1.0E-4

            val krelIRAK2 = 0.001

            val krelMMP1 = 0.001

            val krelMMP13 = 0.001

            val krelMMP13TIMP3 = 0.001

            val krelMMP1TIMP3 = 0.001

            val krelMMP3 = 0.001

            val krelMMP3TIMP3 = 0.001

            val krelOSMOSMR = 1.0E-5

            val krelOSMOSMRa = 1.0E-5

            val krelSOCS3OSMR = 1.0E-5

            val krelSP1TIMP1DNA = 5.0E-6

            val krelTRAF6 = 1.0E-4

            val krelTRAF6PP4 = 1.0E-6

            val ksynADAMTS4 = 5.0E-4

            val ksynADAMTS4mRNA = 5.0E-4

            val ksynADAMTS4mRNAcJun = 4.0E-6

            val ksynbasalcJunmRNA = 0.015

            val ksynbasalTIMP1mRNA = 1.4E-4

            val ksynbasalTIMP3mRNA = 2.8E-4

            val ksyncFos = 0.001

            val ksyncFosmRNA = 5.0E-6

            val ksyncFosmRNAStat3 = 0.05

            val ksyncJun = 0.0026

            val ksyncJunmRNA = 0.0125

            val ksyncJunmRNAcJun = 0.005

            val ksynDUSP16 = 0.005

            val ksynDUSP16cJun = 2.0E-4

            val ksynMatriptase = 9.0E-10

            val ksynMKP1 = 2.5E-5

            val ksynMKP1cJun = 1.0E-6

            val ksynMMP1 = 1.5E-4

            val ksynMMP13 = 1.5E-5

            val ksynMMP13mRNA = 5.0E-4

            val ksynMMP13mRNAcJun = 2.0E-5

            val ksynMMP1mRNA = 0.005

            val ksynMMP1mRNAcJun = 2.0E-4

            val ksynMMP3 = 3.0E-5

            val ksynMMP3mRNA = 0.005

            val ksynMMP3mRNAcJun = 2.0E-4

            val ksynPP4 = 0.005

            val ksynPP4cJun = 2.0E-4

            val ksynPTPRT = 1.0E-4

            val ksynSOCS3 = 0.001

            val ksynSOCS3mRNA = 0.006

            val ksynSP1 = 2.0E-5

            val ksynTIMP1 = 2.0E-4

            val ksynTIMP1mRNA = 5.0E-7

            val ksynTIMP1mRNAStat3 = 4.0E-5

            val ksynTIMP3 = 4.0E-4

            val ksynTIMP3mRNA = 5.0E-7

            val ksynTIMP3mRNAStat3 = 4.0E-5


            dy[0]=((-1.0)*kdegADAMTS4mRNA)*y[0]+ksynADAMTS4mRNA*kAP1activity*y[64]+ksynADAMTS4mRNAcJun*y[67]
            dy[1]=((-1.0)*kdegcFos)*y[1]+((-1.0)*kphoscFos)*y[1]*y[19]+ksyncFos*y[2]+kdephoscFosDUSP16*y[5]*y[65]+kdephoscFos*y[65]
            dy[2]=((-1.0)*kdegcFosmRNA)*y[2]+ksyncFosmRNA*kAP1activity*y[64]+ksyncFosmRNAStat3*y[71]
            dy[3]=((-1.0)*kdegcJun)*y[3]+((-1.0)*kphoscJun)*y[3]*y[12]+ksyncJun*y[4]+kdephoscJun*y[66]
            dy[4]=((-1.0)*kdegcJunmRNA)*y[4]+ksyncJunmRNA*kAP1activity*y[64]+ksyncJunmRNAcJun*y[67]+ksynbasalcJunmRNA*y[73]
            dy[5]=((-1.0)*kdegDUSP16)*y[5]+ksynDUSP16*kAP1activity*y[64]+ksynDUSP16cJun*y[67]
            dy[6]=((-1.0)*kbinIRAK2)*y[6]*y[54]+krelTRAF6*y[7]+krelIRAK2*y[56]
            dy[7]=((-1.0)*krelTRAF6)*y[7]+((-1.0)*kinhibTRAF6)*y[7]*y[20]+krelTRAF6PP4*y[8]+kbinTRAF6*y[31]*y[56]
            dy[8]=kinhibTRAF6*y[7]*y[20]+((-1.0)*krelTRAF6PP4)*y[8]
            dy[9]=((-1.0)*kphosJAK1)*y[9]*y[59]+kdephosJAK1*y[10]+kdephosJAK1PTPRT*y[10]*y[24]
            dy[10]=kphosJAK1*y[9]*y[59]+((-1.0)*kdephosJAK1)*y[10]+((-1.0)*kdephosJAK1PTPRT)*y[10]*y[24]
            dy[11]=kdephosJNKDUSP16*y[5]*y[12]+((-1.0)*kphosJNK)*y[7]*y[11]+kdephosJNK*y[12]
            dy[12]=((-1.0)*kdephosJNKDUSP16)*y[5]*y[12]+kphosJNK*y[7]*y[11]+((-1.0)*kdephosJNK)*y[12]
            dy[13]=((-1.0)*kdegMatriptase)*y[13]+ksynMatriptase*kAP1activity*y[64]
            dy[14]=((-1.0)*kdegMKP1)*y[14]+ksynMKP1*kAP1activity*y[64]+ksynMKP1cJun*y[67]
            dy[15]=((-1.0)*kdegMMP1mRNA)*y[15]+ksynMMP1mRNA*kAP1activity*y[64]+ksynMMP1mRNAcJun*y[67]
            dy[16]=((-1.0)*kdegMMP3mRNA)*y[16]+ksynMMP3mRNA*kAP1activity*y[64]+ksynMMP3mRNAcJun*y[67]
            dy[17]=((-1.0)*kdegMMP13mRNA)*y[17]+ksynMMP13mRNA*kAP1activity*y[64]+ksynMMP13mRNAcJun*y[67]
            dy[18]=((-1.0)*kphosp38)*y[7]*y[18]+kdephosp38MKP1*y[14]*y[19]+kdephosp38*y[19]
            dy[19]=kphosp38*y[7]*y[18]+((-1.0)*kdephosp38MKP1)*y[14]*y[19]+((-1.0)*kdephosp38)*y[19]
            dy[20]=((-1.0)*kinhibTRAF6)*y[7]*y[20]+krelTRAF6PP4*y[8]+((-1.0)*kdegPP4)*y[20]+((-1.0)*kinhibTRAF6)*y[20]*y[31]+krelTRAF6PP4*y[32]+ksynPP4*kAP1activity*y[64]+ksynPP4cJun*y[67]
            dy[21]=((-1.0)*kactMMP1mat)*y[13]*y[21]+ksynMMP1*y[15]+((-1.0)*kactMMP1mmp3)*y[21]*y[45]
            dy[22]=((-1.0)*kactMMP3mat)*y[13]*y[22]+ksynMMP3*y[16]
            dy[23]=ksynMMP13*y[17]+((-1.0)*kactMMP13mmp3)*y[23]*y[45]
            dy[24]=((-1.0)*kdegPTPRT)*y[24]+ksynPTPRT*y[71]
            dy[25]=((-1.0)*kdegSOCS3)*y[25]+((-1.0)*kbinSOCS3OSMR)*y[25]*y[62]+ksynSOCS3*y[26]+krelSOCS3OSMR*y[61]
            dy[26]=((-1.0)*kdegSOCS3mRNA)*y[26]+ksynSOCS3mRNA*y[71]
            dy[27]=((-1.0)*kphosSTAT3)*y[10]*y[27]+kdephosSTAT3PTPRT*y[24]*y[28]+kdephosSTAT3*y[28]+knuc2cytSTAT3*y[70]
            dy[28]=kphosSTAT3*y[10]*y[27]+((-1.0)*kdephosSTAT3PTPRT)*y[24]*y[28]+(((-1.0)*kcyt2nucSTAT3)+((-1.0)*kdephosSTAT3))*y[28]
            dy[29]=((-1.0)*kdegTIMP1mRNA)*y[29]+ksynTIMP1mRNA*kAP1activity*y[64]*y[72]+ksynTIMP1mRNAStat3*y[71]*y[72]+ksynbasalTIMP1mRNA*y[72]
            dy[30]=((-1.0)*kdegTIMP3mRNA)*y[30]+ksynTIMP3mRNA*kAP1activity*y[64]+ksynTIMP3mRNAStat3*kAP1activity*y[71]+ksynbasalTIMP3mRNA*y[73]
            dy[31]=krelTRAF6*y[7]+((-1.0)*kinhibTRAF6)*y[20]*y[31]+((-1.0)*kbinTRAF6)*y[31]*y[56]+krelTRAF6PP4*y[32]
            dy[32]=kinhibTRAF6*y[20]*y[31]+((-1.0)*krelTRAF6PP4)*y[32]
            dy[33]=ksynADAMTS4*y[0]+((-1.0)*kdegADAMTS4)*y[33]+((-1.0)*kinhibADAMTS4TIMP1)*y[33]*y[52]+((-1.0)*kinhibADAMTS4TIMP3)*y[33]*y[53]+krelADAMTS4TIMP1*y[34]+krelADAMTS4TIMP3*y[35]
            dy[34]=kinhibADAMTS4TIMP1*y[33]*y[52]+((-1.0)*krelADAMTS4TIMP1)*y[34]
            dy[35]=kinhibADAMTS4TIMP3*y[33]*y[53]+((-1.0)*krelADAMTS4TIMP3)*y[35]
            dy[36]=0.0
            dy[37]=((-1.0)*kdegAggrecan)*y[33]*y[37]
            dy[38]=kdegAggrecan*y[33]*y[37]
            dy[39]=kdegCollagen2mmp1*y[40]*y[42]+kdegCollagen2mmp13*y[40]*y[48]
            dy[40]=kdegAggrecan*y[33]*y[37]+((-1.0)*kdegCollagen2mmp1)*y[40]*y[42]+((-1.0)*kdegCollagen2mmp13)*y[40]*y[48]
            dy[41]=((-1.0)*kdegIL1)*y[41]+((-1.0)*kbinIL1IL1R)*y[41]*y[57]+((-1.0)*kbinIL1IL1Ra)*y[41]*y[58]+krelIL1IL1R*y[54]+krelIL1IL1Ra*y[55]
            dy[42]=kactMMP1mat*y[13]*y[21]+kactMMP1mmp3*y[21]*y[45]+((-1.0)*kdegMMP1)*y[42]+((-1.0)*kinhibMMP1TIMP1)*y[42]*y[52]+((-1.0)*kinhibMMP1TIMP3)*y[42]*y[53]+krelMMP1*y[43]+krelMMP1TIMP3*y[44]
            dy[43]=kinhibMMP1TIMP1*y[42]*y[52]+((-1.0)*krelMMP1)*y[43]
            dy[44]=kinhibMMP1TIMP3*y[42]*y[53]+((-1.0)*krelMMP1TIMP3)*y[44]
            dy[45]=kactMMP3mat*y[13]*y[22]+((-1.0)*kdegMMP3)*y[45]+((-1.0)*kinhibMMP3TIMP1)*y[45]*y[52]+((-1.0)*kinhibMMP3TIMP3)*y[45]*y[53]+krelMMP3*y[46]+krelMMP3TIMP3*y[47]
            dy[46]=kinhibMMP3TIMP1*y[45]*y[52]+((-1.0)*krelMMP3)*y[46]
            dy[47]=kinhibMMP3TIMP3*y[45]*y[53]+((-1.0)*krelMMP3TIMP3)*y[47]
            dy[48]=kactMMP13mmp3*y[23]*y[45]+((-1.0)*kdegMMP13)*y[48]+((-1.0)*kinhibMMP13TIMP1)*y[48]*y[52]+((-1.0)*kinhibMMP13TIMP3)*y[48]*y[53]+krelMMP13*y[49]+krelMMP13TIMP3*y[50]
            dy[49]=kinhibMMP13TIMP1*y[48]*y[52]+((-1.0)*krelMMP13)*y[49]
            dy[50]=kinhibMMP13TIMP3*y[48]*y[53]+((-1.0)*krelMMP13TIMP3)*y[50]
            dy[51]=((-1.0)*kdegOSM)*y[51]+((-1.0)*kbinOSMOSMR)*y[51]*y[62]+((-1.0)*kbinOSMOSMRa)*y[51]*y[63]+krelOSMOSMR*y[59]+krelOSMOSMRa*y[60]
            dy[52]=ksynTIMP1*y[29]+((-1.0)*kinhibADAMTS4TIMP1)*y[33]*y[52]+krelADAMTS4TIMP1*y[34]+((-1.0)*kinhibMMP1TIMP1)*y[42]*y[52]+krelMMP1*y[43]+((-1.0)*kinhibMMP3TIMP1)*y[45]*y[52]+krelMMP3*y[46]+((-1.0)*kinhibMMP13TIMP1)*y[48]*y[52]+krelMMP13*y[49]+((-1.0)*kdegTIMP1)*y[52]
            dy[53]=ksynTIMP3*y[30]+((-1.0)*kinhibADAMTS4TIMP3)*y[33]*y[53]+krelADAMTS4TIMP3*y[35]+((-1.0)*kinhibMMP1TIMP3)*y[42]*y[53]+krelMMP1TIMP3*y[44]+((-1.0)*kinhibMMP3TIMP3)*y[45]*y[53]+krelMMP3TIMP3*y[47]+((-1.0)*kinhibMMP13TIMP3)*y[48]*y[53]+krelMMP13TIMP3*y[50]+((-1.0)*kdegTIMP3)*y[53]
            dy[54]=((-1.0)*kbinIRAK2)*y[6]*y[54]+kbinTRAF6*y[31]*y[56]+kbinIL1IL1R*y[41]*y[57]+((-1.0)*krelIL1IL1R)*y[54]+krelIRAK2*y[56]
            dy[55]=kbinIL1IL1Ra*y[41]*y[58]+((-1.0)*krelIL1IL1Ra)*y[55]
            dy[56]=kbinIRAK2*y[6]*y[54]+((-1.0)*kbinTRAF6)*y[31]*y[56]+((-1.0)*krelIRAK2)*y[56]
            dy[57]=((-1.0)*kbinIL1IL1R)*y[41]*y[57]+krelIL1IL1R*y[54]
            dy[58]=((-1.0)*kbinIL1IL1Ra)*y[41]*y[58]+krelIL1IL1Ra*y[55]
            dy[59]=kbinOSMOSMR*y[51]*y[62]+((-1.0)*krelOSMOSMR)*y[59]
            dy[60]=kbinOSMOSMRa*y[51]*y[63]+((-1.0)*krelOSMOSMRa)*y[60]
            dy[61]=kbinSOCS3OSMR*y[25]*y[62]+((-1.0)*krelSOCS3OSMR)*y[61]
            dy[62]=((-1.0)*kbinSOCS3OSMR)*y[25]*y[62]+((-1.0)*kbinOSMOSMR)*y[51]*y[62]+krelOSMOSMR*y[59]+krelSOCS3OSMR*y[61]
            dy[63]=((-1.0)*kbinOSMOSMRa)*y[51]*y[63]+krelOSMOSMRa*y[60]
            dy[64]=((-1.0)*krelcFoscJun)*y[64]+kbincFoscJun*y[65]*y[66]
            dy[65]=kphoscFos*y[1]*y[19]+((-1.0)*kdephoscFosDUSP16)*y[5]*y[65]+krelcFoscJun*y[64]+((-1.0)*kdephoscFos)*y[65]+((-1.0)*kbincFoscJun)*y[65]*y[66]
            dy[66]=kphoscJun*y[3]*y[12]+krelcFoscJun*y[64]+((-1.0)*kbincFoscJun)*y[65]*y[66]+(((-1.0)*kdephoscJun)+((-2.0)*((kdimercJun*(-1.0))*0.5)))*y[66]+((-2.0)*(kdimercJun*0.5))*y[66]*y[66]+2.0*kdedimercJun*y[67]
            dy[67]=((kdimercJun*(-1.0))*0.5)*y[66]+kdimercJun*0.5*y[66]*y[66]+((-1.0)*kdedimercJun)*y[67]
            dy[68]=ksynSP1*kAP1activity*y[64]+((-1.0)*kdegSP1)*y[68]+((-1.0)*kbinSP1TIMP1DNA)*y[68]*y[72]+krelSP1TIMP1DNA*y[69]
            dy[69]=kbinSP1TIMP1DNA*y[68]*y[72]+((-1.0)*krelSP1TIMP1DNA)*y[69]
            dy[70]=kdephosSTAT3nucPTPRT*y[24]*y[71]+((-1.0)*knuc2cytSTAT3)*y[70]+kdephosSTAT3nuc*y[71]
            dy[71]=((-1.0)*kdephosSTAT3nucPTPRT)*y[24]*y[71]+kcyt2nucSTAT3*y[28]+((-1.0)*kdephosSTAT3nuc)*y[71]
            dy[72]=((-1.0)*kbinSP1TIMP1DNA)*y[68]*y[72]+krelSP1TIMP1DNA*y[69]
            dy[73]=0.0
            dy[74]=0.0

            dy
        }
    }
}

class T16ModelCreator  {
    fun createModel(reduction: Int) : ModelInfo {
        return ModelInfo(
                randMatrix(7 - reduction, 7, -1.0 until 1.0).MGSON())
        { y: SimpleMatrix ->
            val dy = SimpleMatrix(y)

            val kd = 0.334

            val ka = 8.22E-4

            val mu = 0.79

            val ld = 4.49

            val lm = 0.175

            val la = 1.15

            val sdelta = 0.486

            val lambda = 0.00316

            val TIR1T = 18.5

            val alpha_tr = 30.5


            dy[0]=mu*y[0]+((-1.0)*ka)*y[0]*y[1]+kd*y[2]+alpha_tr*y[6]
            dy[1]=((-1.0)*ka)*y[0]*y[1]+kd*y[2]
            dy[2]=ka*y[0]*y[1]+((-1.0)*kd)*y[2]+((-1.0)*la)*y[2]*y[4]+(lm+ld)*y[3]
            dy[3]=la*y[2]*y[4]+(((-1.0)*ld)+((-1.0)*lm))*y[3]
            dy[4]=((-1.0)*la)*y[2]*y[4]+ld*y[3]+lambda*y[4]+sdelta*y[6]
            dy[5]=((-1.0)*mu)*y[0]+((-1.0)*lambda)*y[4]
            dy[6]=0.0

            dy
        }
    }
}

//BIOMD0000000459
class T17ModelCreator  {
    fun createModel(reduction: Int) : ModelInfo {
        return ModelInfo(
                randMatrix(4 - reduction, 4, -1.0 until 1.0).MGSON())
        { y: SimpleMatrix ->
            val dy = SimpleMatrix(y)

            val kbd = 0.0164812

            val kbs = 100.0

            val kxd = 1.08559E-9

            val kxs = 9.32517E-8

            val kzd = 1.34615E-7

            val kzs = 9.03538E-7

            val kzx = 0.00317772


            dy[0]=0.0
            dy[1]=kbs*y[0]+(((-kbd)+((-1.0)*kzs))+((-1.0)*kxs))*y[1]+((-1.0)*(-kzd))*y[2]+((-1.0)*(-kzx))*y[2]*y[3]+((-1.0)*(-kxd))*y[3]
            dy[2]=kzs*y[1]+-kzd*y[2]+-kzx*y[2]*y[3]
            dy[3]=kxs*y[1]+-kxd*y[3]

            dy
        }
    }
}


class T102ModelCreator  {
    fun createModel(reduction: Int) : ModelInfo {
        return ModelInfo(
                randMatrix(50 - reduction, 50, -1.0 until 1.0).MGSON())
        { y: SimpleMatrix ->
            val dy = SimpleMatrix(y)



            dy[0]=(5.6E-5)*y[0]+((-1.0)*0.00953471)*y[0]*y[1]+((-1.0)*(-(6.60377E-5)))*y[2]+0.001*y[3]
            dy[1]=((-1.0)*0.00953471)*y[0]*y[1]+((-1.0)*(-(6.60377E-5)))*y[2]
            dy[2]=0.00953471*y[0]*y[1]+((-(6.60377E-5))+(5.6E-5))*y[2]+((-1.0)*0.00427827)*y[2]*y[5]
            dy[3]=((-1.0)*0.001)*y[3]+(2.8E-7)*y[48]
            dy[4]=(-(1.0E-4))*y[4]+((-1.0)*0.0976562)*y[4]*y[6]*y[8]+((-1.0)*0.00976562)*y[4]*y[6]*y[19]+((-1.0)*0.15625)*y[4]*y[41]+0.001135*y[10]+(2.0256E-5)*y[49]
            dy[5]=((-1.0)*0.00427827)*y[2]*y[5]+(-(1.0E-4))*y[5]+(2.9344E-5)*y[49]
            dy[6]=((-1.0)*0.0976562)*y[4]*y[6]*y[8]+((-1.0)*0.00976562)*y[4]*y[6]*y[19]+(-(1.0E-4))*y[6]+0.00625*y[9]*y[23]+0.001135*y[10]+(3.3056E-5)*y[49]
            dy[7]=(-(1.0E-4))*y[7]+((-2.0)*0.0118534)*y[7]*y[7]*y[11]+(3.0944E-5)*y[49]
            dy[8]=0.00427827*y[2]*y[5]+((-1.0)*0.0976562)*y[4]*y[6]*y[8]+0.02352*y[8]+0.00625*y[9]*y[23]
            dy[9]=0.0976562*y[4]*y[6]*y[8]+(((-1.0)*0.001135)+(5.6E-5))*y[9]+((-1.0)*0.00625)*y[9]*y[23]
            dy[10]=0.001135*y[9]+((-1.0)*0.001135)*y[10]
            dy[11]=((-1.0)*0.0118534)*y[7]*y[7]*y[11]+0.001135*y[10]
            dy[12]=0.0118534*y[7]*y[7]*y[11]+((-1.0)*0.1135)*y[12]
            dy[13]=0.1135*y[12]+(5.6E-5)*y[13]+((-1.0)*0.3125)*y[13]*y[14]+((-1.0)*0.03125)*y[13]*y[37]+0.45*y[18]+0.3*y[19]
            dy[14]=((-1.0)*0.3125)*y[13]*y[14]+(-(1.0E-4))*y[14]+((-1.0)*0.3125)*y[14]*y[15]+((-1.0)*0.3125)*y[14]*y[16]+((-1.0)*0.15625)*y[14]*y[41]+0.00687273*y[34]+(2.24902E-6)*y[49]
            dy[15]=0.3125*y[13]*y[14]+((-1.0)*0.3125)*y[14]*y[15]+(5.6E-5)*y[15]+((-1.0)*0.3125)*y[15]*y[37]
            dy[16]=0.03125*y[13]*y[37]+((-1.0)*0.3125)*y[14]*y[16]+(5.6E-5)*y[16]+((-1.0)*0.03125)*y[16]*y[37]
            dy[17]=0.3125*y[14]*y[15]+(5.6E-5)*y[17]
            dy[18]=0.03125*y[16]*y[37]+(((-1.0)*0.45)+(5.6E-5))*y[18]
            dy[19]=((-1.0)*0.00976562)*y[4]*y[6]*y[19]+0.3125*y[14]*y[16]+0.3125*y[15]*y[37]+(((-1.0)*0.3)+(5.6E-5))*y[19]
            dy[20]=0.00976562*y[4]*y[6]*y[19]+(5.6E-5)*y[20]
            dy[21]=((-1.0)*93.75)*y[9]*y[21]+((-1.0)*0.03125)*y[20]*y[21]+(-(1.0E-4))*y[21]+0.1*y[22]+(6.4E-5)*y[49]
            dy[22]=93.75*y[9]*y[21]+0.03125*y[20]*y[21]+(((-1.0)*0.1)+(1.0E-4))*y[22]
            dy[23]=(-(1.0E-4))*y[23]+0.0151515*y[31]+(9.6E-6)*y[49]
            dy[24]=0.104167*y[22]*y[26]+(((-1.0)*0.0125)+(-(1.0E-4)))*y[24]+((-1.0)*1.25)*y[24]*y[25]+(1.6E-6)*y[49]
            dy[25]=((-1.0)*1.25)*y[24]*y[25]+(((-1.0)*0.005)+0.00154022)*y[25]+((-1.0)*(-0.00257576))*y[29]+0.0606061*y[32]
            dy[26]=((-1.0)*0.104167)*y[22]*y[26]+1.25*y[24]*y[25]+(1.0E-4)*y[26]+0.0151515*y[30]
            dy[27]=0.104167*y[22]*y[26]+0.0115517*y[27]
            dy[28]=0.0125*y[24]+(1.0E-4)*y[28]+((-1.0)*1.4348)*y[28]*y[29]
            dy[29]=0.005*y[25]+((-1.0)*1.4348)*y[28]*y[29]+((-0.00257576)+(1.0E-4))*y[29]
            dy[30]=1.4348*y[28]*y[29]+(((-1.0)*0.0151515)+(1.0E-4))*y[30]
            dy[31]=(3.78788E-5)*y[28]+(4.70498E-4)*y[31]
            dy[32]=(3.0303E-5)*y[28]+(3.94201E-4)*y[32]
            dy[33]=(3.33333E-5)*y[28]+(1.04931E-4)*y[33]
            dy[34]=(3.33333E-5)*y[28]+(1.65744E-4)*y[34]
            dy[35]=(-(5.78704E-6))*y[35]+((-1.0)*0.520833)*y[35]*y[40]+((-1.0)*(-0.001))*y[43]+(1.66603E-6)*y[49]
            dy[36]=0.0506061*y[33]+(-(1.0E-4))*y[36]+(((-1.0)*1.875)+((-1.0)*0.625))*y[36]*y[41]+((5.0E-5)+((-1.0)*(-0.001)))*y[44]+(7.72256E-4)*y[49]
            dy[37]=((-1.0)*0.03125)*y[13]*y[37]+((-1.0)*0.3125)*y[15]*y[37]+((-1.0)*0.03125)*y[16]*y[37]+(-(6.17284E-5))*y[37]+((-1.0)*0.0015625)*y[37]*y[42]+(1.97531E-4)*y[49]
            dy[38]=(-(6.17284E-5))*y[38]+((-1.0)*0.015625)*y[38]*y[40]+(4.93827E-5)*y[49]
            dy[39]=(-(6.17284E-5))*y[39]+((-1.0)*0.009375)*y[39]*y[41]+(3.95062E-6)*y[49]
            dy[40]=0.45*y[18]+0.3*y[19]+((-1.0)*0.520833)*y[35]*y[40]+0.0015625*y[37]*y[42]+(5.78704E-5)*y[40]+((-1.0)*(-0.001))*y[43]
            dy[41]=((-1.0)*0.625)*y[36]*y[41]+0.015625*y[38]*y[40]+(5.78704E-5)*y[41]+((-1.0)*(-0.001))*y[44]
            dy[42]=0.009375*y[39]*y[41]+(5.78704E-5)*y[42]
            dy[43]=0.520833*y[35]*y[40]+((-0.001)+(5.78704E-5))*y[43]
            dy[44]=0.625*y[36]*y[41]+(((-0.001)+((-1.0)*(5.0E-5)))+(5.78704E-5))*y[44]
            dy[45]=((-1.0)*0.1875)*y[41]*y[45]+(5.78704E-6)*y[45]+(-(9.64506E-6))*y[49]
            dy[46]=0.1875*y[41]*y[45]+(5.78704E-6)*y[46]
            dy[47]=((-1.0)*(5.6E-5))*y[0]+((-1.0)*(5.6E-5))*y[2]+((-1.0)*0.02352)*y[8]+((-1.0)*(5.6E-5))*y[9]+((-1.0)*(5.6E-5))*y[13]+((-1.0)*(5.6E-5))*y[15]+((-1.0)*(5.6E-5))*y[16]+((-1.0)*(5.6E-5))*y[17]+((-1.0)*(5.6E-5))*y[18]+((-1.0)*(5.6E-5))*y[19]+((-1.0)*(5.6E-5))*y[20]+((-1.0)*(1.0E-4))*y[22]+((-1.0)*0.00154022)*y[25]+((-1.0)*(1.0E-4))*y[26]+((-1.0)*0.0115517)*y[27]+((-1.0)*(1.0E-4))*y[28]+((-1.0)*(1.0E-4))*y[29]+((-1.0)*(1.0E-4))*y[30]+((-1.0)*(4.70498E-4))*y[31]+((-1.0)*(3.94201E-4))*y[32]+((-1.0)*(1.04931E-4))*y[33]+((-1.0)*(1.65744E-4))*y[34]+((-1.0)*(5.78704E-5))*y[40]+((-1.0)*(5.78704E-5))*y[41]+((-1.0)*(5.78704E-5))*y[42]+((-1.0)*(5.78704E-5))*y[43]+((-1.0)*(5.78704E-5))*y[44]+((-1.0)*(5.78704E-6))*y[45]+((-1.0)*(5.78704E-6))*y[46]+((-1.0)*(-(9.64506E-6)))*y[49]
            dy[48]=0.0
            dy[49]=0.0

            dy
        }
    }
}

//BIOMD0000000334
class T103ModelCreator  {
    fun createModel(reduction: Int) : ModelInfo {
        return ModelInfo(
                randMatrix(74 - reduction, 74, -1.0 until 1.0).MGSON())
        { y: SimpleMatrix ->
            val dy = SimpleMatrix(y)

            val konII = 0.0043

            val nva = 100.0

            val koffII = 1.0

            val konmIIa = 0.05

            val koffmIIa = 0.475

            val konV = 0.05

            val koffV = 0.145

            val konVa = 0.057

            val koffVa = 0.17

            val konVII = 0.05

            val koffVII = 0.66

            val konVIIa = 0.05

            val koffVIIa = 0.227

            val konVIII = 0.05

            val koffVIII = 0.1

            val konVIIIa = 0.05

            val koffVIIIa = 0.335

            val konIX = 0.05

            val koffIX = 0.115

            val konIXa = 0.05

            val koffIXa = 0.115

            val konX = 0.01

            val koffX = 1.9

            val konXa = 0.029

            val koffXa = 3.3

            val konAPC = 0.05

            val koffAPC = 3.5

            val konPS = 0.05

            val koffPS = 0.2

            val konVIIIai = 0.05

            val koffVIIIai = 0.335

            val konVai = 0.057

            val koffVai = 0.17

            val konPC = 0.05

            val koffPC = 11.5

            val k1 = 0.5

            val k2 = 0.005

            val k3 = 0.005

            val k4 = 0.005

            val k5 = 0.01

            val k6 = 2.09

            val k7 = 0.34

            val k8 = 0.1

            val k9 = 32.5

            val k10 = 1.5

            val k75 = 1.0

            val k11 = 0.05

            val k12 = 44.8

            val k13 = 15.2

            val k14 = 0.1

            val k15 = 0.2

            val k16 = 1.0

            val k17 = 1.0

            val k18 = 0.1

            val k19 = 10.7

            val k20 = 8.3

            val k21 = 0.1

            val k22 = 1.0

            val k23 = 0.043

            val k24 = 0.1

            val k25 = 2.1

            val k26 = 0.023

            val k27 = 0.1

            val k28 = 6.94

            val k29 = 0.23

            val k30 = 0.1

            val k31 = 13.8

            val k32 = 0.9

            val k33 = 0.1

            val k34 = 100.0

            val k35 = 0.1

            val k36 = 66.0

            val k37 = 13.0

            val k38 = 15.0

            val k39 = 0.05

            val k40 = 44.8

            val k41 = 15.2

            val k42 = 0.1

            val k43 = 10.0

            val k44 = 1.43

            val k45 = 0.1

            val k46 = 1.6

            val k47 = 0.4

            val k48 = 0.1

            val k49 = 1.6

            val k50 = 0.4

            val k51 = 0.016

            val k52 = 3.3E-4

            val k53 = 0.01

            val k54 = 0.0011

            val k55 = 4.9E-7

            val k56 = 2.3E-6

            val k57 = 6.83E-5

            val k58 = 0.1

            val k59 = 6.94

            val k60 = 1.035

            val k61 = 0.1

            val k62 = 13.8

            val k63 = 0.9

            val k64 = 1.0

            val k65 = 0.5

            val k66 = 0.1

            val k67 = 6.4

            val k68 = 3.6

            val k69 = 6.83E-6

            val k70 = 0.1

            val k71 = 0.5

            val k72 = 0.01

            val k73 = 1.417

            val k74 = 0.183


            dy[0]=((-1.0)*(1.0*(konII*(1.0/nva))))*y[0]+((-1.0)*(1.0*(-koffII)))*y[1]
            dy[1]=1.0*(konII*(1.0/nva))*y[0]+(1.0*(-koffII))*y[1]+((-1.0)*(1.0*k33))*y[1]*y[43]+((-1.0)*(1.0*(-k34)))*y[50]
            dy[2]=((-1.0)*(1.0*(konmIIa*(1.0/nva))))*y[2]+((-1.0)*(1.0*k69))*y[2]*y[58]+((-1.0)*(1.0*(-koffmIIa)))*y[3]
            dy[3]=1.0*(konmIIa*(1.0/nva))*y[2]+(1.0*(-koffmIIa))*y[3]+((-1.0)*(1.0*k58))*y[3]*y[5]+((-1.0)*(1.0*k61))*y[3]*y[13]+((-1.0)*(1.0*k35))*y[3]*y[43]+((-1.0)*(1.0*(-k36)))*y[51]+((1.0*k60)+((-1.0)*(1.0*(-k59))))*y[66]+((1.0*k63)+((-1.0)*(1.0*(-k62))))*y[67]
            dy[4]=((-1.0)*(1.0*(konV*(1.0/nva))))*y[4]+((-1.0)*(1.0*(-koffV)))*y[5]
            dy[5]=((-1.0)*(1.0*k58))*y[3]*y[5]+1.0*(konV*(1.0/nva))*y[4]+(1.0*(-koffV))*y[5]+((-1.0)*(1.0*k21))*y[5]*y[23]+((-1.0)*(1.0*k27))*y[5]*y[47]+((-1.0)*(1.0*(-k22)))*y[45]+((-1.0)*(1.0*(-k28)))*y[48]+((-1.0)*(1.0*(-k59)))*y[66]
            dy[6]=((-1.0)*(1.0*(konVa*(1.0/nva))))*y[6]+((-1.0)*(1.0*(-koffVa)))*y[7]
            dy[7]=1.0*(konVa*(1.0/nva))*y[6]+(1.0*(-koffVa))*y[7]+((-1.0)*(1.0*k16))*y[7]*y[23]+((-1.0)*(1.0*k48))*y[7]*y[55]+((-1.0)*(1.0*(-k17)))*y[43]+1.0*k23*y[45]+1.0*k29*y[48]+((-1.0)*(1.0*(-k49)))*y[62]+1.0*k60*y[66]
            dy[8]=((-1.0)*(1.0*(konVII*(1.0/nva))))*y[8]+((-1.0)*(1.0*(-koffVII)))*y[9]
            dy[9]=1.0*(konVII*(1.0/nva))*y[8]+(1.0*(-koffVII))*y[9]+((-1.0)*(1.0*k39))*y[9]*y[23]+((-1.0)*(1.0*k3))*y[9]*y[34]+((-1.0)*(1.0*(-k4)))*y[36]+((-1.0)*(1.0*(-k40)))*y[65]
            dy[10]=((-1.0)*(1.0*(konVIIa*(1.0/nva))))*y[10]+((-1.0)*(1.0*(-koffVIIa)))*y[11]
            dy[11]=1.0*(konVIIa*(1.0/nva))*y[10]+(1.0*(-koffVIIa))*y[11]+((-1.0)*(1.0*k1))*y[11]*y[34]+((-1.0)*(1.0*(-k2)))*y[35]+1.0*k41*y[65]
            dy[12]=((-1.0)*(1.0*(konVIII*(1.0/nva))))*y[12]+((-1.0)*(1.0*(-koffVIII)))*y[13]
            dy[13]=((-1.0)*(1.0*k61))*y[3]*y[13]+1.0*(konVIII*(1.0/nva))*y[12]+(1.0*(-koffVIII))*y[13]+((-1.0)*k24)*y[13]*y[23]+((-1.0)*(1.0*k30))*y[13]*y[47]+((-1.0)*(-k25))*y[46]+((-1.0)*(1.0*(-k31)))*y[49]+((-1.0)*(1.0*(-k62)))*y[67]
            dy[14]=((-1.0)*(1.0*(konVIIIa*(1.0/nva))))*y[14]+((-1.0)*(1.0*(-koffVIIIa)))*y[15]
            dy[15]=1.0*(konVIIIa*(1.0/nva))*y[14]+(1.0*(-koffVIIIa))*y[15]+((-1.0)*(1.0*k14))*y[15]*y[19]+((-1.0)*(1.0*k45))*y[15]*y[55]+((-1.0)*(1.0*(-k15)))*y[42]+1.0*k26*y[46]+1.0*k32*y[49]+((-1.0)*(1.0*(-k46)))*y[56]+1.0*k63*y[67]
            dy[16]=((-1.0)*(1.0*(konIX*(1.0/nva))))*y[16]+((-1.0)*(1.0*(-koffIX)))*y[17]
            dy[17]=1.0*(konIX*(1.0/nva))*y[16]+(1.0*(-koffIX))*y[17]+((-1.0)*(1.0*k5))*y[17]*y[35]+((-1.0)*(1.0*k72))*y[17]*y[54]+((-1.0)*(1.0*(-k6)))*y[37]+((-1.0)*(1.0*(-k73)))*y[72]
            dy[18]=((-1.0)*(1.0*(konIXa*(1.0/nva))))*y[18]+((-1.0)*(1.0*k55))*y[18]*y[58]+((-1.0)*(1.0*(-koffIXa)))*y[19]
            dy[19]=((-1.0)*(1.0*k14))*y[15]*y[19]+1.0*(konIXa*(1.0/nva))*y[18]+(1.0*(-koffIXa))*y[19]+1.0*k7*y[37]+((-1.0)*(1.0*(-k15)))*y[42]+1.0*k74*y[72]
            dy[20]=((-1.0)*(1.0*(konX*(1.0/nva))))*y[20]+((-1.0)*(1.0*(-koffX)))*y[21]
            dy[21]=1.0*(konX*(1.0/nva))*y[20]+(1.0*(-koffX))*y[21]+((-1.0)*(1.0*k8))*y[21]*y[35]+((-1.0)*(1.0*k18))*y[21]*y[42]+((-1.0)*(1.0*(-k9)))*y[39]+((-1.0)*(1.0*(-k19)))*y[44]
            dy[22]=((-1.0)*(konXa*(1.0/nva)))*y[22]+((-1.0)*(1.0*k51))*y[22]*y[57]+((-1.0)*(1.0*k56))*y[22]*y[58]+((-1.0)*(-koffXa))*y[23]+((-1.0)*(1.0*(-k52)))*y[60]
            dy[23]=((-1.0)*(1.0*k21))*y[5]*y[23]+((-1.0)*(1.0*k16))*y[7]*y[23]+((-1.0)*(1.0*k39))*y[9]*y[23]+((-1.0)*k24)*y[13]*y[23]+konXa*(1.0/nva)*y[22]+-koffXa*y[23]+((-1.0)*(1.0*k11))*y[23]*y[36]+1.0*k75*y[40]+((1.0*k13)+((-1.0)*(1.0*(-k12))))*y[41]+((-1.0)*(1.0*(-k17)))*y[43]+1.0*k20*y[44]+((1.0*k23)+((-1.0)*(1.0*(-k22))))*y[45]+((1.0*k26)+((-1.0)*(-k25)))*y[46]+((1.0*k41)+((-1.0)*(1.0*(-k40))))*y[65]
            dy[24]=((-1.0)*(1.0*(konAPC*(1.0/nva))))*y[24]+((-1.0)*(1.0*(-koffAPC)))*y[25]
            dy[25]=1.0*(konAPC*(1.0/nva))*y[24]+(1.0*(-koffAPC))*y[25]+((-1.0)*(1.0*k70))*y[25]*y[27]+((-1.0)*(1.0*(-k71)))*y[55]+1.0*k68*y[70]
            dy[26]=((-1.0)*(1.0*(konPS*(1.0/nva))))*y[26]+((-1.0)*(1.0*(-koffPS)))*y[27]
            dy[27]=((-1.0)*(1.0*k70))*y[25]*y[27]+1.0*(konPS*(1.0/nva))*y[26]+(1.0*(-koffPS))*y[27]+((-1.0)*(1.0*(-k71)))*y[55]
            dy[28]=((-1.0)*(1.0*(konVIIIai*(1.0/nva))))*y[28]+((-1.0)*(1.0*(-koffVIIIai)))*y[29]
            dy[29]=1.0*(konVIIIai*(1.0/nva))*y[28]+(1.0*(-koffVIIIai))*y[29]+1.0*k47*y[56]
            dy[30]=((-1.0)*(1.0*(konVai*(1.0/nva))))*y[30]+((-1.0)*(1.0*(-koffVai)))*y[31]
            dy[31]=1.0*(konVai*(1.0/nva))*y[30]+(1.0*(-koffVai))*y[31]+1.0*k50*y[62]
            dy[32]=((-1.0)*(1.0*(konPC*(1.0/nva))))*y[32]+((-1.0)*(1.0*(-koffPC)))*y[33]
            dy[33]=1.0*(konPC*(1.0/nva))*y[32]+(1.0*(-koffPC))*y[33]+((-1.0)*(1.0*k66))*y[33]*y[69]+((-1.0)*(1.0*(-k67)))*y[70]
            dy[34]=((-1.0)*(1.0*k3))*y[9]*y[34]+((-1.0)*(1.0*k1))*y[11]*y[34]+((-1.0)*(1.0*(-k2)))*y[35]+((-1.0)*(1.0*(-k4)))*y[36]
            dy[35]=1.0*k1*y[11]*y[34]+((-1.0)*(1.0*k5))*y[17]*y[35]+((-1.0)*(1.0*k8))*y[21]*y[35]+(1.0*(-k2))*y[35]+((-1.0)*(1.0*k53))*y[35]*y[60]+((1.0*k7)+((-1.0)*(1.0*(-k6))))*y[37]+((-1.0)*(1.0*(-k9)))*y[39]+1.0*k75*y[40]+1.0*k13*y[41]+((-1.0)*(1.0*(-k54)))*y[61]
            dy[36]=1.0*k3*y[9]*y[34]+((-1.0)*(1.0*k11))*y[23]*y[36]+(1.0*(-k4))*y[36]+((-1.0)*(1.0*(-k12)))*y[41]
            dy[37]=1.0*k5*y[17]*y[35]+((1.0*(-k6))+((-1.0)*(1.0*k7)))*y[37]
            dy[38]=0.0
            dy[39]=1.0*k8*y[21]*y[35]+((1.0*(-k9))+((-1.0)*(1.0*k10)))*y[39]
            dy[40]=1.0*k10*y[39]+((-1.0)*(1.0*k75))*y[40]
            dy[41]=1.0*k11*y[23]*y[36]+((1.0*(-k12))+((-1.0)*(1.0*k13)))*y[41]
            dy[42]=1.0*k14*y[15]*y[19]+((-1.0)*(1.0*k18))*y[21]*y[42]+(1.0*(-k15))*y[42]+((1.0*k20)+((-1.0)*(1.0*(-k19))))*y[44]
            dy[43]=((-1.0)*(1.0*k33))*y[1]*y[43]+((-1.0)*(1.0*k35))*y[3]*y[43]+1.0*k16*y[7]*y[23]+(1.0*(-k17))*y[43]+((-1.0)*(1.0*(-k34)))*y[50]+((1.0*k38)+((-1.0)*(1.0*(-k36))))*y[51]
            dy[44]=1.0*k18*y[21]*y[42]+((1.0*(-k19))+((-1.0)*(1.0*k20)))*y[44]
            dy[45]=1.0*k21*y[5]*y[23]+((1.0*(-k22))+((-1.0)*(1.0*k23)))*y[45]
            dy[46]=k24*y[13]*y[23]+((-k25)+((-1.0)*(1.0*k26)))*y[46]
            dy[47]=((-1.0)*(1.0*k27))*y[5]*y[47]+((-1.0)*(1.0*k30))*y[13]*y[47]+((-1.0)*(1.0*k42))*y[47]*y[52]+((-1.0)*(1.0*k57))*y[47]*y[58]+((-1.0)*(1.0*k64))*y[47]*y[68]+((1.0*k29)+((-1.0)*(1.0*(-k28))))*y[48]+((1.0*k32)+((-1.0)*(1.0*(-k31))))*y[49]+1.0*k38*y[51]+((1.0*k44)+((-1.0)*(1.0*(-k43))))*y[53]+((-1.0)*(1.0*(-k65)))*y[69]
            dy[48]=1.0*k27*y[5]*y[47]+((1.0*(-k28))+((-1.0)*(1.0*k29)))*y[48]
            dy[49]=1.0*k30*y[13]*y[47]+((1.0*(-k31))+((-1.0)*(1.0*k32)))*y[49]
            dy[50]=1.0*k33*y[1]*y[43]+((1.0*(-k34))+((-1.0)*(1.0*k37)))*y[50]
            dy[51]=1.0*k35*y[3]*y[43]+1.0*k37*y[50]+((1.0*(-k36))+((-1.0)*(1.0*k38)))*y[51]
            dy[52]=((-1.0)*(1.0*k42))*y[47]*y[52]+((-1.0)*(1.0*(-k43)))*y[53]
            dy[53]=1.0*k42*y[47]*y[52]+((1.0*(-k43))+((-1.0)*(1.0*k44)))*y[53]
            dy[54]=((-1.0)*(1.0*k72))*y[17]*y[54]+1.0*k44*y[53]+((1.0*k74)+((-1.0)*(1.0*(-k73))))*y[72]
            dy[55]=((-1.0)*(1.0*k48))*y[7]*y[55]+((-1.0)*(1.0*k45))*y[15]*y[55]+1.0*k70*y[25]*y[27]+(1.0*(-k71))*y[55]+((1.0*k47)+((-1.0)*(1.0*(-k46))))*y[56]+((1.0*k50)+((-1.0)*(1.0*(-k49))))*y[62]
            dy[56]=1.0*k45*y[15]*y[55]+((1.0*(-k46))+((-1.0)*(1.0*k47)))*y[56]
            dy[57]=((-1.0)*(1.0*k51))*y[22]*y[57]+((-1.0)*(1.0*(-k52)))*y[60]
            dy[58]=((-1.0)*(1.0*k69))*y[2]*y[58]+((-1.0)*(1.0*k55))*y[18]*y[58]+((-1.0)*(1.0*k56))*y[22]*y[58]+((-1.0)*(1.0*k57))*y[47]*y[58]
            dy[59]=1.0*k57*y[47]*y[58]
            dy[60]=1.0*k51*y[22]*y[57]+((-1.0)*(1.0*k53))*y[35]*y[60]+(1.0*(-k52))*y[60]+((-1.0)*(1.0*(-k54)))*y[61]
            dy[61]=1.0*k53*y[35]*y[60]+(1.0*(-k54))*y[61]
            dy[62]=1.0*k48*y[7]*y[55]+((1.0*(-k49))+((-1.0)*(1.0*k50)))*y[62]
            dy[63]=1.0*k55*y[18]*y[58]
            dy[64]=1.0*k56*y[22]*y[58]
            dy[65]=1.0*k39*y[9]*y[23]+((1.0*(-k40))+((-1.0)*(1.0*k41)))*y[65]
            dy[66]=1.0*k58*y[3]*y[5]+((1.0*(-k59))+((-1.0)*(1.0*k60)))*y[66]
            dy[67]=1.0*k61*y[3]*y[13]+((1.0*(-k62))+((-1.0)*(1.0*k63)))*y[67]
            dy[68]=((-1.0)*(1.0*k64))*y[47]*y[68]+((-1.0)*(1.0*(-k65)))*y[69]
            dy[69]=((-1.0)*(1.0*k66))*y[33]*y[69]+1.0*k64*y[47]*y[68]+(1.0*(-k65))*y[69]+((1.0*k68)+((-1.0)*(1.0*(-k67))))*y[70]
            dy[70]=1.0*k66*y[33]*y[69]+((1.0*(-k67))+((-1.0)*(1.0*k68)))*y[70]
            dy[71]=1.0*k69*y[2]*y[58]
            dy[72]=1.0*k72*y[17]*y[54]+((1.0*(-k73))+((-1.0)*(1.0*k74)))*y[72]
            dy[73]=((-100.0)*(1.0*(konII*(1.0/nva))))*y[0]+((-100.0)*(1.0*(-koffII)))*y[1]+((-100.0)*(1.0*(konmIIa*(1.0/nva))))*y[2]+((-100.0)*(1.0*(-koffmIIa)))*y[3]+((-100.0)*(1.0*(konV*(1.0/nva))))*y[4]+((-100.0)*(1.0*(-koffV)))*y[5]+((-100.0)*(1.0*(konVa*(1.0/nva))))*y[6]+((-100.0)*(1.0*(-koffVa)))*y[7]+((-100.0)*(1.0*(konVII*(1.0/nva))))*y[8]+((-100.0)*(1.0*(-koffVII)))*y[9]+((-100.0)*(1.0*(konVIIa*(1.0/nva))))*y[10]+((-100.0)*(1.0*(-koffVIIa)))*y[11]+((-100.0)*(1.0*(konVIII*(1.0/nva))))*y[12]+((-100.0)*(1.0*(-koffVIII)))*y[13]+((-100.0)*(1.0*(konVIIIa*(1.0/nva))))*y[14]+((-100.0)*(1.0*(-koffVIIIa)))*y[15]+((-100.0)*(1.0*(konIX*(1.0/nva))))*y[16]+((-100.0)*(1.0*(-koffIX)))*y[17]+((-100.0)*(1.0*(konIXa*(1.0/nva))))*y[18]+((-100.0)*(1.0*(-koffIXa)))*y[19]+((-100.0)*(1.0*(konX*(1.0/nva))))*y[20]+((-100.0)*(1.0*(-koffX)))*y[21]+((-100.0)*(konXa*(1.0/nva)))*y[22]+((-100.0)*(-koffXa))*y[23]+((-100.0)*(1.0*(konAPC*(1.0/nva))))*y[24]+((-100.0)*(1.0*(-koffAPC)))*y[25]+((-100.0)*(1.0*(konPS*(1.0/nva))))*y[26]+((-100.0)*(1.0*(-koffPS)))*y[27]+((-100.0)*(1.0*(konVIIIai*(1.0/nva))))*y[28]+((-100.0)*(1.0*(-koffVIIIai)))*y[29]+((-100.0)*(1.0*(konVai*(1.0/nva))))*y[30]+((-100.0)*(1.0*(-koffVai)))*y[31]+((-100.0)*(1.0*(konPC*(1.0/nva))))*y[32]+((-100.0)*(1.0*(-koffPC)))*y[33]

            dy
        }
    }
}

//BIOMD0000000332
class T104ModelCreator  {
    fun createModel(reduction: Int) : ModelInfo {
        return ModelInfo(
                randMatrix(78 - reduction, 78, -1.0 until 1.0).MGSON())
        { y: SimpleMatrix ->
            val dy = SimpleMatrix(y)

            val konII = 0.0043

            val nva = 100.0

            val koffII = 1.0

            val konmIIa = 0.05

            val koffmIIa = 0.475

            val konV = 0.05

            val koffV = 0.145

            val konVa = 0.057

            val koffVa = 0.17

            val konVII = 0.05

            val koffVII = 0.66

            val konVIIa = 0.05

            val koffVIIa = 0.227

            val konVIII = 0.05

            val koffVIII = 0.1

            val konVIIIa = 0.05

            val koffVIIIa = 0.335

            val konIX = 0.05

            val koffIX = 0.115

            val konIXa = 0.05

            val koffIXa = 0.115

            val konX = 0.01

            val koffX = 1.9

            val konXa = 0.029

            val koffXa = 3.3

            val konAPC = 0.05

            val koffAPC = 3.5

            val konPS = 0.05

            val koffPS = 0.2

            val konVIIIai = 0.05

            val koffVIIIai = 0.335

            val konVai = 0.057

            val koffVai = 0.17

            val konPC = 0.05

            val koffPC = 11.5

            val k1 = 0.5

            val k2 = 0.005

            val k3 = 0.005

            val k4 = 0.005

            val k5 = 0.01

            val k6 = 2.09

            val k7 = 0.34

            val k8 = 0.1

            val k9 = 32.5

            val k10 = 1.5

            val k11 = 0.05

            val k12 = 44.8

            val k13 = 15.2

            val k14 = 0.1

            val k15 = 0.2

            val k16 = 1.0

            val k17 = 1.0

            val k18 = 0.1

            val k19 = 10.7

            val k20 = 8.3

            val k21 = 0.1

            val k22 = 1.0

            val k23 = 0.043

            val k24 = 0.1

            val k25 = 2.1

            val k26 = 0.023

            val k27 = 0.1

            val k28 = 6.94

            val k29 = 0.23

            val k30 = 0.1

            val k31 = 13.8

            val k32 = 0.9

            val k33 = 0.1

            val k34 = 100.0

            val k35 = 0.1

            val k36 = 66.0

            val k37 = 13.0

            val k38 = 15.0

            val k39 = 0.05

            val k40 = 44.8

            val k41 = 15.2

            val k42 = 0.1

            val k43 = 10.0

            val k44 = 1.43

            val k45 = 0.1

            val k46 = 1.6

            val k47 = 0.4

            val k48 = 0.1

            val k49 = 1.6

            val k50 = 0.4

            val k51 = 0.016

            val k52 = 3.3E-4

            val k53 = 0.01

            val k54 = 0.0011

            val k55 = 4.9E-7

            val k56 = 2.3E-6

            val k57 = 6.83E-6

            val k58 = 0.1

            val k59 = 6.94

            val k60 = 1.035

            val k61 = 0.1

            val k62 = 13.8

            val k63 = 0.9

            val k64 = 1.0

            val k65 = 0.5

            val k66 = 0.1

            val k67 = 6.4

            val k68 = 3.6

            val k69 = 6.83E-6

            val k70 = 0.1

            val k71 = 0.5

            val k72 = 0.01

            val k73 = 1.417

            val k74 = 0.183

            val k75 = 1.0

            val k76 = 2.3E-6

            val k77 = 2.5E-6

            val k78 = 1.4E-6


            dy[0]=((-1.0)*(1.0*(konII*(1.0/nva))))*y[0]+((-1.0)*(1.0*(-koffII)))*y[1]
            dy[1]=1.0*(konII*(1.0/nva))*y[0]+(1.0*(-koffII))*y[1]+((-1.0)*(1.0*k33))*y[1]*y[43]+((-1.0)*(1.0*(-k34)))*y[50]
            dy[2]=((-1.0)*(1.0*(konmIIa*(1.0/nva))))*y[2]+((-1.0)*(1.0*k69))*y[2]*y[58]+((-1.0)*(1.0*(-koffmIIa)))*y[3]
            dy[3]=1.0*(konmIIa*(1.0/nva))*y[2]+(1.0*(-koffmIIa))*y[3]+((-1.0)*(1.0*k58))*y[3]*y[5]+((-1.0)*(1.0*k61))*y[3]*y[13]+((-1.0)*(1.0*k35))*y[3]*y[43]+((-1.0)*(1.0*(-k36)))*y[51]+((1.0*k60)+((-1.0)*(1.0*(-k59))))*y[66]+((1.0*k63)+((-1.0)*(1.0*(-k62))))*y[67]
            dy[4]=((-1.0)*(1.0*(konV*(1.0/nva))))*y[4]+((-1.0)*(1.0*(-koffV)))*y[5]
            dy[5]=((-1.0)*(1.0*k58))*y[3]*y[5]+1.0*(konV*(1.0/nva))*y[4]+(1.0*(-koffV))*y[5]+((-1.0)*(1.0*k21))*y[5]*y[23]+((-1.0)*(1.0*k27))*y[5]*y[47]+((-1.0)*(1.0*(-k22)))*y[45]+((-1.0)*(1.0*(-k28)))*y[48]+((-1.0)*(1.0*(-k59)))*y[66]
            dy[6]=((-1.0)*(1.0*(konVa*(1.0/nva))))*y[6]+((-1.0)*(1.0*(-koffVa)))*y[7]
            dy[7]=1.0*(konVa*(1.0/nva))*y[6]+(1.0*(-koffVa))*y[7]+((-1.0)*(1.0*k16))*y[7]*y[23]+((-1.0)*(1.0*k48))*y[7]*y[55]+((-1.0)*(1.0*(-k17)))*y[43]+1.0*k23*y[45]+1.0*k29*y[48]+((-1.0)*(1.0*(-k49)))*y[62]+1.0*k60*y[66]
            dy[8]=((-1.0)*(1.0*(konVII*(1.0/nva))))*y[8]+((-1.0)*(1.0*(-koffVII)))*y[9]
            dy[9]=1.0*(konVII*(1.0/nva))*y[8]+(1.0*(-koffVII))*y[9]+((-1.0)*(1.0*k39))*y[9]*y[23]+((-1.0)*(1.0*k3))*y[9]*y[34]+((-1.0)*(1.0*(-k4)))*y[36]+((-1.0)*(1.0*(-k40)))*y[65]
            dy[10]=((-1.0)*(1.0*(konVIIa*(1.0/nva))))*y[10]+((-1.0)*(1.0*(-koffVIIa)))*y[11]
            dy[11]=1.0*(konVIIa*(1.0/nva))*y[10]+(1.0*(-koffVIIa))*y[11]+((-1.0)*(1.0*k1))*y[11]*y[34]+((-1.0)*(1.0*(-k2)))*y[35]+1.0*k41*y[65]
            dy[12]=((-1.0)*(1.0*(konVIII*(1.0/nva))))*y[12]+((-1.0)*(1.0*(-koffVIII)))*y[13]
            dy[13]=((-1.0)*(1.0*k61))*y[3]*y[13]+1.0*(konVIII*(1.0/nva))*y[12]+(1.0*(-koffVIII))*y[13]+((-1.0)*k24)*y[13]*y[23]+((-1.0)*(1.0*k30))*y[13]*y[47]+((-1.0)*(-k25))*y[46]+((-1.0)*(1.0*(-k31)))*y[49]+((-1.0)*(1.0*(-k62)))*y[67]
            dy[14]=((-1.0)*(1.0*(konVIIIa*(1.0/nva))))*y[14]+((-1.0)*(1.0*(-koffVIIIa)))*y[15]
            dy[15]=1.0*(konVIIIa*(1.0/nva))*y[14]+(1.0*(-koffVIIIa))*y[15]+((-1.0)*(1.0*k14))*y[15]*y[19]+((-1.0)*(1.0*k45))*y[15]*y[55]+((-1.0)*(1.0*(-k15)))*y[42]+1.0*k26*y[46]+1.0*k32*y[49]+((-1.0)*(1.0*(-k46)))*y[56]+1.0*k63*y[67]
            dy[16]=((-1.0)*(1.0*(konIX*(1.0/nva))))*y[16]+((-1.0)*(1.0*(-koffIX)))*y[17]
            dy[17]=1.0*(konIX*(1.0/nva))*y[16]+(1.0*(-koffIX))*y[17]+((-1.0)*(1.0*k5))*y[17]*y[35]+((-1.0)*(1.0*k72))*y[17]*y[54]+((-1.0)*(1.0*(-k6)))*y[37]+((-1.0)*(1.0*(-k73)))*y[72]
            dy[18]=((-1.0)*(1.0*(konIXa*(1.0/nva))))*y[18]+((-1.0)*(1.0*k55))*y[18]*y[58]+((-1.0)*(1.0*(-koffIXa)))*y[19]
            dy[19]=((-1.0)*(1.0*k14))*y[15]*y[19]+1.0*(konIXa*(1.0/nva))*y[18]+(1.0*(-koffIXa))*y[19]+1.0*k7*y[37]+((-1.0)*(1.0*(-k15)))*y[42]+1.0*k74*y[72]
            dy[20]=((-1.0)*(1.0*(konX*(1.0/nva))))*y[20]+((-1.0)*(1.0*(-koffX)))*y[21]
            dy[21]=1.0*(konX*(1.0/nva))*y[20]+(1.0*(-koffX))*y[21]+((-1.0)*(1.0*k8))*y[21]*y[35]+((-1.0)*(1.0*k18))*y[21]*y[42]+((-1.0)*(1.0*(-k9)))*y[39]+((-1.0)*(1.0*(-k19)))*y[44]
            dy[22]=((-1.0)*(konXa*(1.0/nva)))*y[22]+((-1.0)*(1.0*k51))*y[22]*y[57]+((-1.0)*(1.0*k56))*y[22]*y[58]+((-1.0)*(1.0*k78))*y[22]*y[74]+((-1.0)*(-koffXa))*y[23]+((-1.0)*(1.0*(-k52)))*y[60]
            dy[23]=((-1.0)*(1.0*k21))*y[5]*y[23]+((-1.0)*(1.0*k16))*y[7]*y[23]+((-1.0)*(1.0*k39))*y[9]*y[23]+((-1.0)*k24)*y[13]*y[23]+konXa*(1.0/nva)*y[22]+-koffXa*y[23]+((-1.0)*(1.0*k11))*y[23]*y[36]+1.0*k75*y[40]+((1.0*k13)+((-1.0)*(1.0*(-k12))))*y[41]+((-1.0)*(1.0*(-k17)))*y[43]+1.0*k20*y[44]+((1.0*k23)+((-1.0)*(1.0*(-k22))))*y[45]+((1.0*k26)+((-1.0)*(-k25)))*y[46]+((1.0*k41)+((-1.0)*(1.0*(-k40))))*y[65]
            dy[24]=((-1.0)*(1.0*(konAPC*(1.0/nva))))*y[24]+((-1.0)*(1.0*(-koffAPC)))*y[25]
            dy[25]=1.0*(konAPC*(1.0/nva))*y[24]+(1.0*(-koffAPC))*y[25]+((-1.0)*(1.0*k70))*y[25]*y[27]+((-1.0)*(1.0*(-k71)))*y[55]+1.0*k68*y[70]
            dy[26]=((-1.0)*(1.0*(konPS*(1.0/nva))))*y[26]+((-1.0)*(1.0*(-koffPS)))*y[27]
            dy[27]=((-1.0)*(1.0*k70))*y[25]*y[27]+1.0*(konPS*(1.0/nva))*y[26]+(1.0*(-koffPS))*y[27]+((-1.0)*(1.0*(-k71)))*y[55]
            dy[28]=((-1.0)*(1.0*(konVIIIai*(1.0/nva))))*y[28]+((-1.0)*(1.0*(-koffVIIIai)))*y[29]
            dy[29]=1.0*(konVIIIai*(1.0/nva))*y[28]+(1.0*(-koffVIIIai))*y[29]+1.0*k47*y[56]
            dy[30]=((-1.0)*(1.0*(konVai*(1.0/nva))))*y[30]+((-1.0)*(1.0*(-koffVai)))*y[31]
            dy[31]=1.0*(konVai*(1.0/nva))*y[30]+(1.0*(-koffVai))*y[31]+1.0*k50*y[62]
            dy[32]=((-1.0)*(1.0*(konPC*(1.0/nva))))*y[32]+((-1.0)*(1.0*(-koffPC)))*y[33]
            dy[33]=1.0*(konPC*(1.0/nva))*y[32]+(1.0*(-koffPC))*y[33]+((-1.0)*(1.0*k66))*y[33]*y[69]+((-1.0)*(1.0*(-k67)))*y[70]
            dy[34]=((-1.0)*(1.0*k3))*y[9]*y[34]+((-1.0)*(1.0*k1))*y[11]*y[34]+((-1.0)*(1.0*(-k2)))*y[35]+((-1.0)*(1.0*(-k4)))*y[36]
            dy[35]=1.0*k1*y[11]*y[34]+((-1.0)*(1.0*k5))*y[17]*y[35]+((-1.0)*(1.0*k8))*y[21]*y[35]+(1.0*(-k2))*y[35]+((-1.0)*(1.0*k53))*y[35]*y[60]+((1.0*k7)+((-1.0)*(1.0*(-k6))))*y[37]+((-1.0)*(1.0*(-k9)))*y[39]+1.0*k75*y[40]+1.0*k13*y[41]+((-1.0)*(1.0*(-k54)))*y[61]
            dy[36]=1.0*k3*y[9]*y[34]+((-1.0)*(1.0*k11))*y[23]*y[36]+(1.0*(-k4))*y[36]+((-1.0)*(1.0*(-k12)))*y[41]
            dy[37]=1.0*k5*y[17]*y[35]+((1.0*(-k6))+((-1.0)*(1.0*k7)))*y[37]
            dy[38]=0
            dy[39]=1.0*k8*y[21]*y[35]+((1.0*(-k9))+((-1.0)*(1.0*k10)))*y[39]
            dy[40]=1.0*k10*y[39]+((-1.0)*(1.0*k75))*y[40]
            dy[41]=1.0*k11*y[23]*y[36]+((1.0*(-k12))+((-1.0)*(1.0*k13)))*y[41]
            dy[42]=1.0*k14*y[15]*y[19]+((-1.0)*(1.0*k18))*y[21]*y[42]+(1.0*(-k15))*y[42]+((1.0*k20)+((-1.0)*(1.0*(-k19))))*y[44]
            dy[43]=((-1.0)*(1.0*k33))*y[1]*y[43]+((-1.0)*(1.0*k35))*y[3]*y[43]+1.0*k16*y[7]*y[23]+(1.0*(-k17))*y[43]+((-1.0)*(1.0*(-k34)))*y[50]+((1.0*k38)+((-1.0)*(1.0*(-k36))))*y[51]
            dy[44]=1.0*k18*y[21]*y[42]+((1.0*(-k19))+((-1.0)*(1.0*k20)))*y[44]
            dy[45]=1.0*k21*y[5]*y[23]+((1.0*(-k22))+((-1.0)*(1.0*k23)))*y[45]
            dy[46]=k24*y[13]*y[23]+((-k25)+((-1.0)*(1.0*k26)))*y[46]
            dy[47]=((-1.0)*(1.0*k27))*y[5]*y[47]+((-1.0)*(1.0*k30))*y[13]*y[47]+((-1.0)*(1.0*k42))*y[47]*y[52]+((-1.0)*(1.0*k57))*y[47]*y[58]+((-1.0)*(1.0*k64))*y[47]*y[68]+((-1.0)*(1.0*k77))*y[47]*y[74]+((1.0*k29)+((-1.0)*(1.0*(-k28))))*y[48]+((1.0*k32)+((-1.0)*(1.0*(-k31))))*y[49]+1.0*k38*y[51]+((1.0*k44)+((-1.0)*(1.0*(-k43))))*y[53]+((-1.0)*(1.0*(-k65)))*y[69]
            dy[48]=1.0*k27*y[5]*y[47]+((1.0*(-k28))+((-1.0)*(1.0*k29)))*y[48]
            dy[49]=1.0*k30*y[13]*y[47]+((1.0*(-k31))+((-1.0)*(1.0*k32)))*y[49]
            dy[50]=1.0*k33*y[1]*y[43]+((1.0*(-k34))+((-1.0)*(1.0*k37)))*y[50]
            dy[51]=1.0*k35*y[3]*y[43]+1.0*k37*y[50]+((1.0*(-k36))+((-1.0)*(1.0*k38)))*y[51]
            dy[52]=((-1.0)*(1.0*k42))*y[47]*y[52]+((-1.0)*(1.0*(-k43)))*y[53]
            dy[53]=1.0*k42*y[47]*y[52]+((1.0*(-k43))+((-1.0)*(1.0*k44)))*y[53]
            dy[54]=((-1.0)*(1.0*k72))*y[17]*y[54]+1.0*k44*y[53]+((-1.0)*(1.0*k76))*y[54]*y[58]+((1.0*k74)+((-1.0)*(1.0*(-k73))))*y[72]
            dy[55]=((-1.0)*(1.0*k48))*y[7]*y[55]+((-1.0)*(1.0*k45))*y[15]*y[55]+1.0*k70*y[25]*y[27]+(1.0*(-k71))*y[55]+((1.0*k47)+((-1.0)*(1.0*(-k46))))*y[56]+((1.0*k50)+((-1.0)*(1.0*(-k49))))*y[62]
            dy[56]=1.0*k45*y[15]*y[55]+((1.0*(-k46))+((-1.0)*(1.0*k47)))*y[56]
            dy[57]=((-1.0)*(1.0*k51))*y[22]*y[57]+((-1.0)*(1.0*(-k52)))*y[60]
            dy[58]=((-1.0)*(1.0*k69))*y[2]*y[58]+((-1.0)*(1.0*k55))*y[18]*y[58]+((-1.0)*(1.0*k56))*y[22]*y[58]+((-1.0)*(1.0*k57))*y[47]*y[58]+((-1.0)*(1.0*k76))*y[54]*y[58]
            dy[59]=1.0*k57*y[47]*y[58]
            dy[60]=1.0*k51*y[22]*y[57]+((-1.0)*(1.0*k53))*y[35]*y[60]+(1.0*(-k52))*y[60]+((-1.0)*(1.0*(-k54)))*y[61]
            dy[61]=1.0*k53*y[35]*y[60]+(1.0*(-k54))*y[61]
            dy[62]=1.0*k48*y[7]*y[55]+((1.0*(-k49))+((-1.0)*(1.0*k50)))*y[62]
            dy[63]=1.0*k55*y[18]*y[58]
            dy[64]=1.0*k56*y[22]*y[58]
            dy[65]=1.0*k39*y[9]*y[23]+((1.0*(-k40))+((-1.0)*(1.0*k41)))*y[65]
            dy[66]=1.0*k58*y[3]*y[5]+((1.0*(-k59))+((-1.0)*(1.0*k60)))*y[66]
            dy[67]=1.0*k61*y[3]*y[13]+((1.0*(-k62))+((-1.0)*(1.0*k63)))*y[67]
            dy[68]=((-1.0)*(1.0*k64))*y[47]*y[68]+((-1.0)*(1.0*(-k65)))*y[69]
            dy[69]=((-1.0)*(1.0*k66))*y[33]*y[69]+1.0*k64*y[47]*y[68]+(1.0*(-k65))*y[69]+((1.0*k68)+((-1.0)*(1.0*(-k67))))*y[70]
            dy[70]=1.0*k66*y[33]*y[69]+((1.0*(-k67))+((-1.0)*(1.0*k68)))*y[70]
            dy[71]=1.0*k69*y[2]*y[58]
            dy[72]=1.0*k72*y[17]*y[54]+((1.0*(-k73))+((-1.0)*(1.0*k74)))*y[72]
            dy[73]=((-100.0)*(1.0*(konII*(1.0/nva))))*y[0]+((-100.0)*(1.0*(-koffII)))*y[1]+((-100.0)*(1.0*(konmIIa*(1.0/nva))))*y[2]+((-100.0)*(1.0*(-koffmIIa)))*y[3]+((-100.0)*(1.0*(konV*(1.0/nva))))*y[4]+((-100.0)*(1.0*(-koffV)))*y[5]+((-100.0)*(1.0*(konVa*(1.0/nva))))*y[6]+((-100.0)*(1.0*(-koffVa)))*y[7]+((-100.0)*(1.0*(konVII*(1.0/nva))))*y[8]+((-100.0)*(1.0*(-koffVII)))*y[9]+((-100.0)*(1.0*(konVIIa*(1.0/nva))))*y[10]+((-100.0)*(1.0*(-koffVIIa)))*y[11]+((-100.0)*(1.0*(konVIII*(1.0/nva))))*y[12]+((-100.0)*(1.0*(-koffVIII)))*y[13]+((-100.0)*(1.0*(konVIIIa*(1.0/nva))))*y[14]+((-100.0)*(1.0*(-koffVIIIa)))*y[15]+((-100.0)*(1.0*(konIX*(1.0/nva))))*y[16]+((-100.0)*(1.0*(-koffIX)))*y[17]+((-100.0)*(1.0*(konIXa*(1.0/nva))))*y[18]+((-100.0)*(1.0*(-koffIXa)))*y[19]+((-100.0)*(1.0*(konX*(1.0/nva))))*y[20]+((-100.0)*(1.0*(-koffX)))*y[21]+((-100.0)*(konXa*(1.0/nva)))*y[22]+((-100.0)*(-koffXa))*y[23]+((-100.0)*(1.0*(konAPC*(1.0/nva))))*y[24]+((-100.0)*(1.0*(-koffAPC)))*y[25]+((-100.0)*(1.0*(konPS*(1.0/nva))))*y[26]+((-100.0)*(1.0*(-koffPS)))*y[27]+((-100.0)*(1.0*(konVIIIai*(1.0/nva))))*y[28]+((-100.0)*(1.0*(-koffVIIIai)))*y[29]+((-100.0)*(1.0*(konVai*(1.0/nva))))*y[30]+((-100.0)*(1.0*(-koffVai)))*y[31]+((-100.0)*(1.0*(konPC*(1.0/nva))))*y[32]+((-100.0)*(1.0*(-koffPC)))*y[33]+100.0*(1.0*k38)*y[51]
            dy[74]=((-1.0)*(1.0*k78))*y[22]*y[74]+((-1.0)*(1.0*k77))*y[47]*y[74]
            dy[75]=1.0*k77*y[47]*y[74]
            dy[76]=1.0*k78*y[22]*y[74]
            dy[77]=1.0*k76*y[54]*y[58]

            dy
        }
    }
}

class TCMQModelCreator  {
    fun createModel(reduction: Int) : ModelInfo {
        return ModelInfo(
            randMatrix(3 - reduction, 3, -1.0 until 1.0).MGSON())
        { y: SimpleMatrix ->
            val dy = SimpleMatrix(y)

            dy[0] = y[0] * y[1] + y[2]
            dy[1] = 0.0
            dy[2] = 0.0

            dy
        }
    }
}