import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import javax.swing.JOptionPane;

public class hash_sha1 {
    
    public String sha1(String input) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] messageDigest = md.digest(input.getBytes());
        BigInteger no = new BigInteger(1, messageDigest);
        String hashtext = no.toString(16);
        while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
        } 
        return hashtext;
    }
    
    public boolean verify (String input, String hash) throws Exception{
        boolean result = false;
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] messageDigest = md.digest(input.getBytes());
        BigInteger no = new BigInteger(1, messageDigest);
        String hashtext = no.toString(16);
        while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
        } 
        if(hashtext.equals(hash)){
            result = true;
        }
        return result;
    }
    
    public void openfile(String archivo){
        try {
            File objetofile = new File (archivo);
            Desktop.getDesktop().open(objetofile);
        }catch (IOException ex) {
            String exception = ex.toString();
            JOptionPane.showMessageDialog(null, exception);
        }
    }
}
