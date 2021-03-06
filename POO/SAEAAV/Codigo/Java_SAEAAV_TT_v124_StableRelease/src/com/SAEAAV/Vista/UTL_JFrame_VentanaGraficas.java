/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.SAEAAV.Vista;

import com.SAEAAV.Modelo.Principal_Modelo;
import java.text.DecimalFormat;

/**
 *
 * @author Checo
 */
public class UTL_JFrame_VentanaGraficas extends javax.swing.JFrame {

    ///Atributos_Checo
    private Principal_Modelo objPrincipal_Modelo=null;
    private javax.swing.JFrame jFrame_VentanaInvoca=null;
    private javax.swing.JButton jButton_Graficacion=null;
    
    // Interpolación
    private Double interpolacionValorMaximo=null;
    private Double interpolacionValorMínimo=null;
    private boolean seRealizoInterpolacion=false;
    DecimalFormat formateadorTextoNumeros;
    
    /**
     * Creates new form UTL_JFrame_VentanaGraficas
     */
    public UTL_JFrame_VentanaGraficas() {
        
        initComponents();
    }
    @Deprecated
    public void inicializarVentanaGraficas(Principal_Modelo objPrincipal_Modelo)
    {        
        this.objPrincipal_Modelo=objPrincipal_Modelo;
        
        if(this.objPrincipal_Modelo==null)return;

        this.objPrincipal_Modelo.setjPanel_Graficas(jPanel_Graficas);
        this.objPrincipal_Modelo.setjScrollBar_RotacionX(jScrollBar_RotacionX);
        this.objPrincipal_Modelo.setjScrollBar_RotacionY(jScrollBar_RotacionY);
        this.objPrincipal_Modelo.setjScrollBar_RotacionZ(jScrollBar_RotacionZ);
        this.objPrincipal_Modelo.setjTextField_RotarX(jTextField_RotarX);
        this.objPrincipal_Modelo.setjTextField_RotarY(jTextField_RotarY);
        this.objPrincipal_Modelo.setjTextField_RotarZ(jTextField_RotarZ);
        
        this.objPrincipal_Modelo.graficarVisualizarResultados();
        
        // Interpolación
        this.interpolacionValorMaximo=this.objPrincipal_Modelo.getInterpolacionValorMaximo();
        this.interpolacionValorMínimo=this.objPrincipal_Modelo.getInterpolacionValorMínimo();
        this.seRealizoInterpolacion=this.objPrincipal_Modelo.isSeRealizoInterpolacion();
        this.objPrincipal_Modelo.setSeRealizoInterpolacion(false);
        this.formateadorTextoNumeros = new DecimalFormat("0.00");//"###0.00"
        
        if(this.seRealizoInterpolacion)
        {
            //Activamos los controles
            this.jLabel_Interpolacion_Titulo.setEnabled(true);
            this.jLabel_Interpolacion_valueMax.setEnabled(true);
            this.jLabel_Interpolacion_valueMin.setEnabled(true);
            this.jLabel_Interpolacion_BarraDeColores.setEnabled(true);
            
            //Mandamos datos
            String valueMax=this.formateadorTextoNumeros.format(this.interpolacionValorMaximo);
            String valueMin=this.formateadorTextoNumeros.format(this.interpolacionValorMínimo);
            this.jLabel_Interpolacion_valueMax.setText("Max="+valueMax);
            this.jLabel_Interpolacion_valueMin.setText("Min="+valueMin);
        }
    }
    public void inicializarVentanaGraficas(Principal_Modelo objPrincipal_Modelo, javax.swing.JButton jButton_Graficacion)
    {
        // Bloquea el botón de graficación
        this.jButton_Graficacion=jButton_Graficacion;
        this.jButton_Graficacion.setEnabled(false);
        this.jButton_Graficacion.setVisible(false);
        
        this.objPrincipal_Modelo=objPrincipal_Modelo;
        
        if(this.objPrincipal_Modelo==null)return;

        this.objPrincipal_Modelo.setjPanel_Graficas(jPanel_Graficas);
        this.objPrincipal_Modelo.setjScrollBar_RotacionX(jScrollBar_RotacionX);
        this.objPrincipal_Modelo.setjScrollBar_RotacionY(jScrollBar_RotacionY);
        this.objPrincipal_Modelo.setjScrollBar_RotacionZ(jScrollBar_RotacionZ);
        this.objPrincipal_Modelo.setjTextField_RotarX(jTextField_RotarX);
        this.objPrincipal_Modelo.setjTextField_RotarY(jTextField_RotarY);
        this.objPrincipal_Modelo.setjTextField_RotarZ(jTextField_RotarZ);
        
        this.objPrincipal_Modelo.graficarVisualizarResultados();
        
        // Interpolación
        this.interpolacionValorMaximo=this.objPrincipal_Modelo.getInterpolacionValorMaximo();
        this.interpolacionValorMínimo=this.objPrincipal_Modelo.getInterpolacionValorMínimo();
        this.seRealizoInterpolacion=this.objPrincipal_Modelo.isSeRealizoInterpolacion();
        this.objPrincipal_Modelo.setSeRealizoInterpolacion(false);
        this.formateadorTextoNumeros = new DecimalFormat("0.00");//"###0.00"
        
        if(this.seRealizoInterpolacion)
        {
            //Activamos los controles
            this.jLabel_Interpolacion_Titulo.setEnabled(true);
            this.jLabel_Interpolacion_valueMax.setEnabled(true);
            this.jLabel_Interpolacion_valueMin.setEnabled(true);
            this.jLabel_Interpolacion_BarraDeColores.setEnabled(true);
            
            //Mandamos datos
            String valueMax=this.formateadorTextoNumeros.format(this.interpolacionValorMaximo);
            String valueMin=this.formateadorTextoNumeros.format(this.interpolacionValorMínimo);
            this.jLabel_Interpolacion_valueMax.setText("Max="+valueMax);
            this.jLabel_Interpolacion_valueMin.setText("Min="+valueMin);
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

        jPanel_Controles = new javax.swing.JPanel();
        jButton_Salir = new javax.swing.JButton();
        jTextField_RotarX = new javax.swing.JTextField();
        jTextField_RotarY = new javax.swing.JTextField();
        jTextField_RotarZ = new javax.swing.JTextField();
        jScrollBar_RotacionX = new javax.swing.JScrollBar();
        jScrollBar_RotacionY = new javax.swing.JScrollBar();
        jScrollBar_RotacionZ = new javax.swing.JScrollBar();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel_Herramientas_Camara = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jButton_CamaraMas = new javax.swing.JButton();
        jButton_CamaraMenos = new javax.swing.JButton();
        jPanel_Graficas = new javax.swing.JPanel();
        jLabel_Titulo = new javax.swing.JLabel();
        jLabel_Titulo1 = new javax.swing.JLabel();
        jPanel_Herramientas_Interpolacion = new javax.swing.JPanel();
        jLabel_Interpolacion_BarraDeColores = new javax.swing.JLabel();
        jLabel_Interpolacion_Titulo = new javax.swing.JLabel();
        jLabel_Interpolacion_valueMax = new javax.swing.JLabel();
        jLabel_Interpolacion_valueMin = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel_Controles.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_Controles.setBorder(new javax.swing.border.MatteBorder(null));

        jButton_Salir.setForeground(new java.awt.Color(255, 0, 0));
        jButton_Salir.setText("Salir");
        jButton_Salir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_SalirMouseClicked(evt);
            }
        });

        jTextField_RotarX.setEditable(false);
        jTextField_RotarX.setText("0");

        jTextField_RotarY.setEditable(false);
        jTextField_RotarY.setText("0");

        jTextField_RotarZ.setEditable(false);
        jTextField_RotarZ.setText("0");

        jScrollBar_RotacionX.setMaximum(369);
        jScrollBar_RotacionX.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
            public void adjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {
                jScrollBar_RotacionXAdjustmentValueChanged(evt);
            }
        });

        jScrollBar_RotacionY.setMaximum(369);
        jScrollBar_RotacionY.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
            public void adjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {
                jScrollBar_RotacionYAdjustmentValueChanged(evt);
            }
        });

        jScrollBar_RotacionZ.setMaximum(369);
        jScrollBar_RotacionZ.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
            public void adjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {
                jScrollBar_RotacionZAdjustmentValueChanged(evt);
            }
        });

        jLabel3.setText("X");

        jLabel4.setText("Y");

        jLabel5.setText("Z");

        jLabel6.setText("Girar Modelo");

        jPanel_Herramientas_Camara.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setText("Cámara");

        jButton_CamaraMas.setText("+");
        jButton_CamaraMas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_CamaraMasMouseClicked(evt);
            }
        });

        jButton_CamaraMenos.setText("-");
        jButton_CamaraMenos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_CamaraMenosMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel_Herramientas_CamaraLayout = new javax.swing.GroupLayout(jPanel_Herramientas_Camara);
        jPanel_Herramientas_Camara.setLayout(jPanel_Herramientas_CamaraLayout);
        jPanel_Herramientas_CamaraLayout.setHorizontalGroup(
            jPanel_Herramientas_CamaraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Herramientas_CamaraLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel_Herramientas_CamaraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_CamaraMenos)
                    .addComponent(jButton_CamaraMas)
                    .addComponent(jLabel7))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel_Herramientas_CamaraLayout.setVerticalGroup(
            jPanel_Herramientas_CamaraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Herramientas_CamaraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_CamaraMas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jButton_CamaraMenos)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel_ControlesLayout = new javax.swing.GroupLayout(jPanel_Controles);
        jPanel_Controles.setLayout(jPanel_ControlesLayout);
        jPanel_ControlesLayout.setHorizontalGroup(
            jPanel_ControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ControlesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_ControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel_ControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton_Salir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel_ControlesLayout.createSequentialGroup()
                            .addGroup(jPanel_ControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jTextField_RotarX, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollBar_RotacionX, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel_ControlesLayout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(17, 17, 17)))
                            .addGroup(jPanel_ControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel_ControlesLayout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel_ControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextField_RotarY)
                                        .addComponent(jScrollBar_RotacionY, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel_ControlesLayout.createSequentialGroup()
                                    .addGap(28, 28, 28)
                                    .addComponent(jLabel4)))
                            .addGroup(jPanel_ControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel_ControlesLayout.createSequentialGroup()
                                    .addGap(14, 14, 14)
                                    .addGroup(jPanel_ControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextField_RotarZ)
                                        .addComponent(jScrollBar_RotacionZ, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel_ControlesLayout.createSequentialGroup()
                                    .addGap(33, 33, 33)
                                    .addComponent(jLabel5))))))
                .addGap(18, 18, 18)
                .addComponent(jPanel_Herramientas_Camara, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel_ControlesLayout.setVerticalGroup(
            jPanel_ControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ControlesLayout.createSequentialGroup()
                .addGroup(jPanel_ControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_ControlesLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel_ControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel_ControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollBar_RotacionZ, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollBar_RotacionX, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollBar_RotacionY, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel_ControlesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel_Herramientas_Camara, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_ControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_RotarX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_RotarY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_RotarZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_Salir)
                .addGap(129, 129, 129))
        );

        jPanel_Graficas.setBackground(new java.awt.Color(102, 102, 102));
        jPanel_Graficas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        jPanel_Graficas.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jPanel_GraficasComponentResized(evt);
            }
        });

        javax.swing.GroupLayout jPanel_GraficasLayout = new javax.swing.GroupLayout(jPanel_Graficas);
        jPanel_Graficas.setLayout(jPanel_GraficasLayout);
        jPanel_GraficasLayout.setHorizontalGroup(
            jPanel_GraficasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 610, Short.MAX_VALUE)
        );
        jPanel_GraficasLayout.setVerticalGroup(
            jPanel_GraficasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel_Titulo.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel_Titulo.setText("Herramientas");

        jLabel_Titulo1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel_Titulo1.setText("Gráfica");

        jPanel_Herramientas_Interpolacion.setBackground(new java.awt.Color(255, 255, 255));

        jLabel_Interpolacion_BarraDeColores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/SAEAAV/Vista/Multimedia/BarraDeColores_Vertical_MaxRojo_MinAzul_2_PNG.png"))); // NOI18N
        jLabel_Interpolacion_BarraDeColores.setText(" ");
        jLabel_Interpolacion_BarraDeColores.setToolTipText("");
        jLabel_Interpolacion_BarraDeColores.setEnabled(false);

        jLabel_Interpolacion_Titulo.setText("Interpolación");
        jLabel_Interpolacion_Titulo.setEnabled(false);

        jLabel_Interpolacion_valueMax.setText("Max=n/a");
        jLabel_Interpolacion_valueMax.setEnabled(false);

        jLabel_Interpolacion_valueMin.setText("Min=n/a");
        jLabel_Interpolacion_valueMin.setEnabled(false);

        javax.swing.GroupLayout jPanel_Herramientas_InterpolacionLayout = new javax.swing.GroupLayout(jPanel_Herramientas_Interpolacion);
        jPanel_Herramientas_Interpolacion.setLayout(jPanel_Herramientas_InterpolacionLayout);
        jPanel_Herramientas_InterpolacionLayout.setHorizontalGroup(
            jPanel_Herramientas_InterpolacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Herramientas_InterpolacionLayout.createSequentialGroup()
                .addGroup(jPanel_Herramientas_InterpolacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_Herramientas_InterpolacionLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel_Interpolacion_BarraDeColores)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_Herramientas_InterpolacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_Interpolacion_valueMax)
                            .addComponent(jLabel_Interpolacion_valueMin)))
                    .addGroup(jPanel_Herramientas_InterpolacionLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel_Interpolacion_Titulo)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_Herramientas_InterpolacionLayout.setVerticalGroup(
            jPanel_Herramientas_InterpolacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Herramientas_InterpolacionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_Interpolacion_Titulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel_Herramientas_InterpolacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel_Interpolacion_BarraDeColores, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel_Herramientas_InterpolacionLayout.createSequentialGroup()
                        .addComponent(jLabel_Interpolacion_valueMax)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel_Interpolacion_valueMin)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_Titulo1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 525, Short.MAX_VALUE)
                .addComponent(jLabel_Titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(142, 142, 142))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel_Herramientas_Interpolacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel_Graficas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel_Controles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Titulo1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_Titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                .addComponent(jPanel_Herramientas_Interpolacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(58, 58, 58)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel_Controles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel_Graficas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_SalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_SalirMouseClicked
        this.objPrincipal_Modelo.accionSalir();
        if(this.jFrame_VentanaInvoca!=null)this.jFrame_VentanaInvoca.setEnabled(true);
        
        // Activar el botón de graficación
        if(this.jButton_Graficacion!=null)
        {
            this.jButton_Graficacion.setEnabled(true);
            this.jButton_Graficacion.setVisible(true);
        }
        
        
        this.dispose();
    }//GEN-LAST:event_jButton_SalirMouseClicked

    private void jScrollBar_RotacionXAdjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {//GEN-FIRST:event_jScrollBar_RotacionXAdjustmentValueChanged
        this.objPrincipal_Modelo.RotarX();
    }//GEN-LAST:event_jScrollBar_RotacionXAdjustmentValueChanged

    private void jScrollBar_RotacionYAdjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {//GEN-FIRST:event_jScrollBar_RotacionYAdjustmentValueChanged
        this.objPrincipal_Modelo.RotarY();
    }//GEN-LAST:event_jScrollBar_RotacionYAdjustmentValueChanged

    private void jScrollBar_RotacionZAdjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {//GEN-FIRST:event_jScrollBar_RotacionZAdjustmentValueChanged
        this.objPrincipal_Modelo.RotarZ();
    }//GEN-LAST:event_jScrollBar_RotacionZAdjustmentValueChanged

    private void jButton_CamaraMasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_CamaraMasMouseClicked
        this.objPrincipal_Modelo.CamaraMas();
    }//GEN-LAST:event_jButton_CamaraMasMouseClicked

    private void jButton_CamaraMenosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_CamaraMenosMouseClicked
        this.objPrincipal_Modelo.CamaraMenos();
    }//GEN-LAST:event_jButton_CamaraMenosMouseClicked

    private void jPanel_GraficasComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel_GraficasComponentResized
        this.objPrincipal_Modelo.PanelGraficas_Redimensionado(evt);
    }//GEN-LAST:event_jPanel_GraficasComponentResized

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        this.objPrincipal_Modelo.VentanaActiva();
    }//GEN-LAST:event_formWindowActivated

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(UTL_JFrame_VentanaGraficas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(UTL_JFrame_VentanaGraficas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(UTL_JFrame_VentanaGraficas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(UTL_JFrame_VentanaGraficas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new UTL_JFrame_VentanaGraficas().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_CamaraMas;
    private javax.swing.JButton jButton_CamaraMenos;
    private javax.swing.JButton jButton_Salir;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel_Interpolacion_BarraDeColores;
    private javax.swing.JLabel jLabel_Interpolacion_Titulo;
    private javax.swing.JLabel jLabel_Interpolacion_valueMax;
    private javax.swing.JLabel jLabel_Interpolacion_valueMin;
    private javax.swing.JLabel jLabel_Titulo;
    private javax.swing.JLabel jLabel_Titulo1;
    private javax.swing.JPanel jPanel_Controles;
    private javax.swing.JPanel jPanel_Graficas;
    private javax.swing.JPanel jPanel_Herramientas_Camara;
    private javax.swing.JPanel jPanel_Herramientas_Interpolacion;
    private javax.swing.JScrollBar jScrollBar_RotacionX;
    private javax.swing.JScrollBar jScrollBar_RotacionY;
    private javax.swing.JScrollBar jScrollBar_RotacionZ;
    private javax.swing.JTextField jTextField_RotarX;
    private javax.swing.JTextField jTextField_RotarY;
    private javax.swing.JTextField jTextField_RotarZ;
    // End of variables declaration//GEN-END:variables

    public void setObjPrincipal_Modelo(Principal_Modelo objPrincipal_Modelo) {
        this.objPrincipal_Modelo = objPrincipal_Modelo;
    }

    public void setjFrame_VentanaInvoca(javax.swing.JFrame jFrame_VentanaInvoca) {
        this.jFrame_VentanaInvoca = jFrame_VentanaInvoca;
    }
}
