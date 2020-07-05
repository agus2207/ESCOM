package administrator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class Design extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Create Diagram</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<form action='Diagrama' method='get'>");
        out.println("Inserte texto: <input type='text' name='texto1'><br><br>");
        out.println("Inserte texto: <input type='text' name='texto2'><br><br>");
        String xml = request.getRealPath("WEB-INF\\archivos.xml");
        File fichero = new File(xml);
        if (fichero.isFile()) {
            try {
                SAXBuilder builder = new SAXBuilder();
                Document doc = (Document) builder.build(fichero);
                Element raiz = doc.getRootElement();
                List nombres = raiz.getChildren();
                for (int i = 0; i < 4; i++) {
                    out.println("Seleccione una imagen: <select name='imagen" + i + "'>");
                    for (int j = 0; j < nombres.size(); j++) {
                        Element node1 = (Element) nombres.get(j);
                        out.println("<option>" + node1.getText() + "</option>");
                    }
                    out.println("</select><br><br>");
                }
                /*XMLOutputter xmlOutput = new XMLOutputter();
                xmlOutput.setFormat(Format.getPrettyFormat());
                xmlOutput.output(doc, new FileWriter(xml));*/
            } catch (JDOMException ex) {
                Logger.getLogger(UploadServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.println("<input type = 'submit' value = 'Crear Diagrama' />");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}