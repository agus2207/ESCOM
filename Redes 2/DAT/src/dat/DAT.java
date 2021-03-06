/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author erik_
 */
public class DAT {

    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) throws Exception {
    // 1. crear arreglo.
    String[][] array = new String[8][];
    array[0] = new String[6];
    array[0][0] = "Intifity war";
    array[0][1] = "Genero";
    array[0][2] = "Superhéroes se reúnen para detener a Thanos, "
            + "el sociópata intergaláctico, de obtener todas las piedras del infinito y "
            + "eliminar la mitad de seres vivos en el universo”.";
    array[0][3] = "400";
    array[0][4] = "6";
    array[0][5] = "/img/infinity_opt.jpg";
    
    array[1] = new String[6];
    array[1][0] = "La milla verde";
    array[1][1] = "Dramas";
    array[1][2] = "Un guardiacárcel descubre que uno de los reclusos que ha sido condenado a muerte"
            + "posee poderes misteriosos y trata por todos los medios de detener su ejecuión.";
    array[1][3] = "250";
    array[1][4] = "4";
    array[1][5] = "/img/villa_opt.jpg";
    
    array[2] = new String[6];
    array[2][0] = "Stitch";
    array[2][1] = "Películas infantiles";
    array[2][2] = "Sabiendo que no está solo en el universo,Stitch,también conocido como Experimento"
            + "626, se lanza para liberara los otros 625 experimentos alienígenas en la Tiera.";
    array[2][3] = "150";
    array[2][4] = "10";
    array[2][5] = "/img/stitch_opt.jpg";
    
    array[3] = new String[6];
    array[3][0] = "Iron Man";
    array[3][1] = "Acción";
    array[3][2] = "Cuando el afamado industrial Tony Stark es capturado, construye una armadura"
            + "de alta tecnología para escapar.Ahora, debe salvar el mundo como superhéroe.";
    array[3][3] = "400";
    array[3][4] = "15";
    array[3][5] = "/img/iron_opt.jpg";
    
    array[4] = new String[6];
    array[4][0] = "Star Wars";
    array[4][1] = "Acción";
    array[4][2] = "Mientras la primera orden bisca a luke, una chatarrera y un exsoldado de asalto unen"
            + "fuerza con un convincente droide para encontrarlo primero";
    array[4][3] = "350";
    array[4][4] = "9";
    array[4][5] = "/img/star_opt.jpg";
    
    array[5] = new String[6];
    array[5][0] = "Toy Story 3";
    array[5][1] = "Películas infantiles";
    array[5][2] = "Andy se prepara para ir a la universidad, y woody, Buzz, Jessie se preguntan qué será "
            + "de ellos.¡Hasta que conocen a nuevos  juguetes y comienzan toda una nueva aventura!";
    array[5][3] = "500";
    array[5][4] = "16";
    array[5][5] = "/img/toy_opt.jpg";
    
    array[6] = new String[6];
    array[6][0] = "Vengadores";
    array[6][1] = "Acción";
    array[6][2] = "Iron Man, Hulk, Capitán América, Thor.Si estos tipos no pueden salvar al mundo nada lo hará";
    array[6][3] = "450";
    array[6][4] = "3";
    array[6][5] = "/img/avengers_opt.jpg";
    
    array[7] = new String[6];
    array[7][0] = "Ant-Man";
    array[7][1] = "Acción";
    array[7][2] = "Un ladrón en libertad condicional se roba un traje que lo transforma en un insecto con poderes"
            + "sobrehumanos, pero un rival usa la misma tecnología como arma de guerra. ";
    array[7][3] = "550";
    array[7][4] = "7";
    array[7][5] = "/img/ant_opt.jpg";
    

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

private static void writeArray(String[][] array) throws IOException {
    try (ObjectOutputStream out = new ObjectOutputStream(
            new FileOutputStream("base.dat"))) {

        out.writeObject(array);
    }
}

private static String[][] readArray() throws IOException, ClassNotFoundException {
    try (ObjectInputStream in = new ObjectInputStream(
            new FileInputStream("base.dat"))) {

        return (String[][]) in.readObject();
    }
}
    
}
