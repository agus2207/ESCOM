import java.math.BigInteger;
import java.util.Random;
import java.io.*;
 
public class Karatsuba {
 
  /**
   * MÃ©todo que mediante la tÃ©cnica divide y vencerÃ¡s, multiplica dos nÃºmeros
   * muy grandes tras una serie de pasos.
   * @param u BigInteger Entero grande 1.
   * @param v BigInteger Entero grande 2.
   * @return BigInteger Resultado
   */
  public static BigInteger karatsuba(BigInteger u, BigInteger v) {
    int posiciones = Math.max(u.bitLength(), v.bitLength());
 
    //Para n menor que mil, es mÃ¡s eficiente la multiplicaciÃ³n normal.
    if (posiciones <= 1000) {
      return u.multiply(v);
    }
    posiciones = posiciones / 2;
 
    /*
     * Repartimos en trocitos:
     * u = w * 2^n + x
     * v = y * 2^n + z
     */
 
    BigInteger w = u.shiftRight(posiciones);
    BigInteger x = u.subtract(w.shiftLeft(posiciones));
    BigInteger y = v.shiftRight(posiciones);
    BigInteger z = v.subtract(y.shiftLeft(posiciones));
 
    // Calculamos los resultados parciales
    BigInteger p = karatsuba(w, y); //p=w*y
    BigInteger q = karatsuba(x, z); //q=x*z
    BigInteger r = karatsuba(x.add(w), z.add(y)); //r=(x+w)*(z+y)
    BigInteger z1 = r.subtract(p).subtract(q); //r-p-q
 
    // Se juntan los resultados parciales para obtener el resultado global.
    return p.shiftLeft(2 * posiciones).add(z1.shiftLeft(posiciones)).add(q);
  }
 
  public static void main(String[] args) {
    Random rnd = new Random();
 
    System.out.println("ALGORITMO DE KARATSUBA");
    System.out.println("----------------------");
 
    System.out.print(
        "Introduzca un nÃºmero de bits(Sugerencia: A poder ser mayor que 1000): ");
 
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
    try {
      String numero = br.readLine();
 
      int N = Integer.parseInt(numero);
 
      //Creamos dos nÃºmeros al azar de N cifras.
      BigInteger x = new BigInteger(N, rnd);
      BigInteger y = new BigInteger(N, rnd);
 
      //System.out.println("Numero 1: " + x);
      //System.out.println("\n\nNumero 2: " + y);
      System.out.println("Multiplicamos...");
 
      //Mediremos los tiempos de Karatsuba y MultiplicaciÃ³n normal para ver las diferencias.
      long t = System.nanoTime();
 
      //z serÃ­a el resultado. Hacemos la llamada al mÃ©todo.
      BigInteger z = karatsuba(x, y);
 
      t = System.nanoTime() - t;
      System.out.printf("\nEl valor de X es: %d " , x);
      System.out.printf("\nEl valor de Y es: %d " , y);
      System.out.printf("\nEl resultado de X*Y mediante Karatsuba es: %d " , z);
      System.out.println("\n\n\nN = " + N);
      System.out.println("\n\n\nN = " + N);
      System.out.println("Karatsuba: tiempo = " + t);
 
      t = System.nanoTime();
      z = x.multiply(y);
      t = System.nanoTime() - t;
      System.out.println("MultiplicaciÃ³n Normal: tiempo = " + t);
 
    }
    catch (IllegalArgumentException iae) {
      System.err.println("AtenciÃ³n: CarÃ¡cter no valido.");
      System.exit( -1);
    }
    catch (IOException ioe) {
      System.err.println("Error de E/S");
      System.exit( -1);
    }
    catch (Exception e) {
      System.err.println("Error inesperado. " + e.getMessage());
      System.exit( -1);
    }
  }
}