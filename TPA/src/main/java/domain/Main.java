package domain;

import domain.clima.JobAlertasMeteorológicas;
import domain.eventos.JobEventos;

public class Main {
	public static void main(String[] args){
		JobEventos.ejecutar();
		JobAlertasMeteorológicas.ejecutar();
	}
}