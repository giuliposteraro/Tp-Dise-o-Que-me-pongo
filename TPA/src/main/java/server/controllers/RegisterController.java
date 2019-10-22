package server.controllers;

import java.util.Arrays;

import domain.usuario.TipoUsuario;
import domain.usuario.UsuarioGratuito;
import domain.usuario.UsuarioPremium;
import exceptions.ParametrosNoValidos;
import persistency.services.RegisterService;
import spark.Request;
import spark.Response;

public class RegisterController extends Controller {
	public String showRegister(Request req, Response res) {
		this.addAttribute("tipos", Arrays.asList("Gratuito", "Premium"));
		
		return this.render("register.hbs");
	}
	
	public String register(Request req, Response res) {
		String username = req.queryParams("username");
		String password = req.queryParams("password");
		String tipoUsuario = req.queryParams("tipoUsuario");
		try {
			new RegisterService().register(username, password, this.getTipo(tipoUsuario));
			res.cookie("firstTime", "yes");
			res.redirect("/login");
		} catch (ParametrosNoValidos e) {
			this.addAttribute("errorRegistro", e);
		}
		
		return this.render("register.hbs");
	}
	
	private TipoUsuario getTipo(String tipoUsuario) throws ParametrosNoValidos {
		switch(tipoUsuario.toLowerCase()) {
			case "gratuito": return new UsuarioGratuito();
			case "premium": return new UsuarioPremium();
			default: throw new ParametrosNoValidos("El tipo de usuario no es valido");
		}
	}
}
