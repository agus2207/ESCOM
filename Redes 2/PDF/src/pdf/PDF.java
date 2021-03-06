/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdf;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.util.ArrayList;


/**
 *
 * @author erik_
 */
public class PDF {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Document doc = new Document(PageSize.A4);
        System.out.println("Documento creado...");
             ArrayList <String> imp = new ArrayList();
            imp.add("infinity war");
            imp.add("Acción");
            imp.add("2");
            imp.add("$400");
            imp.add("infinity war");
            imp.add("Acción");
            imp.add("2");
            imp.add("$400");
            imp.add("$8000");
        try {
            PdfWriter.getInstance(doc,new FileOutputStream("Tabla.pdf"));
            doc.open();
            System.out.println("Documento abierto...");
            PdfPTable table = new PdfPTable(11);
            Paragraph parrafo1 = new Paragraph("   ");
            Paragraph parrafo2 = new Paragraph("   ");
            
            Image imagen = Image.getInstance("logotipo.png");
            imagen.scalePercent(35);
            imagen .setAbsolutePosition(450f, 750f);
            doc.add(imagen);
            
            Image imagen2 = Image.getInstance("logo.png");
            imagen2.scalePercent(40);
            imagen2.setAbsolutePosition(20f, 750f);
            doc.add(imagen2);
            
            Image imagen3 = Image.getInstance("qr.png");
            imagen3.scalePercent(60);
            imagen3.setAbsolutePosition(240f, 20f);
            
            doc.add(parrafo1);
            doc.add(parrafo2);
            doc.add(parrafo1);
            doc.add(parrafo2);
            doc.add(parrafo1);
            doc.add(parrafo2);
            
            
            PdfPCell celdaInicial = new PdfPCell(new Paragraph("Clonopolis 'La capital del clon'"));
            celdaInicial.setPaddingLeft(120);
            celdaInicial.setColspan(11);
            
            PdfPCell Pelicula = new PdfPCell(new Paragraph("Pelicula"));
            Pelicula.setColspan(3);
            Pelicula.setPaddingLeft(40);
            
            PdfPCell Cantidad = new PdfPCell(new Paragraph("Cantidad"));
            Cantidad.setColspan(3);
            Cantidad.setPaddingLeft(28);
            
            PdfPCell Costo = new PdfPCell(new Paragraph("Costo"));
            Costo.setColspan(3);
            Costo.setPaddingLeft(25);
            
            PdfPCell Genero = new PdfPCell(new Paragraph("Genero"));
            Genero.setColspan(3);
            Genero.setPaddingLeft(40);
           
            PdfPCell celdaFinal = new PdfPCell(new Paragraph("Total"));
            celdaFinal.setColspan(8);
            celdaFinal.setPadding(5);
            
            PdfPCell Total = new PdfPCell(new Paragraph(imp.get(imp.size()-1)));
            Total.setColspan(3);
            Total.setPaddingLeft(42);
            
            
            
            table.addCell(celdaInicial);
            table.addCell(Pelicula);
            table.addCell(Genero);
            table.addCell(Cantidad);
            table.addCell(Costo); 
           
            
            for(int i=0; i<= imp.size()-2;i++){
                
                PdfPCell agregar = new PdfPCell(new Paragraph(imp.get(i)));
                agregar.setColspan(3);
                agregar.setPaddingLeft(25);
                table.addCell(agregar);
            }
            
            
            
            table.addCell(celdaFinal);
            table.addCell(Total);
            
            doc.add(table);
            doc.add(imagen3);
        } catch (Exception e) {     
            System.out.println(e);
        }
        doc.close();
        System.out.println("Documento Cerrado");
    }
    
    
}
