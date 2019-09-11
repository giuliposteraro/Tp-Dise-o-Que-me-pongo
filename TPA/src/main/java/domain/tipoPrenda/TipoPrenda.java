package domain.tipoPrenda;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


public class TipoPrenda {
	
	protected ECategoria categoria;
	
	protected List<ETela> telasValidas;
	protected Double nivelAbrigo;
	protected String descripcion;
	

	protected TipoPrenda(List<ETela> unasTelas, Double nivelAbrigo, String descripcion) {
		this.telasValidas = unasTelas;
		this.nivelAbrigo = nivelAbrigo;
		this.descripcion = descripcion;
	}
	
	public Boolean esTelaValida(ETela tela) {
		return this.telasValidas.contains(tela);
	}
	
	public ECategoria getCategoria() {
		return  this.categoria;
	}

	public Double getNivelAbrigo() {
		return nivelAbrigo;
	}
	
	public boolean puedeAbrigarseCon(TipoPrenda tipoPrenda) {
		return false;
	}

	public int getCapa() {
		return 0;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
