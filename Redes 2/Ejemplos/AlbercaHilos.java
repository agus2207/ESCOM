import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AlbercaHilos{
    public static void main(String[] args){
        System.out.println("Comienza la ejecucion");
        ExecutorService ex = Executors.newFixedThreadPool(10);
        TareaAlbercaHilos t;
        for(int i = 0; i < 200; i++){
            t = new TareaAlbercaHilos(""+i);
            ex.execute(t); //iguala a t start();
        }
        ex.shutdown();
    }
}