package examen_topologia;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author agust
 */
public class FlowServer {
    
    public static String host;
    public static int pto;
    public static String Archivo;
    
    public FlowServer(){
        
    }
    
    public static void main(String[] args){
        try{
            ServerSocket s = new ServerSocket(9100);
            for(;;){
                Socket cl = s.accept();
                DataInputStream dis = new DataInputStream(cl.getInputStream());
                String mensaje = dis.readUTF();
                if(mensaje.equals("1")){
                     //Flujo de archivo de entrada
                    byte[] b = new byte[1024]; //Paquetes de 1024 bytes
                    String nombre = dis.readUTF();
                    System.out.println("Recibimos el archivo "+nombre);
                    long tam = dis.readLong();
                    DataOutputStream dos = new DataOutputStream(new FileOutputStream("src/examen_topologia/Disponible/"+nombre)); //Flujo de archivo de salida
                    //Seccion para recibir el archivo
                    long recibidos = 0;
                    int n, porcentaje;
                    while(recibidos<tam){
                        n=dis.read(b);
                        dos.write(b, 0, n);
                        dos.flush();
                        recibidos = recibidos+n;
                        porcentaje = (int)(recibidos*100/tam);
                        //System.out.println("Recibido "+porcentaje+"%\r");
                    }
                    System.out.println("\n\nArchivo Recibido. \n");
                    dos.close();
                    dis.close();
                    cl.close();
                }
                
                else if(mensaje.equals("2")){
                    String archivo = dis.readUTF();
                    System.out.println("Archivo solicitado: "+archivo);
                    File f = new File("src/examen_topologia/Disponible/"+archivo);
                    String ruta = f.getAbsolutePath();
                    String nombre = f.getName();
                    long tam = f.length();
                    DataOutputStream dos = new DataOutputStream(cl.getOutputStream());
                    DataInputStream envio = new DataInputStream(new FileInputStream(ruta));
                    dos.writeUTF(nombre);
                    dos.flush();
                    dos.writeLong(tam);
                    dos.flush();
                    //Seccion para el envio del archivo
                    byte[] b = new byte[1024];
                    long enviados = 0;
                    int porcentaje, n;
                    while(enviados < tam){
                        n = envio.read(b);
                        dos.write(b,0,n);
                        dos.flush();
                        enviados = enviados + n;
                        porcentaje = (int)(enviados*100/tam);
                        //System.out.println("Enviado: "+porcentaje+"%\r");
                    }
                    System.out.print("\n\nArchivo enviado. ");
                    envio.close();
                    dos.close();
                    dis.close();
                    cl.close();
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
