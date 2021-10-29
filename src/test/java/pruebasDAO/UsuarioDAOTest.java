package pruebasDAO;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.UsuarioDAO;
import jdbc.ConnectionProvider;
import model.Usuario;

public class UsuarioDAOTest {

	Connection con;
	Usuario usuario;
	UsuarioDAO usuarioDAO;

	@Before
	public void setUp() throws SQLException {
		con = ConnectionProvider.getConnection();
		con.setAutoCommit(false);
		usuario = new Usuario(1, "Homero", 13, 20, "Gastronomia");
		usuarioDAO = new UsuarioDAO();
	}

	@After
	public void tearDown() throws SQLException {
		con = ConnectionProvider.getConnection();
		con.rollback();
		con.setAutoCommit(true);
	}

	@Test
	public void actualizarTiempoYPresupuestoEnBaseDeDatos() throws SQLException {
		usuario.restarPresupuestoYtiempo(5, 2);
		usuarioDAO.actualizarTiempoYPresupuesto(usuario);

		String sql = "SELECT * FROM usuario WHERE id = 1";
		PreparedStatement statement = con.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		int esperadoTiempo = 11;
		int obtenidoTiempo = resultado.getInt("tiempo_disp");
		assertEquals(obtenidoTiempo, esperadoTiempo);

		int esperadoPresupuesto = 15;
		int obtenidoPresupuesto = resultado.getInt("presupuesto");
		assertEquals(obtenidoPresupuesto, esperadoPresupuesto);
	}

}
