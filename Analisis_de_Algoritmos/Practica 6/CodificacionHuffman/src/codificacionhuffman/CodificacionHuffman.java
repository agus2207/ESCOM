/*
Autores: Galindo Reyes Agustin
         Yañes Martinez Josue Ricardo

Fecha: 04 de abril del 2018

Ultima modificacion: 15 de abril del 2018

Descripcion: Este es el menu principal del programa donde elegiremos cualquiera 
             de las 7 partes de la practica 5
*/

package codificacionhuffman;
import java.util.*;

public class CodificacionHuffman {

    public static void Menu() {

        System.out.println("Bienvenido");
        System.out.println("Por favor seleccione una opcion");
        System.out.println("1.- Huffman Binario");
        System.out.println("2.- Huffman con tres nodos");
        System.out.println("3.- Salir");
        System.out.print("Opcion: ");
    }
    
    public static void main(String[] args) {

        PriorityQueue<Double> alfabeto = new PriorityQueue<>();
        alfabeto.add(18.3);
        alfabeto.add(10.2);
        alfabeto.add(7.7);
        alfabeto.add(6.8);
        alfabeto.add(5.9);
        alfabeto.add(5.8);
        alfabeto.add(5.5);
        alfabeto.add(5.1);
        alfabeto.add(4.9);
        alfabeto.add(4.8);
        alfabeto.add(3.5);
        alfabeto.add(3.4);
        alfabeto.add(2.6);
        alfabeto.add(2.4);
        alfabeto.add(2.1);
        alfabeto.add(1.9);
        alfabeto.add(1.8);
        alfabeto.add(1.7);
        alfabeto.add(1.6);
        alfabeto.add(1.6);
        alfabeto.add(1.3);
        alfabeto.add(0.9);
        alfabeto.add(0.6);
        alfabeto.add(0.2);
        alfabeto.add(0.2);
        alfabeto.add(0.1);
        alfabeto.add(0.1);
        //double sum = 0.0;
        //double aux = 0.0;
        
        Huffmanbinario hb = new Huffmanbinario();
        Huffmandetres ht = new Huffmandetres();
        CodificacionHuffman ch = new CodificacionHuffman();
        
        int a;
        Scanner t = new Scanner(System.in);

        do {

            ch.Menu();
            a = t.nextInt();

            if (a >= 3 || a < 1) {

                System.exit(0);
            }

            switch (a) {

                case 1:

                    hb.huff(alfabeto);
                    break;

                case 2:

                    ht.huff(alfabeto);
                    break;
            }

            System.out.println("\n");

        } while (a != 3);
    }
}
