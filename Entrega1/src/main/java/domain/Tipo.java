package domain;

import java.util.ArrayList;

public class Tipo {
	
	ECategoria categoria;
	ArrayList<ETela> telasValidas = new ArrayList<ETela>();
	
	public Tipo(ECategoria categoria) {
		this.categoria = categoria;
	}
	
	public static Tipo REMERA = new Tipo(ECategoria.SUPERIOR);
	public static Tipo BUZO = new Tipo(ECategoria.SUPERIOR);
	public static Tipo PANTALON = new Tipo(ECategoria.INFERIOR);
	public static Tipo BERMUDA = new Tipo(ECategoria.INFERIOR);
	public static Tipo SHORT = new Tipo(ECategoria.INFERIOR);
	public static Tipo ZAPATOS = new Tipo(ECategoria.CALZADO);
	public static Tipo ZAPATILLAS = new Tipo(ECategoria.CALZADO);
	public static Tipo LENTES = new Tipo(ECategoria.ACCESORIO);
	public static Tipo RELOJ = new Tipo(ECategoria.ACCESORIO);
	public static Tipo COLLAR = new Tipo(ECategoria.ACCESORIO);	
	
}