/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author miguel
 */
public class DialogoPuerto extends javax.swing.JDialog implements ActionListener
{

    public int getPuerto() 
    {
        return numeroPuerto;
    }
    
    private int numeroPuerto;
    /**
     * Creates new form DialogoPuerto
     */
    public DialogoPuerto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
    }

    private void init()
    {
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        aceptar.addActionListener(this);
        jPfondo.setBackground(Color.WHITE);
        numeroPuerto = -1;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPfondo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        puerto = new javax.swing.JTextField();
        aceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(0, 153, 153));

        jPfondo.setBackground(new java.awt.Color(0, 204, 204));
        jPfondo.setToolTipText("");

        jLabel1.setText("Número de puerto");

        aceptar.setText("Aceptar");

        javax.swing.GroupLayout jPfondoLayout = new javax.swing.GroupLayout(jPfondo);
        jPfondo.setLayout(jPfondoLayout);
        jPfondoLayout.setHorizontalGroup(
            jPfondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPfondoLayout.createSequentialGroup()
                .addContainerGap(86, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
            .addGroup(jPfondoLayout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(puerto, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPfondoLayout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(aceptar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPfondoLayout.setVerticalGroup(
            jPfondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPfondoLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(puerto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(aceptar)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPfondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPfondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPfondo;
    private javax.swing.JTextField puerto;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        if(ae.getSource().equals(aceptar))
        {
            String aux = puerto.getText();
            if(UIFunctions.isInteger(aux))
            {
                numeroPuerto = Integer.parseInt(aux);
                if(numeroPuerto < 9000 || numeroPuerto > 9009)
                {
                    UIFunctions.warningMessage("Puerto invalido: "
                            + "9000 >= puerto <= 9009", "Error");
                }
                else
                {
                    dispose();
                }
            }else
            {
                UIFunctions.warningMessage("El puerto no es un número", "Error");
            }
        }
    }
}