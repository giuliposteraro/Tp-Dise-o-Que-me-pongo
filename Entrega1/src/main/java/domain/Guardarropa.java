package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

public class Guardarropa {
	
	ArrayList<Prenda> prendas;
	ArrayList<Atuendo> sugerencias;
	
	ArrayList<Prenda> prendasSuperiores;
	ArrayList<Prenda> prendasInferiores;
	ArrayList<Prenda> calzados;
	ArrayList<Prenda> accesorios;
	
	public ArrayList<Atuendo> generarSugerencias() {
		Set<List<ArrayList<Prenda>>> prendasSueltas = sugerenciasDePrendas();
		return crearAtuendos(prendasSueltas);
	}
	
	public Set<List<ArrayList<Prenda>>> sugerenciasDePrendas(){
		return Sets.cartesianProduct(ImmutableList.of(ImmutableSet.of(prendasSuperiores),ImmutableSet.of(prendasInferiores),ImmutableSet.of(calzados),ImmutableSet.of(accesorios)));
	}
	
	public ArrayList<Atuendo> crearAtuendos(Set<List<ArrayList<Prenda>>> prendasSueltas){
		return prendasSueltas.stream().forEach(conjuntoDePrendas -> new Atuendo(conjuntoDePrendas));
	}
	

	
}