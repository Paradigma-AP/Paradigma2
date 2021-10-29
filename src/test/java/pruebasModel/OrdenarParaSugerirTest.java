package pruebasModel;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import model.Atraccion;
import model.ParqueAvSiempreViva;
import model.Promocion;
import model.PromocionAbsoluta;
import model.Sugerencia;
import model.Usuario;

public class OrdenarParaSugerirTest {

	Atraccion a1;
	Atraccion a2;
	Atraccion a3;
	Atraccion a4;
	LinkedList<Sugerencia> sugerencias;
	Usuario u;
	Promocion promo;

	@Before
	public void setUp() {
		sugerencias = new LinkedList<Sugerencia>();
		u = new Usuario(1, "Homero", 13, 20, "Gastronomia");
		a1 = new Atraccion(1, "ElMonorriel", 1, 3, 5, "Aventura");
		a2 = new Atraccion(2, "LaFabricaDeCajas", 15, 2, 5, "Aventura");
		a3 = new Atraccion(3, "KrustyBurguer", 2, 4, 5, "Gastronomia");
		a4 = new Atraccion(2, "Taberna de Moe", 11, 2, 5, "Aventura");

		Atraccion[] atracciones = { a1, a2 };
		promo = new PromocionAbsoluta(1, "PromoAventura", "Aventura", atracciones);

		sugerencias.add(a1);
		sugerencias.add(a2);
		sugerencias.add(a3);
		sugerencias.add(promo);
	}

	@Test
	public void ordenarPorTipoDeSugerenciaTest() {
		ParqueAvSiempreViva.ordenarPorPreferencia(sugerencias, u.getAtraccionPreferida());

		Atraccion esperado = a3;
		Sugerencia obtenido = sugerencias.get(0);

		assertEquals(esperado, obtenido);

	}

	@Test
	public void ordenarSiEsPromocionTest() {
		ParqueAvSiempreViva.ordenarPorPreferencia(sugerencias, u.getAtraccionPreferida());

		Promocion esperado = promo;
		Sugerencia obtenido = sugerencias.get(1);

		assertEquals(esperado, obtenido);

	}

	@Test
	public void ordenarPorPrecioTest() {
		ParqueAvSiempreViva.ordenarPorPreferencia(sugerencias, u.getAtraccionPreferida());

		Atraccion esperado3 = a1;
		Sugerencia obtenido3 = sugerencias.get(2);

		assertEquals(esperado3, obtenido3);
	}

	@Test
	public void ordenarPorDuracionTest() {
		ParqueAvSiempreViva.ordenarPorPreferencia(sugerencias, u.getAtraccionPreferida());

		Atraccion esperado3 = a2;
		Sugerencia obtenido3 = sugerencias.get(3);

		assertEquals(esperado3, obtenido3);
	}

}
