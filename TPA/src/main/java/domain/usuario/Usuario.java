package domain.usuario;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import domain.Config;
import domain.eventos.Evento;
import domain.eventos.RepositorioEventos;
import domain.guardarropa.Guardarropa;
import domain.notificaciones.INotificador;
import domain.prenda.Prenda;
import domain.sugerencias.EstadoSugerencia;
import domain.sugerencias.Sugerencia;
import domain.sugerencias.Sugeridor;
import domain.eventos.EFrecuencia;
import exceptions.*;

public class Usuario {
	
	private TipoUsuario tipo;
	private Set<Guardarropa> guardarropas = new HashSet<Guardarropa>();
	private List<Sugerencia> sugerenciasPendientes = new ArrayList<Sugerencia>();
	private List<Sugerencia> sugerenciasRevisadas = new ArrayList<Sugerencia>();
	private List<INotificador> notificadores = new ArrayList<INotificador>();
	
	
	public void compartirGuardarropaCon(Guardarropa guardarropa, Usuario usuario) {
			if(!this.tieneGuardarropa(guardarropa)) {
				throw new AccesoAGuardarropaDenegado("No se puede compartir este guardarropa");
			}
			usuario.agregarGuardarropa(guardarropa);
	}

	public void agregarGuardarropa(Guardarropa guardarropa) {
		this.guardarropas.add(guardarropa);
	}

	public Usuario(TipoUsuario tipo) {
		this.setTipo(tipo);
		Config.instance().getRepositorioUsuarios().agregarUsuario(this);
	}
	
	public Guardarropa crearGuardarropa() {
		Guardarropa guardarropa = tipo.crearGuardarropa();
		guardarropas.add(guardarropa);
		return(guardarropa);
	}
	
	public List<Double> getToleranciasAlFrio() {
		try{
			return this.sugerenciasAprobadas().stream()
					.map(s -> s.getCalificaciones())
					.reduce((subTotal,element)->vectorialSum(subTotal, element))
					.orElse(Arrays.asList(0,0,0,0))
					.stream().mapToDouble(s->s/sugerenciasAprobadas().size())
					.boxed()
					.collect(Collectors.toList());
		}catch(Exception e){
			return Arrays.asList(0.0,0.0,0.0,0.0);
		}
	}
	
	private List<Sugerencia> sugerenciasAprobadas() {
		return sugerenciasRevisadas.stream().filter(s -> s.getEstado() == EstadoSugerencia.ACEPTADA).collect(Collectors.toList());
	}
	
	public void eliminarGuardarropa(Guardarropa guardarropa) {
		this.guardarropas.remove(guardarropa);
	}

	public Boolean tieneGuardarropa(Guardarropa guardarropa) {
		return guardarropas.contains(guardarropa);
	}

	public void generarSugerencias(Guardarropa guardarropa) {
		validarAccesoAGuardarropa(guardarropa);
		
		Evento evento = new Evento(this, guardarropa, LocalDate.now(), "", "Consulta", EFrecuencia.UNICA);
		
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
		Evento eventoNuevo = new Evento(this,guardarropa,fecha,lugar,motivo,EFrecuencia.UNICA);
		RepositorioEventos repo = Config.instance().getRepositorioEventos();
		repo.agregarEvento(eventoNuevo);
	}
	
	public void aceptarSugerencia(Sugerencia sugerencia) {
		this.revisarSugerencia(sugerencia, EstadoSugerencia.ACEPTADA);
		sugerencia.ponerPrendasEnUso();
	}
	
	public void rechazarSugerencia(Sugerencia sugerencia) {
		this.revisarSugerencia(sugerencia, EstadoSugerencia.RECHAZADA);
	}
	
	private void revisarSugerencia(Sugerencia sugerencia, EstadoSugerencia estado) {
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
	
	public void agregarNotificador(INotificador notificador) {
		notificadores.add(notificador);
	}
	
	public void notificarSugerencias(Evento evento) {
		notificadores.forEach(n -> n.notificarSugerencia(evento));
	}
	
	public void notificarAlertaMeteorologica() {
		notificadores.forEach(n -> n.notificarAlertaMeteorologica());
	}

	public List<Sugerencia> getSugerenciasPendientes() {
		return sugerenciasPendientes;
	}
	
	private List<Integer> vectorialSum(List<Integer> list1, List<Integer> list2){
		return list1.stream().map(x->x + list2.get(list1.indexOf(x))).collect(Collectors.toList());
	}
	
	public List<Sugerencia> getSugerenciasRevisadas() {
		return sugerenciasRevisadas;
	}
	
	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}
	
	//
	public Set<Sugerencia>  sugerenciasPara(Evento unEvento) {
		Set<Sugerencia> mergedSet = new HashSet<Sugerencia>(); 
		  
        // add the two sets to be merged 
        // into the new set 
        mergedSet.addAll(sugerenciasRevisadas.stream().filter(sugerencia -> sugerencia.getEvento().equals(unEvento)).collect(Collectors.toSet())); 
        mergedSet.addAll(sugerenciasPendientes.stream().filter(sugerencia -> sugerencia.getEvento().equals(unEvento)).collect(Collectors.toSet()));
  
        // returning the merged set 
        return mergedSet; 
				
	
	}
	
}
