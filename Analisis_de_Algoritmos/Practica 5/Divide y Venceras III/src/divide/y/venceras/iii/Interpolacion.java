/*
Autores: Galindo Reyes Agustin
         Yañes Martinez Josue Ricardo

Fecha: 04 de abril del 2018

Ultima modificacion: 15 de abril del 2018

Descripcion: En esta funcion recibimos un conjunto de puntos y a traves de la 
             interpolacion de Lagrange recuperamos los coeficientes del polinomio
             original.
*/

package divide.y.venceras.iii;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Interpolacion {
    
    public void puntos() {

        System.out.println("Escribe el numero de puntos:");
        Scanner n = new Scanner(System.in);
        int num = n.nextInt();
        ArrayList<Double> x = new ArrayList<>();
        System.out.println("Introduzca los valores de x");
        llenar(num, x);
        ArrayList<Double> y = new ArrayList<>();
        System.out.println("Introduzca los valores de y");
        llenar(num, y);
        ArrayList<Double> res = interpolacion(x, y);
        resultado(res);
    }

    public void llenar(int num, ArrayList<Double> x) {

        for (int i = 0; i < num; i++) {
            Scanner s = new Scanner(System.in).useLocale(Locale.US);
            x.add(s.nextDouble());
        }
    }
    
    public static void resultado(ArrayList<Double> x) {

        for (int i = 0; i < x.size(); i++) {
            if(i == 0)
                System.out.print(x.get(i)+" + ");
            else if(i == 1)
                System.out.print(x.get(i)+" X + ");
            else if(i == x.size()-1)
                System.out.print(x.get(i)+" X"+i);
            else
                System.out.print(x.get(i)+" X"+i+" + ");
        }
    }
    
    public static ArrayList<Double> multiplica(ArrayList<Double> A, ArrayList<Double> B) {
       int grado = (A.size() - 1) + (B.size() - 1);
       ArrayList<Double> c = new ArrayList<>();
       for (int i = 0; i <= grado; i++)
            c.add(0.0);
       //System.out.println("grado: " + c.size());
       for(int i=0;i<A.size();i++){
           for(int j=0;j<B.size();j++){
               //System.out.println(i+j);
               c.set(i + j, c.get(i+j) + A.get(i)*B.get(j));
           }
       }
       //System.out.println("fin de la multiplica");
      return c;
    }
    
    public static ArrayList<Double> sumaPolinomio(ArrayList<Double> A, ArrayList<Double> B) {
        
        ArrayList<Double> suma= new ArrayList<>();
        for (int i = 0; i < Math.max(A.size(), B.size()); i++)
            suma.add(0.0);
        
        //System.out.println(suma);
        for (int i = 0; i < suma.size(); i++) {
            if (i < A.size()) suma.set(i, suma.get(i) + A.get(i));
            if (i < B.size()) suma.set(i, suma.get(i) + B.get(i));
        }
        //System.out.println(suma);
        return suma;
    }

    public static ArrayList<Double> interpolacion(ArrayList<Double> x, ArrayList<Double> y) {
       
       ArrayList<Double> resultado = new ArrayList<>();
       for(int i=0;i<y.size();i++){
           ArrayList<Double> aux = new ArrayList<>();
           aux.add(1.0);
           for(int j=0;j<x.size();j++){
                if(i!=j){
                    ArrayList<Double> act= new ArrayList<>();
                    act.add(-(x.get(j)));
                    act.add(1.0);
                    //System.out.println("antes:" + aux);
                    aux=multiplica(aux,act);
                    //System.out.println("despues:" + aux);
                }
                
            }
            double coef=y.get(i);
            for(int j=0;j<x.size();j++){
                if(i!=j)
                    coef /= (x.get(i) - x.get(j));
            }
             for(int j=0;j<aux.size();j++)
                 aux.set(j, aux.get(j) * coef);
            //System.out.println("antes: " + resultado);
             resultado=sumaPolinomio(aux,resultado);
             //System.out.println("despues: " + resultado);
       }
       return resultado;
    }
}
