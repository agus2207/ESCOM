package com.Calculadora;

import com.Calculadora.LogicaDeNegocios.Modulo_LogicaDeNegocios;

public class MODULO_Calculadora_Principal 
{
    private Modulo_LogicaDeNegocios objModulo_LogicaDeNegocios=new Modulo_LogicaDeNegocios();
    
    public double Principal(String ecuacion, double varX, double varY, double varZ)
    {
        //Variables
        double resultado;
        
        //Leemos los datos del campo
        ecuacion+=";";
        
        //Procesamos
        objModulo_LogicaDeNegocios.procesarContenido_ContemplarUsoDeVariablesXYZ(ecuacion, varX, varY, varZ);
        
        //Obtenemos resultados
        resultado=Double.parseDouble(objModulo_LogicaDeNegocios.obtenerResultadosYaProcesados());
        
        //Retornamos resultado
        return resultado;
    }
    
    public static void main(String[] args) 
    {
        //Variables
        MODULO_Calculadora_Principal objCalculadora_MainEjemplo=new MODULO_Calculadora_Principal();
        double resultado;
        
        //Calculamos
        resultado = objCalculadora_MainEjemplo.Principal("((x^2)/(1^2))+((y^2)/(2^2))-1",7,2,3);
        
        //Procesamos
        System.out.println("->resultado="+resultado);
    }
}
