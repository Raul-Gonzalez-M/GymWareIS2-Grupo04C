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

	@Override
	public T createInstance(String name, double precio) {
		T ret;
		
		if(builders.containsKey(name))
			ret = builders.get(name).createInstance(name, precio);
		else
			throw new IllegalArgumentException("Material no valido...");
		
		return ret;
	}

}
