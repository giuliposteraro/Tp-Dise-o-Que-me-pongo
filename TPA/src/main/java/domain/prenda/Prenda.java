package domain.prenda;

import domain.color.Color;
import domain.tipoPrenda.ECategoria;
import domain.tipoPrenda.ETela;
import domain.tipoPrenda.TipoPrenda;

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
	
	//public static Prenda SIN_ACCESORIO = new Prenda(RepoPrendas.SIN_ACCESORIO, ETela.NINGUNA, new Color(EColor.NINGUNO, EColor.NINGUNO));
	//public static Prenda SIN_ABRIGO = new Prenda(RepoPrendas.SIN_ABRIGO, ETela.NINGUNA, new Color(EColor.NINGUNO, EColor.NINGUNO));
}

