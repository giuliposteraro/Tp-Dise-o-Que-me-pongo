package domain;

import domain.clima.ClimaOW;
import domain.eventos.JobEventos;

import java.time.LocalDate;

import org.json.JSONException;

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
		
		System.out.println(LocalDate.now().isEqual(LocalDate.of(2019,5,29)));
		
		
	}

}
