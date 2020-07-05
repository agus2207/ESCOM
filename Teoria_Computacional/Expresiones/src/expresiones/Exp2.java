package expresiones;

import java.io.FileWriter;

public class Exp2 {

    FileWriter f;

    public void kleene() throws Exception {

        f = new FileWriter("Expresiones.txt");
        String a = "";
        String b = "";
        String d = "";
        int c = 0;
        f.write("Las Expresiones generadas son:");
        f.write("\r\n");
        
        while (c <= 4) {

            double r0 = Math.rint(Math.random());
            if (r0 == 1) {

                for (int i = 0; i <= Math.random() * 10; i++) {

                    a = a + "10";
                }

                a = a + "0";

            } else {

                a = a + "1";
                for (int i = 0; i <= Math.random() * 10; i++) {

                    a = a + "01";
                }
                a = a + "1";
            }

            for (int k = 0; k <= Math.random() * 10; k++) {

                double r1 = Math.rint(Math.random());
                if (r1 == 1) {

                    b = b + "0";
                    for (int j = 0; j <= Math.random() * 10; j++) {

                        b = b + "01";
                    }

                    double r2 = Math.rint(Math.random());
                    if (r2 == 1) {

                        b = b + "1";

                    } else {

                        b = b + "00";
                    }

                } else {

                    b = b + "1";
                    for (int j = 0; j <= Math.random() * 10; j++) {

                        b = b + "10";
                    }
                    double r2 = Math.rint(Math.random());
                    if (r2 == 1) {

                        b = b + "0";

                    } else {

                        b = b + "11";
                    }
                }
            }

            c++;
            d = a + b;
            f.write(c + ".- " + d);
            f.write("\r\n");
            System.out.println(c + ".- " + d);
            a = "";
            b = "";
            d = "";
        }
        
        f.close();
    }

}
