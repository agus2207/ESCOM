///Paquete de Trabajo
package com.SAEAAV;

///Librerias
//import JOGL.JOGL_Principal;
import com.SAEAAV.Vista.Vista_Principal_GUI_JFrame;

public class Main_SAEAAV
{
    ///Atributos

    ///Constructores

    ///Métodos de propósito general

    ///Métodos de propósito específico
  
    ///Métodos para el manejo de atributos

    ///Punto de Acceso
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
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
//            java.util.logging.Logger.getLogger(Vista_Principal_GUI_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Vista_Principal_GUI_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Vista_Principal_GUI_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Vista_Principal_GUI_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        //</editor-fold>

//        boolean esWindows=false;
//        if(esWindows)
//        {
            //Variables
            JOGL.JOGL_Principal objJOGL_Principal;
            //Inicializamos
            objJOGL_Principal=new JOGL.JOGL_Principal();
            //Cargamos librerias
            objJOGL_Principal.Principal();
//        }
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vista_Principal_GUI_JFrame().setVisible(true);
            }
        });
    }
//    public static void main(String args[])
//    {
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
//            java.util.logging.Logger.getLogger(Vista_JFrame_VentanaGraficador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Vista_JFrame_VentanaGraficador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Vista_JFrame_VentanaGraficador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Vista_JFrame_VentanaGraficador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        //Variables
//        JOGL.JOGL_Principal objJOGL_Principal;
//        
//        //Inicializamos
//        objJOGL_Principal=new JOGL.JOGL_Principal();
//        
//        //Cargamos librerias
//        objJOGL_Principal.Principal();
//        
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Vista_JFrame_VentanaGraficador().setVisible(true);
//            }
//        });
//    }
}
