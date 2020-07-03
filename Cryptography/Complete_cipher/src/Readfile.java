import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Readfile {

    String llave;
    
    public String read(String file) throws Exception {
        String cadena;
        String resultado = "";
        FileReader f = new FileReader(file);
        BufferedReader b = new BufferedReader(f);
        while ((cadena = b.readLine()) != null) {
            resultado = resultado + cadena + "\n";
        }
        resultado = resultado.substring(0, resultado.length()-1);
        b.close();
        return resultado;
    }
    
    public String read3(String file) throws Exception {
        String cadena;
        String resultado = "";
        FileReader f = new FileReader(file);
        BufferedReader b = new BufferedReader(f);
        while ((cadena = b.readLine()) != null) {
            resultado = resultado + cadena + "\r\n";
        }
        resultado = resultado.substring(0, resultado.length()-1);
        b.close();
        return resultado;
    }
    
    public int contar (String file) throws Exception{
        String cadena;
        int count = 0;
        FileReader f = new FileReader(file);
        BufferedReader b = new BufferedReader(f);
        while ((cadena = b.readLine()) != null) {
            count++;
        }
        b.close();
        return count;
    }
    
    public ArrayList<String> resandkey(String file, int count) throws Exception{
        ArrayList <String> parametros = new ArrayList();
        String cadena;
        String resultado = "";
        String llave = "";
        int aux = 0;
        count--;
        FileReader f = new FileReader(file);
        BufferedReader b = new BufferedReader(f);
        while ((cadena = b.readLine()) != null) {
            aux++;
            if (aux <= count) {
                resultado = resultado + cadena + "\n";
            } 
            else {
                llave += cadena;
            }
        }
        resultado = resultado.substring(0, resultado.length()-2);
        b.close();
        parametros.add(resultado);
        parametros.add(llave);
        return parametros;
    }  
    
    public String read2(String file) throws Exception {
        String cadena;
        String resultado = "";
        FileReader f = new FileReader(file);
        BufferedReader b = new BufferedReader(f);
        while ((cadena = b.readLine()) != null) {
            resultado += cadena;
        }
        b.close();
        return resultado;
    }
    
    public String write(String file) throws Exception {
        String cadena;
        String resultado = "";
        FileReader f = new FileReader(file);
        BufferedReader b = new BufferedReader(f);
        while ((cadena = b.readLine()) != null) {
            resultado += cadena + "\r\n";
        }
        b.close();
        return resultado;
    }
}
