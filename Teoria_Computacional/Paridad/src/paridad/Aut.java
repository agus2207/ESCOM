package paridad;

import java.io.FileWriter;
import java.util.Random;

public class Aut {

    int q = 0, c0 = 0, c1 = 0, s = 0;
    String a = "";
    FileWriter f, f1;

    public static void Menu() {

        System.out.println("Bienvenido ");
        System.out.println("Por favor seleccione una opcion");
        System.out.println("1.- Manual ");
        System.out.println("2.- Aleatorio ");
        System.out.println("3.- Leer Archivo ");
        System.out.println("4.- Salir ");
        System.out.print("Opcion: ");
    }

    public static String random(int l) {

        String r="";
        char[] chars = "01".toCharArray();
        int charsLength = chars.length;
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < l; i++) {
            
            buffer.append(chars[random.nextInt(charsLength)]);
        }

        r = buffer.toString();
        r= r+" ";
        return r;       
    }

    public void aut(String p) throws Exception {

        f = new FileWriter("Historial.txt");
        f1 = new FileWriter("Cadenas.txt");
        q = 0;
        c0 = 0;
        c1 = 0;
        f.write("Inicio en q0");
        f.write("\r\n");
        f1.write("Cadenas Aceptadas");
        f1.write("\r\n");

        for (int i = 0; i < p.length(); i++) {

            char c = p.charAt(i);

            if (c == '0' || c == '1') {

                if (q == 0) //estado q0
                {
                    if (c == '0') {
                        //System.out.println("q" + q + "----->" + c + "----->q1");
                        f.write("q" + q + "----->" + c + "----->q1");
                        f.write("\r\n");
                        q = 1; //se queda en q0
                        c0++;
                        a = a + c;
                    }
                    if (c == '1') {
                        //System.out.println("q" + q + "----->" + c + "----->q3");
                        f.write("q" + q + "----->" + c + "----->q3");
                        f.write("\r\n");
                        q = 3; //se va a q1
                        c1++;
                        a = a + c;
                    }
                    //a=a+c;
                    continue;
                }
                if (q == 1) //estado q1
                {
                    if (c == '0') {
                        //System.out.println("q" + q + "----->" + c + "----->q0");
                        f.write("q" + q + "----->" + c + "----->q0");
                        f.write("\r\n");
                        q = 0; //se regresa a q0
                        c0++;
                        a = a + c;
                    }
                    if (c == '1') {
                        //System.out.println("q" + q + "----->" + c + "----->q2");
                        f.write("q" + q + "----->" + c + "----->q2");
                        f.write("\r\n");
                        q = 2; //se queda en q1
                        c1++;
                        a = a + c;
                    }
                    //a=a+c;
                    continue;
                }
                if (q == 2) //estado q1
                {
                    if (c == '0') {
                        //System.out.println("q" + q + "----->" + c + "----->q3");
                        f.write("q" + q + "----->" + c + "----->q3");
                        f.write("\r\n");
                        q = 3; //se regresa a q0
                        c0++;
                        a = a + c;
                    }
                    if (c == '1') {
                        //System.out.println("q" + q + "----->" + c + "----->q1");
                        f.write("q" + q + "----->" + c + "----->q1");
                        f.write("\r\n");
                        q = 1; //se queda en q1
                        c1++;
                        a = a + c;
                    }
                    //a=a+c;
                    continue;
                }
                if (q == 3) //estado q1
                {
                    if (c == '0') {
                        //System.out.println("q" + q + "----->" + c + "----->q2");
                        f.write("q" + q + "----->" + c + "----->q2");
                        f.write("\r\n");
                        q = 2; //se regresa a q0
                        c0++;
                        a = a + c;
                    }
                    if (c == '1') {
                        //System.out.println("q" + q + "----->" + c + "----->q0");
                        f.write("q" + q + "----->" + c + "----->q0");
                        f.write("\r\n");
                        q = 0; //se queda en q1
                        c1++;
                        a = a + c;
                    }
                    //a=a+c;
                    continue;
                }
                //a=a+c;
            } else {

                //System.out.println("q" + q + "----->" + c + "----->q0");
                f.write("q" + q + "----->" + c + "----->q0");
                f.write("\r\n");
                q = 0;
            }
            if ((c0 % 2 == 0 )&& (c1 % 2 == 0)) {

                //System.out.println("Contador de 0 = "+c0);
                //System.out.println("Contador de 1 = " +c1);
                //System.out.println("Cadena con Paridad");
                f.write("Cadena con Paridad");
                f.write("\r\n");
                //System.out.println(a);
                f1.write(a);
                f1.write("\r\n");
                s++;
                a = "";
                c0=0;
                c1=0;
            }
        }
        f.write("Fin del Automata");
        f1.write("Cadenas totales con Paridad " + s);
        f.close();
        f1.close();
        //System.out.println("Cadena totales con Paridad " + s);
        s = 0;
    }
}
