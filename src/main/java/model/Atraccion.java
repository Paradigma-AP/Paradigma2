package model;

public class Atraccion extends Sugerencia {
	private int id;
	private String nombre;
	private int duracionEnHoras;
	private int precio;
	private int cupoDisponible;
	private String tipoDeAtraccion;
	private boolean esPromocion = false;

	public Atraccion(int id, String nombre, int duracionEnHoras, int precio, int cupo, String tipoDeAtraccion) {
		this.id = id;
		this.nombre = nombre;
		this.duracionEnHoras = duracionEnHoras;
		this.precio = precio;
		this.cupoDisponible = cupo;
		this.tipoDeAtraccion = tipoDeAtraccion;
	}
	
	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public int getDuracionEnHoras() {
		return duracionEnHoras;
	}

	public double getPrecio() {
		return precio;
	}

	public int getCupoDisponible() {
		return cupoDisponible;
	}

	public String getTipoDeAtraccion() {
		return tipoDeAtraccion;
	}

	public boolean tieneCupoDisponible() {
		return this.cupoDisponible > 0;
	}

	public void restarCupo() {
		this.cupoDisponible -= 1;
	}

	public boolean esPromocion() {
		return esPromocion;
	}

	@Override
	public String toString() {
		return " \nAtraccion: Nombre= " + nombre + ", Duracion= " + duracionEnHoras + " horas, Precio= " + precio
				+ "monedas de Tomy y Daly, Tipo De Atraccion= " + tipoDeAtraccion;
	}

}
