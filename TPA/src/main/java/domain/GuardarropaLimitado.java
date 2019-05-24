package domain;


public class GuardarropaLimitado extends Guardarropa{
	
	private int capacidadPrendas = 50;
	
	public GuardarropaLimitado() {
		super();
	}
	
	@Override
	public Boolean tieneLugar() {
		return capacidadPrendas > this.cantidadPrendas();
	}
	
	private int cantidadPrendas() {
		return this.getPrendas().size();
	}
}
