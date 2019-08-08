package domain.guardarropa;

import java.util.Arrays;
import java.util.List;

import domain.prenda.Prenda;
import domain.tipoPrenda.ECategoria;

public class Atuendo {
	
	private Prenda abrigo;
	private Prenda superior;
	private Prenda inferior;
	private Prenda calzado;
	private Prenda accesorio;
	
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

	public void ponerPrendasEnUso() {
		superior.setEnUso(true);
		inferior.setEnUso(true);
		calzado.setEnUso(true);
		if(!abrigo.equals(Prenda.SIN_ABRIGO)) // TODO que las prendas incompletas entiendan el enUso pero no hagan nada.
			abrigo.setEnUso(true);
		if(!accesorio.equals(Prenda.SIN_ACCESORIO))
			accesorio.setEnUso(true);
	}

}
