package domain.eventos;
import java.util.TimerTask;
import java.util.stream.Collectors;

public class TareaRevision extends TimerTask {

	RepositorioEventos repo;
	
	  // public MyTask(){
		     //Some stuff
		//   }

		   @Override
		   public void run() {
			   repo.proximosEventos().forEach(evento->evento.sugerir());
			   repo.eventos(repo.eventos().stream().filter(evento->!evento.esProximo()).collect(Collectors.toSet())); //????? aca borraría de la lista de eventos a aquellos que fueron ya sugeridos
		   }

	
}