///Paquete de Trabajo
package com.PersistenciaDeDatos;

///Librerias

public class Bean_CampoVectorial
{
    ///Atributos
    private String contenido;
    Persistencia_UTL_Conversiones objUtileria_Conversiones=new Persistencia_UTL_Conversiones();
    // F(x, y, z) = M(x, y, z)i + N(x, y, z)j + P(x, y, z)k
    private String parametrica_M_xyz;
    private String parametrica_N_xyz;
    private String parametrica_P_xyz;
    private Double magnitudDeCadaVector=null;
    private double numeroDeDivisiones=0.1;//incremento
    private double rango=1.0;//ladoDeCuadrado,ladoDeCubo
    private double escala=1;
    private String modoVisualizacion="";//2D ó 3D
    private Integer plano=0;//{0,1,2,3}
    
    ///Constructores
    public Bean_CampoVectorial()
    {
    }
    
    ///Métodos Públicos
    @Override
    public String toString()
    {
        //Armamos la representación de texto del campo
        this.contenido=parametrica_M_xyz+","+parametrica_N_xyz+","+parametrica_P_xyz;
        
        //Ahora la retornamos
        return this.contenido;
    }
    @Deprecated
    public void armarContenido()
    {
        // Variables
        String texto;
        
        //Armamos la representación de texto del campo
        texto=parametrica_M_xyz+","+parametrica_N_xyz+","+parametrica_P_xyz;
        
        //Fijamos valor
        this.contenido=texto;
    }
    @Deprecated
    public void obtenerContenido(String contenido)
    {
        // Variables
        String txt;
        
        //Obtenemos datos de interés
        this.parametrica_M_xyz=objUtileria_Conversiones.ObtenerDivisionEspecificadaDeLaCadenaPorLaCifra(1, contenido, ",");
        this.parametrica_N_xyz=objUtileria_Conversiones.ObtenerDivisionEspecificadaDeLaCadenaPorLaCifra(2, contenido, ",");
        this.parametrica_P_xyz=objUtileria_Conversiones.ObtenerDivisionEspecificadaDeLaCadenaPorLaCifra(3, contenido, ",");
        txt=objUtileria_Conversiones.ObtenerDivisionEspecificadaDeLaCadenaPorLaCifra(4, contenido, ",");
        this.magnitudDeCadaVector=Double.parseDouble(txt);
        txt=objUtileria_Conversiones.ObtenerDivisionEspecificadaDeLaCadenaPorLaCifra(5, contenido, ",");
        this.numeroDeDivisiones=Double.parseDouble(txt);
        txt=objUtileria_Conversiones.ObtenerDivisionEspecificadaDeLaCadenaPorLaCifra(6, contenido, ",");
        this.rango=Double.parseDouble(txt);
        txt=objUtileria_Conversiones.ObtenerDivisionEspecificadaDeLaCadenaPorLaCifra(7, contenido, ",");
        this.escala=Double.parseDouble(txt);
        this.modoVisualizacion=objUtileria_Conversiones.ObtenerDivisionEspecificadaDeLaCadenaPorLaCifra(8, contenido, ",");
        txt=objUtileria_Conversiones.ObtenerDivisionEspecificadaDeLaCadenaPorLaCifra(9, contenido, ",");
        this.plano=Integer.parseInt(txt);
    }
    public void cargarDatos(String contenido)
    {
        // Variables
        String txt;
        
        //Obtenemos datos de interés
        this.parametrica_M_xyz=objUtileria_Conversiones.ObtenerDivisionEspecificadaDeLaCadenaPorLaCifra(1, contenido, ",");
        this.parametrica_N_xyz=objUtileria_Conversiones.ObtenerDivisionEspecificadaDeLaCadenaPorLaCifra(2, contenido, ",");
        this.parametrica_P_xyz=objUtileria_Conversiones.ObtenerDivisionEspecificadaDeLaCadenaPorLaCifra(3, contenido, ",");
        txt=objUtileria_Conversiones.ObtenerDivisionEspecificadaDeLaCadenaPorLaCifra(4, contenido, ",");
        this.magnitudDeCadaVector=Double.parseDouble(txt);
        txt=objUtileria_Conversiones.ObtenerDivisionEspecificadaDeLaCadenaPorLaCifra(5, contenido, ",");
        this.numeroDeDivisiones=Double.parseDouble(txt);
        txt=objUtileria_Conversiones.ObtenerDivisionEspecificadaDeLaCadenaPorLaCifra(6, contenido, ",");
        this.rango=Double.parseDouble(txt);
        txt=objUtileria_Conversiones.ObtenerDivisionEspecificadaDeLaCadenaPorLaCifra(7, contenido, ",");
        this.escala=Double.parseDouble(txt);
        this.modoVisualizacion=objUtileria_Conversiones.ObtenerDivisionEspecificadaDeLaCadenaPorLaCifra(8, contenido, ",");
        txt=objUtileria_Conversiones.ObtenerDivisionEspecificadaDeLaCadenaPorLaCifra(9, contenido, ",");
        this.plano=Integer.parseInt(txt);
    }
    public void guardarDatos()
    {
        // Variables
        String texto="";
        
        //Armamos la representación de texto del campo
        texto+=parametrica_M_xyz+","+parametrica_N_xyz+","+parametrica_P_xyz;
        texto+=","+this.magnitudDeCadaVector;
        texto+=","+this.numeroDeDivisiones;
        texto+=","+this.rango;
        texto+=","+this.escala;
        texto+=","+this.modoVisualizacion;
        texto+=","+this.plano;
        
        System.out.println("texto="+texto);
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

    public Double getMagnitudDeCadaVector() {
        return magnitudDeCadaVector;
    }

    public void setMagnitudDeCadaVector(Double magnitudDeCadaVector) {
        this.magnitudDeCadaVector = magnitudDeCadaVector;
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
