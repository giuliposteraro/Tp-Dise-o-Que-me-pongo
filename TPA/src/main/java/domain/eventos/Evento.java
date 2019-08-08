package domain.eventos;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

import org.uqbar.arena.bindings.DateTransformer;
import org.uqbar.commons.model.annotations.Observable;

import domain.usuario.*;
import domain.Config;
import domain.guardarropa.Guardarropa;
import domain.sugerencias.*;

@Observable
public class Evento {
	
	Guardarropa guardarropa;
	Usuario usuario;
	Date fecha;
	String lugar;
	String motivo;
	EstadoEvento estado;
	
	public Evento(Usuario unUsuario, Guardarropa unGuardarropa, LocalDate unaFecha, String unLugar, String unMotivo) {
		this.usuario = unUsuario;
		this.guardarropa = unGuardarropa;
		this.fecha = Date.from(unaFecha.atStartOfDay(ZoneId.systemDefault()).toInstant());
		this.lugar = unLugar;
		this.motivo = unMotivo;
		this.estado = EstadoEvento.PENDIENTE;
	}
	
	public boolean esProximo() {  // es proximo si el evento es hoy mismo
		return fecha.toInstant()
			      	.atZone(ZoneId.systemDefault())
			      	.toLocalDate().isEqual(LocalDate.now());
	}
	
	public boolean estaPendiente() {
		return this.estado.equals(EstadoEvento.PENDIENTE); //
	}
	
	public void marcarComoPasado() {
		this.estado = EstadoEvento.PASADO;
	}
	public void sugerir() {
		Sugeridor unSugeridor = new Sugeridor(this, Config.instance().getProveedor());
		unSugeridor.generarSugerencias();
	}	
	
	public Guardarropa getGuardarropa() {
		return guardarropa;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public Set<Sugerencia> sugerencias() {
		return this.usuario.sugerenciasPara(this);
	}
}