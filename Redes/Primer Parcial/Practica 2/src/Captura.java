import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.*;

import org.jnetpcap.Pcap;
import org.jnetpcap.PcapAddr;
import org.jnetpcap.PcapIf;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;
import org.jnetpcap.PcapBpfProgram;
/*import org.jnetpcap.protocol.lan.Ethernet;
import org.jnetpcap.protocol.tcpip.*;
import org.jnetpcap.protocol.network.*;
import org.jnetpcap.nio.JBuffer;
import org.jnetpcap.packet.Payload;
import org.jnetpcap.protocol.network.Arp;
import org.jnetpcap.protocol.lan.IEEE802dot2;
import org.jnetpcap.protocol.lan.IEEE802dot3;

*/
public class Captura {
    private static int i = 0;
	/**
	 * Main startup method
	 *
	 * @param args
	 *          ignored
	 */
   private static String asString(final byte[] mac) {
    
    final StringBuilder buf = new StringBuilder();
    for (byte b : mac) {
      if (buf.length() != 0) {
        buf.append(':');
      }
      if (b >= 0 && b < 16) {
        buf.append('0');
      }
      buf.append(Integer.toHexString((b < 0) ? b + 256 : b).toUpperCase());
    }

    return buf.toString();
  }

	public static void main(String[] args) {
            Pcap pcap=null;
               try{
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));   
		List<PcapIf> alldevs = new ArrayList<PcapIf>(); // Will be filled with NICs
		StringBuilder errbuf = new StringBuilder(); // For any error msgs
                System.out.println("[0]-->Realizar captura de paquetes al vuelo");
                System.out.println("[1]-->Cargar traza de captura desde archivo");
                System.out.print("\nElige una de las opciones:");
                int opcion = Integer.parseInt(br.readLine());
                System.out.println("Cargando traza de captura desde archivo");
                //int opcion = 1;
                if (opcion==1){ 
                    
                    /////////////////////////lee archivo//////////////////////////
                //String fname = "archivo.pcap";
                String fname = "paquetes3.pcap";
                pcap = Pcap.openOffline(fname, errbuf);
                if (pcap == null) {
                  System.err.printf("Error while opening device for capture: "+ errbuf.toString());
                  return;
                 }//if
                } else if(opcion==0){
		/***************************************************************************
		 * First get a list of devices on this system
		 **************************************************************************/
		int r = Pcap.findAllDevs(alldevs, errbuf);
		if (r == Pcap.NOT_OK || alldevs.isEmpty()) {
			System.err.printf("Can't read list of devices, error is %s", errbuf
			    .toString());
			return;
		}

		System.out.println("Network devices found:");

		int i = 0;
		for (PcapIf device : alldevs) {
			String description =
			    (device.getDescription() != null) ? device.getDescription()
			        : "No description available";
                        final byte[] mac = device.getHardwareAddress();
			String dir_mac = (mac==null)?"No tiene direccion MAC":asString(mac);
                        System.out.printf("#%d: %s [%s] MAC:[%s]\n", i++, device.getName(), description, dir_mac);
                        List<PcapAddr> direcciones = device.getAddresses();
                        for(PcapAddr direccion:direcciones){
                            System.out.println(direccion.getAddr().toString());
                        }//foreach

		}//for
                
                System.out.print("\nEscribe el número de interfaz a utilizar:");
                int interfaz = Integer.parseInt(br.readLine());
		PcapIf device = alldevs.get(interfaz); // We know we have atleast 1 device
		System.out
		    .printf("\nChoosing '%s' on your behalf:\n",
		        (device.getDescription() != null) ? device.getDescription()
		            : device.getName());    
                
		/***************************************************************************
		 * Second we open up the selected device
		 **************************************************************************/
                /*"snaplen" is short for 'snapshot length', as it refers to the amount of actual data captured from each packet passing through the specified network interface.
                64*1024 = 65536 bytes; campo len en Ethernet(16 bits) tam máx de trama */

		int snaplen = 64 * 1024;           // Capture all packets, no trucation
		int flags = Pcap.MODE_PROMISCUOUS; // capture all packets
		int timeout = 10 * 1000;           // 10 seconds in millis

                
                pcap = Pcap.openLive(device.getName(), snaplen, flags, timeout, errbuf);

		if (pcap == null) {
			System.err.printf("Error while opening device for capture: "
			    + errbuf.toString());
			return;
		}//if
                  
                       /********F I L T R O********/
            PcapBpfProgram filter = new PcapBpfProgram();
            String expression =""; // "port 80";
            int optimize = 0; // 1 means true, 0 means false
            int netmask = 0;
            int r2 = pcap.compile(filter, expression, optimize, netmask);
            if (r2 != Pcap.OK) {
                System.out.println("Filter error: " + pcap.getErr());
            }//if
            pcap.setFilter(filter);
                /****************/
            }//else if

		/***************************************************************************
		 * Third we create a packet handler which will receive packets from the
		 * libpcap loop.
		 **********************************************************************/
		PcapPacketHandler<String> jpacketHandler = new PcapPacketHandler<String>() {

			public void nextPacket(PcapPacket packet, String user) {
                                
                                System.out.println("\n--------------------------------------------------------------------------");
                                System.out.println("Trama "+ (++i));
				System.out.printf("Paquete recibido el %s caplen=%-4d longitud=%-4d %s\n\n",
				    new Date(packet.getCaptureHeader().timestampInMillis()),
				    packet.getCaptureHeader().caplen(),  // Length actually captured
				    packet.getCaptureHeader().wirelen(), // Original length
				    user                                 // User supplied object
				    );
                                
                                
                                /******Desencapsulado********/
                                for(int i=0;i<packet.size();i++){
                                System.out.printf("%02X ",packet.getUByte(i));
                                
                                if(i%16==15)
                                    System.out.println("");
                                }//if
                                
                                int longitud = (packet.getUByte(12)*256)+packet.getUByte(13);
                                byte control1 = 0x00, control2 = 0x00;
                                int tipo = -1;
                                String mensaje="";
                                  
                                if(longitud<1500){
                                    int ssap = packet.getUByte(15)& 0x00000001;
                                    String c_r = (ssap==1)?"Respuesta":(ssap==0)?"Comando":"Otro";
                                   
                                    //Saber si control es de 1 byte o 2 bytes
                                    if(longitud == 3){                           //control = 1 Byte
                                        control1 = (byte) packet.getUByte(16);
                                        control1 = invertir(control1, 8); 
                                    }
                                    else if(longitud > 3){                       //control = 2 Bytes
                                        control1 =  (byte) packet.getUByte(16);
                                        control1 = invertir(control1, 8); 
                                        control2 =  (byte) packet.getUByte(17);
                                        control2 = invertir(control1, 8); 
                                    }
                                    
                                    tipo = IsTipo(control1);
                                    
                                    //mostrar informacion
                                    System.out.printf("\nLongitud: %d (%04X)",longitud,longitud );
                                    System.out.println("--->Trama IEEE802.3");
                                    System.out.printf(" |-->MAC Destino: %02X:%02X:%02X:%02X:%02X:%02X",packet.getUByte(0),packet.getUByte(1),packet.getUByte(2),packet.getUByte(3),packet.getUByte(4),packet.getUByte(5));
                                    System.out.printf("\n |-->MAC Origen: %02X:%02X:%02X:%02X:%02X:%02X",packet.getUByte(6),packet.getUByte(7),packet.getUByte(8),packet.getUByte(9),packet.getUByte(10),packet.getUByte(11));
                                    System.out.printf("\n |-->DSAP: %02X",packet.getUByte(14));
                                    //System.out.println(packet.getUByte(15)& 0x00000001);
                                    System.out.printf("\n |-->SSAP: %02X   %s\n",packet.getUByte(15), c_r);
                                    //mostrar la informacion especifica de cada tipo
                                    info(tipo, longitud, control1, control2, ssap);
                                    
                                } else if(longitud>=1500){
                                    System.out.println("-->Trama ETHERNET");
                                }//else
                                
                                
                                //System.out.println("\n\nEncabezado: "+ packet.toHexdump());
      

			}
		};


		/***************************************************************************
		 * Fourth we enter the loop and tell it to capture 10 packets. The loop
		 * method does a mapping of pcap.datalink() DLT value to JProtocol ID, which
		 * is needed by JScanner. The scanner scans the packet buffer and decodes
		 * the headers. The mapping is done automatically, although a variation on
		 * the loop method exists that allows the programmer to sepecify exactly
		 * which protocol ID to use as the data link type for this pcap interface.
		 **************************************************************************/
		pcap.loop(-1, jpacketHandler, " ");

		/***************************************************************************
		 * Last thing to do is close the pcap handle
		 **************************************************************************/
		pcap.close();
                }catch(IOException e){e.printStackTrace();}
	}
        static byte invertir(byte control, int nBytes){                                     //Inverrtir 1 Byte
            byte invertido = 0x00, aux = 0x00, aux2 = 0x00, aux3 = 0x00;
            for(int i=0; i<nBytes; i++){
                aux = (byte) Math.pow(2 , i);                                   //numeros de potencia 2
                aux = (byte) (control & aux);                                   //mascara al byte dependiendo de la potencia
                if(aux != 0x00){
                    aux2 = (byte) Math.pow(2, nBytes-1-i);                             //Bit donde se quiere cambiar
                    aux2 = (byte) (aux2 | aux);                                 //sumar el bit al que se quiere cambiar
                    aux3 = (byte) (aux ^ aux2);                                 //solo queda el bit cambiado de posocion
                }
                else
                    aux3 = 0x00;
                invertido = (byte) (invertido | aux3);                          //sumar todo y quede 1 solo byte
            }
            return invertido;
        }
        //preguntar que tipo de trama es
        static int IsTipo(byte control){
            if((control&0xC0)== 0x80)      //trama 's'
                return 2;
            else if((control&0xC0) == 0xC0)
                return 3;
            else if((control & 0x00) == 0x00) //trama 'i'
            return 1;
            return -1;
        }
        //mostrar informacion de la trama
        static void info(int tipo, int longitud, byte control1, byte control2, int ssap){
            System.out.printf("Control: %02X", control1);
            if(longitud > 3)
                System.out.printf(" %02X", control2);
            System.out.printf("\n");
            byte aux = 0x00;
            switch(tipo){
                case 1://Tamas de informacion
                    System.out.printf("Trama tipo 'I'\n");
                    if(longitud == 3){
                        aux = (byte) (control1&0x70);
                        aux = invertir(aux, 3);
                        System.out.printf("N(S): %02X\n", aux);
                        aux = (byte) (control1&0x07);                        
                        aux = invertir(aux, 3);
                        System.out.printf("N(R): %02X\n", aux);
                    }
                    else if(longitud >3){
                        aux = (byte) (control1&0x7F);
                        aux = invertir(aux, 7);
                        System.out.printf("N(S): %02X\n", aux);
                        aux = (byte) (control2&0xF7);
                        aux = invertir(aux, 7);
                        System.out.printf("N(R): %02X\n", aux);
                    }
                    break;
                case 2: //tramas s
                    System.out.printf("Trama tipo 'S'\n");
                    aux = (byte) (control1&30);
                    //aux = invertir(aux, 2);
                    System.out.printf("Codigo: %02X = %s", aux, codigos(aux));
                    if(longitud == 3){
                        aux = (byte) (control1&0x07);
                        aux = invertir(aux, 3);
                        System.out.printf("N(R): %02X", aux);
                    }
                    else if(longitud > 3){
                        aux = (byte) (control1&0xF7);
                        aux = invertir(aux, 7);
                        System.out.printf("N(R): %02X", aux);
                    }
                    break;
                case 3: //tramas u
                    System.out.printf("Trama tipo 'U'\n");
                    aux = (byte) (control1&0x30);
                    aux = (byte) (aux | (control1&0x07));
                    //aux = invertir(aux, 5);
                    System.out.printf("Codigo: %02X = %s", aux, codigou(aux, ssap));
                    break;
                default:
                    break;
            }
         
        }
        static String codigos(byte codigo){
            String cod="";
            switch(codigo){
                case 0x00:
                    cod = "RR (Listo para Recibir)\n";
                    break;
                case 0x01:
                    cod = "REJ (Rechazado)\n";
                    break;
                case 0x02:
                    cod = "RNR (No Listo Para Recibir)\n";
                    break;
                case 0x03:
                    cod = "SREJ (Rechazado Selectivo)\n";
                    break;
                default:
                    break;
            }
            return cod;
        }
        static String codigou(byte codigo, int ssap){   
            String cod = "";
            switch(codigo){
                case 0x01:
                   if(ssap==1)          //respuesta
                       cod = "-\n";
                   else if (ssap == 0)  //orden
                       cod = "SNRM (Activacion de Modo de Respuesta Normal)\n";
                   break;
                case 0x1B:
                   if(ssap==1)          //respuesta
                       cod = "-\n";
                   else if (ssap == 0)  //orden
                       cod = "SNRME (Activacion de Modo de Respuesta Normal Extendido)\n";
                   break;
                case 0x18:
                   if(ssap==1)          //respuesta
                       cod = "DM ()\n";
                   else if (ssap == 0)  //orden
                       cod = "SARM (Activacion de Modo de Respuesta Asincrona)\n";
                   break;
                case 0x1A:
                   if(ssap==1)          //respuesta
                       cod = "-\n";
                   else if (ssap == 0)  //orden
                       cod = "SARME (Activacion de Modo de Respuesta Asincrona Extendida)\n";
                   break;
                case 0x1C:
                   if(ssap==1)          //respuesta
                       cod = "-\n";
                   else if (ssap == 0)  //orden
                       cod = "SABM (Activacion de Modo de Respuesta Asincrona Balanceada)\n";
                   break;
                case 0x1E:
                   if(ssap==1)          //respuesta
                       cod = "-\n";
                   else if (ssap == 0)  //orden
                       cod = "SABME (Activacion de Modo de Respuesta Asincrona Balanceada Extendida)\n";
                   break;
                case 0x00:
                   if(ssap==1)          //respuesta
                       cod = "\n";
                   else if (ssap == 0)  //orden
                       cod = "UI (Informacion sin Numerar)\n";
                   break;
                case 0x06:
                   if(ssap==1)          //respuesta
                       cod = "UA (ACK sin Numerar)\n";
                   else if (ssap == 0)  //orden
                       cod = "-\n";
                   break;
                case 0x02:
                   if(ssap==1)          //respuesta
                       cod = "RD (Peticion de Desconexion)\n";
                   else if (ssap == 0)  //orden
                       cod = "DISC (Modo de Desconexion)\n";
                   break;
                case 0x04:
                   if(ssap==1)          //respuesta
                       cod = "\n";
                   else if (ssap == 0)  //orden
                       cod = "UP (Sondeo sin Numerar)\n";
                   break;   
                case 0x19:
                   if(ssap==1)          //respuesta
                       cod = "\n";
                   else if (ssap == 0)  //orden
                       cod = "RSET (Reinicio)\n";
                   break;
                case 0x1D:
                   cod = "x1D (Intercambio de ID)\n";
                   break;
            }
            return cod;
        }
}
