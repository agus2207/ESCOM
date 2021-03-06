import java.net.*;
import java.io.*;

public class CEcoUDPB{
    public static void main (String[] args){
        try{
            DatagramSocket cl = new DatagramSocket(); //Se crea el datagramsocket
            System.out.println("Cliente iniciado, escriba un mensaje"); 
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String mensaje = br.readLine();
            byte[] b = mensaje.getBytes(); 
            String dst = "127.0.0.1";
            int pto = 2000; //Puerto donde escucha el servidor
            DatagramPacket p = new DatagramPacket(b, b.length, InetAddress.getByName(dst), pto); //Se envia el mensaje
            cl.send(p); //envio el mensaje
            cl.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }    
}