import java.io.*;

class Consumidor extends Thread{
    private double promedio_anterior = 0;
    private DataInputStream entrada;
    
    public Consumidor(InputStream is){
        entrada = new DataInputStream(is);
    }

    public void run(){
        for(;;){
            try {
                double prom = entrada.readDouble();
                if(Math.abs(prom-promedio_anterior) > .01){
                    System.out.println("El promedio actual es: "+prom);
                    promedio_anterior = prom;
                }    
            } 
            catch (IOException io) {
                io.printStackTrace();
            }
        }
    }
}