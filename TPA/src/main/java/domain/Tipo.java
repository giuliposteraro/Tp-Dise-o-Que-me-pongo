package domain;

import java.util.Arrays;
import java.util.List;

public class Tipo {
	
	ECategoria categoria;
	List<ETela> telasValidas;
	
	private Tipo(ECategoria categoria, List<ETela> telasValidas) {
		this.categoria = categoria;
		this.telasValidas = telasValidas;
	}
	
	public Boolean esTelaValida(ETela tela) {
		return telasValidas.contains(tela);
	}
	
	public ECategoria getCategoria() {
		return categoria;
	}
	
	public static Tipo REMERA = new Tipo(ECategoria.SUPERIOR, Arrays.asList(ETela.ALGODON));
	public static Tipo BUZO = new Tipo(ECategoria.SUPERIOR, Arrays.asList(ETela.ALGODON));
	public static Tipo PANTALON = new Tipo(ECategoria.INFERIOR, Arrays.asList(ETela.ALGODON, ETela.JEAN));
	public static Tipo BERMUDA = new Tipo(ECategoria.INFERIOR, Arrays.asList(ETela.ALGODON, ETela.JEAN));
	public static Tipo SHORT = new Tipo(ECategoria.INFERIOR, Arrays.asList(ETela.ALGODON));
	public static Tipo ZAPATOS = new Tipo(ECategoria.CALZADO, Arrays.asList(ETela.CUERO));
	public static Tipo ZAPATILLAS = new Tipo(ECategoria.CALZADO, Arrays.asList(ETela.TELA));
	public static Tipo LENTES = new Tipo(ECategoria.ACCESORIO, Arrays.asList(ETela.NINGUNA));
	public static Tipo RELOJ = new Tipo(ECategoria.ACCESORIO, Arrays.asList(ETela.NINGUNA));
	public static Tipo COLLAR = new Tipo(ECategoria.ACCESORIO, Arrays.asList(ETela.NINGUNA));	
	
}