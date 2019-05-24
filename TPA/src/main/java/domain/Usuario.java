package domain;
import java.util.*;
import java.util.stream.Collectors;

import exceptions.*;

public class Usuario {
	
	private TipoUsuario tipoUsuario = new UsuarioGratuito();
	Set<Guardarropa> guardarropas = new HashSet<Guardarropa>();
	
	public void agregarGuardarropa(Guardarropa guardarropa) {
		if (!esGuardarropaValido(guardarropa)) {
			throw new NoSePuedenCompartirPrendas("No se pueden compartir prendas entre distintos guardarropas");
		} 
		this.guardarropas.add(guardarropa);
	}
	
	public void eliminarGuardarropa(Guardarropa guardarropa) {
		this.guardarropas.remove(guardarropa);
	}

	public Boolean tieneGuardarropa(Guardarropa guardarropa) {
		return guardarropas.contains(guardarropa);
	}
	
	public List<Atuendo> generarSugerencias(Guardarropa guardarropa) {
		validarAccesoAGuardarropa(guardarropa);
		return guardarropa.generarSugerencias();
	}
	
	public List<Atuendo> generarTodasLasSugerencias() {
		return guardarropas.stream()
				.flatMap(guardarropa -> generarSugerencias(guardarropa).stream())
				.collect(Collectors.toList());
	}
	
	public void agregarPrenda(Prenda prenda, Guardarropa guardarropa) {
		validarAccesoAGuardarropa(guardarropa);
		if(this.algunGuardarropaTiene(prenda)) {
			throw new NoSePuedenCompartirPrendas("No se pueden compartir prendas entre distintos guardarropas");
		}
		if(!this.validarCapacidadGuardarropa(guardarropa)) {
			throw new CapacidadDelGuardarropaLlena("Como usuario gratuito ya ocupo su capacidad maxima de prendas en este guardarropa");
		}
		guardarropa.agregarPrenda(prenda);
	}

	private void validarAccesoAGuardarropa(Guardarropa guardarropa) {
		if(!this.tieneGuardarropa(guardarropa)) {
			throw new AccesoAGuardarropaDenegado("El usuario no posee acceso a este guardarropa");
		}
	}
	
	private Boolean esGuardarropaValido(Guardarropa nuevoGuardarropa) {
		return nuevoGuardarropa.getPrendas()
				.stream()
				.allMatch(prenda -> !algunGuardarropaTiene(prenda));
	}
	
	private Boolean algunGuardarropaTiene(Prenda unaPrenda) {
		return guardarropas.stream()
				.anyMatch(guardarropa -> guardarropa.tienePrenda(unaPrenda));
	}
	
	private Boolean validarCapacidadGuardarropa(Guardarropa guardarropa) {
		return tipoUsuario.tieneLugarGuardarropa(guardarropa);
	}
}
