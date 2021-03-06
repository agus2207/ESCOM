package Interfaz;

import Logica.AdministradorDeOperacionesDatagrama;
import static Logica.AdministradorDeOperacionesDatagrama.BUSCAR;
import static Logica.AdministradorDeOperacionesDatagrama.RESPUESTA;
import Logica.AdministradorDeOperacionesMulticast;
import static Logica.AdministradorDeOperacionesMulticast.FIN_ID;
import static Logica.AdministradorDeOperacionesMulticast.INICIO_ID;
import Logica.Busqueda.Archivo;
import Logica.Busqueda.Busqueda;
import Logica.Busqueda.MD5Checksum;
import Logica.Busqueda.Nodo;
import Logica.TransferenciaListener;
import Logica.Mensaje;
import Logica.OperacionesNodo;
import Logica.Transferencia.EnviarArchivo;
import Logica.Transferencia.RecibirArchivo;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.SocketException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


/**
 *
 * @author miguel
 * inicializar Nodo actual
 */
public class Vista extends javax.swing.JFrame implements ActionListener, TransferenciaListener{
    private int puerto;
    private String ip;
    private Nodo nodo;
    
    private ArrayList nodos;
    private Nodo nodoSiguiente;
    private Nodo nodoAnterior;
    private String nodosDisponibles;
    private String logTemporal;
    private OperacionesNodo operacionesNodo;
    private AdministradorDeOperacionesDatagrama adopd;
    private Busqueda busqueda;
    private JRadioButton botones[];
    private long tamArchivoEnvio, tamArchivoRecepcion, bytesEnviados, bytesRecibidos;
   
    public Vista() throws IOException {
        initComponents();
        init();
    }

    private void init() throws IOException
    {
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        DialogoPuerto dp = new DialogoPuerto(new javax.swing.JFrame(), true);
        dp.setVisible(true);
        puerto = dp.getPuerto();
        if(puerto == -1 )
            System.exit(0);
        nodosConectados.setContentType("text/html");
        operacionesNodo = new OperacionesNodo(puerto);
        logTemporal = "";
        busqueda = new Busqueda();
        this.setTitle("" + puerto);
        buscar.addActionListener(this);
        descargar.addActionListener(this);
        descargar.setEnabled(false);
        log.setEditable(false);
        nodosConectados.setEditable(false);
        jpfondo.setBackground(Color.WHITE);
        progreso.setMaximum(100);
        progresoEnvio.setMaximum(100);
        nodos = new ArrayList();
        ip = InetAddress.getLocalHost().getHostAddress();
        //NetworkInterface ni = NetworkInterface.getByName("wlp2s0");
       // ip ="10.0.0.3";
        /*
        Enumeration en = NetworkInterface.getNetworkInterfaces(); 
        
        while(en.hasMoreElements())
        { 
            NetworkInterface ni=(NetworkInterface) en.nextElement(); 
            Enumeration ee = ni.getInetAddresses(); 
            while(ee.hasMoreElements()) 
            { 
                InetAddress ia= (InetAddress) ee.nextElement(); 
                System.out.println(ia.getHostAddress());
                System.out.println(ni.getName());
                
            } 
        } 
*/
        System.out.print(ip);
        nodo = new Nodo(ip + ":" + puerto ,ip, puerto);   
        adopd = AdministradorDeOperacionesDatagrama.getInstance(puerto);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                try {
                    close();
                } catch (IOException ex) {
                    Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
                    
                }
            }
        });
        anunciar();
        actualizarListaDeNodosMulticast();
        recibirDatagrama();
        enviarArchivo();
        decrementarServidor();
        
         System.out.print(puerto+"\n");
         
    }
    
    
    private void close() throws IOException{
        if (JOptionPane.showConfirmDialog(rootPane, "¿Deseas salir?",
                "Salir", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
        {
            AdministradorDeOperacionesMulticast.getInstance().salirDelAnillo(nodo.getId());
            System.exit(0);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpfondo = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        nodosConectados = new javax.swing.JEditorPane();
        jLabel1 = new javax.swing.JLabel();
        idAnterior = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        idSiguiente = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        log = new javax.swing.JEditorPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        nombreArchivo = new javax.swing.JTextField();
        buscar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        progreso = new javax.swing.JProgressBar();
        archivos = new javax.swing.JScrollPane();
        archivosParaDescargar = new javax.swing.JPanel();
        descargar = new javax.swing.JButton();
        progresoEnvio = new javax.swing.JProgressBar();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setBackground(new java.awt.Color(0, 153, 153));

        jpfondo.setBackground(new java.awt.Color(0, 204, 204));

        jScrollPane1.setBackground(new java.awt.Color(0, 153, 153));

        nodosConectados.setContentType("text/html\n"); // NOI18N
        jScrollPane1.setViewportView(nodosConectados);

        jLabel1.setText("ID Anterior");

        jLabel2.setText("ID Siguiente");

        log.setContentType("text/html"); // NOI18N
        jScrollPane2.setViewportView(log);

        jScrollPane3.setViewportView(nombreArchivo);

        buscar.setText("Buscar");

        jLabel3.setText("Archivo a buscar");

        archivos.setForeground(new java.awt.Color(254, 254, 254));

        archivosParaDescargar.setBackground(new java.awt.Color(254, 254, 254));
        archivosParaDescargar.setForeground(new java.awt.Color(254, 254, 254));

        javax.swing.GroupLayout archivosParaDescargarLayout = new javax.swing.GroupLayout(archivosParaDescargar);
        archivosParaDescargar.setLayout(archivosParaDescargarLayout);
        archivosParaDescargarLayout.setHorizontalGroup(
            archivosParaDescargarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 527, Short.MAX_VALUE)
        );
        archivosParaDescargarLayout.setVerticalGroup(
            archivosParaDescargarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 348, Short.MAX_VALUE)
        );

        archivos.setViewportView(archivosParaDescargar);

        descargar.setText("Descargar");

        jLabel4.setIcon(new javax.swing.ImageIcon("C:\\Users\\erik_\\Documents\\Redes 2\\ProyectoFinal\\logo.png")); // NOI18N

        jLabel6.setIcon(new javax.swing.ImageIcon("C:\\Users\\erik_\\Documents\\Redes 2\\ProyectoFinal\\tanque.png")); // NOI18N

        jLabel7.setIcon(new javax.swing.ImageIcon("C:\\Users\\erik_\\Documents\\Redes 2\\ProyectoFinal\\tanque.png")); // NOI18N

        javax.swing.GroupLayout jpfondoLayout = new javax.swing.GroupLayout(jpfondo);
        jpfondo.setLayout(jpfondoLayout);
        jpfondoLayout.setHorizontalGroup(
            jpfondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpfondoLayout.createSequentialGroup()
                .addContainerGap(66, Short.MAX_VALUE)
                .addGroup(jpfondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpfondoLayout.createSequentialGroup()
                        .addGroup(jpfondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpfondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpfondoLayout.createSequentialGroup()
                                    .addGroup(jpfondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(progreso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(progresoEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(26, 26, 26))
                                .addGroup(jpfondoLayout.createSequentialGroup()
                                    .addGroup(jpfondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jpfondoLayout.createSequentialGroup()
                                            .addComponent(jLabel5)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel7)
                                            .addGap(30, 30, 30)
                                            .addComponent(jLabel3)
                                            .addGap(37, 37, 37)
                                            .addComponent(jLabel6))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpfondoLayout.createSequentialGroup()
                                .addComponent(buscar)
                                .addGap(113, 113, 113)))
                        .addGroup(jpfondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane2)
                            .addComponent(archivos, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jpfondoLayout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                                .addGroup(jpfondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(idSiguiente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)
                                    .addComponent(idAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))))
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpfondoLayout.createSequentialGroup()
                        .addComponent(descargar)
                        .addGap(162, 162, 162))))
        );
        jpfondoLayout.setVerticalGroup(
            jpfondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpfondoLayout.createSequentialGroup()
                .addGroup(jpfondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpfondoLayout.createSequentialGroup()
                        .addGroup(jpfondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpfondoLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpfondoLayout.createSequentialGroup()
                                .addGroup(jpfondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jpfondoLayout.createSequentialGroup()
                                        .addGap(54, 54, 54)
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(idAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(idSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jpfondoLayout.createSequentialGroup()
                                        .addGap(28, 28, 28)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(archivos, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpfondoLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jpfondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(jpfondoLayout.createSequentialGroup()
                                .addGroup(jpfondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpfondoLayout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(88, 88, 88))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpfondoLayout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(30, 30, 30))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpfondoLayout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(46, 46, 46)))
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(progreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(progresoEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(buscar)))
                        .addGap(20, 20, 20)))
                .addComponent(descargar)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpfondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpfondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Vista().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane archivos;
    private javax.swing.JPanel archivosParaDescargar;
    private javax.swing.JButton buscar;
    private javax.swing.JButton descargar;
    private javax.swing.JLabel idAnterior;
    private javax.swing.JLabel idSiguiente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel jpfondo;
    private javax.swing.JEditorPane log;
    private javax.swing.JEditorPane nodosConectados;
    private javax.swing.JTextField nombreArchivo;
    private javax.swing.JProgressBar progreso;
    private javax.swing.JProgressBar progresoEnvio;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        if(ae.getSource().equals(buscar))
        {
            busqueda = new Busqueda();
            if(nombreArchivo.getText().length() < 1)
            {
                UIFunctions.warningMessage("Introduce archivo a buscar", " ");
            }
            else {
                String nombre = nombreArchivo.getText();

                if (operacionesNodo.buscarArchivo(nombre)) 
                {
                    logTemporal += ("Archivo: " + nombre + " encontrado.<br>");
                    log.setText(logTemporal);
                    try {
                        agregarArchivoABusqueda(busqueda, operacionesNodo.getChecksum(nombre), nodo);
                    } catch (Exception ex) {
                        Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else 
                {
                    logTemporal += ("Archivo no encontrado.<br>");
                    log.setText(logTemporal);
                }
                
                if (nodos.size() > 0) 
                {
                    
                    busqueda.setId(BUSCAR);
                    busqueda.setNodoOrigen(nodo.getId());
                    busqueda.setNombreArchivo(nombre);
                    try {
                        adopd.enviarMensaje(busqueda, nodoSiguiente.getIp(),
                                nodoSiguiente.getPuerto());
                    } catch (IOException ex) {
                        Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    logTemporal += ("No hay más nodos.<br>");
                    log.setText(logTemporal);
                }

            }
            nombreArchivo.setText("");
        }
        
        if (ae.getSource().equals(descargar)) {
            Thread h = new Thread() {
                public void run() {
                    descargar.setEnabled(false);
                    short tam = (short) botones.length;
                    int pos = 0;
                    for (short i = 0; i < tam; i++) {
                        if (botones[i].isSelected()) {
                            pos = i;
                            break;
                        }
                    }

                    int size = busqueda.getArchivos().get(pos).getNodos().size();
                    Thread hilo[] = new Thread[size];

                    for (int i = 0; i < size; i++) {
                        Nodo n = busqueda.getArchivos().get(pos).getNodos().get(i);

                        try { 
                            hilo[i] = recibirArchivo(n, size, i);
                        } catch (NotBoundException ex) {
                            Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        hilo[i].start();
                    }
                    /*
            for(int i = 0; i < size; i++)
            {
                try {
                    hilo[i].join();
                } catch (InterruptedException ex) 
                {
                    Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                     */
                    boolean noSalir = true;
                    while (noSalir) {
                        boolean noentro = true;
                        for (int i = 0; i < size; i++) {
                            if (hilo[i].getState() != Thread.State.TERMINATED) {
                                noentro = false;
                            }
                        }
                        if (noentro) {
                            noSalir = false;
                        }
                    }

                    if (size > 1) {
                        RecibirArchivo.unirArchivos(size, busqueda.getNombreArchivo(), "Carpetas/" + puerto + "/");
                    } else {
                        File f = new File("Carpetas/" + puerto + "/" + "(0)" + busqueda.getNombreArchivo());
                        f.renameTo(new File("Carpetas/" + puerto + "/" + busqueda.getNombreArchivo()));
                    }
                    botones = null;
                    JPanel j = new JPanel();
                    j.setBackground(Color.WHITE);
                    archivos.setViewportView(j);
                    logTemporal += ("Archivo: " + busqueda.getNombreArchivo() + " transferido<br>");
                    log.setText(logTemporal);
                    busqueda = new Busqueda();
                    progreso.setValue(0);
                    progresoEnvio.setValue(0);
                }
            };
            h.start();

        }
    }
    
    private RecibirArchivo recibirArchivo(Nodo n, int numNodos, int nodo) 
            throws NotBoundException, FileNotFoundException, IOException
    {
        return new RecibirArchivo(n.getIp(), n.getPuerto(), numNodos, nodo,
                                busqueda.getNombreArchivo(), puerto, this);
    }
    
    private void actualizarMulticast() throws IOException
    {
        Mensaje mensaje = AdministradorDeOperacionesMulticast.getInstance().recibe();
        if (mensaje.getNombreOrigen() != null) {
            switch (mensaje.getId()) {
                case INICIO_ID:
                    if (!mensaje.getNombreOrigen().equals(nodo.getId())) 
                    {
                        if(inicioNodo(mensaje.getNombreOrigen()))
                        {
                            actualizarListaDeNodos();
                            nodoSiguiente = getNodoSiguiente(puerto);
                            nodoAnterior = getNodoAnterior(puerto);
                            idAnterior.setText(nodoAnterior.getId());
                            idSiguiente.setText(nodoSiguiente.getId());
                        }
                    }
                    break;
                case FIN_ID:
                    finalizarNodo(mensaje.getNombreOrigen());
                    break;
                
                default:

            }
        }
    }
    
    private void finalizarNodo(String id)
    {
        if (finNodo(id)) 
        {
            actualizarListaDeNodos();
            if (nodos.size() > 0) 
            {
                nodoSiguiente = getNodoSiguiente(puerto);
                nodoAnterior = getNodoAnterior(puerto);
                idAnterior.setText(nodoAnterior.getId());
                idSiguiente.setText(nodoSiguiente.getId());
            } 
            else 
            {
                idAnterior.setText("");
                idSiguiente.setText("");
            }
        }
    }
    
    private void agregarArchivoABusqueda(Busqueda busqueda, String md5, Nodo nodo)
    {
        int tam = busqueda.getArchivos().size();
        boolean noEncontrado = true;
        if(tam > 0)
        {
            for(int i = 0; i < tam; i++ )
            {
               if(busqueda.getArchivos().get(i).getMd5().equals(md5))
               {
                   busqueda.getArchivos().get(i).getNodos().add(nodo);
                   noEncontrado = false;
                   break;
               }
            }
            if(noEncontrado)
            {
                Archivo a = new Archivo();
                a.setMd5(md5);
                a.getNodos().add(nodo);
                busqueda.getArchivos().add(a);
            }
        }
        else
        {
            Archivo a = new Archivo();
            a.setMd5(md5);
            a.getNodos().add(nodo);
            busqueda.getArchivos().add(a);
        }
    }
    
    private void actualizarDatagrama() throws IOException, Exception
    {
        Busqueda busqueda = adopd.recibe();
        busqueda.imprimir();
        
        switch (busqueda.getId()) 
        {
            case BUSCAR:
                
                logTemporal += ("Buscando: " + busqueda.getNombreArchivo() + "<br>");
                log.setText(logTemporal);
                if (operacionesNodo.buscarArchivo(busqueda.getNombreArchivo())) 
                {
                    String md5 = operacionesNodo.getChecksum(busqueda.getNombreArchivo());
                    logTemporal += ("Archivo: " + busqueda.getNombreArchivo() + " encontrado<br>");
                    logTemporal += ("MD5: " + md5 + "<br>");
                    log.setText(logTemporal);
                    agregarArchivoABusqueda(busqueda, md5, nodo);
                } 
                else 
                {
                    logTemporal += ("Archivo: " + busqueda.getNombreArchivo() + " NO encontrado<br>");
                    log.setText(logTemporal);
                }
                
                if (busqueda.getNodoOrigen().equals(nodoSiguiente.getId())) {
                    busqueda.setId(RESPUESTA);
                    adopd.enviarMensaje(busqueda, nodoAnterior.getIp(), nodoAnterior.getPuerto());
                } else {
                    adopd.enviarMensaje(busqueda, nodoSiguiente.getIp(), nodoSiguiente.getPuerto());
                }
                break;
                
            case RESPUESTA:
                
                if(busqueda.getArchivos().size() == 0)
                {
                    logTemporal += ("Archivo: " + busqueda.getNombreArchivo() + " no encontrado "
                            + "en el anillo<br>");
                    log.setText(logTemporal);
                }
                else
                {
                    logTemporal+= ("Archivo " + busqueda.getNombreArchivo() + " localizado en el anillo<br>");
                    log.setText(logTemporal);
                }
                
                if(busqueda.getNodoOrigen().equals(nodo.getId()))
                {
                    JPanel panel = new JPanel(new GridLayout(5, 1));
                    panel.setBackground(Color.WHITE);
                    this.busqueda = busqueda;
                    //Mostrar opcones de descarga
                    if(busqueda.getArchivos().size() > 0)
                    {
                        botones = new JRadioButton[busqueda.getArchivos().size()];
                        for(int i = 0; i < busqueda.getArchivos().size(); i++)
                        {
                            String informacion = "<html><body>";
                            informacion += busqueda.getNombreArchivo() + "<br>";
                            informacion += "md5: " + busqueda.getArchivos().get(i).getMd5() + "<br>";
                            
                            for(int j = 0; j < busqueda.getArchivos().get(i).getNodos().size(); j++)
                            {
                                informacion += (busqueda.getArchivos().get(i).getNodos().get(j).getId() + "<br>");
                            }
                            informacion += "</body></body>";
                            botones[i] = new JRadioButton(informacion);
                            botones[i].addActionListener(new ActionListener(){
                                @Override
                                public void actionPerformed(ActionEvent ae) {
                                    for(short i = 0; i < botones.length; i++)
                                    {
                                        if(ae.getSource().equals(botones[i]))
                                        {
                                            botones[i].setSelected(true);
                                        }
                                        else
                                        {
                                            botones[i].setSelected(false);
                                        }
                                    }
                                    descargar.setEnabled(true);
                                }
                            });
                            panel.add(botones[i]);
                        }
                        archivos.setViewportView(panel);
                    }
                    
                   
                    
                }
                else
                {
                    adopd.enviarMensaje(busqueda, nodoAnterior.getIp(), nodoAnterior.getPuerto());
                    
                }
                
                break;
                
            default:

        }
        
    }
    
    
    private boolean esElMayor(int idNodo)
    {
        int tam = nodos.size();
        Nodo n;
        for(short i = 0; i < tam ; i++)
        {
            n = (Nodo) nodos.get(i);
            if(n.getPuerto() > idNodo)
                   return false;
        }
        return true;
        
    }
    
    private boolean esElMenor(int idNodo)
    {
        int tam = nodos.size();
        Nodo n;
        for(short i = 0; i < tam ; i++)
        {
            n = (Nodo) nodos.get(i);
            if(idNodo > n.getPuerto())
                   return false;
        }
        return true;
    }
    
    private Nodo getNodoMenor()
    {
        int tam = nodos.size();
        Nodo n = (Nodo) nodos.get(0);
        int menor = n.getPuerto();
        int j = 0;
        for (short i = 1; i < tam; i++) 
        {
            n = (Nodo) nodos.get(i);
            if (n.getPuerto() < menor) 
            {
                menor = n.getPuerto();
                j = i;
            }
        }
        return (Nodo) nodos.get(j);
    }
    
    private Nodo getNodoMayor()
    {
        int tam = nodos.size();
        Nodo n = (Nodo) nodos.get(0);
        int mayor = n.getPuerto();
        int j = 0;
        for (short i = 1; i < tam; i++) 
        {
            n = (Nodo) nodos.get(i);
            if (n.getPuerto() > mayor) 
            {
                mayor = n.getPuerto();
                j = i;
            }
        }
        return (Nodo) nodos.get(j);
    }
    
    
    private Nodo getNodoSiguiente(int idNodoPuerto)
    {
        Nodo n = null;
        if(esElMayor(idNodoPuerto))
        {
            n = getNodoMenor();
        }
        else
        {
            int tam = nodos.size();
            int resta = 2147483647;
            int aux, j = 0;
            for (short i = 0; i < tam; i++) 
            {
                n = (Nodo) nodos.get(i);
                aux = n.getPuerto() - idNodoPuerto;
                if (aux < resta && aux > 0) 
                {
                    resta = aux;
                    j = i;
                }
            }
            n = (Nodo) nodos.get(j);
        }
        return n;
        
    }
    
    private Nodo getNodoAnterior(int idNodoPuerto)
    {
        Nodo n = null;
        if(esElMenor(idNodoPuerto))
        {
            n = getNodoMayor();
        }
        else
        {
            
            int tam = nodos.size();
            int resta = 2147483647;
            int aux, j = 0;
            for (short i = 0; i < tam; i++) {
                n = (Nodo) nodos.get(i);
                aux =  idNodoPuerto - n.getPuerto();
                if (aux < resta && aux > 0) 
                {
                    resta = aux;
                    j = i;
                }
            }
            n = (Nodo) nodos.get(j);
        }
        return n;
        
    }
    
    
    private void actualizarListaDeNodos()
    {
        nodosDisponibles = "";
        int tam = nodos.size();
        Nodo n;
        for(short i = 0; i < tam ; i++)
        {
            n = (Nodo) nodos.get(i);
            nodosDisponibles += ("<br>" + n.getId());  
        }
        nodosConectados.setText(nodosDisponibles);
    }
    
    private boolean finNodo(String id)
    {
        int tam = nodos.size();
        Nodo n;
        for(short i = 0; i < tam ; i++)
        {
            n = (Nodo) nodos.get(i);
            if(n.getId().equals(id))
            {
                nodos.remove(i);
                return true;
            }
        }
        return false;
        
    }
    
    private boolean inicioNodo(String id)
    {
        int tam = nodos.size();
        Nodo n;
        for(short i = 0; i < tam ; i++)
        {
            n = (Nodo) nodos.get(i);
            if(n.getId().equals(id))
            {
                n.temporalizador = 11;
                return false;
            }
        }
        n = new Nodo(id, getIP(id), getPuerto(id));
        n.temporalizador = 11;
        nodos.add(n);
        return true;
    }
    
    
    private int getPuerto(String idNodo)
    {
        String puerto = "";
        char c;
        int i = 0;
        while((c = idNodo.charAt(i)) != ':')
        {
            i++;
        }
        puerto = idNodo.substring(i + 1);
        
        return Integer.parseInt(puerto);
    }
    
    private String getIP(String idNodo)
    {
        String ip = "";
        char c;
        int i = 0;
        while((c = idNodo.charAt(i)) != ':')
        {
            ip+=c;
            i++;
        }
        return ip;
    }
    
    private void decrementarServidor()
    {
        Thread hilo = new Thread() {
            public void run() 
            {
                while (true) 
                {
                    Nodo n;
                    for(short i = 0; i < nodos.size(); i++)
                    {
                        n = (Nodo) nodos.get(i);
                        n.temporalizador--;
                        if(n.temporalizador < 1)
                        {
                            finalizarNodo(n.getId());
                            logTemporal += (n.getId() + " ha salido con error<br>");
                            log.setText(logTemporal);
                        }
                    }

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    
                }
                
            }
        };

        hilo.start();
    }
    
    private void anunciar()
    {
        Thread hilo = new Thread() {
            public void run() 
            {
                while (true) {
                    try {

                        AdministradorDeOperacionesMulticast.getInstance().anunciar(nodo.getId());
                        TimeUnit.SECONDS.sleep(5);

                    } catch (IOException ex) {
                        Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
        };

        hilo.start();
    }
    
    private void actualizarListaDeNodosMulticast()
    {
        Thread hilo = new Thread() {
            public void run() 
            {
                while (true) {
                    try {
                       actualizarMulticast();
                    } catch (IOException ex) {
                        Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };

        hilo.start();
    }
    
    
    private void recibirDatagrama()
    {
        Thread hilo = new Thread() {
            public void run() 
            {
                while (true) 
                {
                    try {
                        actualizarDatagrama();
                    } catch (IOException ex) {
                        Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };

        hilo.start();
    }
    
    private void envio()
    {
        try {
            EnviarArchivo ea = new EnviarArchivo(puerto, this);
        } catch (RemoteException ex) {
            Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AlreadyBoundException ex) {
            Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void enviarArchivo()
    {
        Thread hilo = new Thread() {
            public void run() 
            {
                progreso.setValue(0);
                envio();
                
            }
        };

        hilo.start();
    }

    @Override
    public void mensaje(String message) 
    {
        logTemporal += (message + "<br>");
        log.setText(logTemporal);
    }

    @Override
    public void bytesEnviados(int bytes) 
    {
        this.bytesEnviados += bytes;
        this.progresoEnvio.setValue((int) (bytesEnviados * 100 / this.tamArchivoEnvio));
    }

    @Override
    public void bytesRecibidos(int bytes) 
    {
        this.bytesRecibidos += bytes;
        this.progreso.setValue((int)(bytesRecibidos * 100 / tamArchivoRecepcion));
    }

    @Override
    public void cantBytesEnviar(long bytes) 
    {
        
        tamArchivoEnvio = bytes;
        logTemporal += ("Cantidad de bytes a enviar: " + bytes + "\n");
        log.setText(logTemporal);
               
    }

    @Override
    public void cantBytesRecibir(long bytes) 
    {  
        logTemporal += ("Cantidad de bytes a recibir: " + bytes + "\n");
        log.setText(logTemporal);
        tamArchivoRecepcion = bytes;
    }

    
    

    
    
   
}
