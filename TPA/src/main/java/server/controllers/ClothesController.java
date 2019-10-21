package server.controllers;

import domain.color.EColor;
import domain.prenda.ConstructorPrenda;
import domain.prenda.Prenda;
import domain.tipoPrenda.ETela;
import domain.tipoPrenda.RepoTipos;
import persistency.services.WardrobeService;
import spark.Request;
import spark.Response;

public class ClothesController extends Controller {
	public String newClothe(Request req, Response res) {
		Long idGuardarropa = Long.parseLong(req.queryParams("idGuardarropa"));
		String tipo = req.queryParams("tipo");
		String color1 = req.queryParams("color1").toUpperCase();
		String color2 = req.queryParams("color2").toUpperCase();
		String tela = req.queryParams("tela").toUpperCase();
		
		try {
			ConstructorPrenda constPrenda = new ConstructorPrenda();
			constPrenda.setTipo(RepoTipos.getTipo(tipo));
			constPrenda.setColor(EColor.valueOf(color1), EColor.valueOf(color2));
			constPrenda.setTela(ETela.valueOf(tela));
			Prenda prenda = constPrenda.crear();
			new WardrobeService().getGuardarropa(idGuardarropa).agregarPrenda(prenda);
		} catch (Exception e) {
			this.addAttribute("error", "No se pudo crear la prenda: " + e.getMessage());
		}
		this.addAttribute("idGuardarropa", idGuardarropa);
		
		return this.render("new-clothe.hbs");
	}
}
