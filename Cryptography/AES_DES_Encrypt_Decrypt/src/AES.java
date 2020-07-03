import java.awt.Desktop;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Key;

public class AES {
    
    int HEADER_LENGTH = 14;  // 14 byte bmp header
    int INFO_HEADER_LENGTH = 40; // 40-byte bmp info header
    
    public void AES_Encrypt(String name, String key) throws Exception{ 
        File f = new File(name);
        FileInputStream fs = new FileInputStream(name);
        
        byte header [] = new byte[HEADER_LENGTH];
        fs.read(header, 0, HEADER_LENGTH);
        
        byte infoheader [] = new byte[INFO_HEADER_LENGTH];
        fs.read(infoheader, 0, INFO_HEADER_LENGTH);
        
        byte[] content = new byte[fs.available()];
        fs.read(content);
        
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        Key secretKey = new SecretKeySpec(key.getBytes(), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] imageModified = cipher.doFinal(content);
        
        writeToFile(header, infoheader, imageModified, "src/Imagenes/cifrado_aes.bmp");
        
    }
    
    public void AES_Decrypt(String name, String key) throws Exception{ 
        File f = new File(name);
        FileInputStream fs = new FileInputStream(name);
        
        byte header [] = new byte[HEADER_LENGTH];
        fs.read(header, 0, HEADER_LENGTH);
        
        byte infoheader [] = new byte[INFO_HEADER_LENGTH];
        fs.read(infoheader, 0, INFO_HEADER_LENGTH);
        
        byte[] content = new byte[fs.available()];
        fs.read(content);
        
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        Key secretKey = new SecretKeySpec(key.getBytes(), "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] imageModified = cipher.doFinal(content);
        
        writeToFile(header, infoheader, imageModified, "src/Imagenes/descifrado_aes.bmp");
        
    }
    
    public void writeToFile(byte[] header, byte [] infoheader, byte [] content, String fileToWrite) throws Exception{
        FileOutputStream fos = new FileOutputStream(fileToWrite);
        fos.write(header);
        fos.write(infoheader);
        fos.write(content);
        fos.flush();
        fos.close();    
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
