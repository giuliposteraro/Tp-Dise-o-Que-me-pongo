package server;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import server.controllers.ClothesController;
import server.controllers.EventosController;
import server.controllers.HomeController;
import server.controllers.LoginController;
import server.controllers.SugerenciasController;
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
		EventosController eventosc = new EventosController();
		SugerenciasController sugerenciasc = new SugerenciasController();
		
		Spark.before("/*", (req, res) -> {
			beginTransaction();
		});
		Spark.before("/*", loginc::verificarAutenticacion);
		
		Spark.get("/", homec::showHome);
		Spark.get("/login", loginc::loguear);
		Spark.post("/login", loginc::loguear);
		Spark.get("/logout", loginc::logout);
		Spark.get("/home", homec::showHome);
		Spark.get("/wardrobes", wardrobesc::showWardrobes);
		Spark.get("/wardrobes/:id", wardrobesc::showWardrobe);
		Spark.post("/clothes", clothesc::newClothe);
		Spark.get("/clothes/:type/fabrics", clothesc::fabrics);
		Spark.get("/calendar", eventosc::showEventos);
		Spark.get("/calendar/new", eventosc::createEvento);
		Spark.post("/calendar/new", eventosc::saveEvento);
		Spark.get("/calendar/:event/suggestions", sugerenciasc::showSugerencias);
		Spark.get("/calendar/:event/suggestions/:suggestion", sugerenciasc::showAtuendo);
		Spark.post("/calendar/:event/suggestions", sugerenciasc::generarSugerencias);
		Spark.after("/*", (req, res) -> {
			try {
				commitTransaction();
			} catch (Exception e) {
				rollbackTransaction();
			} finally {
				System.out.println("Limpiando cache...");
				entityManager().clear();
			}
		});
	}
}
