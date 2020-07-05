package administrator;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Diagrama extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String texto1 = request.getParameter("texto1");
        String imagen1 = request.getParameter("imagen0");
        String texto2 = request.getParameter("texto2");
        String imagen2 = request.getParameter("imagen1");
        String imagen3 = request.getParameter("imagen2");
        String imagen4 = request.getParameter("imagen3");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");

            out.println("<title>Servlet Servlet2</title>");

            out.println("<script src='fabric.js'></script>");
            out.println("<script src='Jquery.js'></script>");
            out.println("</head>");
            out.println("<body>");

            out.println("<canvas id='c'width='1000' height='1000'></canvas>");
            out.println("<input type='button' value='Guardar' onClick='serializar()'/><br>");

            out.println("<a href='CreateDiagram' target='_top'> Create Diagram</a><br>");
            out.println("<a href='menu.html' target='_top'> Menu del Profesor</a><br>");
            out.println("<a href='Sign out' target='_top'> Cerrar Sesion</a><br>");
            out.println("<script>");
            out.println("var canvas = new fabric.Canvas('c');");

            out.println("var text1 = new fabric.Text('" + texto1 + "', {");
            out.println("fontSize: 100,");
            out.println("left: 0,");
            out.println("top: 0");
            out.println("});");

            out.println("fabric.Image.fromURL('" + imagen1 + "', function(oImg1){");
            out.println("oImg1.scale(0.3);");
            out.println("canvas.add(oImg1);");
            out.println("});");

            out.println("var text2 = new fabric.Text('" + texto2 + "', {");
            out.println("fontSize: 100,");
            out.println("left: 0,");
            out.println("top: 0");
            out.println("});");

            out.println("fabric.Image.fromURL('" + imagen2 + "', function(oImg2){");
            out.println("oImg2.scale(0.3);");
            out.println("canvas.add(oImg2);");
            out.println("});");

            out.println("fabric.Image.fromURL('" + imagen3 + "', function(oImg3){");
            out.println("oImg3.scale(0.3);");
            out.println("canvas.add(oImg3);");
            out.println("});");

            out.println("fabric.Image.fromURL('" + imagen4 + "', function(oImg4){");
            out.println("oImg4.scale(0.3);");
            out.println("canvas.add(oImg4);");
            out.println("});");

            out.println("canvas.add(text1);");
            out.println("canvas.add(text2);");

            out.println("function serializar(){"
                    + "var canvasstring = JSON.stringify(canvas);"
                    + "$.ajax({"
                    + "type:'get',"
                    + "url:'Almacenar',"
                    + "data:{'canv':canvasstring},"
                    + "success: function () {"
                    + "alert ('diagrama guardado');"
                    + "}"
                    + ",error: function (){"
                    + "alert('diagrama no guardado');"
                    + "}"
                    + "});"
                    + "};");
            out.println("</script>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
