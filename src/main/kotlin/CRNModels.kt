
import org.ejml.simple.SimpleMatrix
import utility.MGSON
import utility.ModelInfo
import utility.randMatrix
import utility.until
import kotlin.math.pow

class T10ModelCreator  {
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


class T11ModelCreator  {
    fun createModel(reduction: Int) : ModelInfo {
        return ModelInfo(
                randMatrix(83 - reduction, 83, -1.0 until 1.0).MGSON())
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



class T12ModelCreator  {
    fun createModel(reduction: Int) : ModelInfo {
        return ModelInfo(
                randMatrix(88 - reduction, 88, -1.0 until 1.0).MGSON())
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


class T13ModelCreator  {
    fun createModel(reduction: Int) : ModelInfo {
        return ModelInfo(
                randMatrix(70 - reduction, 70, -1.0 until 1.0).MGSON())
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
