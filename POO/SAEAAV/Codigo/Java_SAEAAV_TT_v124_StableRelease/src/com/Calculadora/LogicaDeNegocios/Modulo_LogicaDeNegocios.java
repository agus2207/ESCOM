///Paquete de Trabajo
package com.Calculadora.LogicaDeNegocios;

///Librerias

import java.util.ArrayList;


public class Modulo_LogicaDeNegocios implements Interface_LogicaDeNegocios
{
    ///Atributos
    private parser objparser=null;
    private String resultados="";
    private ArrayList<String> resultadosLista=new ArrayList<>();
    
    ///Constructores
    public Modulo_LogicaDeNegocios() 
    {
    }
    
    ///Métodos de propósito general
    @Override
    public void calcularResultados(String pathArchivo) 
    {
        try
        {
            //Limpiamos
            this.resultados="";
            this.resultadosLista.clear();
            
            //Procesamos
            this.objparser = parser.Principal(pathArchivo);
        }
        catch(Exception ex)
        {
            System.out.println("***********************************************");
            ex.printStackTrace();
            System.out.println("***********************************************");
        }
    }
    @Override
    public void procesarContenido(String contenido) 
    {
        try
        {
            //Limpiamos
            this.resultados="";
            this.resultadosLista.clear();
            
            //Procesamos
            this.objparser = parser.PrincipalRecibeContenidoDirecto(contenido);
        }
        catch(Exception ex)
        {
            System.out.println("***********************************************");
            ex.printStackTrace();
            System.out.println("***********************************************");
        }
    }
    @Override
    public void procesarContenido_ContemplarUsoDeVariablesXYZ(String contenido, double valorX, double valorY, double valorZ) 
    {
        try
        {
            //Limpiamos
            this.resultados="";
            this.resultadosLista.clear();
            
            //Procesamos
            this.objparser = parser.PrincipalRecibeContenidoDirecto(contenido, valorX, valorY, valorZ);
        }
        catch(Exception ex)
        {
            System.out.println("***********************************************");
            ex.printStackTrace();
            System.out.println("***********************************************");
        }
    }
    
    @Override
    public String obtenerResultadosYaProcesados() 
    {
        //Procesamos
        this.procesarResultadosPrimeraForma();
        
        //Retornamos
        return resultados;
    }
    
    ///Métodos de propósito específico
    private void log(String msg, boolean esUltimoResultado)
    {
        if(esUltimoResultado)
        {
            this.resultados+=msg;
        }
        else
        {
            this.resultados+=msg+"\n";
        }
    }
    private void procesarResultadosPrimeraForma()
    {   
        if(this.objparser.action_obj==null)
        {
            System.out.println("ERROR");
            System.exit(0);
        }
        
        for(int k=0; k<this.objparser.action_obj.getListaDeValores().size(); k++)
        {
            String msg=this.objparser.action_obj.getListaDeValores().get(k);

            if(this.objparser.action_obj.getListaDeTiposDeValores_esError().get(k))
            {
                log(""+msg,false);
            }
            else
            {
                if(k<(this.objparser.action_obj.getListaDeValores().size()-1))
                {
                    log(""+msg,false);
                }
                else
                {
                    log(""+msg,true);
                }
            }
        }
        
        //Limpiamos
        this.objparser.action_obj.getListaDeTiposDeValores_esError().clear();
        this.objparser.action_obj.getListaDeValores().clear();
    }
    @Override
    public ArrayList<String> obtenerResultados() 
    {
        //Procesamos
        this.procesarResultadosSegundaForma();
        
        //Retornamos
        return resultadosLista;
    }
    private void procesarResultadosSegundaForma()
    {
        for(int k=0; k<this.objparser.action_obj.getListaDeValores().size(); k++)
        {
            String msg=this.objparser.action_obj.getListaDeValores().get(k);
            this.resultadosLista.add(msg);
        }
    }
    
    ///Métodos para el manejo de atributos

}
