package domain.usuario;
import java.time.LocalDate;
import java.util.*;

import domain.Config;
import domain.Guardarropa;
import domain.eventos.Evento;
import domain.eventos.RepositorioEventos;
import domain.prenda.Prenda;
import domain.sugerencias.Sugerencia;
import domain.sugerencias.Sugeridor;
import exceptions.*;

public class Usuario {
	
	private TipoUsuario tipo;
	Set<Guardarropa> guardarropas = new HashSet<Guardarropa>();
	List<Sugerencia> sugerenciasPendientes = new ArrayList<Sugerencia>();
	
	public Usuario(TipoUsuario tipo) {
		this.setTipo(tipo);
	}
	
	public Guardarropa crearGuardarropa() {
		Guardarropa guardarropa = tipo.crearGuardarropa();
		guardarropas.add(guardarropa);
		return(guardarropa);
	}
	
	public void eliminarGuardarropa(Guardarropa guardarropa) {
		this.guardarropas.remove(guardarropa);
	}

	public Boolean tieneGuardarropa(Guardarropa guardarropa) {
		return guardarropas.contains(guardarropa);
	}
	
	public void generarSugerencias(Guardarropa guardarropa) {
		validarAccesoAGuardarropa(guardarropa);
		
		Sugeridor sugeridor = new Sugeridor(this, guardarropa/*, Config.instance().getProveedor()*/);
		
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
	
	private Boolean algunGuardarropaTiene(Prenda unaPrenda) {
		return guardarropas.stream()
				.anyMatch(guardarropa -> guardarropa.tienePrenda(unaPrenda));
	}
	
	private Boolean validarCapacidadGuardarropa(Guardarropa guardarropa) {
		return guardarropa.tieneLugar();
	}
	
	public void agregarSugerencia(Sugerencia sugerencia) {
		sugerenciasPendientes.add(sugerencia);
	}
	
	public void crearEvento(Guardarropa guardarropa, LocalDate fecha, String lugar, String motivo) {
		Evento eventoNuevo = new Evento(this,guardarropa,fecha,lugar,motivo);
		RepositorioEventos repo = Config.instance().getRepositorioEventos();
		repo.agregarEvento(eventoNuevo);
	}

	public List<Sugerencia> getSugerenciasPendientes() {
		return sugerenciasPendientes;
	}

	TipoUsuario getTipo() {
		return tipo;
	}

	void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}
}
