/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import java.io.DataOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.StandardProtocolFamily;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

/**
 *
 * @author Feer
 */
public class ServerMulticast{
    int ptoR,ptoF,ptoM;
    
    public ServerMulticast(int ptoMulti,int ptoRMI,int ptoFlujo){
        ptoM=ptoMulti;
        ptoR=ptoRMI;
        ptoF=ptoFlujo;
        try{
            NetworkInterface ni = NetworkInterface.getByName("lo");
            DatagramChannel cl = DatagramChannel.open(StandardProtocolFamily.INET);
            cl.setOption(StandardSocketOptions.SO_REUSEADDR, true);
            cl.setOption(StandardSocketOptions.IP_MULTICAST_IF, ni);
            cl.configureBlocking(false);
            cl.socket().bind(new InetSocketAddress(ptoMulti));
            Selector sel = Selector.open();
            cl.register(sel, SelectionKey.OP_WRITE);
            InetAddress group = InetAddress.getByName("228.1.1.1");
            cl.join(group, ni);
            System.out.println("Servidor Multicast Conectado");
            Manejador m=new Manejador(sel,ptoRMI,ptoFlujo,group);
            m.start();
        }catch(Exception e){
            e.printStackTrace();
        }//catch
    }//main
}

class Manejador extends Thread{
    String ptos;
    Selector sel;
    InetAddress grupo;
    public Manejador(Selector sel,int ptoR,int ptoF,InetAddress gpo){
        this.sel = sel;
        ptos = ptoR+":"+ptoF;
        grupo=gpo;
    }
    
    public void run(){
        try{
            ByteBuffer b= ByteBuffer.allocate(10);
            while(true){
                sel.select();
                Iterator<SelectionKey> it = sel.selectedKeys().iterator();
                while (it.hasNext()) {
                    SelectionKey k = (SelectionKey) it.next();
                    it.remove();
                    if(k.isWritable()){
                        DatagramChannel ch = (DatagramChannel) k.channel();
                        b.clear();
                        b.put(ptos.getBytes());
                        b.flip();
                        ch.send(b, new InetSocketAddress(grupo, 2002));
                        System.out.println("Datagrama Enviado Server");
                        //s.register(sel, SelectionKey.OP_READ);    
                    }
                }//while
                Thread.sleep(3000);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }    
}
