/*
Autores: Galindo Reyes Agustin
         Yañes Martinez Josue Ricardo

Fecha: 18 de abril del 2018

Ultima modificacion: 15 de abril del 2018

Descripcion: En esta funcion calculamos la matriz de Vandermonde donde
             remplazamos los valores de x por las raices complejas de la 
             unidad que el usuario introduzca.
*/

package divide.y.venceras.iii;
import java.util.Scanner;
import  java.lang.Math; 

public class Vandermonde {

    public void inicio() {

        int a;
        System.out.println("Introduzca el tamaño del arreglo:");
        Scanner s = new Scanner(System.in);
        a = s.nextInt();
        raices(a);
    }
    
    public void raices(int a){
        
        double re = Math.cos((2*Math.PI)/a);
        double im = Math.sin((2*Math.PI)/a);
        Complejo c = new Complejo(re, im);
        System.out.print("\nW"+a+" = "+c+"\n\n");
        Complejo comp[] = new Complejo[a];
        System.out.print("Las raices complejas de la unidad son: \n\n");
        for (int i = 0; i < a; i++){
            comp[i] = Complejo.potencia(c, i);
            System.out.print(comp[i]+"\n");
        }
        matriz(comp, a);
    }
    
    public void matriz(Complejo comp[], int a){
        
        Complejo mv[][] = new Complejo[a][a];
        System.out.println("\nLa matriz de Vandermonde es: \n");
        for (int i = 0; i < a; i++){
            for (int j = 0; j < a; j++){
                mv[i][j] = Complejo.potencia(comp[i], j);
                System.out.print(" | " + mv[i][j]+ " | "); 
            }
            System.out.print("\n");
        }
    }
}
