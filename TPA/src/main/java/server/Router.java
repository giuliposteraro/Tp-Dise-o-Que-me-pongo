package server;

import server.controllers.HomeController;
import server.controllers.LoginController;
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
		
		Spark.before("/*", loginc::verificarAutenticacion);
		
		Spark.get("/", homec::showHome);
		Spark.get("/login", loginc::loguear);
		Spark.post("/login", loginc::loguear);
		Spark.get("/home", homec::showHome);
		Spark.get("/wardrobes", wardrobesc::showWardrobes);
	}
}
