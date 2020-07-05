import java.util.Scanner;

public class principal {

    public static void main(String[] args) {
        Scanner entrada= new Scanner(System.in);
        algoritmos obj = new algoritmos();
        System.out.println("Selecciona el algoritmo \n 1)Maximo Comun Divisor \n 2)Exponeciacion modular");
        int opc=entrada.nextInt();
        switch(opc){
            case 1:
                System.out.println("Ingresa el numero a");
                int a=entrada.nextInt();
                System.out.println("Ingresa el numero b");
                int b=entrada.nextInt();
                int mcd=obj.MCD(a,b);
                System.out.println("El Maximo Comun Divisor es:"+mcd);
                break;
            case 2:
                System.out.println("Ingresa el numero a");
                int a2=entrada.nextInt();
                System.out.println("Ingresa el numero b");
                int b2=entrada.nextInt();
                System.out.println("Ingresa el modulo m");
                int m=entrada.nextInt();
                int exp=obj.EXP(a2,b2,m);
                System.out.println("La exponeciacion modular es:"+exp);
                break;
            default:
                System.out.println("Ingresa una opcion valida");
                break;
        }

    }
    
}
