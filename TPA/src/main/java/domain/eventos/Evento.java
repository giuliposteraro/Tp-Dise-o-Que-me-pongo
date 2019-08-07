package domain.eventos;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.time.temporal.ChronoUnit;

import domain.usuario.*;
import domain.Config;
import domain.guardarropa.Guardarropa;
import domain.sugerencias.*;

public class Evento {
	
	Guardarropa guardarropa;
	Usuario usuario;
	LocalDate fecha;
	String lugar;
	String motivo;
	Set<Sugerencia> sugerencias;
	Frecuencia frecuencia;
	Boolean pendiente;
	
	
	public Evento(Usuario unUsuario, Guardarropa unGuardarropa, LocalDate unaFecha, String unLugar, String unMotivo, Frecuencia unaFrecuencia) {
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
		frecuencia.actualizarFecha(this);
		if(frecuencia.equals(Frecuencia.UNICA))
			this.pendiente = false;
	}
	
	public Guardarropa getGuardarropa() {
		return guardarropa;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public boolean entre(LocalDate fecha1, LocalDate fecha2) {
		return this.fecha.isBefore(fecha2) && this.fecha.isAfter(fecha1); 
	}
	
	public void agregarSugerencia(Sugerencia sug) {
		sugerencias.add(sug);
	}
	
	public boolean proximoPendiente() {
		return this.esProximo() && this.pendiente(); 
	}
	
	public void pendiente(boolean pend) {
		pendiente = pend;
	}
	
	public void fecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	public boolean pendiente() {
		return pendiente;
	}

	public LocalDate fecha() {
		return fecha;
	}
}