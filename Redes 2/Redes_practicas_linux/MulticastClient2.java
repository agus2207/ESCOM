import java.io.*;
import java.net.*;
import java.util.Vector;

public class MulticastClient2 extends Thread{
    public static final String MCAST_ADDR = "230.0.0.1";
    public static final int MCAST_PORT = 9013;
    public static final int DGRAM_BUF_LEN = 512;

    public void run(){
        InetAddress group = null;
        try{
            group = InetAddress.getByName(MCAST_ADDR);
        } catch(UnknownHostException e){
            e.printStackTrace();
            System.exit(1);
        }
        Vector d = new Vector();
        boolean salta = true;
        try{
            MulticastSocket socket = new MulticastSocket(MCAST_PORT);
            socket.joinGroup(group);
            int cd = 0;
            while(salta){
                byte[] buf = new byte[DGRAM_BUF_LEN];
                DatagramPacket recv = new DatagramPacket(buf, buf.length);
                socket.receive(recv); 
                System.out.println("Host remoto: "+recv.getAddress());
                System.out.println("Puerto: "+recv.getPort());
                //Aqui no se entienden los datos
                byte[] data = recv.getData();
                //Aqui se define la cadena
                System.out.println("Datos recibidos: "+new String(data));
            }
        }catch(IOException e){
            e.printStackTrace();
            System.exit(2);
        }
    }
     
    public static void main(String[] args){
        try{
            MulticastClient2 mc2 = new MulticastClient2();
            mc2.start();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}