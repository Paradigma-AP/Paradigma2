package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import jdbc.ConnectionProvider;
import model.Atraccion;

public class AtraccionDAO {

	public LinkedList<Atraccion> leerAtracciones() {
		try {
			String sql = "SELECT * FROM atraccion";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			LinkedList<Atraccion> atracciones = new LinkedList<Atraccion>();
			while (resultados.next()) {
				atracciones.add(atraccion(resultados));
			}
			return atracciones;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int actualizarCupo(Atraccion atraccion) {
		try {
			String sql = "UPDATE atraccion SET cupo = ? WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setInt(1, atraccion.getCupoDisponible());
			statement.setInt(2, atraccion.getId());

			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Atraccion atraccion(ResultSet resultados) {
		try {
			return new Atraccion(resultados.getInt("id"), resultados.getString("nombre"), resultados.getInt("duracion"),
					resultados.getInt("precio"), resultados.getInt("cupo"), resultados.getString("fk_tipoDeAtraccion"));
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
}