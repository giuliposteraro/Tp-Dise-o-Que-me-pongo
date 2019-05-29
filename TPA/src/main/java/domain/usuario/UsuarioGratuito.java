package domain.usuario;

import domain.guardarropa.Guardarropa;
import domain.guardarropa.GuardarropaLimitado;

public class UsuarioGratuito implements TipoUsuario{
	
	@Override 
	public Guardarropa crearGuardarropa() {
		return new Guardarropa(new GuardarropaLimitado());
	}
	
}
