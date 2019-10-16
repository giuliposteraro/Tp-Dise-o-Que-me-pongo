package server.controllers;
import persistency.services.WardrobeService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WardrobesController extends Controller {
	WardrobeService wardrobeService = new WardrobeService();
	
	public String showWardrobes(Request req, Response res) {
		String username = req.session().attribute("username");
		this.addAttribute("username", username);
		
		
		
		this.addAttribute("guardarropas",  wardrobeService.getWardrobesForUser(username));
		
		return this.render("wardrobes.hbs");
	}
	
	public String showWardrobe(Request req, Response res) {
		String username = req.session().attribute("username");
		this.addAttribute("username", username);
		
		this.addAttribute("guardarropas",  wardrobeService.getWardrobesForUser(username));
		
		return this.render("wardrobes.hbs");
	}
}
