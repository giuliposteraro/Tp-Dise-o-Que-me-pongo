package domain.tipoPrenda;

import java.util.List;

public class Abrigo extends Superior {
	
	public Abrigo(List<ETela> unasTelas, int capa) {
		super(unasTelas);
		this.categoria = ECategoria.ABRIGO;
		this.nivelAbrigo = 10.0;
		this.capa = capa;
	}
	
	public Abrigo(List<ETela> unasTelas, int capa, Double nivelAbrigo) {
		super(unasTelas);
		this.categoria = ECategoria.ABRIGO;
		this.nivelAbrigo = nivelAbrigo;
		this.capa = capa;
	}
	
	@Override
	public boolean puedeAbrigarseCon(TipoPrenda otroTipo) {
		return this.capa < otroTipo.getCapa();
	}
}
