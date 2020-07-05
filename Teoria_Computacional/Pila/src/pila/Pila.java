package pila;
import java.util.Scanner;

public class Pila {

    public static void main(String[] args) {

        Automata a = new Automata();
        String cadena;
        Scanner s = new Scanner(System.in);
        System.out.print("Ingresar cadena:");
        cadena = s.nextLine();
        a.pila(cadena);
    }

}
