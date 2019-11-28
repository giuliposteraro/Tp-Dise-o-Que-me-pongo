

import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;

import domain.Job;
import domain.clima.TareaAlertasMeteorologicas;
import domain.eventos.TareaSugerenciaEventos;
import scenes.ListadoEventos.ListadoEventosView;
import server.Router;
import spark.Spark;
import spark.debug.DebugScreen;

public class Main extends Application {
	public static void main(String[] args) {
		new Job(new TareaSugerenciaEventos(), 5000, 10000).ejecutar();
		new Job(new TareaAlertasMeteorologicas(), 10000, 100000).ejecutar();
		Spark.ipAddress("0.0.0.0");
		Spark.port(Integer.parseInt(System.getenv("PORT")));		
		Spark.staticFileLocation("/public");
		Router.instance().configurar();
		DebugScreen.enableDebugScreen();
		Spark.init();
	}

	@Override
	protected Window<?> createMainWindow() {
		return new ListadoEventosView(this);
	}
}