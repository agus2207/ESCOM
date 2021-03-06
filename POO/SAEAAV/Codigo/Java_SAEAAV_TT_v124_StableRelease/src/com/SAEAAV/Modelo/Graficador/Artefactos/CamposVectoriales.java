///Paquete de Trabajo
package com.SAEAAV.Modelo.Graficador.Artefactos;

///Librerias
import com.SAEAAV.Modelo.Matematicas.CampoVectorial;
import com.SAEAAV.Modelo.Matematicas.Datos_CampoVectorial;
import com.SAEAAV.Modelo.Matematicas.Vector;
import java.util.ArrayList;
import javax.media.opengl.GL2;
import static javax.media.opengl.GL2.*;

public class CamposVectoriales extends Artefacto
{
    ///Atributos
    private CampoVectorial objCampoVectorial;
    private ArrayList<Vector> vectoresCampoVectorial;
    private Datos_CampoVectorial datosCampoVectorial=null;
    private int cantidadDeVectoresGenerados;
    
    ///Constructores
    public CamposVectoriales(Datos_CampoVectorial datosCampoVectorial)
    {    
        this.datosCampoVectorial=datosCampoVectorial;
        this.inicializar();
    }
    
    ///Métodos de propósito general
    private void inicializar()
    {   
        // Realizamos lo que tenemos que realizar
        this.objCampoVectorial=new CampoVectorial(datosCampoVectorial);
        if(this.datosCampoVectorial.isEsCampoVectorial3D())
        {
            this.vectoresCampoVectorial = objCampoVectorial.crearCampoVectorial3D();
        }
        if(!this.datosCampoVectorial.isEsCampoVectorial3D())
        {
            this.vectoresCampoVectorial = objCampoVectorial.crearCampoVectorial2D();
        }
        
        this.cantidadDeVectoresGenerados=this.vectoresCampoVectorial.size();
        System.out.println("cantidadDeVectoresGenerados="+cantidadDeVectoresGenerados);
    }
    @Override
    public void construir(GL2 gl) 
    {
        super.gl=gl;
        
        super.gl.glScaled(this.datosCampoVectorial.getEscala(), this.datosCampoVectorial.getEscala(), this.datosCampoVectorial.getEscala());
        
        this.construir_CampoVectorial();
        
        super.gl=null;
    }
    
    ///Métodos de propósito específico
    private void construir_CampoVectorial()
    {
        // Variables
        double x1,y1,z1,x2,y2,z2, xm, ym, zm;
        float rColorRojo, gColorVerde, bColorAzul;
        Vector vectorActual;
        
        // Recorremos arreglo
        for(int k=0; k<this.vectoresCampoVectorial.size(); k++)
        {
            // Ajustamos valores
            vectorActual=this.vectoresCampoVectorial.get(k);
            
            // Extraemos datos del vector
            x1=vectorActual.getX1();
            y1=vectorActual.getY1();
            z1=vectorActual.getZ1();
            x2=vectorActual.getX2();
            y2=vectorActual.getY2();
            z2=vectorActual.getZ2();
            xm=vectorActual.getXm();
            ym=vectorActual.getYm();
            zm=vectorActual.getZm();
            rColorRojo=vectorActual.getrColorRojo();
            gColorVerde=vectorActual.getgColorVerde();
            bColorAzul=vectorActual.getbColorAzul();
            
            // Lanzamos el vector
            this.construir_Vector(x1, y1, z1, x2, y2, z2, xm, ym, zm, rColorRojo, gColorVerde, bColorAzul);
        }
    }
    private void construir_Vector(double x1, double y1, double z1,double x2, double y2, double z2, double xm, double ym, double zm, float rColorRojo, float gColorVerde, float bColorAzul)
    {
        // Lanzamos la longitud del vector
        super.gl.glBegin(GL_LINE_STRIP);
            super.gl.glColor3f(0, 0, 0);// Fijamos color de base
            super.gl.glVertex3d(x1, y1, z1);
            //super.gl.glColor3f(1, 1, 1);// Fijamos color de punto medio
            //super.gl.glVertex3d(xm, ym, zm);
            super.gl.glColor3f(rColorRojo, gColorVerde, bColorAzul);// Fijamos color 
            super.gl.glVertex3d(x2, y2, z2);
        super.gl.glEnd();
    }
    private void construir_Vector(double x1, double y1, double z1,double x2, double y2, double z2)
    {
        // Lanzamos la longitud del vector
        super.gl.glBegin(GL_LINE_STRIP);
            super.gl.glColor3f(0, 1, 1);// Fijamos color
            super.gl.glVertex3d(x1, y1, z1);
            super.gl.glColor3f(1, 1, 0);// Fijamos color
            super.gl.glVertex3d(x2, y2, z2);
        super.gl.glEnd();
    }
    
    ///Métodos para el manejo de atributos

    public Datos_CampoVectorial getDatosCampoVectorial() 
    {
        return datosCampoVectorial;
    }
    
    public Double getInterpolacionValorMaximo()
    {
        return this.objCampoVectorial.getDatosCampoVectorial().getInterpolacionValorMaximo();
    }
    public Double getInterpolacionValorMínimo()
    {
        return this.objCampoVectorial.getDatosCampoVectorial().getInterpolacionValorMínimo();
    }
}
