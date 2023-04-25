package factories;

import model.Churro;
import model.Material;

public class ChurroBuilder extends Builder<Material>{

	public ChurroBuilder() {
		super("Churro");
	}

	@Override
	protected Material createInstance(String name, double precio) {
		if(precio <= 0)
			throw new IllegalArgumentException("El precio debe ser mayor que cero.");
		
		return new Churro(precio);
	}
}
