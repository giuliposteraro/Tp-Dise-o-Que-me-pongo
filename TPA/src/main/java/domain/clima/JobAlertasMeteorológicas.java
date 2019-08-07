package domain.clima;

import java.util.Timer;
import java.util.TimerTask;

public class JobAlertasMeteorológicas {	//TODO Abstaer clase Job(task, init, freq);
	public static void ejecutar(){

		Timer timer = new Timer();
		TimerTask tarea = new TareaAlertasMeteorologicas();
		
		// Esta tarea se corre cada 10 seg
		timer.schedule(tarea, 7000, 10000);
	}
}
