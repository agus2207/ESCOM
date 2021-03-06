import java.net.*;
import java.io.*;

public class SPrimUDPB{
    public static void main(String[] args){
        try {
            DatagramSocket s = new DatagramSocket(2000);
            System.out.println("Servidor iniciado");
            for(;;){
                DatagramPacket p = new DatagramPacket(new byte[2000], 2000);
                s.receive(p);
                System.out.println("Datagrama recibido desde "+p.getAddress()+":"+p.getPort());
                DataInputStream dis = new DataInputStream(new ByteArrayInputStream(p.getData()));
                int x = dis.readInt();
                float f = dis.readFloat();
                long l = dis.readLong();
                System.out.println("\n\nEntero: "+x+" Flotante: "+f+" Largo: "+l);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
}