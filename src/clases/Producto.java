package clases;

public abstract class Producto implements Mostrable {
	private static final String MSG_MARCA_INVALIDA = "La marca no puede ser nula ni vac�a";
	private static final String MSG_ID_INVALIDO = "El id no puede ser menor a 1";

	private int id;
	private String marca;

	public Producto(int id, String marca) {
		this.setId(id);
		this.setMarca(marca);
	}

	private void setId(int id) {
		if (id < 1) {
			throw new IllegalArgumentException(MSG_ID_INVALIDO);
		}
		this.id = id;
	}

	private void setMarca(String marca) {
		if (marca == null || marca.isEmpty()) {
			throw new IllegalArgumentException(MSG_MARCA_INVALIDA);
		}
		this.marca = marca;
	}

	public boolean coincideId(int id) {
		return this.id == id;
	}

	public int getId() {
		return id;
	}

	protected String getMarca() {
		return marca;
	}

}
