package model;

import java.util.Comparator;

public class OrdenarParaSugerir implements Comparator<Sugerencia> {

	String atraccionFavorita;

	public OrdenarParaSugerir(String atraccionFavorita) {
		this.atraccionFavorita = atraccionFavorita;
	}

	@Override
	public int compare(Sugerencia o1, Sugerencia o2) {

		if (o1.getTipoDeAtraccion() == atraccionFavorita && o2.getTipoDeAtraccion() != atraccionFavorita) {
			return -1;
		} else if (o1.getTipoDeAtraccion() != atraccionFavorita && o2.getTipoDeAtraccion() == atraccionFavorita) {
			return 1;
		}
		if (o1.esPromocion() && !o2.esPromocion()) {
			return -1;
		} else if (!o1.esPromocion() && o2.esPromocion()) {
			return 1;
		}
		if (o1.getPrecio() > o2.getPrecio()) {
			return -1;
		} else if (o1.getPrecio() < o2.getPrecio()) {
			return 1;
		}
		if (o1.getDuracionEnHoras() > o2.getDuracionEnHoras()) {
			return -1;
		} else if (o1.getDuracionEnHoras() < o2.getDuracionEnHoras()) {
			return 1;
		}
		return 0;
	}
}
