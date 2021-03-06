///Paquete de Trabajo
package com.SAEAAV.Modelo.Graficador.Artefactos;

///Librerias
import com.SAEAAV.Modelo.Matematicas.CampoEscalar;
import com.SAEAAV.Modelo.Matematicas.Datos_CampoEscalar;
import com.SAEAAV.Modelo.Matematicas.Escalar;
import java.util.ArrayList;
import javax.media.opengl.GL2;
import static javax.media.opengl.GL2.*;

public class CamposEscalares extends Artefacto
{
    ///Atributos
    private CampoEscalar objCampoEscalar;
    private ArrayList<Escalar> escalaresCampoEscalar;
    private Datos_CampoEscalar datosCampoEscalar=null;
    private int cantidadDeEscalaresGenerados;
    
    ///Constructores
    public CamposEscalares(Datos_CampoEscalar datosCampoEscalar)
    {    
        this.datosCampoEscalar=datosCampoEscalar;
        this.inicializar();
    }
    
    ///Métodos de propósito general
    private void inicializar()
    {   
        // Realizamos lo que tenemos que realizar
        this.objCampoEscalar=new CampoEscalar(this.datosCampoEscalar);
        
        if(this.datosCampoEscalar.isEsCampoEscalar3D())
        {
            this.escalaresCampoEscalar = objCampoEscalar.crearCampoEscalar3D();
        }
        else
        {
            this.escalaresCampoEscalar = objCampoEscalar.crearCampoEscalar2D();
        }
        
        this.cantidadDeEscalaresGenerados=this.escalaresCampoEscalar.size();
        System.out.println("cantidadDeEscalaresGenerados="+cantidadDeEscalaresGenerados);
    }
    @Override
    public void construir(GL2 gl) 
    {
        super.gl=gl;
        
        super.gl.glScaled(this.datosCampoEscalar.getEscala(), this.datosCampoEscalar.getEscala(), this.datosCampoEscalar.getEscala());
        
        this.construir_CampoEscalar();
        
        super.gl=null;
    }
    
    ///Métodos de propósito específico
    private void construir_CampoEscalar()
    {
        // Variables
        Escalar escalarActual;
        double xPunto;
        double yPunto;
        double zPunto;
        double valorEscalar;
        float rColorRojo;
        float gColorVerde;
        float bColorAzul;
        
        // Recorremos arreglo
        for(int k=0; k<this.escalaresCampoEscalar.size(); k++)
        {
            // Ajustamos valores
            escalarActual=this.escalaresCampoEscalar.get(k);
            
            // Extraemos datos del escalar
            xPunto=escalarActual.getxPunto();
            yPunto=escalarActual.getyPunto();
            zPunto=escalarActual.getzPunto();
            valorEscalar=escalarActual.getValorEscalar();
            rColorRojo=escalarActual.getrColorRojo();
            gColorVerde=escalarActual.getgColorVerde();
            bColorAzul=escalarActual.getbColorAzul();
            
            // Ajustamos el color del escalar
            gl.glColor3f(rColorRojo, gColorVerde, bColorAzul);
            
            // Lanzamos el escalar
            this.construir_Escalar(xPunto, yPunto, zPunto);
        }
    }
    private void construir_Escalar(double xPunto, double yPunto, double zPunto)
    {
        // Lanzamos el escalar como un punto
        super.gl.glBegin(GL_POINTS);
            super.gl.glVertex3d(xPunto, yPunto, zPunto);
        super.gl.glEnd();
    }
    
    ///Métodos para el manejo de atributos
    public Datos_CampoEscalar getDatosCampoEscalar()
    {
        return datosCampoEscalar;
    }
    public Double getInterpolacionValorMaximo()
    {
        return this.objCampoEscalar.getDatosCampoEscalar().getInterpolacionValorMaximo();
    }
    public Double getInterpolacionValorMínimo()
    {
        return this.objCampoEscalar.getDatosCampoEscalar().getInterpolacionValorMínimo();
    }
}
