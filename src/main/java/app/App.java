package app;

import java.sql.SQLException;

import model.Consola;
import model.ParqueAvSiempreViva;

public class App {

	public static void main(String[] args) throws SQLException {
		ParqueAvSiempreViva.getAtracciones();
		ParqueAvSiempreViva.getPromociones();
		Consola.bienvenidaAlParque();

	}

}
