import java.util.Scanner;

public class Automata2 { 

    int cont, pal, cont0, cont1; 
    boolean aceptada;  //para guardar los caratcteres y los va ir separando 
    char [] car; 

    public static void main(String[]args) { 
        Automata2 aut= new Automata2(); 
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
        cont0 = 0;
        cont1 = 0; 
        q0();  //irnos al codigo 0   
    }

    public void q0() {  

        //System.out.println("En q0");  //imprimimos y decimos que estamos en q0

        if(cont < car.length) { //cuantos espacios tiene mi arreglo 

            if(car[cont]!='0'&&car[cont]!='1'&&car[cont]!=' ') { //el arreglo car en el contador 0 lo vamos a comparar si es = a 

                System.out.println("Error");
                //cont++; //incrememnto mi contador 
                //q0();  //si no lo dejo en 0 
            }

            else if (car[cont]=='0') { //si es una b me muevo al q1 
                
                System.out.println("q0-----> "+car[cont]+" ----->q1");
                cont++;
                cont0++;
                q1(); //nos va a mover al estado uno 
            }

            else if (car[cont]=='1') { //si es una b me muevo al q1 
                
                System.out.println("q0-----> "+car[cont]+" ----->q3");
                cont++;
                cont1++;
                q3(); //nos va a mover al estado uno 
            } 

            else if (car[cont]==' ') { //si es una b me muevo al q1 
                
                System.out.println("q0-----> "+car[cont]+" ----->q0");
                cont++;
                Space();
                q0(); //nos va a mover al estado uno 
            }   
        } 

        else if (cont == car.length) {

            Paridad();
        }
    }

    public void q1() {

        //System.out.println("En q1");
         
        if(cont < car.length) { //cuantos espacios tiene mi arreglo 

            if(car[cont]!='0'&&car[cont]!='1'&&car[cont]!=' ') { //el arreglo car en el contador 0 lo vamos a comparar si es = a 

                System.out.println("Error");
                //cont++; //incrememnto mi contador 
                //q0();  //si no lo dejo en 0 
            }

            else if (car[cont]=='0') { //si es una b me muevo al q1 
                
                System.out.println("q1-----> "+car[cont]+" ----->q0");
                cont++;
                cont0++;
                q0(); //nos va a mover al estado uno 
            }

            else if (car[cont]=='1') { //si es una b me muevo al q1 
                
                System.out.println("q1-----> "+car[cont]+" ----->q2");
                cont++;
                cont1++;
                q2(); //nos va a mover al estado uno 
            } 

            else if (car[cont]==' ') { //si es una b me muevo al q1 
                
                System.out.println("q1-----> "+car[cont]+" ----->q1");
                cont++;
                Space();
                q1(); //nos va a mover al estado uno 
            }   
        }  

        else if (cont == car.length) {

            Paridad();
        }
    }

    public void q2() { 

        //System.out.println("En q2"); 

        if(cont < car.length) { //cuantos espacios tiene mi arreglo 

            if(car[cont]!='0'&&car[cont]!='1'&&car[cont]!=' ') { //el arreglo car en el contador 0 lo vamos a comparar si es = a 

                System.out.println("Error");
                //cont++; //incrememnto mi contador 
                //q0();  //si no lo dejo en 0 
            }

            else if (car[cont]=='0') { //si es una b me muevo al q1 
                
                System.out.println("q2-----> "+car[cont]+" ----->q1");
                cont++;
                cont0++;
                q1(); //nos va a mover al estado uno 
            }

            else if (car[cont]=='1') { //si es una b me muevo al q1 
                
                System.out.println("q2-----> "+car[cont]+" ----->q3");
                cont++;
                cont1++;
                q3(); //nos va a mover al estado uno 
            }

            else if (car[cont]==' ') { //si es una b me muevo al q1 
                
                System.out.println("q2-----> "+car[cont]+" ----->q2");
                cont++;
                Space();
                q2(); //nos va a mover al estado uno 
            }    
        } 

        else if (cont == car.length) {

            Paridad();
        } 
    } 

    public void q3() {

        if(cont < car.length) { //cuantos espacios tiene mi arreglo 

            if(car[cont]!='0'&&car[cont]!='1'&&car[cont]!=' ') { //el arreglo car en el contador 0 lo vamos a comparar si es = a 

                System.out.println("Error");
                //cont++; //incrememnto mi contador 
                //q0();  //si no lo dejo en 0 
            }

            else if (car[cont]=='0') { //si es una b me muevo al q1 
                
                System.out.println("q3-----> "+car[cont]+" ----->q2");
                cont++;
                cont0++;
                q2(); //nos va a mover al estado uno 
            }

            else if (car[cont]=='1') { //si es una b me muevo al q1 
                
                System.out.println("q3-----> "+car[cont]+" ----->q0");
                cont++;
                cont1++;
                q0(); //nos va a mover al estado uno 
            } 

            else if (car[cont]==' ') { //si es una b me muevo al q1 
                
                System.out.println("q3-----> "+car[cont]+" ----->q3");
                cont++;
                Space();
                q3(); //nos va a mover al estado uno 
            }   
        } 

        else if(cont == car.length) {

            Paridad();
        }
    } 

    public void Paridad() {

        if (cont0%2==0 && cont1%2==0) {

            System.out.println("\n");
            System.out.println("Cadena con paridad");
            System.out.println("\n");
            pal++;
            System.out.println("Cadena totales con paridad: "+pal);
            System.out.println("\n");
            System.out.println("Fin del Automata");
        }

        else {

            System.out.println("\n");
            System.out.println("La cadena sin paridad");
            System.out.println("\n");
            System.out.println("Cadena totales con paridad: "+pal);
            System.out.println("\n");
            System.out.println("Fin del Automata");
        }
    }

    public void Space() {

        if (cont0%2==0 && cont1%2==0) {

            System.out.println("\n");
            System.out.println("Cadena con paridad");
            System.out.println("\n");
            pal++;
            cont1 = 0;
            cont0 = 0;
        }

        else {

            System.out.println("\n");
            System.out.println("Cadena sin paridad");
            System.out.println("\n");
            cont1 = 0;
            cont0 = 0;
        }

    }
    
} 