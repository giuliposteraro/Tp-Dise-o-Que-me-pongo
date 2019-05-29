package domain;

import java.time.LocalDate;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;


public class ClimaOW implements ProveedorClima{
	
	private Client client;
    private static final String API_OPEN_WEATHER = "http://api.openweathermap.org/data/2.5/";
    private static final String RESOURCE = "forecast";
    private static final String APIKEY = "548beb56f12ba10a1d0a0c39b96d34d9";
    
    //Inicializacion del cliente.
    public ClimaOW() {
        this.client = Client.create();
        //En la documentacion se puede ver como al cliente agregarle un ClientConfig
        //para agregarle filtros en las respuestas (por ejemplo, para loguear).
    }
    
    public ClientResponse getTempByPlace(String filter, String value){
        WebResource recurso = this.client.resource(API_OPEN_WEATHER).path(RESOURCE);
        WebResource recursoConParametros = recurso.queryParam("q",filter + ":" + value).queryParam("APPID",APIKEY);
        System.out.println(recursoConParametros);
        WebResource.Builder builder = recursoConParametros.accept(MediaType.APPLICATION_JSON);

        ClientResponse response = builder.get(ClientResponse.class);
        return response;
    }
        
    
	@Override
	public float getTemp(LocalDate fecha, String lugar) {
		/*try {
		URL url = new URL("http://example.com");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		}catch() {
			System.out.println("Error");
		}*/
		return 0;
	}

}
