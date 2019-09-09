package domain.eventos;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

import org.uqbar.arena.bindings.DateTransformer;
import org.uqbar.commons.model.annotations.Observable;
import java.util.HashSet;
import java.util.Set;
import java.time.temporal.ChronoUnit;

import domain.usuario.*;
import domain.Config;
import domain.guardarropa.Guardarropa;
import domain.sugerencias.*;

@Observable
public class Evento {
	
	private Guardarropa guardarropa;
	private Usuario usuario;
	private LocalDate fecha;
	private String lugar;
	private String motivo;
	private Set<Sugerencia> sugerencias;
	private EFrecuencia frecuencia;
	private Boolean pendiente;
		
	public Evento(Usuario unUsuario, Guardarropa unGuardarropa, LocalDate unaFecha, String unLugar, String unMotivo, EFrecuencia unaFrecuencia) {
		this.usuario = unUsuario;
		this.guardarropa = unGuardarropa;
		this.fecha = unaFecha;
		this.lugar = unLugar;
		this.motivo = unMotivo;

		this.frecuencia = unaFrecuencia;
		this.sugerencias = new HashSet<Sugerencia>();
		this.pendiente = true;
	}
	
	public boolean esProximo() {  // es proximo si el evento es antes de maniana
		return fecha.isBefore(LocalDate.now().plus(2,ChronoUnit.DAYS));
	}
	
	public void sugerir() {
		Sugeridor unSugeridor = new Sugeridor(this, Config.instance().getProveedor());
		unSugeridor.generarSugerencias();
		this.recalcularFecha();
	}	
	
	public void recalcularFecha() {
		this.pendiente = false;
		if(!frecuencia.equals(Frecuencia.UNICA)) {
			frecuencia.actualizarFecha(this);
		}
	}
	
	public Guardarropa getGuardarropa() {
		return guardarropa;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public boolean entre(LocalDate fecha1, LocalDate fecha2) {
		return this.fecha.isBefore(fecha2.plusDays(1)) && this.fecha.isAfter(fecha1.minusDays(1)); 
	}
	
	public void agregarSugerencia(Sugerencia sug) {
		this.sugerencias.add(sug);
	}
	
	public boolean proximoPendiente() {
		return this.esProximo() && this.pendiente(); 
	}
	
	public void pendiente(boolean pend) {
		pendiente = pend;
	}
	
	public LocalDate getFecha() {
		return this.fecha;
	}
	
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	public boolean pendiente() {
		return pendiente;
	}

	public String getMotivo() {
		return motivo;
	}

	public String getLugar() {
		return lugar;
	}
	
	public Evento eventoActualizado(LocalDate nuevaFecha) {
		return new Evento(this.usuario, this.guardarropa, nuevaFecha, this.lugar, this.motivo, this.frecuencia); 
	}

	public Boolean getTieneSugerencias() {
		return !sugerencias.isEmpty();
	}
	
	public void setTieneSugerencias() {}

	public Set<Sugerencia> getSugerencias() {
		return sugerencias;
	}

	public void setSugerencias(Set<Sugerencia> sugerencias) {
		this.sugerencias = sugerencias;
	}

}