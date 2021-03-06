// CEcoUDPB.java
import java.net.*;
import java.io.*;

public class CEcoUDPB{
	public static void main(String [] args) throws IOException{
                String dst= "localhost";
                int pto= 2000;
		System.out.println("Cliente iniciado, escriba un mensaje");
		BufferedReader br= new BufferedReader(new InputStreamReader (System.in));
		String mensaje= br.readLine();
		byte[]b= mensaje.getBytes();
                
                DatagramSocket cl= new DatagramSocket();
                int longitud=mensaje.length(); //Obtenemos la longitud total del mensaje que vamos a enviar
                                
                int cantidad_paquetes= longitud/20; // Dividimos entre 20 para saber cuantos paquetes de 20 enviaremos
                cantidad_paquetes+= (longitud - cantidad_paquetes * 20 > 0) ? 1 : 0; /* Preguntamos si la longitud total de
                la cadena contiene mas caracteres que no alcanzan a formar un paquete de 20, ejemplo: 
                Tenemos una cadena de 26, entonces formamos un paquete de 20 y uno de 6. Esto significa que este 6 
                se debe enviar en otro paquete tambien */
                System.out.println(""+cantidad_paquetes);
                String paquetes= String.valueOf(cantidad_paquetes); /* Convertimos en cadena el numero de paquetes a enviar */
                byte[] pa= paquetes.getBytes();
                DatagramPacket p= new DatagramPacket(pa, pa.length, InetAddress.getByName(dst), pto); /*Le enviamos al servidor
                el numero de paquetes que va a recibir*/
                cl.send(p);

                int inicio=0; // Offset de nuestro mensaje
                int fin; // Numero de caracteres que vamos a enviar por paquete

                for(int i=0; i<cantidad_paquetes; i++){
                    //inicio=fin+1;                        
                    //fin+=Math.min(20, longitud);

                    fin=Math.min(20, longitud); // Obtenemos el minimo entre 20 y la longitud ACTUAL
                    //System.out.println("Voy enviar de "+inicio+" a");
                    //System.out.println(""+fin);

                    DatagramPacket paquetes_enviar= new DatagramPacket(b, inicio, fin, InetAddress.getByName(dst), pto);
                    cl.send(paquetes_enviar); /*Enviamos un paquete de 20 bytes o de menor tamanio*/

                    if(longitud>=20){ /* Si la longitud actual es mayor que 20 indica que podemos formar un paquete completo*/
                        inicio+=20; // El offset para enviar le sumamos 20
                        longitud-=20; /* La longitud actual la actualizamos y se le resta el tamanio  de un paquete completo*/
                    }

                }                                   
                // Fin del envio del mensaje
                
                //Inicio de la recepcion por parte del servidor ***** Esta parte se encuentra documentada en el servidor *****
                 
                DatagramSocket s= new DatagramSocket(7000);
                DatagramPacket p_= new DatagramPacket(new byte[20], 20);
                s.receive(p_); //Bloqueo
                
                String msj= new String(p_.getData(), 0 , p_.getLength());
                System.out.println("Voy a recibir: "+msj+" paquetes en total");
                
                int n= Integer.parseInt(msj);
                String mensaje_completo="";
                for(int i=0; i<n; i++){
                    s.receive(p_);
                    mensaje_completo+= new String(p_.getData(), 0 , p_.getLength());
                    System.out.println("Parte del mensaje: "+new String(p_.getData(), 0 , p_.getLength()));
                }
                System.out.println("El mensaje completo es: "+mensaje_completo);  
                cl.close();
	}
}
