package server.controllers;

import spark.Request;
import spark.Response;

public class ClothesController extends Controller {
	public String newClothe(Request req, Response res) {
		return "Prenda agregada succesfully";
	}
}
