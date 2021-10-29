package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import jdbc.ConnectionProvider;
import model.Atraccion;
import model.Promocion;
import model.PromocionAbsoluta;
import model.PromocionPorcentual;
import model.PromocionTresPorDos;

public class PromocionDAO {

	public LinkedList<Promocion> leerPromociones(LinkedList<Atraccion> atr) {
		try {
			String sql = "SELECT promocion.*, group_concat(fk_atraccion, ',') AS 'list_atr' FROM promocion JOIN atraccion_promocion ON atraccion_promocion.fk_promocion = promocion.id GROUP BY promocion.id";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			LinkedList<Promocion> promocion = new LinkedList<Promocion>();
			while (resultados.next()) {
				int id = resultados.getInt("id");
				String nombre = resultados.getString("nombre");
				String tipo = resultados.getString("fk_tipoDeAtraccion");
				String[] atraccionString = resultados.getString("list_atr").split(",");
				Atraccion[] atracciones = new Atraccion[atraccionString.length];
				for (int i = 0; i < atraccionString.length; i++) {
					for (Atraccion atraccion : atr) {
						if (atraccion.getId() == Integer.valueOf(atraccionString[i])) {
							atracciones[i] = atraccion;
						}
					}
				}
				if (resultados.getInt("id") == 1) {
					promocion.add(new PromocionAbsoluta(id, nombre, tipo, atracciones));
				} else if (resultados.getInt("id") == 2) {
					promocion.add(new PromocionTresPorDos(id, nombre, tipo, atracciones));
				} else if (resultados.getInt("id") == 3) {
					promocion.add(new PromocionPorcentual(id, nombre, tipo, atracciones));
				}
			}
			return promocion;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

}