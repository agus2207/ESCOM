import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Encrypt {
    
    FileWriter f1;
    
    public void reedfile (String file, int number) throws FileNotFoundException, IOException, Exception{
        f1 = new FileWriter("src/Encrypt.txt");
        String cadena;
        FileReader f = new FileReader(file);
        BufferedReader b = new BufferedReader(new InputStreamReader(new FileInputStream(file), "ISO-8859-1"));
        while((cadena = b.readLine())!=null) {
            //String s = new String(cadena.getBytes("UTF-8"),"ISO-8859-1");
            //String prueba = new String(s.getBytes("ISO-8859-1"),"UTF-16"); 
            //System.out.println("UTF "+s);
            //System.out.println("Back "+prueba);
            //System.out.println("Original "+cadena);
            for(int i = 0;  i < cadena.length(); i++){
                char letra = cadena.charAt(i);
                int a= (int) letra;
                //System.out.println(letra+" "+a);
                writefile(letra, number);
            }
            f1.write("\r\n");
        }
        b.close();
        f1.close();
    } 
    
    public void writefile(char c, int number) throws Exception{
        Extend e = new Extend();
        int ext = e.getAsciiCode(c);
        //System.out.println(c+" "+ext);
        int ascii = (ext + number)%256;
        char nc = e.newchar(ascii);
        //System.out.println(nc+" "+ascii);
        f1.write(nc);
    }
    
    public void openfile(String archivo){
        try {
            File objetofile = new File (archivo);
            Desktop.getDesktop().open(objetofile);
        }catch (IOException ex) {
            System.out.println(ex); 
        }
    }
}
