package datagrama;

import java.net.*;
import java.io.*;

public class DatagramCliente extends Thread {
    
    private String mensaje;
    private int port;
    private String dst;
    public DatagramCliente(String mensaje,int port,String ip) {
        this.mensaje = mensaje;
        this.port = port;
        dst = ip;
    }
    public void run(){
        try {
            DatagramSocket cl = new DatagramSocket();
            //System.out.println("Cliente iniciado");
            byte[] b = mensaje.getBytes();
            
            //System.out.println("Enviando" + mensaje + " a el puerto " + port);                 
            DatagramPacket p = new DatagramPacket(b, b.length, InetAddress.getByName(dst), port);
            cl.send(p);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
