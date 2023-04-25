package factories;

public interface Factory <T>{

	public T createInstance(String name, double precio);
}
