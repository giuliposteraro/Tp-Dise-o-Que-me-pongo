package domain.clima;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import domain.Config;
import exceptions.NoSePuedeObtenerElClima;
import javax.ws.rs.core.MediaType;
import org.json.JSONException;
import org.json.JSONObject;

public class ClimaOW implements ProveedorClima{
	
	private Client client;
    private static final String API_OPEN_WEATHER = "http://api.openweathermap.org/data/2.5/";
    private static final String RESOURCE = "weather";
    private static final String CITY = Config.instance().getCiudad();
    private static final String APIKEY = "548beb56f12ba10a1d0a0c39b96d34d9";
    
    public ClimaOW() {
        this.client = Client.create();
    }
    
    private ClientResponse getTempResponse(){
        WebResource recurso = this.client.resource(API_OPEN_WEATHER).path(RESOURCE);
        WebResource recursoConParametros = recurso.queryParam("q", CITY).queryParam("APPID", APIKEY);
        WebResource.Builder builder = recursoConParametros.accept(MediaType.APPLICATION_JSON);

        ClientResponse response = builder.get(ClientResponse.class);
        return response;
    }
   
	@Override
	public Double getTemp() {
		String ResultadoJson = this.getTempResponse().getEntity(String.class);
		JSONObject json;
		String temp;
		
		try {
			json = new JSONObject(ResultadoJson);
			temp = json.getJSONObject("main").getString("temp");
			return Double.valueOf(temp) - 273.15;
		} catch (JSONException e) {
			throw new NoSePuedeObtenerElClima("La API de clima no esta disponible");
		}
	}
}
