///Paquete de Trabajo
package com.SAEAAV.Modelo;

///Librerias
import com.PersistenciaDeDatos.Bean_CampoEscalar;
import com.PersistenciaDeDatos.Bean_CampoVectorial;
import com.PersistenciaDeDatos.Bean_OBEV;
import com.PersistenciaDeDatos.Constantes_Persistencia;
import com.PersistenciaDeDatos.Persistencia_UTL_Archivos;
import com.SAEAAV.Experimental.AltPrintScreen;
import com.SAEAAV.Modelo.Graficador.Artefactos.Artefacto;
import com.SAEAAV.Modelo.Graficador.Artefactos.BarraDeColores;
import com.SAEAAV.Modelo.Graficador.Artefactos.CamposEscalares;
import com.SAEAAV.Modelo.Graficador.Artefactos.CamposVectoriales;
import com.SAEAAV.Modelo.Graficador.Artefactos.Delimitadores;
import com.SAEAAV.Modelo.Graficador.Artefactos.EjesOrtogonales;
import com.SAEAAV.Modelo.Graficador.Artefactos.OperacionesBasicasEntreVectores;
import com.SAEAAV.Modelo.Graficador.RenderizadorDeGraficas3D;
import com.SAEAAV.Modelo.Matematicas.Constantes_Matematicas;
import com.SAEAAV.Modelo.Matematicas.Datos_CampoEscalar;
import com.SAEAAV.Modelo.Matematicas.Datos_CampoVectorial;
import com.SAEAAV.Modelo.Matematicas.Datos_OperacionBasicaEntreVectores;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Datos_Principal_Modelo 
{
    /// Atributos_GUI
    private javax.swing.JScrollBar jScrollBar_RotacionX;
    private javax.swing.JScrollBar jScrollBar_RotacionY;
    private javax.swing.JScrollBar jScrollBar_RotacionZ;
    private javax.swing.JTextField jTextField_RotarX;
    private javax.swing.JTextField jTextField_RotarY;
    private javax.swing.JTextField jTextField_RotarZ;
    private javax.swing.JPanel jPanel_Graficas;
    private javax.swing.JButton jButton_BarraColores;
    //ATRIBUTOS_GRAFICACION
    private boolean esVerdadGraficasEnUso=false;
    private RenderizadorDeGraficas3D objRenderizado3D;
    private Artefacto ejesOrtogonales=null;
    private Artefacto Delimitadores=null;
    private Artefacto camposVectoriales=null;
    private Artefacto camposEscalares=null;
    private Artefacto barraDeColores=null;
    private Artefacto operacionesBasicasEntreVectores=null;
    private GLCanvas canvas;
    private boolean esCampoEscalar;
    //ATRIBUTOS_PERSTENCIA
    private boolean existenDatosArchivo=false;
    private int tipoContenidoArchivo=-1;
    private Bean_CampoVectorial beanCampoVectorial;
    private boolean esOperacionBasicaEntreVectores=false;
    private Datos_CampoEscalar datosCampoEscalar=null;
    private Datos_CampoVectorial datosCampoVectorial=null;
    private Datos_OperacionBasicaEntreVectores datosOperacionBasicaEntreVectores=null;
    // Modo de Visualización
    private boolean esModo3D=false;
    private Integer plano=Constantes_Matematicas.PLANO_XY;
    // Interpolación
    private Double interpolacionValorMaximo=null;
    private Double interpolacionValorMínimo=null;
    private boolean seRealizoInterpolacion=false;
    private Double rango=null;
    private Double escala=null;
}
