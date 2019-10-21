package server.controllers;
import domain.color.EColor;
import domain.tipoPrenda.ETela;
import domain.tipoPrenda.RepoTipos;
import persistency.services.WardrobeService;
import spark.Request;
import spark.Response;

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
		String id = req.params("id");
		
		this.addAttribute("guardarropa",  wardrobeService.getGuardarropa(Long.parseLong(id)));	
		this.addAttribute("username", username);
		this.addAttribute("tipos", RepoTipos.getTipos());
		this.addAttribute("telas", ETela.getTelas());
		this.addAttribute("colores", EColor.getColores());
		
		return this.render("wardrobe-content.hbs");
	}
}
