///Paquete de Trabajo
package com.SAEAAV.Modelo.Graficador.Artefactos;

///Librerias
import com.SAEAAV.Modelo.Matematicas.Constantes_Matematicas;
import com.jogamp.opengl.util.awt.TextRenderer;
import java.awt.Font;
import java.text.DecimalFormat;
import javax.media.opengl.GL2;
import static javax.media.opengl.GL2.*;

public class EjesOrtogonales extends Artefacto
{
    ///Atributos
    TextRenderer renderizadorTextoPlano;
    TextRenderer renderizadorTextoNumerico;
    DecimalFormat formateadorTextoNumeros;
    private Integer plano=null;
    private Double rango=null;
    private Double escala=null;
    
    ///Constructores
    public EjesOrtogonales(Integer plano, Double rango, Double escala)
    {
        this.plano=plano;
        this.escala=(escala==null)?1:escala;
        this.rango=(rango==null)?1:rango*0.7;
        
        // El rango solo afecta la escala
        if(escala!=null&&rango!=null)
        {
            this.rango*=escala;
        }
        
        this.inicializar();
    }
    
    ///Métodos de propósito general
    private void inicializar()
    {
        // Establecemos características del texto normal
        this.renderizadorTextoPlano = new TextRenderer(new Font("SansSerif", Font.PLAIN, 12));
        //this.renderizadorTextoNumerico = new TextRenderer(new Font("SansSerif", Font.PLAIN, 12));
        this.formateadorTextoNumeros = new DecimalFormat("0.00");//"###0.00"
    }
    @Override
    public void construir(GL2 gl) 
    {
        super.gl=gl;
        
        this.construirEjes();
        
        this.construirSimbologiaEjes();
        
        this.construirAdornosEjes();
        
        super.gl=null;
    }
    
    ///Métodos de propósito específico
    private void construirAdornosEjes()
    {
    }
    private void construirSimbologiaEjes()
    {   
        // Dibujar Letras Ejes-dibujar_Texto3D_SoloTexto(drawable,Cifra,X,Y,Z,R,G,B)
        //x=0;y=0;z=1.1;
        //r=0; g=0; b=1;
        this.dibujar_Texto3D_SoloTexto("Z", 0, 0, this.rango+0.1, 0, 0, 1);
        //x=0;y=1.1;z=0;
        //r=0; g=1; b=0;
        this.dibujar_Texto3D_SoloTexto("Y", 0, this.rango+0.1, 0, 0, 1, 0);
        //x=1.1;y=0;z=0;
        //r=1; g=0; b=0;
        this.dibujar_Texto3D_SoloTexto("X", this.rango+0.1, 0, 0, 1, 0, 0);
    }
    private void construirEjes()
    {
        // Eje X
        this.modificarAtributos_Lineas(1, 1, 3, 0);//Activamos estilos de líneas
        gl.glColor3f(1, 0, 0);
        gl.glBegin(GL_LINE_STRIP);
            gl.glVertex3d(this.rango, 0, 0);
            gl.glVertex3d(0, 0, 0);
            gl.glVertex3d(-this.rango, 0, 0);
        gl.glEnd();
        gl.glDisable(GL_LINE_STIPPLE);//Desactivamos estilo de líneas
        
        // Eje 
        this.modificarAtributos_Lineas(1, 1, 3, 0);//Activamos estilos de líneas
        gl.glColor3f(0, 1, 0);
        gl.glBegin(GL_LINE_STRIP);
            gl.glVertex3d(0, this.rango, 0);
            gl.glVertex3d(0, 0, 0);
            gl.glVertex3d(0, -this.rango, 0);
        gl.glEnd();
        gl.glDisable(GL_LINE_STIPPLE);//Desactivamos estilo de líneas
        
        // Eje Z
        this.modificarAtributos_Lineas(1, 1, 3, 0);//Activamos estilos de líneas
        gl.glColor3f(0, 0, 1);
        gl.glBegin(GL_LINE_STRIP);
            gl.glVertex3d(0, 0, this.rango);
            gl.glVertex3d(0, 0, 0);
            gl.glVertex3d(0, 0, -this.rango);
        gl.glEnd();
        gl.glDisable(GL_LINE_STIPPLE);//Desactivamos estilo de líneas
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
    private void dibujar_Texto3D_SoloTexto(String texto,double x, double y, double z, float r, float g, float b)
    {
        // Desplegamos el texto
        super.gl.glPushMatrix();
            this.renderizadorTextoPlano.begin3DRendering();// Inicia renderizado      
                super.gl.glTranslated(x, y, z);// Fija posición del texto en la escena
                if(this.plano!=null)
                {
                    if(this.plano==Constantes_Matematicas.PLANO_XY)
                    {
                        super.gl.glRotated(0, 1, 0, 0);
                    }
                    if(this.plano==Constantes_Matematicas.PLANO_XZ)
                    {
                        super.gl.glRotated(90, 1, 0, 0);
                    }
                    if(this.plano==Constantes_Matematicas.PLANO_YZ)
                    {
                        super.gl.glRotated(90, 1, 0, 0);
                        super.gl.glRotated(90, 0, 1, 0);
                    }
                }
                this.renderizadorTextoPlano.setColor(r, g, b, 1f);//Fija Color RGBA#A=1            
                this.renderizadorTextoPlano.draw3D(texto, 0.0f,0.0f, 0.0f, 0.01f);//draw3D(String texto, float x, float y, float z, float factorEscala)   
                this.renderizadorTextoPlano.flush();//Nos aseguramos de que el texto sea dibujado
            this.renderizadorTextoPlano.end3DRendering();// Finaliza Renderizado
        super.gl.glPopMatrix();
    }
    
    ///Métodos para el manejo de atributos

}
