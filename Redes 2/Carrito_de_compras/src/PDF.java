import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class PDF {
    public void escribir(ArrayList<String> compra){
        Document doc = new Document(PageSize.A4);
        System.out.println("Documento creado...");
        try {
            PdfWriter.getInstance(doc,new FileOutputStream("src/Documentos/Ticket.pdf"));
            doc.open();
            System.out.println("Documento abierto...");
            PdfPTable table = new PdfPTable(11);
            Paragraph parrafo1 = new Paragraph("   ");
            Paragraph parrafo2 = new Paragraph("   ");
            
            Image imagen = Image.getInstance("src/Imagenes/logotipo.png");
            imagen.scalePercent(35);
            imagen .setAbsolutePosition(450f, 750f);
            doc.add(imagen);
            
            Image imagen2 = Image.getInstance("src/Imagenes/logo.png");
            imagen2.scalePercent(40);
            imagen2.setAbsolutePosition(20f, 750f);
            doc.add(imagen2);
            
            Image imagen3 = Image.getInstance("src/Imagenes/qr.png");
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
            
            PdfPCell Total = new PdfPCell(new Paragraph(compra.get(compra.size()-1)));
            Total.setColspan(3);
            Total.setPaddingLeft(42);
            
            
            
            table.addCell(celdaInicial);
            table.addCell(Pelicula);
            table.addCell(Genero);
            table.addCell(Cantidad);
            table.addCell(Costo); 
           
            
            for(int i=0; i<= compra.size()-2;i++){
                if(compra.get(i) != null){
                    PdfPCell agregar = new PdfPCell(new Paragraph(compra.get(i)));
                    agregar.setColspan(3);
                    agregar.setPaddingLeft(25);
                    table.addCell(agregar);
                }
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
