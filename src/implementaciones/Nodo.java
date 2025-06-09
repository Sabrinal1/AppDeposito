package implementaciones;



public class Nodo<T> {
	private T dato;
	private Nodo<T> next;

	public Nodo(T dato) {
		this.dato = dato;
		this.next = null;
	}

	public T getElement() {
		return dato;
	}

	public Nodo<T> next() {
		return next;
	}

	public void next(Nodo<T> next) {
		this.next = next;
	}

	public boolean hasNext() {
		return next != null;
	}

}
