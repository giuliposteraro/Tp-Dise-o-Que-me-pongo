package domain;

public class UsuarioPremium implements TipoUsuario{
	@Override 
	public Guardarropa crearGuardarropa(Guardarropa tipoGuardarropa) {
		return new Guardarropa(new GuardarropaIlimitado());
	}
	
}
