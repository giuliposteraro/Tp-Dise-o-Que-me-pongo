package domain;

public class UsuarioPremium implements TipoUsuario{
	
	@Override 
	public Guardarropa crearGuardarropa() {
		return new GuardarropaIlimitado();
	}
	
}
