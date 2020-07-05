package expresiones;
import java.io.*;
import java.util.Scanner;

public class Expresiones {
    
    public static void main(String[] args) throws Exception {
        
        Exp1 E1= new Exp1();
        Exp2 E2= new Exp2();
        int a;
        Scanner s = new Scanner(System.in);
        
         do {

            E1.Menu();
            a = s.nextInt();

            if (a >= 3 || a < 1) {

                System.exit(0);
            }

            switch (a) {

                case 1:

                    System.out.print("Sus expresiones generadas son:\n");
                    E1.kleene();
                    break;

                case 2:

                    System.out.print("Sus expresiones generadas son:\n");
                    E2.kleene();
                    break;
            }

            System.out.println("\n\n");

        } while (a != 3);
         
    }
    
}
