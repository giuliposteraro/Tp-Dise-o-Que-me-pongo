package domain.tipoPrenda;

import java.util.List;

public class Calzado extends TipoPrenda {
	public Calzado(List<ETela> unasTelas, String descripcion) {
		super(unasTelas, 1.0, descripcion);
		this.categoria = ECategoria.CALZADO;
	}
}
