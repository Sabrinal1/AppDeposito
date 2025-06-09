package clases;

public class Bolso extends Producto {
	private static final String MODELO_DE_BOLSO_INVALIDO = "Modelo de bolso inv�lido.";
	private static final String MSG_MODELO = "El bolso es de modelo %s es de la marca %s y tiene un id %d\n";

	private String modelo;

	public Bolso(int id, String marca, String modelo) {
		super(id, marca);
		this.setModelo(modelo);
	}

	private void setModelo(String modelo) {
		if (modelo == null || modelo.isEmpty()) {
			throw new IllegalArgumentException(MODELO_DE_BOLSO_INVALIDO);
		}
		this.modelo = modelo;
	}

	@Override
	public void mostrar() {

		System.out.printf(MSG_MODELO, this.modelo, this.getMarca(), this.getId());
	}
}
