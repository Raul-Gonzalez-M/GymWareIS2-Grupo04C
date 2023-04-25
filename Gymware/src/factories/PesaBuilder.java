package factories;

import model.Material;
import model.Pesa;

public class PesaBuilder extends Builder<Material>{

	public PesaBuilder() {
		super("Pesa");
	}

	@Override
	protected Material createInstance(String name, double precio) {
		if(precio <= 0)
			throw new IllegalArgumentException("El precio debe ser mayor que cero.");
		
		return new Pesa(precio);
	}

}
