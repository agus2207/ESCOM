import java.net.*;
import java.io.*;

public class CEcoTCPB {

    public static void main(String[] args) throws IOException {
        try{
            BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Escribe la direccion del Servidor:");
            String host = br1.readLine();
            System.out.print("\n\n Escriba el puerto:");
            int pto = Integer.parseInt(br1.readLine());
            //Creamos el socket y nos conectamos
            Socket cl = new Socket(host,pto);
            BufferedReader br2 = new BufferedReader(new InputStreamReader(cl.getInputStream()));
            String mensaje = br2.readLine();
            System.out.print("Recibimos un mensaje desde el Servidor \n");
            System.out.print("Mensaje:"+mensaje);
            String mensaje2 = "Respuesta al servidor";
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(cl.getOutputStream()));
            //se envia el mensaje
            pw.println(mensaje2);
            //Cerramos flujos y socket
            pw.flush();
            pw.close();
            br1.close();
            br2.close();
            cl.close();
        }catch(Exception e){
            e.printStackTrace();
        }//try-catch
    }//main
}//class