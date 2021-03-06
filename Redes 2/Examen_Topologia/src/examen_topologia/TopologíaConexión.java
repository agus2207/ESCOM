/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen_topologia;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;

/**
 *
 * @author agust
 */
public class TopologíaConexión {
 
    public int puerto;
    public InetAddress address = null;
    public String ipaddress;
    MulticastSocket s;
    InterfazTopologi interfaz;
    public TopologíaConexión() throws SocketException, UnknownHostException, IOException, InterruptedException
    {
        String Cadenapuerto = JOptionPane.showInputDialog(null,"INGRESE UN NUMERO DE PUERTO");
        puerto = Integer.parseInt(Cadenapuerto);
        address = InetAddress.getLocalHost();
        ipaddress = ""+address;        
        interfaz = new InterfazTopologi(puerto,address);
        interfaz.setVisible(true);
        Thread HiloClientMult = new ClienteMulticast(puerto,address,interfaz);
        Thread HiloServData = new Server(puerto,interfaz,address);
        Thread HiloClienData = new Cliente(address,puerto,interfaz);
        HiloClientMult.start();
        HiloServData.start();
        Thread.sleep(1000);
        HiloClienData.start();
    }
    
    
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException, InterruptedException {
        //Se definira el puerto 9000 como el servidor
        TopologíaConexión tp = new TopologíaConexión();
    }
 
    
}
