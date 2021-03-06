///Paquete de Trabajo
package Utilerias;

///Librerias
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Utileria_Matematicas
{
    ///Atributos

    ///Constructores
    public Utileria_Matematicas()
    {
        
    }
    
    ///Métodos de propósito General
    public double efectuarRedondeo(double valor, int cifrasDespuesDelPunto)
    {
        // Variables
        String val;
        BigDecimal big;
        
        // Inicialización
        val=valor+"";
        big=new BigDecimal(val);
        
        // Procesamos
        big = big.setScale(cifrasDespuesDelPunto, RoundingMode.HALF_DOWN);
        valor=Double.parseDouble(big.toString());
       
        // Retornamos resultado
        return valor;
    }
    
    ///Métodos de propósito Específico

    ///Métodos para el manejo de Atributos
    
}
