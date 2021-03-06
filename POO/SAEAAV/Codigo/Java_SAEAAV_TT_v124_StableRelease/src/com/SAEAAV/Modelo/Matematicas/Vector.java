///Paquete de Trabajo
package com.SAEAAV.Modelo.Matematicas;

///Librerias

public class Vector
{
    ///Atributos
    private double x1;
    private double y1;
    private double z1;
    private double x2;
    private double y2;
    private double z2;
    private double magnitudOriginal;
    private float rColorRojo;
    private float gColorVerde;
    private float bColorAzul;
    private double xm;//Coordenadas del Punto Medio
    private double ym;//Coordenadas del Punto Medio
    private double zm;//Coordenadas del Punto Medio
    
    ///Constructores
    public Vector(double x1, double y1, double z1, double x2, double y2, double z2)
    {
        this.x1 = x1;
        this.y1 = y1;
        this.z1 = z1;
        this.x2 = x2;
        this.y2 = y2;
        this.z2 = z2;
    }
    
    ///Métodos de propósito general
    @Override
    public String toString()
    {
        return "["+this.x1+","+this.y1+","+this.z1+"]-["+this.x2+","+this.y2+","+this.z2+"]";
    }
    ///Métodos de propósito específico
  
    ///Métodos para el manejo de atributos

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getY1() {
        return y1;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }

    public double getZ1() {
        return z1;
    }

    public void setZ1(double Z1) {
        this.z1 = Z1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public double getY2() {
        return y2;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }

    public double getZ2() {
        return z2;
    }

    public void setZ2(double Z2) {
        this.z2 = Z2;
    }

    public double getMagnitudOriginal() {
        return magnitudOriginal;
    }

    public void setMagnitudOriginal(double magnitudOriginal) {
        this.magnitudOriginal = magnitudOriginal;
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

    public double getXm() {
        return xm;
    }

    public void setXm(double xm) {
        this.xm = xm;
    }

    public double getYm() {
        return ym;
    }

    public void setYm(double ym) {
        this.ym = ym;
    }

    public double getZm() {
        return zm;
    }

    public void setZm(double zm) {
        this.zm = zm;
    }

}
