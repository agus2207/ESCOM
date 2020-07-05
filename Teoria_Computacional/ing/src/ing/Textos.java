package ing;
import java.io.*;

public class Textos {
    
    String temp1;
    
    String leer (String nombreArchivo){
        
        try{
            
            FileReader r = new FileReader(nombreArchivo);
            BufferedReader b = new BufferedReader(r);
            String temp = "";
            
            while (temp != null){
                
                temp = b.readLine();
                if (temp == null)
                    break;
                temp1 = temp;
            }
            return temp1;
            
        }catch (Exception e){
                
                System.out.println(e.getMessage());
            }
    return temp1;
    } 
}
