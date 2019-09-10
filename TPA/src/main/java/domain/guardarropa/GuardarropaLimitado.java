package domain.guardarropa;

import domain.Config;

public class GuardarropaLimitado implements TipoGuardarropa{
	@Override
	public Boolean tieneLugar(Guardarropa guardarropa) {
		return guardarropa.cantidadPrendas() < Config.instance().getCapacidadMaxima() + 2;
	}
	
	@Override
	public String toString() {
		return "LIMITADO";
	}
}
