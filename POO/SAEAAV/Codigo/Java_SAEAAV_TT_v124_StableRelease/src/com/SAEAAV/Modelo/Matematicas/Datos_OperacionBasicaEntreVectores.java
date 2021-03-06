///Paquete de Trabajo
package com.SAEAAV.Modelo.Matematicas;

///Librerias
import com.PersistenciaDeDatos.Bean_OBEV;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Datos_OperacionBasicaEntreVectores
{
    ///Atributos
    private ArrayList<Vector> vectoresOperacion=new ArrayList<Vector>();
    private ArrayList<Vector> vectoresResultado=new ArrayList<Vector>();
    private Double escalarK=null;
    private String mensajeResultado="";
    private boolean esResultadoGrafico=false;
    private Integer operacion=null;
    public static final int OPERACION_SUMADEVECTORES=1;
    public static final int OPERACION_MULTIPLICACIONDEVECTORPORESCALAR=2;
    public static final int OPERACION_PRODUCTOCRUZDEVECTORES=3;
    public static final int OPERACION_PRODUCTOPUNTODEVECTORES=4;
    private boolean esModo3D=true;//2D ó 3D
    
    ///Constructores
    public Datos_OperacionBasicaEntreVectores()
    {
        
    }
    
    ///Métodos de propósito General
    public void inicializarDatos_GUIAutonoma()
    {
        //Variables
        String txt;
        String msg;
        double x1, y1, z1, x2, y2, z2, k;
        Vector v1, v2;
        
        // Capturamos la operación a realizar
        msg="Ingrese la Operación: \n1=Suma de Vectores.\n2=Multiplicación Vector por Escalar.\n3=Producto Cruz.\n4=Producto Escalar.";
        txt=JOptionPane.showInputDialog(msg, "1");
        this.operacion=Integer.parseInt(txt);
        
        if(this.operacion==OPERACION_SUMADEVECTORES)
        {
            txt=JOptionPane.showInputDialog("[Suma de Vectores VR=V1+V2]\nV1x=", "1.0");
            x1=Double.parseDouble(txt);
            txt=JOptionPane.showInputDialog("[Suma de Vectores VR=V1+V2]\nV1y=", "1.0");
            y1=Double.parseDouble(txt);
            txt=JOptionPane.showInputDialog("[Suma de Vectores VR=V1+V2]\nV1z=", "1.0");
            z1=Double.parseDouble(txt);
            txt=JOptionPane.showInputDialog("[Suma de Vectores VR=V1+V2]\nV2x=", "-1.0");
            x2=Double.parseDouble(txt);
            txt=JOptionPane.showInputDialog("[Suma de Vectores VR=V1+V2]\nV2y=", "-1.0");
            y2=Double.parseDouble(txt);
            txt=JOptionPane.showInputDialog("[Suma de Vectores VR=V1+V2]\nV2z=", "-1.0");
            z2=Double.parseDouble(txt);
            
            v1=new Vector(0, 0, 0, x1, y1, z1);
            v2=new Vector(0, 0, 0, x2, y2, z2);
            
            this.vectoresOperacion.add(v1);
            this.vectoresOperacion.add(v2);
        }
        if(this.operacion==OPERACION_MULTIPLICACIONDEVECTORPORESCALAR)
        {
            txt=JOptionPane.showInputDialog("[Multiplicación de Vector VR=k*V1]\nV1x=", "1.0");
            x1=Double.parseDouble(txt);
            txt=JOptionPane.showInputDialog("[Multiplicación de Vector VR=k*V1]\nV1y=", "1.0");
            y1=Double.parseDouble(txt);
            txt=JOptionPane.showInputDialog("[Multiplicación de Vector VR=k*V1]\nV1z=", "1.0");
            z1=Double.parseDouble(txt);
            txt=JOptionPane.showInputDialog("[Multiplicación de Vector VR=k*V1]\nK=", "1.0");
            k=Double.parseDouble(txt);
            v1=new Vector(0, 0, 0, x1, y1, z1);
            
            this.vectoresOperacion.add(v1);
            this.escalarK=k;
        }
        if(this.operacion==OPERACION_PRODUCTOPUNTODEVECTORES)
        {
            txt=JOptionPane.showInputDialog("[Producto Escalar VR=V1.V2]\nV1x=", "1.0");
            x1=Double.parseDouble(txt);
            txt=JOptionPane.showInputDialog("[Producto Escalar VR=V1.V2]\nV1y=", "1.0");
            y1=Double.parseDouble(txt);
            txt=JOptionPane.showInputDialog("[Producto Escalar VR=V1.V2]\nV1z=", "1.0");
            z1=Double.parseDouble(txt);
            txt=JOptionPane.showInputDialog("[Producto Escalar VR=V1.V2]\nV2x=", "-1.0");
            x2=Double.parseDouble(txt);
            txt=JOptionPane.showInputDialog("[Producto Escalar VR=V1.V2]\nV2y=", "-1.0");
            y2=Double.parseDouble(txt);
            txt=JOptionPane.showInputDialog("[Producto Escalar VR=V1.V2]\nV2z=", "-1.0");
            z2=Double.parseDouble(txt);
            
            v1=new Vector(0, 0, 0, x1, y1, z1);
            v2=new Vector(0, 0, 0, x2, y2, z2);
            
            this.vectoresOperacion.add(v1);
            this.vectoresOperacion.add(v2);
        }
        if(this.operacion==OPERACION_PRODUCTOCRUZDEVECTORES)
        {
            txt=JOptionPane.showInputDialog("[Producto Cruz VR=V1XV2]\nV1x=", "1.0");
            x1=Double.parseDouble(txt);
            txt=JOptionPane.showInputDialog("[Producto Cruz VR=V1XV2]\nV1y=", "1.0");
            y1=Double.parseDouble(txt);
            txt=JOptionPane.showInputDialog("[Producto Cruz VR=V1XV2]\nV1z=", "1.0");
            z1=Double.parseDouble(txt);
            txt=JOptionPane.showInputDialog("[Producto Cruz VR=V1XV2]\nV2x=", "-1.0");
            x2=Double.parseDouble(txt);
            txt=JOptionPane.showInputDialog("[Producto Cruz VR=V1XV2]\nV2y=", "-1.0");
            y2=Double.parseDouble(txt);
            txt=JOptionPane.showInputDialog("[Producto Cruz VR=V1XV2]\nV2z=", "-1.0");
            z2=Double.parseDouble(txt);
            
            v1=new Vector(0, 0, 0, x1, y1, z1);
            v2=new Vector(0, 0, 0, x2, y2, z2);
            
            this.vectoresOperacion.add(v1);
            this.vectoresOperacion.add(v2);
        }
    }
    public Bean_OBEV guardarDatosDeOBEV()
    {
        // Variables
        Bean_OBEV datosDeCampoEnArchivo;
        
        // Inicializamos
        datosDeCampoEnArchivo=new Bean_OBEV();
        
        // Insertamos datos
        datosDeCampoEnArchivo.setV1x(this.vectoresOperacion.get(0).getX2());
        datosDeCampoEnArchivo.setV1y(this.vectoresOperacion.get(0).getY2());
        datosDeCampoEnArchivo.setV1z(this.vectoresOperacion.get(0).getZ2());
        datosDeCampoEnArchivo.setV2x(this.vectoresOperacion.get(1).getX2());
        datosDeCampoEnArchivo.setV2y(this.vectoresOperacion.get(1).getY2());
        datosDeCampoEnArchivo.setV2z(this.vectoresOperacion.get(1).getZ2());
        datosDeCampoEnArchivo.setK(escalarK);
        
        // Ajustamos datos
        datosDeCampoEnArchivo.guardarDatos();
        
        // Retornamos
        return datosDeCampoEnArchivo;
    }
    public void cargarDatosDeOBEV(String contenido)
    {
        // Variables
        Bean_OBEV datosDeCampoEnArchivo;
        double x1, y1, z1, x2, y2, z2, k;
        Vector v1, v2;
        
        // Inicializamos
        datosDeCampoEnArchivo=new Bean_OBEV();
        
        // Cargamos datos
        datosDeCampoEnArchivo.cargarDatos(contenido);
        
        // Obtenemos datos
        x1=datosDeCampoEnArchivo.getV1x();
        y1=datosDeCampoEnArchivo.getV1y();
        z1=datosDeCampoEnArchivo.getV1z();
        x2=datosDeCampoEnArchivo.getV2x();
        y2=datosDeCampoEnArchivo.getV2y();
        z2=datosDeCampoEnArchivo.getV2z();
        k=datosDeCampoEnArchivo.getK();
        
        v1=new Vector(0, 0, 0, x1, y1, z1);
        v2=new Vector(0, 0, 0, x2, y2, z2);

        this.vectoresOperacion.add(v1);
        this.vectoresOperacion.add(v2);
        this.escalarK=k;
    }
    
    ///Métodos de propósito Específico

    ///Métodos para el manejo de Atributos

    public ArrayList<Vector> getVectoresOperacion() {
        return vectoresOperacion;
    }

    public void setVectoresOperacion(ArrayList<Vector> vectoresOperacion) {
        this.vectoresOperacion = vectoresOperacion;
    }

    public String getMensajeResultado() {
        return mensajeResultado;
    }

    public void setMensajeResultado(String mensajeResultado) {
        this.mensajeResultado = mensajeResultado;
    }

    public boolean isEsResultadoGrafico() {
        return esResultadoGrafico;
    }

    public void setEsResultadoGrafico(boolean esResultadoGrafico) {
        this.esResultadoGrafico = esResultadoGrafico;
    }

    public Integer getOperacion() {
        return operacion;
    }

    public void setOperacion(Integer operacion) {
        this.operacion = operacion;
    }

    public Double getEscalarK() {
        return escalarK;
    }

    public void setEscalarK(Double escalarK) {
        this.escalarK = escalarK;
    }

    public ArrayList<Vector> getVectoresResultado() {
        return vectoresResultado;
    }

    public void setVectoresResultado(ArrayList<Vector> vectoresResultado) {
        this.vectoresResultado = vectoresResultado;
    }

    public boolean isEsModo3D() {
        return esModo3D;
    }

    public void setEsModo3D(boolean esModo3D) {
        this.esModo3D = esModo3D;
    }
    
}
