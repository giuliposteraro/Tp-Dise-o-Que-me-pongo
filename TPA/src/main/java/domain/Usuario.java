package domain;
import java.util.*;
import java.util.stream.Collectors;

import exceptions.*;

public class Usuario {
	
	Set<Guardarropa> guardarropas = new HashSet<Guardarropa>();
	
	public void agregarGuardarropa(Guardarropa guardarropa) {
		this.guardarropas.add(guardarropa);
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
