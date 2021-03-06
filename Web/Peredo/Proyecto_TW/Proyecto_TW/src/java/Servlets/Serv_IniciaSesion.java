package Servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;


public class Serv_IniciaSesion extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String correo= request.getParameter("correo");
        String pass= request.getParameter("pass");
        PrintWriter out = response.getWriter();
        
        int exist=0 ;
        String cadena = request.getRealPath("/");
        SAXBuilder builder = new SAXBuilder();
        File fichero = new File(cadena.substring(0, (cadena.length()-10))+ "web\\Base_Datos.xml");
        
        try{
            Document document = (Document) builder.build(fichero);
                Element classElement = document.getRootElement();
                List list = classElement.getChildren("USUARIO");
                for (int i= 0; i<list.size(); i++) {
                    Element node = (Element) list.get(i);
                    System.out.println("Nombre: " + node.getChildText("NOMBRE"));
                    System.out.println("Correo: " + node.getChildText("CORREO"));
                    System.out.println("Password: " + node.getChildText("PASS"));
                    System.out.println("Categor: " + node.getChildText("CATEGORIA"));
                    if(correo.equals(node.getChildText("CORREO"))){
                        exist=1;
                        if(pass.equals(node.getChildText("PASS"))){
                            exist=2; //El 2 indica que el correo y el pass son correctos y puede acceder a la página inicial
                        }
                        
                    }
                }
                if(exist==0){
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet Serv_IniciaSesion</title>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1> Error al verificar correo, te invitamos a "+"<a href='formularioregistro.html'>Registrarte! </a>"+"</h1>");
                    out.println("</body>");
                    out.println("</html>");
                }
                
                if(exist==1){
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<meta charset='UTF-8'>");
                    out.println("<title>Servlet Serv_IniciaSesion</title>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1 style='text-align: center;'> La contraseña no es valida, favor de verificar </h1>");
                    out.println("</body>");
                    out.println("</html>");
                }
                if(exist==2){
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<meta charset='UTF-8'>");
                    out.println("<title>Servlet Serv_IniciaSesion</title>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1 style='text-align: center;'> Sesión iniciada exitosamente </h1>");
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
