///Paquete de Trabajo
package com.SAEAAV.Modelo.Graficador.Artefactos;

///Librerias
import com.SAEAAV.Modelo.Graficador.Interpolaciones.ColorFormatoRGB;
import com.SAEAAV.Modelo.Graficador.Interpolaciones.InterpolacionColoresJet;
import com.SAEAAV.Modelo.Matematicas.Datos_OperacionBasicaEntreVectores;
import com.SAEAAV.Modelo.Matematicas.OperacionBasicaEntreVectores;
import com.SAEAAV.Modelo.Matematicas.Vector;
import java.util.ArrayList;
import javax.media.opengl.GL2;
import static javax.media.opengl.GL2.*;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;
import javax.swing.JOptionPane;

public class OperacionesBasicasEntreVectores extends Artefacto
{
    ///Atributos
    private OperacionBasicaEntreVectores obev;
    private ArrayList<Vector> vectoresOperacionesBasicasEntreVectores=new ArrayList<Vector>();
    private Datos_OperacionBasicaEntreVectores datosOperacionBasicaEntreVectores=null;
    private InterpolacionColoresJet objLecturaJet=new InterpolacionColoresJet();
    private int indiceActualDeVector=0;
    private ColorFormatoRGB colorResultadoDeInterpolacion;
    private GLU glu=null;  // Para la GL Utility
    GLUquadric quadricEsferaPuntaVector;//Creamos objeto sombreador de cuádricas
    
    ///Constructores
    public OperacionesBasicasEntreVectores(Datos_OperacionBasicaEntreVectores datosOperacionBasicaEntreVectores)
    {    
        this.datosOperacionBasicaEntreVectores=datosOperacionBasicaEntreVectores;
        this.inicializar();
    }
    
    ///Métodos de propósito general
    private void inicializar()
    {   
        // Inicializar
        this.obev=new OperacionBasicaEntreVectores(this.datosOperacionBasicaEntreVectores);
        
        // Obtenemos datos de interés
        this.vectoresOperacionesBasicasEntreVectores.addAll(this.obev.getDatosOperacionBasicaEntreVectores().getVectoresOperacion());
        this.vectoresOperacionesBasicasEntreVectores.addAll(this.obev.getDatosOperacionBasicaEntreVectores().getVectoresResultado());
        
        // Valores Interpolación
        this.objLecturaJet.setValorMaximo(this.vectoresOperacionesBasicasEntreVectores.size()-1);
        this.objLecturaJet.setValorMinimo(0);
    }
    @Override
    public void construir(GL2 gl) 
    {
        boolean esResultadoGrafico=this.obev.getDatosOperacionBasicaEntreVectores().isEsResultadoGrafico();
        String mensajeResultado=this.obev.getDatosOperacionBasicaEntreVectores().getMensajeResultado();
        
        System.out.println("construir->esResultadoGrafico="+esResultadoGrafico);
        if(!esResultadoGrafico)
        {
            JOptionPane.showMessageDialog(null, "RESULTADO:\n"+mensajeResultado);
            
            return;
        }
        
        super.gl=gl;
        
        this.construir_ResultadoOBEV();
        
        super.gl=null;
    }
    
    ///Métodos de propósito específico
    private void construir_ResultadoOBEV()
    {
        // Variables
        double x1,y1,z1,x2,y2,z2;
        Vector vectorActual;
        
        // Recorremos arreglo
        for(int k=0; k<this.vectoresOperacionesBasicasEntreVectores.size(); k++)
        {
            // Ajustamos valores
            vectorActual=this.vectoresOperacionesBasicasEntreVectores.get(k);
            
            // Extraemos datos del vector
            x1=vectorActual.getX1();
            y1=vectorActual.getY1();
            z1=vectorActual.getZ1();
            x2=vectorActual.getX2();
            y2=vectorActual.getY2();
            z2=vectorActual.getZ2();
       
            // Lanzamos el vector
            this.construir_Vector(x1, y1, z1, x2, y2, z2);
        }
        
        // Actualizamos
        this.indiceActualDeVector=0;
    }
    private void construir_Vector(double x1, double y1, double z1,double x2, double y2, double z2)
    {
        // Extraemos el color que le corresponde al interpolar
        colorResultadoDeInterpolacion = objLecturaJet.realizarInterpolacion(this.indiceActualDeVector);
        float rColorRojo,gColorVerde,bColorAzul;
        float radioPuntaFlecha;
        
        // Convertimos el color a formato RGB
        rColorRojo=colorResultadoDeInterpolacion.getR_ColorRojo();
        gColorVerde=colorResultadoDeInterpolacion.getG_ColorVerde();
        bColorAzul=colorResultadoDeInterpolacion.getB_ColorAzul();
        
        // Lanzamos la longitud del vector
        this.modificarAtributos_Lineas(3, 0, 0, 0);//Activamos estilos de líneas
        super.gl.glBegin(GL_LINE_STRIP);
            super.gl.glColor3f(rColorRojo, gColorVerde, bColorAzul);// Fijamos color
            super.gl.glVertex3d(x1, y1, z1);
            super.gl.glColor3f(rColorRojo, gColorVerde, bColorAzul);// Fijamos color
            super.gl.glVertex3d(x2, y2, z2);
        super.gl.glEnd();
        gl.glDisable(GL_LINE_STIPPLE);//Desactivamos estilo de líneas
        
        // Aumentamos el índice actual
        this.indiceActualDeVector++;
        
        // Posicionamos esfera como punta de la flecha...
        if(this.glu==null)return;
        super.gl.glColor3f(rColorRojo, gColorVerde, bColorAzul);// Fijamos color
        // Posiciona y muestra una esféra en GLU
        radioPuntaFlecha=0.05f;
        super.gl.glPushMatrix();// Copia Matriz I en cima de pila
            super.gl.glTranslated(x2, y2, z2); // Transformación: Translación
            this.quadricEsferaPuntaVector = this.glu.gluNewQuadric(); // Instanciamos objeto sombreador de cuádricas
            this.glu.gluQuadricTexture(this.quadricEsferaPuntaVector, true); // Sombreador: Habilitamos textura.
            this.glu.gluQuadricDrawStyle(this.quadricEsferaPuntaVector, GLU.GLU_FILL);// Sombreador: Modo de Visualización
            this.glu.gluQuadricOrientation(this.quadricEsferaPuntaVector, GLU.GLU_OUTSIDE);// Sombreador: Dirección Vector Normal-Caras
            this.glu.gluQuadricNormals(this.quadricEsferaPuntaVector, GLU.GLU_SMOOTH); // Sombreador: Modo de generación vectores Normales a caras
            this.glu.gluSphere(this.quadricEsferaPuntaVector, radioPuntaFlecha, 8, 8); //gluSphere(Radio, RebanadasPizza, Pilas)32
            this.glu.gluDeleteQuadric(this.quadricEsferaPuntaVector);//Libera recursos del Sombreador
        super.gl.glPopMatrix();// Reset
    }
    private void modificarAtributos_Lineas(float gruesoLinea, int cadenciaLinea, int estiloLinea, int suavizadoLinea)
    {   
        //Modificamos el grueso de la línea
        gl.glLineWidth(gruesoLinea);//glLineWidth(float)-Default=1-Grueso de Línea
        
        //Procedemos a aplicar el estilo de línea
        gl.glEnable(GL_LINE_STIPPLE);//Activar característica de patrones de línea
        switch(estiloLinea)
        {
            case 1:
                gl.glLineStipple(cadenciaLinea, (short) 0x0101); //Polilínea Punteada
            break;
            case 2:
                gl.glLineStipple(cadenciaLinea, (short) 0x00FF); //Polilínea a trazos
            break;
            case 3:
                gl.glLineStipple(cadenciaLinea, (short) 0x1C47); //Polilínea trazo-Punto
            break;
            default://Sin estilo
                gl.glDisable(GL_LINE_STIPPLE);
            break;
        }
        /*NOTA IMPORTANTE:
        *Una vez dibujada la línea desactivar polilínea con:
            gl.glDisable(GL_LINE_STIPPLE);
        *Valor recomendado CadenciaLinea=1
        */
        
        //Para aplicar color usar gl.glColor3f, ya que glColor3i NO SIRVE
        switch(estiloLinea)
        {
            case 1:
                gl.glShadeModel(GL_FLAT);//Un solo color línea segundo extremo
            break;
            default:
                //Estilo default, es equivalente a 
                gl.glShadeModel(GL_SMOOTH);//Propiedad default suavizado color
            break;
        }
    }
    
    ///Métodos para el manejo de atributos

    public Datos_OperacionBasicaEntreVectores getDatosOperacionBasicaEntreVectores()
    {
        return datosOperacionBasicaEntreVectores;
    }

    public void setGlu(GLU glu) {
        this.glu = glu;
    }
    
}
