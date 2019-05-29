package domain.usuario;

import domain.Guardarropa;
import domain.GuardarropaLimitado;

public class UsuarioGratuito implements TipoUsuario{
	
	@Override 
	public Guardarropa crearGuardarropa() {
		return new Guardarropa(new GuardarropaLimitado());
	}
	
}
