import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Decrypt {
    
    FileWriter f1;
    
    public void reedfile (String file, int invalpha, int invbeta) throws FileNotFoundException, IOException, Exception{
        f1 = new FileWriter("src/Decrypt.txt");
        String cadena;
        FileReader f = new FileReader(file);
        BufferedReader b = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
        while((cadena = b.readLine())!=null) {
            
            for(int i = 0;  i < cadena.length(); i++){
                char letra = cadena.charAt(i);
                writefile(letra, invalpha, invbeta);
            }
            f1.write("\r\n");
        }
        
        b.close();
        f1.close();
    } 
    
    public void writefile(char c, int invalpha, int invbeta) throws Exception{
        Extend e = new Extend();
        char nc;
        int ext = e.getAsciiCode(c);
        System.out.println(c+" "+ext);
        int ascii = (invalpha*(ext+invbeta))%256;
        nc = e.newchar(ascii);
        System.out.println(nc+" "+ascii);
        f1.write(nc); 
    }
}