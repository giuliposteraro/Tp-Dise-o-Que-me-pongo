package domain.eventos;
import java.util.TimerTask;
import java.util.stream.Collectors;

import domain.Config;

public class TareaSugerenciaEventos extends TimerTask {

	RepositorioEventos repo = Config.instance().getRepositorioEventos();

   @Override
   public void run() {
	   System.out.println("Running Job...");
	   repo.proximosEventos().forEach(evento->evento.sugerir());
	   System.out.println("Job Finished!");
   }
}