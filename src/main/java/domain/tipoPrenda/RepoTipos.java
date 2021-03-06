package domain.tipoPrenda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RepoTipos {

	private static List<TipoPrenda> _getTipos() {
		return Arrays.asList(REMERA, BUZO, CAMPERA, CAMISA, SWEATER, PANTALON, BERMUDA, SHORT, POLLERA, ZAPATOS, ZAPATILLAS, LENTES, RELOJ, COLLAR);
	}
	
	public static Superior REMERA = new Superior(Arrays.asList(ETela.ALGODON),"Remera");
	public static Abrigo BUZO = new Abrigo(Arrays.asList(ETela.ALGODON), 30, "Buzo");
	public static Abrigo CAMPERA = new Abrigo(Arrays.asList(ETela.CUERO, ETela.ALGODON, ETela.POLAR), 40, "Campera");
	public static Abrigo CAMISA = new Abrigo(Arrays.asList(ETela.ALGODON), 10, "Camisa");
	public static Abrigo SWEATER = new Abrigo(Arrays.asList(ETela.LANA), 20, "Sweater");
	public static Inferior PANTALON = new Inferior(Arrays.asList(ETela.ALGODON, ETela.JEAN), "Pantalon");
	public static Inferior BERMUDA = new Inferior(Arrays.asList(ETela.ALGODON, ETela.JEAN), "Bermuda");
	public static Inferior SHORT = new Inferior(Arrays.asList(ETela.ALGODON, ETela.JEAN), "Short");
	public static Inferior POLLERA = new Inferior(Arrays.asList(ETela.HILO), "Pollera");
	public static Calzado ZAPATOS = new Calzado(Arrays.asList(ETela.CUERO), "Zapatos");
	public static Calzado ZAPATILLAS = new Calzado(Arrays.asList(ETela.HILO), "Zapatillas");
	public static Accesorio LENTES = new Accesorio(Arrays.asList(ETela.NINGUNA), "Lentes");
	public static Accesorio RELOJ = new Accesorio(Arrays.asList(ETela.NINGUNA), "Reloj");
	public static Accesorio COLLAR = new Accesorio(Arrays.asList(ETela.NINGUNA), "Collar");

	public static List<TipoPrenda> getTipos() {
		return _getTipos()
				.stream()
				.sorted((t1, t2)-> t1.getDescripcion().compareTo(t2.getDescripcion()))
				.collect(Collectors.toList());
	}
	
	public static TipoPrenda getTipo(String tipo) {
		return _getTipos().stream()
				.filter(t -> tipo.equalsIgnoreCase(t.getDescripcion()))
				.findFirst()
				.orElse(null);
	}
}