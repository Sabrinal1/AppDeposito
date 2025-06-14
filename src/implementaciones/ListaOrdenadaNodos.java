package implementaciones;

import java.util.Iterator;

import Interfaces.ListaOrdenada;

public abstract class ListaOrdenadaNodos<K, T> extends TdaNodos<T> implements ListaOrdenada<K, T> {

	private NodoLista<T> last;

	public ListaOrdenadaNodos() {
		this(SIN_LIMITE);
	}

	public ListaOrdenadaNodos(int maxSize) {
		super(maxSize);
		this.last = null;
	}

	@Override
	public void add(T newElement) {
		checkFullness();
		NodoLista<T> node = new NodoLista<>(newElement);
		if (isEmpty()) {

			first = node;
			last = node;
		} else if (compare(newElement, first.getElement()) <= 0) {

			node.next(first);
			((NodoLista<T>) first).previous(node);
			first = node;
		} else if (compare(newElement, last.getElement()) > 0) {

			node.previous(last);
			last.next(node);
			last = node;
		} else {

			NodoLista<T> aux = (NodoLista<T>) first;
			while (compare(newElement, aux.next().getElement()) > 0) {
				aux = (NodoLista<T>) aux.next();
			}
			node.next(aux.next());
			aux.next(node);
			node.previous(aux);
			if (node.hasNext()) {
				((NodoLista<T>) node.next()).previous(node);
			}
		}
		incrementSize();
	}

	@Override
	public boolean exists(K key) {
		NodoLista<T> node = searchByKey(key);
		return node != null && compareByKey(key, node.getElement()) == 0;
	}

	@Override
	public T get(int pos) {
		NodoLista<T> aux = getNodeAt(pos);
		return aux.getElement();
	}

	protected NodoLista<T> getFirst() {
		return (NodoLista<T>) first;
	}

	private NodoLista<T> getNodeAt(int pos) {
		checkEmptiness();
		if (pos < 0 || pos >= size()) {
			throw new IndexOutOfBoundsException(String.format(MSG_INDEX_OUT_OF_RANGE, size()));
		}
		NodoLista<T> aux = (NodoLista<T>) first;
		for (int i = 0; i < pos; i++) {
			aux = (NodoLista<T>) aux.next();
		}
		return aux;
	}

	@Override
	public boolean remove(T elem) {
		checkEmptiness();
		if (elem == null) {
			throw new IllegalArgumentException(MSG_ELEM_IS_NULL);
		}
		boolean result = false;
		NodoLista<T> node = (NodoLista<T>) first;
		while (node != null && !elem.equals(node.getElement())) {
			node = (NodoLista<T>) node.next();
		}
		if (node != null) {
			removeNode(node);
			result = true;
		}
		return result;
	}

	@Override
	public T removeAt(int pos) {
		return removeNode(getNodeAt(pos));
	}

	@Override
	public T removeByKey(K key) {
		T element = null;
		NodoLista<T> node = searchByKey(key);
		if (node != null && compareByKey(key, node.getElement()) == 0) {
			element = removeNode(node);
		}
		return element;
	}

	private T removeNode(NodoLista<T> node) {
		if (node == null) {
			throw new IllegalArgumentException(MSG_NULL_NODE);
		}
		T element;
		element = node.getElement();

		if (node.hasPrevious()) {
			node.previous().next(node.next());
		} else {
			first = node.next();
		}
		if (node.hasNext()) {
			((NodoLista<T>) node.next()).previous(node.previous());
		}
		decrementSize();
		return element;
	}

	@Override
	public T search(K key) {
		NodoLista<T> node = searchByKey(key);
		T element = null;
		if (node != null && compareByKey(key, node.getElement()) == 0) {
			element = node.getElement();
		}
		return element;
	}

	private NodoLista<T> searchByKey(K key) {
		if (key == null) {
			throw new IllegalArgumentException(MSG_INVALID_KEY);
		}
		NodoLista<T> aux = (NodoLista<T>) first;
		while (aux != null && compareByKey(key, aux.getElement()) > 0) {
			aux = (NodoLista<T>) aux.next();
		}
		return aux;
	}

	@Override
	public int size() {
		return getCurrentSize();
	}

	@Override
	public Iterator<T> iterator() {
		return new ListaOrdenadaIterator<>(getFirst());
	}

}
