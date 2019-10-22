package server.controllers;

import domain.eventos.Evento;
import persistency.services.SugerenciasService;
import spark.Request;
import spark.Response;

public class SugerenciasController extends Controller{
	SugerenciasService sugerenciasService = new SugerenciasService();

	public String showSugerencias(Request req, Response res) {
		Evento evento = req.session().attribute("evento");
		this.addAttribute("evento", evento);
		this.addAttribute("sugerencias",  sugerenciasService.getSugerenciasForEvent(evento));
		
		return this.render("sugerencias.hbs");
	
	}
}
