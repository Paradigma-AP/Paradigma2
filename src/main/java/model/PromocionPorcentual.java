package model;

public class PromocionPorcentual extends Promocion {

	private static final double DESCUENTO = 0.2;

	public PromocionPorcentual(int id, String nombre, String tipoDePromocion, Atraccion[] atracciones) {
		super(id, nombre, tipoDePromocion, atracciones);
	}

	@Override
	public double getPrecio() {
		int i = 0;
		int precio = 0;
		while (i < atracciones.length) {
			precio += atracciones[i].getPrecio();
			i++;
		}
		double descuento = precio * DESCUENTO;
		double precioTotal = precio - descuento;
		return precioTotal;
	}

}