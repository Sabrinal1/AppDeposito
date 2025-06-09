package clases;

import Interfaces.Pila;
import implementaciones.PilaNodos;

public class Estante implements Depositante<Producto, Integer> {

	
	private Pila<Producto> ubicaciones;
	private int cantidadAlojados;
	private int profundidadEstanteria;

	public Estante(int profundidadEstanteria) {
		this.profundidadEstanteria = profundidadEstanteria;
		this.ubicaciones = new PilaNodos<>();
	}

	
	@Override
	public void depositar(Producto p) throws RuntimeException {

		if (profundidadEstanteria == cantidadAlojados) {
			throw new RuntimeException("No se pueden agregar mas productos a este estante.");
		}
		ubicaciones.push(p);
		cantidadAlojados++;
	}

	
	@Override
	public Producto retirarPorId(Integer id) {
		Producto retorno = null;
		Pila<Producto> aux = new PilaNodos<>();
		while (retorno == null && !this.ubicaciones.isEmpty()) {
			Producto productoAuxiliar = ubicaciones.pop();
			if (productoAuxiliar.coincideId(id)) {
				retorno = productoAuxiliar;
			} else {
				aux.push(productoAuxiliar);
			}
		}

		while (!aux.isEmpty()) {
			ubicaciones.push(aux.pop());
		}

		return retorno;
	}

}
