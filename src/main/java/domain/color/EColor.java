package domain.color;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum EColor {
	ROJO, NARANJA, AMARILLO, VERDE, AZUL, INDIGO, VIOLETA, BLANCO, NEGRO, NINGUNO;

	public static List<String> getColores() {
		return Arrays.asList(EColor.values())
				.stream()
				.map(color -> formatColor(color))
				.collect(Collectors.toList());
	}
	
	private static String formatColor(EColor color) {
		return color.name().toUpperCase().charAt(0) + color.name().substring(1).toLowerCase();
	}
}
