/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketAddress;
import java.net.StandardProtocolFamily;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.JTextArea;

/**
 *
 * @author alex
 */
public class ClienteMulticas {

    ArrayList al;

    public ClienteMulticas(ArrayList a, String ptos, JTextArea area, ArrayList cont) {
        try {
            NetworkInterface ni = NetworkInterface.getByName("lo");
            DatagramChannel cl = DatagramChannel.open(StandardProtocolFamily.INET);
            cl.setOption(StandardSocketOptions.SO_REUSEADDR, true);
            cl.setOption(StandardSocketOptions.IP_MULTICAST_IF, ni);
            cl.configureBlocking(false);
            cl.socket().bind(new InetSocketAddress(2002));
            Selector sel = Selector.open();
            cl.register(sel, SelectionKey.OP_READ);
            InetAddress group = InetAddress.getByName("228.1.1.1");
            cl.join(group, ni);
            System.out.println("Cliente Multicast Conectado");
            String aux = ni.getInetAddresses().nextElement() + ":" + ptos;
            aux = aux.substring(1);
            Shared s = new Shared(a, cont, area, aux, sel);
            ManejadorCM m = new ManejadorCM(s);
            m.start();
            Verificador v = new Verificador(s);
            v.start();

        } catch (Exception e) {
            e.printStackTrace();
        }//catch
    }
}

class ManejadorCM extends Thread {

    private Shared shared;
    private final Lock l;

    public ManejadorCM(Shared shared) {
        this.shared = shared;
        l = shared.getLock();
    }

    public void run() {
        ByteBuffer b = ByteBuffer.allocate(9);
        try {
            while (true) {
                l.lock();

                shared.sharedManejador();

                System.out.println("Desbloqueado MANEJADOR");
                l.unlock();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Verificador extends Thread {

    private Shared shared;
    private final Lock l;

    public Verificador(Shared shared) {
        this.shared = shared;
        l = this.shared.getLock();
    }

    public void run() {
        while (true) {
            l.lock();
            shared.sharedVerificador();
            System.out.println("Desbloqueado VERIFICADOR");
            l.unlock();
        }
    }
}

class Shared {

    private volatile ArrayList hosts, cont;
    private volatile boolean disp;
    private final Lock lock;
    private final Condition condition;
    private volatile JTextArea area;
    private volatile String datos;
    private volatile Selector sel;

    public Shared(ArrayList hosts, ArrayList cont, JTextArea area, String datos, Selector sel) {
        this.hosts = hosts;
        this.cont = cont;
        this.area = area;
        this.datos = datos;
        lock = new ReentrantLock();
        condition = lock.newCondition();
        disp = false;
        this.sel = sel;
    }

    Lock getLock() {
        return lock;
    }

    void sharedManejador() {
        lock.lock();
        //System.out.println("Sección crítica MANEJADOR 1");
        try {
            
            while (disp) {
                try {
                    condition.await();
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
            
            disp = true;//Bloquea el acceso a la variable compartida
            
            ByteBuffer b = ByteBuffer.allocate(9);
            sel.select();
            Iterator<SelectionKey> it = sel.selectedKeys().iterator();
            while (it.hasNext()) {

                SelectionKey k = (SelectionKey) it.next();
                it.remove();
                if (k.isReadable()) {

                    //  Escucha Las direcciones de los servidores
                    DatagramChannel ch = (DatagramChannel) k.channel();
                    b.clear();
                    SocketAddress emisor = ch.receive(b);
                    b.flip();
                    InetSocketAddress d = (InetSocketAddress) emisor;
                    InetAddress sa = d.getAddress();
                    String sDir = sa.getHostAddress();
                    String aux = new String(b.array());
                    String host = sDir + ":" + aux;
                    if (!host.equals(datos)) {
                        if (!hosts.contains(host)) {
                            hosts.add(host);
                            cont.add(5);
                        } else {
                            cont.add(hosts.indexOf(host), 5);
                        }
                    }
                    System.out.println(host);
                    area.setText("");
                    Iterator<String> ite = hosts.iterator();
                    //System.out.println("Sección crítica MANEJADOR 3");
                    while (ite.hasNext()) {
                        area.append(ite.next() + "\n");
                    }
                    //System.out.println("Desbloqueado MANEJADOR");

                }
            }
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void sharedVerificador() {
        lock.lock();
        //System.out.println("Sección crítica VERIFICADOR 1");
        int i = 0;
        try {
            while (!disp) {
                try {
                    condition.await();
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
            disp = false;
            condition.signal();
            Iterator it = cont.iterator();
            //System.out.println("Sección crítica VERIFICADOR 2");
            while (it.hasNext()) {
                int c = (int) it.next();
                c--;
                cont.add(i, c);

                if (c == 0) {
                    cont.remove(i);
                    hosts.remove(i);
                }

                i++;
                /*try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }*/
            }
            i = 0;
            //System.out.println("Sección crítica VERIFICADOR 3");
        } catch (Exception e) {
            //e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
