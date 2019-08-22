package domain;

import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;
import domain.eventos.JobEventos;
import scenes.ListadoEventos.ListadoEventosView;
import scenes.ListadoEventos.ListadoEventosViewModel;

public class Main extends Application{
	public static void main(String[] args){
	//	JobEventos.ejecutar();
		new Main().start();//
	}

	@Override
	protected Window<?> createMainWindow() {
		return new ListadoEventosView(this);
	}
}