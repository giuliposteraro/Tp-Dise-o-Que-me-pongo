package server;
import persistency.services.WardrobeService;
import server.controllers.Controller;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WardrobesController extends Controller {
	WardrobeService wardrobeService = new WardrobeService();
	
	public String showWardrobes(Request req, Response res) {
		String username = req.session().attribute("username");
		return "Guardarropas: " + wardrobeService.getWardrobesForUser(username).size();
	}
}
