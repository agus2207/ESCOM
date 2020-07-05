package administrator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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


public class Almacenar extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String canvas = request.getParameter("canv");
        String xml=request.getRealPath("WEB-INF\\diagramas.xml");
        File fichero=new File(xml);
        if (fichero.isFile())
        {
            try{
                SAXBuilder builder=new SAXBuilder();
                Document doc=(Document) builder.build(fichero);
                Element raiz=doc.getRootElement();
                List nombres = raiz.getChildren();
                int i = nombres.size() + 1;
                Element ejemplo=new Element("ejemplo");
                ejemplo.setAttribute("id", "Diagrama "+i+"");
                Element can = new Element("canvas");
                can.setText(canvas);
                ejemplo.addContent(can);
                raiz.addContent(ejemplo);
                XMLOutputter xmlOutput = new XMLOutputter();
                xmlOutput.setFormat(Format.getPrettyFormat());
                xmlOutput.output(doc, new FileWriter(xml));
                } catch (JDOMException ex) {
                Logger.getLogger(Almacenar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else {
                out.println("error");
        }
    }
}
