package server.controllers;

import domain.eventos.Evento;
import domain.eventos.RepositorioEventos;
import domain.sugerencias.Calificacion;
import domain.sugerencias.EstadoSugerencia;
import domain.sugerencias.Sugerencia;
import domain.usuario.RepositorioUsuarios;
import domain.usuario.Usuario;
import exceptions.NoSePuedeGenerarSugerencia;
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
		String id_sugerencia = req.params("suggestion");
		
		this.addAttribute("sugerencia",  sugerenciasService.getSugerencia(Long.parseLong(id_sugerencia)));
		
		return this.render("sugerencias-content.hbs");
	}
	
	public String generarSugerencias(Request req, Response res) {
		String id_evento = req.params("event");
		Evento evento = repoEventos.getEvento(Long.parseLong(id_evento));
		String username = req.session().attribute("username");
		
		this.addAttribute("username", username);
		this.addAttribute("id_evento", id_evento);
		this.addAttribute("evento", sugerenciasService.getMotivoEvento(Long.parseLong(id_evento)));
		
		try {
			evento.sugerir();
			this.addAttribute("sugerencias",  sugerenciasService.getSugerenciasForEvent(Long.parseLong(id_evento)));
		} catch (NoSePuedeGenerarSugerencia e) {
			this.addAttribute("error", e.getMessage());
		}
		
		return this.render("sugerencias.hbs");		
	}
  
	public String calificarSugerencia(Request req, Response res) {
		String sugerenciaId = req.params("sug");
		String notaString = req.queryParams("nota");
		
		Double nota = Double.parseDouble(notaString);
		
		Sugerencia sug = sugerenciasService.getSugerencia(Long.parseLong(sugerenciaId));
		
		Calificacion c = new Calificacion(nota,nota,nota);
		
		sugerenciasService.persistCalificacion(c);
		sug.setCalificacion(c);
		
		res.redirect("/suggestions");
		
		return "";
	}
  
	public String decidirSugerencia(Request req, Response res) {
		String username = req.session().attribute("username");
		
		String id_sugerencia = req.params("suggestion");
		String decision = req.params("decision").toUpperCase();
		
		Sugerencia sug = sugerenciasService.getSugerencia(Long.parseLong(id_sugerencia));
		String id_evento = sug.getEvento().getId_evento().toString();	
		
		
		Usuario user = repoUsuarios.getUsuario(username);
		
		if(decision.equals("RECHAZADA"))
			user.rechazarSugerencia(sug);
		else
			user.aceptarSugerencia(sug);
					
		res.redirect("/calendar/"+ id_evento + "/suggestions");
		
		return "";
	}
	
	public String mostrarSugerenciasAceptadas(Request req, Response res) {
		String username = req.session().attribute("username");
		
		this.addAttribute("username", username);
		this.addAttribute("sugerenciasAprobadas", sugerenciasService.getSugerenciasForUser(username));
		
		return this.render("calificacion-sugerencias.hbs");
		
		
	}
	
	
}
	

