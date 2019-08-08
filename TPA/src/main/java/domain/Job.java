package domain;

import java.util.Timer;
import java.util.TimerTask;

import domain.eventos.TareaSugerenciaEventos;

public class Job {

	private TimerTask tarea;
	private int inicio;
	private int frecuencia;
	
	public Job(TimerTask tarea, int inicio, int frecuencia) {
		this.tarea = tarea;
		this.inicio = inicio;
		this.frecuencia = frecuencia;
	}
	
	public Job ejecutar() {
		Timer timer = new Timer();
		timer.schedule(tarea, inicio, frecuencia);
		return this;
	}
}