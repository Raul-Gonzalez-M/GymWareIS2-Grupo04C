package factories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuilderBasedFactory<T> implements Factory<T> {
	
	private Map<String, Builder<T>> builders;
	
	public BuilderBasedFactory() {
		this.builders = new HashMap<>();
	}
	
	public BuilderBasedFactory(List<Builder<T>> builders) {
		this();
		for(Builder<T> b : builders) {
			this.builders.put(b.getTag(), b);
		}
	}


	public T createInstance(int id, String nombre, int precio, int cantidad_disponible, String actividad_asociada) {
		T ret;
		
		if(builders.containsKey(nombre))
			ret = builders.get(nombre).createInstance(id, nombre, precio, cantidad_disponible, actividad_asociada);
		else
			throw new IllegalArgumentException("Material no valido...");
		
		return ret;
	}

}
