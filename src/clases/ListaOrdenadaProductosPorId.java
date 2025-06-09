package clases;

import implementaciones.ListaOrdenadaNodos;

public class ListaOrdenadaProductosPorId extends ListaOrdenadaNodos<Integer, Producto> {

	@Override
	public int compare(Producto dato1, Producto dato2) {
		return dato1.getId() - dato2.getId();
	}

	@Override
	public int compareByKey(Integer clave, Producto elemento) {
		return clave - elemento.getId();
	} 

}
