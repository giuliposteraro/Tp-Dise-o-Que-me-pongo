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
	String nombre;
	
	public Evento(Usuario unUsuario, Guardarropa unGuardarropa, LocalDate unaFecha, String unLugar, String unNombre) {
		this.usuario = unUsuario;
		this.guardarropa = unGuardarropa;
		this.fecha = unaFecha;
		this.lugar = unLugar;
		this.nombre = unNombre;
	}
	
	public boolean esProximo() {  // es proximo si el evento es hoy mismo
		return fecha.isEqual(LocalDate.now());
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

	public String getNombre() {
		return nombre;
	}
}