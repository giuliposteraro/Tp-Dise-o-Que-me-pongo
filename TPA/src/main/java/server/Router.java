package server;

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
		LoginController lc = new LoginController();
		WardrobesController wc = new WardrobesController();
		Spark.get("/login", lc::loguear);
		Spark.post("/login", lc::loguear);
		//Spark.get("/wardrobes", route);
	}
}
