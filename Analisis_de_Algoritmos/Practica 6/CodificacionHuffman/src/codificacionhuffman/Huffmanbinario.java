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

public class Huffmanbinario {

    public void huff(PriorityQueue<Double> alfabeto) {
        
        PriorityQueue<Double> huffmanf = new PriorityQueue<>();
        PriorityQueue<Double> huffman = new PriorityQueue<>();
        concatenar(alfabeto, huffmanf);
        while (alfabeto.size() != 1) {
            double aux1 = alfabeto.poll();
            alfabeto.peek();
            double aux2 = alfabeto.poll();
            alfabeto.peek();
            huffman.add(suma(aux1, aux2));
        }
        huffman.add(suma(alfabeto.poll(), 0.0));
        //System.out.println(huffman);
        concatenar(huffman, huffmanf);

        PriorityQueue<Double> huffman1 = new PriorityQueue<>();
        while (!huffman.isEmpty()) {
            double aux1 = huffman.peek();
            huffman.poll();
            double aux2 = huffman.peek();
            huffman.poll();
            huffman1.add(suma(aux1, aux2));
        }
        //System.out.println(huffman1);
        concatenar(huffman1, huffmanf);

        PriorityQueue<Double> huffman2 = new PriorityQueue<>();
        while (huffman1.size() != 1) {
            double aux1 = huffman1.poll();
            huffman1.peek();
            double aux2 = huffman1.poll();
            huffman1.peek();
            huffman2.add(suma(aux1, aux2));
        }
        huffman2.add(suma(huffman1.poll(), 0.0));
        //System.out.println(huffman2);
        concatenar(huffman2, huffmanf);

        PriorityQueue<Double> huffman3 = new PriorityQueue<>();
        while (!huffman2.isEmpty()) {
            double aux1 = huffman2.peek();
            huffman2.poll();
            double aux2 = huffman2.peek();
            huffman2.poll();
            huffman3.add(suma(aux1, aux2));
        }
        //System.out.println(huffman3);
        concatenar(huffman3, huffmanf);

        double aux1 = huffman3.peek();
        huffman3.poll();
        double aux2 = huffman3.peek();
        huffman3.poll();
        huffmanf.add(suma(aux1, aux2));
        huffmanf.remove(18.3);
        huffmanf.remove(36.2);
        System.out.println(huffmanf);
        codificar(huffmanf);
    }

    public static double suma(double a, double b) {

        double sum = a + b;
        return sum;
    }

    public static PriorityQueue<Double> concatenar(PriorityQueue<Double> a, PriorityQueue<Double> b) {

        Double[] g2 = new Double[a.size()];
        Double[] g1 = a.toArray(g2);
        for (int i = 0; i < g1.length; i++) {
            b.add(g1[i]);
        }
        return b;
    }

    public static void codificar(PriorityQueue<Double> a) {

        Double[] g2 = new Double[a.size()];
        Double[] g1 = a.toArray(g2);
        Arrays.sort(g1, Collections.reverseOrder());
        String b = "";
        for (int i = g1.length; i < 1; i--) {
            if (i % 2 == 0) {
                b = "0";
                while(i/2 != 1){
                    if (i%2 == 0){
                        b = b+"0";
                    } else{
                        b = b+"1"; 
                    }
                }
                System.out.println(b);
            } else {
                b = "1";
                while(i/2 != 1){
                    if (i%2 == 0){
                        b = b+"0";
                    } else{
                        b = b+"1"; 
                    }
                }
                System.out.println(b);
            }
        } 
    }

}
