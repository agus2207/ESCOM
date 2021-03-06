/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen_topologia;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 *
 * @author agust
 */
public class ClienteMulticast extends Thread{
    
    public MulticastSocket clienteMul;
    public int puerto;
    public InetAddress address;
    public InterfazTopologi interfaz;
    
    public ClienteMulticast(int p, InetAddress a,InterfazTopologi i)
    {
        puerto = p;
        address = a;
        interfaz = i;
    }

    public void run()
    {
        ArrayList<String> NodosRecibidos = new ArrayList();
        InetAddress grupo = null;
        try{
            clienteMul = new MulticastSocket(4001);
            System.out.println("Cliente escuchando en el puerto "+ clienteMul.getLocalPort());
            clienteMul.setReuseAddress(true);
            try{
                grupo = InetAddress.getByName("228.1.1.1");
            }catch(UnknownHostException u){
                System.err.println("Direccion no valida");
            }//catch
            clienteMul.joinGroup(grupo);
            System.out.println("Se unio al grupo: "+grupo);            
            while(true)
            {              
                NodosRecibidos.clear();
                String cadena = address+":"+puerto;
                //Se envia la IP
                DatagramPacket dp = new DatagramPacket(cadena.getBytes(), cadena.length(), InetAddress.getByName("228.1.1.1"), 4000);
                clienteMul.send(dp); 
                String envio = new String(dp.getData(),dp.getOffset(),dp.getLength());
                System.out.println("Se envio: "+envio);            
                //Se reciben los nodos
                byte[] bytes = new byte[500];
                DatagramPacket dprec = new DatagramPacket(bytes,bytes.length);
                clienteMul.receive(dprec);
                byte [] bufferDatos = new byte [dprec.getLength()];
                System.arraycopy(dprec.getData(),0,bufferDatos,0,dprec.getLength());
                String Datos = new String(dprec.getData(),dprec.getOffset(),dprec.getLength());
                System.out.println("Recibido: "+Datos);
                ByteArrayInputStream bais = new ByteArrayInputStream(bufferDatos);
                DataInputStream in = new DataInputStream(bais);
                while (in.available() > 0) {
                    String element = in.readUTF();
                    NodosRecibidos.add(element);
                }
                System.out.println(NodosRecibidos);
                for(int i = 0;i<NodosRecibidos.size();i++)
                {
                    if(NodosRecibidos.size()!=1 && NodosRecibidos.get(i).equals(envio))
                    {
                        if(i==NodosRecibidos.size()-1)
                        {
                            interfaz.TFieldSiguiente.setText(NodosRecibidos.get(0));
                            interfaz.TxtFieldAnterior.setText(NodosRecibidos.get(i-1));
                        }
                        else if(i == 0)
                        {
                            interfaz.TFieldSiguiente.setText(NodosRecibidos.get(i+1));
                            interfaz.TxtFieldAnterior.setText(NodosRecibidos.get(NodosRecibidos.size()-1));
                        }
                        else{
                            interfaz.TFieldSiguiente.setText(NodosRecibidos.get(i+1));
                            interfaz.TxtFieldAnterior.setText(NodosRecibidos.get(i-1));
                        }
                    }
                    else if(NodosRecibidos.size()==1 && NodosRecibidos.get(i).equals(envio))
                    {
                        interfaz.TFieldSiguiente.setText(NodosRecibidos.get(i));
                        interfaz.TxtFieldAnterior.setText(NodosRecibidos.get(i));
                    }
                }
                interfaz.AreaDeNodos.setText("");
                System.out.println("Nodo:"+NodosRecibidos.get(0));
                for(int i=0;i<NodosRecibidos.size();i++)
                    interfaz.AreaDeNodos.append(""+NodosRecibidos.get(i)+"\n");
                interfaz.TxtDisponible.setText(""+NodosRecibidos.size());
                Thread.sleep(5000);
            }
        }catch(Exception e){
            System.out.println("Excepcion en el cliente -> "+e.getMessage());
            e.printStackTrace();
        }
    }
}
