/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multicast;

import java.net.*;

public class SMulticastThread extends Thread{
    private int port;
    private String msj;

    public int getPort() { return port;}
    public String getMsj() { return msj; }
    
    public SMulticastThread(int port, String msj) {
        this.port = port;
        this.msj = msj;
    }
    
    @Override
    public void run(){
        InetAddress gpo;
        try{
            MulticastSocket s = new MulticastSocket(port);
            s.setReuseAddress(true);
            s.setTimeToLive(1);            
            byte[] b = msj.getBytes();
            gpo = InetAddress.getByName("224.0.0.1");
            s.joinGroup(gpo);
            for(;;){
                DatagramPacket p = new DatagramPacket(b,b.length,gpo,9999);
                s.send(p);
                /*System.out.println("Enviando mensaje "+msj+ " con un TTL= " +
				s.getTimeToLive());*/
               try{
                    Thread.sleep(3000);
                }catch(InterruptedException ie){
                    ie.printStackTrace();
                }
            }//for
        }catch(Exception e){
            e.printStackTrace();
        }//catch
    }
}
