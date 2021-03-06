import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DAT {

 public static void main(String[] args) throws IOException, ClassNotFoundException {
    // 1. crear arreglo.
    String[][] array = new String[1][];
    array[0] = new String[40];
    array[0][0] = "Hola";
    array[0][1] = "Parangaricutirimicuaro";
    array[0][2] = "Libro";
    array[0][3] = "Thanos";
    array[0][4] = "Bajar";
    array[0][5] = "Esternocleidomastoideo";
    array[0][6] = "Int";
    array[0][7] = "Otorrinolaringologo";
    array[0][8] = "Float";
    array[0][9] = "Vindication";
    array[0][10] = "Gemas";
    array[0][11] = "Ninenine";
    array[0][12] = "Poder";
    array[0][13] = "Computadora";
    array[0][14] = "Alma";
    array[0][15] = "Vengadores";
    array[0][16] = "Tape";
    array[0][17] = "Spiderman";
    array[0][18] = "Cool";
    array[0][19] = "Superman";
    array[0][20] = "Punk";
    array[0][21] = "Software";
    array[0][22] = "Utf";
    array[0][23] = "Hardware";
    array[0][24] = "Emoji";
    array[0][25] = "Pelicula";
    array[0][26] = "Dat";
    array[0][27] = "Comipilar";
    array[0][28] = "Txt";
    array[0][29] = "Endgame";
    array[0][30] = "Oso";
    array[0][31] = "Superheroe";
    array[0][32] = "Java";
    array[0][33] = "Cerrar";
    array[0][34] = "Fin";
    array[0][35] = "Playera";
    array[0][36] = "Bicho";
    array[0][37] = "Mensaje";
    array[0][38] = "On";
    array[0][39] = "Problema";

    // 2. Escribir el arreglo al archivo.
    writeArray(array);

    // 3. Leer el arreglo del archivo.
    String[][] deserializedArray = readArray();

    // 4. Verificar contenidos del arreglo.
    for (String[] subArray : deserializedArray) {
        for (String item : subArray) {
            System.out.println(item);
        }
    }
}

public static void writeArray(String[][] array) throws IOException {
    try (ObjectOutputStream out = new ObjectOutputStream(
            new FileOutputStream("palabras.dat"))) {

        out.writeObject(array);
    }
}

public static String[][] readArray() throws IOException, ClassNotFoundException {
    try (ObjectInputStream in = new ObjectInputStream(
            new FileInputStream("palabras.dat"))) {

        return (String[][]) in.readObject();
    }
}
    
}