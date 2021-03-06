/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen_topologia;

import java.io.File;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author agust
 */
public class Server extends Thread{
    
    public ServerSocket server;
    public int puerto;
    public InetAddress address;
    public InterfazTopologi interfaz;
    public String msj;
            
    public Server(int p, InterfazTopologi i,InetAddress a)
    {
        puerto = p;
        address = a;
        interfaz = i;
    }
    
    public void run()
    {
        try{
            msj = address+":"+puerto;
            //server = new ServerSocket(puerto);
            DatagramSocket mySocket = new DatagramSocket(puerto, address);
            interfaz.TextAreaLog.append("Server conectado: "+puerto+"\n");
            byte [] buffer = msj.getBytes();
            while(true)
            {
                if(interfaz.AreaDeNodos.getText().length()>=68)
                {
                    interfaz.TextAreaLog.setText("Conectado al nodo final... esperando peticion de busqueda,Server Break \n");
                    break;
                }
                if(interfaz.TxtFieldAnterior.getText().equals(msj)||interfaz.TxtFieldAnterior.getText().equals(""))
                {
                    interfaz.TextAreaLog.append("Servidor levantado esperando un nuevo nodo\n");
                }else
                {
                    interfaz.TextAreaLog.append("Esperando cliente: "+(interfaz.TxtFieldAnterior.getText())+"\n");
                    Socket cliente = server.accept();
                    interfaz.TextAreaLog.append("Cliente conectado: "+(interfaz.TxtFieldAnterior.getText())+"\n");
                    DatagramPacket datagram = new DatagramPacket(buffer,buffer.length,address,puerto); 
                    mySocket.send(datagram); 
                }
                Thread.sleep(10000);
            }
            while(true)
            {
                byte buffer2[] = new byte [1000];
                interfaz.TextAreaLog.append("Esperando peticion.......\n");
                DatagramPacket datagram = new DatagramPacket(buffer2,buffer2.length); 
                mySocket.receive(datagram);
                byte buffer3[] = new byte [1000];
                DatagramPacket datagram2 = new DatagramPacket(buffer3,buffer3.length);
                mySocket.receive(datagram2);
                String g = new String(datagram.getData(),datagram.getOffset(),datagram.getLength());
                String pos = new String(datagram2.getData(),datagram2.getOffset(),datagram2.getLength());
                if(g.length() !=0){
                    interfaz.TextAreaLog.append("Se recibio: "+g+"\n");
                    Thread HiloBusqueda = new Busqueda(g, pos, interfaz, puerto, address);
                    HiloBusqueda.start();
                }
                Thread.sleep(10000);

            }
        }catch(Exception e){
            interfaz.TextAreaLog.append("Error con el servidor: "+puerto+"\n");
            e.printStackTrace();
        }
    }
}