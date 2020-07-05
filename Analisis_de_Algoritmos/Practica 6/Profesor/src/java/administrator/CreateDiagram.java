package administrator;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateDiagram extends HttpServlet {

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
        out.println("<frameset cols='30%, *'/>");
        out.println("<frame src='formulario.html'/>");
        out.println("<<frame src='Design'/>");
        out.println("<body>");
        out.println("<a href='Signout'><p align='right'>Sign Out</p></a><br>");
        out.println("<center><h1>Create Diagram</h1></center>");
        out.println("<br>");
        out.println("</body>");
        out.println("</html>");
    }
}
