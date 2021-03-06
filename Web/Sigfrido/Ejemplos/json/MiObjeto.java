import java.util.*;
import java.util.ArrayList;
import com.google.gson.Gson;

public class MiObjeto {
	private String nombre;
	private String origen;
	String miCadena;
	ArrayList<String> miColeccion = new ArrayList<String>();
	Vector miVector;
	private MiObjeto(String nombre, String origen, String cadena) {
		this.nombre = nombre;
		this.origen = origen;
		this.miCadena = cadena;
		miColeccion.add("adios");
		miColeccion.add("10"); //*
		miVector = new Vector();
		miVector.add("Elemento1");
		miVector.add(null);
		miVector.add("Elemento3");	
		miVector.add("Elemento4");
	}
	public static void main(String args[]) throws java.io.IOException {
		Gson gson = new Gson();
		MiObjeto obj = new MiObjeto("Juan", "Madrid", null);
		String jsonString = gson.toJson(obj);
		System.out.println("JSON: " + jsonString);
	}
}