import java.net.*;
import java.io.*;

public class MulticastServer2 extends Thread{
    public static final String MCAST_ADDR = "230.0.0.1";
    public static final int MCAST_PORT = 9013;
    public static final int DGRAM_BUF_LEN = 512;

    public void run(){
        String msg = "Hola, soy servidor de Java";
        InetAddress group = null;
        try{
            group = InetAddress.getByName(MCAST_ADDR);
        }catch(UnknownHostException e){
            e.printStackTrace();
            System.exit(1);
        }
        for(;;){ //Lazo infinito del Servidor
            try{
                MulticastSocket socket = new MulticastSocket(MCAST_PORT);
                socket.joinGroup(group);
                DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.length(), group, MCAST_PORT);
                System.out.println("Enviando. "+msg+" con un TTL = "+socket.getTimeToLive());
                socket.send(packet);
                socket.close();
            }catch(IOException e){
                e.printStackTrace();
                System.exit(2);
            }
            try{
                Thread.sleep(1000*5);
            }catch(InterruptedException ie){}
        }
    }

    public static void main(String[] args){
        try{
            MulticastServer2 mc2 = new MulticastServer2();
            mc2.start();
        } catch(Exception e){
            e.printStackTrace();
        }
    } 
}