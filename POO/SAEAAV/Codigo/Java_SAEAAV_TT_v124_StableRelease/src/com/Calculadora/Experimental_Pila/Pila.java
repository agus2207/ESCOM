///Paquete de Trabajo
package com.Calculadora.Experimental_Pila;

///Librerias
import java.util.Stack;

public class Pila implements Interface_Pila
{
    ///Atributos
    private Stack<Object> Pila;
    
    ///Constructores
    public Pila()
    {
        // Inicializamos la pila
        this.inicializarPila();
    }
    
    ///Métodos de propósito Específico
    private void inicializarPila()
    {
        //Reiniciarla, significa eleminar lo que tengamos y volverla a
        //su estado vacío, por lo que simplemente rompemos la referencia
        //y la recreamos
        this.Pila= new Stack<Object>();
    }
    
    ///Métodos de propósito General
    @Override
    public String toString()
    {
        return this.mostrarContenidoDeLaPilaFormatoEstandar();
    }
    @Override
    public void apilar(Object X)
    {
        this.push(X);
    }
    @Override
    public void desapilar() 
    {
        this.pop();
    }
    @Override
    public Object cima()
    {
        return this.observarElElementoDeLaCimaSinRemoverlo();
    }
    @Override
    public Object cimaYDesapilar() 
    {
        return this.pop();
    }
    @Override
    public boolean esVacia()
    {
        return this.laPilaEstaVacia();
    }
    @Override
    public void vaciar()
    {
        this.Pila.clear();
    }
    
    ///Métodos de propósito Específico
    
    ///Métodos propios de la pila
    private void push(Object elemento)
    {
        //Si la pila está inicializada..
        if(this.Pila!=null)
        {
            //Simplemente accedemos a la pila y agregamos el elemento
            this.Pila.add(elemento);
        }
        else
        {
            //Inicializamos la pila
            this.Pila= new Stack<Object>();
            
            //Y ahora sí insertamos el nuevo elemento en el tope
            this.Pila.push(elemento);
        }
    }
    private Object pop()
    {
        //Declaración de variables
        Object simboloAExtraer=null;
        
        //Si la pila no está vacía..
        if(!this.laPilaEstaVacia())
        {
            //Simplemente accedemos a la pila y quitamos el elemento de la cima
            simboloAExtraer=this.Pila.pop();
        }
        
        //Retornamos el resultado
        return simboloAExtraer;
    }
    private Object observarElElementoDeLaCimaSinRemoverlo()
    {
        //Declaración de variables
        Object simboloDeLaCima=null;
        
        //Si la pila no está vacía..
        if(!this.laPilaEstaVacia())
        {
            simboloDeLaCima=this.Pila.peek();
        }
        
        //Finalmente retornamos el resultado
        return simboloDeLaCima;
    }
    private Object observarElElementoIndicadoPorElIndice(int indice)
    {
        //Declaración de variables
        Object simboloAObservar=null;
        
        //Si la pila no está vacía..
        if( (!this.laPilaEstaVacia()) && (0<=indice && indice<this.Pila.size()))
        {
            //Aqui obtenemos el elemento como tal
            simboloAObservar=this.Pila.get(indice);
        }
        
        //Finalmente retornamos el resultado
        return simboloAObservar;
    }
    private boolean laPilaEstaVacia()
    {
        //Declaración de variables
        boolean estaVacia=false;
        
        //Simplemente verificamos con el método propio
        estaVacia=this.Pila.empty();
        
        //Finalmente retornamos el resultado
        return estaVacia;
    }
    private String mostrarContenidoDeLaPilaFormatoEstandar()
    {
        //Declaración de variables
        String contenido="";
        Object aux=null;
        String auxString="";
        boolean esInicio=true;
        
        //Iremos viendo uno a uno los símbolos contenidos
        for(int k=0; k<this.Pila.size();k++)
        {
            //Por lo que iremos buscando cada estado
            aux=this.observarElElementoIndicadoPorElIndice(k);
            
            //Realizamos una conversión forzada
            auxString=aux.toString();
            
            //Y adjuntandolo al resultado con un cierto formato
            if(!esInicio)
            {
                contenido+=" |- "+auxString;
            }
            if(esInicio)
            {
                contenido+=" Pila= "+auxString;
                esInicio=false;
            }   
        }
        
        //Finalmente retornamos el resultado obtenido
        if(!this.laPilaEstaVacia())
            return contenido;
        else if(this.laPilaEstaVacia())
            return contenido="La pila está vacía!!";
        
        return contenido;
    }
    
    ///Main Test OK2
//    public static void main(String[] args)
//    {
//        Pila objPila=new Pila();
//        String msg;
//        for(int k=0; k<10; k++)
//        {
//            msg=new String(""+k);
//            objPila.apilar(msg);
//        }
//        
//        System.out.println("Pila="+objPila);
//        Object pop = objPila.cimaYDesapilar();
//        System.out.println("pop="+pop.toString());
//    }
}
