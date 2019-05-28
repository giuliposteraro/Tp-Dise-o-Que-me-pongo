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
	
	Prenda superior;
	Prenda abrigo;
	Prenda inferior;
	Prenda calzado;
	Prenda accesorio;
	
	public Atuendo(List<Prenda> conjuntoDePrendas) {
		this.superior = obtenerPrendaDeCategoria(ECategoria.SUPERIOR, conjuntoDePrendas);
		this.abrigo = obtenerPrendaDeCategoria(ECategoria.ABRIGO, conjuntoDePrendas);
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
	
	public static Prenda SIN_ACCESORIO = new Prenda(RepoPrendas.SIN_ACCESORIO, ETela.NINGUNA, new Color(EColor.NINGUNO, EColor.NINGUNO));
	public static Prenda SIN_ABRIGO = new Prenda(RepoPrendas.SIN_ABRIGO, ETela.NINGUNA, new Color(EColor.NINGUNO, EColor.NINGUNO));

}
