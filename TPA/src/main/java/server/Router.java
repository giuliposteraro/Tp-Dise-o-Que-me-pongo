package server;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import server.controllers.ClothesController;
import server.controllers.HomeController;
import server.controllers.LoginController;
import server.controllers.WardrobesController;
import spark.Spark;

public class Router implements WithGlobalEntityManager, TransactionalOps {
	static Router _instance;

	private Router() {}

	public static Router instance() {
		if (_instance == null) {
			_instance = new Router();
		}
		return _instance;
	}

	public void configurar() {
		LoginController loginc = new LoginController();
		WardrobesController wardrobesc = new WardrobesController();
		ClothesController clothesc = new ClothesController();
		HomeController homec = new HomeController();
		
		Spark.before("/*", (req, res) -> {
			beginTransaction();
		});
		Spark.before("/*", loginc::verificarAutenticacion);	//abrir transaccion aca y cerrarla en el after
		
		Spark.get("/", homec::showHome);
		Spark.get("/login", loginc::loguear);
		Spark.post("/login", loginc::loguear);
		Spark.get("/logout", loginc::logout);
		Spark.get("/home", homec::showHome);
		Spark.get("/wardrobes", wardrobesc::showWardrobes);
		Spark.get("/wardrobes/:id", wardrobesc::showWardrobe);
		Spark.post("/clothes", clothesc::newClothe);
		Spark.get("/clothes", clothesc::newClothe);
		
		Spark.after("/*", (req, res) -> {
			try {
				commitTransaction();
			} catch (Exception e) {
				rollbackTransaction();
			}
			entityManager().clear();
		});
	}
}
