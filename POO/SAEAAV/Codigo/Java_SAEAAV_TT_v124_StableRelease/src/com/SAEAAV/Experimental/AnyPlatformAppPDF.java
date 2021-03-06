///Paquete de Trabajo
package com.SAEAAV.Experimental;

///Librerias
import java.awt.Desktop;
import java.io.File;

/*
//Cross platform solution to view a PDF file
Referencia:
http://www.mkyong.com/java/how-to-open-a-pdf-file-in-java/
*/
public class AnyPlatformAppPDF
{
    ///Atributos

    ///Constructores

    ///Métodos de propósito General
    public void principalOriginal()
    {
         try {
 
//		File pdfFile = new File("c:\\Java-Interview.pdf");
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
    public static void main(String[] args) 
    {
       AnyPlatformAppPDF obj=new AnyPlatformAppPDF();
       obj.principalOriginal();
    }
}

