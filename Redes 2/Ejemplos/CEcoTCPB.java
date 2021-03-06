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
            System.out.print("Recibimos un mensaje desde el Servidor");
            System.out.print("Mensaje:"+mensaje);
            //Cerramos flujos y socket
            br1.close();
            br2.close();
            cl.close();
        }catch(Exception e){
            e.printStackTrace();
        }//try-catch
    }//main
}//class
