package domain;

import java.util.HashMap;

import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;

import controllers.PrendasController;
import scenes.ListadoEventos.ListadoEventosView;
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import domain.clima.TareaAlertasMeteorologicas;
import domain.eventos.TareaSugerenciaEventos;

public class Main extends Application {
	public static void main(String[] args) {
//		new Job(new TareaSugerenciaEventos(), 5000, 10000).ejecutar();
//		new Job(new TareaAlertasMeteorologicas(), 10000, 100000).ejecutar();
//		new Main().start();
		Spark.port(9001);
		
		PrendasController prendaController = new PrendasController();
		Spark.post("/wardrobes/:id/clothes", prendaController::crear);
		
		Spark.get("session/example", (req, res) -> {
			return req.session().attribute("name");
		});
		
		Spark.post("session/example/:name", (req, res) -> {
			req.session(true);
			req.session().attribute("name", new String(req.params("name")));
			return "OK";
		});
		
		Spark.init();
	}

	@Override
	protected Window<?> createMainWindow() {
		return new ListadoEventosView(this);
	}
}