package persistency.converters;

import javax.persistence.AttributeConverter;

import domain.tipoPrenda.RepoTipos;
import domain.tipoPrenda.TipoPrenda;

public class TipoPrendaConverter implements AttributeConverter <TipoPrenda,String> {

	@Override
	public String convertToDatabaseColumn(TipoPrenda tipoPrenda) {
		return tipoPrenda.getDescripcion();
	}

	@Override
	public TipoPrenda convertToEntityAttribute(String descripcionPrenda) {
		switch(descripcionPrenda) {
			case "Remera":
				return RepoTipos.REMERA;
			case "Buzo":
				return RepoTipos.BUZO;
			case "Campera":
				return RepoTipos.CAMPERA;
			case "Camisa":
				return RepoTipos.CAMISA;
			case "Sweater":
				return RepoTipos.SWEATER;
			case "Pantalon":
				return RepoTipos.PANTALON;
			case "Bermuda":
				return RepoTipos.BERMUDA;
			case "Short":
				return RepoTipos.SHORT;
			case "Pollera":
				return RepoTipos.POLLERA;
			case "Zapatos":
				return RepoTipos.ZAPATOS;
			case "Zapatillas":
				return RepoTipos.ZAPATILLAS;
			case "Lentes":
				return RepoTipos.LENTES;
			case "Reloj":
				return RepoTipos.RELOJ;
			case "Collar":
				return RepoTipos.COLLAR;
			default:
				return null;
		}
	}
}
