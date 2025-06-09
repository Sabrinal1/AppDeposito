package implementaciones;

import implementaciones.Nodo;
import implementaciones.NodoLista;

public class NodoLista<T> extends Nodo<T> {

	private NodoLista<T> previous;

	public NodoLista(T dato) {
		super(dato);
		this.previous = null;
	}

	public NodoLista<T> previous() {
		return previous;
	}

	public void previous(NodoLista<T> previous) {
		this.previous = previous;
	}

	public boolean hasPrevious() {
		return previous != null;
	}

}