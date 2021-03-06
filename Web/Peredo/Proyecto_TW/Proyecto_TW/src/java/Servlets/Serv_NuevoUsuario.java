package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

    public class Serv_NuevoUsuario extends HttpServlet {

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            String nombre= request.getParameter("nombre");
            String correo= request.getParameter("correo");
            String pass= request.getParameter("pass");
            String valor_select= request.getParameter("categoria");
            boolean exist= false; //Este booleano indicará si el correo ya se encuentra asociado
            String cadena = request.getRealPath("/");
            System.out.println("RealPath: "+cadena+"Base_Datos.xml");
            System.out.println("RealPath corregido: "+cadena.substring(0, (cadena.length()-10))+ "web\\Base_Datos.xml");
            SAXBuilder builder = new SAXBuilder();
            
            
            File fichero = new File(cadena.substring(0, (cadena.length()-10))+ "web\\Base_Datos.xml");
            try {    
                Document document = (Document) builder.build(fichero);
                //System.out.println("Root element :" + document.getRootElement().getName());
                Element classElement = document.getRootElement();
                List list = classElement.getChildren("USUARIO");
                for (int i= 0; i<list.size(); i++) {
                    Element node = (Element) list.get(i);
                    System.out.println("Nombre: " + node.getChildText("NOMBRE"));
                    System.out.println("Correo: " + node.getChildText("CORREO"));
                    System.out.println("Password: " + node.getChildText("PASS"));
                    System.out.println("Categor: " + node.getChildText("CATEGORIA"));
                    if(correo.equals(node.getChildText("CORREO"))){
                        exist=true; //exist cambia a true si encuentra una coincidencia 
                    }
                }

                if(exist == false){
                    
                     //----------Añade nuevo elemento usuario------------
                     Element staff = new Element("USUARIO");
                     //staff.setAttribute(new Attribute("id", "1"));
                     staff.addContent(new Element("NOMBRE").setText(""+nombre));
                     staff.addContent(new Element("CORREO").setText(""+correo));
                     staff.addContent(new Element("PASS").setText(""+pass));
                     staff.addContent(new Element("CATEGORIA").setText(""+valor_select));
                     classElement.addContent(staff);
                     //------------------------------------------------

                     
                     //------------Escritor dentro del archivo XML----------
                     XMLOutputter xmlOutput = new XMLOutputter();
                     xmlOutput.setFormat(Format.getPrettyFormat());
                     xmlOutput.output(document, new FileWriter(fichero));
                     //----------------------------------------------------
                     out.println("<html>");
                     out.println("<head>");
                     out.println("<title> Nuevo Usuario </title>");
                     out.println("</head>");
                     out.println("<body>");
                     out.println("<h1 style='text-align: center;'>Bienvenido "+nombre+"! tu registro se ha realizado exitosamente</h1>");
                     out.println("<a href='Pagina_Inicio.html'> Pagina Inicial </a>");
                     out.println("</body>");
                     out.println("</html>");                     
                     
                    }else{
                        System.out.println("El correo ya se encuentra asociado");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title> Nuevo Usuario </title>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1 style='text-align: center;'>El correo que has ingresado ya se encuentra registrado, intentalo nuevamente</h1>");
                        out.println("<br/>");
                        out.println("<a href= 'formularioregistro.html'> Registro </a>");
                        out.println("</body>");
                        out.println("</html>");                        
                    }

            }catch (IOException io) {
                System.out.println(io.getMessage());
            }catch (JDOMException jdomex) {
                System.out.println(jdomex.getMessage());
            }
        }
    }