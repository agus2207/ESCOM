import java.io.*;
import java.util.*;
import java.util.Scanner;

public class Practica02 {

	public void Primos (int n) {

		int i,j;
		boolean[] esPrimo = new boolean[n+1];

		for (i = 1; i< n ;i++){

  			esPrimo[i] = true;
  		}

  		for(i = 2 ; i <= n ; i++){

  			for(j = 2; j<=(n/i); j++){

  				esPrimo[i*j] = false;
  			}
  		}

  		try{

  			escribir(esPrimo, n);
  		} catch (IOException e){

  			e.getMessage();
  		}
	}

	public void escribir (boolean p[], int r) throws IOException {

		int i, j;
		String b = "";
		j = 0;

		for(i=2; i<=r; i++){

  			if (p[i]){

  				j++;

  			}  			
  		}

  		String nombreArchivo = "Primos.txt";

  		try {

  			if (existe(nombreArchivo)) {

  				FileWriter w = new FileWriter(nombreArchivo, true);
  				BufferedWriter bw = new BufferedWriter(w);
  				PrintWriter wr = new PrintWriter(bw);
  				wr.write("Hasta el numero "+r+" hay "+j+" primos");
  				wr.write("\r\n");

  				if (r < 2) {

  					wr.write("Ninguno");
  				}

  				if (r == 2) {

  					wr.write("{2}");
  					wr.write("{10}");
  				}

  				if (r > 2) {

  					wr.write("{");
  					for(i=2; i<=r; i++){

  						if  (p[i]){

  							wr.write(i+","); 				
  						}
  					}
  					wr.write("}");
  					wr.write("\r\n");
  					wr.write("{");
  					for(j=2; j<=r; j++){

  						if (p[j]){
  							b=binario(j);
  							wr.write(b +",");
  						}
  					}
  					wr.write("}");
  				}
  				wr.close();
  			}
  		}catch (IOException e){

  			e.getMessage();
  		}
	}

	public boolean existe (String nombreArchivo){

		File f;
  		f= new File(nombreArchivo);
  		boolean l = true;

  		if (f.exists()) {

            if (f.isFile()) {

                System.out.println("El archivo ya existe.");
                f.delete();
                l = true;
            }

        } else {

            f.delete();
            System.out.println("Archivo creado.");
            l = true;
        }
        return l;
	}

	public static String binario(int numero) {
        int exp, binario, digito;
        String stringB;

        exp = 0;
        binario = 0;
        while (numero != 0) {
            digito = numero % 2;
            binario = (int) (binario + digito * Math.pow(10, exp));
            exp++;
            numero = numero / 2;
        }

        stringB = binario + "";
        char[] arrayB = stringB.toCharArray();

        return stringB;
    }

    public static int random() {
        int random;
        int limite=100000;
        random =  (int) (Math.random() * limite) + 1 ;
        return random;
    }
    
    public static void Menu() {
        System.out.println("Bienvenido ");
        System.out.println("Por favor seleccione una opcion");
        System.out.println("1.- Manual ");
        System.out.println("2.- Automatico ");
        System.out.println("3.- Salir ");
        System.out.print("Opcion: ");
    }

   public static void digital() {

        System.out.printf("El valor de K = ");
    }

    public static void main (String[] args) throws IOException {

		int k, a, opcion;
		Practica02 p = new Practica02();
		Scanner s= new Scanner (System.in);
		Scanner t= new Scanner (System.in);
		
		do{

			p.Menu();
			a = s.nextInt();

			if(a>=3||a<1){

				System.exit(0);
			}

			 switch(a){

				case 1:
				System.out.print("Ingresar numero");
				p.digital();
				k = t.nextInt();
				p.Primos(k);
				break;

				case 2:
				System.out.println("Automatico");
				p.digital();
				k = p.random();
				System.out.println(""+k+"  ");
				p.Primos(k);
				break;
			}

			System.out.println("\n\n\n\n"); 

		} while(a!=3);
	}
}