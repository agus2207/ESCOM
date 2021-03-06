import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Suma extends Remote{
    //Aqui se implementa las operaciones que se pueden realizar
    //con sus respectivos parametros
    int suma(int a, int b) throws RemoteException;
}