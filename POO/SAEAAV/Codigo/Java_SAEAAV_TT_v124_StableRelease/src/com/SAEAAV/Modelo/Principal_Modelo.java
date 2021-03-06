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

public class Principal_Modelo implements Interface_Principal_Modelo
{
    ///Atributos
    private javax.swing.JScrollBar jScrollBar_RotacionX;
    private javax.swing.JScrollBar jScrollBar_RotacionY;
    private javax.swing.JScrollBar jScrollBar_RotacionZ;
    private javax.swing.JTextField jTextField_RotarX;
    private javax.swing.JTextField jTextField_RotarY;
    private javax.swing.JTextField jTextField_RotarZ;
    private javax.swing.JPanel jPanel_Graficas;
    private javax.swing.JButton jButton_BarraColores;
    
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
    
    ///Constructores
    public Principal_Modelo()
    {
        
    }
    
    ///Métodos Públicos
    public void guardarDatosDeCampoVectorial(Datos_CampoVectorial datosCampoVectorial)
    {
        // Configuramos variables globales
        this.datosCampoVectorial=datosCampoVectorial;
        this.esCampoEscalar=false;
        this.esOperacionBasicaEntreVectores=false;
        this.camposEscalares=null;
        this.operacionesBasicasEntreVectores=null;
        this.datosCampoVectorial.setEsCampoVectorial3D(this.esModo3D);
        this.datosCampoVectorial.setPlano(plano);
        
        //Fijamos valores
        this.tipoContenidoArchivo=Constantes_Persistencia.TIPO_ARCHIVO_CAMPOVECTORIAL;
        
        //Recuperamos datos
        Bean_CampoVectorial datosDeCampo = this.datosCampoVectorial.guardarDatosDeCampo();
        
        //Obtenemos la representación del contenido del bean de datos
        String contenido = datosDeCampo.getContenido();

        //Guardamos en el archivo
        this.guardarDatosEnArchivo(contenido);
    }
    public void guardarDatosDeCampoEscalar(Datos_CampoEscalar datosCampoEscalar)
    {
        // Configuramos variables globales
        this.camposVectoriales=null;
        this.operacionesBasicasEntreVectores=null;
        this.datosCampoEscalar=datosCampoEscalar;
        this.datosCampoEscalar.setEsCampoEscalar3D(this.esModo3D);
        this.datosCampoEscalar.setPlano(plano);
        this.esCampoEscalar=true;
        this.esOperacionBasicaEntreVectores=false;
        
        //Fijamos valores
        this.tipoContenidoArchivo=Constantes_Persistencia.TIPO_ARCHIVO_CAMPOESCALAR;
        
        //Recuperamos datos
        Bean_CampoEscalar datosDeCampo = this.datosCampoEscalar.guardarDatosDeCampo();
        
        //Obtenemos la representación del contenido del bean de datos
        String contenido = datosDeCampo.getContenido();

        //Guardamos en el archivo
        this.guardarDatosEnArchivo(contenido);
    }
    public void guardarDatosDeOperacionBasicaEntreVectores(Datos_OperacionBasicaEntreVectores datosOperacionBasicaEntreVectores)
    {
        this.datosOperacionBasicaEntreVectores=datosOperacionBasicaEntreVectores;
        this.esCampoEscalar=false;
        this.esOperacionBasicaEntreVectores=true;
        // Configuramos variables globales
        this.camposEscalares=null;
        this.camposVectoriales=null;
        // Configuramos datos
        this.datosOperacionBasicaEntreVectores.setEsModo3D(esModo3D);
        
        //Fijamos valores
        this.tipoContenidoArchivo=Constantes_Persistencia.TIPO_ARCHIVO_OPERACIONBASICAENTREVECTORES;
        
        //Recuperamos datos
        Bean_OBEV datosDeCampo = this.datosOperacionBasicaEntreVectores.guardarDatosDeOBEV();
        
        //Obtenemos la representación del contenido del bean de datos
        String contenido = datosDeCampo.getContenido();

        //Guardamos en el archivo
        this.guardarDatosEnArchivo(contenido);
    }
    
    private void guardarDatosEnArchivo(String contenido)
    {
        //Ahora fijamos valores del archivo
        String rutaArchivo;
        String nombreArchivo;
        //Desplegamos selector de archivos
        UIManager.put("FileChooser.cancelButtonText", "Cancelar");    
        UIManager.put("FileChooser.openButtonText", "Guardar");    
        UIManager.put("FileChooser.filesOfTypeLabelText","Tipo de Archivo:");   
        UIManager.put("FileChooser.fileNameLabelText","Nombre de Archivo:");   
        UIManager.put("FileChooser.openDialogTitleText","Guardar");   
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "ARCHIVOS SAEAAV", "saeaav");
        chooser.setFileFilter(filter);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION)
        {
        //System.out.println("Path del Archivo:" +chooser.getSelectedFile().getPath());
        rutaArchivo=chooser.getSelectedFile().getPath()+".saeaav";
        nombreArchivo="";
        }
        else
        {
        return;
        }
        Persistencia_UTL_Archivos objPersistencia_UTL_Archivos=new Persistencia_UTL_Archivos(rutaArchivo,nombreArchivo);
        
        //Procedemos a escribir
        objPersistencia_UTL_Archivos.setTipoContenidoArchivo(tipoContenidoArchivo);
        objPersistencia_UTL_Archivos.escribirIncluyendoTipo(contenido);
    }
    public Datos_CampoVectorial cargarDatosDeCampoVectorial()
    {
        //Variables
        Persistencia_UTL_Archivos objPersistencia_UTL_Archivos;
        Datos_CampoVectorial datosCampo=null;
        
        //Inicializamos
        objPersistencia_UTL_Archivos=this.cargarDatosDeArchivo();
        
        //Procedemos a leer
        objPersistencia_UTL_Archivos.leerTipo();
        this.tipoContenidoArchivo = objPersistencia_UTL_Archivos.getTipoContenidoArchivo();
        System.out.println("->tipoContenidoArchivo="+this.tipoContenidoArchivo);

        if(this.tipoContenidoArchivo==Constantes_Persistencia.TIPO_ARCHIVO_CAMPOVECTORIAL)
        {
            objPersistencia_UTL_Archivos.leerArchivoExcluyendoTipo();
            String contenido = objPersistencia_UTL_Archivos.getContenido();

            //Mandamos a bean
            datosCampo=new Datos_CampoVectorial();
            datosCampo.cargarDatosDeCampo(contenido);
        }
        else
        {
            //Error
            
        }
        
        // Retornamos
        return datosCampo;
    }
    public Datos_CampoEscalar cargarDatosDeCampoEscalar()
    {
        //Variables
        Persistencia_UTL_Archivos objPersistencia_UTL_Archivos;
        Datos_CampoEscalar datosCampo=null;
        
        //Inicializamos
        objPersistencia_UTL_Archivos=this.cargarDatosDeArchivo();
        
        //Procedemos a leer
        objPersistencia_UTL_Archivos.leerTipo();
        this.tipoContenidoArchivo = objPersistencia_UTL_Archivos.getTipoContenidoArchivo();
        System.out.println("->tipoContenidoArchivo="+this.tipoContenidoArchivo);

        if(this.tipoContenidoArchivo==Constantes_Persistencia.TIPO_ARCHIVO_CAMPOESCALAR)
        {
            objPersistencia_UTL_Archivos.leerArchivoExcluyendoTipo();
            String contenido = objPersistencia_UTL_Archivos.getContenido();

            //Mandamos a bean
            datosCampo=new Datos_CampoEscalar();
            datosCampo.cargarDatosDeCampo(contenido);
        }
        else
        {
            //Error
            
        }
        
        // Retornamos
        return datosCampo;
    }
    public Datos_OperacionBasicaEntreVectores cargarDatosDeOperacionBasicaEntreVectores()
    {
        //Variables
        Persistencia_UTL_Archivos objPersistencia_UTL_Archivos;
        Datos_OperacionBasicaEntreVectores datosOBEV=null;
        
        //Inicializamos
        objPersistencia_UTL_Archivos=this.cargarDatosDeArchivo();
        
        //Procedemos a leer
        objPersistencia_UTL_Archivos.leerTipo();
        this.tipoContenidoArchivo = objPersistencia_UTL_Archivos.getTipoContenidoArchivo();
        System.out.println("->tipoContenidoArchivo="+this.tipoContenidoArchivo);

        if(this.tipoContenidoArchivo==Constantes_Persistencia.TIPO_ARCHIVO_OPERACIONBASICAENTREVECTORES)
        {
            objPersistencia_UTL_Archivos.leerArchivoExcluyendoTipo();
            String contenido = objPersistencia_UTL_Archivos.getContenido();

            //Mandamos a bean
            datosOBEV=new Datos_OperacionBasicaEntreVectores();
            datosOBEV.cargarDatosDeOBEV(contenido);
        }
        else
        {
            //Error
        }
        
        // Retornamos
        return datosOBEV;
    }
    private Persistencia_UTL_Archivos cargarDatosDeArchivo()
    {
        //Ahora fijamos valores del archivo
            String rutaArchivo;
            String nombreArchivo;
           
            //Desplegamos selector de archivos
            UIManager.put("FileChooser.cancelButtonText", "Cancelar");    
            UIManager.put("FileChooser.openButtonText", "Cargar");    
            UIManager.put("FileChooser.filesOfTypeLabelText","Tipo de Archivo:");   
            UIManager.put("FileChooser.fileNameLabelText","Nombre de Archivo:");  
            UIManager.put("FileChooser.openDialogTitleText","Cargar");   
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "ARCHIVOS SAEAAV", "saeaav");
            chooser.setFileFilter(filter);
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int returnVal = chooser.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION)
            {
               //System.out.println("Path del Archivo:" +chooser.getSelectedFile().getPath());
               rutaArchivo=chooser.getSelectedFile().getPath();
               nombreArchivo="";
            }
            else
            {
                return null;
            }
            
            Persistencia_UTL_Archivos objPersistencia_UTL_Archivos=new Persistencia_UTL_Archivos(rutaArchivo,nombreArchivo);
            
            return objPersistencia_UTL_Archivos;
    }
    public void graficarVisualizarResultados()
    {
        if(esVerdadGraficasEnUso)
        {
           this.borrarGrafica_ligero();
        }
        
        //Ajustamos
        esVerdadGraficasEnUso=true;
        
        // Configuramos lo que necesitaremos del escenario
        this.ejesOrtogonales=new EjesOrtogonales(this.plano, this.rango,this.escala);
        this.Delimitadores=new Delimitadores(this.plano, this.rango);
        
        if(this.esOperacionBasicaEntreVectores)this.Delimitadores=null;
        
        // Mostramos
        this.objRenderizado3D=new RenderizadorDeGraficas3D();
        this.objRenderizado3D.setEjesOrtogonales(ejesOrtogonales);//Insertamos
        this.objRenderizado3D.setDelimitadores(Delimitadores);//Insertamos
        this.objRenderizado3D.setCamposVectoriales(camposVectoriales);//Insertamos
        this.objRenderizado3D.setCamposEscalares(camposEscalares);//Insertamos
        this.objRenderizado3D.setBarraDeColores(barraDeColores);//Insertamos
        this.objRenderizado3D.setOperacionesBasicasEntreVectores(operacionesBasicasEntreVectores);//Insertamos
        this.objRenderizado3D.inicializarAnimacion();
        canvas = objRenderizado3D.getCanvas();
        this.objRenderizado3D.arrancarAnimacion();
        Dimension size = this.jPanel_Graficas.getSize();
        canvas.setSize(size.width, size.height);
        this.jPanel_Graficas.add(canvas);    
        this.objRenderizado3D.arrancarAnimacion();
        
        this.ajustarCamaraAModo2D();
    }
    public void accionSalir()
    {
        this.objRenderizado3D.finalizarAnimacion();
    }
    public void RotarX()
    {
        if(!this.esVerdadGraficasEnUso)return;
        int value = this.jScrollBar_RotacionX.getValue();
        this.jTextField_RotarX.setText(""+value);
        
        this.objRenderizado3D.setRotacionModelo_AnguloEjeX(value);
    }
    public void RotarY()
    {
        if(!this.esVerdadGraficasEnUso)return;
        int value = this.jScrollBar_RotacionY.getValue();
        this.jTextField_RotarY.setText(""+value);
        
        this.objRenderizado3D.setRotacionModelo_AnguloEjeY(value);
    }
    public void RotarZ()
    {
        if(!this.esVerdadGraficasEnUso)return;
        int value = this.jScrollBar_RotacionZ.getValue();
        this.jTextField_RotarZ.setText(""+value);
        
        this.objRenderizado3D.setRotacionModelo_AnguloEjeZ(value);
    }
    public void CamaraMas()
    {
        if(!this.esVerdadGraficasEnUso)return;
        this.objRenderizado3D.camaraMas(this.esModo3D, this.plano);
    }
    public void CamaraMenos()
    {
        if(!this.esVerdadGraficasEnUso)return;
        this.objRenderizado3D.camaraMenos(this.esModo3D, this.plano);
    }
    public void PanelGraficas_Redimensionado(java.awt.event.ComponentEvent evt)
    {
        if(!this.esVerdadGraficasEnUso)return;
        if(!esVerdadGraficasEnUso)
        {
           return; 
        }
        Dimension size = evt.getComponent().getSize();
        
        this.objRenderizado3D.continuarAnimacionDobleVez();
        
        this.objRenderizado3D.getCanvas().setSize(size.width, size.height);
    }
    public void esCampoEscalar(Datos_CampoEscalar datosCampoEscalar)
    {
        this.datosCampoEscalar=datosCampoEscalar;
        this.datosCampoEscalar.setEsCampoEscalar3D(this.esModo3D);
        this.datosCampoEscalar.setPlano(plano);
        if(this.esModo3D)
        {
            this.rango=datosCampoEscalar.getLadoDeCubo();
        }    
        else
        {
            this.rango=datosCampoEscalar.getLadoDeCuadrado();
        }    
        this.escala=datosCampoEscalar.getEscala();
        this.esCampoEscalar=true;
        this.esOperacionBasicaEntreVectores=false;
        
        // Configuramos variables globales
        this.camposVectoriales=null;
        this.operacionesBasicasEntreVectores=null;
        
        CamposEscalares campoCalculo=new CamposEscalares(datosCampoEscalar);//OJO, PORQUE NO THIS.DATOS..
        this.camposEscalares=campoCalculo;
        
        this.interpolacionValorMaximo=campoCalculo.getInterpolacionValorMaximo();
        this.interpolacionValorMínimo=campoCalculo.getInterpolacionValorMínimo();
        this.seRealizoInterpolacion=true;
        
    }
    public void esCampoVectorial(Datos_CampoVectorial datosCampoVectorial)
    {
        this.datosCampoVectorial=datosCampoVectorial;
        this.esCampoEscalar=false;
        this.esOperacionBasicaEntreVectores=false;
        if(this.esModo3D)
        {
            this.rango=datosCampoVectorial.getLadoDeCubo();
        }    
        else
        {
            this.rango=datosCampoVectorial.getLadoDeCuadrado();
        }
        this.escala=datosCampoVectorial.getEscala();
        
        // Configuramos variables globales
        this.camposEscalares=null;
        this.operacionesBasicasEntreVectores=null;
        
        this.datosCampoVectorial.setEsCampoVectorial3D(this.esModo3D);
        this.datosCampoVectorial.setPlano(plano);
        CamposVectoriales campoCalculo=new CamposVectoriales(this.datosCampoVectorial);
        this.camposVectoriales=campoCalculo;
        
        this.interpolacionValorMaximo=campoCalculo.getInterpolacionValorMaximo();
        this.interpolacionValorMínimo=campoCalculo.getInterpolacionValorMínimo();
        this.seRealizoInterpolacion=true;
        
    }
    public void esOperacionBasicaEntreVectores(Datos_OperacionBasicaEntreVectores datosOperacionBasicaEntreVectores)
    {
        this.datosOperacionBasicaEntreVectores=datosOperacionBasicaEntreVectores;
        
        this.esCampoEscalar=false;
        this.esOperacionBasicaEntreVectores=true;
        
        // Configuramos variables globales
        this.camposEscalares=null;
        this.camposVectoriales=null;
        this.rango=null;
        this.escala=null;
        
        // Configuramos datos
        this.datosOperacionBasicaEntreVectores.setEsModo3D(esModo3D);
        this.operacionesBasicasEntreVectores=new OperacionesBasicasEntreVectores(this.datosOperacionBasicaEntreVectores);
        OperacionesBasicasEntreVectores obj=(OperacionesBasicasEntreVectores)this.operacionesBasicasEntreVectores;
        boolean esResultadoGrafico = obj.getDatosOperacionBasicaEntreVectores().isEsResultadoGrafico();
        if(!esResultadoGrafico)
        {
            this.operacionesBasicasEntreVectores.construir(null);
            this.operacionesBasicasEntreVectores=null;
        }
    }
    public void borrarGrafica_ligero()
    {
        // Verificamos si la sección de gráficos se reiniciará
        if(this.esVerdadGraficasEnUso)
        {
            // De ser así receteamos la sección de gráficos
            this.objRenderizado3D.finalizarAnimacion();
            this.objRenderizado3D.destroy();
            this.jPanel_Graficas.removeAll();


            this.jScrollBar_RotacionX.setValue(0);
            this.jScrollBar_RotacionY.setValue(0);
            this.jScrollBar_RotacionZ.setValue(0);
            this.jTextField_RotarX.setText("0");
            this.jTextField_RotarY.setText("0");
            this.jTextField_RotarZ.setText("0");
            
            this.esVerdadGraficasEnUso=false;
            this.objRenderizado3D=null;
        }
    }
    public void VentanaNoActiva()
    {
        System.out.println(">>formWindowDeactivated");
    }
    public void VentanaActiva()
    {
        System.out.println(">>formWindowActivated");
        if(!this.esVerdadGraficasEnUso)return;
        this.objRenderizado3D.continuarAnimacion();
    }
    public void BarraDeColores()
    {
        this.barraDeColores=new BarraDeColores();
        this.jButton_BarraColores.setBackground(Color.red);
    }
    public void SalvarImagen()
    {
        try {
            System.out.println("SAVE...");
            AltPrintScreen objAltPrintScreen=new AltPrintScreen();
            
            Rectangle bounds = this.jPanel_Graficas.getBounds();//this.canvas.getBounds();//
            objAltPrintScreen.setBoundsPanel(bounds);
            objAltPrintScreen.Principal();
////        Graphics graphics = this.canvas.getGraphics();
////        this.jPanel_Graficas.update(graphics);
//            BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
//            BufferedImage subimage = image.getSubimage(10, 100, 10, 100);
//            ImageIO.write(subimage, "png", new File("C:\\Users\\Sergio\\Documents\\NetBeansProjects\\RECURSOS\\\\savescreenshot.png"));
////        canvas.createImage(null);
////        .addPaintListener(new PaintListener() {
////          public void paintControl(PaintEvent e) {
////            
////          }
////        });
////        VolatileImage createVolatileImage = canvas.createVolatileImage(canvas.getHeight(), canvas.getWidth());
////        BufferedImage snapshot = createVolatileImage.getSnapshot();
////        
////        BufferedImage img = new BufferedImage( jPanel_Graficas.getWidth(),  jPanel_Graficas.getHeight(), BufferedImage.TYPE_INT_RGB);
////        Graphics2D g2d = img.createGraphics();
////        //canvas.printAll(jPanel_Graficas.getGraphics());//.printAll(g2d);
////        jPanel_Graficas.printAll(g2d);
////        g2d.dispose();
////        try {
////            ImageIO.write(snapshot, "png", new File("C:\\Users\\Sergio\\Documents\\NetBeansProjects\\RECURSOS\\save.png"));
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
            System.out.println("SAVE...OK");
        } catch (Exception ex) {
            //Logger.getLogger(Vista_JFrame_VentanaGraficador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void esModo3D()
    {
        this.esModo3D=true;
        this.plano=null;
    }
    @Deprecated
    public void esModo2D()
    {        
        this.esModo3D=false;
        
        String txt = JOptionPane.showInputDialog("Inserte Plano: [XY=1;XZ=2;YZ=3]", "1");
        this.plano=Integer.parseInt(txt);
    }
    public void esModo2D(Integer plano)
    {
        this.esModo3D=false;
        this.plano=plano;
    }
    public void camera_ViewPoint_DefaultSetting()
    {
        if(this.esVerdadGraficasEnUso)
        {
            this.objRenderizado3D.camera_ViewPoint_DefaultSetting();
        }
    }
    public void ajustarCamaraAModo2D()
    {
        //this.camera_ViewPoint_DefaultSetting();
        
        if(this.plano==null || !this.esVerdadGraficasEnUso || this.esModo3D)
        {
            //System.out.println("return;esVerdadGraficasEnUso="+esVerdadGraficasEnUso);
            return;
        }
        
        if(plano==Constantes_Matematicas.PLANO_XY)
        {
            this.objRenderizado3D.setCameraAtributes(0, 0, -0.5, 0, 0, +2, 0, 1, 0, 90);
        }
        if(plano==Constantes_Matematicas.PLANO_XZ)
        {
            this.objRenderizado3D.setCameraAtributes(0, +0.5, 0, 0, -2, 0.001, 0, 1, 0, 90);
        }
        if(plano==Constantes_Matematicas.PLANO_YZ)
        {
            this.objRenderizado3D.setCameraAtributes(-0.5, 0, 0, +2, 0, 0, 0, 0, 1, 90);
        }

        this.objRenderizado3D.continuarAnimacion();
        
    }
    public void limpiar()
    {
        // Receteamos campos en general
        this.ejesOrtogonales=null;
        this.camposVectoriales=null;
        this.camposEscalares=null;
        this.barraDeColores=null;
        this.esCampoEscalar=false;
        this.esOperacionBasicaEntreVectores=false;
        this.operacionesBasicasEntreVectores=null;
        //this.plano=null;
        
        
        // Verificamos si la sección de gráficos se reiniciará
        if(this.esVerdadGraficasEnUso)
        {
            // De ser así receteamos la sección de gráficos
            this.objRenderizado3D.finalizarAnimacion();
            this.objRenderizado3D.destroy();
            this.jPanel_Graficas.removeAll();


            this.jScrollBar_RotacionX.setValue(0);
            this.jScrollBar_RotacionY.setValue(0);
            this.jScrollBar_RotacionZ.setValue(0);
            this.jTextField_RotarX.setText("0");
            this.jTextField_RotarY.setText("0");
            this.jTextField_RotarZ.setText("0");
            
            this.esVerdadGraficasEnUso=false;
            this.objRenderizado3D=null;
        }   
    }
    
    ///Métodos Privados

    ///Métodos para el manejo de Atributos

    public void setjScrollBar_RotacionX(javax.swing.JScrollBar jScrollBar_RotacionX) {
        this.jScrollBar_RotacionX = jScrollBar_RotacionX;
    }

    public void setjScrollBar_RotacionY(javax.swing.JScrollBar jScrollBar_RotacionY) {
        this.jScrollBar_RotacionY = jScrollBar_RotacionY;
    }

    public void setjScrollBar_RotacionZ(javax.swing.JScrollBar jScrollBar_RotacionZ) {
        this.jScrollBar_RotacionZ = jScrollBar_RotacionZ;
    }

    public void setjTextField_RotarX(javax.swing.JTextField jTextField_RotarX) {
        this.jTextField_RotarX = jTextField_RotarX;
    }

    public void setjTextField_RotarY(javax.swing.JTextField jTextField_RotarY) {
        this.jTextField_RotarY = jTextField_RotarY;
    }

    public void setjTextField_RotarZ(javax.swing.JTextField jTextField_RotarZ) {
        this.jTextField_RotarZ = jTextField_RotarZ;
    }

    public void setjPanel_Graficas(javax.swing.JPanel jPanel_Graficas) {
        this.jPanel_Graficas = jPanel_Graficas;
    }

    public void setjButton_BarraColores(javax.swing.JButton jButton_BarraColores) {
        this.jButton_BarraColores = jButton_BarraColores;
    }

    public void setDatosCampoEscalar(Datos_CampoEscalar datosCampoEscalar) {
        this.datosCampoEscalar = datosCampoEscalar;
    }

    public void setDatosCampoVectorial(Datos_CampoVectorial datosCampoVectorial) {
        this.datosCampoVectorial = datosCampoVectorial;
    }

    public void setDatosOperacionBasicaEntreVectores(Datos_OperacionBasicaEntreVectores datosOperacionBasicaEntreVectores) {
        this.datosOperacionBasicaEntreVectores = datosOperacionBasicaEntreVectores;
    }

    public Double getInterpolacionValorMaximo() {
        return interpolacionValorMaximo;
    }

    public Double getInterpolacionValorMínimo() {
        return interpolacionValorMínimo;
    }

    public boolean isSeRealizoInterpolacion() {
        return seRealizoInterpolacion;
    }

    public void setSeRealizoInterpolacion(boolean seRealizoInterpolacion) {
        this.seRealizoInterpolacion = seRealizoInterpolacion;
    }
    
    
}
