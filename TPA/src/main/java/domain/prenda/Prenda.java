package domain.prenda;

import domain.color.Color;
import domain.color.EColor;
import domain.tipoPrenda.ECategoria;
import domain.tipoPrenda.ETela;
import domain.tipoPrenda.Tipo;

//No instanciar directamente, usar ConstructorPrenda

public class Prenda {
	
	Tipo tipo;
	Color color;
	ETela tela;
	
	public Prenda(Tipo tipo, ETela tela, Color color) {
		this.tipo = tipo;
		this.tela = tela;
		this.color = color;
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
	
	public static Prenda SIN_ACCESORIO = new Prenda(Tipo.SIN_ACCESORIO, ETela.NINGUNA, new Color(EColor.NINGUNO, EColor.NINGUNO));
}
