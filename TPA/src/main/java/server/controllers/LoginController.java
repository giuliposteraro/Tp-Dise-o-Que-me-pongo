package server.controllers;
import java.util.HashMap;

import persistency.services.LoginService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class LoginController {
	LoginService loginService = new LoginService();
	
	public String loguear(Request req,Response res) {
		String username = req.queryParams("username");
		String password = req.queryParams("password");
		HashMap<String,Object> viewModel = new HashMap();
		if (username!=null) {
			if (loginService.usuarioValido(username,password)) {
				res.redirect("/home");
			}else {
				viewModel.put("username",username);
				viewModel.put("errorLogueo",true);
			}
		}
		ModelAndView mav= new ModelAndView(viewModel,"login.hbs");
		return (new HandlebarsTemplateEngine().render(mav));	
		
	}
	
}
