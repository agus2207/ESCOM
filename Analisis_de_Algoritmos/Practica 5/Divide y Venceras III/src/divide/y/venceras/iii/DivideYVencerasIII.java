/*
Autores: Galindo Reyes Agustin
         Yañes Martinez Josue Ricardo

Fecha: 04 de abril del 2018

Ultima modificacion: 15 de abril del 2018

Descripcion: Este es el menu principal del programa donde elegiremos cualquiera 
             de las 7 partes de la practica 5
*/

package divide.y.venceras.iii;
import java.util.Scanner;

public class DivideYVencerasIII {

    public static void Menu() {

        System.out.println("Bienvenido");
        System.out.println("Por favor seleccione una opcion");
        System.out.println("1.- Generacion de puntos");
        System.out.println("2.- Interpolacion de Lagrange");
        System.out.println("3.- Suma de polinomios");
        System.out.println("4.- Multiplicacion de polinomios");
        System.out.println("5.- Raices Complejas de la Unidad");
        System.out.println("6.- Matriz de Vandermonde");
        System.out.println("7.- Salir");
        System.out.print("Opcion: ");
    }

    public static void main(String[] args) throws Exception{

        Puntos p = new Puntos();
        DivideYVencerasIII d = new DivideYVencerasIII();
        Interpolacion i = new Interpolacion();
        Suma s = new Suma();
        Multiplicacion m = new Multiplicacion();
        RaicesComplejas rc = new RaicesComplejas();
        Vandermonde v = new Vandermonde();
        int a;
        Scanner t = new Scanner(System.in);

        do {

            d.Menu();
            a = t.nextInt();

            if (a >= 7 || a < 1) {

                System.exit(0);
            }

            switch (a) {

                case 1:

                    p.coeficientes();
                    break;

                case 2:

                    i.puntos();
                    break;
                 
                case 3:

                    s.puntos();
                    break;
                    
                case 4:

                    m.puntos();
                    break;
                    
                case 5:

                    rc.inicio();
                    break;
                    
                case 6:

                    v.inicio();
                    break;
            }

            System.out.println("\n");

        } while (a != 7);
    }

}
