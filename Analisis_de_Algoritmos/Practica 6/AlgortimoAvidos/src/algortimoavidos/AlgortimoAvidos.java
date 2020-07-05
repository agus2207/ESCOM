
package algortimoavidos;

import java.util.Scanner;

public class AlgortimoAvidos {

    public static void main(String[] args) {
        
        // TODO code application logic here
        Scanner entrada = new Scanner(System.in);
        algoritmos obj = new algoritmos();
        System.out.println("Ingresa el numero de licencias");
        int NLicencias = entrada.nextInt();
        Double[] factores = new Double[NLicencias];
        for(int i=0;i<NLicencias;i++){
            System.out.println("Ingresa el factor "+(int)(i+1));
            factores[i]=entrada.nextDouble();
            
        }
        obj.voraz1(factores);
        
        
    }
    
}
