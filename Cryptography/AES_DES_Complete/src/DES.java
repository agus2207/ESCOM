import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import javax.crypto.spec.IvParameterSpec;

public class DES {
    
    int HEADER_LENGTH = 14;  // 14 byte bmp header
    int INFO_HEADER_LENGTH = 40; // 40-byte bmp info header
    
    public void DES_Encrypt(String name, String key, String initvector, String mode) throws Exception{
        File f = new File(name);
        FileInputStream fs = new FileInputStream(name);
        
        byte header [] = new byte[HEADER_LENGTH];
        fs.read(header, 0, HEADER_LENGTH);
        
        byte infoheader [] = new byte[INFO_HEADER_LENGTH];
        fs.read(infoheader, 0, INFO_HEADER_LENGTH);
        
        byte[] content = new byte[fs.available()];
        fs.read(content);
        
        if (mode.equals("ECB")){
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5PADDING");
            Key secretKey = new SecretKeySpec(key.getBytes(), "DES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] imageModified = cipher.doFinal(content);
        
            writeToFile(header, infoheader, imageModified, "src/Imagenes/cifrado_des_ecb.bmp");
        }
        
        if (mode.equals("CBC")){
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5PADDING");
            Key secretKey = new SecretKeySpec(key.getBytes(), "DES");
            IvParameterSpec iv = new IvParameterSpec(initvector.getBytes()); 
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
            byte[] imageModified = cipher.doFinal(content);
        
            writeToFile(header, infoheader, imageModified, "src/Imagenes/cifrado_des_cbc.bmp");
        }
        
        if (mode.equals("CFB")){
            Cipher cipher = Cipher.getInstance("DES/CFB/PKCS5PADDING");
            Key secretKey = new SecretKeySpec(key.getBytes(), "DES");
            IvParameterSpec iv = new IvParameterSpec(initvector.getBytes()); 
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
            byte[] imageModified = cipher.doFinal(content);
        
            writeToFile(header, infoheader, imageModified, "src/Imagenes/cifrado_des_cfb.bmp");
        }
        
        if (mode.equals("OFB")){
            Cipher cipher = Cipher.getInstance("DES/OFB/PKCS5PADDING");
            Key secretKey = new SecretKeySpec(key.getBytes(), "DES");
            IvParameterSpec iv = new IvParameterSpec(initvector.getBytes()); 
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
            byte[] imageModified = cipher.doFinal(content);
        
            writeToFile(header, infoheader, imageModified, "src/Imagenes/cifrado_des_ofb.bmp");
        }
    }
    
    public void DES_Decrypt(String name, String key, String initvector, String mode) throws Exception{
        File f = new File(name);
        FileInputStream fs = new FileInputStream(name);
        
        byte header [] = new byte[HEADER_LENGTH];
        fs.read(header, 0, HEADER_LENGTH);
        
        byte infoheader [] = new byte[INFO_HEADER_LENGTH];
        fs.read(infoheader, 0, INFO_HEADER_LENGTH);
        
        byte[] content = new byte[fs.available()];
        fs.read(content);
        
        if (mode.equals("ECB")){
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5PADDING");
            Key secretKey = new SecretKeySpec(key.getBytes(), "DES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] imageModified = cipher.doFinal(content);
        
            writeToFile(header, infoheader, imageModified, "src/Imagenes/descifrado_des_ecb.bmp");
        }
        
        if (mode.equals("CBC")){
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5PADDING");
            Key secretKey = new SecretKeySpec(key.getBytes(), "DES");
            IvParameterSpec iv = new IvParameterSpec(initvector.getBytes()); 
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
            byte[] imageModified = cipher.doFinal(content);
        
            writeToFile(header, infoheader, imageModified, "src/Imagenes/descifrado_des_cbc.bmp");
        }
        
        if (mode.equals("CFB")){
            Cipher cipher = Cipher.getInstance("DES/CFB/PKCS5PADDING");
            Key secretKey = new SecretKeySpec(key.getBytes(), "DES");
            IvParameterSpec iv = new IvParameterSpec(initvector.getBytes()); 
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
            byte[] imageModified = cipher.doFinal(content);
        
            writeToFile(header, infoheader, imageModified, "src/Imagenes/descifrado_des_cfb.bmp");
        }
        
        if (mode.equals("OFB")){
            Cipher cipher = Cipher.getInstance("DES/OFB/PKCS5PADDING");
            Key secretKey = new SecretKeySpec(key.getBytes(), "DES");
            IvParameterSpec iv = new IvParameterSpec(initvector.getBytes()); 
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
            byte[] imageModified = cipher.doFinal(content);
        
            writeToFile(header, infoheader, imageModified, "src/Imagenes/descifrado_des_ofb.bmp");
        }
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
