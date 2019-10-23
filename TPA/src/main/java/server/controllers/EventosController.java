package server.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import domain.eventos.EFrecuencia;
import domain.eventos.RepositorioEventos;
import domain.guardarropa.Guardarropa;
import domain.usuario.RepositorioUsuarios;
import domain.usuario.Usuario;
import persistency.services.WardrobeService;
import spark.Request;
import spark.Response;

public class EventosController extends Controller {
	WardrobeService wardrobeService = new WardrobeService();
	RepositorioUsuarios repoUsuarios = new RepositorioUsuarios();
	RepositorioEventos repoEventos = new RepositorioEventos(repoUsuarios);
		
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
		
		Usuario user = repoUsuarios.getUsuario(username);		
		
		user.crearEvento(guardarropa, fecha, lugar, motivo, frecuencia);		
		
		return username + motivo;
	}
}
