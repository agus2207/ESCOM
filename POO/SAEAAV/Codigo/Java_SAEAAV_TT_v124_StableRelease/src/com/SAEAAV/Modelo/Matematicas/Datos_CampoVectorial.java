///Paquete de Trabajo
package com.SAEAAV.Modelo.Matematicas;

///Librerias
import com.PersistenciaDeDatos.Bean_CampoVectorial;
import javax.swing.JOptionPane;

public class Datos_CampoVectorial
{
    ///Atributos
    private double incremento=0.1;
    private boolean esCampoVectorial3D=true;//2D ó 3D
    private double ladoDeCuadrado=1.0;
    private double ladoDeCubo=1.0; 
    private Integer plano=null;
    private Double magnitudDeCadaVector=null;
    private double escala=1;
    // F(x, y, z) = M(x, y, z)i + N(x, y, z)j + P(x, y, z)k
    private String parametrica_M_xyz;
    private String parametrica_N_xyz;
    private String parametrica_P_xyz;
    // Interpolación
    private Double interpolacionValorMaximo=null;
    private Double interpolacionValorMínimo=null;
    
    ///Constructores
    public Datos_CampoVectorial()
    {
        
    }
    
    ///Métodos de propósito General
    public void inicializarDatos_GUIAutonoma()
    {
        //Inicializamos datos extras
        String txt="";
        JOptionPane.showMessageDialog(null, "Un campo vectorial: \n F(x, y, z) = M(x, y, z)i + N(x, y, z)j + P(x, y, z)k");
        txt = JOptionPane.showInputDialog("Inserte Ecuación: \n"+"M(x, y, z)i=", "x/sqrt(x^2+y^2+z^2)");
        this.parametrica_M_xyz=txt;
        txt="";
        txt = JOptionPane.showInputDialog("Inserte Ecuación: \n"+"N(x, y, z)j=", "y/sqrt(x^2+y^2+z^2)");
        this.parametrica_N_xyz=txt;
        txt="";
        txt = JOptionPane.showInputDialog("Inserte Ecuación: \n"+"P(x, y, z)k", "z/sqrt(x^2+y^2+z^2)");
        this.parametrica_P_xyz=txt;
        txt="";
        txt = JOptionPane.showInputDialog("Inserte Incremento:", "0.1");
        this.incremento=Double.parseDouble(txt);
        txt = JOptionPane.showInputDialog("Inserte la magnitud para cada vector:\n(menor que el incremento no sale de los marcos)", "0.1");
        this.magnitudDeCadaVector=Double.parseDouble(txt);
        txt = JOptionPane.showInputDialog("Inserte la escala", "1");
        this.escala=Double.parseDouble(txt);
    }
    public Bean_CampoVectorial guardarDatosDeCampo()
    {
        // Variables
        Bean_CampoVectorial datosDeCampoEnArchivo;
        
        // Inicializamos
        datosDeCampoEnArchivo=new Bean_CampoVectorial();
        
        // Insertamos datos
        datosDeCampoEnArchivo.setParametrica_M_xyz(parametrica_M_xyz);
        datosDeCampoEnArchivo.setParametrica_N_xyz(parametrica_N_xyz);
        datosDeCampoEnArchivo.setParametrica_P_xyz(parametrica_P_xyz);
        datosDeCampoEnArchivo.setMagnitudDeCadaVector(magnitudDeCadaVector);
        datosDeCampoEnArchivo.setNumeroDeDivisiones(incremento);
        
        if(this.esCampoVectorial3D)
        {
            datosDeCampoEnArchivo.setRango(this.ladoDeCubo);
            datosDeCampoEnArchivo.setRango(this.ladoDeCuadrado);
            datosDeCampoEnArchivo.setEscala(escala);
            datosDeCampoEnArchivo.setModoVisualizacion("3D");
            datosDeCampoEnArchivo.setPlano(0);
        }
        else
        {
            datosDeCampoEnArchivo.setRango(this.ladoDeCubo);
            datosDeCampoEnArchivo.setRango(this.ladoDeCuadrado);
            datosDeCampoEnArchivo.setEscala(escala);
            datosDeCampoEnArchivo.setModoVisualizacion("2D");
            datosDeCampoEnArchivo.setPlano(this.plano);
        }
        
        // Ajustamos datos
        datosDeCampoEnArchivo.guardarDatos();
        
        // Retornamos
        return datosDeCampoEnArchivo;
    }
    public void cargarDatosDeCampo(String contenido)
    {
        // Variables
        Bean_CampoVectorial datosDeCampoEnArchivo;
        
        // Inicializamos
        datosDeCampoEnArchivo=new Bean_CampoVectorial();
        
        // Cargamos datos
        datosDeCampoEnArchivo.cargarDatos(contenido);
        
        // Obtenemos datos
        this.parametrica_M_xyz=datosDeCampoEnArchivo.getParametrica_M_xyz();
        this.parametrica_N_xyz=datosDeCampoEnArchivo.getParametrica_N_xyz();
        this.parametrica_P_xyz=datosDeCampoEnArchivo.getParametrica_P_xyz();
        this.magnitudDeCadaVector=datosDeCampoEnArchivo.getMagnitudDeCadaVector();
        this.incremento=datosDeCampoEnArchivo.getNumeroDeDivisiones();
        
        if(datosDeCampoEnArchivo.getModoVisualizacion().equals("3D"))
        {
            this.ladoDeCubo=datosDeCampoEnArchivo.getRango();
            this.escala=datosDeCampoEnArchivo.getEscala();
            this.esCampoVectorial3D=true;
            this.plano=datosDeCampoEnArchivo.getPlano();
        }
        else
        {
            this.ladoDeCuadrado=datosDeCampoEnArchivo.getRango();
            this.escala=datosDeCampoEnArchivo.getEscala();
            this.esCampoVectorial3D=false;
            this.plano=datosDeCampoEnArchivo.getPlano();
        }
    }
    
    ///Métodos de propósito Específico

    ///Métodos para el manejo de Atributos

    public double getIncremento() {
        return incremento;
    }

    public void setIncremento(double incremento) {
        this.incremento = incremento;
    }

    public boolean isEsCampoVectorial3D() {
        return esCampoVectorial3D;
    }

    public void setEsCampoVectorial3D(boolean esCampoVectorial3D) {
        this.esCampoVectorial3D = esCampoVectorial3D;
    }

    public double getLadoDeCuadrado() {
        return ladoDeCuadrado;
    }

    public void setLadoDeCuadrado(double ladoDeCuadrado) {
        this.ladoDeCuadrado = ladoDeCuadrado;
    }

    public double getLadoDeCubo() {
        return ladoDeCubo;
    }

    public void setLadoDeCubo(double ladoDeCubo) {
        this.ladoDeCubo = ladoDeCubo;
    }

    public Integer getPlano() {
        return plano;
    }

    public void setPlano(Integer plano) {
        this.plano = plano;
    }

    public Double getMagnitudDeCadaVector() {
        return magnitudDeCadaVector;
    }

    public void setMagnitudDeCadaVector(Double magnitudDeCadaVector) {
        this.magnitudDeCadaVector = magnitudDeCadaVector;
    }

    public String getParametrica_M_xyz() {
        return parametrica_M_xyz;
    }

    public void setParametrica_M_xyz(String parametrica_M_xyz) {
        this.parametrica_M_xyz = parametrica_M_xyz;
    }

    public String getParametrica_N_xyz() {
        return parametrica_N_xyz;
    }

    public void setParametrica_N_xyz(String parametrica_N_xyz) {
        this.parametrica_N_xyz = parametrica_N_xyz;
    }

    public String getParametrica_P_xyz() {
        return parametrica_P_xyz;
    }

    public void setParametrica_P_xyz(String parametrica_P_xyz) {
        this.parametrica_P_xyz = parametrica_P_xyz;
    }

    public double getEscala() {
        return escala;
    }

    public void setEscala(double escala) {
        this.escala = escala;
    }

    public Double getInterpolacionValorMaximo() {
        return interpolacionValorMaximo;
    }

    public void setInterpolacionValorMaximo(Double interpolacionValorMaximo) {
        this.interpolacionValorMaximo = interpolacionValorMaximo;
    }

    public Double getInterpolacionValorMínimo() {
        return interpolacionValorMínimo;
    }

    public void setInterpolacionValorMínimo(Double interpolacionValorMínimo) {
        this.interpolacionValorMínimo = interpolacionValorMínimo;
    }
    
}
