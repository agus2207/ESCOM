import java.awt.Desktop;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class AES {
    
    int headerSize = 54;
    
    public void AES_Encrypt(String name, String key){ 
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            //IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            
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
            byte[] imageModified = cipher.doFinal(encryptedBytes);			
            for(int i = headerSize, j = 0; i < imageInByte.length; i++, j++) 
		finalBytes[i] = imageModified[j];
			
            InputStream in = new ByteArrayInputStream(finalBytes);
            BufferedImage bImageFromConvert = ImageIO.read(in);
            ImageIO.write(bImageFromConvert, "bmp", new File("src/Imagenes/cifrado_aes.bmp"));
            JOptionPane.showMessageDialog(null, "AES Encrypt successful\ncifrado.aes.bmp file create");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Key or IV invalid\nTry with a 16 bits keys or IV");
        }
    }
    
    public void AES_Decrypt(String name, String key){
        try {
            
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            //IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            Cipher decCBC = Cipher.getInstance("AES/ECB/NoPadding");
	    decCBC.init(Cipher.DECRYPT_MODE, skeySpec);      
             
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
            ImageIO.write(bImageFromConvert2, "bmp", new File("src/Imagenes/descifrado_aes.bmp")); 
            JOptionPane.showMessageDialog(null, "AES Decrypt successful\ndescifrado_aes.bmp file create");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Key or IV invalid\nTry with a 16 bits keys or IV");
        }
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
