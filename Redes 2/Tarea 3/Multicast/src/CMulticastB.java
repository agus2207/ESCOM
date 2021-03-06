import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.net.*;
import java.io.*;

public class CMulticastB {
    public static void main(String args[]) throws IOException{
        InetAddress gpo= null;
        
        System.out.println("Cliente iniciado, escriba un mensaje");
        BufferedReader br= new BufferedReader(new InputStreamReader (System.in));
        String mensaje= br.readLine();
	byte[]b= mensaje.getBytes();
        
        try {
            MulticastSocket cl= new MulticastSocket(9999);
            System.out.println("Cliente escuchando al puerto "+cl.getLocalPort());
            cl.setReuseAddress(true);
            
            try {
                gpo= InetAddress.getByName("228.1.1.1");
            } catch (UnknownHostException e) {System.err.println("Direccion erronea");}
            
            cl.joinGroup(gpo);
            System.out.println("Unido al grupo");
            
            while(true){
                DatagramPacket p= new DatagramPacket(new byte[100], 100);
                cl.receive(p);
                String msj= new String(p.getData());
                System.out.println("Datagrama recibido "+msj);
                System.out.println("Servidor descubierto: "+p.getAddress()+": "+p.getPort());
                
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException ie) {ie.printStackTrace();}
                
                DatagramPacket p_e= new DatagramPacket(b, b.length, gpo, 9999);
                cl.send(p_e);
                
            }
        } catch (Exception e) {e.printStackTrace();}
    }
}
