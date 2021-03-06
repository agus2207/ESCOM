package paquete;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom.*;
import org.jdom.output.XMLOutputter;
import java.io.PrintWriter;
import java.io.FileWriter;

/**
 * Servlet implementation class Servlet1
 */
public class ServletXML1 extends HttpServlet {
//	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		try
		{
			Element raiz = new Element("ROOT");
			Element hoja = new Element("hoja");
			hoja.setAttribute("numerodehojas", "4");
			hoja.setText("VALORDELNODO");
			raiz.addContent(hoja);
  
			Document newdoc = new Document(raiz);
			XMLOutputter fmt = new XMLOutputter();
			FileWriter writer = new FileWriter("c:\\pregunta2.xml");
			fmt.output(newdoc, writer);
            writer.flush();
            writer.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		

	}


}
