package domain.eventos;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class JobEventos{
	   public static void ejecutar(){

	     Timer timer = new Timer();
	     TimerTask tarea = new TareaRevision();
	     // Esta tarea se corre cada 10 seg

	     timer.schedule(tarea, 10000, 10000);
	   }

	}