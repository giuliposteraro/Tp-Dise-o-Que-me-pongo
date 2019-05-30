package domain.eventos;
import java.util.TimerTask;
import java.util.stream.Collectors;

import domain.Config;

public class TareaRevision extends TimerTask {

	RepositorioEventos repo = Config.instance().getRepositorioEventos();

   @Override
   public void run() {
	   System.out.println("Running Job...");
	   repo.proximosEventos().forEach(evento->evento.sugerir());
	   repo.eventos(repo.eventos().stream().filter(evento->!evento.esProximo()).collect(Collectors.toSet())); //????? aca borrar�a de la lista de eventos a aquellos que fueron ya sugeridos
	   System.out.println("Job Finished!");
   }

	
}