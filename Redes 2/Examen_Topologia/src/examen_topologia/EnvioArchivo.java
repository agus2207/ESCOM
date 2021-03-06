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
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author agust
 */
public class EnvioArchivo extends Thread{
    
    public int puerto;
    public InetAddress address;
    public InterfazTopologi interfaz;
    public String msj;
    
    public EnvioArchivo(InetAddress a, String mensaje, int port, InterfazTopologi i){
        address = a;
        msj = mensaje;
        puerto = port;
        interfaz = i;
    }
    
    public void run(){
        try {
            Socket cl = new Socket(address,9100);
            String mensaje = "1";
            DataOutputStream dos = new DataOutputStream(cl.getOutputStream());
            dos.writeUTF(mensaje);
            dos.flush();
            File f1 = new File("src/"+puerto+"/"+msj);
            String archivo = f1.getAbsolutePath();
            String nombre = f1.getName();
            long tam = f1.length();
            //DataOutputStream dos = new DataOutputStream(cl.getOutputStream());
            DataInputStream dis = new DataInputStream(new FileInputStream(archivo));
            dos.writeUTF(nombre);
            dos.flush();
            dos.writeLong(tam);
            dos.flush();
            //Seccion para el envio del archivo
            byte[] b = new byte[1024];
            long enviados = 0;
            int porcentaje, n;
            interfaz.Progresoenvio.setValue(0);
            interfaz.Progresoenvio.setMinimum(0);
            interfaz.Progresoenvio.setMaximum(100);
            while(enviados < tam){
                n = dis.read(b);
                dos.write(b,0,n);
                dos.flush();
                enviados = enviados + n;
                porcentaje = (int)(enviados*100/tam);
                interfaz.Progresoenvio.setValue(porcentaje);
                //System.out.println("Enviado: "+porcentaje+"%\r");
            }   
            System.out.print("\n\nArchivo enviado. ");
            dos.close();
            dis.close();
            cl.close();
            JOptionPane.showMessageDialog(null, "Archivo enviado correctamente\nListo para descargar");
        } 
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
