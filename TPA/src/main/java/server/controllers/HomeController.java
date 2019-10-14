package server.controllers;

import java.util.HashMap;
import java.util.Map;

import spark.Request;
import spark.Response;

public class HomeController extends Controller {
	
	public String showHome(Request req, Response res) {
		
		Map<String, Object> viewModel = new HashMap<String, Object>();
		viewModel.put("username", req.session().attribute("username"));
		
		return this.render(viewModel, "home.hbs");
	}
	
}
