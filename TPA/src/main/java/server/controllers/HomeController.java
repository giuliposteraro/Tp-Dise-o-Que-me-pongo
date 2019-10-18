package server.controllers;

import spark.Request;
import spark.Response;

public class HomeController extends Controller {
	
	public String showHome(Request req, Response res) {
		this.addAttribute("username", req.session().attribute("username"));
		return this.render("home.hbs");
	}
	
}
