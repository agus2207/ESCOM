///Paquete de Trabajo
package JOGL;

///Librerias
import JOGL.windows_amd64.CargadorLibrerias_windows_amd64;
import JOGL.windows_i586.CargadorLibrerias_windows_i586;
import Utilerias.Utileria_SistemaArchivos;
import javax.swing.JOptionPane;

public class CargadorLibreriasGeneral
{
    ///Atributos
    private String rutaJAR;
    private Utileria_SistemaArchivos objUtileria_SistemaArchivos=new Utileria_SistemaArchivos();
    
    ///Constructores
    public CargadorLibreriasGeneral()
    {    
    }
    
    ///Métodos de propósito general
    public void Principal()
    {
        //Procedemos a inicializar el módulo
        this.Inicializar();
        
        //Procedemos a cargar las librerias necesarias
        this.CargarLibrerias();
    }

    ///Métodos de propósito específico
    private void Inicializar()
    {
        try
        {
            this.rutaJAR=objUtileria_SistemaArchivos.Obtener_RutaJAR_SinArchivoReferencia();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERROR:\n"+ex.getMessage());
        }
    }
    private void CargarLibrerias()
    {
        try
        {
            //Identificamos la plataforma correspondiente
            if(System.getProperty("os.name").equals("MAC OS X"))
            {//MAC OS 
                System.out.println("SO: MAC");
            }
            else if(System.getProperty("os.name").equals("Linux"))
            {//Linux x86_64
                System.out.println("SO: LINUX");
            }
            else 
            {
                if(System.getProperty("os.arch").equals("amd64") || System.getProperty("os.arch").equals("x86_64"))
                {//Windows AMD-Intel x64
                    CargadorLibrerias_windows_amd64 objCargadorLibrerias_windows_amd64;
                    objCargadorLibrerias_windows_amd64=new CargadorLibrerias_windows_amd64();
                    objCargadorLibrerias_windows_amd64.setRutaJAR(rutaJAR);
                    objCargadorLibrerias_windows_amd64.Principal();
                }
                else//Windows x86
                {
                    CargadorLibrerias_windows_i586 objCargadorLibrerias_windows_i586;
                    objCargadorLibrerias_windows_i586=new CargadorLibrerias_windows_i586();
                    objCargadorLibrerias_windows_i586.setRutaJAR(rutaJAR);
                    objCargadorLibrerias_windows_i586.Principal();
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERROR:\n"+ex.getMessage());
        }
    }
    
    ///Métodos para el manejo de atributos

}
