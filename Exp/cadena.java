import java.util.Scanner;
import java.io.*;

class cadena{

	public static void main (String[] args) {

		String texto = "";
		String g;
		Scanner s= new Scanner (System.in);
		texto = s.nextLine();
		texto=texto.concat("-Esto es una concatenacion");
		System.out.println(texto);
		texto=texto.replaceAll(" ", "");
		System.out.println(texto);
		g=texto.replaceAll("[^\\x20-\\x7e]", "");
		System.out.println(g);
		int a=g.length();
		System.out.println("las letras son "+a);
	}
}