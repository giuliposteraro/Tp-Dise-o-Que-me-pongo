package domain.clima;

import java.util.Timer;
import java.util.TimerTask;

public class JobAlertasMeteorologicas {	//TODO eliminar
	public static void ejecutar(){

		Timer timer = new Timer();
		TimerTask tarea = new TareaAlertasMeteorologicas();
		
		// Esta tarea se corre cada 10 seg
		timer.schedule(tarea, 7000, 10000);
	}
}
