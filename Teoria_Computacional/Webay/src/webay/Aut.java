package webay;

import java.util.StringTokenizer;
import java.io.FileWriter;

public class Aut {

    int q = 0, s = 0;
    String a = "";
    String b = "";
    FileWriter f, f1;

    public static void Menu() {

        System.out.println("Bienvenido ");
        System.out.println("Por favor seleccione una opcion");
        System.out.println("1.- Manual ");
        System.out.println("2.- Leer Archivo ");
        System.out.println("3.- Salir ");
        System.out.print("Opcion: ");
    }

    public void aut(String p) throws Exception {
        q = 0;
        f = new FileWriter("Historial.txt");
        f1 = new FileWriter("Palabras.txt");
        f.write("Inicio en q0");
        f.write("\r\n");
        f1.write("Palabras Aceptadas");
        f1.write("\r\n");
        StringTokenizer st = new StringTokenizer(p);
        
        while (st.hasMoreElements()) {
            
            String token = st.nextElement().toString();
            token = token + " ";
            for (int i = 0; i < token.length(); i++) {

                char c = token.charAt(i);

                if (c >= 97 && c <= 122 || c >= 65 && c <= 90) {

                    if (c == 'w' || c == 'W') {

                        //System.out.println("q" + q + "----->" + c + "----->q1");
                        f.write("q" + q + "----->" + c + "----->q1");
                        f.write("\r\n");
                        q = 1;
                        a = a + c;
                    } else if (c == 'e' && q == 1 || c == 'E' && q == 1) {

                        //System.out.println("q" + q + "----->" + c + "----->q2");
                        f.write("q" + q + "----->" + c + "----->q2");
                        f.write("\r\n");
                        q = 2;
                        a = a + c;
                        b = b + c;
                    } else if (c == 'b' && q == 2 || c == 'B' && q == 2) {

                        //System.out.println("q" + q + "----->" + c + "----->q3");
                        f.write("q" + q + "----->" + c + "----->q3");
                        f.write("\r\n");
                        q = 3;
                        a = a + c;
                        b = b + c;
                    } else if (c == 'e' && q == 0 || c == 'E' && q == 0 || c == 'e' && q == 4 ||c == 'e' && q == 3 || c == 'E' && q == 3 || c == 'E' && q == 4 || c == 'e' && q == 2 || c == 'E' && q == 2 || c == 'e' && q == 5 || c == 'E' && q == 5 || c == 'e' && q == 6 || c == 'E' && q == 6 || c == 'e' && q == 7 || c == 'E' && q == 7) {

                        //System.out.println("q" + q + "----->" + c + "----->q3");
                        f.write("q" + q + "----->" + c + "----->q4");
                        f.write("\r\n");
                        q = 4;
                        b = b + c;
                    } else if (c == 'b' && q == 4 || c == 'B' && q == 4) {

                        //System.out.println("q" + q + "----->" + c + "----->q3");
                        f.write("q" + q + "----->" + c + "----->q5");
                        f.write("\r\n");
                        q = 5;
                        b = b + c;
                    } else if (c == 'a' && q == 5 || c == 'A' && q == 5 || c == 'a' && q == 3 || c == 'A' && q == 3) {

                        //System.out.println("q" + q + "----->" + c + "----->q3");
                        f.write("q" + q + "----->" + c + "----->q6");
                        f.write("\r\n");
                        q = 6;
                        b = b + c;
                    } else if (c == 'y' && q == 6 || c == 'Y' && q == 6) {

                        //System.out.println("q" + q + "----->" + c + "----->q3");
                        f.write("q" + q + "----->" + c + "----->q7");
                        f.write("\r\n");
                        q = 7;
                        b = b + c;
                    } else {
                        //System.out.println("q" + q + "----->" + c + "----->q0");
                        f.write("q" + q + "----->" + c + "----->q0");
                        f.write("\r\n");
                        q = 0;
                        a = "";
                        b = "";
                    }
                    //a=a+c;

                } else {
                    //System.out.println("q" + q + "----->" + c + "----->q0");
                    f.write("q" + q + "----->" + c + "----->q0");
                    f.write("\r\n");
                    q = 0;
                    a = "";
                    b = "";
                }

                if (q == 3 || q == 7) {

                    //System.out.println(a);
                    if (q == 3) {

                        f1.write("Encontre: "+a+" en ---> "+token);
                        f1.write("\r\n");
                        a = "";
                    } else if (q == 7) {

                        f1.write("Encontre: "+b+" en ---> "+token);
                        f1.write("\r\n");
                        b = "";
                    } else if (q == 3 && q == 7) {

                        f1.write("Encontre: "+a+" en ---> "+token);
                        f1.write("\r\n");
                        f1.write("Encontre: "+a+" en ---> "+token);
                        f1.write("\r\n");
                        a = "";
                        b = "";
                    }
                    s++;
                    //System.out.println("Palabras aceptadas "+s);
                    //f1.write("Palabras aceptadas "+s);
                    //a = "";
                }
                //a = "";
            }
        }
        f.write("Fin del Automata");
        f1.write("Numero de palabras aceptadas " + s);
        s = 0;
        f.close();
        f1.close();
    }

}
