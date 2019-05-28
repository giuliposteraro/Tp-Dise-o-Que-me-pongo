import java.util.Timer;

public class Main{
	   public static void main(String[] args){

	     Timer timer = new Timer();
	     TareaRevision tarea = new TareaRevision();
	     // Esta tarea se corre cada 10 seg

	     timer.scheduleAtFixedRate(tarea, 0, 10000);
	   }

	}