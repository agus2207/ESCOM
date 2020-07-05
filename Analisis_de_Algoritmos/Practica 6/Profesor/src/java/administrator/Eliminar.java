package administrator;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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

public class Eliminar extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String diagrama = request.getParameter("nom");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Create Diagram</title>");
        out.println("<script src='fabric.js'></script>");
        out.println("<script src='Jquery.js'></script>");
        out.println("</head>");
        out.println("<body>");
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
                    String id = node1.getAttributeValue("id");
                    if (diagrama.equals(id)) {
                        String canvas = node1.getChildText("canvas");
                        node1.removeChildren(canvas);
                        
                    }
                }
            } catch (JDOMException ex) {
                Logger.getLogger(Almacenar.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.println("</body>");
            out.println("</html>");
        }

    }
}
