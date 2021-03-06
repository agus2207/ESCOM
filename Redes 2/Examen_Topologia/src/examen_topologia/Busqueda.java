/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen_topologia;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author agust
 */
public class Busqueda extends Thread{
    
    public ServerSocket server;
    public int puerto;
    public InetAddress address;
    public InterfazTopologi interfaz;
    public String msj;
    public String pos;
    public int desplazamiento;
    
    public Busqueda(String mensaje, String posicion, InterfazTopologi i, int p, InetAddress a){
        msj = mensaje;
        interfaz = i;
        puerto = p;
        pos = posicion;
        desplazamiento = Integer.parseInt(pos);
        address = a;
    }
    
    public void run(){
        try{
            if(!msj.contains("Archivo")){
                File f = new File("src/"+puerto);
                File[] ficheros = f.listFiles();
                int estado = 0;
                for (int i = 0; i < ficheros.length; i++) {
                    System.out.println("ARCHIVO: "+ficheros[i].getName());
                    if (ficheros[i].getName().equals(msj)) {
                        System.out.println("Archivo encontrado");
                        interfaz.TextAreaLog.append("ARCHIVO ENCONTRADO\n");
                        estado = 1;
                    } 
                    else {
                        System.out.println("Archivo no encontrado");
                        interfaz.TextAreaLog.append("ARCHIVO NO ENCONTRADO\n");
                        estado = 0;
                    }
                }
                int total = Integer.parseInt(interfaz.TxtDisponible.getText());
                if(estado == 1){
                    interfaz.TextAreaLog.append("ENVIANDO ARCHIVO AL SERVIDOR DE FLUJO\n");
                    Thread HiloEnvio = new EnvioArchivo(address, msj, puerto, interfaz);
                    HiloEnvio.start();
                    Thread.sleep(1000);
                    if(desplazamiento+1 > 1){
                        try{
                            DatagramSocket EnviarDatos = new DatagramSocket();
                            byte [] ArchivoABuscar = "Archivo encontrado".getBytes();
                            InetAddress hostServidor = InetAddress.getLocalHost();
                            String ObtenerPuerto = interfaz.TxtFieldAnterior.getText();
                            ObtenerPuerto = ObtenerPuerto.substring(ObtenerPuerto.lastIndexOf(":")+1);
                            int puertoServer = Integer.parseInt(ObtenerPuerto);
                            DatagramPacket petición = new DatagramPacket(ArchivoABuscar, ArchivoABuscar.length, hostServidor,
                                           puertoServer);
                            EnviarDatos.send(petición);
                            int envio = desplazamiento-1;
                            byte [] Enviapos = Integer.toString(envio).getBytes();
                            DatagramPacket posicion = new DatagramPacket(Enviapos, Enviapos.length, hostServidor,
                                           puertoServer);
                            EnviarDatos.send(posicion);
                            interfaz.TextAreaLog.append("AVISAR A -----> : "+ObtenerPuerto+"\n");
                            interfaz.TextAreaLog.append("Se envio la petición\n");
                        }
                        catch(Exception ex){
                            System.out.println("Error Send mesage");
                        }
                    }
                    else{
                        try{
                            interfaz.TextAreaLog.append("FIN DE LA BUSQUEDA\n");
                            interfaz.TextAreaLog.append("SI EXISTE: "+interfaz.TBoxBusqueda.getText()+"\n");
                        }
                        catch(Exception ex){
                            System.out.println("Error Send mesage");
                        }
                    }
                }
                else{
                    if(desplazamiento+1 < total){
                        try{
                            DatagramSocket EnviarDatos = new DatagramSocket();
                            byte [] ArchivoABuscar = msj.getBytes();
                            InetAddress hostServidor = InetAddress.getLocalHost();
                            String ObtenerPuerto = interfaz.TFieldSiguiente.getText();
                            ObtenerPuerto = ObtenerPuerto.substring(ObtenerPuerto.lastIndexOf(":")+1);
                            int puertoServer = Integer.parseInt(ObtenerPuerto);
                            DatagramPacket petición = new DatagramPacket(ArchivoABuscar, ArchivoABuscar.length, hostServidor,
                                           puertoServer);
                            EnviarDatos.send(petición);
                            int envio = desplazamiento+1;
                            byte [] Enviapos = Integer.toString(envio).getBytes();
                            DatagramPacket posicion = new DatagramPacket(Enviapos, Enviapos.length, hostServidor,
                                           puertoServer);
                            EnviarDatos.send(posicion);
                            interfaz.TextAreaLog.append("BUSCAR EN -----> "+ObtenerPuerto+"\n");
                            interfaz.TextAreaLog.append("Se envio la petición\n");
                        }
                        catch(Exception ex){
                            System.out.println("Error Send mesage");
                        }
                    }
                    else{
                        try{
                            DatagramSocket EnviarDatos = new DatagramSocket();
                            byte [] ArchivoABuscar = "Archivo Inexistente".getBytes();
                            InetAddress hostServidor = InetAddress.getLocalHost();
                            String ObtenerPuerto = interfaz.TxtFieldAnterior.getText();
                            ObtenerPuerto = ObtenerPuerto.substring(ObtenerPuerto.lastIndexOf(":")+1);
                            int puertoServer = Integer.parseInt(ObtenerPuerto);
                            DatagramPacket petición = new DatagramPacket(ArchivoABuscar, ArchivoABuscar.length, hostServidor,
                                           puertoServer);
                            EnviarDatos.send(petición);
                            int envio = desplazamiento+1;
                            byte [] Enviapos = Integer.toString(envio).getBytes();
                            DatagramPacket posicion = new DatagramPacket(Enviapos, Enviapos.length, hostServidor,
                                           puertoServer);
                            EnviarDatos.send(posicion);
                            interfaz.TextAreaLog.append("AVISAR A -----> "+ObtenerPuerto+"\n");
                            interfaz.TextAreaLog.append("Se envio la petición\n");
                        }
                        catch(Exception ex){
                            System.out.println("Error Send mesage");
                        }
                    }
                }
            }

            else{
                if(msj.equals("Archivo encontrado")){
                    if(desplazamiento+1 > 1){
                        try{
                            DatagramSocket EnviarDatos = new DatagramSocket();
                            byte [] ArchivoABuscar = "Archivo encontrado".getBytes();
                            InetAddress hostServidor = InetAddress.getLocalHost();
                            String ObtenerPuerto = interfaz.TxtFieldAnterior.getText();
                            ObtenerPuerto = ObtenerPuerto.substring(ObtenerPuerto.lastIndexOf(":")+1);
                            int puertoServer = Integer.parseInt(ObtenerPuerto);
                            DatagramPacket petición = new DatagramPacket(ArchivoABuscar, ArchivoABuscar.length, hostServidor,
                                           puertoServer);
                            EnviarDatos.send(petición);
                            int envio = desplazamiento-1;
                            byte [] Enviapos = Integer.toString(envio).getBytes();
                            DatagramPacket posicion = new DatagramPacket(Enviapos, Enviapos.length, hostServidor,
                                               puertoServer);
                            EnviarDatos.send(posicion);
                            interfaz.TextAreaLog.append("AVISAR A -----> "+ObtenerPuerto+"\n");
                            interfaz.TextAreaLog.append("Se envio la petición\n");
                        }
                        catch(Exception ex){
                            System.out.println("Error Send mesage");
                        }
                    }
                    else{
                        try{
                            interfaz.TextAreaLog.append("FIN DE LA BUSQUEDA\n");
                            interfaz.TextAreaLog.append("SI EXISTE: "+interfaz.TBoxBusqueda.getText()+"\n");
                        }
                        catch(Exception ex){
                            System.out.println("Error Send mesage");
                        }
                    }
                }
                
                else if(msj.equals("Archivo Inexistente")){
                    if(desplazamiento-1 > 1){
                        try{
                            DatagramSocket EnviarDatos = new DatagramSocket();
                            byte [] ArchivoABuscar = "Archivo Inexistente".getBytes();
                            InetAddress hostServidor = InetAddress.getLocalHost();
                            String ObtenerPuerto = interfaz.TxtFieldAnterior.getText();
                            ObtenerPuerto = ObtenerPuerto.substring(ObtenerPuerto.lastIndexOf(":")+1);
                            int puertoServer = Integer.parseInt(ObtenerPuerto);
                            DatagramPacket petición = new DatagramPacket(ArchivoABuscar, ArchivoABuscar.length, hostServidor,
                                           puertoServer);
                            EnviarDatos.send(petición);
                            int envio = desplazamiento-1;
                            byte [] Enviapos = Integer.toString(envio).getBytes();
                            DatagramPacket posicion = new DatagramPacket(Enviapos, Enviapos.length, hostServidor,
                                               puertoServer);
                            EnviarDatos.send(posicion);
                            interfaz.TextAreaLog.append("AVISAR A -----> "+ObtenerPuerto+"\n");
                            interfaz.TextAreaLog.append("Se envio la petición\n");
                        }
                        catch(Exception ex){
                            System.out.println("Error Send mesage");
                        }
                    }
                    else{
                        try{
                            interfaz.TextAreaLog.append("FIN DE LA BUSQUEDA\n");
                            interfaz.TextAreaLog.append("ARCHIVO NO DISPONIBLE\n");
                        }
                        catch(Exception ex){
                            System.out.println("Error Send mesage");
                        }
                    }
                }
            }
            Thread.sleep(5000);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
