import java.net.*;
import java.io.*;

//Recordar que para hacerlo bidireccional necesitamos definir dos distintos puertos en cada lado 
//Convertir el ejemplo en un eco bidireccional
//Considerar mensajes grandes (20) en varios paquetes

public class SEcoUDPB{
    public static void main(String [] args) throws IOException{
        try{
            DatagramSocket s= new DatagramSocket(2000);
            System.out.println("Servidor iniciado");
            while(true){
                DatagramPacket p= new DatagramPacket(new byte[20], 20); //Creamos un buffer de lectura de 20 bytes
                s.receive(p); //Bloqueo
                System.out.println("Datagrama desde "+p.getAddress()+": "+p.getPort());
                String msj= new String(p.getData(), 0 , p.getLength()); // Obtenemos una String que contiene en num
                //de paquetes que vamos a recibir
                System.out.println("Voy a recibir: "+msj+" paquetes en total");
                
                int n= Integer.parseInt(msj); // Parseamos a int el numero de paquetes a recibir
                String mensaje_completo=""; // String que va a contener la cadena completa
                for(int i=0; i<n; i++){ // ciclo for para recibir n paquetes
                    s.receive(p); // Recibimos cada paquete
                    mensaje_completo+= new String(p.getData(), 0 , p.getLength()); // Agregamos este paquete a la String
                    System.out.println("Parte del mensaje: "+new String(p.getData(), 0 , p.getLength()));
                }
                System.out.println("El mensaje completo es: "+mensaje_completo); //Imprimimos la String completa
                
                // Fin de la recepcion de datos
                
                // Inicio del envio de datos al cliente ***** Esta parte se encuentra documentada en el cliente *****
               
                BufferedReader br= new BufferedReader(new InputStreamReader (System.in));
		String mensaje= br.readLine();
		byte[]b= mensaje.getBytes();
                String dst= "localhost";
                DatagramSocket cl= new DatagramSocket();
		int pto= 7000;                
                int longitud=mensaje.length();
                int cantidad_paquetes= longitud/20;
                cantidad_paquetes+= (longitud - cantidad_paquetes * 20 > 0) ? 1 : 0;
                String paquetes= String.valueOf(cantidad_paquetes);
                byte[] pa= paquetes.getBytes();
                           
		DatagramPacket p_= new DatagramPacket(pa, pa.length, InetAddress.getByName(dst), pto);
		cl.send(p_);                
                int inicio=0;
                int fin;
                
                for(int i=0; i<cantidad_paquetes; i++){                    
                    fin=Math.min(20, longitud);
                    DatagramPacket paquetes_enviar= new DatagramPacket(b, inicio, fin, InetAddress.getByName(dst), pto);
                    cl.send(paquetes_enviar);
                    if(longitud>=20){
                        inicio+=20;
                        longitud-=20;
                    }
                }               
                cl.close();           
            }
        }catch(Exception e){e.printStackTrace();}
    }
}