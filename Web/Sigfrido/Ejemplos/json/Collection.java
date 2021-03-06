import java.util.*;
import java.util.ArrayList;
import com.google.gson.Gson;

public class Collection {
	public static void main(String args[]) throws java.io.IOException {
		Gson gson = new Gson();
		ArrayList<String> c = new ArrayList<String>();
		c.add("hello");
		c.add("5");
		String json = gson.toJson(c);
		System.out.println("JSON: " + json);
	}
}