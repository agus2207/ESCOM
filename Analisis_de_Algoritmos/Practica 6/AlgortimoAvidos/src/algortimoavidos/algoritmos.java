
package algortimoavidos;

import java.util.Arrays;
import java.util.Collections;


public class algoritmos {
    
    public void voraz1(Double[] factores){
        Arrays.sort(factores,Collections.reverseOrder());
        
        System.out.println("Orden de licencias a comprar");
        for(int i=0;i<factores.length;i++)
            System.out.println("Lincencia con factor "+factores[i]);
    
    }
    
}
