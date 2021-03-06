import java.util.Scanner;

class Main {

	public static void main (String[] args) {
		
		int x [][];
		x = new int[3][3];
		Scanner s= new Scanner (System.in);

		for(int i=0; i<3; i++)
		{
			for (int j=0; j<3; j++)
			{

				System.out.println("Ingrese numeros");
				x[i][j] = s.nextInt();
				
			}
		}

		System.out.println("los numeros almacenados son");

		for(int i=0; i<3; i++)
		{
			for (int j=0; j<3; j++)
			{

				System.out.println("x["+i+"]["+j+"]"+x[i][j]);
			}
		}


	}
}