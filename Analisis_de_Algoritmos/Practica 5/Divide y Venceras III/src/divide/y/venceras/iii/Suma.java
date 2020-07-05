/*
Autores: Galindo Reyes Agustin
         Yañes Martinez Josue Ricardo

Fecha: 04 de abril del 2018

Ultima modificacion: 15 de abril del 2018

Descripcion: En esta funcion calculamos la suma de dos polinomios
             mediante las coordenadas de ambos polinomios, donde ambos
             deben coincidir en x y sumamos las coordenadas en y de los 
             polinomios, creando las coordenadas de un nuevo polinomio
             al cual mediante la interpolacion de Lagrange obtendremos 
             sus coeficientes.
*/

package divide.y.venceras.iii;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import java.util.Hashtable;
import java.util.ArrayList;

public class Suma {
    
     public void puntos() {

        System.out.println("Escribe el numero de puntos:");
        Scanner n = new Scanner(System.in);
        int num = n.nextInt();
        ArrayList<Double> x = new ArrayList<>();
        System.out.println("Introduzca los valores de x");
        llenar(num, x);
        ArrayList<Double> yA = new ArrayList<>();
        System.out.println("Introduzca los valores de yA");
        llenar(num, yA);
        ArrayList<Double> yB = new ArrayList<>();
        System.out.println("Introduzca los valores de yB");
        llenar(num, yB);
        datosc(num, x, yA, yB);
    }

    public void llenar(int num, ArrayList<Double> x) {

        for (int i = 0; i < num; i++) {
            Scanner s = new Scanner(System.in).useLocale(Locale.US);
            x.add(s.nextDouble());
        }
    }

    public void datosc(int num, ArrayList<Double> x, ArrayList<Double> yA, ArrayList<Double> yB) {
        
        ArrayList<Double> c = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            c.add(yA.get(i) + yB.get(i));
        }
        ArrayList<Double> res = Interpolacion.interpolacion(x, c);
        System.out.println("El polinomio resultante es:");
        Interpolacion.resultado(res);
    }
}
