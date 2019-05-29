package domain;

public class GuardarropaIlimitado implements TipoGuardarropa {
	@Override
	public Boolean tieneLugar(Guardarropa guardarropa) {
		return true;
	}
}
