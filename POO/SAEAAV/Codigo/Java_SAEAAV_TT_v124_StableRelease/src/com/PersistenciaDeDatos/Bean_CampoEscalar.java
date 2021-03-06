///Paquete de Trabajo
package com.PersistenciaDeDatos;

///Librerias

public class Bean_CampoEscalar
{
    ///Atributos
    private String contenido;
    Persistencia_UTL_Conversiones objUtileria_Conversiones=new Persistencia_UTL_Conversiones();
    private String ecuacionCampoEscalar;
    private double numeroDeDivisiones=0.1;//incremento
    private double rango=1.0;//ladoDeCuadrado,ladoDeCubo
    private double escala=1;
    private String modoVisualizacion="";//2D ó 3D
    private Integer plano=0;//{0,1,2,3}
    
    ///Constructores
    public Bean_CampoEscalar()
    {
        //Predeterminado
    }
    
    ///Métodos Públicos
    @Override
    public String toString()
    {
        //Armamos la representación de texto del campo
        this.contenido=this.ecuacionCampoEscalar;
        
        //Ahora la retornamos
        return this.contenido;
    }
    @Deprecated
    public void armarContenido()
    {
        // Variables
        String texto;
        
        //Armamos la representación de texto del campo
        texto=this.ecuacionCampoEscalar;
        
        //Fijamos valor
        this.contenido=texto;
    }
    @Deprecated
    public void obtenerContenido(String contenido)
    {
        //Realizamos conversión
        
        //Obtenemos datos de interés
        this.ecuacionCampoEscalar=contenido;
    }
    public void cargarDatos(String contenido)
    {
        // Variables
        String txt;
        
        //Obtenemos datos de interés
        this.ecuacionCampoEscalar=objUtileria_Conversiones.ObtenerDivisionEspecificadaDeLaCadenaPorLaCifra(1, contenido, ",");
        txt=objUtileria_Conversiones.ObtenerDivisionEspecificadaDeLaCadenaPorLaCifra(2, contenido, ",");
        this.numeroDeDivisiones=Double.parseDouble(txt);
        txt=objUtileria_Conversiones.ObtenerDivisionEspecificadaDeLaCadenaPorLaCifra(3, contenido, ",");
        this.rango=Double.parseDouble(txt);
        txt=objUtileria_Conversiones.ObtenerDivisionEspecificadaDeLaCadenaPorLaCifra(4, contenido, ",");
        this.escala=Double.parseDouble(txt);
        this.modoVisualizacion=objUtileria_Conversiones.ObtenerDivisionEspecificadaDeLaCadenaPorLaCifra(5, contenido, ",");
        txt=objUtileria_Conversiones.ObtenerDivisionEspecificadaDeLaCadenaPorLaCifra(6, contenido, ",");
        this.plano=Integer.parseInt(txt);
    }
    public void guardarDatos()
    {
        // Variables
        String texto="";
        
        //Armamos la representación de texto del campo
        texto+=ecuacionCampoEscalar;
        texto+=","+this.numeroDeDivisiones;
        texto+=","+this.rango;
        texto+=","+this.escala;
        texto+=","+this.modoVisualizacion;
        texto+=","+this.plano;
        
        //Fijamos valor
        this.contenido=texto;
    }
    
    ///Métodos Privados
    
    ///Métodos para el manejo de Atributos

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getEcuacionCampoEscalar() {
        return ecuacionCampoEscalar;
    }

    public void setEcuacionCampoEscalar(String ecuacionCampoEscalar) {
        this.ecuacionCampoEscalar = ecuacionCampoEscalar;
    }

    public double getNumeroDeDivisiones() {
        return numeroDeDivisiones;
    }

    public void setNumeroDeDivisiones(double numeroDeDivisiones) {
        this.numeroDeDivisiones = numeroDeDivisiones;
    }

    public double getRango() {
        return rango;
    }

    public void setRango(double rango) {
        this.rango = rango;
    }

    public double getEscala() {
        return escala;
    }

    public void setEscala(double escala) {
        this.escala = escala;
    }

    public String getModoVisualizacion() {
        return modoVisualizacion;
    }

    public void setModoVisualizacion(String modoVisualizacion) {
        this.modoVisualizacion = modoVisualizacion;
    }

    public Integer getPlano() {
        return plano;
    }

    public void setPlano(Integer plano) {
        this.plano = plano;
    }
    
}
