package domain.TipoPrenda;

import java.util.List;

import domain.ECategoria;
import domain.ETela;

public class Accesorio extends TipoPrenda {
	public Accesorio(List<ETela> unasTelas) {
		super();
		this.categoria = ECategoria.ACCESORIO;
		this.telasValidas = unasTelas;
	}
}
