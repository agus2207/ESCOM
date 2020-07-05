package pila;

import java.util.*;

public class Automata {
    
     public static void Menu() {

        System.out.println("Bienvenido ");
        System.out.println("Por favor seleccione una opcion");
        System.out.println("1.- Manual ");
        System.out.println("2.- Leer Archivo ");
        System.out.println("3.- Salir ");
        System.out.print("Opcion: ");
    }

    public static void pila(String s) {

        LinkedList lista = new LinkedList();
        lista.addLast("Z0");
        String a = "Z0";
        String b = "" + s;
        System.out.println("q, " + s + " ," + a);
        for (int i = 0; i < s.length(); i++) {

            //String b = ""+s;
            char c = s.charAt(i);

            if (c == '0' || c == '1') {

                if (c == '0') {

                    lista.addFirst(c);
                    a = "X" + a;
                    b = b.substring(1);
                    System.out.println("q, " + b + " ," + a);
                    
                } else {

                    lista.removeFirst();
                    a = a.substring(1);
                    b = b.substring(1);
                    if (b.isEmpty()) {

                        System.out.println("p, ε ," + a);
                        
                    } else {

                        System.out.println("p, " + b + " ," + a);
                    }
                }
            } else {

                System.out.println("Error");
            }

        }
        if (lista.getFirst() == "Z0") {

            System.out.println("Cadena aceptada");
        } else if (lista.getFirst() == null){

            throw new NoSuchElementException();
            //System.out.println("Cadena no aceptada");
        }

    }

}
