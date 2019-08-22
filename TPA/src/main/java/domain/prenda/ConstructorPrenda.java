package domain.prenda;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import domain.color.Color;
import domain.color.EColor;
import domain.tipoPrenda.ETela;
import domain.tipoPrenda.TipoPrenda;
import exceptions.*;

public class ConstructorPrenda {
	
	// Mismos atributos que Prenda
	private TipoPrenda tipo;
	private Color color;
	private ETela tela;
	private BufferedImage imagen;
	
	public Prenda crear() {
		validar();
		return new Prenda(tipo, tela, color, imagen);
	}
	
	private void validar() {
		if (Arrays.asList(tipo, color, tela).contains(null) || !color.esValido() || !tipo.esTelaValida(tela)) {
			throw new ParametrosNoValidos("Los parametros no son validos.");
		}
	}

	public void setTipo(TipoPrenda tipo) {
		this.tipo = tipo;
	}

	public void setColor(EColor colorPrimario, EColor colorSecundario) {
		this.color = new Color(colorPrimario, colorSecundario);
	}

	public void setTela(ETela tela) {
		this.tela = tela;
	}
	
	public void setImagen(String path) {
		File archivoImg = new File(path);
		try {
			imagen = ImageIO.read(archivoImg);
		} catch (IOException e) {
			imagen = null;
			throw new NoSePudoCargarLaImagen("La ruta indicada no es valida");
		}
	}
}
