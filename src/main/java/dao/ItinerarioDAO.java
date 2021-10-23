package dao;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import jdbc.ConnectionProvider;
import model.Sugerencia;
import model.Usuario;

public class ItinerarioDAO {

	public int crearTablaItinerario() throws SQLException { // CORREGIR
		String sql = "CREATE TABLE IF NOT EXISTS \"itinerarios_usuarios\" (\r\n" + "	\"id_usuario\"	INTEGER,\r\n"
				+ "	\"id_atraccion\"	INTEGER CHECK((id_promocion NOTNULL AND id_atraccion ISNULL) OR (id_atraccion NOTNULL AND id_promocion ISNULL)) ,\r\n"
				+ "	\"id_promocion\"	INTEGER CHECK((id_promocion NOTNULL AND id_atraccion ISNULL) OR (id_atraccion NOTNULL AND id_promocion ISNULL)),\r\n"
				+ "	PRIMARY KEY(\"id_usuario\",\"id_atraccion\",\"id_promocion\")\r\n" + ");";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);

		int rows = statement.executeUpdate();
		return rows;
	}

	public int insert(Usuario usuario, Sugerencia sugerencia) throws SQLException {
		String sql = "INSERT INTO itinerarios_usuarios (id_usuario, id_atraccion, id_promocion) VALUES (?,?,?)";
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
	}

}
