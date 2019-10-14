package server.controllers;

import java.util.HashMap;
import java.util.Map;

import persistency.services.LoginService;
import spark.Request;
import spark.Response;

public class LoginController extends Controller {
	LoginService loginService = new LoginService();

	public String loguear(Request req, Response res) {
		if (usuarioAutenticado(req) != null)
			res.redirect("/home");

		String username = req.queryParams("username");
		String password = req.queryParams("password");

		Map<String, Object> viewModel = new HashMap<String, Object>();

		if (username != null) {
			if (loginService.autenticar(username, password)) {
				req.session(true);
				req.session().attribute("username", username);
				res.redirect("/home");
			} else {
				viewModel.put("username", username);
				viewModel.put("errorLogueo", true);
			}
		}

		return this.render(viewModel, "login.hbs");

	}

	public void verificarAutenticacion(Request req, Response res) {
		if (!req.url().contains("/login") && usuarioAutenticado(req) == null)
			res.redirect("/login");
	}

	private String usuarioAutenticado(Request req) {
		return req.session().attribute("username");
	}
}
