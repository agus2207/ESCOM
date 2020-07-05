import java.io.*;
import java.util.*;
import java.util.Scanner;

public class Practica01 extends Universo {

	public static void main (String[] args) throws IOException {

		int k, a, opcion;
		Universo f = new Universo();
		Scanner s= new Scanner (System.in);
		Scanner t= new Scanner (System.in);
		
		do{

			f.Menu();
			a = s.nextInt();

			if(a>=3||a<1){

				System.exit(0);
			}

			 switch(a){

				case 1:
				System.out.print("Ingresar numero");
				f.digital();
				k = t.nextInt();
				f.elementos(k);
				break;

				case 2:
				System.out.println("Automatico");
				f.digital();
				k = f.random();
				System.out.println(""+k+"  ");
				f.elementos(k);
				break;
			}

			System.out.println("\n\n\n\n"); 

		} while(a!=3);
	}

	public static void imprimir(String[] array){

		System.out.printf("{");
		for (int i=0; i<array.length; i++){

			System.out.printf(array[i] +",");
		}

		System.out.printf("}");
	}

	public static int Combinaciones(int k){

		int totalC = 0;
		System.out.println(Math.pow(2,k));
		for(int i=0; i<=k; i++){

			totalC = totalC + (int)(Math.pow(2,i));
		}

		System.out.println(totalC);
		return totalC;
	}
}