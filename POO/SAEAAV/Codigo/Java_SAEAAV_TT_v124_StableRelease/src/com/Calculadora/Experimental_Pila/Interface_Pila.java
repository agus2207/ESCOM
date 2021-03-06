package com.Calculadora.Experimental_Pila;

public interface Interface_Pila 
{
    // CONSTRUCCIÓN: sin ningun valor de inicialización
    // **************OPERACIONES PÚBLICAS******************
    // void apilar( X )         -->Inserta x
    // void desapilar( )        -->Elimina el último elemento desapilado
    // Object cima( )           -->Devuelve el último elemento insertado
    // Object cimaYDesapilar( ) -->Devuelve, y elimina, el elemento más reciente
    // boolean esVacia( )       -->Elimina todos los elementos
    // void vaciar( )           -->Elimina todos los elementos
    // **************ERRORES*******************************
    // desapilar, cima y cimaYDesapilar sobre una pila vacía
    // **************NOTAS*********************************
    
    public void apilar(Object X);
    public void desapilar();
    public Object cima();
    public Object cimaYDesapilar();
    public boolean esVacia();
    public void vaciar();
    
}
