///Paquete de Trabajo
package Utilerias;

///Librerias
import java.io.File;
import java.net.URL;
import javax.swing.JOptionPane;


public class Utileria_CargadorArchivos
{
    ///Atributos
    private Utileria_Conversiones objUtileria_Conversiones=new Utileria_Conversiones();
    
    ///Constructores

    ///Métodos de propósito general
    public void Principal()
    {
        try
        {
            URL url = getClass().getResource("pato");//pato*../otherresources*
//            URL url = Utileria_CargadorArchivos.class.getClassLoader().getResource("../otherresources");//pato*../otherresources*
            
            System.out.println("*url="+url);
            JOptionPane.showMessageDialog(null, "url="+url);
            File directory = new File(url.getPath());
            File[] files = directory.listFiles();

            for(int k=0; k<files.length; k++)
            {
                System.out.println("->Archivo #"+k+"::Contiene:");
                System.out.println("getAbsolutePath="+files[k].getAbsolutePath());
                System.out.println("getCanonicalPath="+files[k].getCanonicalPath());
                System.out.println("getPath="+files[k].getPath());
                System.out.println("getName="+files[k].getName());
                System.out.println("getParent="+files[k].getParent());
            }
            
            System.exit(0);
            System.out.println("*************************************************");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERROR");
        }
    }
    public void Principal2()
    {
        try
        {
            URL url=getClass().getResource("Pato/OpenAL32.dll");//*Pato/OpenAL32.dll*OpenAL32.dll*
            String camino=""+url;
            camino=objUtileria_Conversiones.QuitarCaracteresEspecificadosDeLaCadena(camino, "jar:file:/");
            camino=objUtileria_Conversiones.QuitarCaracteresEspecificadosDeLaCadena(camino, "!/Utilerias/Pato/OpenAL32.dll");
            camino=objUtileria_Conversiones.QuitarCaracteresEspecificadosDeLaCadena(camino, "Java_SAEAAV_TT_Test_JOGL_v05.jar");
            camino+="/lib/natives/";
            
            System.out.println("*url="+camino);
            JOptionPane.showMessageDialog(null, "url="+camino);
            
            File directory = new File(url.getPath());
            File[] files = directory.listFiles();

            for(int k=0; k<files.length; k++)
            {
                System.out.println("->Archivo #"+k+"::Contiene:");
                System.out.println("getAbsolutePath="+files[k].getAbsolutePath());
                System.out.println("getCanonicalPath="+files[k].getCanonicalPath());
                System.out.println("getPath="+files[k].getPath());
                System.out.println("getName="+files[k].getName());
                System.out.println("getParent="+files[k].getParent());
            }
            
            System.exit(0);
            System.out.println("*************************************************");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERROR+\n"+ex.getMessage());
        }
    }
    public String Principal3()
    {
        String caminoOK="";
        try
        {
            URL url=getClass().getResource("Pato/OpenAL32.dll");//*Pato/OpenAL32.dll*OpenAL32.dll*
            String camino=""+url;
            camino=objUtileria_Conversiones.QuitarCaracteresEspecificadosDeLaCadena(camino, "jar:file:/");
            camino=objUtileria_Conversiones.QuitarCaracteresEspecificadosDeLaCadena(camino, "!/Utilerias/Pato/OpenAL32.dll");
            camino=objUtileria_Conversiones.QuitarCaracteresEspecificadosDeLaCadena(camino, "Java_SAEAAV_TT_Test_JOGL_v05.jar");
            camino+="/lib/";
            
            caminoOK=camino;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERROR+\n"+ex.getMessage());
        }
        
        return caminoOK;
    }
    ///Métodos de propósito específico
  
    ///Métodos para el manejo de atributos

}
