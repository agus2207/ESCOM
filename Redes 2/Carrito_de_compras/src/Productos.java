import java.io.File;
import java.io.Serializable;

public class Productos implements Serializable{
    private File imagen;
    private String nombre, ruta, descripcion, genero, precio, cantidad;
    private int compra;
    
    public Productos(){
        
    }
    
    public Productos(String nombre, String genero, String descripcion, String precio, String cantidad, String ruta){
        super();
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.genero = genero;
        this.precio = precio;
        this.cantidad = cantidad;
        this.ruta = ruta;
    }
    
    public void setCompra(int compra) {
        this.compra = compra;
    }

    public int getCompra() {
        return compra;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public File getImagen() {
        return imagen;
    }

    public void setImagen(File imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    
    public String [] getDatos() {
        String [] datos  = {this.getNombre(),this.getDescripcion(),this.getPrecio(),this.getGenero(),"0"};
        return datos;
    }
   
}
