package domain.tipoPrenda;

import java.util.Arrays;

public class RepoTipos {
	public static Superior REMERA = new Superior(Arrays.asList(ETela.ALGODON),"Remera");
	public static Abrigo BUZO = new Abrigo(Arrays.asList(ETela.ALGODON), 30, "Buzo");
	public static Abrigo CAMPERA = new Abrigo(Arrays.asList(ETela.CUERO, ETela.ALGODON), 40, "Campera");
	public static Abrigo CAMISA = new Abrigo(Arrays.asList(ETela.ALGODON), 10, "Camisa");
	public static Abrigo SWEATER = new Abrigo(Arrays.asList(ETela.LANA), 20, "Sweater");
	public static Inferior PANTALON = new Inferior(Arrays.asList(ETela.ALGODON, ETela.JEAN), "Pantalon");
	public static Inferior BERMUDA = new Inferior(Arrays.asList(ETela.ALGODON, ETela.JEAN), "Bermuda");
	public static Inferior SHORT = new Inferior(Arrays.asList(ETela.ALGODON, ETela.JEAN), "Short");
	public static Inferior POLLERA = new Inferior(Arrays.asList(ETela.TELA), "Pollera");
	public static Calzado ZAPATOS = new Calzado(Arrays.asList(ETela.CUERO), "Zapatos");
	public static Calzado ZAPATILLAS = new Calzado(Arrays.asList(ETela.TELA), "Zapatillas");
	public static Accesorio LENTES = new Accesorio(Arrays.asList(ETela.NINGUNA), "Lentes");
	public static Accesorio RELOJ = new Accesorio(Arrays.asList(ETela.NINGUNA), "Reloj");
	public static Accesorio COLLAR = new Accesorio(Arrays.asList(ETela.NINGUNA), "Collar");
}