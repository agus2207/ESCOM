import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Cliente{
    
    private Cliente(){}

    public static void main(String[] args){
        String host = (args.length < 1)?null:args[0];
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            //Tambien se puede usar getRegistry(host, port)
            Suma stub = (Suma)registry.lookup("Suma");
            int x = 5, y = 4;
            int response = stub.suma(x, y); //Manda a ejecutar
            System.out.println("respuesta sumar "+x+" y "+y+" : "+response); 
        } 
        catch (Exception e) {
            System.err.println("Excepcion del Cliente: "+e.toString());
            e.printStackTrace();
        }
    }
}