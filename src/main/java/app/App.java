package app;

import java.sql.SQLException;

import paradigma.Consola;
import paradigma.ParqueAvSiempreViva;

public class App {

	public static void main(String[] args) throws SQLException {
		ParqueAvSiempreViva parque = new ParqueAvSiempreViva();
		parque.getUsuarios();
		parque.getAtracciones();
		parque.getPromociones();
		parque.getSugerencias();

		Consola.bienvenidaAlParque();

	}

}
