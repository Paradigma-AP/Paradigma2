package pruebasDAO;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.ItinerarioDAO;
import jdbc.ConnectionProvider;
import model.Atraccion;
import model.Promocion;
import model.PromocionAbsoluta;
import model.Usuario;

public class ItinerarioDAOTest {
	Connection con;
	Atraccion a1;
	Atraccion a2;
	ItinerarioDAO itinerarioDAO;
	Usuario usuario;
	Promocion promoAventura;
	
	@Before
	public void setUp() throws SQLException {
		con = ConnectionProvider.getConnection();
		con.setAutoCommit(false);
		itinerarioDAO = new ItinerarioDAO();
		a1 = new Atraccion(1, "ElMonorriel", 1, 3, 1, "Aventura");
		a2 = new Atraccion(2, "LaFabricaDeCajas", 3, 2, 5, "Aventura");
		Atraccion[] atracciones = { a1, a2 };
		promoAventura = new PromocionAbsoluta(1, "PromoAventura", "Aventura", atracciones);
		usuario = new Usuario(1,"Homero", 13, 20, "Gastronomia");
	
	}
		
	@After
	public void tearDown() throws SQLException {
		con = ConnectionProvider.getConnection();
		con.rollback();
		con.setAutoCommit(true);
	}

	@Test
	public void insertarUnUsuarioyUnaAtraccion() throws SQLException {
		itinerarioDAO.insert(usuario, a1);

		String sql = "SELECT * FROM itinerarios_usuarios";
		PreparedStatement statement = con.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		
		int esperadoAtraccion = 1;
		int obtenidoAtraccion = resultado.getInt("fk_atraccion");
		assertEquals(obtenidoAtraccion, esperadoAtraccion);
		
		int esperadoUsuario = 1;
		int obtenidoUsuario = resultado.getInt("fk_usuario");
		assertEquals(esperadoUsuario, obtenidoUsuario);
		
		int esperadoPromo = 0;
		int obtenidoPromo = resultado.getInt("fk_promocion");
		assertEquals(esperadoPromo, obtenidoPromo);

	}
	
	
	@Test
	public void insertarUnUsuarioyUnaPromo() throws SQLException {
		itinerarioDAO.insert(usuario, promoAventura);

		String sql = "SELECT * FROM itinerarios_usuarios";
		PreparedStatement statement = con.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		
		int esperadoAtraccion = 0;
		int obtenidoAtraccion = resultado.getInt("fk_atraccion");
		assertEquals(obtenidoAtraccion, esperadoAtraccion);
		
		int esperadoUsuario = 1;
		int obtenidoUsuario = resultado.getInt("fk_usuario");
		assertEquals(esperadoUsuario, obtenidoUsuario);
		
		int esperadoPromo = 1;
		int obtenidoPromo = resultado.getInt("fk_promocion");
		assertEquals(esperadoPromo, obtenidoPromo);

	}


}
