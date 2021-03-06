///Paquete de Trabajo
package com.PersistenciaDeDatos;

///Librerias
import Utilerias.Utileria_SistemaArchivos;
import java.awt.Desktop;
import java.io.File;
import java.net.URL;
import javax.swing.JOptionPane;

/*
//Cross platform solution to view a PDF file
Referencia:
http://www.mkyong.com/java/how-to-open-a-pdf-file-in-java/
*/
public class AbrirArchivosPropiosMultiplataforma
{
    ///Atributos
    private Utileria_SistemaArchivos objUtileria_SistemaArchivos=new Utileria_SistemaArchivos();
    private String rutaJAR;
    private final String rutaLibreriaDentroJAR="/com/SAEAAV/";
    private String rutaLibreriasExternas;
    
    ///Constructores
    public AbrirArchivosPropiosMultiplataforma()
    {
        
    }
    
    ///Métodos de propósito General
    public void abrir_ManualDeUsuario()
    {
        this.abreArchivoMultiplataforma("/com/SAEAAV/Vista/Multimedia/ManualDeUsuario.pdf");
    }
    private void abreArchivoMultiplataforma(String rutaArchivoDentroJAR)
    {
        try 
        {
            File archivoAAbrir=new File(getClass().getResource(rutaArchivoDentroJAR).getPath());
            if (archivoAAbrir.exists())
            {
                if (Desktop.isDesktopSupported()) 
                {
                    Desktop.getDesktop().open(archivoAAbrir);
                } 
                else 
                {
                    JOptionPane.showMessageDialog(null, "No es posible ver el archivo.", "ERROR AL INTENTAR ABRIR ARCHIVO.", JOptionPane.ERROR_MESSAGE);
                    System.out.println("Exception=\nDesktop.isDesktopSupported()="+Desktop.isDesktopSupported());
                }
            } 
            else 
            {
                JOptionPane.showMessageDialog(null, "No es posible ver el archivo.", "ERROR AL INTENTAR ABRIR ARCHIVO.", JOptionPane.ERROR_MESSAGE);
                System.out.println("Exception=\narchivoAAbrir.exists()="+archivoAAbrir.exists());
            }
        } 
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, "No es posible ver el archivo.", "ERROR AL INTENTAR ABRIR ARCHIVO.", JOptionPane.ERROR_MESSAGE);
            System.out.println("Exception=\n"+ex.getMessage());
        }
    }
    public void abreArchivoMultiplataformaOK()
    {
        this.rutaJAR=objUtileria_SistemaArchivos.Obtener_RutaJAR_SinArchivoReferencia();
        this.rutaLibreriasExternas=this.rutaJAR+"/lib";
        this.objUtileria_SistemaArchivos.CopiarArchivo_EspecializadoJAR(this.rutaLibreriaDentroJAR+"ManualDeUsuario.pdf", this.rutaLibreriasExternas+"/"+"ManualDeUsuario.pdf");
        try 
        {
            File archivoAAbrir=new File(this.rutaLibreriasExternas+"/"+"ManualDeUsuario.pdf");
            if (archivoAAbrir.exists())
            {
                if (Desktop.isDesktopSupported()) 
                {
                    Desktop.getDesktop().open(archivoAAbrir);
                } 
                else 
                {
                    JOptionPane.showMessageDialog(null, "No es posible ver el archivo.", "ERROR AL INTENTAR ABRIR ARCHIVO.", JOptionPane.ERROR_MESSAGE);
                    System.out.println("Exception=\nDesktop.isDesktopSupported()="+Desktop.isDesktopSupported());
                }
            } 
            else 
            {
                JOptionPane.showMessageDialog(null, "No es posible ver el archivo.", "ERROR AL INTENTAR ABRIR ARCHIVO.", JOptionPane.ERROR_MESSAGE);
                System.out.println("Exception=\narchivoAAbrir.exists()="+archivoAAbrir.exists());
            }
        } 
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, "No es posible ver el archivo.", "ERROR AL INTENTAR ABRIR ARCHIVO.", JOptionPane.ERROR_MESSAGE);
            System.out.println("Exception=\n"+ex.getMessage());
        }
    }
    private void principalOriginal()
    {
         try {
 
//		File archivoAAbrir = new File("c:\\Java-Interview.pdf");
                File pdfFile=new File(getClass().getResource("/com/SAEAAV/Vista/Multimedia/ManualDeUsuario.pdf").getPath());
		if (pdfFile.exists()) {
 
			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().open(pdfFile);
			} else {
				System.out.println("Awt Desktop is not supported!");
			}
 
		} else {
			System.out.println("File is not exists!");
		}
 
		System.out.println("Done");
 
	  } catch (Exception ex) {
		ex.printStackTrace();
	  }
    }
    
    ///Métodos de propósito Específico

    ///Métodos para el manejo de Atributos

    ///Main
    
}

