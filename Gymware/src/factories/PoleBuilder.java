package factories;

import model.Material;
import model.Pole;

public class PoleBuilder extends Builder<Material>{

	public PoleBuilder(String tag) {
		super("Pole");
	}

	@Override
	protected Material createInstance(String name, double precio) {
		if(precio <= 0)
			throw new IllegalArgumentException("El precio debe ser mayor que cero.");
		
		return new Pole(precio);
	}

}
