import java.net.*;
import java.io.*;
import java.awt.*;

public class server {

	public static void main(String args[]) {
		Socket s;
		DataOutputStream oos;
		DataInputStream ois;
		ServerSocket ss;

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

		Respuestas[] = {"AGUSTIN",
		"ESCOM", "TERCERO", "EL CINE", "AVENGERS", 
		"POP ROCK", "ONCE UPON A TIME", "RUMPELSTILTSKIN", "TAEKWONDO",
		"AZUL"};

		try{
			ss = new ServerSocket(5000);
			System.out.println("Waiting for a connection");

			while(true){
				s = ss.accept();
				System.out.println("Client connected");
				oos = new DataOutputStream(s.getOutputStream());
				ois = new DataInputStream(s.getInputStream());
				String request = ois.readUTF();

				for (int i=0; i<Preguntas.length; i++) {
					if (request.equals(Preguntas[i]))
						oos.writeUTF(Respuestas[i]);
				}

				System.out.println("Closing connection");
				s.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}