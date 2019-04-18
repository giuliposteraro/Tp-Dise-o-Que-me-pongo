package domain;

//No instanciar directamente, usar ConstructorPrenda

public class Prenda {
	
	ECategoria categoria;
	Tipo tipo;
	Color color;
	ETela tela;
	
	public Prenda(Tipo tipo, ETela tela, Color color) {
		this.tipo = tipo;
		this.color = color;
		this.tela = tela;
	}
}
