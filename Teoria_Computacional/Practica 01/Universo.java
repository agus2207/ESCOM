import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

public class Universo implements Serializable{

	public void elementos (int k) throws UnsupportedEncodingException, FileNotFoundException, IOException{

		int temporal = k;
		int com;
		int totalC = 0;
		String Binario = "";

		for (int i=0; i<=k; i++){

			totalC = totalC + (int)(Math.pow(2,i));
		}

		System.out.println("El numero total de elmentos es " +totalC+ "\n");
		String[] particularBinaryElements;

		try {
    
            String FileWrite = "Universo.txt";
            File F = new File(FileWrite);

            if (F.exists()) {

                if (F.isFile()) {

                    System.out.println("Ya existe el archivo");
                    F.delete();
                    System.out.println("Archivo eliminado: " + (!F.delete()));
                }

            } else {

                F.delete();
                System.out.println("Archivo creado. ");
            }

            FileWriter fw = new FileWriter(FileWrite, true);
            PrintWriter pw = new PrintWriter(fw);          
            pw.flush();
            pw.write("{");

            if (k > 1) {

                for (int j = 0; j <= k ; k--) {

                    Binario = "";
                    System.out.println("K = " + k);  //Pow/Length
                    com = (int) (Math.pow(2, k));
                    particularBinaryElements = new String[com];
                    System.out.println("Cuando K = " + k + " | las combinaciones son  = " + com);

                    for (int i = 0; i < com; i++) {
                        Binario = toBinary(i);
                        while (Binario.length() < k) {
                            Binario = 0 + Binario;
                        }
                        pw.write(Binario + ",");
                        pw.write("\n");
                    }
                }

            } else if (k == 1) {

                pw.write("0,1,");
            }

            pw.write("E}");
            pw.close();     

        } catch (IOException e) {

            System.out.println("Error: Archivo sin guardar.");
            e.getMessage();
        }
    }

    public static String toBinary(int n) {
        
        int exp, b, d;
        String cadenaB;

        exp = 0;
        b = 0;

        while (n != 0) {

            d = n % 2;
            b = (int) (b + d * Math.pow(10, exp));
            exp++;
            n = n / 2;
        }

        cadenaB = b + "";
        char[] arrayB = cadenaB.toCharArray();

        return cadenaB;
    }

	public static void Menu() {
        System.out.println("Bienvenido ");
        System.out.println("Por favor seleccione una opcion");
        System.out.println("1.- Manual ");
        System.out.println("2.- Automatic ");
        System.out.println("3.- Exit ");
        System.out.print("Opcion: ");
    }

    public static void digital() {

        System.out.printf("El valor de K = ");
    }

    public static int random() {
        int random;
        random = (int) (Math.random() * 20) + 1;
        return random;
    }

}