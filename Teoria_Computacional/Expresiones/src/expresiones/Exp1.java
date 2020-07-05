package expresiones;

import java.io.FileWriter;

public class Exp1 {

    FileWriter f;

    public static void Menu() {

        System.out.println("Bienvenido ");
        System.out.println("Por favor seleccione una opcion");
        System.out.println("1.- Expresion 1 --> ((0+10)*(ε+1)) ");
        System.out.println("2.- Expresion 2 --> [(10)*0+1(01)*1] [(0(01)*(1+00) + 1(10)*(0+11))]*");
        System.out.println("3.- Salir ");
        System.out.print("Opcion: ");
    }

    public void kleene() throws Exception {

        f = new FileWriter("Expresiones.txt");
        String a = "";
        int c = 0;
        f.write("Las expresiones generadas son:");
        f.write("\r\n");

        while (c <= 4) {

            for (int i = 0; i <= Math.random() * 100; i++) {

                double r = Math.rint(Math.random());
                if (r == 1) {

                    a = a + "10";

                } else {

                    a = a + "0";
                }
            }

            double r1 = Math.rint(Math.random());
            if (r1 == 1) {

                a = a + "1";

            } else {

                a = a + "ε";
            }
            c++;
            f.write(c + ".- " + a);
            f.write("\r\n");
            System.out.println(c + ".- " + a);
            a = "";
        }
        
        f.close();
    }
}
