package domain;

import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;
import scenes.ListadoEventos.ListadoEventosView;
import domain.clima.TareaAlertasMeteorologicas;
import domain.eventos.TareaSugerenciaEventos;


public class Main extends Application{
	public static void main(String[] args){
		new Job(new TareaSugerenciaEventos(), 5000, 10000).ejecutar();
		new Job(new TareaAlertasMeteorologicas(), 10000, 100000).ejecutar();
		new Main().start();
	}

	@Override
	protected Window<?> createMainWindow() {
		return new ListadoEventosView(this);
	}
}