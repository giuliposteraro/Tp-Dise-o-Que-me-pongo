package domain;

class Main {
	
	public static void main(String[] args) {
		
		ConstructorPrenda c = new ConstructorPrenda();
		
		c.setTipo(Tipo.REMERA);

		c.setColor(EColor.ROJO, EColor.NINGUNO);

		c.setTela(ETela.ALGODON);
		
		try {
			System.out.println("La prenda se ha creado satisfactoriamente.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
