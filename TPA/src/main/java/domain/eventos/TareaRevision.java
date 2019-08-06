package domain.eventos;
import java.util.TimerTask;
//import java.util.stream.Collectors;

import domain.Config;

public class TareaRevision extends TimerTask {

	RepositorioEventos repo = Config.instance().getRepositorioEventos();

   @Override
   public void run() {
	   System.out.println("Running Job...");
	   repo.eventosProximosYPendientes().forEach(evento->evento.sugerir());
	   repo.eventosProximosYPendientes().forEach(evento->evento.marcarComoPasado());
	   //repo.eventos(repo.eventos().stream().filter(evento -> !evento.esProximo()).collect(Collectors.toSet()));
	   System.out.println("Job Finished!");
   }
}