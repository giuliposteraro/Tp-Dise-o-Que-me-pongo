package domain.eventos;
import java.time.LocalDate;

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
	EstadoEvento estado;
	
	public Evento(Usuario unUsuario, Guardarropa unGuardarropa, LocalDate unaFecha, String unLugar, String unMotivo) {
		this.usuario = unUsuario;
		this.guardarropa = unGuardarropa;
		this.fecha = unaFecha;
		this.lugar = unLugar;
		this.motivo = unMotivo;
		this.estado = EstadoEvento.PENDIENTE;
	}
	
	public boolean esProximo() {  // es proximo si el evento es hoy mismo
		return fecha.isEqual(LocalDate.now());
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
}