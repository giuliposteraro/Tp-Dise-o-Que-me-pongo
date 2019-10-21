package domain.tipoPrenda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum ETela {
	ALGODON, CUERO, JEAN, LANA, HILO, POLAR, NINGUNA;
	
	public static List<String> getTelas() {
		return Arrays.asList(ETela.values())
				.stream()
				.map(tela -> formatTela(tela))
				.collect(Collectors.toList());
	}
	
	private static String formatTela(ETela tela) {
		return tela.name().toUpperCase().charAt(0) + tela.name().substring(1).toLowerCase();
	}
}
