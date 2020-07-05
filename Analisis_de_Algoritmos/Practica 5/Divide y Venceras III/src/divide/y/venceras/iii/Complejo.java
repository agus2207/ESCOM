/*
Autores: Galindo Reyes Agustin
         Yañes Martinez Josue Ricardo

Fecha: 11 de abril del 2018

Ultima modificacion: 15 de abril del 2018

Descripcion: Esta funcion la ocupamos para el manejo de los numeros
             complejos mediante su parte real e imaginaria, en esta 
             funcion agregamos la operacion de potencia que nos 
             ayudara a calcular la potencia de un numero complejo 
             que sera de gran ayuda en las siguientes practicas.
*/

package divide.y.venceras.iii;

public class Complejo {

    private double real;
    private double imag;

    public String toString() {
        if (imag > 0) {
            return new String((double) Math.round(100 * real) / 100
                    + " + " + (double) Math.round(100 * imag) / 100 + "i");
        }
        return new String((double) Math.round(100 * real) / 100
                + " - " + (double) Math.round(-100 * imag) / 100 + "i");
    }

    public Complejo() {

        real = 0.0;
        imag = 0.0;
    }

    public Complejo(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

//función auxiliar  para la potencia de un número complejo
    private static double potencia(double base, int exponente) {
        double resultado = 1.0;
        for (int i = 0; i < exponente; i++) {
            resultado *= base;
        }
        return resultado;
    }
//función auxiliar para la potencia de un número complejo

    private static double combinatorio(int m, int n) {
        long num = 1;
        long den = 1;
        for (int i = m; i > m - n; i--) {
            num *= i;
        }
        for (int i = 2; i <= n; i++) {
            den *= i;
        }
        return (double) num / den;
    }
//potencia de un número complejo

    public static Complejo potencia(Complejo c, int exponente) {
        double x = 0.0, y = 0.0;
        int signo;
        for (int i = 0; i <= exponente; i++) {
            signo = (i % 2 == 0) ? +1 : -1;
//parte real
            x += combinatorio(exponente, 2 * i) * potencia(c.real, exponente - 2 * i) * potencia(c.imag, 2 * i) * signo;
            if (exponente == 2 * i) {
                break;
            }
//parte imaginaria
            y += combinatorio(exponente, 2 * i + 1) * potencia(c.real, exponente - (2 * i + 1)) * potencia(c.imag, 2 * i + 1) * signo;
        }
        return new Complejo(x, y);
    }

}
