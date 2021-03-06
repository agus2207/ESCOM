import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.*;

import org.jnetpcap.Pcap;
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
		List<PcapIf> alldevs = new ArrayList<PcapIf>(); // Will be filled with NICs
		StringBuilder errbuf = new StringBuilder(); // For any error msgs
                
                
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
                try{
		for (PcapIf device : alldevs) {
			String description =
			    (device.getDescription() != null) ? device.getDescription()
			        : "No description available";
                        final byte[] mac = device.getHardwareAddress();
			String dir_mac = (mac==null)?"No tiene direccion MAC":asString(mac);
                        System.out.printf("#%d: %s [%s] MAC:[%s]\n", i++, device.getName(), description, dir_mac);

		}//for
/****************************************************************************************************************************************************************************
 * **************************************************************************************************************************************************************************
 * Con 0 usa cable con 1 no
 */               
		PcapIf device = alldevs.get(1); // We know we have atleast 1 device
		System.out
		    .printf("\nChoosing '%s' on your behalf:\n",
		        (device.getDescription() != null) ? device.getDescription()
		            : device.getName());

		/***************************************************************************
		 * Second we open up the selected device
		 **************************************************************************/
                /*"snaplen" is short for 'snapshot length', as it refers to the amount of actual data captured from each packet passing through the specified network interface.
                64*1024 = 65536 bytes; campo len en Ethernet(16 bits) tam mÃ¡x de trama */

		int snaplen = 64 * 1024;           // Capture all packets, no trucation
		int flags = Pcap.MODE_PROMISCUOUS; // capture all packets
		int timeout = 10 * 1000;           // 10 seconds in millis
                Pcap pcap =
		    Pcap.openLive(device.getName(), snaplen, flags, timeout, errbuf);

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


		/***************************************************************************
		 * Third we create a packet handler which will receive packets from the
		 * libpcap loop.
		 **********************************************************************/
		PcapPacketHandler<String> jpacketHandler;
                    jpacketHandler = new PcapPacketHandler<String>() {
                        
                        public void nextPacket(PcapPacket packet, String user) {
                            //Variables
                            byte auxenc[], auxps[];                              //auxiliar para mandar al metodo calculateChecksum()
                            ArrayList<Byte> encabezado = new ArrayList<>();       //encabezado IP
                            ArrayList <Byte> pseudo = new ArrayList<>();          //pseudoencabezado (UDP/TCP)
                            String tipoProtocolo = null;
                            byte aux;
                            int protocolo = packet.getUByte(23);                //leer el protocolo
                            int tipo = packet.getUByte(12)*256+packet.getUByte(13); //tipo de trama
                            long checksumEncabezado, checksumPseudo;      
                            int tamaño = packet.getUByte(14) & 0x0F;             //Obtener el tamaño    
                            tamaño *= 4;                                        //tamaño en Bytes del encabezado
                            
                            System.out.println("---------------------------------------------------------------------------\n");
                            System.out.printf("Received packet at %s caplen=%-4d len=%-4d %s\n",
                                    new Date(packet.getCaptureHeader().timestampInMillis()),
                                    packet.getCaptureHeader().caplen(),  // Length actually captured
                                    packet.getCaptureHeader().wirelen(), // Original length
                                    user                                 // User supplied object
                            );
                            
                            /******Desencapsulado********/
                            if(tipo == 2048){                                   //si el encabeado es tipo IP                    
                                for(int i=0;i<packet.size();i++){               //recorrer el paquete
                                    aux = (byte) packet.getUByte(i);
                                    System.out.printf("%02X ",aux);
                                    if(i%16==15)
                                        System.out.println("");     
                                    
                                    if(i>13 && i<14+tamaño){                    //Desde donde empieza el encabezado IP(byte 14) hasta el tamaño del encabezado
                                        encabezado.add(aux);
                                        if(i > 25)                              //Si es mayor a 25 (IP origen e IP destino)    
                                            pseudo.add(aux);
                                    }
                                } //fin for
                                
                                int longitudpseudo = packet.getUByte(16)*256 + packet.getUByte(17);   //Campo longitud del pseudoencabezado
                                
                                longitudpseudo -= tamaño;                          //valor decimal de la longitud del pseudoencabezado
                                
                                //-----Agregar los bytes al Psuedo-encabezado
                                 pseudo.add((byte)0x00);                        //Byte zero
                                 pseudo.add((byte)protocolo);                   //Byte Protocolo
                                
                                if(protocolo == 17){                            //Si es protocolo UDP
                                   tipoProtocolo = "UDP";
                                   
                                   pseudo.add((byte)packet.getUByte(18+tamaño));
                                   pseudo.add((byte)packet.getUByte(19+tamaño));
                                   
                                }//fin protocolo UDP
                              
                                else if(protocolo == 06){                       //Protocolo TCP
                                    tipoProtocolo = "TCP";
                                    //-------Campo longitud--------------------
                                    pseudo.add((byte)packet.getUByte(16));      //primer byte del campo longitud
                                    pseudo.add((byte)(packet.getUByte(17)-tamaño));   //segundo byte del campo longitud - longitud(IHL) 
                                }//fin protocolo tcp
                                //--------------Recorrer el encabezado TCP/UDP
                                for (int i = 14+tamaño; i< longitudpseudo+14+tamaño; i++)
                                       pseudo.add((byte)packet.getUByte(i));
                                
                                //------------Llamar a la funcion para calcular el checksum---------
                                auxenc = new byte[encabezado.size()];
                                auxps = new byte[pseudo.size()];
                                //-----------pasar los bytes al arreglo
                                for(int i=0; i<encabezado.size(); i++)          //para el arreglo del encabezado
                                    auxenc[i] = encabezado.get(i);          
                                
                                for(int i=0; i<pseudo.size(); i++)              //para el arreglo del pseudoencabezado
                                    auxps[i] = pseudo.get(i);
                                
                                checksumEncabezado = Checksum.calculateChecksum(auxenc); 
                                checksumPseudo = Checksum.calculateChecksum(auxps);
                                
                                System.out.printf("\n\nTrama IP\nProtocolo = %02x - %s \nIHL: %d\nLongitud del Pseudoencabezado: %d\n",protocolo, tipoProtocolo, tamaño, longitudpseudo);
                                //--------------------Verificar si es velido el checksum
                                System.out.printf("Checksum del encabezado IP: %02X\n", checksumEncabezado);
                                if(checksumEncabezado == 0)                     //Encabezado
                                    System.out.printf("Checksum del encabezado Valido\n");
                                else
                                    System.out.printf("Checksum del encabezado NO Valido\n");
                                    
                                System.out.printf("Checksum del protocolo (pseudoencabezado): %02X\n", checksumPseudo);
                                if(checksumPseudo == 0)
                                    System.out.printf("Checksum del protocolo Valido\n");
                                else
                                    System.out.printf("Checksum del protocolo NO Valido\n");
                                
                            } //fin if tipo
                          //  System.out.println("\n\nEncabezado: "+ packet.toHexdump());
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
		pcap.loop(10, jpacketHandler, "jNetPcap rocks!");

		/***************************************************************************
		 * Last thing to do is close the pcap handle
		 **************************************************************************/
		pcap.close();
                }catch(IOException e){e.printStackTrace();}
	}        
}
