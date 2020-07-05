package ing;

import java.io.*;
import java.util.Scanner;

public class Ing {

    int cont, pal;
    char[] car;

    public static void main(String[] args) throws Exception {

        Aut p = new Aut();
        Textos t = new Textos();
        int a, opcion;
        String cadena, archivo, b;
        Scanner s = new Scanner(System.in);
        Scanner l = new Scanner(System.in);
        Scanner u = new Scanner(System.in);

        do {

            p.Menu();
            a = s.nextInt();

            if (a >= 3 || a < 1) {

                System.exit(0);
            }

            switch (a) {

                case 1:

                    System.out.print("Ingresar cadena:");
                    cadena = l.nextLine();
                    p.aut(cadena);
                    break;

                case 2:

                    System.out.println("Ingrese nombre del archivo:");
                    archivo = u.nextLine();
                    b = t.leer(archivo);
                    p.aut(b);
                    break;
            }

            System.out.println("\n\n");

        } while (a != 3);
    }

}
