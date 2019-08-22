package domain.eventos;
import java.util.TimerTask;

import domain.Config;

public class TareaSugerenciaEventos extends TimerTask {
	@Override
	public void run() {
		System.out.println("Evaluando eventos proximos...");
		Config.instance()
			.getRepositorioEventos()
			.proximosEventos()
			.forEach(evento -> evento.sugerir());
	}
}