public class TareaRevision extends TimerTask {

	RepositorioEventos repo;
	
	  // public MyTask(){
		     //Some stuff
		//   }

		   @Override
		   public void run() {
			   repo.proximosEventos().forEach(evento->evento.sugerir());
			   repo.eventos(repo.eventos().filter(evento->!evento.esProximo())); //????? aca borraría de la lista de eventos a aquellos que fueron ya sugeridos
		   }

	
}