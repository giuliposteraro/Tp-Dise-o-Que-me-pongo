package domain;

import java.util.Arrays;
import java.util.List;

import domain.color.Color;
import domain.color.EColor;
import domain.prenda.Prenda;
import domain.prenda.RepoPrendas;
import domain.tipoPrenda.ECategoria;
import domain.tipoPrenda.ETela;

public class Atuendo {
	
	Prenda abrigo;
	Prenda superior;
	Prenda inferior;
	Prenda calzado;
	Prenda accesorio;
	
	public Atuendo(List<Prenda> conjuntoDePrendas) {
		this.abrigo = obtenerPrendaDeCategoria(ECategoria.ABRIGO, conjuntoDePrendas);
		this.superior = obtenerPrendaDeCategoria(ECategoria.SUPERIOR, conjuntoDePrendas);
		this.inferior = obtenerPrendaDeCategoria(ECategoria.INFERIOR, conjuntoDePrendas);
		this.calzado = obtenerPrendaDeCategoria(ECategoria.CALZADO, conjuntoDePrendas);
		this.accesorio = obtenerPrendaDeCategoria(ECategoria.ACCESORIO, conjuntoDePrendas);
	}

	private Prenda obtenerPrendaDeCategoria(ECategoria categoria, List<Prenda> conjuntoDePrendas) {
		return conjuntoDePrendas.stream()
			.filter(prenda -> categoria.equals(prenda.getCategoria()))
			.findFirst()
			.orElse(null);
	}
	
	public List<Prenda> prendas() {
		return Arrays.asList(superior, abrigo, inferior, calzado, accesorio);
	}

	public Double getNivelAbrigo() {
		return abrigo.getNivelAbrigo() + superior.getNivelAbrigo() + inferior.getNivelAbrigo() + calzado.getNivelAbrigo() + accesorio.getNivelAbrigo();
	}

}
