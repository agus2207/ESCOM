package palindromo;

import java.util.Scanner;

public class Palindromo {

    public static void main(String[] args) throws Exception {

        Generar g = new Generar();
        int a;
        Scanner s = new Scanner(System.in);
        do {

            g.Menu();
            a = s.nextInt();

            if (a >= 2 || a < 1) {

                System.exit(0);
            }

            switch (a) {

                case 1:

                    System.out.print("El palindromo generado es:\n");
                    g.pal();
                    break;
            }

            System.out.println("\n\n");

        } while (a != 2);
    }

}
