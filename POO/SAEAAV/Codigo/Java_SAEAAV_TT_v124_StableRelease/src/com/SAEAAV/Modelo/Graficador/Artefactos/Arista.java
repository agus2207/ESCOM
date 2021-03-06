///Paquete de Trabajo
package com.SAEAAV.Modelo.Graficador.Artefactos;

///Librerias

public class Arista
{
    ///Atributos
    private Vertice vInicial;
    private Vertice vFinal;
    
    ///Constructores
    public Arista()
    {
    }
    public Arista(Vertice vInicial, Vertice vFinal) 
    {
        this.vInicial = vInicial;
        this.vFinal = vFinal;
    }
    
    ///Métodos de propósito General
    @Override
    public String toString()
    {
        return "e("+this.vInicial+";"+this.vFinal+");";
    }
    
    ///Métodos de propósito Específico

    ///Métodos para el manejo de Atributos

    public Vertice getvInicial() {
        return vInicial;
    }

    public void setvInicial(Vertice vInicial) {
        this.vInicial = vInicial;
    }

    public Vertice getvFinal() {
        return vFinal;
    }

    public void setvFinal(Vertice vFinal) {
        this.vFinal = vFinal;
    }
    
}
