package proyecto;

import java.net.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ClienteFlujo {

    public ClienteFlujo() {
    }

    public void descarga(String host, int pto, String ruta, JProgressBar barra, JTable tabla) {
        try {
            Socket cl = new Socket(host, pto);
            ManejadorCFlujo m = new ManejadorCFlujo(cl, ruta, barra, tabla, false);
            m.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void descarga(String hosts[], int ptos[], String rutas[], JProgressBar barras[], JTable tabla, long tam) {
        long cant = (long) (tam / hosts.length);
        long residuo = (long) (tam % hosts.length);

        for (int i = 0; i < hosts.length; i++) {
            try {
                Socket cl = new Socket(hosts[i], ptos[i]);
                long offset = (long) i * cant;
                long leer;
                if (i == hosts.length - 1) {
                    leer = cant + residuo;
                } else {
                    leer = cant - 1;
                }

                ManejadorCFlujo m = new ManejadorCFlujo(cl, rutas[i], offset, leer, barras[i], tabla, i, true);
                m.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class ManejadorCFlujo extends Thread {

    private Socket cl;
    private String ruta;
    private long offset, cant;
    private JProgressBar barra;
    private JTable tabla;
    private int index;
    private boolean distribuido;

    ManejadorCFlujo(Socket cl, String ruta, long offset, long cant, JProgressBar barra, JTable tabla, int index, boolean dis) {
        this.cl = cl;
        this.ruta = ruta;
        this.offset = offset;
        this.cant = cant;
        this.barra = barra;
        this.tabla = tabla;
        this.index = index;
        this.distribuido = dis;
    }
    ManejadorCFlujo(Socket cl, String ruta, JProgressBar barra, JTable tabla, boolean dis) {
        this.cl = cl;
        this.ruta = ruta;
        this.barra = barra;
        this.tabla = tabla;
        this.distribuido = dis;
    }

    @Override
    public void run() {
        if (distribuido) {
            try {
                System.out.println("Bytes solicitados: de " + offset + " a " + (offset + cant));
                DataOutputStream dos = new DataOutputStream(cl.getOutputStream());
                DataInputStream dis = new DataInputStream(cl.getInputStream());

                dos.writeBoolean(true); // Indica que es descarga múltiple
                dos.flush();

                dos.writeUTF(ruta);
                dos.flush();

                dos.writeLong(offset);
                dos.flush();

                dos.writeLong(cant);
                dos.flush();

                byte[] b;
                String nombre = dis.readUTF();
                System.out.println("Recibimos el archivo: " + nombre);
                RandomAccessFile raf = new RandomAccessFile(nombre, "rw");
                long recibidos = 0;
                int n, porcentaje;

                while (recibidos < cant) {
                    raf.seek(offset);
                    if (cant - recibidos >= 1024) {
                        b = new byte[1024];
                        n = dis.read(b);
                    } else {
                        int resta = (int) (cant - recibidos);
                        b = new byte[resta];
                        n = dis.read(b);
                    }
                    raf.write(b, 0, n);
                    offset += n;
                    recibidos = recibidos + n;
                    porcentaje = (int) (recibidos * 100 / cant);
                    //System.out.println("Recibido: " + porcentaje + "%\t (" + recibidos + " bytes)");
                    barra.setValue(porcentaje);
                    barra.setString(porcentaje + "%");

                    DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
                    modelo.setValueAt(barra, index, 3);

                    if (porcentaje < 100) {
                        modelo.setValueAt("Descargando", index, 2);
                    } else {
                        modelo.setValueAt("Descarga completa", index, 2);
                    }

                    tabla.setModel(modelo);
                    tabla.getColumn("Progreso").setCellRenderer(new CellRendered());
                    //Thread.sleep(500);
                }
                System.out.println("Archivo recibido");
                dos.close();
                dis.close();
                raf.close();
                cl.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                DataOutputStream dos = new DataOutputStream(cl.getOutputStream());
                DataInputStream dis = new DataInputStream(cl.getInputStream());

                dos.writeBoolean(false); // Indica que no es descarga múltiple
                dos.flush();

                dos.writeUTF(ruta);
                dos.flush();

                byte[] b = new byte[1024];
                String nombre = dis.readUTF();
                System.out.println("Recibimos el archivo: " + nombre);
                long tam = dis.readLong();
                DataOutputStream dosf = new DataOutputStream(new FileOutputStream(nombre));
                long recibidos = 0;
                int n, porcentaje;

                while (recibidos < tam) {
                    if (tam - recibidos >= 1024) {
                        b = new byte[1024];
                        n = dis.read(b);
                    } else {
                        int resta = (int) (tam - recibidos);
                        b = new byte[resta];
                        n = dis.read(b);
                    }
                    dosf.write(b, 0, n);
                    dosf.flush();
                    recibidos = recibidos + n;
                    porcentaje = (int) (recibidos * 100 / tam);
                    //System.out.println("Recibido: " + porcentaje + "%\r");
                    barra.setValue(porcentaje);
                    barra.setString(porcentaje + "%");

                    DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
                    modelo.setValueAt(barra, 0, 3);

                    if (porcentaje < 100) {
                        modelo.setValueAt("Descargando", 0, 2);
                    } else {
                        modelo.setValueAt("Descarga completa", 0, 2);
                    }

                    tabla.setModel(modelo);
                    tabla.getColumn("Progreso").setCellRenderer(new CellRendered());
                    //Thread.sleep(500);
                }
                System.out.println("Archivo recibido");
                dos.close();
                dis.close();
                dosf.close();
                cl.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
