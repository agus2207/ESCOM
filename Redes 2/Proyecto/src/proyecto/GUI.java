package proyecto;

import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.*;

public class GUI extends javax.swing.JFrame {

    private int ptoRMI = 1092;
    private int ptoFlujo = 7002;
    private int ptoM = 2008; // El 2002 está ocupado siempre por el cliente multicast. El 2005 es usado por el Servidor Principal
    ArrayList a = new ArrayList();
    ArrayList cont = new ArrayList();
    /**
     * Creates new form GUI
     */
    public GUI() {
        initComponents();
        
        ServerMulticast sm = new ServerMulticast(ptoM, ptoRMI, ptoFlujo);
        String aux = ptoRMI + ":" + ptoFlujo;
        ClienteMulticas cm = new ClienteMulticas(a, aux, areaServidores, cont);

        ServidorRMI srmi = new ServidorRMI(ptoRMI, "Documents");
        srmi.start();

        ServidorFlujo sflujo = new ServidorFlujo(ptoFlujo);
        sflujo.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        campoBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        lblResultados = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaResultados = new javax.swing.JTable();
        lblDescargas = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaDescargas = new javax.swing.JTable();
        lblServidores = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        areaServidores = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Descarga de archivos P2P");
        setBackground(new java.awt.Color(51, 204, 0));
        setLocationByPlatform(true);
        setResizable(false);

        btnBuscar.setText("Tengo Suerte");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        lblResultados.setText("Resultados:");

        tablaResultados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "MD5", "Estado", "Progreso"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaResultados.setPreferredSize(new java.awt.Dimension(225, 92));
        jScrollPane1.setViewportView(tablaResultados);

        lblDescargas.setText("Lista de Descargas");

        tablaDescargas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "MD5", "Tamaño (bytes)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaDescargas.setPreferredSize(new java.awt.Dimension(225, 92));
        jScrollPane2.setViewportView(tablaDescargas);

        lblServidores.setText("Usuarios Activos");

        areaServidores.setEditable(false);
        areaServidores.setColumns(20);
        areaServidores.setRows(5);
        jScrollPane3.setViewportView(areaServidores);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDescargas)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblServidores)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblResultados)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(campoBuscar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnBuscar))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
                        .addComponent(jScrollPane3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(campoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscar))
                    .addComponent(lblDescargas, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblResultados)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblServidores)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        int i, filas, encontrados = 0;

        DefaultTableModel modelo = (DefaultTableModel) tablaResultados.getModel();

        filas = modelo.getRowCount();

        // Borra los resultados de la búsqueda anterior
        for (i = 0; i < filas; i++) {
            modelo.removeRow(0);
        }
        
        // Se crea un cliente RMI y se solicita la búsqueda del archivos
        ClienteRMI crmi = new ClienteRMI();
        Object[][] resultados = crmi.busqueda(campoBuscar.getText(), a);// Se obtiene  una lista con los nombres de los archivos, 
                                                                          // los md5, las rutas donde fueron buscados, el tamaño, 
                                                                          // la dirección IP de los servidores, y los puertos de Flujo

        JProgressBar[] barras = new JProgressBar[resultados.length];
        
        // Se cargan los resultados de búsqueda en la tabla
        for (i = 0; i < resultados.length; i++) {
            barras[i] = new JProgressBar(0, 100);
            barras[i].setString("0%");
            barras[i].setStringPainted(true);
            if (!resultados[i][0].equals("-1") && !resultados[i][1].equals("-1")) {
                Object[] aux = new Object[4];

                aux[0] = resultados[i][0];
                aux[1] = resultados[i][1];
                aux[2] = "No descargado";
                aux[3] = barras[i]; // Porcentaje o barra de progreso

                modelo.addRow(aux);
                encontrados++;
            }

        }
        tablaResultados.setModel(modelo);
        tablaResultados.getColumn("Progreso").setCellRenderer(new CellRendered());

        int[] indexResults = new int[resultados.length];
        int coincidencias = 0;

        for (i = 0; i < indexResults.length; i++) {
            indexResults[i] = -1;
        }

        if (encontrados == 0) {
            JOptionPane.showMessageDialog(this, "Archivo no encontrado", "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
        } else {
            for (i = 0; i < resultados.length; i++) {
                if (!resultados[i][0].equals("-1") && !resultados[i][1].equals("-1")) {
                    indexResults[0] = i;
                    coincidencias++;
                    for (int j = i + 1; j < resultados.length; j++) {
                        if (resultados[i][1].equals(resultados[j][1])) {
                            indexResults[j] = j;
                            coincidencias++;
                        }
                    }
                    break;
                }
            }
            ClienteFlujo cflujo = new ClienteFlujo();
            
            if (coincidencias == 1) {
                // Descarga individual
                String host = resultados[indexResults[0]][4].toString();
                int puerto = Integer.parseInt(resultados[indexResults[0]][5].toString());
                String ruta = resultados[indexResults[0]][2].toString() + resultados[indexResults[0]][0].toString();
                
                cflujo.descarga(host, puerto, ruta, barras[indexResults[0]], tablaResultados);
            } else {
                // Descarga distribuida
                String hosts[] = new String[coincidencias];
                int ptos[] = new int[coincidencias];
                String rutas[] = new String[coincidencias];
                JProgressBar barrasAux[] = new JProgressBar[coincidencias];
                
                for (i = 0; i < indexResults.length; i++) {
                    if (indexResults[i] != -1) {
                        System.out.println("Archivo encontrado en: " + resultados[indexResults[i]][4] + ":" + resultados[indexResults[i]][5]);
                        hosts[i] = resultados[indexResults[i]][4].toString();
                        ptos[i] = Integer.parseInt(resultados[indexResults[i]][5].toString());
                        rutas[i] = resultados[indexResults[i]][2].toString() + resultados[indexResults[i]][0].toString();
                        barrasAux[i] = barras[indexResults[i]];
                    } else {
                        break;
                    }
                }
                cflujo.descarga(hosts, ptos, rutas, barrasAux, tablaResultados, Long.parseLong(resultados[indexResults[0]][3].toString()));
            }
            modelo = (DefaultTableModel) tablaDescargas.getModel();
            Object[] fila = new Object[3];

            fila[0] = resultados[indexResults[0]][0];
            fila[1] = resultados[indexResults[0]][1];
            fila[2] = resultados[indexResults[0]][3];

            modelo.addRow(fila);
            tablaDescargas.setModel(modelo);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

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
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaServidores;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JTextField campoBuscar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblDescargas;
    private javax.swing.JLabel lblResultados;
    private javax.swing.JLabel lblServidores;
    private javax.swing.JTable tablaDescargas;
    private javax.swing.JTable tablaResultados;
    // End of variables declaration//GEN-END:variables
}

class CellRendered implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        return (JProgressBar) value;
    }
}
