package webay;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class Webay {

    int cont, pal;
    char[] car;

    public static void main(String[] args) throws Exception {

        Aut p = new Aut();
        Textos t = new Textos();
        Grafico f = new Grafico();
        int a, opcion;
        String cadena, archivo, b;
        Scanner s = new Scanner(System.in);
        Scanner l = new Scanner(System.in);
        Scanner u = new Scanner(System.in);

        do {

            p.Menu();
            a = s.nextInt();

            if (a >= 3 || a < 1) {

                System.exit(0);
            }

            switch (a) {

                case 1:

                    System.out.print("Ingresar cadena:");
                    cadena = l.nextLine();
                    if (cadena.isEmpty()) {

                        System.out.println("Esta Vacia");
                    } else {

                        p.aut(cadena);
                    }
                    break;

                case 2:

                    System.out.println("Ingrese nombre del archivo:");
                    archivo = u.nextLine();
                    b = t.leer(archivo);
                    p.aut(b);
                    break;
            }

            System.out.println("\n\n");

        } while (a != 3);

    }

}
