package domain.prenda;

import java.awt.image.BufferedImage;
import java.util.Optional;

import domain.color.Color;
import domain.color.EColor;
import domain.tipoPrenda.ECategoria;
import domain.tipoPrenda.ETela;
import domain.tipoPrenda.TipoPrenda;

//No instanciar directamente, usar ConstructorPrenda

public class Prenda {
	
	TipoPrenda tipo;
	Color color;
	ETela tela;
	BufferedImage imagen;
	
	private Optional<Prenda> prendaAbajo = Optional.empty();
	
	public Prenda(TipoPrenda tipo, ETela tela, Color color, BufferedImage imagen) {
		this.tipo = tipo;
		this.tela = tela;
		this.color = color;
		this.imagen = imagen;
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
	
	public BufferedImage getImagen() {
		return imagen;
	}
	
	public ECategoria getCategoria() {
		return tipo.getCategoria();
	}

	public Double getNivelAbrigo() {
		return tipo.getNivelAbrigo() + this.prendaAbajo.map(prenda -> prenda.getNivelAbrigo()).orElse(0.0);
	}
	
	public Prenda ponerSobre(Prenda otraPrenda) {
		Prenda resultadoPrenda = new Prenda(tipo, tela, color, imagen);
		if(otraPrenda.puededeAbrigarseCon(resultadoPrenda)) {
			resultadoPrenda.prendaAbajo = Optional.of(otraPrenda);
			return resultadoPrenda;
		}
		return null;
	}
	
	protected boolean puededeAbrigarseCon(Prenda otraPrenda) {
		return tipo.puedeAbrigarseCon(otraPrenda.getTipo());
	}
	
	public static Prenda SIN_ACCESORIO = new Prenda(RepoPrendas.SIN_ACCESORIO, ETela.NINGUNA, new Color(EColor.NINGUNO, EColor.NINGUNO), null);
	public static Prenda SIN_ABRIGO = new Prenda(RepoPrendas.SIN_ABRIGO, ETela.NINGUNA, new Color(EColor.NINGUNO, EColor.NINGUNO), null);
}

