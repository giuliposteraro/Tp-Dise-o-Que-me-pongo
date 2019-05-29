package domain.usuario;

import domain.Guardarropa;
import domain.GuardarropaIlimitado;

public class UsuarioPremium implements TipoUsuario{
	@Override 
	public Guardarropa crearGuardarropa() {
		return new Guardarropa(new GuardarropaIlimitado());
	}
	
}
