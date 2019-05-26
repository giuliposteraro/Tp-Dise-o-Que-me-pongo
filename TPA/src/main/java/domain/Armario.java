package domain;

import java.util.Arrays;
import java.util.List;

import domain.TipoPrenda.*;

public class Armario {
	public static Superior REMERA = new Superior(Arrays.asList(ETela.ALGODON));
	public static Abrigo BUZO = new Abrigo(Arrays.asList(ETela.ALGODON), 10);
	public static TipoPrenda PANTALON = new TipoPrenda(ECategoria.INFERIOR, Arrays.asList(ETela.ALGODON, ETela.JEAN));
	public static TipoPrenda BERMUDA = new TipoPrenda(ECategoria.INFERIOR, Arrays.asList(ETela.ALGODON, ETela.JEAN));
	public static TipoPrenda SHORT = new TipoPrenda(ECategoria.INFERIOR, Arrays.asList(ETela.ALGODON));
	public static TipoPrenda ZAPATOS = new TipoPrenda(ECategoria.CALZADO, Arrays.asList(ETela.CUERO));
	public static TipoPrenda ZAPATILLAS = new TipoPrenda(ECategoria.CALZADO, Arrays.asList(ETela.TELA));
	public static TipoPrenda LENTES = new TipoPrenda(ECategoria.ACCESORIO, Arrays.asList(ETela.NINGUNA));
	public static TipoPrenda RELOJ = new TipoPrenda(ECategoria.ACCESORIO, Arrays.asList(ETela.NINGUNA));
	public static TipoPrenda COLLAR = new TipoPrenda(ECategoria.ACCESORIO, Arrays.asList(ETela.NINGUNA));
	public static TipoPrenda SIN_ACCESORIO = new TipoPrenda(ECategoria.ACCESORIO, Arrays.asList(ETela.NINGUNA));
	
}