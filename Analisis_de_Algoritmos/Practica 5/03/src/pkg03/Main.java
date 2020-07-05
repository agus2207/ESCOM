package pkg03;

import java.util.Scanner;

public class Main {

    public void puntos() {

        int a;
        System.out.println("Introduzca el tamaño del arreglo:");
        Scanner s = new Scanner(System.in);
        a = s.nextInt();
        int arrayx[] = new int[a];
        System.out.println("Introduzca los elementos x:");
        for (int i = 0; i < a; i++) {
            Scanner t = new Scanner(System.in);
            int b = t.nextInt();
            arrayx[i] = b;
        }
        int arraya[] = new int[a];
        System.out.println("Introduzca los elementos de y en A:");
        for (int i = 0; i < a; i++) {
            Scanner t = new Scanner(System.in);
            int b = t.nextInt();
            arraya[i] = b;
        }
        int arrayb[] = new int[a];
        System.out.println("Introduzca los elementos de y en b:");
        for (int i = 0; i < a; i++) {
            Scanner t = new Scanner(System.in);
            int b = t.nextInt();
            arrayb[i] = b;
        }
        arrayc(a, arrayx, arraya, arrayb);
    }

    public void arrayc(int a, int arrayx[], int arraya[], int arrayb[]) {

        int arrayc[] = new int[a];
        for (int i = 0; i < a; i++) {
            arrayc[i] = arraya[i] + arrayb[i];
            System.out.println("(" + arrayx[i] + "," + arrayc[i] + "),");
        }
        Lagrange(a, arrayx, arrayc);
    }

    public void Lagrange(int a, int arrayx[], int arrayc[]) {

        for (int i = 0; i < a; i++) {
            int m = 1;
            for (int j = 0; j < a; i++) {
                
                if (i != j){
                    int r = arrayx[i] - arrayx[j];
                    m=r*m;
                }//coeficientes(a, arrayx[j]);
            }
            //arrayc[i] = arrayc[i]/m;
        }
    }

    public void coeficientes(int a, int b) {

        int arrayco[] = new int[2];
        arrayco[0] = 1;
        arrayco[1] = -b;
        int arrayf[] = new int[a];
        for (int i = 0; i < a; i++) {
            arrayf[i] = 1;
        }
        int arrayf1[] = new int[a + 1];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < a; j++) {
                arrayf1[i + j] = (arrayco[i] * arrayf[j]) + arrayf1[i + j];
                //System.out.println(i + j);
            }
        }
        for (int i = 0; i < a; i++) {
            System.out.println(arrayf1[i]);
        }

    }

    public static void main(String[] args) {

        Main m = new Main();
        m.puntos();
    }

}
