import java.util.Scanner;

public class Automata { 

    int cont, pal; 
    boolean aceptada;  //para guardar los caratcteres y los va ir separando*/ 
    char [] car; 

    public static void main(String[]args) { 
        Automata aut= new Automata(); 
        System.out.println("Ingrese cadena:");
        Scanner s= new Scanner (System.in);
        String cadena = s.nextLine();
        System.out.println("\n");
        System.out.println("Inicio en q0");
        System.out.println("\n");
        aut.car=cadena.toCharArray(); 
        aut.inicio(); 
    }

    public void inicio() { //metodo
        cont = 0; 
        pal = 0;
        //aceptada = false; 
        q0();  //irnos al codigo 0   
    }

    public void q0() {  

        //System.out.println("En q0");  //imprimimos y decimos que estamos en q0

        if(cont < car.length) { //cuantos espacios tiene mi arreglo 

            if(car[cont]!='i') { //el arreglo car en el contador 0 lo vamos a comparar si es = a 

                System.out.println("q0-----> "+car[cont]+" ----->q0");
                cont++; //incrememnto mi contador 
                q0();  //si no lo dejo en 0 
            }

            else if (car[cont]=='i') { //si es una b me muevo al q1 
                
                System.out.println("q0-----> "+car[cont]+" ----->q1");
                cont++;
                q1(); //nos va a mover al estado uno 
            }  
        } 

        else if (cont == car.length) {

            Nel();
        }
    }

    public void q1() {

        //System.out.println("En q1");
         
        if(cont < car.length) {  //cuantos espacios tiene mi arreglo

            if(car[cont]!='i'&&car[cont]!='n') {  //el arreglo car en el contador 0 lo vamos a comparar si es = a*

                System.out.println("q1-----> "+car[cont]+" ----->q0");
                cont++;
                q0(); 
            }

            else if (car[cont]=='i') { 
                    
                System.out.println("q1-----> "+car[cont]+" ----->q1");
                cont++;
                q1();  //nos va a mover al estado uno
            }

            else if (car[cont]=='n') {

                System.out.println("q1-----> "+car[cont]+" ----->q2");
                cont++;
                q2(); //nos va a mover al estado uno 
            } 
        } 

        else if (cont == car.length) {

            Nel();
        }
    }

    public void q2() { 

        //System.out.println("En q2"); 

        if(cont < car.length) {  //cuantos espacios tiene mi arreglo

            if(car[cont]!='i'&&car[cont]!='g') {  //el arreglo car en el contador 0 lo vamos a comparar si es = a

                System.out.println("q2-----> "+car[cont]+" ----->q0");
                cont++;
                q0(); 
            }

            else if (car[cont]=='i') { 
                
                System.out.println("q2-----> "+car[cont]+" ----->q1");
                cont++;
                q1();  //nos va a mover al estado uno 
            }

            else if (car[cont]=='g') { 

                System.out.println("q2-----> "+car[cont]+" ----->q3");
                cont++; 
                q3();  //nos va a mover al estado uno 
            } 
        }

        else if (cont == car.length) {

            Nel();
        } 
    } 

    public void q3() {

        //System.out.println("En q3");

        if(cont == car.length) {

            pal++;
            System.out.println("\n");
            System.out.println("Cadena aceptada");
            //for (int i=cont; i<=car[cont];i++) {

                String w = String.valueOf(car);
                System.out.println(w);
            //}
            System.out.println("\n");
            System.out.println("Cadenas aceptadas: " +pal);
            System.out.println("\n");
            System.out.println("Fin del Automata");

        }
        
        else if(cont < car.length) {  //cuantos espacios tiene mi arreglo

            if(car[cont]!='i'&&car[cont]!=' ') {  //el arreglo car en el contador 0 lo vamos a comparar si es = a

                System.out.println("q3-----> "+car[cont]+" ----->q0");
                cont++; 
                q0(); 
            }

            else if (car[cont]=='i') { 

                System.out.println("q3-----> "+car[cont]+" ----->q1");
                cont++; 
                q1();           
            }

            else if (car[cont]=='.'||car[cont]==' '||car[cont]==',') {
                
                System.out.println("\n");
                System.out.println("Cadena aceptada");
                //for (int i=cont; i<=car[cont];i++) {

                    String w = String.valueOf(car);
                    System.out.println(w);
               // }
                pal++;
                System.out.println("\n"); 
                System.out.println("q3-----> "+car[cont]+" ----->q0");
                //System.out.println("\n"); 
                cont++;
                q0(); 
            }
        } 
    } 

    public void Nel() {

        System.out.println("\n");
        System.out.println("No se acepto ninguna cadena");
        System.out.println("\n");
        System.out.println("Fin del Automata");
    }
    
} 