package domain;

import java.util.Arrays;
import java.util.List;

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
		return conjuntoDePrendas.parallelStream()
			.filter(prenda -> prenda.getCategoria() == categoria)
			.findFirst()
			.orElse(null);
	}
	
	public List<Prenda> prendas() {
		return Arrays.asList(superior, inferior, calzado, accesorio);
	}

}
