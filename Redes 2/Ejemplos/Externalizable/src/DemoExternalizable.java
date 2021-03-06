import java.io.*;
import java.util.*;

public class DemoExternalizable {
    public static void main (String[] args) throws IOException, ClassNotFoundException{
        System.out.println("Creando el objeto");
        String[] usuarios = {"A", "B", "C"};
        String[] passwords = {"1", "2", "3"};
        ListaUsuarios ip = new ListaUsuarios(usuarios, passwords);
        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("objetos.out"));
        o.writeObject(ip);
        o.close();
        System.out.println("\nRecuperando Objeto");
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("objetos.out"));
        ip = (ListaUsuarios) in.readObject();
        ip.muestraUsuarios();

    }
}
