/*
Autores: Galindo Reyes Agustin
         Yañes Martinez Josue Ricardo

Fecha: 11 de abril del 2018

Ultima modificacion: 15 de abril del 2018

Descripcion: En esta funcion calculamos las raices complejas de la unidad
             especificada por el usuario las cuales almacenaremos en un 
             archivo .
*/

package divide.y.venceras.iii;
import java.io.FileWriter;
import java.util.Scanner;

public class RaicesComplejas {
    
    FileWriter f, f1;
    
    public void inicio() throws Exception {

        int a;
        System.out.print("Introduzca la unidad:");
        Scanner s = new Scanner(System.in);
        a = s.nextInt();
        raices(a);
    }
    
    public void raices(int a) throws Exception{
        
        f = new FileWriter("Raices Complejas.txt");
        f.write("Las raices complejas de la unidad son:");
        f.write("\r\n");
        double re = Math.cos((2*Math.PI)/a);
        double im = Math.sin((2*Math.PI)/a);
        Complejo c = new Complejo(re, im);
        System.out.print("\nW"+a+" = "+c+"\n\n");
        f.write("\nW"+a+" = "+c+"\n\n");
        f.write("\r\n");
        Complejo comp[] = new Complejo[a];
        for (int i = 0; i < a; i++){
            comp[i] = Complejo.potencia(c, i);
            f.write("W"+a+"^"+i+" = "+comp[i]);
            f.write("\r\n");
        }
        System.out.println("Archivo Generado");
        f.close();
    }
}
