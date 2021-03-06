/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen_topologia;

import java.awt.List;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.ServerSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author agust
 */
public class ServidorMulticast {
    
    public ArrayList<String> Nodos;
    public MulticastSocket serverMul;
    
    public ServidorMulticast() throws UnknownHostException, IOException
    {
        int puerto = 4000;
        InetAddress grupo = null;
        Nodos = new ArrayList<>();
        /*ArrayList<String> NodosRecibidos = new ArrayList();
        Nodos.add("DESKTOP-14A27NN/192.168.56.1:9000");
        Nodos.add("DESKTOP-14A27NN/192.168.56.1:9001");
        Nodos.add("DESKTOP-14A27NN/192.168.56.1:9002");
        System.out.println("Lista: "+Nodos);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(baos);
        for (String element : Nodos) {
            out.writeUTF(element);
        }
        byte[] bytes = baos.toByteArray();
        System.out.println("Mensaje Enviado");
        System.out.println(new String(bytes,0));
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        DataInputStream in = new DataInputStream(bais);
        while (in.available() > 0) {
            String element = in.readUTF();
            NodosRecibidos.add(element);
            System.out.println(element);
        }
        System.out.println("Lista Recibida: "+NodosRecibidos);*/
        try{
            serverMul = new MulticastSocket(puerto);
            System.out.println("Socket Multicast iniciado en el puerto "+puerto);
            serverMul.setReuseAddress(true);
            serverMul.setTimeToLive(128);
             try{
                grupo = InetAddress.getByName("228.1.1.1");
                System.out.println("Grupo: "+grupo);
            }catch(UnknownHostException u){
                System.err.println("Direccion no valida");
            }
             serverMul.joinGroup(grupo);
             while(true){
                //Se reciben datos
                DatagramPacket dp =new DatagramPacket(new byte[65535],65535);
                serverMul.receive(dp);
                System.out.println("Actualizacion de los nodos");
                String g = new String(dp.getData(),dp.getOffset(),dp.getLength());
                int flag=0;
                for(int i=0; i<Nodos.size();i++)
                {
                    if(g.equals(Nodos.get(i)))
                        flag = 1;
                    System.out.println("Flag :"+flag);
                }
                if(flag == 0)
                    Nodos.add(g);
                System.out.println("SocketAddress :"+g);
                System.out.println("Lista: "+Nodos);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                DataOutputStream out = new DataOutputStream(baos);
                for (String element : Nodos) {
                    out.writeUTF(element);
                }
                byte[] bytes = baos.toByteArray();
                //Se envian datos
                DatagramPacket dpsend = new DatagramPacket(bytes, bytes.length,grupo,4001);
                serverMul.send(dpsend);
                String envioDatos = new String(dpsend.getData(),dpsend.getOffset(),dpsend.getLength());
                System.out.println("Envio de datos: "+envioDatos);
                Thread.sleep(5000);
            }
        }catch(Exception e)
        {
            System.out.println("Error al server multicast.");
        }
    }
    
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        //Se definira el puerto 4000 como el servidor
        ServidorMulticast srv = new ServidorMulticast();
    }

}


