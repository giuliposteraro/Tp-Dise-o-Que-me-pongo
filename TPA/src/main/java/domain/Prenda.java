package domain;

//No instanciar directamente, usar ConstructorPrenda

public class Prenda {
	
	Tipo tipo;
	Color color;
	ETela tela;
	
	public Prenda(Tipo tipo, ETela tela, Color color) {
		this.tipo = tipo;
		this.color = color;
		this.tela = tela;
	}
	
	public Tipo getTipo() {
		return tipo;
	}

	public Color getColor() {
		return color;
	}

	public ETela getTela() {
		return tela;
	}
	
	public ECategoria getCategoria() {
		return tipo.getCategoria();
	}
}
