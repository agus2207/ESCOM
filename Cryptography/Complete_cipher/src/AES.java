import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.spec.IvParameterSpec;

public class AES {
    
    private IvParameterSpec ivParameterSpec;
    
    public String AES_Encrypt(String info, String key) throws Exception{ 

        byte[] encryptedBytes;
        
        int ivSize = 16;
        byte[] iv = new byte[ivSize];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        ivParameterSpec = new IvParameterSpec(iv);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        Key secretKey = new SecretKeySpec(key.getBytes(), "AES");
        //this.iv = new IvParameterSpec(this.generateIv());
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
        encryptedBytes = cipher.doFinal(info.getBytes());

        return bytesToString(encryptedBytes);
    }
    
    public String AES_Decrypt(String info, String key, String initialvector) throws Exception {

        byte[] decryptedBytes;

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        Key secretKey = new SecretKeySpec(key.getBytes(), "AES");
        IvParameterSpec iv = new IvParameterSpec(stringToBytes(initialvector));
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        decryptedBytes = cipher.doFinal(stringToBytes(info));
        return new String(decryptedBytes);
    }
    
    public String initialvector(){
        String initialv = "";
        byte[] iv = ivParameterSpec.getIV();
        initialv = bytesToString(iv);
        return initialv;
    }
    
    public String bytesToString(byte[] b) {
        byte[] b2 = new byte[b.length + 1];
        b2[0] = 1;
        System.arraycopy(b, 0, b2, 1, b.length);
        return new BigInteger(b2).toString(36);
    }

    public byte[] stringToBytes(String s) {
        byte[] b2 = new BigInteger(s, 36).toByteArray();
        return Arrays.copyOfRange(b2, 1, b2.length);
    }
}
