package domain;

public class UsuarioGratuito implements TipoUsuario{
	
	@Override
	public Guardarropa crearGuardarropa() {
		return new GuardarropaLimitado();
	}
	
}
