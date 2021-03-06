///Paquete de Trabajo
package com.SAEAAV.Modelo.Graficador;

///Librerias
import com.SAEAAV.Modelo.Graficador.Artefactos.Artefacto;
import com.SAEAAV.Modelo.Graficador.Artefactos.OperacionesBasicasEntreVectores;
import com.SAEAAV.Modelo.Matematicas.Constantes_Matematicas;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import java.awt.Dimension;
import static javax.media.opengl.GL2.*; // GL2 constants
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class RenderizadorDeGraficas3D extends GLCanvas implements GLEventListener
{
    ///Atributos_Despliegue
    private String tituloGrafica = "SAEAAV";//Título de ventana
    private int canvasAncho = 600;//Ancho de Pantalla de Video
    private int canvasAlto = 600;//Altura de Pantalla de Video
    ///Atributos_Renderizado
    private int FPS = 24; // Animador-Propiedad-Frames/Cuadros Por Segundo
    private GLU glu;  // Para la GL Utility
    private FPSAnimator animator; //Para la animación en canvas
    private GLCanvas canvas;//Contenedor para desplegar las gráficas
    ///Atributos_VisualizaciónTridimensional_Cámara
    private double x0=0.0, y0=0.0, z0=1.0;//Origen de coordenadas de visualización.[1.0,1.0,1.0]
    private double xref=0.0, yref=0.0, zref=-5.0; //Punto observado.[0.0,0.0,0.0]
    private double Vx=0.0, Vy=1.0, Vz=0.0; //Vector vertical.[0.0,1.0,0.0]
    private double dnear=0.1, dfar=100;// Establecer posicion planos de recorte próximo y lejano,0.1-100,25-125
    private double ViewAngle=90;//Angulo de Visión;90-OK
    ///Atributos_VisualizaciónTridimensional_ModeloVisualizado
    private float rotacionModelo_AnguloEjeX = 0; // rotational angle for x-axis in degree
    private float rotacionModelo_AnguloEjeY = 0; // rotational angle for y-axis in degree
    private float rotacionModelo_AnguloEjeZ = 0; // rotational angle for z-axis in degree
    ///Atributos_Extras
    private GL2 gl=null;
    private boolean esInicio=true;
    private boolean siContinuarAnimacionDosVeces=false;
    ///Atributos_Modelo
    private Artefacto ejesOrtogonales=null;
    private Artefacto camposVectoriales=null;
    private Artefacto camposEscalares=null;
    private Artefacto barraDeColores=null;
    private Artefacto Delimitadores=null;
    private Artefacto operacionesBasicasEntreVectores=null;
    private double xModelo=0, yModelo=0, zModelo=0;
    private OperacionesBasicasEntreVectores varOBEV=null;
    
    ///Constructores
    public RenderizadorDeGraficas3D() 
    {
        //Fijamos las propiedades
        this.addGLEventListener(this);
        
        // Fijamos la OpenGL Utility-Necesaria en toda implementación OpenGL
        this.glu = new GLU();                         
    }

    ///Métodos de propósito general
    public void inicializarAnimacion()
    {
        // Creamos el lienzo de renderizado para OpenGL
        this.canvas = this;
        canvas.setPreferredSize(new Dimension(canvasAncho, canvasAlto));

        // Fijamos manejadores de eventos
        //canvas.addMouseListener(this);

        // Creamos animador que se despliega en canvas. Invoca Display().  
        this.animator = new FPSAnimator(canvas, FPS, true);
    }
    public void arrancarAnimacion()
    {
        //Comienza el ciclo de animación, después de adjuntar el canvas
        animator.start(); 
    }
    public void finalizarAnimacion()
    {
        //Paramos la animación, si es que esta ha sido ya iniciada
        if (animator.isStarted())
        {
            animator.stop();
        }
    }

    ///Métodos Públicos de Renderizado
    //Método para la Inicialización de GLU
    @Override
    public void init(GLAutoDrawable drawable)
    {
        System.out.println("init()");

        // Obtenemos el objeto para para manejo de OpenGL
        if(this.esInicio)
        {
            // Obtenemos el objeto para para manejo de OpenGL
            this.gl = drawable.getGL().getGL2();//Fijamos la versión requerida
            
            this.esInicio=false;
        }
        
        //Inicializamos
        this.init3D();
        this.display_Principal();
    }
    //Método para caso de evento de re-dimensión, se llama una vez al inicio
    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height)
    {   
        System.out.println("->reshape()");
        
        this.reshape3D(x, y, width, height);
    }
    //Método llamado por el animador para realizar el renderizado
    @Override
    public void display(GLAutoDrawable drawable) 
    {
        System.out.println("->display()");
                
        this.display_Principal();
        
        if (animator.isAnimating() && !this.siContinuarAnimacionDosVeces)
        {
            animator.pause();
        }
        if(this.siContinuarAnimacionDosVeces)
        {
            this.siContinuarAnimacionDosVeces=false;
        }
    }   
    //Método llamado cuando el contexto OpenGL es destruido. Liberamos recursos.
    @Override
    public void dispose(GLAutoDrawable drawable) 
    {    
        System.out.println("->dispose()");
    }
    
    ///Métodos Privados de Renderizado
    private void init3D()
    {
        // Carga color de fondo pantalla de visualización RGBA
        //gl.glClearColor(0.83f, 0.83f, 0.83f, 0.0f);//4to parámetro fundido-Alfa
        //OK-this.gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);//4to parámetro fundido-Alfa
        //ACTIVO=this.gl.glClearColor(0.25f, 0.25f, 0.25f, 0.0f);//4to parámetro fundido-Alfa
        //NO-this.gl.glClearColor(0.31f, 0.31f, 0.31f, 0.0f);//4to parámetro fundido-Alfa
        //this.gl.glClearColor(0.16f, 0.38f, 0.27f, 0.0f);//4to parámetro fundido-Alfa
        this.gl.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);//4to parámetro fundido-Alfa
        
        // Activar modo de visualización de modelo
        this.gl.glMatrixMode(GL_MODELVIEW);

        // Especificamos los parámetros de visualización
        // gluLookAt(x0,y0,z0,xref,yref,zref,Vx,Vy,Vz);
        // Default: P0=(0,0,0)-Pref(0,0,-1)-V=(0,1,0)
        this.glu.gluLookAt(x0,y0,z0,xref,yref,zref,Vx,Vy,Vz);

        // Establece parámetrods de proyección Ortogonal
        // Default: gluOrtho2D(-1,1,-1,1,-1,1)->Cubo normalizado Simétrico
        this.gl.glOrtho(-1.0, 1.0, -1.0, 1.0, -1.0, 1.0);//OMITIBLE

        // Establece modo de proyección en coordenadas universales
        this.gl.glMatrixMode(GL_PROJECTION);

        // Establece propiedades de ventana de recorte
        this.glu.gluOrtho2D(0.0, canvasAncho, 0.0, canvasAlto);

        // Establece las propiedades de la ventana de visualización
        // De no invocarse toma propiedades de ventana de visualización
        // Sospecho que aquí el Viewport es el canvas de Java.
        this.gl.glViewport(0, 0, canvasAncho, canvasAlto);

        // Especificamos rango de valores de normalización
        // glDepthRange(ValorProfundidadCercana, ValorProfundidadLejana)
        // Valores defualt: ValorProfundidadCercana=0 y ValorProfundidadLejana=1
        // Explicación: Todos los valores de distancias se mapean a este rango.
        this.gl.glDepthRange(0.0, 1.0);

        // Cargamos valor de inicialización de buffer profundidad al máximo
        this.gl.glClearDepth(1.0f);// El máximo valor de profundidad por default es 1.

        // Inicializamos el buffer de profundidad (Buffer-Z)
        this.gl.glClear(GL_DEPTH_BUFFER_BIT);// Toma el valor ClearDepth.

        // Activa rutinas de detección de visibilidad basada en búfer de profundidad
        this.gl.glEnable(GL_DEPTH_TEST);// Habilita Buffer de profundidad (Buffer-Z)
        //gl.glDisable(GL_DEPTH_TEST);// Deshabilita Buffer de profundidad (Buffer-Z)

        // Establece condiciones de prueba para búfer de profundidad
        // Explicación: Se utiliza para optimizar cálculos del buffer de profundidad.
        // Constantes:GL_LESS,GL_GREATER,GL_EQUAL,GL_NOTEEQUAL,GL_LEQUAL,
        // GL_GEQUAL,GL_NEVER, GL_ALWAYS.
        this.gl.glDepthFunc(GL_LESS);//lDepthFunc(CondiciónDeTest)-Default=GL_LESS

        // Establecemos el estado del búfer de profundidad
        // Valores posibles: GL_TRUE, GL_FALSE.
        this.gl.glDepthMask(true);//glDepthMask(PermisoDeEscritura)-Default=true

        // Añadimos pasos extras...
        this.gl.glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST); // best perspective correction
        this.gl.glShadeModel(GL_SMOOTH); // blends colors nicely, and smoothes out lighting
    }
    private void reshape3D(int x, int y, int newWidth, int newHeight)
    {
        // Obtenemos el objeto para para manejo de OpenGL
        //this.gl = drawable.getGL().getGL2();//Fijamos la versión requerida
        
        // Variables
        float relacionAspecto;
        
        // Realizamos cálculos necesarios
        if (newHeight == 0)
        {
            newHeight = 1;//Prevenimos división por cero
        }   
        relacionAspecto = (float)newWidth / newHeight;

        // Establece las propiedades de la ventana de visualización
        // De no invocarse toma propiedades de ventana de visualización
        // Sospecho que aquí el Viewport es el canvas de Java.
        this.gl.glViewport(0, 0, newWidth, newHeight);

        // Establece modo de proyección en coordenadas universales
        this.gl.glMatrixMode(GL_PROJECTION);

        // Asignamos la matriz identidad como matriz de proyección
        this.gl.glLoadIdentity();//Garantiza valores no repetidos, resetea atributos

        // Establece parámetros de frustrum de visualización simétrico para
        // "Proyección en Perspectiva"-Simétrica
        // gluPerspective(thetaº, relacionAspecto, dnear, dfar);
        this.glu.gluPerspective(this.ViewAngle, relacionAspecto, dnear, dfar);

        // Establece parámetrods de proyección Ortogonal
        // Default: gluOrtho2D(-1,1,-1,1,-1,1)->Cubo normalizado Simétrico
        this.gl.glOrtho(-2, 2, -2, 2, 0, 50);

        // Activar modo de visualización de modelo
        this.gl.glMatrixMode(GL_MODELVIEW);
    }
    public void display_Principal() 
    {
        // Definimos propiedades generales de color
        this.gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); //Reseteamos todos búferes de color y profundidad

        // Asignamos la matriz identidad como matriz de proyección
        this.gl.glLoadIdentity();//Garantiza valores no repetidos, resetea atributos

        // Ajustamos los atributos de la cámara
        this.glu.gluLookAt(x0,y0,z0,xref,yref,zref,Vx,Vy,Vz);

        // Ajustamos la eliminación de caras posteriores
        // Tenemos Opciones GL_FRONT, GL_BACK, GL_FRONT_AND_BACK
        this.gl.glEnable(GL_CULL_FACE);//Activa eliminación de caras posteriores
        this.gl.glCullFace(GL_BACK);//glCullFace(Modo)-GL_BACK=DEFAULT
        this.gl.glDisable(GL_CULL_FACE);//Desactiva eliminación de caras posteriores

        // Definimos propiedades del color del objeto
        this.gl.glColor3f(0.0f, 0.0f, 1.0f);//RGBA

        // Aplicamos transformaciones a la escena en general
        this.gl.glTranslated(this.xModelo, this.yModelo, this.zModelo);//Enviamos al punto observado
        this.gl.glRotatef(this.rotacionModelo_AnguloEjeX, 1.0f, 0.0f, 0.0f);
        this.gl.glRotatef(this.rotacionModelo_AnguloEjeY, 0.0f, 1.0f, 0.0f);
        this.gl.glRotatef(this.rotacionModelo_AnguloEjeZ, 0.0f, 0.0f, 1.0f);

        // Ejecutamos los dibujos
        if(this.barraDeColores==null)
        {
            if(this.ejesOrtogonales!=null)
            {
                this.ejesOrtogonales.construir(gl);
            }
            if(this.camposVectoriales!=null)
            {
                this.camposVectoriales.construir(gl);
            }
            if(this.camposEscalares!=null)
            {
                this.camposEscalares.construir(gl);
            }
            if(this.Delimitadores!=null)
            {
                this.Delimitadores.construir(gl);
            }
            if(this.operacionesBasicasEntreVectores!=null)
            {
                this.varOBEV=(OperacionesBasicasEntreVectores) this.operacionesBasicasEntreVectores;
                this.varOBEV.setGlu(glu);
                this.operacionesBasicasEntreVectores.construir(gl);
            }
        }
        else
        {
            this.barraDeColores.construir(gl);
        }
        
        // Procesa subrutinas tan rápido como sea posible
        this.gl.glFlush();
    }

    ///Métodos de propósito específico

    ///Métodos para el manejo de atributos
    
    public GLCanvas getCanvas() 
    {
        return canvas;
    }

    public void setRotacionModelo_AnguloEjeX(float rotacionModelo_AnguloEjeX) 
    {
        this.rotacionModelo_AnguloEjeX = rotacionModelo_AnguloEjeX;
        
        this.animator.resume();
    }

    public void setRotacionModelo_AnguloEjeY(float rotacionModelo_AnguloEjeY) 
    {
        this.rotacionModelo_AnguloEjeY = rotacionModelo_AnguloEjeY;
        
        this.animator.resume();
    }

    public void setRotacionModelo_AnguloEjeZ(float rotacionModelo_AnguloEjeZ)
    {
        this.rotacionModelo_AnguloEjeZ = rotacionModelo_AnguloEjeZ;
        
        this.animator.resume();
    }
 
    public void camaraMas(boolean esModo3D, Integer plano)
    {
        System.out.println("esModo3D="+esModo3D+";plano="+plano);
        double factor=0.5;
        
        if(esModo3D)
        {
            this.zModelo-=factor;
        }
        if(!esModo3D)
        {
            if(plano==Constantes_Matematicas.PLANO_XY)
            {
                this.zModelo-=factor;
                //this.setCameraAtributes(0, 0, -0.5, 0, 0, +2, 0, 1, 0, 90);
            }
            if(plano==Constantes_Matematicas.PLANO_XZ)
            {
                this.yModelo+=factor;
                //this.setCameraAtributes(0, +0.5, 0, 0, -2, 0.001, 0, 1, 0, 90);
            }
            if(plano==Constantes_Matematicas.PLANO_YZ)
            {
                this.xModelo-=factor;
                //this.setCameraAtributes(-0.5, 0, 0, +2, 0, 0, 0, 0, 1, 90);
            }
        }
        
        this.animator.resume();
    }
 
    public void camaraMenos(boolean esModo3D, Integer plano)
    {
        double factor=0.5;
        
        if(esModo3D)
        {
            this.zModelo+=factor;
        }
        if(!esModo3D)
        {
            if(plano==Constantes_Matematicas.PLANO_XY)
            {
                this.zModelo+=factor;
                //this.setCameraAtributes(0, 0, -0.5, 0, 0, +2, 0, 1, 0, 90);
            }
            if(plano==Constantes_Matematicas.PLANO_XZ)
            {
                this.yModelo-=factor;
                //this.setCameraAtributes(0, +0.5, 0, 0, -2, 0.001, 0, 1, 0, 90);
            }
            if(plano==Constantes_Matematicas.PLANO_YZ)
            {
                this.xModelo+=factor;
                //this.setCameraAtributes(-0.5, 0, 0, +2, 0, 0, 0, 0, 1, 90);
            }
        }
        
        this.animator.resume();
    }
    public String getTituloGrafica() {
        return tituloGrafica;
    }

    public void setTituloGrafica(String tituloGrafica) {
        this.tituloGrafica = tituloGrafica;
    }

    public int getCanvasAncho() {
        return canvasAncho;
    }

    public void setCanvasAncho(int canvasAncho) {
        this.canvasAncho = canvasAncho;
    }

    public int getCanvasAlto() {
        return canvasAlto;
    }

    public void setCanvasAlto(int canvasAlto) {
        this.canvasAlto = canvasAlto;
    }

    public int getFPS() {
        return FPS;
    }

    public void setFPS(int FPS) {
        this.FPS = FPS;
    }
    
    public void continuarAnimacion()
    {
        this.animator.resume();
    }
    
    public void continuarAnimacionDobleVez()
    {
        this.animator.resume();
        this.siContinuarAnimacionDosVeces=true;
    }
    
    public void setEjesOrtogonales(Artefacto ejesOrtogonales)
    {
        this.ejesOrtogonales = ejesOrtogonales;
    }

    public void setCamposVectoriales(Artefacto camposVectoriales) {
        this.camposVectoriales = camposVectoriales;
    }

    public void setCamposEscalares(Artefacto camposEscalares) {
        this.camposEscalares = camposEscalares;
    }

    public void setBarraDeColores(Artefacto barraDeColores) {
        this.barraDeColores = barraDeColores;
    }
    public void camera_ViewPoint_DefaultSetting()
    {
        System.out.println("->camera_ViewPoint_DefaultSetting");
        /*
        {1.3,-2.4,2}	default setting
        {0,-2,0}	directly in front
        {0,-2,2}	in front and up
        {0,-2,-2}	in front and down
        {-2,-2,0}	left-hand corner
        {2,-2,0}	right-hand corner
        {0,0,2}         directly above
         */
        String txt;
        txt = JOptionPane.showInputDialog("Ingrese xref=","0.5/0.0");
        this.xref=Double.parseDouble(txt);
        txt = JOptionPane.showInputDialog("Ingrese yref=","0.5/0.0");
        this.yref=Double.parseDouble(txt);
        txt = JOptionPane.showInputDialog("Ingrese zref=","0.5/0.0");
        this.zref=Double.parseDouble(txt);
        
//        this.xref=1.3;this.yref=+2.4;this.zref=2;
        /*
        ViewCenter->{1/2,1/2,1/2}
        */
        
        txt = JOptionPane.showInputDialog("Ingrese x0=","1.3/1.0");
        this.x0=Double.parseDouble(txt);
        txt = JOptionPane.showInputDialog("Ingrese y0=","-2.4/1.0");
        this.y0=Double.parseDouble(txt);
        txt = JOptionPane.showInputDialog("Ingrese z0=","2.0/1.0");
        this.z0=Double.parseDouble(txt);
        
        //this.x0=1/2;this.y0=1/2;this.z0=1/2;
        
        txt = JOptionPane.showInputDialog("Ingrese ViewAngle=","35/90");
        this.ViewAngle=Double.parseDouble(txt);
    }

    public void setDelimitadores(Artefacto Delimitadores) 
    {
        this.Delimitadores = Delimitadores;
    }
    
    public void setCameraAtributes(double xref, double yref, double zref, double x0, double y0, double z0, double Vx, double Vy, double Vz, double ViewAngle)
    {
        this.xref=xref;
        this.yref=yref;
        this.zref=zref;
        
        this.x0=x0;
        this.y0=y0;
        this.z0=z0;
        
        this.Vx=Vx;
        this.Vy=Vy;
        this.Vz=Vz;
                
        this.ViewAngle=ViewAngle;
    }

    public void setOperacionesBasicasEntreVectores(Artefacto operacionesBasicasEntreVectores) {
        this.operacionesBasicasEntreVectores = operacionesBasicasEntreVectores;
    }
}
