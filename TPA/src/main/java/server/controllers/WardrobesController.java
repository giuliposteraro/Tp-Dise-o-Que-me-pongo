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
	
	public String showWardrobeContent(Request req, Response res) {
		String username = req.session().attribute("username");
		String guardarropa = req.params("guardarropa");
		
		this.addAttribute("guardarropa",  wardrobeService.getGuardarropa(Long.parseLong(guardarropa)));
		System.out.println(wardrobeService.getGuardarropa(Long.parseLong(guardarropa)).getPrendas());
		this.addAttribute("username", username);
		this.addAttribute("tipos", RepoTipos.getTipos());
		this.addAttribute("telas", ETela.getTelas());
		this.addAttribute("colores", EColor.getColores());
		
		return this.render("wardrobe-content.hbs");
	}
}
