///Paquete de Trabajo
package com.SAEAAV.Modelo.Matematicas;

///Librerias

public class Escalar
{
    ///Atributos
    private double xPunto;
    private double yPunto;
    private double zPunto;
    private double valorEscalar;
    private float rColorRojo;
    private float gColorVerde;
    private float bColorAzul;
    
    ///Constructores
    public Escalar()
    {
    }
    public Escalar(double xPunto, double yPunto, double zPunto, double valorEscalar, float rColorRojo, float gColorVerde, float bColorAzul) 
    {
        this.xPunto = xPunto;
        this.yPunto = yPunto;
        this.zPunto = zPunto;
        this.valorEscalar = valorEscalar;
        this.rColorRojo = rColorRojo;
        this.gColorVerde = gColorVerde;
        this.bColorAzul = bColorAzul;
    }
    
    ///Métodos de propósito general
    @Override
    public String toString()
    {
        return "["+this.xPunto+","+this.yPunto+","+this.zPunto+"]="+valorEscalar+"-("+this.rColorRojo+","+this.gColorVerde+","+this.bColorAzul+")";
    }
    
    ///Métodos de propósito específico
  
    ///Métodos para el manejo de atributos

    public double getxPunto() {
        return xPunto;
    }

    public void setxPunto(double xPunto) {
        this.xPunto = xPunto;
    }

    public double getyPunto() {
        return yPunto;
    }

    public void setyPunto(double yPunto) {
        this.yPunto = yPunto;
    }

    public double getzPunto() {
        return zPunto;
    }

    public void setzPunto(double zPunto) {
        this.zPunto = zPunto;
    }

    public double getValorEscalar() {
        return valorEscalar;
    }

    public void setValorEscalar(double valorEscalar) {
        this.valorEscalar = valorEscalar;
    }

    public float getrColorRojo() {
        return rColorRojo;
    }

    public void setrColorRojo(float rColorRojo) {
        this.rColorRojo = rColorRojo;
    }

    public float getgColorVerde() {
        return gColorVerde;
    }

    public void setgColorVerde(float gColorVerde) {
        this.gColorVerde = gColorVerde;
    }

    public float getbColorAzul() {
        return bColorAzul;
    }

    public void setbColorAzul(float bColorAzul) {
        this.bColorAzul = bColorAzul;
    }
    
}
