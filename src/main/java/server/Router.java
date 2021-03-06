package server;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import server.controllers.ClothesController;
import server.controllers.EventosController;
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
		EventosController eventosc = new EventosController();
		SugerenciasController sugerenciasc = new SugerenciasController();
		
		Spark.before("/*", (req, res) -> {
			beginTransaction();
		});
		Spark.before("/*", loginc::verificarAutenticacion);
		

		Spark.get("/",  eventosc::showEventos);
		Spark.get("/login", loginc::loguear);
		Spark.post("/login", loginc::loguear);
		Spark.get("/logout", loginc::logout);
		Spark.get("/wardrobes", wardrobesc::showWardrobes);
		Spark.get("/wardrobes/:guardarropa", wardrobesc::showWardrobeContent);
		Spark.post("/clothes", clothesc::newClothe);
		Spark.get("/clothes/:type/fabrics", clothesc::fabrics);
		Spark.get("/calendar", eventosc::showEventos);
		Spark.get("/calendar/new", eventosc::createEvento);
		Spark.post("/calendar/new", eventosc::saveEvento);

		Spark.get("/calendar/:event/suggestions", sugerenciasc::showSugerencias);
		Spark.post("/calendar/:event/suggestions", sugerenciasc::generarSugerencias);

		Spark.get("/calendar/:event/suggestions/:suggestion", sugerenciasc::showAtuendo);
		Spark.post("/suggestions/:suggestion/:decision", sugerenciasc::decidirSugerencia);
		
		Spark.get("/suggestions", sugerenciasc::mostrarSugerenciasAceptadas);
		Spark.post("/sugerencias/:sug/nota", sugerenciasc::calificarSugerencia);
		
		Spark.get("/register", loginc::showRegister);
		Spark.post("/register", loginc::register);
		Spark.get("/users/:username", loginc::userExists);
		Spark.delete("/wardrobes/:guardarropa/clothes/:prenda", clothesc::deleteClothe);
		
		Spark.after("/*", (req, res) -> {
			try {
				commitTransaction();
			} catch (Exception e) {
				rollbackTransaction();
			} finally {
				System.out.println("Limpiando cache...");
				entityManager().close();
				entityManager().clear();
			}
		});
	}
}
