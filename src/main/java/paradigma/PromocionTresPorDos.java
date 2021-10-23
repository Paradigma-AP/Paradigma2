package paradigma;

public class PromocionTresPorDos extends Promocion {

	public PromocionTresPorDos(int id, String nombre, String tipoDePromocion, Atraccion[] atracciones) {
		super(id, nombre, tipoDePromocion, atracciones);
	}

	@Override
	public double getPrecio() {
		int i = 0;
		int precio = 0;
		while (i < atracciones.length - 1) {
			precio += atracciones[i].getPrecio();
			i++;
		}
		return precio;
	}

}
