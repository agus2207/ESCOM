import java.io.*;
import java.net.*;
import java.util.Scanner;
import javax.swing.JFileChooser;


public class CArchTCPB{
    public static void main(String[] args){
        try{
            //Variable para pedir cosas por el teclado
            //Scanner opcion = new Scanner(System.in);
            DataOutputStream dos = null;
            DataInputStream dis = null;
            String archivo;
            String nombre;
            long tam;
            int cantidad;
            PrintWriter pw;

            //Este fragmento de codigo pide al usuario la direccion y el puerto del servidor
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.printf("Escriba la dirección del servidor: ");
            String host = br.readLine();
            System.out.printf("\nEscriba el puerto: ");
            int pto = Integer.parseInt(br.readLine());

            //Creación del socket
            Socket cl = new Socket(host,pto);
            
            JFileChooser jf = new JFileChooser();
            jf.setFileSelectionMode(JFileChooser.APPROVE_OPTION);
            if(!jf.isMultiSelectionEnabled())
                jf.setMultiSelectionEnabled(true);
               
            int r = jf.showOpenDialog(null);
            File[] f = jf.getSelectedFiles();
            cantidad = f.length;

            pw = new PrintWriter(new OutputStreamWriter(cl.getOutputStream()));
            pw.println(cantidad);
            pw.flush();

            for(int i = 0; i < f.length; i++){
                //int contador = 0;
                archivo = f[i].getAbsolutePath();
                nombre = f[i].getName();
                tam = (int) f[i].length();
                
                dos = new DataOutputStream(cl.getOutputStream());
                dis = new DataInputStream(new FileInputStream(f[i].getPath()));
                
                dos.writeUTF(nombre);
                dos.flush();
                dos.writeLong(tam);
                dos.flush();
                
                byte[] b = new byte[1024];
                long enviados = 0;
                int porcentaje = 0, n;
                while(enviados < tam){
                    n = dis.read(b);
                    dos.write(b,0,n);
                    dos.flush();
                    enviados = enviados + n;
                    porcentaje = (int)(enviados * 100 / tam);
                }
                System.out.print("Archivo" + (i + 1) + ", " + porcentaje + "% enviado: \n" + f[i].getName() + "\n");
            }
            //Cerramos todo
            dos.close();
            dis.close();
            pw.close();
            cl.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}