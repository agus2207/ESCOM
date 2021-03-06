import java.io.*;
import java.util.*;

public class Usuario implements Externalizable {
    private String usuario;
    private String password;
    public Usuario(){
        System.out.println("Creando Usuario");
    }
    Usuario(String u, String p){
        System.out.println("Creando Usuario ("+u+","+p+")");
        usuario = u;
        password = p;
    }
    public void writeExternal (ObjectOutput out) throws IOException{
        //Explicitamente indicamos cuales atributos se van a enviar
        out.writeObject(usuario);
    }
    public void readExternal (ObjectInput in) throws IOException, ClassNotFoundException{
        System.out.println("Usuario readExternal");
        //Explicitamente indicamos cuales atributos se van a recuperar
        usuario = (String)in.readObject();
    }
    public void muestraUsuario(){
        String cad = "Usuario: "+usuario+" Password: ";
        if(password == null)
            cad = cad+"No Disponible";
        else
            cad = cad+password;
        System.out.println(cad);
    }
}
class ListaUsuarios implements Serializable{
    private LinkedList lista = new LinkedList();
    int valor;
    ListaUsuarios(String[] usuarios, String[] password){
        for(int i = 0; i < usuarios.length; i++)
            lista.add(new Usuario(usuarios[i], password[i]));
    }
    public void muestraUsuarios(){
        ListIterator li = lista.listIterator();
        Usuario u;
        while(li.hasNext()){
            u=(Usuario)li.next();
            u.muestraUsuario();
        }
    }
    
}
