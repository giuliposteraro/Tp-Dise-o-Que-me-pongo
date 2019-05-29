package domain;
import exceptions.CapacidadDelGuardarropaLlena;

public class GuardarropaLimitado implements TipoGuardarropa{
	
	@Override
	public Boolean tieneLugar(Guardarropa guardarropa) {
		if(guardarropa.cantidadPrendas() > Config.instance().getCapacidadMaxima()) {
			throw new CapacidadDelGuardarropaLlena("El guardarropa no tiene lugar suficiente");
		}
			return true;
	}
}
