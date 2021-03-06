/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.StandardProtocolFamily;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;


/**
 *
 * @author alex
 */
public class ServidorPrincipal {

    public static void main(String[] args) {
        int pto=2005;
        try {
        NetworkInterface n = NetworkInterface.getByName("wlan0");

            InetSocketAddress dir = new InetSocketAddress(pto);
            DatagramChannel s = DatagramChannel.open(StandardProtocolFamily.INET);
            s.setOption(StandardSocketOptions.SO_REUSEADDR, true);
            s.setOption(StandardSocketOptions.IP_MULTICAST_IF, n);
            InetAddress group = InetAddress.getByName("227.1.1.1");
            s.join(group, n);
            s.configureBlocking(false);
            s.socket().bind(dir);
            Selector sel = Selector.open();
            s.register(sel, SelectionKey.OP_WRITE);
            ByteBuffer b = ByteBuffer.allocate(4);
            System.out.println("Servidor listo.. ");
            while (true) {
            
            }//while
        }catch (Exception e){
            e.printStackTrace();
        }//catch
    }
    }
    