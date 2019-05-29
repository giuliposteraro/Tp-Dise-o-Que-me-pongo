package domain.eventos;
import java.time.LocalDate;
import domain.Guardarropa;
import domain.usuario.*;
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
	
	public boolean esProximo() {  // es próximo si el evento es hoy mismo
		return fecha == LocalDate.now();
	}
	
	public void sugerir() {   
		Sugeridor unSugeridor = new Sugeridor(usuario,guardarropa/*,provClima*/);
		unSugeridor.generarSugerencias();
	}
	
	
	
	
	
	
	
	
	
}