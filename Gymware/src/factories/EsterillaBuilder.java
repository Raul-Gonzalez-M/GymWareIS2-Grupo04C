package factories;

import model.Esterilla;
import model.Material;

public class EsterillaBuilder extends Builder<Material>{

	public EsterillaBuilder() {
		super("Esterilla");
	}

	@Override
	protected Material createInstance(String name, double precio) {
		if(precio <= 0)
			throw new IllegalArgumentException("El precio debe ser mayor que cero.");
		
		return new Esterilla(precio);
	}

}
