package domain;

import java.util.Arrays;
import java.util.List;

import domain.prenda.Prenda;
import domain.tipoPrenda.ECategoria;

public class Atuendo {
	
	Prenda superior;
	Prenda inferior;
	Prenda calzado;
	Prenda accesorio;
	
	public Atuendo(List<Prenda> conjuntoDePrendas) {
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
		return Arrays.asList(superior, inferior, calzado, accesorio);
	}

}
