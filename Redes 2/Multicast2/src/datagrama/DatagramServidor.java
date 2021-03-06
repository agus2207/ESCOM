package datagrama;


import flujo.Cliente;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javafx.util.Pair;
import javax.swing.JOptionPane;
import javax.swing.plaf.RootPaneUI;
import multicast.CMulticastThread;
import multicast.Interfaz;

public class DatagramServidor extends Thread {
    private int puerto;
    private int puertoAnt;
    private int puertoSig;
    private boolean encontrado;
    private CMulticastThread objcMulticast;
    public ArrayList< Pair<Integer, ArrayList<byte[]> > > parts;
    public ArrayList< byte[]> partsfinal;

    public DatagramServidor(int puerto,int puertoAnt,int puertoSig,CMulticastThread objcMulticast) {
        this.puerto = puerto;
        this.puertoAnt = puertoAnt;
        this.puertoSig = puertoSig;
        this.objcMulticast = objcMulticast;
        encontrado = false;
        partsfinal = new ArrayList<byte[]>();
        parts = new ArrayList< Pair<Integer, ArrayList<byte[]> >>();
    }

    public boolean isEncontrado() {
        return encontrado;
    }

    public void setEncontrado(boolean encontrado) {
        this.encontrado = encontrado;
    }
    public void setPuerto(int puerto){
        this.puerto = puerto;
    }
    public void setPuertoAnt(int puerto){
        this.puertoAnt = puerto;
    }
    public void setPuertoSig(int puerto){
        this.puertoSig = puerto;
    }
    public int getPuerto(){
        return this.puerto;
    }
    public int getPuertoAnt(){
        return this.puertoAnt;
    }
    public int getPuertoSig(){
        return this.puertoSig;
    }
   
    public int esNumero(String str){
        int res;
        try{
            res = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return -1;
        }
        return res;
    }
    boolean tengoArchivo(String archivo){
        System.out.println("Buscando " + archivo);
        String path = "../" + Integer.toString(puerto);
        
        String file;
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles(); 

        for(int i = 0; i < listOfFiles.length; i++){
            if (listOfFiles[i].isFile()){
                file = listOfFiles[i].getName();
                //System.out.println(path + " " + file);
                if(file.equals(archivo) == true){
                
                    return true;
                }
            }
        }
        return false;
    }
    Pair<Pair<String,String>, Pair<String,String> > divide(String mensaje){
        Pair<Pair<String,String>,Pair<String,String> > res;
        String s1 = "",s2 = "",s3 = "",s4 = "";
        
        int found = 0;
        for(int i = 0; i < mensaje.length(); i++){
            if(mensaje.charAt(i) == '$'){
                found++;
            }else{
                if(found == 0)s1 += mensaje.charAt(i);
                else if(found == 1)s2 += mensaje.charAt(i);
                else if(found == 2)s3 += mensaje.charAt(i);
                else s4 += mensaje.charAt(i);
                
            }
        }
        Pair<String,String> tmp1 = new Pair<String,String>(s1,s2);
        Pair<String,String> tmp2 = new Pair<String,String>(s3,s4);
        res = new Pair< Pair<String,String>,Pair<String,String> >(tmp1,tmp2);
        return res;
    }
    /*La estructura del paquete es:
        NombreArchivo$PuertoEmisor$Direcciones$MD5s
            NombreaArchivo: String
            PuertoEmisor: Entero
            Direciones: String
            MD5s
    
            La estructura de Direcciones es:
                Ip:Puerto
            La estructura de MD5s es:
                MD5:MD5:MD5
    */
    //0:0:a:0
    ArrayList< Pair<String,String> > getDirecciones(String str){
        ArrayList< Pair<String,String> > res = new ArrayList< Pair<String,String> >();
        String s1 = "";
        String s2 = "";
        int cnt = 0;
        System.out.println(str);
        for(int i = 0; i < str.length(); i++){
            if(cnt == 1 && str.charAt(i) == ':'){
                Pair<String,String> tmp = new Pair<String,String>(s1,s2); 
                res.add(tmp);
                cnt = 0;
                s1 = "";
                s2 = "";
                continue;
            }
            if(str.charAt(i) == ':'){
                cnt++;
                continue;
            }
            if(cnt == 0)s1 += str.charAt(i);
            else s2 += str.charAt(i);
        }
        return res;
    }
    ArrayList< String > getMD5s(String str){
        ArrayList< String > res = new ArrayList< String >();
        String s1 = "";
        int cnt = 0;
        System.out.println(str);
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == ':'){
                res.add(s1);
                s1 = "";
                continue;
            }
            s1 += str.charAt(i);
        }
        if(s1.length()>= 1)res.add(s1);
        return res;
    }
    private ArrayList<String> obtenerGpo(ArrayList<String> md5s, ArrayList< Pair<String,String> >  direcciones) {
        ArrayList<ArrayList<String>> gps = new ArrayList<>();
        
        ArrayList<String> uniqueMd5 = new ArrayList<>();
        uniqueMd5.add(md5s.get(0));
        
        ArrayList<String> newGpo  = new ArrayList<>();
        newGpo.add(direcciones.get(0).getKey());
        gps.add(newGpo);
        
        for(int i = 1; i < md5s.size(); i++){
            boolean anadido = false;
            for(int j = 0; j < uniqueMd5.size(); j++){
                if(uniqueMd5.get(j).equals(md5s.get(i))){
                    anadido = true;
                    gps.get(j).add(direcciones.get(i).getKey());
                    break;
                }
            }
            if(!anadido){
                uniqueMd5.add(md5s.get(i));
                newGpo  = new ArrayList<>();
                newGpo.add(direcciones.get(i).getKey());
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
    private void copyFileUsingStream(File dest) throws IOException {
        FileOutputStream fos;
        try{
            fos = new FileOutputStream(dest,true);
            for(int i = 0; i < partsfinal.size(); i++){
                fos.write(partsfinal.get(i));
                fos.flush();
            }
            //fos = null;
            fos.close();
            
        }catch(Exception e){ System.out.println("Eroorrrr");}
    }
    
    @Override
    public void run(){
        try {
            DatagramSocket s = new DatagramSocket(puerto);
            System.out.println("Servidor con puerto " + puerto + " iniciado.");
            
            for (;;) {
                DatagramPacket p = new DatagramPacket(new byte[1000], 1000);
                s.receive(p);
                String msj = new String(p.getData(), 0, p.getLength());
                System.out.println("Datagrama recibido desde" + p.getAddress() + ":" + p.getPort() + " " + msj);
                
                //Interfaz.jTextArea1.append("Datagrama recibido desde" + p.getAddress() + ":" + p.getPort() + "\n");
                
                Pair< Pair<String,String>, Pair<String,String> > paquete = divide(msj);
                
                String archivo = paquete.getKey().getKey();
                String emi = paquete.getKey().getValue();
                ArrayList< Pair<String,String> >  direcciones = getDirecciones(paquete.getValue().getKey());
                ArrayList< String > md5s = getMD5s(paquete.getValue().getValue());
                
                /*for(int i = 0; i < direcciones.size(); i++){
                    System.out.println(direcciones.get(i).getKey()+ " : " + direcciones.get(i).getValue());
                                
                            }*/
                int emisor = esNumero(emi);
               
                DatagramCliente cliente;
                
                //Si yo soy el emisor
                if(emisor == puerto){
                    int tmp = esNumero(direcciones.get(0).getValue());
                    //int tmp = esNumero(puertoRes);
                    if(direcciones.size() > 1){
                        //Si recibi un puerto, se encontro el archivo en este puerto
                        // Ya podemos descargar el archivo
                        //System.out.println("Se encontro el archivo en " + puertoRes);
                            Interfaz.jTextArea1.append("Se encontro el archivo = " + archivo + " en:\n");                            
                            for(int i = 1; i < direcciones.size(); i++){
                                Interfaz.jTextArea1.append(direcciones.get(i).getKey()+ " : " + direcciones.get(i).getValue() +"\n");
                                
                            }
                            for(int i = 1; i < md5s.size(); i++){
                                Interfaz.jTextArea1.append(md5s.get(i)+'\n');
                            }
                            encontrado = true;
                            /*Este es el array de md5's y direcciones que usa el metodo, solo cambialo por tu arrayList :v*/
                            ArrayList<String> gpoServidores = obtenerGpo(md5s,direcciones);
                            
                            //10 10 10 10 10 
                            int cuantos = gpoServidores.size();
                            System.out.println("Se pedira a " + cuantos);
                            ArrayList<Cliente> asd = new ArrayList<>();
                            parts = new ArrayList<>();
                            partsfinal = new ArrayList<>();
                            
                            System.out.println(parts.size());
                            System.err.println(partsfinal.size());
                            for(int i = 0; i < cuantos; i++){
                                Cliente cli = new Cliente(gpoServidores.get(i),archivo,cuantos,i,this);
                                cli.run();
                                asd.add(cli);
                            }
                            /*for(int i = 0; i < cuantos; i++){
                                asd.get(i).wait();
                            }*/
                            Comparator <Pair<Integer, ArrayList<byte[]>>> mycomp = new Comparator <Pair<Integer, ArrayList<byte[]>>>(){
                                @Override
                                public int compare(Pair<Integer, ArrayList<byte[]>> p1, Pair<Integer, ArrayList<byte[]>> p2) {
                                    if(p1.getKey() < p2.getKey())return 1;
                                    return 0;
                                } 
                            };
                            Collections.sort(parts, mycomp);
                            for(int i = 0; i < parts.size(); i++){
                                //System.out.println(parts.get(i).getKey());
                                for(int j = 0; j < parts.get(i).getValue().size(); j++)partsfinal.add(parts.get(i).getValue().get(j));
                            }
                            String aasdasd = "../"+puerto+"/"+archivo;
                            copyFileUsingStream(new File(aasdasd));
                    }else{
                        // No encontramos el archivo
                        // Respuesta negativa
                        if(tmp == -2){
                            Interfaz.jTextArea1.append("No se encontro archivo = " + archivo + "\n");   
                        }else{
                            String miPuerto = Integer.toString(-2);
                            String nuevoMensage = archivo + '$' + emisor + '$' + "0:-2:" + '$' + '0';
                            String ip = findId(puertoAnt);
                            cliente = new DatagramCliente(nuevoMensage, puertoAnt,ip);
                            cliente.run();
                        }
                    }
                }else{
                    //int tmp = esNumero(puertoRes);
                    int tmp = esNumero(direcciones.get(0).getValue());
                    if(tmp == -2){ 
                        // respuesta negativa
                        Interfaz.jTextArea1.append("Mandando respuesta negativa de archivo = " + archivo + "  para atras hacia " + puertoAnt + "\n");                        
                        String ip = findId(puertoAnt);
                        cliente = new DatagramCliente(msj, puertoAnt,ip);
                        cliente.run();
                    }else{
                        if(tengoArchivo(archivo) == true){
                            Interfaz.jTextArea1.append("Tengo el archivo = " + archivo + ", propagando de " + puerto + " a " + puertoSig + "\n");
                            //System.out.println("Tengo el archivo mensaje de " + puerto + " a " + puertoAnt);
                            String miPuerto = Integer.toString(puerto);
                            String dirs = paquete.getValue().getKey();
                            //Agrego mi direccion
                            String host = findId(puerto);
                            System.out.println("Servidor enviando desde: "+host);
                            dirs += host+":"+miPuerto+":";
                            String path = "../" + Integer.toString(puerto)+ "/"+ archivo;
                            MD5Checksum ch = new MD5Checksum();
                            String md5 = paquete.getValue().getValue();
                            md5 += ":"+ch.getMD5Checksum(path);
                            String nuevoMensage = archivo + '$' + emisor + '$' + dirs + '$'+ md5;
                            String ip = findId(puertoSig);
                            cliente = new DatagramCliente(nuevoMensage, puertoSig,ip);
                            cliente.run();
                        }else{
                            //Si no lo tengo, que lo busquen adelante
                            Interfaz.jTextArea1.append("No tengo el archivo = " + archivo + " busca adelanta " + puertoSig + "\n");
                            //System.out.println("Mensaje de " + puerto + " a " + puertoSig);                            
                            cliente = new DatagramCliente(msj, puertoSig,findId(puertoSig));
                            cliente.run();
                        }
                    }
               
                }      
            }        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String findId(int target){
        String host = "";
        for(int i = 0; i<objcMulticast.getList().size(); i++){
            if(objcMulticast.getList().get(i).getKey() == target ){
                host = getIp(objcMulticast.getList().get(i).getValue());
            }
        }
        return host;
    }
    public String getIp(String id){
        String ip = "";
        boolean isIp = false;
        for(int i = 0; i<id.length();i++){
            if(isIp) ip += id.charAt(i); 
            if(id.charAt(i) == '/') isIp = true;
        }
        return ip;
    }

   
}
