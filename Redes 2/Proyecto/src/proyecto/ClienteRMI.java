package proyecto;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;

public class ClienteRMI{
    public ClienteRMI(){}
    
    public Object[][] busqueda(String nombre, ArrayList<String> dirs){
        Object[][] results = new Object[dirs.size()][6];
        int i = 0;
        
        Iterator<String> it = dirs.iterator();
        
        while(it.hasNext()){
            String[] partes = it.next().split(":");
            ManejadorRMI m = new ManejadorRMI(nombre, partes[0], Integer.parseInt(partes[1]));
            try{
                m.start();
                m.join();
                results[i] = m.getResult(); // Se llenan results[i][0], results[i][1], results[i][2], results[i][3]
                results[i][4] = partes[0];
                results[i][5] = partes[2];
            }catch(Exception e){
                e.printStackTrace();
            }
            
            i++;
        }
        
        return results; // Devuelve  una lista con los nombres de los archivos, los md5, las rutas donde fueron buscados,
                        // la dirección IP de los servidores, y los puertos de Flujo
    }
}

class ManejadorRMI extends Thread{
    private String cad, host;
    private int pto;
    private Object[] res;
    
    public ManejadorRMI(String cad, String host, int pto){
        this.cad = cad;
        this.host = host;
        this.pto = pto;
        res = new Object[6];
    }
    
    public void run(){
        try{
            Registry registry = LocateRegistry.getRegistry(host, pto);
            System.out.println("Conectado a " + host + ":" + pto);
            
            BuscarRMI stub = (BuscarRMI)registry.lookup("BuscarRMI");
            String response[] = stub.buscar(cad); // Obtiene el nombre del archivo, el md5, la ruta donde fue buscado y el tamaño
            
            if(!response[0].equals("-1") && !response[1].equals("-1"))
                System.out.println("Nombre: "+response[0]+" MD5: " + response[1]);
            
            for(int i = 0; i < response.length; i++)
                res[i] = response[i];
            
        }catch(Exception e){
            System.err.println("Excepción del cliente: " + e.toString());
            e.printStackTrace();
        }
    }
    
    public Object[] getResult(){
        return res; // Devuelve el nombre del archivo, el md5, la ruta donde fue buscado y el tamaño
    }
}