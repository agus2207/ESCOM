package profesor;

import inicio.BaseXML;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class AgregaE extends HttpServlet {
    String path="";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            //Recuperamos nombre y usuario de la sesión para los datos de la cuenta activa  
            HttpSession session=request.getSession();
            String nombre=(String)session.getAttribute("nombre");  
            String user=(String)session.getAttribute("user");
            
            //Para obtener la ruta relativa
            ServletContext context = request.getServletContext();           
            path = context.getRealPath("/");
            path=path.replace("\\build", "");
            System.out.println(path);
            
            BaseXML BD = new BaseXML(path);
            String auxi[]=BD.ObtenerDatosUsuario(BD.BuscarUsuario(user));
            session.setAttribute("grupo", auxi[5]);
            
            response.setContentType("text/html;charset=UTF-8");     
            PrintWriter out = response.getWriter();                 
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Agregar ejercicio</title>");            
            out.println("<link rel='stylesheet' type='text/css' href='"+request.getContextPath()+"/Estilos/formularios.css' />");
            out.println("</head>");
            out.println("<body>");
            out.println("<div>");
            out.println("<ul>");
            out.println("<li id=activo><a href='Profesor'>Inicio Profesor</a></li>");
            out.println("<li><a href='VerGrupo'>Ver Grupo</a></li>");
            out.println("<li><a href='VerE'>Ver Ejercicios</a></li>");
            out.println("<li><a href='AgregaE'>Agregar Ejercicio</a></li>");
            out.println("<li><a href='BuscarE'>Buscar/Modificar Ejercicio</a></li>");
            out.println("<li><a href='BuscarE'>Calificar Ejercicio</a></li>");
            out.println("<li><a href='Subida'>Subir archivos</a></li>");
            out.println("<li><a href=''>Cuenta activa: "+user+".</a>");
            out.println("<ul>");
            out.println("<li><a href='login.html'>Cerrar Sesión</a></li>");
            out.println("</ul></li>");
            out.println("</div>");
            out.println("<div class='title'><h1>Agregar Ejercicio</h1></div>");
            out.println("<div class='form IngresaE'>");
            out.println("<form action='Diagrama' method='get'>");
            out.println("Inserte texto: <input type='text' name='texto1'><br><br>");
        out.println("Inserte texto: <input type='text' name='texto2'><br><br>");
        String xml = request.getRealPath("archivos.xml");
        File fichero = new File(xml);
        if (fichero.isFile()) {
                SAXBuilder builder = new SAXBuilder();
                Document doc;
                try {
                    doc = (Document) builder.build(fichero);
                
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

                } catch (JDOMException ex) {
                    Logger.getLogger(AgregaE.class.getName()).log(Level.SEVERE, null, ex);
                }
            out.println("<input type = 'submit' value = 'Crear Diagrama' />");
            out.println("</form>");
            out.println("</div>");
            out.println("<div id='plot-id'></div>");
            out.println("<p>wpvinrwp</p>");
            out.println("</body>");
            out.println("</html>");     
        }
    }
}
