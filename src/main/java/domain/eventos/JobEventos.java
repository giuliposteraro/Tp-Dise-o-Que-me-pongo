package domain.eventos;

import java.util.TimerTask;

import domain.Job;

public class JobEventos extends Job {	//TODO eliminar

	public JobEventos(TimerTask tarea, int inicio, int frecuencia) {
		super(tarea, inicio, frecuencia);
	}
}