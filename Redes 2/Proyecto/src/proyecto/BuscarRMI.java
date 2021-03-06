package proyecto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BuscarRMI extends Remote{
    
    String[] buscar(String nombre) throws RemoteException;
}
