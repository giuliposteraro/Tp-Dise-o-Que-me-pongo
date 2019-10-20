package server.controllers;
import persistency.services.EventosService;
import persistency.services.WardrobeService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class EventosController extends Controller{
	EventosService eventosService = new EventosService();

	
	public String showEventos(Request req, Response res) {
		String username = req.session().attribute("username");
		this.addAttribute("username", username);
		this.addAttribute("eventos",  eventosService.getEventosForUser(username));
		
		return this.render("calendar.hbs");
	}
	
	public String newEvento(Request req, Response res) {
		String username = req.session().attribute("username");
		this.addAttribute("username", username);
		this.addAttribute("eventos",  eventosService.getEventosForUser(username));
		
		return this.render("calendar.hbs");
	}
}
