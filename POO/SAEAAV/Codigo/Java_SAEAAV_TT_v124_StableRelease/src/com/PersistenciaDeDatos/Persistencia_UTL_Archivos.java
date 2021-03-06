///Paquete de Trabajo
package com.PersistenciaDeDatos;

///Librerias
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Persistencia_UTL_Archivos {

    ///Atributos
    private String ruta;
    private String nombre;
    private String contenido;
    private Persistencia_UTL_Conversiones UTLConversiones = new Persistencia_UTL_Conversiones();
    private int tipoContenidoArchivo;

    ///Constructores
    public Persistencia_UTL_Archivos(String ruta, String nombre) {
        this.ruta = ruta;
        this.nombre = nombre;
    }

    ///Métodos de propósito General
    public void escribir(ArrayList<String> contenido) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(ruta + "" + nombre);
            pw = new PrintWriter(fichero);

            for (int k = 0; k < contenido.size(); k++) {
                pw.println(contenido.get(k));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           // Nuevamente aprovechamos el finally para 
            // asegurarnos que se cierra el fichero.
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void escribir(String contenido) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(ruta + "" + nombre);
            pw = new PrintWriter(fichero);

            pw.print(contenido);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Nuevamente aprovechamos el finally para 
            // asegurarnos que se cierra el fichero.
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void leer() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        this.contenido = "";

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File(ruta + "" + nombre);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            int coin = 1;
            boolean control = false;
            while ((linea = br.readLine()) != null) {
                contenido += linea;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void escribirIncluyendoTipo(String contenidoArchivo) {
        contenidoArchivo = this.tipoContenidoArchivo + "?" + contenidoArchivo;
        this.escribir(contenidoArchivo);
    }

    public void leerTipo() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        this.contenido = "";
        String resultado = "";

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File(ruta + "" + nombre);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            int coin = 0;
            boolean control = false;
            while ((linea = br.readLine()) != null) {
                coin++;

                if (coin == 1) {
                    System.out.println("linea=" + linea);
                    resultado = this.UTLConversiones.ObtenerDivisionEspecificadaDeLaCadenaPorLaCifra(1, linea, "?");
                    this.tipoContenidoArchivo = Integer.parseInt(resultado);
                } else {
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void leerArchivoExcluyendoTipo() {
        //Leemos
        this.leer();

        //Alteramos el contenido excluyendo tipo: #?
        this.contenido = this.UTLConversiones.ExtraerSubcadenaDeterminadaPorIndices(this.contenido, 2, this.contenido.length());
    }

    ///Métodos de propósito Específico
    ///Métodos para el manejo de Atributos
    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContenido() {
        return contenido;
    }

    public int getTipoContenidoArchivo() {
        return tipoContenidoArchivo;
    }

    public void setTipoContenidoArchivo(int tipoContenidoArchivo) {
        this.tipoContenidoArchivo = tipoContenidoArchivo;
    }

}
