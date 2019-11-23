

import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;

import scenes.ListadoEventos.ListadoEventosView;
import server.Router;
import spark.Spark;
import spark.debug.DebugScreen;

public class Main extends Application {
	public static void main(String[] args) {
//		new Job(new TareaSugerenciaEventos(), 5000, 10000).ejecutar();
//		new Job(new TareaAlertasMeteorologicas(), 10000, 100000).ejecutar();
//		new Main().start();
		Spark.port(9001);		
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