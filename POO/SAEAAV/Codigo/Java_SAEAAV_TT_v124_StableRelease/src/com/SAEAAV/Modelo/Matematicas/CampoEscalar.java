///Paquete de Trabajo
package com.SAEAAV.Modelo.Matematicas;

///Librerias
import Utilerias.Utileria_Matematicas;
import com.Calculadora.MODULO_Calculadora_Principal;
import com.SAEAAV.Modelo.Graficador.Interpolaciones.ColorFormatoRGB;
import com.SAEAAV.Modelo.Graficador.Interpolaciones.InterpolacionColoresJet;
import java.util.ArrayList;

public class CampoEscalar
{
    ///Atributos
    private Utileria_Matematicas obj_UTL_matematicas=new Utileria_Matematicas();
    private MODULO_Calculadora_Principal objCalculadora_MainEjemplo=new MODULO_Calculadora_Principal();
    private InterpolacionColoresJet objLecturaJet=new InterpolacionColoresJet();
    private ArrayList<Escalar> listaCampoEscalar=new ArrayList<Escalar>();;
    private double valorMaximo=-999999999;;
    private double valorMinimo=+999999999;
    private Datos_CampoEscalar datosCampoEscalar=null;
    
    ///Constructores
    public CampoEscalar(Datos_CampoEscalar datosCampoEscalar) 
    {
        this.datosCampoEscalar=datosCampoEscalar;
    }
    
    ///Métodos de propósito general
    public ArrayList<Escalar> crearCampoEscalar3D()
    {
        // Generamos campo
        this.generar_CampoEscalar3D();
        
        // Decimos cuando los cálculos estén listos...
        java.awt.Toolkit.getDefaultToolkit().beep();
        
        // Retoramos
        return this.listaCampoEscalar;
    }
    public ArrayList<Escalar> crearCampoEscalar2D()
    {   
        // Generamos campo
        this.generar_CampoEscalar2D();
        
        // Decimos cuando los cálculos estén listos...
        java.awt.Toolkit.getDefaultToolkit().beep();
        
        // Retoramos
        return this.listaCampoEscalar;
    }
    
    ///Métodos de propósito específico
    private void generar_CampoEscalar3D()
    {
        // Variables
        double i,j,k,d, xValor, yValor, zValor;
        Escalar escalarGenerado;
        float rColorRojo,gColorVerde,bColorAzul;
        Escalar escalarACompletar;
        double valorAInterpolar;
        ColorFormatoRGB colorResultadoDeInterpolacion;
        double incremento;
        double valorAproximadoEscalar;
        
        // Inicializamos
        d=this.datosCampoEscalar.getLadoDeCubo()/2;
        escalarGenerado=null;
        incremento=this.datosCampoEscalar.getIncremento();
       
        // Recorremos
        for(j=-d; j<=d; j+=incremento)
        {
            for(k=-d; k<=d; k+=incremento)
            {
                for(i=-d; i<=d; i+=incremento)
                {
                    // Ajustamos valores del punto actual
                    xValor=i;
                    yValor=j;
                    zValor=k;
                    
                    // Invocamos a la función que genera campo
                    escalarGenerado=this.generar_UnEscalarDeCampo(xValor, yValor, zValor);
                    
                    // Realizamos comprobación de uso de constante
                    valorAproximadoEscalar=obj_UTL_matematicas.efectuarRedondeo(escalarGenerado.getValorEscalar(), 1);
                    if(!datosCampoEscalar.isUsaConstanteEspecifica())
                    {
                        // Ahora añadimos al conjunto de escalares
                        this.listaCampoEscalar.add(escalarGenerado);
                    }
                    else
                    {
                        // Comprobamos que concuerde con el valor de la constante elegida
                        if( datosCampoEscalar.getValorConstanteEspecifica()==valorAproximadoEscalar )
                        {
                            // Ahora añadimos al conjunto de escalares
                            this.listaCampoEscalar.add(escalarGenerado);
                        }
                    }
                    
                    // Inicializamos
                    escalarGenerado=null;
                }
            }
        }
        
        // Actualizamos valores para la interpolación
        this.objLecturaJet.setValorMaximo(this.valorMaximo);
        this.objLecturaJet.setValorMinimo(this.valorMinimo);
        this.datosCampoEscalar.setInterpolacionValorMaximo(valorMaximo);
        this.datosCampoEscalar.setInterpolacionValorMínimo(valorMinimo);
        
        // Recorremos por una segunda vez para interporlar el color
        for(int pos=0; pos<this.listaCampoEscalar.size(); pos++)
        {
            // Obtenemos el escalar a completar
            escalarACompletar = this.listaCampoEscalar.get(pos);
            
            // Extraemos el valor que servirá para la interpolación
            valorAInterpolar=escalarACompletar.getValorEscalar();
            
            // Extraemos el color que le corresponde al interpolar
            colorResultadoDeInterpolacion = objLecturaJet.realizarInterpolacion(valorAInterpolar);
            
            // Convertimos el color a formato RGB
            rColorRojo=colorResultadoDeInterpolacion.getR_ColorRojo();
            gColorVerde=colorResultadoDeInterpolacion.getG_ColorVerde();
            bColorAzul=colorResultadoDeInterpolacion.getB_ColorAzul();
            
            // Insertamos el color en el formato RGB
            escalarACompletar.setrColorRojo(rColorRojo);
            escalarACompletar.setgColorVerde(gColorVerde);
            escalarACompletar.setbColorAzul(bColorAzul);
            
            // Actualizamos el escalar
            this.listaCampoEscalar.set(pos, escalarACompletar);
        }
    }
    
    private void generar_CampoEscalar2D()
    {
        // Variables
        double i,j,k,d, xValor, yValor, zValor;
        Escalar escalarGenerado;
        float rColorRojo,gColorVerde,bColorAzul;
        Escalar escalarACompletar;
        double valorAInterpolar;
        ColorFormatoRGB colorResultadoDeInterpolacion;
        double incremento;
        Integer plano;
        double valorAproximadoEscalar;
        
        // Inicializamos
        d=this.datosCampoEscalar.getLadoDeCuadrado()/2;
        escalarGenerado=null;
        incremento=this.datosCampoEscalar.getIncremento();
        plano=this.datosCampoEscalar.getPlano();
        valorAproximadoEscalar=0.0;
        
        if(plano==Constantes_Matematicas.PLANO_XY)
        {
            // Recorremos;Z=0
            for(i=-d; i<=d; i+=incremento)
            {
                for(j=-d; j<=d; j+=incremento)
                {
                    // Ajustamos valores del punto actual
                    xValor=i;
                    yValor=j;
                    zValor=0;

                    // Invocamos a la función que genera campo
                    escalarGenerado=this.generar_UnEscalarDeCampo(xValor, yValor, zValor);
                            
                    // Realizamos comprobación de uso de constante
                    valorAproximadoEscalar=obj_UTL_matematicas.efectuarRedondeo(escalarGenerado.getValorEscalar(), 1);
                    if(!datosCampoEscalar.isUsaConstanteEspecifica())
                    {
                        // Ahora añadimos al conjunto de escalares
                        this.listaCampoEscalar.add(escalarGenerado);
                    }
                    else
                    {
                        // Comprobamos que concuerde con el valor de la constante elegida
                        if( datosCampoEscalar.getValorConstanteEspecifica()==valorAproximadoEscalar )
                        {
                            // Ahora añadimos al conjunto de escalares
                            this.listaCampoEscalar.add(escalarGenerado);
                        }
                    }
                    
                    // Inicializamos
                    escalarGenerado=null;
                }
            }
        }

        if(plano==Constantes_Matematicas.PLANO_XZ)
        {
            // Recorremos;Y=0
            for(i=-d; i<=d; i+=incremento)
            {
                for(k=-d; k<=d; k+=incremento)
                {
                    // Ajustamos valores del punto actual
                    xValor=i;
                    yValor=0;
                    zValor=k;

                    // Invocamos a la función que genera campo
                    escalarGenerado=this.generar_UnEscalarDeCampo(xValor, yValor, zValor);

                    // Realizamos comprobación de uso de constante
                    valorAproximadoEscalar=obj_UTL_matematicas.efectuarRedondeo(escalarGenerado.getValorEscalar(), 1);
                    if(!datosCampoEscalar.isUsaConstanteEspecifica())
                    {
                        // Ahora añadimos al conjunto de escalares
                        this.listaCampoEscalar.add(escalarGenerado);
                    }
                    else
                    {
                        // Comprobamos que concuerde con el valor de la constante elegida
                        if( datosCampoEscalar.getValorConstanteEspecifica()==valorAproximadoEscalar )
                        {
                            // Ahora añadimos al conjunto de escalares
                            this.listaCampoEscalar.add(escalarGenerado);
                        }
                    }

                    // Inicializamos
                    escalarGenerado=null;
                }
            }
        }
        if(plano==Constantes_Matematicas.PLANO_YZ)
        {
            // Recorremos;Y=0
            for(j=-d; j<=d; j+=incremento)
            {
                for(k=-d; k<=d; k+=incremento)
                {
                    // Ajustamos valores del punto actual
                    xValor=0;
                    yValor=j;
                    zValor=k;

                    // Invocamos a la función que genera campo
                    escalarGenerado=this.generar_UnEscalarDeCampo(xValor, yValor, zValor);

                    // Realizamos comprobación de uso de constante
                    valorAproximadoEscalar=obj_UTL_matematicas.efectuarRedondeo(escalarGenerado.getValorEscalar(), 1);
                    if(!datosCampoEscalar.isUsaConstanteEspecifica())
                    {
                        // Ahora añadimos al conjunto de escalares
                        this.listaCampoEscalar.add(escalarGenerado);
                    }
                    else
                    {
                        // Comprobamos que concuerde con el valor de la constante elegida
                        if( datosCampoEscalar.getValorConstanteEspecifica()==valorAproximadoEscalar )
                        {
                            // Ahora añadimos al conjunto de escalares
                            this.listaCampoEscalar.add(escalarGenerado);
                        }
                    }

                    // Inicializamos
                    escalarGenerado=null;
                }
            }
        }
        
        // Actualizamos valores para la interpolación
        this.objLecturaJet.setValorMaximo(this.valorMaximo);
        this.objLecturaJet.setValorMinimo(this.valorMinimo);
        this.datosCampoEscalar.setInterpolacionValorMaximo(valorMaximo);
        this.datosCampoEscalar.setInterpolacionValorMínimo(valorMinimo);
        
        // Recorremos por una segunda vez para interporlar el color
        for(int pos=0; pos<this.listaCampoEscalar.size(); pos++)
        {
            // Obtenemos el escalar a completar
            escalarACompletar = this.listaCampoEscalar.get(pos);
            
            // Extraemos el valor que servirá para la interpolación
            valorAInterpolar=escalarACompletar.getValorEscalar();
            
            // Extraemos el color que le corresponde al interpolar
            colorResultadoDeInterpolacion = objLecturaJet.realizarInterpolacion(valorAInterpolar);
            
            // Convertimos el color a formato RGB
            rColorRojo=colorResultadoDeInterpolacion.getR_ColorRojo();
            gColorVerde=colorResultadoDeInterpolacion.getG_ColorVerde();
            bColorAzul=colorResultadoDeInterpolacion.getB_ColorAzul();
            
            // Insertamos el color en el formato RGB
            escalarACompletar.setrColorRojo(rColorRojo);
            escalarACompletar.setgColorVerde(gColorVerde);
            escalarACompletar.setbColorAzul(bColorAzul);
            
            // Actualizamos el escalar
            this.listaCampoEscalar.set(pos, escalarACompletar);
        }
    }
    private Escalar generar_UnEscalarDeCampo(double xValor, double yValor, double zValor)
    {
        // Variables
        double xPunto,yPunto,zPunto,valorEscalar;
        Escalar escalarResultado;
        String ecuacionCampoEscalar;
        
        // Inicializamos
        valorEscalar=0;
        xPunto=xValor;
        yPunto=yValor;
        zPunto=zValor;
        ecuacionCampoEscalar=this.datosCampoEscalar.getEcuacionCampoEscalar();
        
        
        //Procesamos con el módulo correspondiente
        valorEscalar=objCalculadora_MainEjemplo.Principal(ecuacionCampoEscalar,xValor,yValor,zValor);
        //if(valorEscalar==0)System.out.println("**>>valorEscalar="+valorEscalar+"::["+xValor+";"+yValor+";"+zValor+"]");

        // Ajustamos los valores máximos y mínimos del campo
        if(valorEscalar<this.valorMinimo)this.valorMinimo=valorEscalar;
        if(this.valorMaximo<valorEscalar)this.valorMaximo=valorEscalar;
        
        // Generamos el escalar
        escalarResultado=new Escalar();
        
        // Insertamos valores
        escalarResultado.setValorEscalar(valorEscalar);
        escalarResultado.setxPunto(xPunto);
        escalarResultado.setyPunto(yPunto);
        escalarResultado.setzPunto(zPunto);
        
        // Retornamos resultado
        return escalarResultado;
    }
    
    ///Métodos para el manejo de atributos
    public ArrayList<Escalar> getListaCampoEscalar() 
    {
        return listaCampoEscalar;
    }
    public Datos_CampoEscalar getDatosCampoEscalar() 
    {
        return datosCampoEscalar;
    }
    
    ///Métodos Tester

    
    
}
