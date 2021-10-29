package app;

import model.Consola;
import model.ParqueAvSiempreViva;

public class App {

	public static void main(String[] args) {
		ParqueAvSiempreViva.getAtracciones();
		ParqueAvSiempreViva.getPromociones();
		ParqueAvSiempreViva.getSugerencias();
		ParqueAvSiempreViva.getUsuarios();

		Consola.bienvenidaAlParque();
	}
}
