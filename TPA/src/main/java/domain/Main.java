package domain;

import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;
import domain.eventos.JobEventos;
import scenes.ListadoEventos.ListadoEventosView;
import scenes.ListadoEventos.ListadoEventosViewModel;
import domain.clima.ClimaOW;
import domain.clima.TareaAlertasMeteorologicas;
import domain.eventos.TareaSugerenciaEventos;


public class Main extends Application{
	public static void main(String[] args){
//		Job jobEventos = new Job(new TareaSugerenciaEventos(), 5000, 10000).ejecutar();
//		Job jobAlertas = new Job(new TareaAlertasMeteorologicas(), 10000, 100000).ejecutar();
		new Main().start();//
	}

	@Override
	protected Window<?> createMainWindow() {
		return new ListadoEventosView(this);
	}
}