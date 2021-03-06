import java.net.*;
import java.io.*;

public class SEcoUDPB{
    public static void main(String[] args){
        try{
            DatagramSocket s = new DatagramSocket(2000); //Puerto donde escuchamos
            System.out.println("Hola mundo");
            for(;;){
                DatagramPacket p = new DatagramPacket(new byte[2000], 2000); //Se recibe el paquete
                s.receive(p); //Bloqueo
                System.out.println("Datagrama recibido desde "+p.getAddress()+"."+p.getPort()); //Direcion y puerto desde el que se envio
                String msj = new String(p.getData(), 0, p.getLength());
                System.out.println("Con el mensaje: "+msj);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
