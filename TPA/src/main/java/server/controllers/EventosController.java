package server.controllers;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import domain.Config;
import domain.eventos.EFrecuencia;
import domain.eventos.RepositorioEventos;
import domain.guardarropa.Guardarropa;
import domain.usuario.Usuario;
import persistency.services.EventosService;
import persistency.services.WardrobeService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class EventosController extends Controller{
	//EventosService eventosService = new EventosService();
	WardrobeService wardrobeService = new WardrobeService();
	RepositorioEventos repoEventos = Config.instance().getRepositorioEventos();
			
	public String showEventos(Request req, Response res) {
		String username = req.session().attribute("username");
		this.addAttribute("username", username);
		this.addAttribute("eventos",  repoEventos.getEventosForUser(username));
		
		return this.render("calendar.hbs");
	}
	
	public String createEvento(Request req, Response res) {
		String username = req.session().attribute("username");
		this.addAttribute("username", username);
		this.addAttribute("frecuencias", EFrecuencia.getFrecuencias());
		this.addAttribute("guardarropas", wardrobeService.getWardrobesForUser(username));

		
		return this.render("event-create.hbs");
	}
	
	public String saveEvento(Request req, Response res) {
		String username = req.session().attribute("username");
		String motivo = req.queryParams("motivo");
		String lugar = req.queryParams("lugar");
		String fechaString = req.queryParams("fecha");
		String frecuenciaString = req.queryParams("frecuencia").toUpperCase();
		String guardarropaId = req.queryParams("guardarropa");
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fecha = LocalDate.parse(fechaString, formatter);
		
		Guardarropa guardarropa = wardrobeService.getGuardarropa(Long.parseLong(guardarropaId));
		
		EFrecuencia frecuencia = EFrecuencia.valueOf(frecuenciaString);		
		
		Usuario user = repoEventos.getUsuario(username);		
		
		user.crearEvento(guardarropa, fecha, lugar, motivo, frecuencia);		
		
		return username + motivo;
	}
}
