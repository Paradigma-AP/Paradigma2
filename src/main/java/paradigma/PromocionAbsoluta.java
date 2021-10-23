package paradigma;

public class PromocionAbsoluta extends Promocion {

	private static final double DESCUENTO = 3;

	public PromocionAbsoluta(int id, String nombre, String tipoDePromocion, Atraccion[] atracciones) {
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
		return precio - DESCUENTO;
	}

}