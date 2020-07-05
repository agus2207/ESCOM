package administrator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class MenuP extends HttpServlet {

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
        out.println("<a href='Signout'><p align='right'>Sign Out</p></a><br>");
        out.println("<center><h1>Create Diagram</h1></center>");
        out.println("<a href='CreateDiagram' target='_top'> Create Diagram</a><br>");
        out.println("<table border='1'>");
        out.println("<tr>");
        out.println("<th>Diagrama</th>");
        out.println("<th colspan='3'>Acciones</th>");
        out.println("</tr>");
        String xml = request.getRealPath("WEB-INF\\diagramas.xml");
        File fichero = new File(xml);
        if (fichero.isFile()) {
            try {
                SAXBuilder builder = new SAXBuilder();
                Document doc = (Document) builder.build(fichero);
                Element raiz = doc.getRootElement();
                List nombres = raiz.getChildren();
                for (int i = 0; i < nombres.size(); i++) {
                    Element node1 = (Element) nombres.get(i);
                    out.println("<th>"+node1.getAttributeValue("id")+"</th>");
                    out.println("<th><a href = 'Ver?nom="+node1.getAttributeValue("id")+"'>Ver Diagrama</a></th>");
                    out.println("<th><a href = 'Eliminar?nom="+node1.getAttributeValue("id")+"'>Eliminar</a></th>");
                    out.println("<th>Modificar</th>");
                    out.println("</tr>");
                }
                /*XMLOutputter xmlOutput = new XMLOutputter();
                xmlOutput.setFormat(Format.getPrettyFormat());
                xmlOutput.output(doc, new FileWriter(xml));*/
            } catch (JDOMException ex) {
                Logger.getLogger(Almacenar.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            out.println("error");
        }
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }
}
