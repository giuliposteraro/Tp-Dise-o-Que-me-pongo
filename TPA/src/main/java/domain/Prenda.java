package domain;

import domain.TipoPrenda.TipoPrenda;

//No instanciar directamente, usar ConstructorPrenda

public class Prenda {
	
	TipoPrenda tipo;
	Color color;
	ETela tela;
	
	public Prenda(TipoPrenda tipo, ETela tela, Color color) {
		this.tipo = tipo;
		this.tela = tela;
		this.color = color;
	}
	
	public TipoPrenda getTipo() {
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
	
	public static Prenda SIN_ACCESORIO = new Prenda(Pepito.SIN_ACCESORIO, ETela.NINGUNA, new Color(EColor.NINGUNO, EColor.NINGUNO));
}
