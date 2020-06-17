
package BSM;

public class BSM {

    public static void main(String[] args) {
        //some initializations 
        double X, S, T, T2, T1, r, ba,b, v, toh;
        double d1,d2,Xa, Sa, power1, power2, power3, power4, power5, M1, M2,c,p;
        double pw1, pw2,pw3, pw4;
        // input parameters
        X=165;      //Strike price
        T=1;    
        T2=0.5;     //expiration time
        r=0.1;      // Risk free interest
        T1=T-T2;
        v=0.12;     // volatility
        b=0.05;
        toh = 0.5;  
        S=172.77;   // Asset Price
        
       M1= (Math.exp(b*T)-Math.exp(b*toh)) / (b*(T-toh));
        //System.out.println("value of M1 is "+M1);
         power1= (2*b)+(v*v);
        power2= b+(v*v);
        power3= T-toh;
        power4= (1/power1) - (Math.exp(b*power3)) / power2;
        
        M2 = ((2*Math.exp(power1*T) ) / (power2*power1*(power3*power3)) ) + ( (2*Math.exp((power1)*toh) )/ b*(power3*power3)) * power4;
        //System.out.println("value of M2 is "+M2);
        
        ba = Math.log(M1)/T;
        //System.out.println("value of ba is "+ba);
        
        v= Math.sqrt( ( (Math.log(M2)) /T) - 2*ba);            //sigma a
        double iv=v*10;
        
        d1= (Math.log(S/X)+ (ba+ (v*v)/2)*T2 )/(v*Math.sqrt(T2));
        //System.out.println("value of d1 is "+d1);
        
        d2= d1- (v*Math.sqrt(T2));
        //System.out.println("value of d2 is "+d2);
        System.out.println("value of implied volatility is "+iv);
        
            power5=ba-r;
            c= S*Math.exp(power5)*T2*CND(d1)- X*Math.exp(-r*T2)*CND(d2);
            if(c>0)
            System.out.println("value of C is "+c);
            else
            System.out.println("value of C is "+0);
       
            p= X*Math.exp(r*T2)*CND(d2)-S*Math.exp(power5)*T2*CND(d1);
            if(p>0)
            System.out.println("value of p is "+p);
            else
            System.out.println("value of p is "+0);
         
    }
    private static double CND(double x) 
     {
        int neg = (x < 0d) ? 1 : 0;
        if ( neg == 1) 
        x *= -1d;

        double k = (1d / ( 1d + 0.2316419 * x));
        double y = (((( 1.330274429 * k - 1.821255978) * k + 1.781477937) *
                   k - 0.356563782) * k + 0.319381530) * k;
                y = 1.0 - 0.398942280401 * Math.exp(-0.5 * x * x) * y;

        return (1d - neg) * y + neg * (1d - y);
    }
    
}
