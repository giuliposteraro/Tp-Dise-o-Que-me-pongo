package domain.usuario;

import domain.guardarropa.Guardarropa;
import domain.guardarropa.GuardarropaIlimitado;

public class UsuarioPremium implements TipoUsuario {
	@Override
	public Guardarropa crearGuardarropa() {
		return new Guardarropa(new GuardarropaIlimitado());
	}

	@Override
	public String toString() {
		return "PREMIUM";
	}
}
