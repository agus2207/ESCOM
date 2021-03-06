///Paquete de Trabajo
package com.SAEAAV.Modelo.Graficador.Artefactos;

///Librerias
import com.SAEAAV.Modelo.Matematicas.Constantes_Matematicas;
import com.jogamp.opengl.util.awt.TextRenderer;
import java.awt.Font;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.media.opengl.GL2;
import static javax.media.opengl.GL2.*;

public class Delimitadores extends Artefacto
{
    ///Atributos
    ArrayList<Arista> aristasCubo=new ArrayList<Arista>();
    ArrayList<Arista> aristasCuboExterno=new ArrayList<Arista>();
    ArrayList<Arista> aristasDelimitadores=new ArrayList<Arista>();
    ArrayList<Vertice> verticesNumerosDelimitadores3D=new ArrayList<Vertice>();
    private double ladoCuboDelimitador=1.0;
    private Integer plano=null;
    TextRenderer renderizadorTextoPlano;
    DecimalFormat formateadorTextoNumeros;
    TextRenderer renderizadorTextoNumerico;
    Vertice t1,t2,t3,t4,t5,t6,t7,t8;
    
    ///Constructores
    public Delimitadores(Integer plano, Double rango) 
    {
        this.inicializarTexto();
        this.plano=plano;
        this.ladoCuboDelimitador=(rango==null)?1:rango;
        
        if(this.plano==null)
        {
            this.inicializarModo3D();
        }
        else
        {
            if(plano==Constantes_Matematicas.PLANO_XY)
            {
                this.inicializar_cuadradoPlanoXY();
            }
            if(plano==Constantes_Matematicas.PLANO_XZ)
            {
                this.inicializar_cuadradoPlanoXZ();
            }
            if(plano==Constantes_Matematicas.PLANO_YZ)
            {
                this.inicializar_cuadradoPlanoYZ();
            }
        }
    }
    
    ///Métodos de propósito General
    @Override
    public void construir(GL2 gl) 
    {
        super.gl=gl;
        
        this.construirDelimitadorCaja();
        
        if(this.plano==null)
        {
            this.construirSimbologiaDelimitadores3D();
        }
        if(this.plano!=null)
        {
            this.construirSimbologiaDelimitadores2D();
        }
        
        super.gl=null;
    }   
    private void construirSimbologiaDelimitadores3D()
    {   
        double d,cifra;
        Vertice vDelimitador;
        int numeroDivisiones=1;
        
        d=this.ladoCuboDelimitador/2;
        cifra=-d;
        //dibujar_Texto3D_SoloNumeros(double cifra,x,y,z,float r,g,b)
        //x=0;y=0;z=1.1;
        //r=1; g=0; b=0;
        for(int k=0; k<this.verticesNumerosDelimitadores3D.size(); k++)
        {
            if(numeroDivisiones==4)
            {
                numeroDivisiones=1;
                cifra=-d;
            }
            //System.out.println("numeroDivisiones="+numeroDivisiones);
            //System.out.println("cifra="+cifra);
            vDelimitador = this.verticesNumerosDelimitadores3D.get(k);
            this.dibujar_Texto3D_SoloNumeros(cifra, vDelimitador.getX(), vDelimitador.getY(), vDelimitador.getZ(), 1, 0, 0);
            cifra+=d;
            numeroDivisiones++;
        }
        
    }
    private void construirSimbologiaDelimitadores2D()
    {   
        double d,cifra;
        Vertice vDelimitador;
        int numeroDivisiones=1;
        boolean esSignoPositivo=false;
        
            
        d=this.ladoCuboDelimitador/2;
        cifra=-d;
        //dibujar_Texto3D_SoloNumeros(double cifra,x,y,z,float r,g,b)
        //x=0;y=0;z=1.1;
        //r=1; g=0; b=0;
        //System.out.println("*********************************************");
        for(int k=0; k<this.verticesNumerosDelimitadores3D.size(); k++)
        {
            if(numeroDivisiones==4)
            {
                numeroDivisiones=1;
                
                if(!esSignoPositivo)
                {
                    cifra=+d;
                    esSignoPositivo=true;
                }
                else
                {
                    cifra=-d;
                    esSignoPositivo=false;
                }
            }
            //System.out.println("***");
            //System.out.println("numeroDivisiones="+numeroDivisiones);
            //System.out.println("cifra="+cifra);
            //System.out.println("***");
        
            vDelimitador = this.verticesNumerosDelimitadores3D.get(k);
            this.dibujar_Texto3D_SoloNumeros(cifra, vDelimitador.getX(), vDelimitador.getY(), vDelimitador.getZ(), 1, 0, 0);
            if(!esSignoPositivo)
            {
                cifra+=d;
            }
            else
            {
                cifra-=d;
            }
            
            numeroDivisiones++;
        }
        //System.out.println("*********************************************");
        
        
    }
    private void inicializarTexto()
    {
        // Establecemos características del texto normal
        this.renderizadorTextoPlano = new TextRenderer(new Font("SansSerif", Font.PLAIN, 12));
        this.renderizadorTextoNumerico = new TextRenderer(new Font("SansSerif", Font.PLAIN, 8));
        this.formateadorTextoNumeros = new DecimalFormat("0.0");//"###0.00"
    }
    public void inicializarModo3D()
    {
        this.inicializar_caja();
    }
    
    ///Métodos de propósito Específico
    private void construirDelimitadorCaja()
    {
        // Variables
        Vertice vInicial;
        Vertice vFinal;
        
        // Fijamos el color del objeto
        gl.glColor3f(0, 0, 0);
        
        //Pintamos las aristas=lineas de la caja
        for(int k=0; k<this.aristasCubo.size(); k++)
        {
            // Fijamos vertices
            vInicial = this.aristasCubo.get(k).getvInicial();
            vFinal = this.aristasCubo.get(k).getvFinal();
            
            // Pintamos línea
            gl.glBegin(GL_LINE_STRIP);
                gl.glVertex3d(vInicial.getX(), vInicial.getY(), vInicial.getZ());
                gl.glVertex3d(vFinal.getX(), vFinal.getY(), vFinal.getZ());
            gl.glEnd();
        }
        
        //Pintamos las aristas=lineas delimitadoras
        for(int k=0; k<this.aristasDelimitadores.size(); k++)
        {
            // Fijamos vertices
            vInicial = this.aristasDelimitadores.get(k).getvInicial();
            vFinal = this.aristasDelimitadores.get(k).getvFinal();
            
            // Pintamos línea
            gl.glBegin(GL_LINE_STRIP);
                gl.glVertex3d(vInicial.getX(), vInicial.getY(), vInicial.getZ());
                gl.glVertex3d(vFinal.getX(), vFinal.getY(), vFinal.getZ());
            gl.glEnd();
        }
    }
    private void inicializar_caja()
    {
        // Variables
        double d,g;
        Vertice v1,v2,v3,v4,v5,v6,v7,v8;
        Arista e1,e2,e3,e4,e5,e6,e7,e8,e9,e10,e11,e12;
        Arista u1,u2,u3,u4,u5,u6,u7,u8,u9,u10,u11,u12;
        ArrayList<Vertice> em=new ArrayList<Vertice>();
        ArrayList<Vertice> um=new ArrayList<Vertice>();
        Vertice em1,em2,em3,em4,em5,em6,em7,em8,em9,em10,em11,em12;
        Vertice um1,um2,um3,um4,um5,um6,um7,um8,um9,um10,um11,um12;
        Vertice vInicial;
        Vertice vFinal;
        Vertice verticeMedio;
        Arista aristaDelimitador;
        double kCubo;
        
        // Inicializamos
        d=this.ladoCuboDelimitador/2;
        kCubo=0.2;//(this.ladoCuboDelimitador/7)
        g=(this.ladoCuboDelimitador+kCubo)/2;
        
        // Lista de Vertices
        v1=new Vertice(d,d,d);
        v2=new Vertice(d,d,-d);
        v3=new Vertice(d,-d,-d);
        v4=new Vertice(d,-d,d);
        v5=new Vertice(-d,-d,d);
        v6=new Vertice(-d,d,d);
        v7=new Vertice(-d,d,-d);
        v8=new Vertice(-d,-d,-d);
        
        // Lista de Aristas
        e1=new Arista(v1,v2);
        e2=new Arista(v2,v3);
        e3=new Arista(v3,v4);
        e4=new Arista(v4,v1);
        e5=new Arista(v5,v6);
        e6=new Arista(v6,v7);
        e7=new Arista(v7,v8);
        e8=new Arista(v8,v5);
        e9=new Arista(v4,v5);
        e10=new Arista(v3,v8);
        e11=new Arista(v2,v7);
        e12=new Arista(v1,v6);
        
        // Ingresamos la lista de aristas al atributo que corresponde
        this.aristasCubo.add(e1);
        this.aristasCubo.add(e2);
        this.aristasCubo.add(e3);
        this.aristasCubo.add(e4);
        this.aristasCubo.add(e5);
        this.aristasCubo.add(e6);
        this.aristasCubo.add(e7);
        this.aristasCubo.add(e8);
        this.aristasCubo.add(e9);
        this.aristasCubo.add(e10);
        this.aristasCubo.add(e11);
        this.aristasCubo.add(e12);
       
        // Lista de Vertices #2
        t1=new Vertice(g,g,g);
        t2=new Vertice(g,g,-g);
        t3=new Vertice(g,-g,-g);
        t4=new Vertice(g,-g,g);
        t5=new Vertice(-g,-g,g);
        t6=new Vertice(-g,g,g);
        t7=new Vertice(-g,g,-g);
        t8=new Vertice(-g,-g,-g);
        
        // Lista de Aristas #2
        u1=new Arista(t1,t2);
        u2=new Arista(t2,t3);
        u3=new Arista(t3,t4);
        u4=new Arista(t4,t1);
        u5=new Arista(t5,t6);
        u6=new Arista(t6,t7);
        u7=new Arista(t7,t8);
        u8=new Arista(t8,t5);
        u9=new Arista(t4,t5);
        u10=new Arista(t3,t8);
        u11=new Arista(t2,t7);
        u12=new Arista(t1,t6);
        
        // Ingresamos la lista de aristas al atributo externo que corresponde
        this.aristasCuboExterno.add(u1);
        this.aristasCuboExterno.add(u2);
        this.aristasCuboExterno.add(u3);
        this.aristasCuboExterno.add(u4);
        this.aristasCuboExterno.add(u5);
        this.aristasCuboExterno.add(u6);
        this.aristasCuboExterno.add(u7);
        this.aristasCuboExterno.add(u8);
        this.aristasCuboExterno.add(u9);
        this.aristasCuboExterno.add(u10);
        this.aristasCuboExterno.add(u11);
        this.aristasCuboExterno.add(u12);
        
        // Ahora calculamos los vértices medios/fraccionales
        for(int k=0; k<this.aristasCubo.size(); k++)
        {
            // Fijamos vertices
            vInicial = this.aristasCubo.get(k).getvInicial();
            vFinal = this.aristasCubo.get(k).getvFinal();
            
            //Creamos el nuevo vértice medio
            verticeMedio=this.obtenerVerticeMedio(vInicial.getX(), vInicial.getY(), vInicial.getZ(),vFinal.getX(), vFinal.getY(), vFinal.getZ());
            
            //Lo insertamos en la lista que le corresponde
            em.add(verticeMedio);
        }
        for(int k=0; k<this.aristasCuboExterno.size(); k++)
        {
            // Fijamos vertices
            vInicial = this.aristasCuboExterno.get(k).getvInicial();
            vFinal = this.aristasCuboExterno.get(k).getvFinal();
            
            //Creamos el nuevo vértice medio
            verticeMedio=this.obtenerVerticeMedio(vInicial.getX(), vInicial.getY(), vInicial.getZ(),vFinal.getX(), vFinal.getY(), vFinal.getZ());
            
            //Lo insertamos en la lista que le corresponde
            um.add(verticeMedio);
        }
        
        // Formamos la lista de aristas delimitadores
        aristaDelimitador=new Arista(t1,v1);
        this.aristasDelimitadores.add(aristaDelimitador);
        aristaDelimitador=new Arista(t2,v2);
        this.aristasDelimitadores.add(aristaDelimitador);
        aristaDelimitador=new Arista(t3,v3);
        this.aristasDelimitadores.add(aristaDelimitador);
        aristaDelimitador=new Arista(t4,v4);
        this.aristasDelimitadores.add(aristaDelimitador);
        aristaDelimitador=new Arista(t5,v5);
        this.aristasDelimitadores.add(aristaDelimitador);
        aristaDelimitador=new Arista(t6,v6);
        this.aristasDelimitadores.add(aristaDelimitador);
        aristaDelimitador=new Arista(t7,v7);
        this.aristasDelimitadores.add(aristaDelimitador);
        aristaDelimitador=new Arista(t8,v8);
        this.aristasDelimitadores.add(aristaDelimitador);
        for(int k=0; k<um.size(); k++)
        {
            aristaDelimitador=new Arista(um.get(k), em.get(k));
            this.aristasDelimitadores.add(aristaDelimitador);
        }
        
        // Fijamos los vértices para los delimitadores numéricos
        this.verticesNumerosDelimitadores3D.add(t7);
        vInicial = u11.getvInicial();vFinal = u11.getvFinal();
        verticeMedio=this.obtenerVerticeMedio(vInicial.getX(), vInicial.getY(), vInicial.getZ(),vFinal.getX(), vFinal.getY(), vFinal.getZ());
        this.verticesNumerosDelimitadores3D.add(verticeMedio);
        this.verticesNumerosDelimitadores3D.add(t2);
        this.verticesNumerosDelimitadores3D.add(t3);
        vInicial = u3.getvInicial();vFinal = u3.getvFinal();
        verticeMedio=this.obtenerVerticeMedio(vInicial.getX(), vInicial.getY(), vInicial.getZ(),vFinal.getX(), vFinal.getY(), vFinal.getZ());
        this.verticesNumerosDelimitadores3D.add(verticeMedio);
        this.verticesNumerosDelimitadores3D.add(t4);
        this.verticesNumerosDelimitadores3D.add(t5);
        vInicial = u5.getvInicial();vFinal = u5.getvFinal();
        verticeMedio=this.obtenerVerticeMedio(vInicial.getX(), vInicial.getY(), vInicial.getZ(),vFinal.getX(), vFinal.getY(), vFinal.getZ());
        this.verticesNumerosDelimitadores3D.add(verticeMedio);
        this.verticesNumerosDelimitadores3D.add(t6);
        
    }   
    private void inicializar_cuadradoPlanoXY()
    {
        // Variables
        double d,g;
        Vertice v1,v2,v3,v4,v5,v6,v7,v8;
        Arista e1,e2,e3,e4,e5,e6,e7,e8,e9,e10,e11,e12;
        Arista u1,u2,u3,u4,u5,u6,u7,u8,u9,u10,u11,u12;
        ArrayList<Vertice> em=new ArrayList<Vertice>();
        ArrayList<Vertice> um=new ArrayList<Vertice>();
        Vertice em1,em2,em3,em4,em5,em6,em7,em8,em9,em10,em11,em12;
        Vertice um1,um2,um3,um4,um5,um6,um7,um8,um9,um10,um11,um12;
        Vertice vInicial;
        Vertice vFinal;
        Vertice verticeMedio;
        Arista aristaDelimitador;
        double x,y,z;
        Vertice tRepetido;
        double kCubo;
        
        // Inicializamos
        d=this.ladoCuboDelimitador/2;
        kCubo=0.1;//this.ladoCuboDelimitador/7;
        g=(this.ladoCuboDelimitador+kCubo)/2;
        
        // Lista de Vertices Z=0
        v1=new Vertice(d,d,0);
        v4=new Vertice(d,-d,0);
        v5=new Vertice(-d,-d,0);
        v6=new Vertice(-d,d,0);
        
        // Lista de Aristas
        e4=new Arista(v4,v1);
        e5=new Arista(v5,v6);
        e9=new Arista(v4,v5);
        e12=new Arista(v1,v6);
        
        // Ingresamos la lista de aristas al atributo que corresponde
        this.aristasCubo.add(e4);
        this.aristasCubo.add(e5);
        this.aristasCubo.add(e9);
        this.aristasCubo.add(e12);
       
        // Lista de Vertices #2
        t1=new Vertice(g,g,0);
        t4=new Vertice(g,-g,0);
        t5=new Vertice(-g,-g,0);
        t6=new Vertice(-g,g,0);
        
        // Lista de Aristas #2
        u4=new Arista(t4,t1);
        u5=new Arista(t5,t6);
        u9=new Arista(t4,t5);
        u12=new Arista(t1,t6);
        
        // Ingresamos la lista de aristas al atributo externo que corresponde
        this.aristasCuboExterno.add(u4);
        this.aristasCuboExterno.add(u5);
        this.aristasCuboExterno.add(u9);
        this.aristasCuboExterno.add(u12);
        
        // Ahora calculamos los vértices medios/fraccionales
        for(int k=0; k<this.aristasCubo.size(); k++)
        {
            // Fijamos vertices
            vInicial = this.aristasCubo.get(k).getvInicial();
            vFinal = this.aristasCubo.get(k).getvFinal();
            
            //Creamos el nuevo vértice medio
            verticeMedio=this.obtenerVerticeMedio(vInicial.getX(), vInicial.getY(), vInicial.getZ(),vFinal.getX(), vFinal.getY(), vFinal.getZ());
            
            //Lo insertamos en la lista que le corresponde
            em.add(verticeMedio);
        }
        for(int k=0; k<this.aristasCuboExterno.size(); k++)
        {
            // Fijamos vertices
            vInicial = this.aristasCuboExterno.get(k).getvInicial();
            vFinal = this.aristasCuboExterno.get(k).getvFinal();
            
            //Creamos el nuevo vértice medio
            verticeMedio=this.obtenerVerticeMedio(vInicial.getX(), vInicial.getY(), vInicial.getZ(),vFinal.getX(), vFinal.getY(), vFinal.getZ());
            
            //Lo insertamos en la lista que le corresponde
            um.add(verticeMedio);
        }
        
        // Formamos la lista de aristas delimitadores
        aristaDelimitador=new Arista(t1,v1);
        this.aristasDelimitadores.add(aristaDelimitador);
        aristaDelimitador=new Arista(t4,v4);
        this.aristasDelimitadores.add(aristaDelimitador);
        aristaDelimitador=new Arista(t5,v5);
        this.aristasDelimitadores.add(aristaDelimitador);
        aristaDelimitador=new Arista(t6,v6);
        this.aristasDelimitadores.add(aristaDelimitador);
        for(int k=0; k<um.size(); k++)
        {
            aristaDelimitador=new Arista(um.get(k), em.get(k));
            this.aristasDelimitadores.add(aristaDelimitador);
        }
        
        // Fijamos los vértices para los delimitadores numéricos
        this.verticesNumerosDelimitadores3D.add(t6);
        vInicial = u12.getvInicial();vFinal = u12.getvFinal();
        verticeMedio=this.obtenerVerticeMedio(vInicial.getX(), vInicial.getY(), vInicial.getZ(),vFinal.getX(), vFinal.getY(), vFinal.getZ());
        this.verticesNumerosDelimitadores3D.add(verticeMedio);
        x=t1.getX()-kCubo*1.5;
        y=t1.getY();
        z=t1.getZ();
        tRepetido=new Vertice(x,y,z);
        this.verticesNumerosDelimitadores3D.add(tRepetido);
        x=t1.getX();
        y=t1.getY()-kCubo;
        z=t1.getZ();
        tRepetido=new Vertice(x,y,z);
        this.verticesNumerosDelimitadores3D.add(tRepetido);
        vInicial = u4.getvInicial();vFinal = u4.getvFinal();
        verticeMedio=this.obtenerVerticeMedio(vInicial.getX(), vInicial.getY(), vInicial.getZ(),vFinal.getX(), vFinal.getY(), vFinal.getZ());
        this.verticesNumerosDelimitadores3D.add(verticeMedio);
        this.verticesNumerosDelimitadores3D.add(t4);
        
    }
    private void inicializar_cuadradoPlanoXZ()
    {
        // Variables
        double d,g;
        Vertice v1,v2,v3,v4,v5,v6,v7,v8;
        Arista e1,e2,e3,e4,e5,e6,e7,e8,e9,e10,e11,e12;
        Arista u1,u2,u3,u4,u5,u6,u7,u8,u9,u10,u11,u12;
        ArrayList<Vertice> em=new ArrayList<Vertice>();
        ArrayList<Vertice> um=new ArrayList<Vertice>();
        Vertice em1,em2,em3,em4,em5,em6,em7,em8,em9,em10,em11,em12;
        Vertice um1,um2,um3,um4,um5,um6,um7,um8,um9,um10,um11,um12;
        Vertice vInicial;
        Vertice vFinal;
        Vertice verticeMedio;
        Arista aristaDelimitador;
        double x,y,z;
        Vertice tRepetido;
        double kCubo;
        
        // Inicializamos
        d=this.ladoCuboDelimitador/2;
        kCubo=0.1;//this.ladoCuboDelimitador/7;
        g=(this.ladoCuboDelimitador+kCubo)/2;
        
        // Lista de Vertices Y=0
        v3=new Vertice(d,-0,-d);
        v4=new Vertice(d,-0,d);
        v5=new Vertice(-d,-0,d);
        v8=new Vertice(-d,-0,-d);
        
        // Lista de Aristas
        e3=new Arista(v3,v4);
        e8=new Arista(v8,v5);
        e9=new Arista(v4,v5);
        e10=new Arista(v3,v8);
        
        // Ingresamos la lista de aristas al atributo que corresponde
        this.aristasCubo.add(e3);
        this.aristasCubo.add(e8);
        this.aristasCubo.add(e9);
        this.aristasCubo.add(e10);
        
        // Lista de Vertices #2
        t3=new Vertice(g,-0,-g);
        t4=new Vertice(g,-0,g);
        t5=new Vertice(-g,-0,g);
        t8=new Vertice(-g,-0,-g);
        
        // Lista de Aristas #2
        u3=new Arista(t3,t4);
        u8=new Arista(t8,t5);
        u9=new Arista(t4,t5);
        u10=new Arista(t3,t8);
        
        // Ingresamos la lista de aristas al atributo externo que corresponde
        this.aristasCuboExterno.add(u3);
        this.aristasCuboExterno.add(u8);
        this.aristasCuboExterno.add(u9);
        this.aristasCuboExterno.add(u10);
        
        // Ahora calculamos los vértices medios/fraccionales
        for(int k=0; k<this.aristasCubo.size(); k++)
        {
            // Fijamos vertices
            vInicial = this.aristasCubo.get(k).getvInicial();
            vFinal = this.aristasCubo.get(k).getvFinal();
            
            //Creamos el nuevo vértice medio
            verticeMedio=this.obtenerVerticeMedio(vInicial.getX(), vInicial.getY(), vInicial.getZ(),vFinal.getX(), vFinal.getY(), vFinal.getZ());
            
            //Lo insertamos en la lista que le corresponde
            em.add(verticeMedio);
        }
        for(int k=0; k<this.aristasCuboExterno.size(); k++)
        {
            // Fijamos vertices
            vInicial = this.aristasCuboExterno.get(k).getvInicial();
            vFinal = this.aristasCuboExterno.get(k).getvFinal();
            
            //Creamos el nuevo vértice medio
            verticeMedio=this.obtenerVerticeMedio(vInicial.getX(), vInicial.getY(), vInicial.getZ(),vFinal.getX(), vFinal.getY(), vFinal.getZ());
            
            //Lo insertamos en la lista que le corresponde
            um.add(verticeMedio);
        }
        
        // Formamos la lista de aristas delimitadores
        aristaDelimitador=new Arista(t3,v3);
        this.aristasDelimitadores.add(aristaDelimitador);
        aristaDelimitador=new Arista(t4,v4);
        this.aristasDelimitadores.add(aristaDelimitador);
        aristaDelimitador=new Arista(t5,v5);
        this.aristasDelimitadores.add(aristaDelimitador);
        aristaDelimitador=new Arista(t8,v8);
        this.aristasDelimitadores.add(aristaDelimitador);
        for(int k=0; k<um.size(); k++)
        {
            aristaDelimitador=new Arista(um.get(k), em.get(k));
            this.aristasDelimitadores.add(aristaDelimitador);
        }
        
        // Fijamos los vértices para los delimitadores numéricos
        this.verticesNumerosDelimitadores3D.add(t5);
        vInicial = u9.getvInicial();vFinal = u9.getvFinal();
        verticeMedio=this.obtenerVerticeMedio(vInicial.getX(), vInicial.getY(), vInicial.getZ(),vFinal.getX(), vFinal.getY(), vFinal.getZ());
        this.verticesNumerosDelimitadores3D.add(verticeMedio);
        x=t4.getX()-kCubo*1.5;
        y=t4.getY();
        z=t4.getZ();
        tRepetido=new Vertice(x,y,z);
        this.verticesNumerosDelimitadores3D.add(tRepetido);
        x=t4.getX();
        y=t4.getY();
        z=t4.getZ()-kCubo;
        tRepetido=new Vertice(x,y,z);
        this.verticesNumerosDelimitadores3D.add(tRepetido);
        vInicial = u3.getvInicial();vFinal = u3.getvFinal();
        verticeMedio=this.obtenerVerticeMedio(vInicial.getX(), vInicial.getY(), vInicial.getZ(),vFinal.getX(), vFinal.getY(), vFinal.getZ());
        this.verticesNumerosDelimitadores3D.add(verticeMedio);
        this.verticesNumerosDelimitadores3D.add(t3);
    }   
    private void inicializar_cuadradoPlanoYZ()
    {
        // Variables
        double d,g;
        Vertice v1,v2,v3,v4,v5,v6,v7,v8;
        Arista e1,e2,e3,e4,e5,e6,e7,e8,e9,e10,e11,e12;
        Arista u1,u2,u3,u4,u5,u6,u7,u8,u9,u10,u11,u12;
        ArrayList<Vertice> em=new ArrayList<Vertice>();
        ArrayList<Vertice> um=new ArrayList<Vertice>();
        Vertice em1,em2,em3,em4,em5,em6,em7,em8,em9,em10,em11,em12;
        Vertice um1,um2,um3,um4,um5,um6,um7,um8,um9,um10,um11,um12;
        Vertice vInicial;
        Vertice vFinal;
        Vertice verticeMedio;
        Arista aristaDelimitador;
        double x,y,z;
        Vertice tRepetido;
        double kCubo;
        
        // Inicializamos
        d=this.ladoCuboDelimitador/2;
        kCubo=0.1;//this.ladoCuboDelimitador/7;
        g=(this.ladoCuboDelimitador+kCubo)/2;
        
        // Lista de Vertices X=0
        v1=new Vertice(0,d,d);
        v2=new Vertice(0,d,-d);
        v3=new Vertice(0,-d,-d);
        v4=new Vertice(0,-d,d);
        
        // Lista de Aristas
        e1=new Arista(v1,v2);
        e2=new Arista(v2,v3);
        e3=new Arista(v3,v4);
        e4=new Arista(v4,v1);
        
        // Ingresamos la lista de aristas al atributo que corresponde
        this.aristasCubo.add(e1);
        this.aristasCubo.add(e2);
        this.aristasCubo.add(e3);
        this.aristasCubo.add(e4);
        
        // Lista de Vertices #2 X=0
        t1=new Vertice(0,g,g);
        t2=new Vertice(0,g,-g);
        t3=new Vertice(0,-g,-g);
        t4=new Vertice(0,-g,g);
        
        // Lista de Aristas #2
        u1=new Arista(t1,t2);
        u2=new Arista(t2,t3);
        u3=new Arista(t3,t4);
        u4=new Arista(t4,t1);
        
        // Ingresamos la lista de aristas al atributo externo que corresponde
        this.aristasCuboExterno.add(u1);
        this.aristasCuboExterno.add(u2);
        this.aristasCuboExterno.add(u3);
        this.aristasCuboExterno.add(u4);
        
        // Ahora calculamos los vértices medios/fraccionales
        for(int k=0; k<this.aristasCubo.size(); k++)
        {
            // Fijamos vertices
            vInicial = this.aristasCubo.get(k).getvInicial();
            vFinal = this.aristasCubo.get(k).getvFinal();
            
            //Creamos el nuevo vértice medio
            verticeMedio=this.obtenerVerticeMedio(vInicial.getX(), vInicial.getY(), vInicial.getZ(),vFinal.getX(), vFinal.getY(), vFinal.getZ());
            
            //Lo insertamos en la lista que le corresponde
            em.add(verticeMedio);
        }
        for(int k=0; k<this.aristasCuboExterno.size(); k++)
        {
            // Fijamos vertices
            vInicial = this.aristasCuboExterno.get(k).getvInicial();
            vFinal = this.aristasCuboExterno.get(k).getvFinal();
            
            //Creamos el nuevo vértice medio
            verticeMedio=this.obtenerVerticeMedio(vInicial.getX(), vInicial.getY(), vInicial.getZ(),vFinal.getX(), vFinal.getY(), vFinal.getZ());
            
            //Lo insertamos en la lista que le corresponde
            um.add(verticeMedio);
        }
        
        // Formamos la lista de aristas delimitadores
        aristaDelimitador=new Arista(t1,v1);
        this.aristasDelimitadores.add(aristaDelimitador);
        aristaDelimitador=new Arista(t2,v2);
        this.aristasDelimitadores.add(aristaDelimitador);
        aristaDelimitador=new Arista(t3,v3);
        this.aristasDelimitadores.add(aristaDelimitador);
        aristaDelimitador=new Arista(t4,v4);
        this.aristasDelimitadores.add(aristaDelimitador);
        for(int k=0; k<um.size(); k++)
        {
            aristaDelimitador=new Arista(um.get(k), em.get(k));
            this.aristasDelimitadores.add(aristaDelimitador);
        }
        
        // Fijamos los vértices para los delimitadores numéricos
        this.verticesNumerosDelimitadores3D.add(t4);
        vInicial = u4.getvInicial();vFinal = u4.getvFinal();
        verticeMedio=this.obtenerVerticeMedio(vInicial.getX(), vInicial.getY(), vInicial.getZ(),vFinal.getX(), vFinal.getY(), vFinal.getZ());
        this.verticesNumerosDelimitadores3D.add(verticeMedio);
        x=t1.getX();
        y=t1.getY()-kCubo*1.5;
        z=t1.getZ();
        tRepetido=new Vertice(x,y,z);
        this.verticesNumerosDelimitadores3D.add(tRepetido);
        x=t1.getX();
        y=t1.getY();
        z=t1.getZ()-kCubo;
        tRepetido=new Vertice(x,y,z);
        this.verticesNumerosDelimitadores3D.add(tRepetido);
        vInicial = u1.getvInicial();vFinal = u1.getvFinal();
        verticeMedio=this.obtenerVerticeMedio(vInicial.getX(), vInicial.getY(), vInicial.getZ(),vFinal.getX(), vFinal.getY(), vFinal.getZ());
        this.verticesNumerosDelimitadores3D.add(verticeMedio);
        this.verticesNumerosDelimitadores3D.add(t2);
    } 
    private Vertice obtenerVerticeMedio(double xi, double yi, double zi, double xf, double yf, double zf)
    {
        // Variables
        double xm, ym, zm;
        Vertice verticeMedio;
        
        // Procedemos a calcular
        xm=xi+(xf-xi)*0.5;//Calculo del punto medio
        ym=yi+(yf-yi)*0.5;//Calculo del punto medio
        zm=zi+(zf-zi)*0.5;//Calculo del punto medio
        
        // Generamos el vértice
        verticeMedio=new Vertice(xm, ym, zm);
        
        // Retornamos el resultado
        return verticeMedio;
    }
    private void dibujar_Texto3D_SoloNumeros(double cifra, double x, double y, double z,float r, float g, float b)
    {
        // Desplegamos el texto de Numeros
        super.gl.glPushMatrix();
            this.renderizadorTextoNumerico.begin3DRendering();// Inicia renderizado      
                super.gl.glTranslated(x, y, z);// Fija posición del texto en la escena
                if(this.plano!=null)
                {
                    if(this.plano==Constantes_Matematicas.PLANO_XY)
                    {
                        super.gl.glRotated(0, 1, 0, 0);
                    }
                    if(this.plano==Constantes_Matematicas.PLANO_XZ)
                    {
                        super.gl.glRotated(90, 1, 0, 0);
                    }
                    if(this.plano==Constantes_Matematicas.PLANO_YZ)
                    {
                        super.gl.glRotated(90, 1, 0, 0);
                        super.gl.glRotated(90, 0, 1, 0);
                    }
                }
                this.renderizadorTextoNumerico.setColor(r, g, b, 1f);//Fija Color RGBA#A=1       
                this.renderizadorTextoNumerico.draw3D(formateadorTextoNumeros.format(cifra), 0.0f,0.0f, 0.0f, 0.01f);//draw3D(String textoNumeros, float x, float y, float z, float factorEscala)   
                this.renderizadorTextoNumerico.flush();//Nos aseguramos de que el textoNumeros sea dibujado
            this.renderizadorTextoNumerico.end3DRendering();// Finaliza Renderizado
        super.gl.glPopMatrix();
    }
      
    ///Métodos para el manejo de Atributos

    public void setLadoCuboDelimitador(double ladoCuboDelimitador) {
        this.ladoCuboDelimitador = ladoCuboDelimitador;
    }

    public void setPlano(Integer plano) {
        this.plano = plano;
    }
    
}
