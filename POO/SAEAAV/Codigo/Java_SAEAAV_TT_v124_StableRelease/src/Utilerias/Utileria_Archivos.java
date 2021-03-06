///Paquete de Trabajo
package Utilerias;

///Librerias
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Utileria_Archivos 
{
    ///Atributos
    private String ruta;
    private String nombre;
    private String contenido;

    ///Constructores
    public Utileria_Archivos(String ruta, String nombre)
    {
        this.ruta = ruta;
        this.nombre = nombre;
    }
    
    ///Métodos de propósito General
    public void escribir(ArrayList<String> contenido)
    {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
         {
          fichero = new FileWriter(ruta+""+nombre);
          pw = new PrintWriter(fichero);

          for(int k=0; k<contenido.size(); k++)
          {
              pw.println(contenido.get(k));
          }
         }
        catch (Exception e)
         {
          e.printStackTrace();
         } 
         finally 
          {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           try
            {
             if (null != fichero)
             fichero.close();         
            }
           catch (Exception e2)
            {
             e2.printStackTrace();
            }
         }
    }
    public void escribir(String contenido)
    {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
         {
          fichero = new FileWriter(ruta+""+nombre);
          pw = new PrintWriter(fichero);

          pw.print(contenido);      
         }
        catch (Exception e)
         {
          e.printStackTrace();
         } 
         finally 
          {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           try
            {
             if (null != fichero)
             fichero.close();         
            }
           catch (Exception e2)
            {
             e2.printStackTrace();
            }
         }
    }
    public void leer()
    {
    File archivo = null;
    FileReader fr = null;
    BufferedReader br = null;
    this.contenido="";
    
    try
     {
      // Apertura del fichero y creacion de BufferedReader para poder
      // hacer una lectura comoda (disponer del metodo readLine()).
      archivo = new File (ruta+""+nombre);
      fr = new FileReader (archivo);
      br = new BufferedReader(fr);

      // Lectura del fichero
      String linea;
      int coin=1;
      boolean control=false;
      while((linea=br.readLine())!=null)
      {
        contenido+=linea;
      }
     }
    
      catch(Exception e)
       {
        e.printStackTrace();
       }
      finally
       {
        // En el finally cerramos el fichero, para asegurarnos
        // que se cierra tanto si todo va bien como si salta 
        // una excepcion.
        try
         {
          if( null != fr )
           {
            fr.close();     
           }                  
         }
        catch (Exception e2)
         {
          e2.printStackTrace();
         }
       }
   }
    
    ///Métodos de propósito Específico
    
    ///Métodos para el manejo de Atributos

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContenido() {
        return contenido;
    }
    
}
