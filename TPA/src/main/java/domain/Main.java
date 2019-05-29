package domain;

import com.google.gson.Gson;
import java.util.Properties;

import org.json.JSONException;
import org.json.JSONObject;

public class Main {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	ClimaOW cl = new ClimaOW();
	
	String json = cl.getTempByPlace("id","2172797").getEntity(String.class);
	//System.out.println(json);
	//final String json = "{\"id\":46,\"nombre\":\"Miguel\",\"empresa\":\"Autentia\"}";
	//final Gson gson = new Gson();
    //final Properties properties = gson.fromJson(json, Properties.class);
    //System.out.println(properties.getProperty("id"));
	System.out.println(json);
	}

}
