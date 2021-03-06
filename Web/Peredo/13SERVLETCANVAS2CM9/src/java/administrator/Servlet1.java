package administrator;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Servlet1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        String numero = request.getParameter("numero");
        int numeroi = Integer.parseInt(numero);
        HttpSession session = request.getSession();
        session.setAttribute("anumero", numero);
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet Servlet1</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<form action = 'Servlet 2' method='get'>");
        for (int i = 0; i < numeroi; i++){
            out.println("PUNTO INICIAL X: <input type='text' name='xi"+i+"' />");
        out.println("PUNTO INICIAL Y: <input type='text' name='yi"+i+"' /><br/>");
        out.println("PUNTO FINAL X: <input type='text' name='xf"+i+"' />");
        out.println("PUNTO FINAL Y: <input type='text' name='yf"+i+"' /><br/>");
        }
        out.println("<input type='sumbit' />");
        out.println("</from>");
        out.println("</body>");
        out.println("</html>");
    }

}