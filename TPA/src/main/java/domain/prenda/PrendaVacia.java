package domain.prenda;

import java.awt.image.BufferedImage;

import domain.color.Color;
import domain.tipoPrenda.ETela;
import domain.tipoPrenda.TipoPrenda;

public class PrendaVacia extends Prenda {

	public PrendaVacia(TipoPrenda tipo, ETela tela, Color color, BufferedImage imagen) {
		super(tipo, tela, color, imagen);
	}

	@Override
	public void setEnUso(Boolean enUso) {}
}
