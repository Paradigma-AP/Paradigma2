package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import jdbc.ConnectionProvider;
import model.Usuario;

public class UsuarioDAO {

	public LinkedList<Usuario> leerUsuarios() {
		try {
			String sql = "SELECT * FROM usuario";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			LinkedList<Usuario> usuarios = new LinkedList<Usuario>();
			while (resultados.next()) {
				usuarios.add(usuarioResult(resultados));
			}
			return usuarios;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int actualizarTiempoYPresupuesto(Usuario usuario) {
		try {
			String sql = "UPDATE usuario SET tiempo_disp = ?, presupuesto = ?  WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setInt(1, usuario.getTiempoDisponibleEnHoras());
			statement.setInt(2, usuario.getPresupuesto());
			statement.setInt(3, usuario.getId());

			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Usuario usuarioResult(ResultSet resultados) throws SQLException {
		return new Usuario(resultados.getInt("id"), resultados.getString("nombre"), resultados.getInt("tiempo_disp"),
				resultados.getInt("presupuesto"), resultados.getString("fk_tipoDeAtraccion"));
	}
}