package pruebasModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import model.Atraccion;

public class AtraccionesTest {

	Atraccion a1;

	@Before
	public void setUp() {
		a1 = new Atraccion(1, "ElMonorriel", 1, 3, 5, "Aventura");
	}

	@Test
	public void corroborarSiTieneCupoDisponible() {

		assertTrue(a1.tieneCupoDisponible());
	}

	@Test
	public void corroborarSiRestaCupo() {

		a1.restarCupo();

		int esperado = 4;
		int obtenido = a1.getCupoDisponible();

		assertEquals(esperado, obtenido);
	}

}
