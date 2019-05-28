package domain;
import java.util.*;
import exceptions.*;

public class Usuario {
	
	private TipoUsuario tipoUsuario = new UsuarioGratuito();
	Set<Guardarropa> guardarropas = new HashSet<Guardarropa>();
	
	List<Sugerencia> sugerenciasPendientes = new ArrayList<Sugerencia>();
	
	
	// ELIMINAR
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
	
	public void generarSugerencias(Guardarropa guardarropa) {
		validarAccesoAGuardarropa(guardarropa);
		
		Sugeridor sugeridor = new Sugeridor(this, guardarropa, new Date());
		
		sugeridor.generarSugerencias();
	}
	
	public void generarTodasLasSugerencias() {
		guardarropas.stream()
			.forEach(guardarropa -> generarSugerencias(guardarropa));
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
	
	public void agregarSugerencia(Sugerencia sugerencia) {
		sugerenciasPendientes.add(sugerencia);
	}

	public List<Sugerencia> getSugerenciasPendientes() {
		return sugerenciasPendientes;
	}
}
