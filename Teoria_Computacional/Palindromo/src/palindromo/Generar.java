package palindromo;

import java.io.FileWriter;

public class Generar {

    FileWriter f, f1;

    public static void Menu() {

        System.out.println("Bienvenido ");
        System.out.println("Por favor seleccione una opcion");
        System.out.println("1.- Generar palindromo");
        System.out.println("2.- Salir");
        System.out.print("Opcion: ");
    }

    public void pal() throws Exception {

        f = new FileWriter("Pasos.txt");
        f1 = new FileWriter("Palindromo.txt");
        f.write("Pasos");
        f.write("\r\n");
        f1.write("Palindromo Genarado");
        f1.write("\r\n");
        String a = "P";
        int random;
        random = (int) (Math.random() * 100);
        for (int i = 0; i <= random/2; i++) {

            double r1 = Math.rint(Math.random());
            if (r1 == 1) {

                a = a.replaceAll("P", "0P0");
                System.out.println(a);
                f.write(a);
                f.write("\r\n");
            } else {

                a = a.replaceAll("P", "1P1");
                System.out.println(a);
                f.write(a);
                f.write("\r\n");
            }
            if (i == random/2) {

                int random1;
                random1 = (int) (Math.random() * 3) + 1;
                if (random1 == 1) {

                    a = a.replaceAll("P", "0");
                    System.out.println(a);
                    f1.write(a);
                    f1.write("\r\n");
                } else if (random1 == 2) {

                    a = a.replaceAll("P", "1");
                    System.out.println(a);
                    f1.write(a);
                    f1.write("\r\n");
                } else if (random1 == 3) {

                    a = a.replaceAll("P", "ε");
                    System.out.println(a);
                    f1.write(a);
                    f1.write("\r\n");
                }
            }
        }
        f.close();
        f1.close();
    }
}
