import java.io.*;

public class Tuberias{

    public static void main(String[] args){
        try{
            PipedOutputStream po1 = new PipedOutputStream();
            PipedInputStream pi1 = new PipedInputStream(po1);
            PipedOutputStream po2 = new PipedOutputStream();
            PipedInputStream pi2 = new PipedInputStream(po2);
            Productor p = new Productor(po1);
            Filtro f = new Filtro(pi1, po2);
            Consumidor c = new Consumidor(pi2);
            p.start();
            f.start();
            c.start();
        } 
        catch (IOException io){
            io.printStackTrace();
        }
    }
}