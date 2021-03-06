import java.util.Scanner;

public class criba2{

	public static String toBinary(int number) {
        int exp, binary, digit;
        String stringBinary;

        exp = 0;
        binary = 0;
        while (number != 0) {
            digit = number % 2;
            binary = (int) (binary + digit * Math.pow(10, exp));
            exp++;
            number = number / 2;
        }
        stringBinary = binary + "";
        char[] arrayBinary = stringBinary.toCharArray();

        return stringBinary;
    }


	public static void main(String[] args){

	 	int i,j;      
	 	int n;
	 	String x = "";
	 	String b = "";
  		System.out.println("Introduzca un numero:");
  		Scanner s = new Scanner (System.in);
  		n = s.nextInt();
  		boolean[] esPrimo = new boolean[n];
  		esPrimo[0] = false;

  		for (i = 0; i< n ;i++){

  			esPrimo[i] = true;
  		}

  		for(i = 2 ; i <= (n/2);i++){

  			for(j = 2; j<=(n/i); j++){

  				esPrimo[i*j-1] = false;
  			}
  		}

  		j=0;

  		for(i=1;i<n;i++){

  			if (esPrimo[i]){

  				j++;

  			}  			
  		}

		System.out.println("Hasta "+n+" hay "+j+" primos");

  		for(i=1;i<n;i++){

  			if  (esPrimo[i]){
  				x=i+1+",";
  				System.out.print(x); 				
  			}
  		}

  		System.out.println("\n");

  		for(j=0;j<n;j++){

  			if (esPrimo[j]){
  				b=toBinary(j);
  				System.out.print(b +",");
  			}
  		}
	 }
}