/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flujo;

/**
 *
 * @author Maurcio10
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
//import Suma.*;
	
public class Servidor extends Thread implements Suma{
	
    
    private ArrayList<Integer> lengths;
    private int puerto;
    
    public Servidor(int p){
        this.puerto = p;
    }
    public ArrayList< byte[] > archivo(String FILE_NAME ,int cuantos,int idx) {
        String path = "../"+ Integer.toString(puerto) + '/' + FILE_NAME;
        int PART_SIZE = 5;
        File inputFile = new File(path);
        FileInputStream inputStream;
        String newFileName;
	int fileSize = (int) inputFile.length();
	int nChunks = 0, read = 0, readLength = PART_SIZE;
	byte[] byteChunkPart;
        ArrayList< byte[] > partes = new ArrayList<byte[]>();
	try {
            inputStream = new FileInputStream(inputFile);
            while (true) {
                if(fileSize <= 0){
                    System.out.println(partes.size());
                    System.out.println(fileSize);
                    inputStream.close();
                    int tmp = partes.size()/cuantos;
                    int maxi = idx*tmp+tmp;
                    if(cuantos == idx+1)maxi = partes.size();
                  
                    ArrayList< byte[] > res = new ArrayList<byte[]>();
                    System.out.println(idx + ":D");
                    for(int i = idx*tmp; i < maxi; i++){
                        //System.out.println(i);
                        res.add(partes.get(i));
                    }
                    return res;
                }
                if (fileSize <= PART_SIZE) {
                    readLength = fileSize;
		}
		byteChunkPart = new byte[readLength];
		read = inputStream.read(byteChunkPart, 0, readLength);
                
                partes.add(byteChunkPart);
		fileSize -= read;
		nChunks++;
		newFileName = FILE_NAME + ".part"
				+ Integer.toString(nChunks - 1);
                //System.out.println(newFileName);
                //System.out.println(partes.size());
                byteChunkPart = null;
            }
            //System.out.println(parts.size() + ":D");
            
	}catch (IOException exception) {
            System.out.println("ASD");
            exception.printStackTrace();
	}
        return partes;
    }
    /*Metodo para retornar un BufferedReader o algo */
    public void run() {
	try {
               //puerto default del rmiregistry
               java.rmi.registry.LocateRegistry.createRegistry(1099); 
               System.out.println("RMI registro listo.");
	} catch (Exception e) {
               System.out.println("Excepcion RMI del registry:");
                e.printStackTrace();
           }//catch
        try {
                System.setProperty("java.rmi.server.codebase",
				      "file:/c:/Temp/Suma/");
	    Servidor obj = new Servidor(this.puerto);
	    Suma stub =  (Suma) UnicastRemoteObject.exportObject(obj, 0);
	    // Ligamos el objeto remoto en el registro
	    Registry registry = LocateRegistry.getRegistry();
	    registry.bind("Suma", stub);

	    System.err.println("Servidor listo...");
	} catch (Exception e) {
	    System.err.println("Excepción del servidor: " +
					 e.toString());
	    e.printStackTrace();
	}
      }
}


