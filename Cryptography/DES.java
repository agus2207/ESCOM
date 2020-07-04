import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class DES {
    
    int headerSize = 54;
    
    public void DES_Encrypt(String name, String key){
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "DES");
            //IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8")); 
            
            //AlgorithmParameterSpec algorithmParameterSpec = iv;
            Cipher encCBC = Cipher.getInstance("DES/ECB/PKCS5PADDING"); //Debe ser una clave de 8 cambiarlo a CBC para iv
            encCBC.init(Cipher.ENCRYPT_MODE, skeySpec); // como tercer parametro colocar iv
            
            
            BufferedImage img = ImageIO.read(new File(name));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    ImageIO.write( img, "bmp", baos );
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();

            byte[] finalBytes = new byte[(int)imageInByte.length];
            byte[] encryptedBytes = new byte[(int)imageInByte.length-headerSize];
            
            for(int i = 0, j=0 ; i < imageInByte.length; i++) {
                if(i < headerSize) 
                    finalBytes[i] = imageInByte[i];
		else 
                    encryptedBytes[j++] = imageInByte[i];				
            }
            byte[] imageModified = encCBC.doFinal(encryptedBytes);
			
            for(int i = headerSize, j = 0; i < imageInByte.length; i++, j++) 
                finalBytes[i] = imageModified[j];
            
            InputStream in = new ByteArrayInputStream(finalBytes);
            BufferedImage bImageFromConvert = ImageIO.read(in);
            ImageIO.write(bImageFromConvert, "bmp", new File("src/Imagenes/cifrado_des.bmp"));
            JOptionPane.showMessageDialog(null, "DES Encrypt successful\ncifrado_des.bmp file create");      
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Key or IV invalid\nTry with a 8 bits keys or IV");
        }  
    }
    
    public void DES_Decrypt(String name, String key){
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "DES");
            //IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8")); 
            
            //AlgorithmParameterSpec algorithmParameterSpec = iv;
            Cipher decCBC = Cipher.getInstance("DES/ECB/PKCS5PADDING"); //Debe ser una clave de 8 cambiarlo a CBC para iv
            decCBC.init(Cipher.DECRYPT_MODE, skeySpec); // como tercer parametro colocar iv
            
            BufferedImage img2 = ImageIO.read(new File(name));
            ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
            ImageIO.write( img2, "bmp", baos2 );
            baos2.flush();
            byte[] imageInByte2 = baos2.toByteArray();
            baos2.close();
            
            byte[] finalBytes2 = new byte[(int)imageInByte2.length];
            byte[] encryptedBytes2 = new byte[(int)imageInByte2.length-headerSize];
			
            for(int i = 0, j=0 ; i < imageInByte2.length; i++) {
                if(i < headerSize) 
                    finalBytes2[i] = imageInByte2[i];
                else 
                    encryptedBytes2[j++] = imageInByte2[i];				
            }	
            byte[] imageModified2 = decCBC.update(encryptedBytes2);
            
            for(int i = headerSize, j = 0; i < imageModified2.length; i++, j++) 
                finalBytes2[i] = imageModified2[j];
            			
            InputStream in2 = new ByteArrayInputStream(finalBytes2);
            BufferedImage bImageFromConvert2 = ImageIO.read(in2);
            ImageIO.write(bImageFromConvert2, "bmp", new File("src/Imagenes/descifrado_des.bmp"));
            JOptionPane.showMessageDialog(null, "DES Encrypt successful\ndescifrado_des.bmp file create");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Key or IV invalid\nTry with a 8 bits keys or IV");
        }
    }
}