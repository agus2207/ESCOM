///Paquete de Trabajo
package com.SAEAAV.Modelo.Graficador.Interpolaciones;

///Librerias
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class InterpolacionColoresJet
{
    ///Atributos
    private ArrayList<ColorFormatoRGB> coloresJET=new ArrayList<ColorFormatoRGB>();
    private int indiceDeColorMinimo;
    private int indiceDeColorMaximo;
    private double valorMaximo=0.0;
    private double valorMinimo=0.0;
    
    ///Constructores
    public InterpolacionColoresJet()
    {
        this.inicializar();
    }
    
    ///Métodos de propósito general
    public ColorFormatoRGB realizarInterpolacion(double valorAInterpolar)
    {
        // Variables
        double y, iCMax, iCMin, max, min, x;
        ColorFormatoRGB interpolacionColorJet;
        int indiceColor;
        
        // Inicializamos
        iCMax=this.indiceDeColorMaximo;
        iCMin=this.indiceDeColorMinimo;
        max=this.valorMaximo;
        min=this.valorMinimo;
        x=valorAInterpolar;
        
        // Calculamos
        y=((iCMax-iCMin)/(max-min))*(x-min);
        
        // Ajustamos
        indiceColor=(int) Math.floor(y);
        
        // Prevenimos indice fuera de rango
        if(this.coloresJET.size()<=indiceColor)indiceColor=this.coloresJET.size()-1;
        if(indiceColor<0)indiceColor=0;
        
        // Obtenemos el color correspondiente
        interpolacionColorJet=this.coloresJET.get(indiceColor);
        
        // Retornamos resultado
        return interpolacionColorJet;
    }
    
    ///Métodos de propósito específico
    private void inicializar()
    {
        BufferedReader in;
        Scanner scanner;
        String linea;
        String nextAux;
        float valor;
        ColorFormatoRGB nuevoColorJet;
        
        try
        {
            in = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("ColoresJET.txt")));   
            while((linea=in.readLine())!=null)
            {
                // Instanciamos nuevo color
                nuevoColorJet=new ColorFormatoRGB();
                
                // Abstraemos la línea
                scanner = new Scanner(linea);
                
                // Rojo
                nextAux = scanner.next();
                valor=Float.parseFloat(nextAux);
                nuevoColorJet.setR_ColorRojo(valor);
                
                // Verde
                nextAux = scanner.next();
                valor=Float.parseFloat(nextAux);
                nuevoColorJet.setG_ColorVerde(valor);
                
                // Azul
                nextAux = scanner.next();
                valor=Float.parseFloat(nextAux);
                nuevoColorJet.setB_ColorAzul(valor);

                // Insertamos nuevo color
                this.coloresJET.add(nuevoColorJet);
            }
            
            // Fijamos valores
            this.indiceDeColorMaximo=this.coloresJET.size();
            this.indiceDeColorMinimo=0;
            
            // Cerramos flujo de datos
            in.close();
        }
        catch(Exception ex)
        {
            System.out.println("Exception::InterpolacionColoresJet::"+ex.getMessage());
        }
    }
    
    ///Métodos para el manejo de atributos
    public ArrayList<ColorFormatoRGB> getColoresJET()
    {
        return coloresJET;
    }
    public void setValorMaximo(double valorMaximo)
    {
        this.valorMaximo = valorMaximo;
    }

    public void setValorMinimo(double valorMinimo)
    {
        this.valorMinimo = valorMinimo;
    }
    
    ///Tester
    //    public static void main(String[] args) 
    //    {
    //        InterpolacionColoresJet objLecturaJet=new InterpolacionColoresJet();
    //        objLecturaJet.setValorMaximo(30);
    //        objLecturaJet.setValorMinimo(15);
    //        double valorAInterpolar=23;
    //        ColorFormatoRGB realizarInterpolacion = objLecturaJet.realizarInterpolacion(valorAInterpolar);
    //        System.out.println("interpolar="+realizarInterpolacion);
    //        ArrayList<ColorFormatoRGB> coloresJET1 = objLecturaJet.getColoresJET();
    //        for(int k=0; k<coloresJET1.size(); k++)
    //        {
    //            System.out.println("coloresJET #"+k+"="+coloresJET1.get(k));
    //        }
    //    }
    
}
