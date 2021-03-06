import java.io.*;
import java.net.*;

public class SArchTCPB{
    public static void main(String[] args) {
        try{
            //Inicializacion de dos y dis
            DataOutputStream dos = null;
            DataInputStream dis = null;
            String nombre = null;
            String aux = null;
            
            //Creación del server socket
            ServerSocket s = new ServerSocket(7000);
            
            //Ciclo infinito del server
            for(;;){
                int cantidad = 1;
                //int i = 0;

                //Creación del socket
                Socket cl = s.accept();

                //Conexion con el cliente
                System.out.println("Conexión establecida desde:" + cl.getInetAddress() + ":" + cl.getPort());

                BufferedReader br2 = new BufferedReader(new InputStreamReader(cl.getInputStream()));
                aux = br2.readLine();
                cantidad = Integer.parseInt(aux);
                System.out.println("Numero de archivos: " + cantidad);
                
                for(int i = 0; i < cantidad; i++){
                    //Flujo de nivel de bits de entrada ligada al socket
                    //DataInputStream 
                    dis = new DataInputStream(cl.getInputStream());
                    //Se leen los datos principales del archivo y se crea un flujo de salida para el archivo
                    byte[] b = new byte[1024];
                    nombre = dis.readUTF();
                    System.out.println("Recibimos el archivo: " + nombre);
                    long tam = dis.readLong();
                    //DataOutputStream
                    dos = new DataOutputStream(new FileOutputStream(nombre));

                    //Preparacion de los datos para recibir los paquetes de datos del archivo
                    long recibidos = 0;
                    int n, porcentaje = 0;

                    //Ciclo receptor de datos enviados por el cliente
                    while(recibidos < tam){
                        //n = dis.read(b); tam -recibidos < 1024 o > 1024
                        n = dis.read(b,0,Math.min(1024, (int)(tam - recibidos)));
                        dos.write(b,0,n);
                        dos.flush();
                        recibidos = recibidos + n;
                        porcentaje = (int)(recibidos * 100 / tam);
                    }
                    System.out.print("\tArchivo recibido: " + porcentaje + "%\n");
                    dos.close();
                }
                dis.close();
                cl.close();
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}