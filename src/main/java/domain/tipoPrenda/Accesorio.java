package domain.tipoPrenda;

import java.util.List;

public class Accesorio extends TipoPrenda {
	public Accesorio(List<ETela> unasTelas, String descripcion) {
		super(unasTelas, 0.0, descripcion);
		this.categoria = ECategoria.ACCESORIO;
	}
}
