package persistency.converters;

import javax.persistence.AttributeConverter;

import domain.usuario.TipoUsuario;
import domain.usuario.UsuarioGratuito;
import domain.usuario.UsuarioPremium;

public class TipoUsuarioConverter implements AttributeConverter<TipoUsuario, String> {
	@Override
	public String convertToDatabaseColumn(TipoUsuario attribute) {
		return attribute.toString();
	}

	@Override
	public TipoUsuario convertToEntityAttribute(String dbData) {
		switch (dbData) {
		case "GRATUITO":
			return new UsuarioGratuito();
		case "PREMIUM":
			return new UsuarioPremium();
		default:
			return new UsuarioGratuito();
		}
	}
}
