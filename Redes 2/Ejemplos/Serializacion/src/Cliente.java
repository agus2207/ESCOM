import java.net.*;
import java.io.*;

public class Cliente {
    public static void main (String args[]){
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        String host = "127.0.0.1";
        int port = 9999;
        try{
            Socket cl = new Socket(host, port);
            System.out.println("Conexion establecida...");
            oos = new ObjectOutputStream(cl.getOutputStream());
            ois = new ObjectInputStream(cl.getInputStream());
            Usuario u = new Usuario("Pepito", "Perez", "Juarez", "12345", 20);
            System.out.println("Enviando Objeto");
            oos.writeObject(u);
            oos.flush();
            System.out.println("Preparado para recibir respuesta");
            Usuario u2 = (Usuario)ois.readObject();
            System.out.println("Objeto recivido... Extrayendo datos");
            System.out.println("Nombre "+u2.getNombre()+"\nA.paterno "+u2.getApaterno()+"\nA.materno "+u2.getAmaterno()+"\nPassword "+u2.getPwd()+"\nEdad "+u2.getEdad());
        }catch(Exception e){
            System.err.println(e);
        }
    }
}
