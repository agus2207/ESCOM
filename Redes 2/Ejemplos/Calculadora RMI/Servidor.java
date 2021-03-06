import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Servidor implements Suma{

    public Servidor(){}

    public int suma(int a, int b){
        return a+b;
    }

    public static void main(String[] args){
        try {
            //Puerto por defecto del rmiRegistry
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            System.out.println("RMI registro listo");
        } 
        catch (Exception e) {
            System.out.println("Excepcion RMI del registry: ");
            e.printStackTrace();
        }
        try {
            System.setProperty("java.rmi.server.codebase", "file:/c:/Temp/Suma/");
            Servidor obj = new Servidor();
            Suma stub = (Suma)UnicastRemoteObject.exportObject(obj, 0);
            //Ligamos el objeto remoto en el registro
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("Suma", stub); 
            System.err.println("Servidor listo...");   
        } 
        catch (Exception e) {
            System.err.println("Excepcion del servidor: "+e.toString());
            e.printStackTrace();
        }
    }
}