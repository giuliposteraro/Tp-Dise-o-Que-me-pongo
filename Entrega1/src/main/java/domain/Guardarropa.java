package domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;

public class Guardarropa {

	Set<Prenda> prendas;

	Set<Prenda> prendasSuperiores;
	Set<Prenda> prendasInferiores;
	Set<Prenda> calzados;
	Set<Prenda> accesorios;

	public Guardarropa() {
		this.prendas = new HashSet<Prenda>();
	}

	public List<Atuendo> generarSugerencias() throws Exception {
		validarListas();
		return crearAtuendos(sugerenciasDePrendas());
	}

	public Boolean tienePrenda(Prenda unaPrenda) {
		return prendas.contains(unaPrenda);
	}

	public void agregarPrenda(Prenda prenda) {
		prendas.add(prenda);
		actualizarCategorias();
	}

	public void eliminarPrenda(Prenda prenda) {
		prendas.remove(prenda);
		actualizarCategorias();
	}

	private Set<List<Prenda>> sugerenciasDePrendas() {
		return Sets.cartesianProduct(ImmutableList.of(prendasSuperiores, prendasInferiores, calzados, accesorios));
	}

	private List<Atuendo> crearAtuendos(Set<List<Prenda>> prendasSueltas) {
		return prendasSueltas.stream().map(conjuntoDePrendas -> new Atuendo(conjuntoDePrendas))
				.collect(Collectors.toList());
	}

	private void actualizarCategorias() {
		prendasSuperiores = filtrarPrendasPorCategoria(ECategoria.SUPERIOR);
		prendasInferiores = filtrarPrendasPorCategoria(ECategoria.INFERIOR);
		calzados = filtrarPrendasPorCategoria(ECategoria.CALZADO);
		accesorios = filtrarPrendasPorCategoria(ECategoria.ACCESORIO);
	}

	private Set<Prenda> filtrarPrendasPorCategoria(ECategoria categoria) {
		return prendas.stream().filter(p -> p.getCategoria() == categoria).collect(Collectors.toSet());
	}

	private void validarListas() throws Exception {
		if (prendasSuperiores.isEmpty() || prendasInferiores.isEmpty() || calzados.isEmpty() || accesorios.isEmpty())
			throw new Exception("No se pueden generar sugerencias en este guardarropa");
	}
}
