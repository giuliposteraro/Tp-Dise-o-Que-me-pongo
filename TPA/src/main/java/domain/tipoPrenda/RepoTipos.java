package domain.tipoPrenda;

import java.util.Arrays;

public class RepoTipos {
	public static Superior REMERA = new Superior(Arrays.asList(ETela.ALGODON));
	public static Abrigo BUZO = new Abrigo(Arrays.asList(ETela.ALGODON), 30);
	public static Abrigo CAMPERA = new Abrigo(Arrays.asList(ETela.CUERO, ETela.ALGODON), 40);
	public static Abrigo CAMISA = new Abrigo(Arrays.asList(ETela.ALGODON), 10);
	public static Abrigo SWEATER = new Abrigo(Arrays.asList(ETela.LANA), 20);
	public static Inferior PANTALON = new Inferior(Arrays.asList(ETela.ALGODON, ETela.JEAN));
	public static Inferior BERMUDA = new Inferior(Arrays.asList(ETela.ALGODON, ETela.JEAN));
	public static Inferior SHORT = new Inferior(Arrays.asList(ETela.ALGODON, ETela.JEAN));
	public static Inferior POLLERA = new Inferior(Arrays.asList(ETela.TELA));
	public static Calzado ZAPATOS = new Calzado(Arrays.asList(ETela.CUERO));
	public static Calzado ZAPATILLAS = new Calzado(Arrays.asList(ETela.TELA));
	public static Accesorio LENTES = new Accesorio(Arrays.asList(ETela.NINGUNA));
	public static Accesorio RELOJ = new Accesorio(Arrays.asList(ETela.NINGUNA));
	public static Accesorio COLLAR = new Accesorio(Arrays.asList(ETela.NINGUNA));
}