import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) throws Exception{
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try{
            BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Escribe la direccion del Servidor:");
            String host = br1.readLine();
            System.out.println("Escriba el puerto:");
            int pto = Integer.parseInt(br1.readLine());
            Socket cl = new Socket(host, pto);
            System.out.println("Conexion establecida...");
            oos = new ObjectOutputStream(cl.getOutputStream());
            ois = new ObjectInputStream(cl.getInputStream());
            System.out.println("Bienvenido al juego del ahorcado\nIntroduzca su nombre:");
            String nombre = br1.readLine();
            System.out.println("Ingrese su edad: ");
            int edad = Integer.parseInt(br1.readLine());
            System.out.println("Selecione la dificultad\n1. Facil\n2.Dificil");
            int dificultad = Integer.parseInt(br1.readLine());
            Cliente_info ci = new Cliente_info(nombre, edad, dificultad); //Creamos un objeto cliente_info con la informacion solicitada
            oos.writeObject(ci); //Mandamos el objeto al servidor
            oos.flush();
            int count, tamaño;
            tamaño = ois.readInt(); //Recibimos el tamaño de la cadena a descubrir
            count = ois.readInt(); //Recibimos el contador de vidas
            String aviso;
            aviso = (String)ois.readObject(); //Cadena con las vidas totales
            System.out.println(aviso);
            StringBuilder aux = new StringBuilder("");
            for(int i = 0; i < tamaño; i++){
                aux.append("_ ");
            }
            System.out.println(aux);
            while((count < 4 && count >= 0) && tamaño > 0){ //Mientras tenga vidas o descubra la palabra
                System.out.println("\nIntroduzca una letra:");
                String letra = br1.readLine();
                if(aux.toString().contains(letra)){
                    System.out.println("Ya se ingreso esa letra");
                    continue;
                }
                else{
                    oos.writeObject(letra); //Mandamos una letra al servidor
                    oos.flush();
                }
                int opcion = ois.readInt(); //Leemos si la letra coincide o no
                if(opcion == 1){ //Si coincide
                    aviso = (String)ois.readObject();
                    System.out.println(aviso);
                    int pos;
                    pos = ois.readInt(); //Recibimos la posicion
                    aux.setCharAt(2*pos, letra.charAt(0));
                    System.out.println(aux);
                    while(pos != -1){ //Esperando si se encuentra mas de una coincidencia
                        aviso = (String) ois.readObject();
                        System.out.println(aviso);
                        pos = ois.readInt(); //Recibimos la posicion de las otras coincidencias
                        if(pos > -1){
                            aux.setCharAt(2*pos, letra.charAt(0));
                            System.out.println(aux);
                        }
                    }
                    aviso = (String) ois.readObject(); //Vidas Restantes
                    System.out.println(aviso);
                }
                else if(opcion == 2){ //Si no la encuentra
                    aviso = (String)ois.readObject(); //Vidas restantes
                    System.out.println(aviso);
                    System.out.println(aux);
                }
                count = ois.readInt(); //Recibimos las vidas restantes
                tamaño = ois.readInt();//Recibimos cuantas letras hemos adivinado de la cadena         
            }
            int opcion = ois.readInt(); //Ganamos o Perdimos
            if(opcion == 1){ //Si ganamos
                aviso = (String)ois.readObject(); //Mensaje de victoria
                System.out.println(aviso);
            }
            if(opcion == 2){ //Si Perdimos
                aviso = (String)ois.readObject(); //Mensaje de derrota
                System.out.println(aviso);
                aviso = (String)ois.readObject(); //Palabra correcta
                System.out.println(aviso);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
