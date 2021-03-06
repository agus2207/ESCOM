import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Servidor {
    public static void main(String[] args) throws Exception{
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try{
            ServerSocket s = new ServerSocket(7000);
            for(;;){
                int count = 0;
                Socket cl = s.accept();
                System.out.println("Conexion establecida desde: "+cl.getInetAddress()+" : "+cl.getPort());
                oos = new ObjectOutputStream(cl.getOutputStream());
                ois = new ObjectInputStream(cl.getInputStream());
                Cliente_info ci = (Cliente_info)ois.readObject();
                String [][] palabra = readArray(); //Leemos archivo serializado
                ArrayList<String>palabras = reedfile(palabra, ci.getDificultad()); //Creamos un ArrayList con las palabras dependiendo de la dificultad
                int index = (int)(Math.random()*palabras.size()); //Escogemos una cadena al azar de Arraylist
                String selection = palabras.get(index);
                System.out.println("La palabra seleccionada es: "+selection);
                int tamaño = selection.length();
                oos.writeInt(tamaño); //Mandamos el tamaño al cliente
                oos.flush();
                oos.writeInt(count); //Mandamos el contador de sus vidas
                oos.flush();
                oos.writeObject("Tienes 4 vidad");
                oos.flush();
                while((count >= 0 && count < 4) && tamaño > 0){  //Mientras que las vidas no pasen de 4 y el tamaño de la cadena no sea 0   
                    String intento = (String)ois.readObject(); //Recibimos la letra del cliente
                    //System.out.println(intento);
                    if(selection.toLowerCase().contains(intento)){ //Si la cadena contiene la letra
                        oos.writeInt(1);
                        oos.flush();
                        int pos;
                        pos = selection.toLowerCase().indexOf(intento, 0); //Buscamos la primer ocurrencia
                        //System.out.println("Posicion"+pos);
                        oos.writeObject("La letra coincide en la posicion "+pos); 
                        oos.flush();
                        oos.writeInt(pos); //Mandamos la posicion en la que aparecio
                        oos.flush();
                        while(pos != -1){ //Buscamos la misma letra en caso de que halla mas de una coincidencia
                            pos = selection.indexOf(intento, pos+1);
                            //System.out.println(pos);
                            oos.writeObject("La letra coincide en la posicion " + pos);
                            oos.flush();
                            oos.writeInt(pos); //Madamos la posicion de la siguiente coincidencia
                            oos.flush();
                            tamaño--; //Restamos las coincidencias al tamaño de la cadena
                        }
                        oos.writeObject("Te quedan "+(4-count)+" vidas");
                        oos.flush();
                    }
                    else{ //Si no existen coincidencias
                        oos.writeInt(2);
                        oos.flush();
                        count++;
                        oos.writeObject("Te quedan "+(4-count)+" vidas"); //Le quitamos una vida al usuario
                        oos.flush();
                    }
                    //System.out.println(count);
                    //System.out.println("Tamaño "+tamaño);
                    oos.writeInt(count); //Mandamos sus vidas restantes
                    oos.flush();
                    oos.writeInt(tamaño); //Mandamos las letras que le faltan adivinar
                    oos.flush();
                }
                if(tamaño == 0){ //Si el tamaño de la cadena llego a 0
                    oos.writeInt(1);
                    oos.flush();
                    oos.writeObject(ci.getNombre()+" HA GANADO"); //El cliente gano
                    oos.flush();
                }
                if(count == 4){ //Si gasto sus 4 vidas
                    oos.writeInt(2);
                    oos.flush();
                    oos.writeObject(ci.getNombre()+" HA PERDIDO"); //El cliente perdio
                    oos.flush();
                    oos.writeObject("La palabra era: "+selection); //Mandamos la respuesta correcta
                    oos.flush();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static String[][] readArray() throws IOException, ClassNotFoundException { //Lee el archivo serializado
    try (ObjectInputStream in = new ObjectInputStream(
            new FileInputStream("src/palabras.dat"))) {

        return (String[][]) in.readObject();
    }
}

    public static ArrayList<String> reedfile(String [][] p, int a) throws Exception{ //Crea el ArrayList con las palabras dependiendo de la dificultad
        ArrayList<String>palabras = new ArrayList();
        int tamaño = p.length;
        for(int i = 0; i < tamaño; i++){
            for(int j = 0; j < 40; j++){
                if(a == 1 && (p[i][j].length()<=5))
                    palabras.add(p[i][j]);
                if (a == 2 && (p[i][j].length()>=6))
                    palabras.add(p[i][j]);
            }
        }
        return palabras;
    }
}
