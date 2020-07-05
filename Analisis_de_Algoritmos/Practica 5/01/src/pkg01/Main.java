package pkg01;
import java.io.FileWriter;
import java.util.Scanner;
import java.lang.*;
import java.util.Random;

public class Main {
    
    FileWriter f, f1;
    
    public void coeficientes() throws Exception{
    
        int a;
        System.out.println("Introduzca el tamaño del arreglo:");
        Scanner s = new Scanner(System.in);
        a = s.nextInt();
        int array[] = new int[a+1];
        System.out.println("Introduzca los elementos del arreglo:");
        for (int i = 0; i < a; i++){
            Scanner t = new Scanner(System.in);
            int b = t.nextInt();
            array[i] = b;
        }
        random(a, array);
    }
    
    public void random(int a, int array[]) throws Exception{
        
        int array2[] = new int[a+1];
        for (int i = 0; i < a+1; i++){
            Random rn = new Random();
            int b = rn.nextInt(11) -5;
            array2[i] = b;
        }
        ejey(a, array, array2);
    }
    
    public void ejey(int a, int array1[], int array2[]) throws Exception{
        
        int array3[] = new int[a+1];
        for(int i = 0; i < a+1; i++){
            for(int j = 0; j < a; j++){
                if (j == 0){
                    array3[i] = array1[j];
                }
                else{
                    int p = (int)Math.pow(array2[i], j);
                    array3[i] = (p*array1[j])+array3[i];
                }
            }
        }
        puntos(a, array2, array3);
    }
    
    public void puntos(int a, int array2[], int array3[]) throws Exception{
        
        f = new FileWriter("Pasos.txt");
        f.write("Puntos Generados");
        f.write("\r\n");
        for(int i = 0; i < a+1; i++){
            System.out.println("("+array2[i]+", "+array3[i]+")");
            f.write("("+array2[i]+", "+array3[i]+")");
            f.write("\r\n");
        }
        f.close();
    }

    public static void main(String[] args) throws Exception {
        
        Main m = new Main();
        m.coeficientes();
    }
    
}
