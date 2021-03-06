///Paquete de Trabajo
package com.SAEAAV.Modelo.Matematicas;

///Librerias

public class OperacionBasicaEntreVectores
{
    ///Atributos
    private Datos_OperacionBasicaEntreVectores datosOperacionBasicaEntreVectores=null;
    
    ///Constructores
    public OperacionBasicaEntreVectores(Datos_OperacionBasicaEntreVectores datosOperacionBasicaEntreVectores) 
    {
        this.datosOperacionBasicaEntreVectores=datosOperacionBasicaEntreVectores;
        this.inicializar();
    }
    
    ///Métodos de propósito General
    private void inicializar()
    {
        // Comprobación
        if(this.datosOperacionBasicaEntreVectores==null)return;
       
        //Variables
        Integer operacion;
        
        //Extraemos datos de interés
        operacion = this.datosOperacionBasicaEntreVectores.getOperacion();
        
        // Realizamos la operación
        switch(operacion)
        {
            case Datos_OperacionBasicaEntreVectores.OPERACION_SUMADEVECTORES:
                this.operacion_sumaDeVectores();
            break;
            case Datos_OperacionBasicaEntreVectores.OPERACION_MULTIPLICACIONDEVECTORPORESCALAR:
                this.operacion_multiplicacionDeVectorPorEscalar();
            break;
            case Datos_OperacionBasicaEntreVectores.OPERACION_PRODUCTOCRUZDEVECTORES:
                this.operacion_productoCRUZDeVectores();
            break;
            case Datos_OperacionBasicaEntreVectores.OPERACION_PRODUCTOPUNTODEVECTORES:
                this.operacion_productoPUNTODeVectores_Escalar();
            break;    
            default:
                // Nada
            break;
        }
        
        // Decimos cuando los cálculos estén listos...
        java.awt.Toolkit.getDefaultToolkit().beep();
    }
    
    ///Métodos de propósito Específico
    private void operacion_sumaDeVectores()
    {
        Vector v1=this.datosOperacionBasicaEntreVectores.getVectoresOperacion().get(0);
        Vector v2=this.datosOperacionBasicaEntreVectores.getVectoresOperacion().get(1);
        
        this.datosOperacionBasicaEntreVectores.getVectoresResultado().add(this.realizar_sumaDeVectores(v1, v2));
        this.datosOperacionBasicaEntreVectores.setMensajeResultado("");
        this.datosOperacionBasicaEntreVectores.setEsResultadoGrafico(true);
    }
    private void operacion_multiplicacionDeVectorPorEscalar()
    {
        Vector v1=this.datosOperacionBasicaEntreVectores.getVectoresOperacion().get(0);
        double escalarK=this.datosOperacionBasicaEntreVectores.getEscalarK();
        
        this.datosOperacionBasicaEntreVectores.getVectoresResultado().add(this.realizar_multiplicacionDeVectorPorEscalar(v1, escalarK));
        this.datosOperacionBasicaEntreVectores.setMensajeResultado("");
        this.datosOperacionBasicaEntreVectores.setEsResultadoGrafico(true);
    }
    private void operacion_productoCRUZDeVectores()
    {
        Vector v1=this.datosOperacionBasicaEntreVectores.getVectoresOperacion().get(0);
        Vector v2=this.datosOperacionBasicaEntreVectores.getVectoresOperacion().get(1);
        
        this.datosOperacionBasicaEntreVectores.getVectoresResultado().add(this.realizar_productoCRUZDeVectores(v1, v2));
        this.datosOperacionBasicaEntreVectores.setMensajeResultado("");
        this.datosOperacionBasicaEntreVectores.setEsResultadoGrafico(true);
    }
    private void operacion_productoPUNTODeVectores_Escalar()
    {
        Vector v1=this.datosOperacionBasicaEntreVectores.getVectoresOperacion().get(0);
        Vector v2=this.datosOperacionBasicaEntreVectores.getVectoresOperacion().get(1);
        
        String mensajeResultado=""+this.realizar_productoPUNTODeVectores_Escalar(v1, v2);
        this.datosOperacionBasicaEntreVectores.setMensajeResultado(mensajeResultado);
        this.datosOperacionBasicaEntreVectores.setEsResultadoGrafico(false);
    }
    private Vector realizar_sumaDeVectores(Vector v1, Vector v2)
    {
        //Variables
        double x1;
        double y1;
        double z1;
        double x2;
        double y2;
        double z2;
        Vector vResultante;
        double x_Resultante;
        double y_Resultante;
        double z_Resultante;
        
        //Inicializamos
        x1=v1.getX2();
        y1=v1.getY2();
        z1=v1.getZ2();
        x2=v2.getX2();
        y2=v2.getY2();
        z2=v2.getZ2();
        
        
        //Calculamos la suma de los dos vectores
        x_Resultante=x1+x2;
        y_Resultante=y1+y2;
        z_Resultante=z1+z2;
        
        //Lo metemos en el vector resultante
        vResultante=new Vector(0,0,0, x_Resultante,y_Resultante,z_Resultante);
        
        //Retornamos resultado
        return vResultante;
    }
    private Vector realizar_multiplicacionDeVectorPorEscalar(Vector v1, double k)
    {
        //Variables
        double x1;
        double y1;
        double z1;
        double x2;
        double y2;
        double z2;
        Vector vResultante;
        double x_Resultante;
        double y_Resultante;
        double z_Resultante;
        
        //Inicializamos
        x1=v1.getX2();
        y1=v1.getY2();
        z1=v1.getZ2();
        
        //Calculamos la multiplicación del vector por el escalar
        x_Resultante=x1*k;
        y_Resultante=y1*k;
        z_Resultante=z1*k;
        
        //Lo metemos en el vector resultante
        vResultante=new Vector(0,0,0,x_Resultante,y_Resultante,z_Resultante);
        
        //Retornamos resultado
        return vResultante;
    }
    private Vector realizar_productoCRUZDeVectores(Vector v1, Vector v2)
    {
        //Variables
        double Ax;
        double Ay;
        double Az;
        double Bx;
        double By;
        double Bz;
        Vector vResultante;
        double x_Resultante;
        double y_Resultante;
        double z_Resultante;
        
        //Inicializamos
        Ax=v1.getX2();
        Ay=v1.getY2();
        Az=v1.getZ2();
        Bx=v2.getX2();
        By=v2.getY2();
        Bz=v2.getZ2();
        
        
        //Calculamos la suma de los dos vectores
        x_Resultante=Ay*Bz-Az*By;
        y_Resultante=-1*((Ax*Bz)-(Az*Bx));
        z_Resultante=Ax*By-Ay*Bx;
        
        //Lo metemos en el vector resultante
        vResultante=new Vector(0,0,0,x_Resultante,y_Resultante,z_Resultante);
        
        //Retornamos resultado
        return vResultante;
    }
    private double realizar_productoPUNTODeVectores_Escalar(Vector v1, Vector v2)
    {
        //Variables
        double Ax;
        double Ay;
        double Az;
        double Bx;
        double By;
        double Bz;
        double productoEscalar;
        
        //Inicializamos
        Ax=v1.getX2();
        Ay=v1.getY2();
        Az=v1.getZ2();
        Bx=v2.getX2();
        By=v2.getY2();
        Bz=v2.getZ2();
        
        
        //Calculamos la producto escalante de los dos vectores
        productoEscalar=Ax*Bx+Ay*By+Az*Bz;
        
        //Retornamos resultado
        return productoEscalar;
    }
    private double realizar_productoPUNTODeVectores_Angulo(Vector v1, Vector v2)
    {
        //Variables
        double Ax;
        double Ay;
        double Az;
        double Bx;
        double By;
        double Bz;
        double productoEscalar;
        double anguloEntreVectores;
        double magnitudA;
        double magnitudB;
        
        //Inicializamos
        Ax=v1.getX2();
        Ay=v1.getY2();
        Az=v1.getZ2();
        Bx=v2.getX2();
        By=v2.getY2();
        Bz=v2.getZ2();
                
        //Calculamos la producto escalante de los dos vectores
        productoEscalar=Ax*Bx+Ay*By+Az*Bz;
        
        //Calculamos magnitudes
        magnitudA=Math.sqrt(Math.pow(Ax, 2)+Math.pow(Ay, 2)+Math.pow(Az, 2));
        magnitudB=Math.sqrt(Math.pow(Bx, 2)+Math.pow(By, 2)+Math.pow(Bz, 2));
        
        //Ahora vamos por el ángulo
        anguloEntreVectores=Math.acos(productoEscalar/(magnitudA*magnitudB));
        
        //Convertimos a grados
        anguloEntreVectores=Math.toDegrees(anguloEntreVectores);
        
        //Retornamos resultado
        return anguloEntreVectores;
    }
    
    ///Métodos para el manejo de Atributos

    public Datos_OperacionBasicaEntreVectores getDatosOperacionBasicaEntreVectores() 
    {
        return datosOperacionBasicaEntreVectores;
    }
    
}
