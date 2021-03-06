import java.lang.Math;

public class calculos{
    public static void main(String[] args){
        double i = 0.0;
        double seno = 0.0;
        double coseno = 0.0;
        double tangente = 0.0;
        double logaritmo = 0.0;
        double raiz = 0.0;
        while(i<20000){
            seno += Math.sin(i);
            coseno += Math.cos(i);
            tangente += Math.tan(i);
            logaritmo += Math.log(i);
            raiz += Math.sqrt(i);
            i++; 
        }
    }
}