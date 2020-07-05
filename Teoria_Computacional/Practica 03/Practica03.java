import java.io.*;
import java.util.*;
import java.util.Scanner;

public class Practica03 {

	int cont, pal;  
    char [] car; 

	public static void main (String[] args) throws IOException {

		Practica03 p = new Practica03();
		int a, opcion;
		String cadena;
		Scanner s= new Scanner (System.in);
		Scanner t= new Scanner (System.in); 
		
		do{

			p.Menu();
			a = s.nextInt();

			if(a>=3||a<1){

				System.exit(0);
			}

			 switch(a){

				case 1:
				System.out.print("Ingresar cadena:");
				cadena = t.nextLine();
				p.car=cadena.toCharArray();
				p.inicio();
				break;

				case 2:
				System.out.println("Ingrese nombre del archivo:");
				//k = p.random();
				//System.out.println(""+k+"  ");
				p.inicio();
				break;
			}

			System.out.println("\n\n\n\n"); 

		} while(a!=3);
	}

	public static int random() {

        int random;
        int limite=100000;
        random =  (int) (Math.random() * limite) + 1 ;
        return random;
    }
    
    public static void Menu() {

        System.out.println("Bienvenido ");
        System.out.println("Por favor seleccione una opcion");
        System.out.println("1.- Manual ");
        System.out.println("2.- Leer Archivo ");
        System.out.println("3.- Salir ");
        System.out.print("Opcion: ");
    }

    public void inicio() { //metodo
        cont = 0; 
        pal = 0;
        //aceptada = false; 
        q0();  //irnos al codigo 0   
    }

    public void q0() {  

        //String w = String.valueOf(car[cont]);
        String k = "";

        if(cont < car.length) { //cuantos espacios tiene mi arreglo 

            if(car[cont]!='i') { //el arreglo car en el contador 0 lo vamos a comparar si es = a 

                String a = String.valueOf(car[cont]);
                k="q0-----> ";
                k=k.concat(a);
                k=k.concat(" ----->q0");
                cont++; //incrememnto mi contador 
                escribir(k);
                q0();  //si no lo dejo en 0 
            }

            else if (car[cont]=='i') { //si es una b me muevo al q1 
                
                String c = String.valueOf(car[cont]);
                k="q0-----> ";
                k=k.concat(c);
                k=k.concat(" ----->q1");
                cont++; //incrememnto mi contador 
                escribir(k);
                q1(); //nos va a mover al estado uno 
            }  
        } 

        else if (cont == car.length) {

            Nel();
        }
    }

    public void q1() {

        //String w = String.valueOf(car[cont]);
        String k = "";
         
        if(cont < car.length) {  //cuantos espacios tiene mi arreglo

            if(car[cont]!='i'&&car[cont]!='n') {  //el arreglo car en el contador 0 lo vamos a comparar si es = a*

                String e = String.valueOf(car[cont]);
                k ="q1-----> ";
                k=k.concat(e);
                k=k.concat(" ----->q0");
                cont++;
                escribir(k);
                q0(); 
            }

            else if (car[cont]=='i') { 
                    
                String e = String.valueOf(car[cont]);
                k ="q1-----> ";
                k=k.concat(e);
                k=k.concat(" ----->q1");
                cont++;
                escribir(k);
                q1();  //nos va a mover al estado uno
            }

            else if (car[cont]=='n') {

                String e = String.valueOf(car[cont]);
                k="q1-----> ";
                k=k.concat(e);
                k=k.concat(" ----->q2");
                cont++;
                escribir(k);
                q2(); //nos va a mover al estado uno 
            } 
        } 

        else if (cont == car.length) {

            Nel();
        }
    }

    public void q2() { 

        //String w = String.valueOf(car[cont]);
        String k = ""; 

        if(cont < car.length) {  //cuantos espacios tiene mi arreglo

            if(car[cont]!='i'&&car[cont]!='g') {  //el arreglo car en el contador 0 lo vamos a comparar si es = a

                String  g= String.valueOf(car[cont]);
                k="q2-----> ";
                k=k.concat(g);
                k=k.concat(" ----->q0");
                cont++;
                escribir(k);
                q0(); 
            }

            else if (car[cont]=='i') { 
                
                String g = String.valueOf(car[cont]);
                k="q2-----> ";
                k=k.concat(g);
                k=k.concat(" ----->q1");
                cont++;
                escribir(k);
                q1();  //nos va a mover al estado uno 
            }

            else if (car[cont]=='g') { 

                String g = String.valueOf(car[cont]);
                k="q2-----> ";
                k=k.concat(g);
                k=k.concat(" ----->q3");
                cont++; 
                escribir(k);
                q3();  //nos va a mover al estado uno 
            } 
        }

        else if (cont == car.length) {

            Nel();
        } 
    } 

    public void q3() {

        //String w = String.valueOf(car[cont]);
        String k = "";

        if(cont == car.length) {

            pal++;
            System.out.println("\n");
            System.out.println("Cadena aceptada");
            System.out.println("\n");
            System.out.println("Cadenas aceptadas: " +pal);
            System.out.println("\n");
            System.out.println("Fin del Automata");

        }
        
        else if(cont < car.length) {  //cuantos espacios tiene mi arreglo

            if(car[cont]!='i'&&car[cont]!=' ') {  //el arreglo car en el contador 0 lo vamos a comparar si es = a

                String w = String.valueOf(car[cont]);
                k="q3-----> ";
                k=k.concat(w);
                k=k.concat(" ----->q0");
                cont++; 
                escribir(k);
                q0(); 
            }

            else if (car[cont]=='i') { 

                String w = String.valueOf(car[cont]);
                k="q3-----> ";
                k=k.concat(w);
                k=k.concat(" ----->q1");
                cont++;
                escribir(k); 
                q1();           
            }

            else if (car[cont]=='.'||car[cont]==' '||car[cont]==',') {
                
                System.out.println("\n");
                System.out.println("Cadena aceptada");
                pal++;
                System.out.println("\n"); 
                String w = String.valueOf(car[cont]);
                k="q3-----> ";
                k=k.concat(w);
                k=k.concat(" ----->q0");
                //System.out.println("\n"); 
                cont++;
                escribir(k);
                q0(); 
            }
        } 
    } 

    public void Nel() {

        System.out.println("\n");
        String k = "No se acepto ninguna cadena";
        System.out.println("\n");
        String w = "Fin del Automata";
    }

    public void escribir(String k) {

    	File f;
    	f = new File ("Proceso.txt");

    	try {
    
           /* if (f.exists()) {

                if (f.isFile()) {

                    System.out.println("Ya existe el archivo");
                    f.delete();
                    System.out.println("Archivo eliminado: " + (!f.delete()));
                }

            } else {

                f.delete();
                System.out.println("Archivo creado. ");
            }*/

            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);         
            pw.write("Inicio en q0");
            pw.write("\r\n");
			pw.write(k);
            pw.close();
   

        } catch (IOException e) {

            System.out.println("Error: Archivo sin guardar.");
            e.getMessage();
        }
    }
}