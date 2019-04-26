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

	public Boolean tieneGuardarropa(Guardarropa g) {
		return guardarropas.contains(g);
	}
	
	public List<Atuendo> generarSugerencias(Guardarropa guardarropa) throws NoSePuedeGenerarSugerencia {
		if(tieneGuardarropa(guardarropa)){
			return guardarropa.generarSugerencias();
		}
		else {
			throw new NoSePuedeGenerarSugerencia("No tiene el guardarropa solicitado");	
		}
	}
	
	public List<List<Atuendo>> generarTodasLasSugerencias() throws NoSePuedeGenerarSugerencia {
		return guardarropas.stream()
				.map(guardarropa -> generarSugerencias(guardarropa))
				.collect(Collectors.toList());
	}
}
