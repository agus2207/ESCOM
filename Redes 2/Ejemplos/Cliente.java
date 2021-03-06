import java.util.Random;

class Cliente extends Thread{
    private restaurante r;
    private int idCliente;
    private static final Random aleatorio = new Random();

    public Cliente(restaurante r, int idCliente){
        this.r = r;
        this.idCliente = idCliente;
    }

    public void run(){
        r.obtenerMesa(this.idCliente);
        try{
            //Come durante un tiempo aleatorio
            int tiempoComida = aleatorio.nextInt(30)+1;
            System.out.println("Cliente #"+this.idCliente+" comera por: "+tiempoComida+" segundos");
            Thread.sleep(tiempoComida*1000);
            System.out.println("Cliente #"+this.idCliente+" termino de comer");
        } 
        catch (InterruptedException ie){
            ie.printStackTrace();
        }
        finally{
            r.regresarMesa(this.idCliente);
        }
    }
}