import java.util.Scanner;
import java.io.*;

public class sumador{

	public static void main (String[] args){

		System.out.println("Ingrese el numero de sumas");
		Scanner s= new Scanner (System.in);
		int n = s.nextInt();
		int[] a = new int[10];
		int[] res = new int[10];
		int c=0;
		int b=1;
		for (int j=0;j<=n;j++){

			for (int i=n;i>=0;i--){

				if(a[i]+b+c==0){
				res[i]=0;
				c=0;
				}

				if(a[i]+b+c==1){
				res[i]=1;
				c=0;
				}

				if(a[i]+b+c==2){
				res[i]=0;
				c=1;
				}

				if(a[i]+b+c==3){
				res[i]=1;
				c=1;
				}

				
				System.out.println(res[i]);

			}
		}

	}
}