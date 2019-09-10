package persistency.converters;

import javax.persistence.AttributeConverter;

import domain.guardarropa.GuardarropaIlimitado;
import domain.guardarropa.GuardarropaLimitado;
import domain.guardarropa.TipoGuardarropa;

public class TipoGuardarropaConverter implements AttributeConverter<TipoGuardarropa, String> {
	@Override
	public String convertToDatabaseColumn(TipoGuardarropa attribute) {
		return attribute.toString();
	}

	@Override
	public TipoGuardarropa convertToEntityAttribute(String dbData) {
		switch (dbData) {
		case "LIMITADO":
			return new GuardarropaLimitado();
		case "ILIMITADO":
			return new GuardarropaIlimitado();
		default:
			return new GuardarropaLimitado();
		}
	}
}
