package server;

import server.controllers.EventosController;
import server.controllers.HomeController;
import server.controllers.LoginController;
import server.controllers.WardrobesController;
import spark.Spark;

public class Router {
	static Router _instance;

	private Router() {
	}

	public static Router instance() {
		if (_instance == null) {
			_instance = new Router();
		}
		return _instance;
	}

	public void configurar() {
		LoginController loginc = new LoginController();
		WardrobesController wardrobesc = new WardrobesController();
		HomeController homec = new HomeController();
		EventosController eventosc = new EventosController();
		
		Spark.before("/*", loginc::verificarAutenticacion);	//abrir transaccion aca y cerrarla en el after
		
		Spark.get("/", homec::showHome);
		Spark.get("/login", loginc::loguear);
		Spark.post("/login", loginc::loguear);
		Spark.get("/logout", loginc::logout);
		Spark.get("/home", homec::showHome);
		Spark.get("/wardrobes", wardrobesc::showWardrobes);
		Spark.get("/wardrobes/:id", wardrobesc::showWardrobe);
		Spark.get("/calendar", eventosc::showEventos);
		
	}
}
