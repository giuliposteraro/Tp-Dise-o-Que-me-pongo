package domain;

import domain.clima.TareaAlertasMeteorologicas;
import domain.eventos.TareaSugerenciaEventos;

public class Main {
	public static void main(String[] args){
		Job jobEventos = new Job(new TareaSugerenciaEventos(), 5000, 10000).ejecutar();
		Job jobAlertas = new Job(new TareaAlertasMeteorologicas(), 10000, 100000).ejecutar();
	}
}