package ing;

import java.io.FileWriter;

public class Aut {

    int q = 0, s = 0;
    String a = "";
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
        p=p+'\0';

        for (int i = 0; i < p.length(); i++) {

            char c = p.charAt(i);

            if (c >= 97 && c <= 122 || c >= 65 && c <= 90) {
                
                //a=a+c;

                if (c == 'i' || c == 'I') {

                    //System.out.println("q" + q + "----->" + c + "----->q1");
                    f.write("q" + q + "----->" + c + "----->q1");
                    f.write("\r\n");
                    q = 1;
                } else if (c == 'n' && q == 1 || c == 'N' && q == 1) {

                    //System.out.println("q" + q + "----->" + c + "----->q2");
                    f.write("q" + q + "----->" + c + "----->q2");
                    f.write("\r\n");
                    q = 2;
                } else if (c == 'g' && q == 2 || c == 'G' && q == 1) {

                    //System.out.println("q" + q + "----->" + c + "----->q3");
                    f.write("q" + q + "----->" + c + "----->q3");
                    f.write("\r\n");
                    q = 3;
                } else {
                    //System.out.println("q" + q + "----->" + c + "----->q0");
                    f.write("q" + q + "----->" + c + "----->q0");
                    f.write("\r\n");
                    q = 0;
                }
                a = a + c;

            } else {
                
                if (q == 3) {

                    //System.out.println(a);
                    f1.write(a);
                    f1.write("\r\n");
                    s++;
                    a = "";
                } 
                
                a="";
            }  
        }
        //a="";
        f.write("Fin del Automata");
        f1.write("Numero de palabras aceptadas " + s);
        f.close();
        f1.close();
        s = 0;
    }

}
