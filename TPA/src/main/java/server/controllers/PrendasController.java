package server.controllers;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class PrendasController {
	public String crear(Request req, Response res) {
		
//		res.cookie("builder", "{'name':'saigucci','ganks':'50'}");
//		String builder = req.cookie("builder");
		
		HashMap<String, Object> model = new HashMap<String, Object>();
		
		try {
			JSONObject inventi = new JSONObject("{'name':'saigucci','ganks':'50'}");
			String name = inventi.getString("name");
			Integer ganks = inventi.getInt("ganks");
			model.put("name", name);
			model.put("ganks", ganks.toString());
			System.out.println(name + ganks.toString());
		} catch(JSONException e) {
			System.out.println(e.getMessage());
		}
	
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, "layout.hbs"));
	}
}
