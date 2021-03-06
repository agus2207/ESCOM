import java.util.*;
import java.util.Scanner;

public class Bin {

	public static void main(String[] args){

		String num= new String("001");
		String num2= new String("100");
		int cadena[] = new int [20];
		int res[] = new int [20];
		int cad[] = new int [20];
		int cadr[]= new int [20];
		int x, y, i;
		Scanner s= new Scanner (System.in);
		System.out.println("Ingrese numero de bytes");
		int n = s.nextInt();
		for(int j=0; j<Math.pow(2,n); j++){
			
			for (i=0; i<num.length();i++){

				if(num.charAt(i)==48){

					cadena[i]=0;
				}

				else {

					cadena[i]=1;
				}
			}

			x=num.length();

			for (i=0; i<num2.length();i++){

				if(num.charAt(i)==48){

					cad[i]=0;
				}

				else {

					cad[i]=1;
				}
			}
			y=num2.length();
			int k=0;
			for (i=0; i<x; i++){

				if(i<(x-y)){

					cadr[i]=0;

				}

				else{

					cadr[i]=cad[k];
					k++;
				}
			}

			for(i=0; i<x; i++){

				System.out.println(cadena[i]+" ");
			}
			int acarreo=0;
			for (i=x-1;i>=0;i--){

				if(acarreo==1){

					cadena[i]=cadena[i]+acarreo;
					if (cadena[i]==2){

						acarreo=1;
						cadena[i]=0;
					}
				}

				res[i+1]=cadena[i]+cad[i];
				if(res[i+1]==2){

					acarreo=1;
					res[i+1]=0;
				}

				else{

					acarreo=0;
				}
			}

			if (acarreo==1){

				res[0]=acarreo;
			}

			for(i=0; i<x+1;i++){

				System.out.println(res[i] +"  ");
			}
		}
	}
}