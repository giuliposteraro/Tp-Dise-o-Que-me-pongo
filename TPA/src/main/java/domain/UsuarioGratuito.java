package domain;

public class UsuarioGratuito implements TipoUsuario{
	
	@Override 
	public Guardarropa crearGuardarropa(Guardarropa tipoGuardarropa) {
		return new Guardarropa(new GuardarropaLimitado());
	}
	
}
