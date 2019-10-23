package server.controllers;

import domain.eventos.Evento;
import domain.eventos.RepositorioEventos;
import domain.usuario.RepositorioUsuarios;
import persistency.services.EventosService;
import persistency.services.SugerenciasService;
import spark.Request;
import spark.Response;

public class SugerenciasController extends Controller{
	SugerenciasService sugerenciasService = new SugerenciasService();
	RepositorioUsuarios repoUsuarios = new RepositorioUsuarios();
	RepositorioEventos repoEventos = new RepositorioEventos(repoUsuarios);
	
	public String showSugerencias(Request req, Response res) {
		String id_evento = req.params("event");
		String username = req.session().attribute("username");
		this.addAttribute("evento", sugerenciasService.getMotivoEvento(Long.parseLong(id_evento)));
		this.addAttribute("id_evento", id_evento);
		this.addAttribute("sugerencias",  sugerenciasService.getSugerenciasForEvent(Long.parseLong(id_evento)));
		this.addAttribute("username", username);
		return this.render("sugerencias.hbs");	
	}
	
	public String showAtuendo(Request req, Response res) {
		String username = req.session().attribute("username");
		String id_sugerencia = req.params("id_sugerencia");
		
		this.addAttribute("sugerencia",  sugerenciasService.getSugerencia(Long.parseLong(id_sugerencia)));
		System.out.println(sugerenciasService.getSugerencia(Long.parseLong(id_sugerencia)).getAtuendo());
		return this.render("suggestion-content.hbs");
	}
	
	public String generarSugerencias(Request req, Response res) {
		String id_evento = req.params("event");
		Evento evento = repoEventos.getEvento(Long.parseLong(id_evento));
		String username = req.session().attribute("username");
		evento.sugerir();
		this.addAttribute("evento", sugerenciasService.getMotivoEvento(Long.parseLong(id_evento)));
		this.addAttribute("id_evento", id_evento);
		this.addAttribute("sugerencias",  sugerenciasService.getSugerenciasForEvent(Long.parseLong(id_evento)));
		this.addAttribute("username", username);
		return this.render("sugerencias.hbs");		
	}
	
}
	

