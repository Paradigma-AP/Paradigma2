package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;

import jdbc.ConnectionProvider;
import model.Sugerencia;
import model.Usuario;

public class ItinerarioDAO {

	public int insert(Usuario usuario, Sugerencia sugerencia) {
		try {
			String sql = "INSERT INTO itinerarios_usuarios (fk_usuario, fk_atraccion, fk_promocion) VALUES (?,?,?)";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setInt(1, usuario.getId());
			if (sugerencia.esPromocion()) {
				statement.setNull(2, Types.INTEGER);
				statement.setInt(3, sugerencia.getId());

			} else {
				statement.setNull(3, Types.INTEGER);
				statement.setInt(2, sugerencia.getId());
			}

			int rows = statement.executeUpdate();
			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

}
