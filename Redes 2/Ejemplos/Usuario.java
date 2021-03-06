import java.io.Serializable;
public class Usuario implements Serializable{
    String nombre;
    String aparterno;
    String amaterno;
    transient String pwd;
    int edad;
    public Usuario(String nombre, String aparterno, String amaterno, String pwd, int edad){
        this.nombre = nombre;
        this.apaterno = apaterno;
        
    }
}
