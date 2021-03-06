import com.google.gson.Gson;

public class escribe_json {
	public static void main(String args[]) throws java.io.IOException {
		Gson gson = new Gson();
		String datos = "Hola";
		String jsonString = gson.toJson(datos);
		System.out.println("JSON: " + jsonString);
	}
}