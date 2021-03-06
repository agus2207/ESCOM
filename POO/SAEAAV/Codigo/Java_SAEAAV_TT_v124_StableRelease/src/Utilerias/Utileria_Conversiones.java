//Paquete de trabajo
package Utilerias;

//Librerias
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Utileria_Conversiones 
{
    //Atributos
    
    //Constructores
    public Utileria_Conversiones()
    {
        //Constructor por defecto
    }
    
    //Métodos específicos
    public String ReemplazarSegmentoDeCadena(String cadenaOriginal, String cadenaAEncontrar,String cadenaReemplazo)//OK
    {
        //Declaración de variables
        String cadenaNueva="";
        
        //Procedemos a realizar la sustitución como tal
        cadenaNueva=cadenaOriginal.replace(cadenaAEncontrar, cadenaReemplazo);
        
        //Finalmente retornamos el resultado obtenido
        return cadenaNueva;
    }
    public String QuitarCaracteresEspecificadosDeLaCadena(String cadenaOriginal, String CaracteresAQuitarDeLaCadena)//OK
    {   
        //Simple, solo reemplazamos...
        cadenaOriginal=this.ReemplazarSegmentoDeCadena(cadenaOriginal, CaracteresAQuitarDeLaCadena, "");
        
        //Finalmente retornamos el resultado obtenido
        return cadenaOriginal;
    }
    
    /*Comentario: El índice empieza por el número 1 en adelante.*/
    public String ObtenerDivisionEspecificadaDeLaCadenaPorLaCifra(int numeroDivisionAObtener, String CadenaOriginal, String CadenaDivisor)//OK 
    {
        //Declaración de variables
        StringTokenizer division=null;
        int coin=0;
        
        //Si es que contiene algo la cadena..
        if(!CadenaOriginal.equals(""))
        {
            //Despues ya me encargo con tokenizer de realiar la division,
            //Aunque bueno, en este caso más bien lo que pretendemos es 
            //quitar símbolos en especial
            division = new StringTokenizer(CadenaOriginal,CadenaDivisor);

            //Inicializamos el valor de la cadenaOriginal
            CadenaOriginal="";

            //Y por supuesto de varificamos el contenido
            while(division.hasMoreElements())
            {
                //Si el contenido es igual a la parte de la cadena que se 
                //requiere obtener (tomando en cuenta el ajuste..)
                if( (coin+1)==numeroDivisionAObtener)
                {
                    CadenaOriginal=""+division.nextToken();
                }
                if( (coin+1)!=numeroDivisionAObtener)
                {
                    division.nextToken();
                }
                
                //Ajustamos la variable de control
                coin++;
            }
        }
        
        //Finalmente retornamos los resultados
        return CadenaOriginal;
    }
    
    public int ObtenerNumeroDeDivisiones(String CadenaOriginal, String CadenaDivisor)
    {
        //Variables
        String resultado="";
        int coin=1;
        
        //Recorremos mientras algo todavía obtengamos diferente de ""
        while(!this.ObtenerDivisionEspecificadaDeLaCadenaPorLaCifra(coin, CadenaOriginal, CadenaDivisor).equals(""))
        {
            coin++;
        }
        
        //Ajustamos 
        --coin;
        
        //Retornamos
        return coin;
    }
    
    public String ObtenerUltimaDivisionEspecificadaDeLaCadenaPorLaCifra(String CadenaOriginal, String CadenaDivisor)
    {
        //Declaración de variables
        StringTokenizer division=null;
        int coin=0;
        
        //Si es que contiene algo la cadena..
        if(!CadenaOriginal.equals(""))
        {
            //Despues ya me encargo con tokenizer de realiar la division,
            //Aunque bueno, en este caso más bien lo que pretendemos es 
            //quitar símbolos en especial
            division = new StringTokenizer(CadenaOriginal,CadenaDivisor);

            //Inicializamos el valor de la cadenaOriginal
            CadenaOriginal="";

            //Y por supuesto de varificamos el contenido
            while(division.hasMoreElements())
            {
                CadenaOriginal=division.nextToken();
            }
        }
        
        //Finalmente retornamos los resultados
        return CadenaOriginal;
    }
    
    public String UnirCadenas(ArrayList<String> Cadenas,String CadenaDivisor)
    {
        //Declaración de variables
        String cadenaUnificada="";
        
        //Simplemente procedemos a relizar la unión
        for(int k=0; k<Cadenas.size(); k++)
        {
            if(k!=Cadenas.size()-1)
            {
                cadenaUnificada+=Cadenas.get(k)+CadenaDivisor;
            }
            else if(k==Cadenas.size()-1)
            {
                cadenaUnificada+=Cadenas.get(k);
            }
        }
        
        //Retornamos el resultado
        return cadenaUnificada;
    }
    
    public ArrayList<String> SepararCadenas(String cadenaOriginal,String CadenaDivisor)
    {
        //Declaración de variables
        StringTokenizer division=null;
        ArrayList<String> ConjuntoSubcadenas=new ArrayList<String>(); 
        
        //Si es que contiene algo la cadena..
        if(!cadenaOriginal.equals(""))
        {
            //Despues ya me encargo con tokenizer de realiar la division,
            //Aunque bueno, en este caso más bien lo que pretendemos es 
            //quitar símbolos en especial
            division = new StringTokenizer(cadenaOriginal,CadenaDivisor);

            //Inicializamos el valor de la cadenaOriginal
            cadenaOriginal="";

            //Recorremos y anexamos subcadenas
            while(division.hasMoreElements())
            {
                ConjuntoSubcadenas.add(division.nextToken().trim());
            }
        }
        
        //Retornamos el resultado
        return ConjuntoSubcadenas;
    }
    
    public boolean VerificarExistenciaDeSubcadena(String Cadena, String Subcadena)
    {
        //Variables
        boolean existeSubcadena=true;
        
        //Realizamos directamente la verificación
        if(Cadena.indexOf(Subcadena)==-1) 
        {
            existeSubcadena=false;
        }
        
        //Retornamos el resultado
        return existeSubcadena;
    }
    
    public int ExtraerIndiceDeComienzoDeSubcadena(String Cadena, String Subcadena)
    {
        //Variables
        int indice;
        
        //Realizamos la extracción del índice de forma directa
        indice=Cadena.indexOf(Subcadena);
        
        //Retornamso el resultado
        return indice;
    }
    
    public String ExtraerSubcadenaDeterminadaPorIndices(String CadenaOriginal, int inicio, int fin)
    {
        //Variables
        String cadena;
        
        //Realizamos la operación
        cadena=CadenaOriginal.substring(inicio, fin);
        
        //Retornamos el resultado
        return cadena;
    }
    
    public boolean existePrefijoEnCadena(String cadenaOriginal, String cadenaPrefijo)
    {
        //Variables
        boolean existePrefijo;
        
        //Realizamos la operación
        existePrefijo=cadenaOriginal.startsWith(cadenaPrefijo);
        
        //Retornamos el resultado
        return existePrefijo;
    }
    
    public boolean laCadenaEsUnNumeroEntero(String cadena)
    {
        boolean esNumeroEntero=false;
        try{
            Integer.parseInt(cadena);
            esNumeroEntero=true;
        }
        catch(Exception e)
        {
            esNumeroEntero=false;
        }
        return esNumeroEntero;
    }
    public String agregarCadenaAlFinalNVeces(String cadenaOriginal, String cadenaAgregar, int n)
    {
        //Variables
        String cadenaFinal;
        
        //Inicializamos
        cadenaFinal=cadenaOriginal;
        
        //Añadimos tantas veces necesitemos
        for(int k=0; k<n; k++)
        {
            cadenaFinal+=cadenaAgregar;
        }
        
        //Retornamos
        return cadenaFinal;
    }
    public String completarCadenaConEspacios(String cadenaOriginal, int longitudEsperada)
    {
        //Variables
        int longCadOriginal;
        int faltante;
        
        //Calculamos
        longCadOriginal=cadenaOriginal.length();
        faltante=longitudEsperada-longCadOriginal;
        
        //Agregamos lo que falta
        cadenaOriginal=this.agregarCadenaAlFinalNVeces(cadenaOriginal, " ", faltante);
        
        //Retornamos
        return cadenaOriginal;
    }
    
//    public static void main(String[] args)
//    {
//        Utileria_Conversiones objUtileria_Conversiones=new Utileria_Conversiones();
//        String cadena="1: a,5 c,4";//cadena ejemplo
//        String resultado="";
//        
//        //Presentamos
//        System.out.println("cadena original:    "+cadena);
//        
//        //Obtener 1*:
//        resultado=objUtileria_Conversiones.ObtenerDivisionEspecificadaDeLaCadenaPorLaCifra(1, cadena, " ");
//        System.out.println(resultado);
//        
//        //Obtener a,5
//        resultado=objUtileria_Conversiones.ObtenerDivisionEspecificadaDeLaCadenaPorLaCifra(2, cadena, " ");
//        System.out.println(resultado);
//        
//        //Obtener c,4
//        resultado=objUtileria_Conversiones.ObtenerDivisionEspecificadaDeLaCadenaPorLaCifra(3, cadena, " ");
//        System.out.println(resultado);
//        
//        //Obtener a,5 y leer el a
//        resultado=objUtileria_Conversiones.ObtenerDivisionEspecificadaDeLaCadenaPorLaCifra(2, cadena, " ");
//        resultado=objUtileria_Conversiones.ObtenerDivisionEspecificadaDeLaCadenaPorLaCifra(1, resultado, ",");
//        System.out.println(resultado);
//        
//        //Obtener a,5 y leer el 5
//        resultado=objUtileria_Conversiones.ObtenerDivisionEspecificadaDeLaCadenaPorLaCifra(2, cadena, " ");
//        resultado=objUtileria_Conversiones.ObtenerDivisionEspecificadaDeLaCadenaPorLaCifra(2, resultado, ",");
//        System.out.println(resultado);
//        
//        //Te recomiendo que solo ocupemos esta clase para extracción de datos
//    }
}
