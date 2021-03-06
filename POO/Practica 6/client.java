import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class client {

	String Preguntas[] = {"COMO TE LLAMAS",
	"DONDE ESTUDIAS",
	"EN QUE SEMESTRE ESTAS",
	"CUAL ES TU LUGAR FAVORITO",
	"CUAL ES TU PELICULA FAVORITA",
	"QUE MUSICA TE GUSTA",
	"CUAL ES TU SERIE FAVORITA",
	"PERSONAJE FICTICIO FAVORITO",
	"PRACTICAS ALGUN DEPORTE",
	"QUE CINTA ERES"},
	a;

	Socket s;
	DataOutputStream oos;
	DataInputStream ois;

	public client(){
		
		Scanner scanner = new Scanner(System.in);
		a = scanner.nextLine();
	}

	public void connectClient(){
		try{
			s = new Socket("localhost", 5000);
			oos = new DataOutputStream(s.getOutputStream());
			oos.writeUTF(a);
			ois = new DataInputStream(s.getInputStream());
			
			System.out.println("SERVIDOR: " + ois.readUTF());

			s.close();
		} catch(IOException e) {
			System.out.println("SERVIDOR: Disculpe");
		}
	}

	public static void main(String[] args){
		do{
			new client().connectClient();
		} while(true);
	}
}