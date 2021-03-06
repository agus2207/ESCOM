/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datagrama;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Maurcio10
 */
public class Prueba {
    private static ArrayList<String> obtenerGpo(ArrayList<String> md5s,ArrayList<String> direcciones) {
        ArrayList<ArrayList<String>> gps = new ArrayList<>();
        
        ArrayList<String> uniqueMd5 = new ArrayList<>();
        uniqueMd5.add(md5s.get(0));
        
        ArrayList<String> newGpo  = new ArrayList<>();
        newGpo.add(direcciones.get(0));
        gps.add(newGpo);
        
        for(int i = 1; i < md5s.size(); i++){
            boolean anadido = false;
            for(int j = 0; j < uniqueMd5.size(); j++){
                if(uniqueMd5.get(j).equals(md5s.get(i))){
                    anadido = true;
                    gps.get(j).add(direcciones.get(i));
                    break;
                }
            }
            if(!anadido){
                uniqueMd5.add(md5s.get(i));
                newGpo  = new ArrayList<>();
                newGpo.add(direcciones.get(i));
                gps.add(newGpo);
            }
        }
        String msj = "Por favor eliga el grupo de servidores dónde desea descargar el programa";
        JOptionPane.showMessageDialog(null,msj);
        msj = "";
        for(int i = 0; i<gps.size(); i++){
            String numOp = String.valueOf(i+1);
            msj += numOp + ": ";
            for(int j = 0; j<gps.get(i).size();j++){
                if(j == gps.get(i).size() - 1){ msj += gps.get(i).get(j) + "\n"; break;} 
                msj += gps.get(i).get(j) + ", ";
            }
        }
        
        int op = Integer.parseInt(JOptionPane.showInputDialog(msj));
        return gps.get(op-1);
    }
    public static void main(String[] args) {
        ArrayList<String> md5s = new ArrayList<>();
        ArrayList<String> direcciones = new ArrayList<>();
        md5s.add("aaabbbb"); direcciones.add("1");
        md5s.add("aaabbb");direcciones.add("2");
        md5s.add("aabzzbb");direcciones.add("6");
        md5s.add("aaabbbb");direcciones.add("3");
        md5s.add("aabzbb");direcciones.add("4");
        md5s.add("aabzzbb");direcciones.add("5");
        ArrayList<String> res = obtenerGpo(md5s, direcciones);
        for(int i = 0; i<res.size(); i++) System.out.println(res.get(i));
        /*String id = "9000/192.168.2.1";
        String ip = "";
        boolean isIp = false;
        for(int i = 0; i<id.length();i++){
            if(isIp) ip += id.charAt(i); 
            if(id.charAt(i) == '/') isIp = true;
        }
        System.out.println(ip);*/
    }
 
}
