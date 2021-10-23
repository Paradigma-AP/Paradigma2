package model;

public abstract class Promocion extends Sugerencia {

	private int id;
	private String nombre;
	private String tipoDePromocion;
	protected Atraccion[] atracciones;
	private boolean esPromocion = true;

	public Promocion(int id, String nombre, String tipoDePromocion, Atraccion[] atracciones) {
		this.id = id;
		this.nombre = nombre;
		this.tipoDePromocion = tipoDePromocion;
		this.atracciones = atracciones;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public Atraccion[] getAtracciones() {
		return atracciones;
	}

	public String getTipoDeAtraccion() {
		return this.tipoDePromocion;
	}

	public boolean esPromocion() {
		return esPromocion;
	}

	public int getDuracionEnHoras() {
		int i = 0;
		int duracion = 0;
		while (i < atracciones.length) {
			duracion += atracciones[i].getDuracionEnHoras();
			i++;
		}
		return duracion;
	}

	public void restarCupo() {
		int i = 0;
		while (i < atracciones.length) {
			atracciones[i].restarCupo();
			i++;
		}
	}

	public boolean tieneCupoDisponible() {
		int i = 0;
		while (i < atracciones.length) {
			if (atracciones[i].tieneCupoDisponible()) {
				i++;
			} else {
				return false;
			}
		}
		return true;
	}

	public abstract double getPrecio();

	@Override
	public String toString() {
		String atraccionesString = "";
		for (Atraccion atraccion : atracciones) {
			atraccionesString += atraccion;
		}
		return "\nPromocion: Nombre= " + nombre + ", TipoDePromocion= " + tipoDePromocion + "\n Atracciones= "
				+ atraccionesString;
	}

}
