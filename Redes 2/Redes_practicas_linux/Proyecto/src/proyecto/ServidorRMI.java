package proyecto;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.io.*;
import java.security.MessageDigest;

public class ServidorRMI extends Thread implements BuscarRMI{
    private int pto;
    private String folder;
    
    public ServidorRMI(int pto, String folder){
        this.pto = pto;
        this.folder = folder;
    }
    
    public byte[] createChecksum(String filename) throws Exception {
       InputStream fis =  new FileInputStream(filename);

       byte[] buffer = new byte[1024];
       MessageDigest complete = MessageDigest.getInstance("MD5");
       int numRead;

       do {
           numRead = fis.read(buffer);
           if (numRead > 0) {
               complete.update(buffer, 0, numRead);
           }
       } while (numRead != -1);

       fis.close();
       return complete.digest();
   }

   // see this How-to for a faster way to convert
   // a byte array to a HEX string
   public String getMD5Checksum(String filename) throws Exception {
       byte[] b = createChecksum(filename);
       String result = "";

       for (int i=0; i < b.length; i++) {
           result += Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
       }
       return result;
   }
    
    public String[] buscar(String nombre) throws RemoteException{
        String path = "src/proyecto";//System.getProperty("user.home") + System.getProperty("file.separator")+ 
                //folder + System.getProperty("file.separator");
        System.out.println("Buscando en: " + path);
        String name = "-1", md5 = "-1";
        long tam = 0;
        File carpeta = new File(path);
        
        File[] lista = carpeta.listFiles();
        
        System.out.println("Buscando archivo " + nombre);
        
        for(int i = 0; i < lista.length; i++){
            if(lista[i].isFile()){
                if(nombre.equals(lista[i].getName())){
                    name = lista[i].getName();

                    try{
                        md5 = getMD5Checksum(path+name);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    
                    tam = lista[i].length();
                    break;
                }
            }
        }
        
        if(name.equals("-1") && md5.equals("-1"))
            System.out.println("No se encontró el archivo");
        else
            System.out.println("Archivo encontrado");
        
        return new String[]{name, md5, path, Long.toString(tam)};
    }
    
    public void run(){
        try{
            java.rmi.registry.LocateRegistry.createRegistry(pto);
            System.out.println("RMI registry listo");
        }catch(Exception e){
            System.err.println("Excepción del rmiregistry:");
            e.printStackTrace();
        }
        try{
            ServidorRMI obj = new ServidorRMI(pto, folder);
            
            BuscarRMI stub = (BuscarRMI)UnicastRemoteObject.exportObject(obj, 0);
            
            Registry registry = LocateRegistry.getRegistry(pto); // Se debe especificar el puerto cuando es
                                                                 // distinto del predeterminado (1099)
            registry.bind("BuscarRMI", stub);
            System.out.println("Servidor RMI listo...");
        }catch(Exception e){
            System.err.println("Excepción del servidor: " + e.toString());
            e.printStackTrace();
        }
    }
}
