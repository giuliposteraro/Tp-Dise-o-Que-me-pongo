package domain.eventos;
import java.time.LocalDate;

import domain.usuario.*;
import domain.guardarropa.Guardarropa;
import domain.sugerencias.*;

public class Evento {
	
	Guardarropa guardarropa;
	Usuario usuario;
	LocalDate fecha;
	String lugar;
	String motivo;
	
	public Evento(Usuario unUsuario,Guardarropa unGuardarropa,LocalDate unaFecha,String unLugar, String unMotivo) {
		this.usuario=unUsuario;
		this.guardarropa=unGuardarropa;
		this.fecha=unaFecha;
		this.lugar=unLugar;
		this.motivo=unMotivo;
	}
	
	public boolean esProximo() {  // es pr�ximo si el evento es hoy mismo
		return fecha.isEqual(LocalDate.now());
	}
	
	public void sugerir() {   
		Sugeridor unSugeridor = new Sugeridor(usuario,guardarropa/*,provClima*/);
		unSugeridor.generarSugerencias();
	}
	
	
	
	
	
	
	
	
	
}