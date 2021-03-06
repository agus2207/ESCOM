import Utilidades.ModeloTabla;
import java.awt.Desktop;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Cliente{
        
    static ArrayList<Productos> producto = new ArrayList();
    private ImageIcon ticon = new ImageIcon(getClass().getResource("/Imagenes/adios.jpg"));
    private ImageIcon warning = new ImageIcon(getClass().getResource("/Imagenes/warning.jpg"));
    //static ArrayList<Productos> a = new ArrayList();
    public static void main(String[] args){
        String host = "127.0.0.1";
        int port = 7000;
        try{
            File fichero = new File("Ticket.pdf");
            fichero.delete();
            Socket cl = new Socket(host, port);
            ObjectOutputStream oos = new ObjectOutputStream(cl.getOutputStream()); //manda
            oos.writeObject("1");
            ObjectInputStream ois = new ObjectInputStream(cl.getInputStream()); //recibe
            int tam = (int) ois.readObject();
            for(int i = 0; i < tam; i++){
                Productos prod = (Productos)ois.readObject();
                producto.add(prod);
            }
            VentanaTabla mi = new VentanaTabla();
            mi.setVisible(true);
        }catch(Exception e){
            System.err.println(e);
        }
    }
    
    public ArrayList<Productos> inventario(){
        return producto;
    }
    
    public void comprar(ArrayList<Productos> np, String[][]ticket, int precio){
        ArrayList<Productos> compra = new ArrayList();
        for(int i = 0; i < np.size(); i++){
            Productos p = np.get(i);
            compra.add(p);
        }
        String host = "127.0.0.1";
        int port = 7000;
        try{
            Socket cl = new Socket(host, port);
            ObjectOutputStream oos = new ObjectOutputStream(cl.getOutputStream()); //manda
            oos.writeObject("2");
            oos.writeInt(precio);
            oos.writeInt(compra.size());
            for (int i = 0; i < compra.size(); i++){
                oos.writeObject(compra.get(i));
            }
            oos.writeInt(ticket.length);
            for(int i = 0; i < ticket.length; i++){
                for(int j = 0; j < 4; j++){
                    oos.writeObject(ticket[i][j]);
                }
            }
            DataInputStream dis = new DataInputStream(cl.getInputStream()); //Flujo de archivo de entrada
            byte[] b = new byte[1024]; //Paquetes de 1024 bytes
            String nombre = dis.readUTF();
            System.out.println("Recibimos el archivo "+nombre);
            long tam = dis.readLong();
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(nombre)); //Flujo de archivo de salida
            //Seccion para recibir el archivo
            long recibidos = 0;
            int n, porcentaje;
            while(recibidos<tam){
                n = dis.read(b,0,Math.min(1024, (int)(tam - recibidos)));
                dos.write(b, 0, n);
                dos.flush();
                recibidos = recibidos + n;
                porcentaje = (int) (recibidos * 100 / tam);
                //System.out.println("Recibido " + porcentaje + "%\r");
            }
            System.out.println("\n\nArchivo Recibido. \n");
            dos.close();
            dis.close();
            cl.close();
        }catch(Exception e){
            System.err.println(e);
        }
    }
    
    public void openfile(String archivo){
        try {
            File objetofile = new File (archivo);
            if(objetofile.exists())
                Desktop.getDesktop().open(objetofile);
            else
                JOptionPane.showMessageDialog(null, "No se puede generar el ticket\nRealice su compra primero", "Warning",JOptionPane.INFORMATION_MESSAGE, warning);
        }catch (IOException ex) {
            System.out.println(ex); 
        }
    }
    
    public void salir(){
        JOptionPane.showMessageDialog(null, "Gracias por visitarnos\nVuelva pronto", "ADIOS",JOptionPane.INFORMATION_MESSAGE, ticon);
        System.exit(0);
    }
}
