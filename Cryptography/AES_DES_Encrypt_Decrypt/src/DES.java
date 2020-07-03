import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;

public class DES {
    
    int HEADER_LENGTH = 14;  // 14 byte bmp header
    int INFO_HEADER_LENGTH = 40; // 40-byte bmp info header
    
    public void DES_Encrypt(String name, String key) throws Exception{
        File f = new File(name);
        FileInputStream fs = new FileInputStream(name);
        
        byte header [] = new byte[HEADER_LENGTH];
        fs.read(header, 0, HEADER_LENGTH);
        
        byte infoheader [] = new byte[INFO_HEADER_LENGTH];
        fs.read(infoheader, 0, INFO_HEADER_LENGTH);
        
        byte[] content = new byte[fs.available()];
        fs.read(content);
        
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5PADDING");
        Key secretKey = new SecretKeySpec(key.getBytes(), "DES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] imageModified = cipher.doFinal(content);
        
        writeToFile(header, infoheader, imageModified, "src/Imagenes/cifrado_des.bmp");
    }
    
    public void DES_Decrypt(String name, String key) throws Exception{
        File f = new File(name);
        FileInputStream fs = new FileInputStream(name);
        
        byte header [] = new byte[HEADER_LENGTH];
        fs.read(header, 0, HEADER_LENGTH);
        
        byte infoheader [] = new byte[INFO_HEADER_LENGTH];
        fs.read(infoheader, 0, INFO_HEADER_LENGTH);
        
        byte[] content = new byte[fs.available()];
        fs.read(content);
        
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5PADDING");
        Key secretKey = new SecretKeySpec(key.getBytes(), "DES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] imageModified = cipher.doFinal(content);
        
        writeToFile(header, infoheader, imageModified, "src/Imagenes/descifrado_des.bmp");
    }
    
    public void writeToFile(byte[] header, byte [] infoheader, byte [] content, String fileToWrite) throws Exception{
        FileOutputStream fos = new FileOutputStream(fileToWrite);
        fos.write(header);
        fos.write(infoheader);
        fos.write(content);
        fos.flush();
        fos.close();    
    }
}
