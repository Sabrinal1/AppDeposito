package Main;

import clases.Bolso;
import clases.Deposito;
import clases.Pelota;
import clases.Producto;
import clases.Raqueta;
import clases.Superficie;

public class Main {
	private static final int ONCE = 11;
	private static int id = 10;

	public static void main(String[] args) {

		Deposito d = new Deposito(3);

		depositar(d);

		int unId = 55;
		System.out.println(d.productoDepositado(unId) ? "El producto id " + unId + " est� depositado"
				: "El producto id " + unId + " NO est� depositado");
		unId = 23;
		System.out.println(d.productoDepositado(unId) ? "El producto id " + unId + " est� depositado"
				: "El producto id " + unId + " NO est� depositado");

		retiros(d);
	}

	private static void retiros(Deposito d) {
		System.out.println("Se comienza a retirar elementos");
		System.out.println();
		retirar(d, 12);
		System.out.println();
		retirar(d, 13);
		System.out.println();
		retirar(d, 28);
		System.out.println();
		System.out.println("Fin del retiro de elementos");
	}

	private static void retirar(Deposito d, int id) {
		Producto p = d.retirarPorId(id);
		if (p != null) {
			System.out.println("Se retir� el producto id: " + id);
			p.mostrar();
		} else {
			System.out.println("No se pudo retirar producto id: " + id);
		}

	}

	private static void depositar(Deposito d) {

		System.out.println("Depositando productos");
		System.out.println();

		depositarBolso(d, id++, "Wilson", "Us Open");
		System.out.println();
		depositarRaqueta(d, id++, "Wilson", 100);
		System.out.println();
		depositarPelota(d, id++, "Wilson", Superficie.POLVO_LADRILLO);
		System.out.println();
		depositarBolso(d, id++, "", "Us Open 2");
		System.out.println();
		depositarRaqueta(d, 0, "Wilson", 105);
		System.out.println();
		depositarBolso(d, id++, "Wilson", "All Team");
		System.out.println();
		depositarRaqueta(d, id++, "Prince", 98);
		System.out.println();
		depositarPelota(d, id++, "Wilson", Superficie.RAPIDA);
		System.out.println();
		depositarBolso(d, id++, "Wilson", null);
		System.out.println();
		depositarRaqueta(d, id++, "Wilson", 544);
		System.out.println();
		depositarPelota(d, id++, "Wilson", null);
		System.out.println();
		for (int i = 0; i < ONCE; i++) {
			depositarPelota(d, id++, "Wimbledon", Superficie.CESPED);
			System.out.println();
		}
		depositarBolso(d, id++, "Wilson", "Australian Bag");
		System.out.println();
		System.out.println("Fin de los dep�sitos");
	}

	private static void depositarBolso(Deposito d, int id, String marca, String modelo) {
		try {
			Producto p = new Bolso(id, marca, modelo);
			d.depositar(p);
			System.out.println("Bolso depositado id: " + id);
		} catch (RuntimeException e) {
			System.out.println("Error depositando bolso: " + e.getMessage());
		}
	}

	private static void depositarRaqueta(Deposito d, int id, String marca, int tamanioAro) {
		try {
			Producto p = new Raqueta(id, marca, tamanioAro);
			d.depositar(p);
			System.out.println("Raqueta depositada id: " + id);
		} catch (RuntimeException e) {
			System.out.println("Error depositando raqueta: " + e.getMessage());
		}
	}

	private static void depositarPelota(Deposito d, int id, String marca, Superficie superficie) {
		try {
			Producto p = new Pelota(id, marca, superficie);
			d.depositar(p);
			System.out.println("Pelota depositada id: " + id);
		} catch (RuntimeException e) {
			System.out.println("Error depositando pelota: " + e.getMessage());
		}
	}

}
