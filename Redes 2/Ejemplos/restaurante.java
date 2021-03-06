import java.util.concurrent.Semaphore;

public class restaurante{
    private Semaphore mesas;

    public restaurante(int contadorMesas){
        //Crear un semaforo con las mesas
        this.mesas = new Semaphore(contadorMesas);
    }

    public void obtenerMesa(int idCliente){
        try {
            System.out.println("Cliente #"+idCliente+" esta intentando obtener mesa");
            //Obtiene permiso para usar una mesa
            mesas.acquire();
            System.out.println("Cliente #"+idCliente+ " consiguio una mesa");    
        } 
        catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    public void regresarMesa(int idCliente){
        System.out.println("Cliente #"+idCliente+" devolvio la mesa");
        mesas.release();
    }

    public static void main(String[] args){
        restaurante r = new restaurante(2);
        for(int i = 1; i <= 5; i++){
            Cliente c = new Cliente(r, i);
            c.start();
        }
    }
}