package domain;
import java.util.*;
import java.util.stream.Collectors;

import exceptions.*;

public class Usuario {
	
	Set<Guardarropa> guardarropas = new HashSet<Guardarropa>();
	
	public void agregarGuardarropa(Guardarropa guardarropa) {
		if (!esValidoUn(guardarropa)) {
			throw new NoSePuedenCompartirPrendas("No se pueden compartir prendas entre distintos guardarropas");
		} 
		this.guardarropas.add(guardarropa);
	}
	
	private Boolean esValidoUn(Guardarropa nuevoGuardarropa) {
		return nuevoGuardarropa.getPrendas()
				.stream()
				.allMatch(prenda -> !algunGuardarropaTiene(prenda));
	}
	
	private Boolean algunGuardarropaTiene(Prenda unaPrenda) {
		return guardarropas.stream()
				.anyMatch(guardarropa -> guardarropa.tienePrenda(unaPrenda));
	}
	
	public void eliminarGuardarropa(Guardarropa guardarropa) {
		this.guardarropas.remove(guardarropa);
	}

	public Boolean tieneGuardarropa(Guardarropa guardarropa) {
		return guardarropas.contains(guardarropa);
	}
	
	public List<Atuendo> generarSugerencias(Guardarropa guardarropa) {
		if(!tieneGuardarropa(guardarropa))
			throw new NoSePuedeGenerarSugerencia("No tiene el guardarropa solicitado");
		return guardarropa.generarSugerencias();
	}
	
	public List<List<Atuendo>> generarTodasLasSugerencias() {
		return guardarropas.stream()
				.map(guardarropa -> generarSugerencias(guardarropa))
				.collect(Collectors.toList());
	}
}
