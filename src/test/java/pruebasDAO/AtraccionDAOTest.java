package pruebasDAO;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.AtraccionDAO;
import jdbc.ConnectionProvider;
import model.Atraccion;

public class AtraccionDAOTest {

	Connection con;
	Atraccion atraccion;
	AtraccionDAO atraccionDAO;

	@Before
	public void setUp() throws SQLException {
		con = ConnectionProvider.getConnection();
		con.setAutoCommit(false);
		atraccion = new Atraccion(1, "ElMonorriel", 1, 3, 5, "Aventura");
		atraccionDAO = new AtraccionDAO();
	}

	@After
	public void tearDown() throws SQLException {
		con = ConnectionProvider.getConnection();
		con.rollback();
		con.setAutoCommit(true);
	}

	@Test
	public void actualizarTiempoEnBaseDeDatos() throws SQLException {
		atraccion.restarCupo();
		atraccion.restarCupo();

		atraccionDAO.actualizarCupo(atraccion);
		String sql = "SELECT cupo FROM atraccion WHERE id = 1";
		PreparedStatement statement = con.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		int esperadoCupo = 3;
		int obtenidoCupo = resultado.getInt("cupo");
		assertEquals(esperadoCupo, obtenidoCupo);

	}

}
