package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

public class Guardarropa {
	
	ArrayList<Prenda> prendas;
	
	ArrayList<Prenda> prendasSuperiores;
	ArrayList<Prenda> prendasInferiores;
	ArrayList<Prenda> calzados;
	ArrayList<Prenda> accesorios;
	
	public List<Atuendo> generarSugerencias() {
		Set<List<Prenda>> prendasSueltas = sugerenciasDePrendas();
		return crearAtuendos(prendasSueltas);
	}
	
	public Boolean tienePrenda (Prenda unaPrenda) {
		return prendas.contains(unaPrenda);
	}
	
	private Set<List<Prenda>> sugerenciasDePrendas(){
		return Sets.cartesianProduct(ImmutableList.of(ImmutableSet.copyOf(prendasSuperiores),ImmutableSet.copyOf(prendasInferiores),ImmutableSet.copyOf(calzados),ImmutableSet.copyOf(accesorios)));
	}
	
	private List<Atuendo> crearAtuendos(Set<List<Prenda>> prendasSueltas){
		return prendasSueltas.stream().map(conjuntoDePrendas -> new Atuendo(conjuntoDePrendas)).collect(Collectors.toList());
	}
	
	private void actualizarCategorias() {
		prendasSuperiores = prendas.stream().filter(p->p.getGategoria()==ECategoria.SUPERIOR);
		prendasInferiores = prendas.stream().filter(p->p.getGategoria()==ECategoria.INFERIOR);
		calzados = prendas.stream().filter(p->p.getGategoria()==ECategoria.CALZADO);
		accesorios = prendas.stream().filter(p->p.getGategoria()==ECategoria.ACCESORIO);
	}
	
	
}
