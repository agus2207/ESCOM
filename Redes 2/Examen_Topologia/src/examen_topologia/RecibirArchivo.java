/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen_topologia;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author agust
 */
public class RecibirArchivo extends Thread{
    
    public int puerto;
    public InetAddress address;
    public InterfazTopologi interfaz;
    public String msj;
    
    public RecibirArchivo(InetAddress a, String mensaje, int port, InterfazTopologi i){
        address = a;
        msj = mensaje;
        puerto = port;
        interfaz = i;
    }
    
    public void run(){
        try{
            Socket cl = new Socket(address,9100);
            String mensaje = "2";
            DataOutputStream dos = new DataOutputStream(cl.getOutputStream());
            dos.writeUTF(mensaje);
            dos.flush();
            dos.writeUTF(msj);
            dos.flush();
            DataInputStream dis = new DataInputStream(cl.getInputStream());
            byte[] b = new byte[1024]; //Paquetes de 1024 bytes
            String nombre = dis.readUTF();
            System.out.println("Recibi esto: "+nombre);
            long tam = dis.readLong();
            DataOutputStream recive = new DataOutputStream(new FileOutputStream("src/"+puerto+"/"+nombre)); //Flujo de archivo de salida
            long recibidos = 0;
            int n, porcentaje;
            interfaz.Progreso.setValue(0);
            interfaz.Progreso.setMinimum(0);
            interfaz.Progreso.setMaximum(100);
            while(recibidos<tam){
                n=dis.read(b);
                recive.write(b, 0, n);
                recive.flush();
                recibidos = recibidos+n;
                porcentaje = (int)(recibidos*100/tam);
                interfaz.Progreso.setValue(porcentaje);
                //System.out.println("Recibido "+porcentaje+"%\r");
            }
            System.out.println("\n\nArchivo Recibido. \n");
            recive.close();
            dos.close();
            dis.close();
            cl.close();
            JOptionPane.showMessageDialog(null, "Archivo descargado correctamente");

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
