///Paquete de Trabajo
package Utilerias;

///Librerias
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.channels.FileChannel;

/**
 * 
 * @author Checo
 * Apoyo:
 * http://www.mkyong.com/java/how-to-create-directory-in-java/
 * http://www.mkyong.com/java/how-to-delete-directory-in-java/
 * http://www.mkyong.com/java/how-to-copy-file-in-java/
 * http://examples.javacodegeeks.com/core-java/io/file/4-ways-to-copy-file-in-java/
 */
public class Utileria_SistemaArchivos
{
    ///Atributos
    private Utileria_Conversiones objUtileria_Conversiones=new Utileria_Conversiones();
    private String ruta="C:\\";
    private String nombre="Directory1";
    
    ///Constructores
    public Utileria_SistemaArchivos()
    {
    }
    
    ///Métodos de propósito general
    public boolean CrearDirectorio()
    {
        //Variables
        boolean esOperacionCorrecta=false;
        
        //Nos aseguramos de que todo salga bien
        try
        {
            File file = new File(this.ruta+this.nombre);

            if (!file.exists()) 
            {
                esOperacionCorrecta = file.mkdir();
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
        //Retornamos resultado
        return esOperacionCorrecta;
    }
    public boolean CrearDirectorio(String ruta, String nombre)
    {
        //Variables
        boolean esOperacionCorrecta=false;
        
        //Nos aseguramos de que todo salga bien
        try
        {  
            File file = new File(ruta+nombre);

            if (!file.exists()) 
            {
                esOperacionCorrecta = file.mkdir();
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
        //Retornamos resultado
        return esOperacionCorrecta;
    }
    public boolean CrearDirectorio(String rutaCompleta)
    {
        //Variables
        boolean esOperacionCorrecta=false;
        
        //Nos aseguramos de que todo salga bien
        try
        {  
            File file = new File(rutaCompleta);

            if (!file.exists()) 
            {
                esOperacionCorrecta = file.mkdir();
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
        //Retornamos resultado
        return esOperacionCorrecta;
    }
    public boolean EliminarDirectorio()
    {
        //Variables
        boolean esOperacionCorrecta=false;
        
        //Nos aseguramos de que todo salga bien
        try
        {
            File directory= new File(this.ruta+this.nombre);
     
            //make sure directory exists
            if(directory.exists())
            {
               esOperacionCorrecta=esclavo_EliminarDirectorio(directory);
            }
         }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
        //Retornamos resultado
        return esOperacionCorrecta;
    }
    public boolean EliminarDirectorio(String ruta, String nombre)
    {
        //Variables
        boolean esOperacionCorrecta=false;
        
        //Nos aseguramos de que todo salga bien
        try
        {
            File directory= new File(ruta+nombre);
     
            //make sure directory exists
            if(directory.exists())
            {
               esOperacionCorrecta=esclavo_EliminarDirectorio(directory);
            }
         }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
        //Retornamos resultado
        return esOperacionCorrecta;
    }
    public boolean EliminarDirectorio(String rutaCompleta)
    {
        //Variables
        boolean esOperacionCorrecta=false;
        
        //Nos aseguramos de que todo salga bien
        try
        {
            File directory= new File(rutaCompleta);
     
            //make sure directory exists
            if(directory.exists())
            {
               esOperacionCorrecta=esclavo_EliminarDirectorio(directory);
            }
         }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
        //Retornamos resultado
        return esOperacionCorrecta;
    }
    @Deprecated
    public boolean CopiarArchivo7(String rutaOrigen, String rutaDestino)
    {
        //Variables
        boolean esOperacionCorrecta=false;
        
        //Nos aseguramos de que todo salga bien
        try
        {
            //File source =new File(rutaOrigen);
            //File dest =new File(rutaDestino);
            //Files.copy(source.toPath(), dest.toPath());
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
        //Retornamos resultado
        return esOperacionCorrecta;
    }
    public boolean CopiarArchivo(String rutaOrigen, String rutaDestino)
    {
        //Variables
        boolean esOperacionCorrecta=false;
        
        //Nos aseguramos de que todo salga bien
        try
        {
            File source =new File(rutaOrigen);
            File dest =new File(rutaDestino);
            
            esOperacionCorrecta=this.esclavo_copyFileUsingFileChannels(source, dest);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
        //Retornamos resultado
        return esOperacionCorrecta;
    }
    public boolean CopiarArchivo_EspecializadoJAR(String rutaOrigenDentroJAR, String rutaDestino)
    {
        //Variables
        boolean esOperacionCorrecta=false;
        
        //Nos aseguramos de que todo salga bien
        try 
        {
            InputStream is = getClass().getResourceAsStream(rutaOrigenDentroJAR);//"/JOGL/README.txt"
             
            OutputStream os = new FileOutputStream(rutaDestino);//"H:/jocl.dll"
             
            byte[] buffer = new byte[1024];
            int bytesRead;
            //read from is to buffer
            while((bytesRead = is.read(buffer)) !=-1){
                os.write(buffer, 0, bytesRead);
            }
            is.close();
            
            //flush OutputStream to write any buffered data to file
            os.flush();
            os.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        
        //Retornamos resultado
        return esOperacionCorrecta;
    }
    @Deprecated
    public String Obtener_RutaJAR(String nombreJAR)
    {
        String caminoOK="";
        try
        {
            URL url=getClass().getResource("REFERENCIA.txt");
            
            String camino=""+url;
            camino=objUtileria_Conversiones.QuitarCaracteresEspecificadosDeLaCadena(camino, "jar:file:/");
            camino=objUtileria_Conversiones.QuitarCaracteresEspecificadosDeLaCadena(camino, "file:/");
            camino=objUtileria_Conversiones.QuitarCaracteresEspecificadosDeLaCadena(camino, "!/Utilerias/REFERENCIA.txt");
            camino=objUtileria_Conversiones.QuitarCaracteresEspecificadosDeLaCadena(camino, "/REFERENCIA.txt");
            camino=objUtileria_Conversiones.QuitarCaracteresEspecificadosDeLaCadena(camino, nombreJAR+".jar");
            
            
            caminoOK=camino;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
        return caminoOK;
    }
    @Deprecated
    public String Obtener_RutaJAR()
    {
        String caminoOK="";
        String nombreJAR=this.Obtener_NombreJAR();
        
        try
        {
            URL url=getClass().getResource("REFERENCIA.txt");
            
            String camino=""+url;
            camino=objUtileria_Conversiones.QuitarCaracteresEspecificadosDeLaCadena(camino, "jar:file:/");
            camino=objUtileria_Conversiones.QuitarCaracteresEspecificadosDeLaCadena(camino, "file:/");
            camino=objUtileria_Conversiones.QuitarCaracteresEspecificadosDeLaCadena(camino, "!/Utilerias/REFERENCIA.txt");
            camino=objUtileria_Conversiones.QuitarCaracteresEspecificadosDeLaCadena(camino, "/REFERENCIA.txt");
            camino=objUtileria_Conversiones.QuitarCaracteresEspecificadosDeLaCadena(camino, nombreJAR);
            
            
            caminoOK=camino;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
        return caminoOK;
    }
    public String Obtener_RutaJAR_SinArchivoReferencia()
    {
        String caminoOK="";
        String nombreJAR=this.Obtener_NombreJAR();
        
        try
        {
            String path=new java.io.File(this.getClass()
            .getProtectionDomain()
            .getCodeSource()
            .getLocation()
            .getPath()).getPath();
            
            caminoOK=objUtileria_Conversiones.QuitarCaracteresEspecificadosDeLaCadena(path, nombreJAR);
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
        return caminoOK;
    }
    
    public String Obtener_NombreJAR()
    {                
         //Variables
        String name=null;
        
        try
        {
            name=new java.io.File(this.getClass()
            .getProtectionDomain()
            .getCodeSource()
            .getLocation()
            .getPath()).getName();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
        return name;
    }
    ///Métodos de propósito específico
    private boolean esclavo_EliminarDirectorio(File file)
    {
        //Variables
        boolean esOperacionCorrecta=false;
        
        //Nos aseguramos de que todo salga bien
        try
        {
            if(file.isDirectory())
            {
                //directory is empty, then delete it
                if(file.list().length==0)
                {
                   file.delete();
                   esOperacionCorrecta=true;
                }
                else
                {

                   //list all the directory contents
                   String files[] = file.list();

                   for (String temp : files) 
                   {
                      //construct the file structure
                      File fileDelete = new File(file, temp);

                      //recursive delete
                      esclavo_EliminarDirectorio(fileDelete);
                   }

                   //check the directory again, if empty then delete it
                   if(file.list().length==0)
                   {
                     file.delete();
                     esOperacionCorrecta=true;
                   }
                }

            }
            else
            {
                //if file, then delete it
                file.delete();
                esOperacionCorrecta=true;//Eliminamos un archivo
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
        //Retornamos resultado
        return esOperacionCorrecta;
    }
    private boolean esclavo_copyFileUsingFileChannels(File source, File dest)throws IOException 
    {
        //Variables
        boolean esOperacionCorrecta=false;
	FileChannel inputChannel = null;
	FileChannel outputChannel = null;
        
        //Nos aseguramos de que todo salga bien
	try 
        {
            inputChannel = new FileInputStream(source).getChannel();
            outputChannel = new FileOutputStream(dest).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
            esOperacionCorrecta=true;
	} 
        finally
        {
            inputChannel.close();
            outputChannel.close();
	}
        
        //Retornamos resultado
        return esOperacionCorrecta;
    }
    
    ///Métodos para el manejo de atributos

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

//    ///MAIN
//    public static void main(String[] args) {
//        Utileria_SistemaArchivos obj=new Utileria_SistemaArchivos();
////        obj.CopiarArchivo7("/home/checo_pchecho/Documents/JOGL_LINUX/"+"libgluegen-rt.so", "/home/checo_pchecho/Documents/JOGL_LINUX/"+"copia.so");
////        boolean CrearDirectorio = obj.CrearDirectorio("/home/checo_pchecho/NetBeansProjects/"+"lib");
////        boolean CrearDirectorio = obj.CrearDirectorio("/home/checo_pchecho/Documents/JOGL_LINUX/"+"lib");
////        boolean CrearDirectorio = obj.CrearDirectorio("//"+"CHOCO");
////        System.out.println("CrearDirectorio="+CrearDirectorio);
//        String Obtener_NombreJAR = obj.Obtener_RutaJAR_SinArchivoReferencia();
//        System.out.println("Obtener_RutaJAR_SinArchivoReferencia="+Obtener_NombreJAR);
////        Utileria_Archivos obj2=new Utileria_Archivos("/home/checo_pchecho/Documents/JOGL_LINUX/","libgluegen-rt.so");
////        obj2.leer();
////        System.out.println("->Contenido=\n"+obj2.getContenido());
//    }
}
