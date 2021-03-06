import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

//Convertir este programa en donde tambien el cliente envie tramas al servidor

public class SMulticastB {
    public static void main(String[] args) {
        InetAddress gpo= null;
        try {
            MulticastSocket s= new MulticastSocket();
            s.setReuseAddress(false);
            s.setTimeToLive(1);
            String msj= "Hola soy el servidor";
            byte[]b= msj.getBytes();
            gpo= InetAddress.getByName("228.1.1.1");
            s.joinGroup(gpo);
            while(true){
                DatagramPacket p= new DatagramPacket(b, b.length, gpo, 9999);
                s.send(p);
                System.out.println("Mensaje: "+msj+" con TTL"+s.getTimeToLive());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ie) {ie.printStackTrace();}
            }
            
        } catch (Exception e) {e.printStackTrace();}
    }
}
