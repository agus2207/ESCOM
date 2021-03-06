///Paquete de Trabajo
package com.SAEAAV.Modelo.Matematicas;

///Librerias
import com.Calculadora.MODULO_Calculadora_Principal;
import com.SAEAAV.Modelo.Graficador.Interpolaciones.ColorFormatoRGB;
import com.SAEAAV.Modelo.Graficador.Interpolaciones.InterpolacionColoresJet;
import java.util.ArrayList;

public class CampoVectorial
{
    ///Atributos
    private MODULO_Calculadora_Principal objCalculadora_MainEjemplo=new MODULO_Calculadora_Principal();
    private InterpolacionColoresJet objLecturaJet=new InterpolacionColoresJet();
    private ArrayList<Vector> listaCampoVectorial=new ArrayList<Vector>();
    private double valorMaximo=-999999999;
    private double valorMinimo=+999999999;
    private Datos_CampoVectorial datosCampoVectorial=null;
    
    ///Constructores
    public CampoVectorial(Datos_CampoVectorial datosCampoVectorial) 
    {
        this.datosCampoVectorial=datosCampoVectorial;
    }
    
    ///Métodos de propósito general
    public ArrayList<Vector> crearCampoVectorial3D()
    {
        // Generamos campo
        this.generar_CampoVectorial3D();
        
        // Decimos cuando los cálculos estén listos...
        java.awt.Toolkit.getDefaultToolkit().beep();
        
        // Retoramos
        return this.listaCampoVectorial;
    }
    public ArrayList<Vector> crearCampoVectorial2D()
    {   
        // Generamos campo
        this.generar_CampoVectorial2D();
        
        // Decimos cuando los cálculos estén listos...
        java.awt.Toolkit.getDefaultToolkit().beep();
        
        // Retoramos
        return this.listaCampoVectorial;
    }
    
    ///Métodos de propósito específico
    private void generar_CampoVectorial3D()
    {
        // Variables
        double i,j,k,d, xValor, yValor, zValor;
        Vector vectorGenerado;
        float rColorRojo,gColorVerde,bColorAzul;
        Vector vectorACompletar;
        double valorAInterpolar;
        ColorFormatoRGB colorResultadoDeInterpolacion;
        double incremento;
        
        // Inicializamos
        d=this.datosCampoVectorial.getLadoDeCubo()/2;
        vectorGenerado=null;
        incremento=this.datosCampoVectorial.getIncremento();
        
        // Recorremos
        for(j=-d+incremento; j<=d-incremento; j+=incremento)
        {
            for(k=-d+incremento; k<=d-incremento; k+=incremento)
            {
                for(i=-d+incremento; i<=d-incremento; i+=incremento)
                {
                    // Ajustamos valores del punto actual
                    xValor=i;
                    yValor=j;
                    zValor=k;
                    
                    // Invocamos a la función que genera campo
                    vectorGenerado=this.generar_UnVectorDeCampo(xValor, yValor, zValor);
                    
                    // Ahora añadimos al conjunto de vectores
                    this.listaCampoVectorial.add(vectorGenerado);
                    
                    // Inicializamos
                    vectorGenerado=null;
                }
            }
        }
        
        // Actualizamos valores para la interpolación
        this.objLecturaJet.setValorMaximo(this.valorMaximo);
        this.objLecturaJet.setValorMinimo(this.valorMinimo);
        this.datosCampoVectorial.setInterpolacionValorMaximo(valorMaximo);
        this.datosCampoVectorial.setInterpolacionValorMínimo(valorMinimo);
        
        // Recorremos por una segunda vez para interporlar el color
        for(int pos=0; pos<this.listaCampoVectorial.size(); pos++)
        {
            // Obtenemos el escalar a completar
            vectorACompletar = this.listaCampoVectorial.get(pos);
            
            // Extraemos el valor que servirá para la interpolación
            valorAInterpolar=vectorACompletar.getMagnitudOriginal();
            
            // Extraemos el color que le corresponde al interpolar
            colorResultadoDeInterpolacion = objLecturaJet.realizarInterpolacion(valorAInterpolar);
            
            // Convertimos el color a formato RGB
            rColorRojo=colorResultadoDeInterpolacion.getR_ColorRojo();
            gColorVerde=colorResultadoDeInterpolacion.getG_ColorVerde();
            bColorAzul=colorResultadoDeInterpolacion.getB_ColorAzul();
            
            // Insertamos el color en el formato RGB
            vectorACompletar.setrColorRojo(rColorRojo);
            vectorACompletar.setgColorVerde(gColorVerde);
            vectorACompletar.setbColorAzul(bColorAzul);
            
            // Actualizamos el vector
            this.listaCampoVectorial.set(pos, vectorACompletar);
        }
    }
    private void generar_CampoVectorial2D()
    {
        // Variables
        double i,j,k,d, xValor, yValor, zValor;
        Vector vectorGenerado;
        float rColorRojo,gColorVerde,bColorAzul;
        Vector vectorACompletar;
        double valorAInterpolar;
        ColorFormatoRGB colorResultadoDeInterpolacion;
        Integer plano;
        double incremento;
        
        // Inicializamos
        d=this.datosCampoVectorial.getLadoDeCuadrado()/2;
        vectorGenerado=null;
        plano=this.datosCampoVectorial.getPlano();
        incremento=this.datosCampoVectorial.getIncremento();
        
        // Constrímos de acuerdo al plano que corresponde
        if(plano==Constantes_Matematicas.PLANO_XY)
        {
            // Recorremos;Z=0
            for(i=-d+incremento; i<=d-incremento; i+=incremento)
            {
                for(j=-d+incremento; j<=d-incremento; j+=incremento)
                {
                    // Ajustamos valores del punto actual
                    xValor=i;
                    yValor=j;
                    zValor=0;
                    
                    // Invocamos a la función que genera campo
                    vectorGenerado=this.generar_UnVectorDeCampo(xValor, yValor, zValor);
                    
                    // Ahora añadimos al conjunto de vectores
                    this.listaCampoVectorial.add(vectorGenerado);
                    
                    // Inicializamos
                    vectorGenerado=null;
                }
            }
        }
        if(plano==Constantes_Matematicas.PLANO_XZ)
        {
            // Recorremos;Y=0
            for(i=-d+incremento; i<=d-incremento; i+=incremento)
            {
                for(k=-d+incremento; k<=d-incremento; k+=incremento)
                {
                    // Ajustamos valores del punto actual
                    xValor=i;
                    yValor=0;
                    zValor=k;

                    // Invocamos a la función que genera campo
                    vectorGenerado=this.generar_UnVectorDeCampo(xValor, yValor, zValor);
                    
                    // Ahora añadimos al conjunto de vectores
                    this.listaCampoVectorial.add(vectorGenerado);
                    
                    // Inicializamos
                    vectorGenerado=null;
                }
            }
        }
        if(plano==Constantes_Matematicas.PLANO_YZ)
        {
            // Recorremos;Y=0
            for(j=-d+incremento; j<=d-incremento; j+=incremento)
            {
                for(k=-d+incremento; k<=d-incremento; k+=incremento)
                {
                    // Ajustamos valores del punto actual
                    xValor=0;
                    yValor=j;
                    zValor=k;

                    // Invocamos a la función que genera campo
                    vectorGenerado=this.generar_UnVectorDeCampo(xValor, yValor, zValor);
                    
                    // Ahora añadimos al conjunto de vectores
                    this.listaCampoVectorial.add(vectorGenerado);
                    
                    // Inicializamos
                    vectorGenerado=null;
                }
            }
        }
        
        // Actualizamos valores para la interpolación
        this.objLecturaJet.setValorMaximo(this.valorMaximo);
        this.objLecturaJet.setValorMinimo(this.valorMinimo);
        this.datosCampoVectorial.setInterpolacionValorMaximo(valorMaximo);
        this.datosCampoVectorial.setInterpolacionValorMínimo(valorMinimo);
        
        // Recorremos por una segunda vez para interporlar el color
        for(int pos=0; pos<this.listaCampoVectorial.size(); pos++)
        {
            // Obtenemos el escalar a completar
            vectorACompletar = this.listaCampoVectorial.get(pos);
            
            // Extraemos el valor que servirá para la interpolación
            valorAInterpolar=vectorACompletar.getMagnitudOriginal();
            
            // Extraemos el color que le corresponde al interpolar
            colorResultadoDeInterpolacion = objLecturaJet.realizarInterpolacion(valorAInterpolar);
            
            // Convertimos el color a formato RGB
            rColorRojo=colorResultadoDeInterpolacion.getR_ColorRojo();
            gColorVerde=colorResultadoDeInterpolacion.getG_ColorVerde();
            bColorAzul=colorResultadoDeInterpolacion.getB_ColorAzul();
            
            // Insertamos el color en el formato RGB
            vectorACompletar.setrColorRojo(rColorRojo);
            vectorACompletar.setgColorVerde(gColorVerde);
            vectorACompletar.setbColorAzul(bColorAzul);
            
            // Actualizamos el vector
            this.listaCampoVectorial.set(pos, vectorACompletar);
        }
    }
    private Vector generar_UnVectorDeCampo(double xValor, double yValor, double zValor)
    {
        // Variables
        double x1, y1, z1, x2, y2, z2, deltaX, deltaY, deltaZ;
        Vector vectorResultado;
        String parametrica_M_xyz;
        String parametrica_N_xyz;
        String parametrica_P_xyz;
        Double magnitudDeCadaVector;
        
        // Inicializamos
        x1=xValor;
        y1=yValor;
        z1=zValor;
        deltaX=0;
        deltaY=0;
        deltaZ=0;
        parametrica_M_xyz=this.datosCampoVectorial.getParametrica_M_xyz();
        parametrica_N_xyz=this.datosCampoVectorial.getParametrica_N_xyz();
        parametrica_P_xyz=this.datosCampoVectorial.getParametrica_P_xyz();
        magnitudDeCadaVector=this.datosCampoVectorial.getMagnitudDeCadaVector();
        
        // Calculamos los valores de diferencia
        if(parametrica_M_xyz!=null&&parametrica_N_xyz!=null&&parametrica_P_xyz!=null)
        {
            deltaX=objCalculadora_MainEjemplo.Principal(parametrica_M_xyz,xValor,yValor,zValor);
            deltaY=objCalculadora_MainEjemplo.Principal(parametrica_N_xyz,xValor,yValor,zValor);
            deltaZ=objCalculadora_MainEjemplo.Principal(parametrica_P_xyz,xValor,yValor,zValor);
        }
        
        // Calculamos los valores de la cabeza del vector
        x2=x1+deltaX;
        y2=y1+deltaY;
        z2=z1+deltaZ;
        
        // Generamos el vector
        //vectorResultado=new Vector(x1,y1,z1,x2,y2,z2);
        vectorResultado=this.ajustarVector(x1, y1, z1, x2, y2, z2, magnitudDeCadaVector);
        
        // Retornamos resultado
        return vectorResultado;
    }
    private Vector ajustarVector(double xi,double yi,double zi,double xf,double yf,double zf, double magnitudNueva)
    {
        // Variables
        double rx, ry, rz, lamda, magnitudActual, xm, ym, zm;
        Vector vectorResultado;
        
        // Calculamos la magnitud actual del vector
        magnitudActual=Math.sqrt(Math.pow((xf-xi), 2)+Math.pow((yf-yi), 2)+Math.pow((zf-zi), 2));
        
        // Calculamos lamda
        lamda=magnitudNueva/magnitudActual;
        
        // Calculamos las coordenadas del "Nuevo punto final"
        rx=xi+(xf-xi)*lamda;
        ry=yi+(yf-yi)*lamda;
        rz=zi+(zf-zi)*lamda;
        
        // Generamos el vector
        vectorResultado=new Vector(xi,yi,zi,rx,ry,rz);
        
        // Ingresamos datos extras obtenidos 
        xm=xi+(xf-xi)*0.5;//Calculo del punto medio
        ym=yi+(yf-yi)*0.5;//Calculo del punto medio
        zm=zi+(zf-zi)*0.5;//Calculo del punto medio
        vectorResultado.setMagnitudOriginal(magnitudActual);
        vectorResultado.setXm(xm);
        vectorResultado.setYm(ym);
        vectorResultado.setZm(zm);
        
        // Ajustamos los valores máximos y mínimos del campo
        //if(magnitudActual<0.01)System.out.println("**>>magnitudActual="+magnitudActual+"::["+xi+";"+yi+";"+zi+"]");
        if(magnitudActual<this.valorMinimo)this.valorMinimo=magnitudActual;
        if(this.valorMaximo<magnitudActual)this.valorMaximo=magnitudActual;
        
        // Retornamos el resultado
        return vectorResultado;
    }
    
    ///Métodos para el manejo de atributos
    public ArrayList<Vector> getListaCampoVectorial() 
    {
        return listaCampoVectorial;
    }
    public Datos_CampoVectorial getDatosCampoVectorial()
    {
        return datosCampoVectorial;
    }
    
    ///Métodos Tester
    
}
