package domain.TipoPrenda;

import java.util.List;
import java.util.Optional;

import domain.ECategoria;
import domain.ETela;

public class Abrigo extends Superior {
	private Optional<Superior> prendaAbajo = Optional.empty();
	
	public Abrigo(List<ETela> unasTelas, int nivelAbrigo) {
		super(unasTelas);
		this.categoria = ECategoria.ABRIGO;
		this.nivelAbrigo = nivelAbrigo;
	}
	
	public void ponerSobre(Superior otraPrenda) {
		if(otraPrenda.puededeAbrigarseCon(this)) {
			this.prendaAbajo = Optional.of(otraPrenda);
		}
		else {
			//FIXME: chequear excepcion o no
		}
	}
	
	@Override
	public int getNivelAbrigo(){
		return this.nivelAbrigo + this.prendaAbajo.map(prenda -> prenda.getNivelAbrigo()).orElse(0);
	}
}
