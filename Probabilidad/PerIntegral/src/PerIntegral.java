import java.util.Scanner;

public class PerIntegral {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int a,b,n,Mu,Var;
        double h,h1,h2;
        char A=0;
        
        System.out.print("---------Calculo de 1era Integral-------\n");
        Scanner S1=new Scanner(System.in);
           
        System.out.print("Valor de a=");
        a=S1.nextInt();
        System.out.print("\n");
        System.out.print("Valor de b=");
        b=S1.nextInt();
        System.out.print("\n");
        A=181;
        System.out.print("Valor de "+A+"=");
        Mu=S1.nextInt();///Valor de Mu
        System.out.print("\n");
        A=330;
        System.out.print("Valor de "+A+"=");
        Var=S1.nextInt();// valor de la Varianza
        System.out.print("\n");
        while(Var==0){
            System.out.print(A+"no puede ser  0, Ingrese el dato de nuevo= ");
        Var=S1.nextInt();// valor de la Varianza
        }
        n=16;
        double I1=0,I2=0,Isup,Iinf, IFin;
        double m=0.5;
        h=(double)(b-a)/n;
        h2=(double)a+(h/2);
        h1=(double)a+h;
        //System.out.print(h);
        for(int i=0;i<n;i++){
            I1=(I1+(4*(Math.exp(m*(Math.pow(((h2-Mu)/(Var)),2))))));
           // System.out.print(h2+"\n");
            h2=h2+h;
            
        }
        //I1=I1*4;
        for(int j=0;j<n-1;j++){
            I1=(I1+(4*(Math.exp(m*(Math.pow(((h1-Mu)/(Var)),2))))));
            //System.out.print(h1+"\n");
            h1=h1+h;
        }
       // I2=I2*2;
        
        Isup=(Math.exp(m*(Math.pow(((b-Mu)/(Var)),2))));
        Iinf=(Math.exp(m*(Math.pow(((a-Mu)/(Var)),2))));
        
       IFin=(float) ((b-a)*(I1+I2+Isup+Iinf)/(Var*Math.sqrt(2*Math.PI)*6*n));//*(I1+I2+Isup+Iinf));
       
       System.out.print("La integral resultante="+IFin+"\n");
        
    }
    
}

