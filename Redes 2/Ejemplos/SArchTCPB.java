import java.net.*;
import java.io.*;

public class SArchTCPB{
    public static void main(String[] args){
        try{
            //Creamos el socket
            ServerSocket s = new ServerSocket(700);
            //Iniciamos el ciclo infinito
            for(;;){
                //Esperamos una conexion
                Socket cl = s.accept();
                System.out.println("Conexion establecida desde "+cl.getInetAddress()+":"+cl.getPort());
                DataInputStream dis = new DataInputStream(cl.getInputStream()); //Flujo de archivo de entrada
                byte[] b = new byte[1024]; //Paquetes de 1024 bytes
                String nombre = dis.readUTF();
                System.out.println("Recibimos el archivo "+nombre);
                long tam = dis.readLong();
                DataOutputStream dos = new DataOutputStream(new FileOutputStream(nombre)); //Flujo de archivo de salida
                //Seccion para recibir el archivo
                long recibidos = 0;
                int n, porcentaje;
                while(recibidos<tam){
                    n=dis.read(b);
                    dos.write(b, 0, n);
                    dos.flush();
                    recibidos = recibidos+n;
                    porcentaje = (int)(recibidos*100/tam);
                    System.out.println("Recibido "+porcentaje+"%\r");
                }
                System.out.println("\n\nArchivo Recibido. \n");
                dos.close();
                dis.close();
                cl.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}