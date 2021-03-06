///Paquete de Trabajo
package com.Calculadora.LogicaDeNegocios;

import java.util.ArrayList;


public interface Interface_LogicaDeNegocios 
{
    public void calcularResultados(String pathArchivo);
    public void procesarContenido(String contenido);
    public String obtenerResultadosYaProcesados();
    public void procesarContenido_ContemplarUsoDeVariablesXYZ(String contenido, double valorX, double valorY, double valorZ);
    public ArrayList<String> obtenerResultados();
}
