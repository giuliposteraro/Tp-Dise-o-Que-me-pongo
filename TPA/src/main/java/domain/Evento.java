package domain;
import java.time;

public class Evento {
	
	Guardarropa guardarropa;
	Usuario usuario;
	LocalDate fecha;
	String lugar;
	String motivo;
	
	public Evento(unUsuario,unGuardarropa,unaFecha,unLugar,unMotivo) {
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
		Sugeridor unSugeridor = new Sugeridor(usuario,guardarropa,/*proveedorClima*/,motivo);
		unSugeridor.generarSugerencia();
	}
	
	
	
	
	
	
	
	
	
}