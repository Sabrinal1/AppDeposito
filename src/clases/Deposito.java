package clases;

import Interfaces.ListaOrdenada;

public class Deposito implements Depositante<Producto, Integer> {

	private static final String MSG_TIPO_PROD_INVALIDO = "Tipo de producto inv�lido";
	private static final String MSG_NO_PUDO_DEPOSITAR = "No se pudo depositar el producto.";
	private static final String MSG_PROF_INVALIDO = "Profundidad de estanter�a inv�lido";
	private static final int ALTO_ESTANTERIA = 3;
	private static final int ANCHO_ESTANTERIA = 4;
	private static final int PROFUNDIDAD_ESTANTERIA_MAX = 10;
	private static final int PROFUNDIDAD_ESTANTERIA_MIN = 2;
	private static final int FILA_PELOTA = 0;
	private static final int FILA_RAQUETA = 1;
	private static final int FILA_BOLSO = 2;

	
	private Estante[][] estanteria;
	private ListaOrdenada<Integer, Producto> productosDepositados;

	
	public Deposito(int profundidadEstanteria) {

		inicializarEstanteria(profundidadEstanteria);
		this.productosDepositados = new ListaOrdenadaProductosPorId();
	}

	
	private void inicializarEstanteria(int profundidadEstanteria) {
		if (profundidadEstanteria > PROFUNDIDAD_ESTANTERIA_MAX || profundidadEstanteria < PROFUNDIDAD_ESTANTERIA_MIN) {
			throw new IllegalArgumentException(MSG_PROF_INVALIDO);
		}
		estanteria = new Estante[ALTO_ESTANTERIA][ANCHO_ESTANTERIA];
		for (int i = 0; i < estanteria.length; i++) {
			for (int j = 0; j < estanteria[i].length; j++) {
				estanteria[i][j] = new Estante(profundidadEstanteria);
			}
		}
	}

	
	@Override
	public void depositar(Producto p) throws RuntimeException {

		int fila = calcularFila(p);
		int idxColumna = 0;
		boolean depositado = false;

		do {
			try {
				estanteria[fila][idxColumna].depositar(p);
				depositado = true;
				this.productosDepositados.add(p);
			} catch (RuntimeException e) {
				idxColumna++;
			}
		} while (idxColumna < estanteria[fila].length && !depositado);

		if (!depositado) {
			throw new RuntimeException(MSG_NO_PUDO_DEPOSITAR);
		}
	}

	
	private int calcularFila(Producto p) {
		int fila;
		if (p instanceof Pelota) {
			fila = FILA_PELOTA;
		} else if (p instanceof Raqueta) {
			fila = FILA_RAQUETA;
		} else if (p instanceof Bolso) {
			fila = FILA_BOLSO;
		} else {
			throw new RuntimeException(MSG_TIPO_PROD_INVALIDO);
		}
		return fila;
	}

	
	public boolean productoDepositado(int idProducto) {
		return this.productosDepositados.exists(idProducto);
	}

	
	@Override
	public Producto retirarPorId(Integer id) {
		Producto p = this.productosDepositados.removeByKey(id);
		if (p != null) {
			sacarDeLaEstanteria(p);
		}
		return p;
	}

	
	private void sacarDeLaEstanteria(Producto p) {
		
		int fila = calcularFila(p);
		int idxColumna = 0;
		boolean retirado = false;
		while (!retirado && idxColumna < this.estanteria[fila].length) {
			if (estanteria[fila][idxColumna].retirarPorId(p.getId()) != null) {
				retirado = true;
			}
			idxColumna++;
		}
	}
}

