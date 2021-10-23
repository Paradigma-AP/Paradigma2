package model;

public abstract class Sugerencia {

	public abstract int getId();

	public abstract int getDuracionEnHoras();

	public abstract String getTipoDeAtraccion();

	public abstract boolean esPromocion();

	public abstract double getPrecio();

	public abstract String getNombre();

	public abstract boolean tieneCupoDisponible();

	public abstract void restarCupo();

}
