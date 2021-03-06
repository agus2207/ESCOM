///Paquete de Trabajo
package JOGL.windows_i586;

///Librerias
import Utilerias.Utileria_Conversiones;
import Utilerias.Utileria_SistemaArchivos;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

public class CargadorLibrerias_windows_i586
{
    ///Atributos
    private Utileria_Conversiones objUtileria_Conversiones=new Utileria_Conversiones();
    private Utileria_SistemaArchivos objUtileria_SistemaArchivos=new Utileria_SistemaArchivos();
    private final String rutaLibreriaDentroJAR="/JOGL/windows_i586/";
    private final String extensionLibrerias=".dll";
    private String rutaJAR;
    private String rutaLibreriasExternas;
    private ArrayList<String> nombreLibrerias=new ArrayList<>();
    private ArrayList<String> identificadorLibrerias=new ArrayList<>();
    
    ///Constructores
    public CargadorLibrerias_windows_i586()
    {
        //Cargamos nombres de librerias
        this.nombreLibrerias.add("gluegen-rt");
        this.nombreLibrerias.add("joal");
        this.nombreLibrerias.add("soft_oal");
        this.nombreLibrerias.add("newt");
        this.nombreLibrerias.add("jogl_cg");
        this.nombreLibrerias.add("jogl_desktop");
        this.nombreLibrerias.add("jogl_mobile");
        this.nombreLibrerias.add("nativewindow_win32");
        this.nombreLibrerias.add("jocl");
//        this.nombreLibrerias.add("nativewindow_awt");
        
        //Cargamos sus identificadores
        String identificador="";
        for(int k=0; k<this.nombreLibrerias.size(); k++)
        {
            identificador=this.nombreLibrerias.get(k).toUpperCase();
            this.identificadorLibrerias.add(identificador);
            System.out.println("*identificador="+identificador);
        }
    }
    
    ///Métodos de propósito general
    public void Principal()
    {
        try
        {
            //Variables
            String nombreLibreria="";
            
            //Realizamos ajustes
            this.rutaLibreriasExternas=this.rutaJAR+"/lib";
            this.objUtileria_SistemaArchivos.CrearDirectorio(this.rutaLibreriasExternas);
            
            //Cargamos los archivos de las librerias
            for(int k=0; k<this.nombreLibrerias.size(); k++)
            {
                nombreLibreria=this.nombreLibrerias.get(k)+this.extensionLibrerias;
                this.objUtileria_SistemaArchivos.CopiarArchivo_EspecializadoJAR(this.rutaLibreriaDentroJAR+nombreLibreria, this.rutaLibreriasExternas+"/"+nombreLibreria);
            }
            
            //Cargamos la ruta de la libreria
            this.addLibraryPath(this.rutaLibreriasExternas);
            
            //Cargamos las librerias a memoria
            for(int k=0; k<this.identificadorLibrerias.size(); k++)
            {
                System.out.println("Cargando...System.loadLibrary="+this.identificadorLibrerias.get(k));
                System.loadLibrary(this.identificadorLibrerias.get(k));
                System.out.println("...OK");
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    ///Métodos de propósito específico
    private void addLibraryPath(String s) throws Exception
    {
        final Field usr_paths_field = ClassLoader.class.getDeclaredField("usr_paths");
        usr_paths_field.setAccessible(true);
        
        final String[] paths = (String[]) usr_paths_field.get(null);
        
        for(String path : paths)
        {
            if(path.equals(s))
            {
                return;
            }
        }
        
        final String[] new_paths = Arrays.copyOf(paths, paths.length+1);
        new_paths[paths.length-1]=s;
        usr_paths_field.set(null, new_paths);
    }
    
    ///Métodos para el manejo de atributos
    public void setRutaJAR(String rutaJAR) 
    {
        this.rutaJAR = rutaJAR;
    }
    
}
