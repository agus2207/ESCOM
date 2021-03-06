public class TareaAlbercaHilos implements Runnable{
    private int sleepTime;
    private String name;
    
    public TareaAlbercaHilos(String name){
        this.name = name;
        sleepTime = 1000;
    }

    public void run(){
        try{
            System.out.println("Hilo: "+this.name+" duerme durante: "+sleepTime+" milisegundos");
            Thread.sleep(sleepTime);
        }
        catch(InterruptedException exception){
            exception.printStackTrace();
        }
        System.out.println("Este hilo ya a dormido bastante "+name);
    }
}