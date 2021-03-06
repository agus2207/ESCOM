import java.io.*;
import java.util.*;

class Productor extends Thread{
    private DataOutputStream salida;
    private Random aleatorio = new Random();

    public Productor(OutputStream os){
        salida = new DataOutputStream(os);
    }

    public void run(){
        while(true){
            try{
                double num = aleatorio.nextDouble();
                salida.writeDouble(num);
                salida.flush();
                sleep(Math.abs(aleatorio.nextInt())%1000);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}

class Filtro extends Thread{
    private DataInputStream entrada;
    private DataOutputStream salida;
    private double total = 0;
    private int cuenta = 0;

    public Filtro(InputStream is, OutputStream os){
        entrada = new DataInputStream(is);
        salida = new DataOutputStream(os);
    }

    public void run(){
        for(;;){
            try{
                double x = entrada.readDouble();
                total += x;
                cuenta++;
                if(cuenta != 0){
                    salida.writeDouble(total/cuenta);
                    salida.flush();
                }
            } 
            catch (IOException io) {
                io.printStackTrace();
            }
        }
    }
}