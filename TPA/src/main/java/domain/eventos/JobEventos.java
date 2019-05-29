package domain.eventos;
import java.util.Timer;
import java.util.TimerTask;

public class JobEventos{
	   public static void main(String[] args){

	     Timer timer = new Timer();
	     TimerTask tarea = new TareaRevision();
	     // Esta tarea se corre cada 10 seg

	     timer.scheduleAtFixedRate(tarea, 0, 10000);
	   }

	}