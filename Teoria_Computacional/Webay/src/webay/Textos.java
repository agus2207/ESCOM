package webay;

import java.io.*;
import java.util.*;

public class Textos {

    //String temp1;
    String leer(String nombreArchivo) {

        String temp1 = "";

        try {

            FileReader r = new FileReader(nombreArchivo);
            int c = r.read();

            while (c != -1) {

                temp1 = temp1 + (char) c;

                c = r.read();
            }
            c = r.read();

            temp1 = temp1 + c;
            r.close();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
        return temp1;
    }
}
