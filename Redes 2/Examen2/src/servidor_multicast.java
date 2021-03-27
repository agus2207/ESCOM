
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author agust
 */
public class servidor_multicast extends javax.swing.JFrame {

    public static DefaultListModel modelo;
    /**
     * Creates new form servidor_multicast
     */
    public servidor_multicast() {
        initComponents();
        jEditorPane1.setContentType("text/html");
        jEditorPane1.setEditable(false);
        jEditorPane1.setText("");
        modelo = new DefaultListModel();
        jList1.setModel(modelo);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextPane3 = new javax.swing.JTextPane();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 255, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel1.setText("Servidor multicast");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel2.setText("Servidores Disponibles");

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        jButton1.setText("Actualizar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Agregar Nodo");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel3.setText("Nodo Anterior");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel4.setText("Nodo Actual");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel5.setText("Nodo Siguiente");

        jScrollPane2.setViewportView(jTextPane1);

        jScrollPane3.setViewportView(jTextPane2);

        jScrollPane4.setViewportView(jTextPane3);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel6.setText("Notificaciones");

        jScrollPane5.setViewportView(jEditorPane1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(234, 234, 234)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButton1)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton2)))
                                .addGap(66, 66, 66)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel4)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel3)
                                            .addGap(18, 18, 18)
                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel6)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel1)
                        .addGap(45, 45, 45)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(servidor_multicast.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(servidor_multicast.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(servidor_multicast.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(servidor_multicast.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        new servidor_multicast().setVisible(true);
        String cadena = "";
        InetAddress gpo = null;
        DatagramPacket p;
        DatagramPacket p1;
        ArrayList<String> servidores = new ArrayList();
        int pos = 0;
        //DatagramPacket p2;
        //int cantidad = 2;
        String aux2 = new String();
        //int i = 0;
        try{
            MulticastSocket cl = new MulticastSocket(4000); //se envia el paquete en el puerto 9876
            cl.setReuseAddress(true);
            try{
                gpo = InetAddress.getByName("228.1.1.1");
            }
            catch(UnknownHostException e){
                JOptionPane.showMessageDialog(null, "Direccion no válida");
            }
            cl.joinGroup(gpo);
            for(;;){
                p = new DatagramPacket(new byte[2048], 2048); 
                cl.receive(p);  
                String msj = new String(p.getData());
                System.out.println("Mensaje recibido: "+msj);
               
                if(msj.contains("Inicio")){
                    System.out.println("Inicio de la busqueda");
                    jEditorPane1.setText("Cliente --> Solicito archivo");
                    jTextPane1.removeAll();
                    jTextPane1.setText("No hay anterior");
                    jTextPane2.removeAll();
                    if(modelo.size() > 1){
                        String siguiente = (String)modelo.getElementAt(1);
                        jTextPane2.setText(siguiente);
                    }
                    else
                        jTextPane2.setText("No hay siguiente");
                    jTextPane3.removeAll();
                    String actual = (String)modelo.getElementAt(0);
                    jTextPane3.setText(actual);
                }
                
                else if(msj.contains("Anterior")){
                    System.out.println("Archivo encontrado");
                     if(pos != 0){
                        String posicion = (String)modelo.getElementAt(pos);
                        jEditorPane1.setText(posicion+"---->Archivo Encontrado");
                        jTextPane1.removeAll();
                        String anterior = (String)modelo.getElementAt(pos-1);
                        jTextPane1.setText(anterior);
                        if(pos+1 >= modelo.size()-1){
                            jTextPane2.removeAll();
                            jTextPane2.setText("No hay siguiente");
                        }
                        else{
                            jTextPane2.removeAll();
                            String siguiente = (String)modelo.getElementAt(pos+1);
                            jTextPane2.setText(siguiente);
                        }
                        jTextPane3.removeAll();
                        String actual = (String)modelo.getElementAt(pos);
                        jTextPane3.setText(actual);
                        pos--;
                    }
                    else{
                        String posicion = (String)modelo.getElementAt(pos);
                        jEditorPane1.setText(posicion+"---->Archivo Encontrado");
                        jTextPane1.removeAll();
                        jTextPane1.setText("No hay anterior");
                        if(pos+1 >= modelo.size()-1){
                            jTextPane2.removeAll();
                            jTextPane2.setText("No hay siguiente");
                        }
                        else{
                            jTextPane2.removeAll();
                            String siguiente = (String)modelo.getElementAt(pos+1);
                            jTextPane2.setText(siguiente);
                        }
                        jTextPane3.removeAll();
                        String actual = (String)modelo.getElementAt(pos);
                        jTextPane3.setText(actual);
                    }
                }
                        
                else if(msj.contains("Siguiente")){
                    System.out.println("Archivo no encontrado");
                    if(pos < modelo.getSize()-1){
                        String posicion = (String)modelo.getElementAt(pos);
                        jEditorPane1.setText(posicion+"---->Archivo no encontrado");
                        if(pos == 0){
                            jTextPane1.removeAll();
                            jTextPane1.setText("No hay anterior");
                            if(pos+1 >= modelo.size()-1){
                                jTextPane2.removeAll();
                                jTextPane2.setText("No hay siguiente");
                            }
                            else{
                                jTextPane2.removeAll();
                                String siguiente = (String)modelo.getElementAt(pos+1);
                                jTextPane2.setText(siguiente);
                            }
                            jTextPane3.removeAll();
                            String actual = (String)modelo.getElementAt(pos);
                            jTextPane3.setText(actual);
                            pos++;
                        }
                        else{
                            jTextPane1.removeAll();
                            String anterior = (String)modelo.getElementAt(pos-1);
                            jTextPane1.setText(anterior);
                            if(pos+1 >= modelo.size()-1){
                                jTextPane2.removeAll();
                                jTextPane2.setText("No hay siguiente");
                            }
                            else{
                                jTextPane2.removeAll();
                                String siguiente = (String)modelo.getElementAt(pos+1);
                                jTextPane2.setText(siguiente);
                            }
                            jTextPane3.removeAll();
                            String actual = (String)modelo.getElementAt(pos);
                            jTextPane3.setText(actual);
                            pos++;
                        }
                        
                    }
                    else{
                        String posicion = (String)modelo.getElementAt(pos);
                        jEditorPane1.setText(posicion+"---->Archivo no encontrado");
                        if(pos == 0){
                            jTextPane1.removeAll();
                            jTextPane1.setText("No hay anterior");
                            jTextPane2.removeAll();
                            jTextPane2.setText("No hay siguiente");
                            jTextPane3.removeAll();
                            String actual = (String)modelo.getElementAt(pos);
                            jTextPane3.setText(actual);
                        }
                        else{
                            jTextPane1.removeAll();
                            String anterior = (String)modelo.getElementAt(pos-1);
                            jTextPane1.setText(anterior);
                            jTextPane2.removeAll();
                            jTextPane2.setText("No hay siguiente");
                            jTextPane3.removeAll();
                            String actual = (String)modelo.getElementAt(pos);
                            jTextPane3.setText(actual);
                        }
                    }
                }
                        
                else if(msj.contains("Cliente                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 ")){
                    System.out.println("Se conecto el cliente");
                    for (int i = 0; i < servidores.size(); i++) {
                        modelo.addElement(servidores.get(i));
                        byte[] b = servidores.get(i).getBytes();
                        p1 = new DatagramPacket(b, b.length, gpo, 4000);
                        cl.send(p1);
                    }
                } 
                
                else if(msj.contains("Mensaje")){
                    //String posicion = (String)modelo.getElementAt(pos);
                    jEditorPane1.setText(msj);
                }
                
                else {
                    System.out.println("Recibi un nombre");
                    aux2 += msj;
                    Pattern regex = Pattern.compile("\\b" + Pattern.quote(msj) + "\\b", Pattern.CASE_INSENSITIVE);
                    Matcher match = regex.matcher(aux2);
                    if (!match.find()) {
                        servidores.add(msj);
                    }
                    Collections.sort(servidores);
                }
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public javax.swing.JButton jButton1;
    public javax.swing.JButton jButton2;
    public static javax.swing.JEditorPane jEditorPane1;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JList<String> jList1;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JScrollPane jScrollPane3;
    public javax.swing.JScrollPane jScrollPane4;
    public javax.swing.JScrollPane jScrollPane5;
    public static javax.swing.JTextPane jTextPane1;
    public static javax.swing.JTextPane jTextPane2;
    public static javax.swing.JTextPane jTextPane3;

/*
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPane2;
    private javax.swing.JTextPane jTextPane3;
    // End of variables declaration//GEN-END:variables
*/}