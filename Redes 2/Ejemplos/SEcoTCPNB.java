import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class SEcoTCPNB{
    public static void main(String[] args){
        try{
            String EECO = "";
            int pto = 9999;
            ServerSocketChannel s = ServerSocketChannel.open(); //Escucha
            s.configureBlocking(false);
            s.socket().bind(new InetSocketAddress(pto));
            System.out.println("Esperando clientes...");
            Selector sel = Selector.open();
            s.register(sel, SelectionKey.OP_ACCEPT);
            while(true){
                sel.select();
                Iterator<SelectionKey> it = sel.selectedKeys().iterator();
                while(it.hasNext()){
                    SelectionKey k = (SelectionKey)it.next();
                    it.remove();
                    if(k.isAcceptable()){ //En caso se que esten esperando mas clientes
                        SocketChannel cl = s.accept(); //Alguien ya se conecto
                        System.out.println("Cliente conectado desde "+cl.socket().getInetAddress()+" : "+cl.socket().getPort());
                        cl.configureBlocking(false);
                        cl.register(sel, SelectionKey.OP_ACCEPT|SelectionKey.OP_WRITE);
                        continue;
                    }
                    if(k.isReadable()){
                        try{
                            SocketChannel ch = (SocketChannel)k.channel();
                            ByteBuffer b = ByteBuffer.allocate(2000);
                            b.clear();
                            int n = 0;
                            String msj = "";
                            n = ch.read(b);
                            b.flip();
                            if(n > 0)
                                msj = new String (b.array(), 0, 10);
                            System.out.println("Mensaje de "+n+" bytes recibido: "+msj);
                            if(msj.equalsIgnoreCase("SALIR")){ // En caso de que el cliente quiera salir
                                k.interestOps(SelectionKey.OP_WRITE);
                                ch.close(); //Se cierra el canal
                            } 
                            else{
                                EECO = "ECO->"+msj;
                                k.interestOps(SelectionKey.OP_WRITE);
                            }
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                        continue;
                    }
                    else if(k.isWritable()){
                        try{
                            SocketChannel ch = (SocketChannel)k.channel();
                            ByteBuffer bb = ByteBuffer.wrap(EECO.getBytes());
                            ch.write(bb);
                            System.out.println("Mensaje de "+EECO.length()+" bytes enviado: "+EECO);
                            EECO = "";
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                        k.interestOps(SelectionKey.OP_READ); //Quiero seguir leyendo
                        continue;
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}