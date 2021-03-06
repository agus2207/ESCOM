import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Servidor {
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        DAT a = new DAT();
        try{
            ServerSocket s = new ServerSocket(7000);
            for(;;){
                Socket cl = s.accept();
                System.out.println("Cliente conectado desde: "+cl.getInetAddress()+" : "+cl.getPort());
                ObjectInputStream ois = new ObjectInputStream(cl.getInputStream());
                int opcion = Integer.parseInt((String)ois.readObject());
                //System.out.println("Recibi la opcion "+opcion);
                switch(opcion){
                    case 1:
                        String [][] catalogo = a.readArray();
                        ArrayList<Productos> producto = new ArrayList();
                        for(int i = 0; i < catalogo.length; i++){
                            producto.add(new Productos(catalogo[i][0], catalogo[i][1], catalogo[i][2], catalogo[i][3], catalogo[i][4], catalogo[i][5]));
                        }
                        ObjectOutputStream oos = new ObjectOutputStream(cl.getOutputStream());
                        int cantidad = producto.size();
                        oos.writeObject(cantidad);
                        for(int i = 0; i < producto.size(); i++){
                            oos.writeObject(producto.get(i));
                        }
                        break;
                    case 2:
                        System.out.println("Entre al caso 2");
                        //ois = new ObjectInputStream(cl.getInputStream());
                        int total = ois.readInt();
                        //System.out.println("Tengo el total");
                        int tam = ois.readInt();
                        String[][] array = new String[tam][];
                        for(int i = 0; i < tam; i++){
                            Productos prod = (Productos)ois.readObject();
                            array[i] = new String[6];
                            array[i][0] = prod.getNombre();
                            array[i][1] = prod.getGenero();
                            array[i][2] = prod.getDescripcion();
                            array[i][3] = prod.getPrecio();
                            array[i][4] = prod.getCantidad();
                            array[i][5] = prod.getRuta();
                        }
                        //System.out.println("Tengo el catalogo actualizado");
                        ArrayList<String> ticket = new ArrayList();
                        a.writeArray(array);
                        int ticket_tam = ois.readInt();
                        for(int i = 0; i < ticket_tam; i++){
                            for(int j = 0; j < 4; j++){
                                ticket.add((String)ois.readObject());
                            }
                        }
                        ticket.add("$"+total);
                        PDF p = new PDF();
                        p.escribir(ticket);
                        File fichero=new File("src/Documentos/Ticket.pdf");
                        String archivo = fichero.getAbsolutePath();
                        String nombre = fichero.getName();
                        long tamaño = fichero.length();
                        DataOutputStream dos = new DataOutputStream(cl.getOutputStream());
                        DataInputStream dis = new DataInputStream(new FileInputStream(archivo));
                        dos.writeUTF(nombre);
                        dos.flush();
                        dos.writeLong(tamaño);
                        dos.flush();
                        //Seccion para el envio del archivo
                        byte[] b = new byte[1024];
                        long enviados = 0;
                        int porcentaje, n;
                        while(enviados < tamaño){
                            n = dis.read(b);
                            dos.write(b,0,n);
                            dos.flush();
                            enviados = enviados + n;
                            porcentaje = (int)(enviados*100/tamaño);
                            //System.out.println("Enviado: "+porcentaje+"%\r");
                        }
                        System.out.print("\n\nArchivo enviado. ");
                        dos.close();
                        dis.close();
                        break;
                }
                cl.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}