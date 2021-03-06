///Paquete de Trabajo
package com.PersistenciaDeDatos;

///Librerias

public class Bean_OBEV
{
    ///Atributos
    private Double v1x;
    private Double v1y;
    private Double v1z;
    private Double v2x;
    private Double v2y;
    private Double v2z;
    private Double k;
    private Integer operacion;
    Persistencia_UTL_Conversiones objUtileria_Conversiones=new Persistencia_UTL_Conversiones();
    private String contenido;
    
    ///Constructores
    public Bean_OBEV()
    {
        
    }
     
    ///Métodos de propósito General
    public void cargarDatos(String contenido)
    {
        // Variables
        String txt;
        
        //Obtenemos datos de interés
        txt=objUtileria_Conversiones.ObtenerDivisionEspecificadaDeLaCadenaPorLaCifra(1, contenido, ",");
        this.v1x=Double.parseDouble(txt);
        txt=objUtileria_Conversiones.ObtenerDivisionEspecificadaDeLaCadenaPorLaCifra(2, contenido, ",");
        this.v1y=Double.parseDouble(txt);
        txt=objUtileria_Conversiones.ObtenerDivisionEspecificadaDeLaCadenaPorLaCifra(3, contenido, ",");
        this.v1z=Double.parseDouble(txt);
        txt=objUtileria_Conversiones.ObtenerDivisionEspecificadaDeLaCadenaPorLaCifra(4, contenido, ",");
        this.v2x=Double.parseDouble(txt);
        txt=objUtileria_Conversiones.ObtenerDivisionEspecificadaDeLaCadenaPorLaCifra(5, contenido, ",");
        this.v2y=Double.parseDouble(txt);
        txt=objUtileria_Conversiones.ObtenerDivisionEspecificadaDeLaCadenaPorLaCifra(6, contenido, ",");
        this.v2z=Double.parseDouble(txt);
        txt=objUtileria_Conversiones.ObtenerDivisionEspecificadaDeLaCadenaPorLaCifra(7, contenido, ",");
        this.k=Double.parseDouble(txt);
    }
    public void guardarDatos()
    {
        // Variables
        String texto="";
        
        //Armamos la representación de texto del campo
        texto+=""+this.v1x;
        texto+=","+this.v1y;
        texto+=","+this.v1z;
        texto+=","+this.v2x;
        texto+=","+this.v2y;
        texto+=","+this.v2z;
        texto+=","+this.k;
        
        //Fijamos valor
        this.contenido=texto;
    }
    
    ///Métodos de propósito Específico

    ///Métodos para el manejo de Atributos

    public Double getV1x() {
        return v1x;
    }

    public void setV1x(Double v1x) {
        this.v1x = v1x;
    }

    public Double getV1y() {
        return v1y;
    }

    public void setV1y(Double v1y) {
        this.v1y = v1y;
    }

    public Double getV1z() {
        return v1z;
    }

    public void setV1z(Double v1z) {
        this.v1z = v1z;
    }

    public Double getV2x() {
        return v2x;
    }

    public void setV2x(Double v2x) {
        this.v2x = v2x;
    }

    public Double getV2y() {
        return v2y;
    }

    public void setV2y(Double v2y) {
        this.v2y = v2y;
    }

    public Double getV2z() {
        return v2z;
    }

    public void setV2z(Double v2z) {
        this.v2z = v2z;
    }

    public Double getK() {
        return k;
    }

    public void setK(Double k) {
        this.k = k;
    }

    public Integer getOperacion() {
        return operacion;
    }

    public void setOperacion(Integer operacion) {
        this.operacion = operacion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

}
