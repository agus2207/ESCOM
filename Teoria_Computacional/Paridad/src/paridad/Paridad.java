package paridad;
import java.util.Scanner;

public class Paridad {

    public static void main(String[] args) throws Exception {
        
        Aut p = new Aut();
        Textos t = new Textos();
        int a, opcion, n;
        String cadena, archivo, b, k;
        Scanner s = new Scanner(System.in);
        Scanner l = new Scanner(System.in);
        Scanner u = new Scanner(System.in);
        Scanner w = new Scanner(System.in);

        do {

            p.Menu();
            a = s.nextInt();

            if (a >= 4 || a < 1) {

                System.exit(0);
            }

            switch (a) {

                case 1:

                    System.out.print("Ingresar cadena:");
                    cadena = l.nextLine();
                    p.aut(cadena);
                    break;
                  
                case 2:
                    
                    System.out.print("Introduzca tamaño de cadena: ");
                    n = w.nextInt();
                    System.out.print("Generando cadena:");
                    k=p.random(n);
                    System.out.println("\n");
                    System.out.print(k);
                    System.out.println("\n");
                    p.aut(k);
                    break;

                case 3:

                    System.out.println("Ingrese nombre del archivo:");
                    archivo = u.nextLine();
                    b = t.leer(archivo);
                    p.aut(b);
                    break;
            }

            System.out.println("\n\n");

        } while (a != 4);
    }
    
}
