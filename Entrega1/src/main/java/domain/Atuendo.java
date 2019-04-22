package domain;

import java.util.List;

public class Atuendo {
	
	Prenda superior;
	Prenda inferior;
	Prenda calzado;
	Prenda accesorio;
	
	public Atuendo(List<Prenda> prendas) {
		this.superior = obtenerPrendaDeCategoria(ECategoria.SUPERIOR, prendas);
		this.inferior = obtenerPrendaDeCategoria(ECategoria.INFERIOR, prendas);
		this.calzado = obtenerPrendaDeCategoria(ECategoria.CALZADO, prendas);
		this.accesorio = obtenerPrendaDeCategoria(ECategoria.ACCESORIO, prendas);
	}

	private Prenda obtenerPrendaDeCategoria(ECategoria categoria, List<Prenda> prendas) {
		return prendas.parallelStream()
			.filter(prenda -> prenda.getCategoria() == categoria)
			.findFirst()
			.orElse(null);
	}

}
