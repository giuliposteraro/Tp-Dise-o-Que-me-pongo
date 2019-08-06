package domain.usuario;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

import domain.Config;
import domain.eventos.Evento;
import domain.eventos.RepositorioEventos;
import domain.guardarropa.Guardarropa;
import domain.prenda.Prenda;
import domain.sugerencias.EstadoSugerencia;
import domain.sugerencias.Sugerencia;
import domain.sugerencias.Sugeridor;
import exceptions.*;

public class Usuario {
	
	private TipoUsuario tipo;
	Set<Guardarropa> guardarropas = new HashSet<Guardarropa>();
	List<Sugerencia> sugerenciasPendientes = new ArrayList<Sugerencia>();
	List<Sugerencia> sugerenciasRevisadas = new ArrayList<Sugerencia>();
	
	
	public void compartirGuardarropaCon(Set<Guardarropa> guardarropas, Usuario usuario) {
		if(usuario.guardarropasOcupados(guardarropas)){
			throw new NoSePuedeCompartirGuardarropa("No se pueden compartir los guardarropas porque tienen prendas en uso");
		}
		
			usuario.agregarGuardarropa(usuario.guardarropasLibres(guardarropas));
	}


	private Stream<Guardarropa> guardarropasLibres(Set<Guardarropa> guardarropas) {
		return guardarropas.stream().filter(guardarropa -> !guardarropa.tienePrendasEnUso());
	}

	public boolean guardarropasOcupados(Stream<Guardarropa> guardarropas) {
		return guardarropas.allMatch(g -> g.tienePrendasEnUso());
	}

	public void agregarGuardarropa(Set<Guardarropa> guardarropas) {
		((Set<Guardarropa>) this.guardarropas.stream()).addAll(guardarropas);
	}

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
		
		Evento evento = new Evento(this, guardarropa, LocalDate.now(), "", "Consulta");
		
		Sugeridor sugeridor = new Sugeridor(evento, Config.instance().getProveedor());
		
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
	
	public void revisarSugerencia(Sugerencia sugerencia, EstadoSugerencia estado) {
		if(!sugerenciasPendientes.contains(sugerencia)) {
			throw new NoTieneSugerenciaPendiente("Esta sugerencia no esta pendiente para este usuario");
		}
		sugerencia.setEstado(estado);
		sugerenciasPendientes.remove(sugerencia);
		sugerenciasRevisadas.add(sugerencia);
	}
	
	public void deshacerUltimaSugerenciaRevisada() {
		if(sugerenciasRevisadas.isEmpty()) {
			throw new NoHaySugerenciasRevisadas("Este usuario no tiene sugerencias para deshacer");
		}
		Sugerencia sugerenciaADeshacer = sugerenciasRevisadas.remove(sugerenciasRevisadas.size() - 1);
		sugerenciaADeshacer.setEstado(EstadoSugerencia.PENDIENTE);
		sugerenciasPendientes.add(sugerenciaADeshacer);
	}

	public List<Sugerencia> getSugerenciasPendientes() {
		return sugerenciasPendientes;
	}
	
	public List<Sugerencia> getSugerenciasRevisadas() {
		return sugerenciasRevisadas;
	}
	
	TipoUsuario getTipo() {
		return tipo;
	}

	void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}
}
