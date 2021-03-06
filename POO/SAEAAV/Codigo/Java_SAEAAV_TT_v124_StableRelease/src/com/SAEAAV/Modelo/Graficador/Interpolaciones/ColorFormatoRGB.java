///Paquete de Trabajo
package com.SAEAAV.Modelo.Graficador.Interpolaciones;

///Librerias

public class ColorFormatoRGB
{
    ///Atributos
    private float r_ColorRojo;
    private float g_ColorVerde;
    private float b_ColorAzul;

    ///Constructores
    public ColorFormatoRGB() 
    {
    }
    public ColorFormatoRGB(float r_ColorRojo, float g_ColorVerde, float b_ColorAzul) 
    {
        this.r_ColorRojo = r_ColorRojo;
        this.g_ColorVerde = g_ColorVerde;
        this.b_ColorAzul = b_ColorAzul;
    }
    
    ///Métodos de propósito general
    @Override
    public String toString()
    {
        return "{"+this.r_ColorRojo+","+this.g_ColorVerde+","+this.b_ColorAzul+"}";
    }
    
    ///Métodos de propósito específico
  
    ///Métodos para el manejo de atributos

    public float getR_ColorRojo() {
        return r_ColorRojo;
    }

    public void setR_ColorRojo(float r_ColorRojo) {
        this.r_ColorRojo = r_ColorRojo;
    }

    public float getG_ColorVerde() {
        return g_ColorVerde;
    }

    public void setG_ColorVerde(float g_ColorVerde) {
        this.g_ColorVerde = g_ColorVerde;
    }

    public float getB_ColorAzul() {
        return b_ColorAzul;
    }

    public void setB_ColorAzul(float b_ColorAzul) {
        this.b_ColorAzul = b_ColorAzul;
    }
    
}
