/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flujo;


import datagrama.DatagramServidor;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import javafx.util.Pair;

public class Cliente extends Thread{
    private String name;
    private String host;
    private int cuantos;
    private int pos;
    private DatagramServidor ds;
    private ArrayList<byte[]> parts;
    public Cliente(String h,String name,int cuantos,int pos,DatagramServidor ds) {
        this.name = name;
        host = h;
        this.cuantos = cuantos;
        this.pos = pos;
        this.ds = ds;
        parts = new ArrayList<byte[]>();
    }
    public void run(){
	try {
	    Registry registry = LocateRegistry.getRegistry(host);	
            //también puedes usar getRegistry(String host, int port)
            Suma stub = (Suma) registry.lookup("Suma");
            parts = stub.archivo(name,cuantos,pos);
            Pair<Integer,ArrayList<byte[]> > asd = new Pair<Integer,ArrayList<byte[]>>(pos,parts);
            this.ds.parts.add(asd);
            System.out.println(parts.size());
	} catch (Exception e) {
	    /*System.err.println("Excepción del cliente: " +
						 e.toString());
	    e.printStackTrace();*/
	}
    }
}