package clases;

public interface Depositante<T, K> {

	public void depositar(T elemento);

	public T retirarPorId(K id);
}
