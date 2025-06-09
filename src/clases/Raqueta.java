package clases;

public class Raqueta extends Producto {

	private static final String MSG_TAMANIO_ARO_INVALIDO = "Tama�o de aro inv�lido.";
	private static final String MSG_RAQUETA = "La raqueta tiene un tama�o de aro %d es de la marca %s y tiene un id %d\n";

	private static final int TAMANIO_MINIMO = 93;
	private static final int TAMANIO_MAXIMO = 120;
	private int tamanioAro;

	public Raqueta(int id, String marca, int tamanioAro) {
		super(id, marca);
		this.setTamanioAro(tamanioAro);
	}

	private void setTamanioAro(int tamanioAro) {
		if (tamanioAro < TAMANIO_MINIMO || tamanioAro > TAMANIO_MAXIMO) {
			throw new IllegalArgumentException(MSG_TAMANIO_ARO_INVALIDO);
		}
		this.tamanioAro = tamanioAro;
	}

	@Override
	public void mostrar() {

		System.out.printf(MSG_RAQUETA, this.tamanioAro, this.getMarca(), this.getId());
	}
}
