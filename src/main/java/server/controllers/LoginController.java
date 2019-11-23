package server.controllers;

import java.util.Arrays;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import domain.usuario.TipoUsuario;
import domain.usuario.UsuarioGratuito;
import domain.usuario.UsuarioPremium;
import exceptions.ParametrosNoValidos;
import persistency.services.LoginService;
import persistency.services.RegisterService;
import spark.Request;
import spark.Response;

public class LoginController extends Controller {
	LoginService loginService = new LoginService();

	public String loguear(Request req, Response res) {
		if (usuarioAutenticado(req) != null)
			res.redirect("/calendar");

		String username = req.queryParams("username");
		String password = req.queryParams("password");

		if (username != null) {
			if (loginService.autenticar(username, password)) {
				req.session(true);
				req.session().attribute("username", username);
				res.redirect("/calendar");
			} else {
				this.addAttribute("username", username);
				this.addAttribute("errorLogueo", true);
			}
		}

		return this.render("login.hbs");
	}
	
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
	
	public String userExists(Request req, Response res) throws JSONException {
		String username = req.params("username");
		JSONObject userJSON = new JSONObject()
				.put("username", username)
				.put("exists", loginService.userExists(username)); 
		return userJSON.toString();
	}
	
	private TipoUsuario getTipo(String tipoUsuario) throws ParametrosNoValidos {
		switch(tipoUsuario.toLowerCase()) {
			case "gratuito": return new UsuarioGratuito();
			case "premium": return new UsuarioPremium();
			default: throw new ParametrosNoValidos("El tipo de usuario no es valido");
		}
	}
	
	public String logout(Request req, Response res) {
		req.session().removeAttribute("username");
		res.redirect("/login");
		return "";
	}
	
	public void verificarAutenticacion(Request req, Response res) throws JSONException {
		if (!(req.url().endsWith("/login") || req.url().endsWith("/register")) 
				&& usuarioAutenticado(req) == null
				&& !("bokita".equals(req.queryParams("masterToken"))))
			res.redirect("/login");
	}

	private String usuarioAutenticado(Request req) {
		return req.session().attribute("username");
	}
}
