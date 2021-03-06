import java.net.*;
import java.io.*;

public class SecoTCPB {

    public static void main(String[] args) throws IOException {
        try{
            //se crea el socket
            
            ServerSocket s=new ServerSocket(1234);
            System.out.println("Esperando cliente...");
            //iniciando ciclo infinito
            for(;;){
                //Bloqueo
                Socket cl = s.accept();
                System.out.println("Conexión establecida desde"+ cl.getInetAddress()+":"+ cl.getPort());
                String mensaje = "Hola Mundo";
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(cl.getOutputStream()));
                //se envia el mensaje
                pw.println(mensaje);
                pw.flush();
                BufferedReader br2 = new BufferedReader(new InputStreamReader(cl.getInputStream()));
                String mensaje2 = br2.readLine();
                System.out.print("Recibimos un mensaje del Cliente \n");
                System.out.print("Mensaje:"+mensaje2);
                //se limpia el flujo
                pw.close();
                //Cerramos flujos y socket
                br2.close();
                cl.close();
            }//for  
        }
        catch(Exception e){
            e.printStackTrace();
        }//try-catch
    }//main
}//class
