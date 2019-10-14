package server.controllers;

import java.util.Map;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public abstract class Controller {
	protected String render(Map<String, Object> viewModel, String template) {
		ModelAndView mav= new ModelAndView(viewModel, template);
		return (new HandlebarsTemplateEngine().render(mav));
	}
}
