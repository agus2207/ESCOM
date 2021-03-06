import java.io.File;
import java.io.Serializable;

public class Cliente_info implements Serializable{
    
    private String nombre;
    private int dificultad, edad;
    
    public Cliente_info(){
        
    }
    
    public Cliente_info(String nombre, int edad, int dificultad){
        this.nombre = nombre;
        this.dificultad = dificultad;
        this.edad = edad;
    }

    public int getDificultad() {
        return dificultad;
    }

    public int getEdad() {
        return edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
