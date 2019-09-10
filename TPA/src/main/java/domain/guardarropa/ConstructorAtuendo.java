package domain.guardarropa;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import domain.prenda.Prenda;
import exceptions.ParametrosNoValidos;

public class ConstructorAtuendo {

	private Set<Prenda> prendas = new HashSet<Prenda>();

	public void agregarPrenda(Prenda prenda) {
		prendas.add(prenda);
	}
	
	public void agregarPrendas(Collection<Prenda> prendas) {
		prendas.addAll(prendas);
	}

	public void eliminarPrendas() {
		prendas.clear();
	}

	public Atuendo crear() {
		if(!Atuendo.esAtuendoValido(this.prendas)) {
			throw new ParametrosNoValidos("Las prendas ingresadas no forman un atuendo valido.");
		}
		Atuendo atuendo = new Atuendo(this.prendas);
		this.eliminarPrendas();
		return atuendo;
	}
}
