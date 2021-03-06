package examen_topologia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author agust
 */
public class Cliente extends Thread{

    public int puerto;
    public InetAddress address;
    public InterfazTopologi interfaz;
    public Socket cliente;
    public String msjAnterior,msj,nuevoPuerto;
    
    public Cliente(InetAddress a,int p,InterfazTopologi i)
    {
        puerto = p;
        address = a;
        interfaz = i;
    }
    
    public void run()
    {
        msj = address+":"+puerto;
        msjAnterior = interfaz.TFieldSiguiente.getText();
        while(true)
        {
            if(interfaz.AreaDeNodos.getText().length()>=68)
            {
                interfaz.TextAreaLog.setText("Conectado al nodo final... esperando peticion de busqueda\n");
                break;
            }
            try{
                 if(interfaz.TFieldSiguiente.getText().equals(msj))
                    interfaz.TextAreaLog.append("No se puede conectar en el servidor de origen\n");
                 else if(interfaz.TFieldSiguiente.getText().equals(msjAnterior))
                    interfaz.TextAreaLog.append("No se detecto un nuevo servidor\n");
                 else{
                    interfaz.TextAreaLog.append("Esperando al servidor: "+(interfaz.TFieldSiguiente.getText())+"...\n");
                    nuevoPuerto = interfaz.TFieldSiguiente.getText();
                    nuevoPuerto = nuevoPuerto.substring(nuevoPuerto.lastIndexOf(":")+1);
                    System.out.println("Nueva Dirección: "+address+":"+nuevoPuerto);
                    cliente = new Socket(address,Integer.parseInt(nuevoPuerto));
                    interfaz.TextAreaLog.append("Conectado al servidor: "+interfaz.TFieldSiguiente.getText()+"\n");
                   
                 }
            }catch(Exception e){
                interfaz.TextAreaLog.append("Error al conectarse al servidor: "+(puerto+1)+"\n");
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                interfaz.TextAreaLog.append("Error dentro de Thread Sleep");
            }
        }
        
        interfaz.BtnBusqueda.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
               if(interfaz.TBoxBusqueda.getText().length() !=0)
               {
                   try{
                        DatagramSocket EnviarDatos = new DatagramSocket();
                        byte [] ArchivoABuscar = interfaz.TBoxBusqueda.getText().getBytes();
                        InetAddress hostServidor = InetAddress.getLocalHost();
                        String ObtenerPuerto = interfaz.TFieldSiguiente.getText();
                        System.out.println("Obtener puerto: "+ObtenerPuerto);
                        ObtenerPuerto = ObtenerPuerto.substring(ObtenerPuerto.lastIndexOf(":")+1);
                        System.out.println("Puerto: "+ObtenerPuerto);
                        interfaz.TextAreaLog.append("BUSCAR EN -----> "+ObtenerPuerto+"\n");
                        int puertoServer = Integer.parseInt(ObtenerPuerto);
                        DatagramPacket petición = new DatagramPacket(ArchivoABuscar, ArchivoABuscar.length, hostServidor,
                                       puertoServer);
                        //EnviarDatos.send(petición);
                        byte [] pos = "1".getBytes();
                        DatagramPacket posicion = new DatagramPacket(pos, pos.length, hostServidor,
                                       puertoServer);
                        EnviarDatos.send(petición);
                        EnviarDatos.send(posicion);
                        interfaz.TextAreaLog.append("Se envio la petición\n");
                    } catch (Exception ex) {
                        System.out.println("Error Send mesage");
                    }
               }
            }
            
        });
        
        interfaz.BtnDescarga.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                String disponibilidad = interfaz.TextAreaLog.getText();
                String accept =  "SI EXISTE: "+interfaz.TBoxBusqueda.getText();
                if(disponibilidad.contains(accept)){
                    Thread HiloRecibe = new RecibirArchivo(address, interfaz.TBoxBusqueda.getText(), puerto, interfaz);
                    HiloRecibe.start();
                    try {
                        Thread.sleep(1000);
                    } 
                    catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "El archivo solicitado no existe");
                }
            }
        });
            
    }
    
}
