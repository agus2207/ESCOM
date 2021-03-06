///Paquete de Trabajo
package com.SAEAAV.Modelo.Matematicas;

///Librerias
import com.PersistenciaDeDatos.Bean_CampoEscalar;
import javax.swing.JOptionPane;

public class Datos_CampoEscalar
{
    ///Atributos
    private String ecuacionCampoEscalar="";//((x^2)/(1^2))+((y^2)/(2^2))-1
    private double incremento=0.01;//0.01
    private Integer plano=null;//Constantes_Matematicas.PLANO_XY;
    private boolean esCampoEscalar3D=true;//2D ó 3D
    private double ladoDeCuadrado=1;
    private double ladoDeCubo=1;
    private double escala=1;
    // Interpolación
    private Double interpolacionValorMaximo=null;
    private Double interpolacionValorMínimo=null;
    // Especializados
    private boolean usaConstanteEspecifica=false;
    private double valorConstanteEspecifica=0.0;
    
    ///Constructores
    public Datos_CampoEscalar()
    {
    }

    ///Métodos de propósito General
    public void inicializarDatos_GUIAutonoma() 
    {        
        //Inicializamos datos extras
        String txt="";
        txt = JOptionPane.showInputDialog("Inserte Ecuación:", "x^2+y^2");
        this.ecuacionCampoEscalar=txt;
        txt="";
        txt = JOptionPane.showInputDialog("Inserte Incremento:", "0.1");
        this.incremento=Double.parseDouble(txt);
        
        txt = JOptionPane.showInputDialog("Inserte la escala", "1");
        this.escala=Double.parseDouble(txt);
    }
    public String obtenerContenido()
    {
        // Variables
        String texto;
        
        //Armamos la representación de texto del campo
        texto=this.ecuacionCampoEscalar;
        
        //Retornamos resultado 
        return texto;
    }
    public void inicializarDatos_UsandoContenidoDeArchivo(String contenido)
    {
        //Realizamos conversión
        
        //Obtenemos datos de interés
        this.ecuacionCampoEscalar=contenido;
    }
    @Override
    public String toString()
    {
        // Variables
        String contenido;
        
        //Armamos la representación de texto del campo
        contenido=this.ecuacionCampoEscalar;
        
        //Ahora la retornamos
        return contenido;
    }
    public Bean_CampoEscalar guardarDatosDeCampo()
    {
        // Variables
        Bean_CampoEscalar datosDeCampoEnArchivo;
        
        // Inicializamos
        datosDeCampoEnArchivo=new Bean_CampoEscalar();
        
        // Insertamos datos
        datosDeCampoEnArchivo.setEcuacionCampoEscalar(ecuacionCampoEscalar);
        datosDeCampoEnArchivo.setNumeroDeDivisiones(incremento);
        
        if(this.esCampoEscalar3D)
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
        Bean_CampoEscalar datosDeCampoEnArchivo;
        
        // Inicializamos
        datosDeCampoEnArchivo=new Bean_CampoEscalar();
        
        // Cargamos datos
        datosDeCampoEnArchivo.cargarDatos(contenido);
        
        // Obtenemos datos
        this.ecuacionCampoEscalar=datosDeCampoEnArchivo.getEcuacionCampoEscalar();
        this.incremento=datosDeCampoEnArchivo.getNumeroDeDivisiones();
        
        if(datosDeCampoEnArchivo.getModoVisualizacion().equals("3D"))
        {
            this.ladoDeCubo=datosDeCampoEnArchivo.getRango();
            this.escala=datosDeCampoEnArchivo.getEscala();
            this.esCampoEscalar3D=true;
            this.plano=datosDeCampoEnArchivo.getPlano();
        }
        else
        {
            this.ladoDeCuadrado=datosDeCampoEnArchivo.getRango();
            this.escala=datosDeCampoEnArchivo.getEscala();
            this.esCampoEscalar3D=false;
            this.plano=datosDeCampoEnArchivo.getPlano();
        }
    }
    
    ///Métodos de propósito Específico
    
    ///Métodos para el manejo de Atributos

    public String getEcuacionCampoEscalar() {
        return ecuacionCampoEscalar;
    }

    public void setEcuacionCampoEscalar(String ecuacionCampoEscalar) {
        this.ecuacionCampoEscalar = ecuacionCampoEscalar;
    }

    public double getIncremento() {
        return incremento;
    }

    public void setIncremento(double incremento) {
        this.incremento = incremento;
    }

    public Integer getPlano() {
        return plano;
    }

    public void setPlano(Integer plano) {
        this.plano = plano;
    }

    public boolean isEsCampoEscalar3D() {
        return esCampoEscalar3D;
    }

    public void setEsCampoEscalar3D(boolean esCampoEscalar3D) {
        this.esCampoEscalar3D = esCampoEscalar3D;
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

    public boolean isUsaConstanteEspecifica() {
        return usaConstanteEspecifica;
    }

    public void setUsaConstanteEspecifica(boolean usaConstanteEspecifica) {
        this.usaConstanteEspecifica = usaConstanteEspecifica;
    }

    public double getValorConstanteEspecifica() {
        return valorConstanteEspecifica;
    }

    public void setValorConstanteEspecifica(double valorConstanteEspecifica) {
        this.valorConstanteEspecifica = valorConstanteEspecifica;
    }
    
}
