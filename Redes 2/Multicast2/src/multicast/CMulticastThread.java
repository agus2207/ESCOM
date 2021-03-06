/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multicast;


import static java.lang.Math.pow;
import java.net.*;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.Collections;
import javafx.util.Pair;
import java.util.Comparator;

public class CMulticastThread extends Thread{
    private ArrayList<Pair<Integer,String>> list;
    private boolean[] vivos;
    private int anterior;
    private int siguiente;
    private int myport;

    public CMulticastThread(int myport){
        list = new ArrayList<>();
        this.myport = myport;
        vivos = new boolean[10];
    }
    
    public int getAnterior() {
        return anterior;
    }

    public int getSiguiete() {
        return siguiente;
    }
    
    public void restartVivos(){
        for(int i = 0; i<10; i++) vivos[i] = false;
    }

    public boolean[] getVivos() {
        return vivos;
    }
    public void eraseNode(int id){
            list.remove(id);
            for(int i = 0; i<list.size(); i++){
                if(myport == list.get(i).getKey()){
                    int idxa, idxs;
                    idxa = i - 1; idxs = (i+1)%list.size();
                    if(idxa < 0) idxa = list.size() - 1;
                    siguiente = list.get(idxs).getKey();
                    anterior = list.get(idxa).getKey();
                }
                //System.out.println(list.get(i).getKey());
            }
    }
    public ArrayList<Pair<Integer, String>> getList() {
        return list;
    }
    @Override
    public void run(){
        InetAddress gpo=null;
        try{
            MulticastSocket cl= new MulticastSocket(9999);
            //System.out.println("Cliente escuchando puerto "+ cl.getLocalPort());
            cl.setReuseAddress(true);
            try{
                gpo = InetAddress.getByName("224.0.0.1");
            }catch(UnknownHostException u){
                //System.err.println("Direccion no valida");
            }//catch
            cl.joinGroup(gpo);
            //System.out.println("Unido al grupo");
            for(;;){
                DatagramPacket p = new DatagramPacket(new byte[10],10);
                cl.receive(p);
                String msj = new String(p.getData());
                int portData = 0;
                for(int i = 0; i<4; i++){
                    int dig = msj.charAt(i) - '0';
                    portData += dig * pow(10,4-i-1);
                }
                String addr = p.getAddress().toString();
                String id = msj + addr;
                //System.out.println("Puerto recibido.."+msj);         
                /*System.out.println("Servidor descubierto: " + p.getAddress()+""
                        + " Identificador: "
                        +id);*/
                boolean noesta = true;
                for(int i = 0; i<list.size(); i++){
                    if(list.get(i).getKey() == portData){ noesta = false; break;}
                }
                if(noesta){
                    Pair<Integer,String> pair = new Pair<>(portData,id);
                    list.add(pair);
                    Comparator <Pair<Integer, String>> mycomp = new Comparator <Pair<Integer, String>>(){
                        @Override
                        public int compare(Pair<Integer, String> p1, Pair<Integer, String> p2) {
                            return p1.getValue().compareTo(p2.getValue());
                        } 
                    };
                    Collections.sort(list, mycomp);
                } 
                for(int i = 0; i<list.size(); i++){
                    if(list.get(i).getKey() == portData){
                        vivos[portData - 9000] = true;
                        break;
                    }
                }
                for(int i = 0; i<list.size(); i++){
                    if(myport == list.get(i).getKey()){
                        int idxa, idxs;
                        idxa = i - 1; idxs = (i+1)%list.size();
                        if(idxa < 0) idxa = list.size() - 1;
                        siguiente = list.get(idxs).getKey();
                        anterior = list.get(idxa).getKey();
                    }
                    //System.out.println(list.get(i).getKey());
                }
                 /*System.out.println("Nodo anterior : "+anterior+" Nodo Sig"
                            + "uiente: "+siguiente);*/
                
            }//for 
        }catch(Exception e){
            e.printStackTrace();
        }//catch
    }//main
}
