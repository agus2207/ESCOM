import java.util.Random;

public class IPN{
    public static void main(String[] args){
        String Cadenota = "";
        Random rnd = new Random();
        int r;
        for(int i = 0; i < 20000; i++){
            for(int j = 0; j < 3; j++){
                Cadenota += (char)(Math.random()*(90-65+1)+65);
            }
            Cadenota += " ";
        }
        System.out.println(Cadenota);
        boolean resultado = Cadenota.contains("IPN");
        int count = 0;
        if(resultado)
            count++;
        System.out.println("Se encontro la palabra IPN: "+count+" veces");
    }
}