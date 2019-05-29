package domain;

import domain.clima.ClimaOW;
import domain.eventos.JobEventos;
import org.json.JSONException;
import org.json.JSONObject;

public class Main {

	
	public static void main(String[] args){
		ClimaOW cl = new ClimaOW();
		try {
			System.out.println(cl.getTemp());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		JobEventos.ejecutar();
		
	}

}
