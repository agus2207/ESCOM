import java.util.Scanner;

public class criba{

  public static void main(String[] args){

  int i,j;
  Array x = "";      
  int n;
  System.out.println("Introduzca un numero:");
  Scanner s = new Scanner (System.in);
  n = s.nextInt();
  boolean[] esPrimo = new boolean[n];
  esPrimo[0] = false;    
  for (i = 0; i< n ;i++) 
    esPrimo[i] = true;

    for(i = 2 ; i <= (n/2);i++)

      for(j = 2; j<=(n/i); j++) 

        esPrimo[i*j - 1] = false;

        j = 0;

        for(i=1;i<n;i++)

          if (esPrimo[i])  
            j++;

        System.out.println("Hasta "+n+" hay "+j+" primos");

        for(i=1;i<n;i++)

          if  (esPrimo[i])
          x[i]= i+1+" ";
          System.out.print(x[i]);
      }
    }
