package proyecto;

import java.net.*;
import java.io.*;

public class ServidorFlujo extends Thread{
    private int pto;
    
    public ServidorFlujo(int pto){
        this.pto = pto;
    }
    
    public void run(){
        try{
            ServerSocket s = new ServerSocket(pto);
            System.out.println("Servidor de flujo listo...");
            
            for(;;){
                Socket cl = s.accept();
                System.out.println("Cliente conectado");
                ManejadorFlujo m = new ManejadorFlujo(cl);
                m.start();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

class ManejadorFlujo extends Thread{
    private Socket cl;
    
    public ManejadorFlujo(Socket cl){
        this.cl = cl;
    }
    
    public void run(){
        try{
            DataInputStream dis = new DataInputStream(cl.getInputStream());
            DataOutputStream dos = new DataOutputStream(cl.getOutputStream());
            
            boolean multiple = dis.readBoolean();
            
            String ruta = dis.readUTF();
            File archivo = new File(ruta);
            String nombre = archivo.getName();
            
            if(!multiple){
                DataInputStream disf = new DataInputStream(new FileInputStream(archivo));
                
                long tam = archivo.length();

                dos.writeUTF(nombre);
                dos.flush();
                dos.writeLong(tam);
                dos.flush();

                byte[] b;
                long enviados = 0;
                int porcentaje, n;
                while(enviados < tam){
                    if(tam - enviados >= 1024){
                        b = new byte[1024];
                        n = disf.read(b);
                    }else{
                        int resta = (int)(tam-enviados);
                        b = new byte[resta];
                        n = disf.read(b);
                    }
                    dos.write(b,0,n);
                    dos.flush();
                    enviados = enviados + n;
                    porcentaje = (int)(enviados*100/tam);
                    //System.out.println("Enviado: " + porcentaje + "%\r");
                }                    
                System.out.println("Archivo enviado: " + nombre + "\n\n");
                disf.close();
            }else{                
                long offset = dis.readLong();
                long cant = dis.readLong();
                
                /*FileInputStream fis = new FileInputStream(archivo);
                fis.skip(offset);
                DataInputStream disf = new DataInputStream(fis);*/
                RandomAccessFile raf = new RandomAccessFile(archivo, "rw");
                
                dos.writeUTF(nombre);
                dos.flush();
                
                byte[] b;
                long enviados = 0;
                int porcentaje, n;
                while(enviados < cant){
                    raf.seek(offset);
                    if(cant - enviados >= 1024){
                        b = new byte[1024];
                        n = raf.read(b);
                    }else{
                        int resta = (int)(cant-enviados);
                        b = new byte[resta];
                        n = raf.read(b);
                    }
                    dos.write(b,0,n);
                    dos.flush();
                    offset += n;
                    enviados = enviados + n;
                    porcentaje = (int)(enviados*100/cant);
                    System.out.println("Enviado: " + porcentaje + "%\r");
                }
                raf.close();
            }
            
            dis.close();
            dos.close();
            cl.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
