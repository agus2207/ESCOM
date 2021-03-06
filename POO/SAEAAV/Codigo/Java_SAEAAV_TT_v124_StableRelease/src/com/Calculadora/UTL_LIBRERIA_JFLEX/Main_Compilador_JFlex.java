///Paquete de Trabajo
package com.Calculadora.UTL_LIBRERIA_JFLEX;

///Librerias
import java.io.File;

///Esta clase compila los archivos *.flex y los transforma en *.java 
public class Main_Compilador_JFlex 
{
    ///Atributos
    private String path = "/home/checo_checho/NetBeansProjects/Java_SAEAAV_TT_Modulo_EvaluadorDeExpresiones_v10/src/com/Calculadora/Flex/AnalizadorLexico_v05_Optima.flex";
    
    ///Constructores
    public Main_Compilador_JFlex()
    {
    }
    public Main_Compilador_JFlex(String path) 
    {
        this.path=path;
    }
    
    ///Métodos de propósito específico
    public void Principal()
    {
        //Invocamos a la generación de la gramática
        generarLexer(path);
    }
    private void generarLexer(String path)
    {
        //Cargamos el archivo
        File file=new File(path);
        
        //Finalmente lo generamos
        jflex.Main.generate(file);
    }
    
    ///Main
    public static void main(String[] args) 
    {
        //Creamos objeto
        Main_Compilador_JFlex objMain_Compilador_JFlex=new Main_Compilador_JFlex();
        
        //Generamos la gramática
        objMain_Compilador_JFlex.Principal();
    }
    
}
