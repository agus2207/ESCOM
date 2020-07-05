package administrator;

import java.io.*;
import static java.lang.System.out;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class UploadServlet extends HttpServlet {

    private boolean isMultipart;
    private String filePath;
    private int maxFileSize = 5000000 * 1024;
    private int maxMemSize = 4 * 1024;
    private File file;

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {

        // Check that we have a file upload request
        filePath = request.getRealPath("/");
        isMultipart = ServletFileUpload.isMultipartContent(request);
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        java.io.PrintWriter out = response.getWriter();
        if (!isMultipart) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet upload</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<p>No se subio el archivo</p>");
            out.println("</body>");
            out.println("</html>");
            return;
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();

        // maximum size that will be stored in memory
        factory.setSizeThreshold(maxMemSize);

        // Location to save data that is larger than maxMemSize.
        factory.setRepository(new File(filePath));

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // maximum file size to be uploaded.
        upload.setSizeMax(maxFileSize);

        try {
            // Parse the request to get file items.
            List fileItems = upload.parseRequest(request);

            // Process the uploaded file items
            Iterator i = fileItems.iterator();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet upload</title>");
            out.println("</head>");
            out.println("<body>");

            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                if (!fi.isFormField()) {
                    // Get the uploaded file parameters
                    String fieldName = fi.getFieldName();
                    String fileName = fi.getName();
                    String contentType = fi.getContentType();
                    boolean isInMemory = fi.isInMemory();
                    long sizeInBytes = fi.getSize();

                    // Write the file
                    if (fileName.lastIndexOf("\\") >= 0) {
                        file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\")));
                    } else {
                        file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\") + 1));
                    }
                    fi.write(file);
                    out.println("Archivo subido: " + fileName + "<br />");
                    out.println("<form action = 'UploadServlet' method = 'post' enctype = 'multipart/form-data'>");
                    out.println("<input type = 'file' name = 'file' size = '45' />");
                    out.println("<br />");
                    out.println("<input type = 'submit' value = 'Upload File' />");
                    out.println("</form>");
                    String xml = request.getRealPath("WEB-INF\\archivos.xml");
                    File fichero = new File(xml);
                    if (fichero.isFile()) {
                        try {
                            SAXBuilder builder = new SAXBuilder();
                            Document doc = (Document) builder.build(fichero);
                            Element raiz = doc.getRootElement();
                            Element nombre = new Element("nombre");
                            List nombres = raiz.getChildren();
                            nombre.setText(fileName);
                            raiz.addContent(nombre);
                            for (int j = 0; j < nombres.size(); j++) {
                                Element node1 = (Element) nombres.get(j);
                                out.println("<h6>"+node1.getText()+"</h6>");
                                out.println("<img src=" + node1.getText() + " width='80' height='80'><br>");
                            }
                            XMLOutputter xmlOutput = new XMLOutputter();
                            xmlOutput.setFormat(Format.getPrettyFormat());
                            xmlOutput.output(doc, new FileWriter(xml));
                        } catch (JDOMException ex) {
                            Logger.getLogger(UploadServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        out.println("error");
                    }
                }
            }
            out.println("</body>");
            out.println("</html>");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {

        throw new ServletException("GET method used with "
                + getClass().getName() + ": POST method required.");
    }
}
