package domain;

public class UsuarioGratuito implements TipoUsuario{
	
	public Boolean tieneLugarGuardarropa(Guardarropa guardarropa) {
		return guardarropa.tieneLugar();
	}
	
	public Guardarropa crearGuardarropa() {
		return new GuardardarropaLimitado();
	}
	
	
}
