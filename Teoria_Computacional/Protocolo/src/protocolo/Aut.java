package protocolo;

import java.util.*;

public class Aut {

    int q = 0, s = 0, cont;
    String a = "";
    //char c;
    char[] car;

    public static void Menu() {

        System.out.println("Bienvenido ");
        System.out.println("Por favor seleccione una opcion");
        System.out.println("1.- Manual ");
        System.out.println("2.- Leer Archivo ");
        System.out.println("3.- Salir ");
        System.out.print("Opcion: ");
    }

    public void Pro(String p) throws Exception {

        String t = "";
        ArrayList<String> al = new ArrayList<>();
        if (p.length() >= 100) {

            for (int i = 0; i < p.length(); i++) {
                t += p.charAt(i);
                if (i % 99 == 0 && i != 0) {
                    System.out.println("package_protocol: " + t);
                    al.add(t);
                    t = "";
                }
            }
            System.out.println("package_protocol: " + t);
            if (!t.isEmpty()) {
                al.add(t);
            }

        } else {
            al.add(p);
        }
        
        q = 0;
        double r = Math.rint(Math.random());
        if (r == 1) {//Is working = true
            System.out.println("Protocol is on");
            //historyProtocol.writeSth( + "---" + 1 + "--->" + Ready);
            q = 1;
            
        } else {//Protocol isn't working
            //historyProtocol.writeSth(actualState + "---" + 0 + "--->" + Start);
            System.out.println("Protocol is off");
        }
    }
}

