///Paquete de Trabajo
package com.SAEAAV.Modelo.Graficador.Artefactos;

///Librerias

public class Vertice
{
    ///Atributos
    private double x;
    private double y;
    private double z;
    
    ///Constructores
    public Vertice()
    {
    }
    public Vertice(double x, double y, double z) 
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    
    ///Métodos de propósito General
    @Override
    public String toString()
    {
        return "V("+this.x+","+this.y+","+this.z+");"; 
    }
    
    ///Métodos de propósito Específico

    ///Métodos para el manejo de Atributos

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }
    
}
