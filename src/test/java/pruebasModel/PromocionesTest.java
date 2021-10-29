package pruebasModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import model.Atraccion;
import model.Promocion;
import model.PromocionAbsoluta;
import model.PromocionPorcentual;
import model.PromocionTresPorDos;

public class PromocionesTest {

	@Test
	public void calcularPrecioDeAtraccionAbsoluta() {
		Atraccion a1 = new Atraccion(1, "ElMonorriel", 1, 3, 1, "Aventura");
		Atraccion a2 = new Atraccion(2, "LaFabricaDeCajas", 3, 2, 5, "Aventura");
		Atraccion[] atracciones = { a1, a2 };
		Promocion promoAventura = new PromocionAbsoluta(1, "PromoAventura", "Aventura", atracciones);

		double esperado = 2;
		double obtenido = promoAventura.getPrecio();

		assertEquals(esperado, obtenido, 0);
	}

	@Test
	public void calcularPrecioDeAtraccionPorcentual() {
		Atraccion a1 = new Atraccion(1, "KrustyBurguer", 2, 4, 5, "Gastronomia");
		Atraccion a2 = new Atraccion(2, "TabernaDeMoe", 4, 6, 4, "Gastronomia");
		Atraccion[] atracciones = { a1, a2 };
		Promocion promoGastronomica = new PromocionPorcentual(1, "PromoGastronomica", "Gastronomia", atracciones);

		double esperado = 8;
		double obtenido = promoGastronomica.getPrecio();

		assertEquals(esperado, obtenido, 0);
	}

	@Test
	public void calcularPrecioDeAtraccionTresPorDos() {
		Atraccion a1 = new Atraccion(1, "LaPlantaNuclear", 3, 2, 4, "VisitaGuiada");
		Atraccion a2 = new Atraccion(2, "PenitenciariaDeSpringfield", 2, 4, 4, "VisitaGuiada");
		Atraccion a3 = new Atraccion(3, "MuseoJeremiasSpringfield", 1, 1, 2, "VisitaGuiada");
		Atraccion[] atracciones2 = { a1, a2, a3 };
		Promocion promoDePaseo = new PromocionTresPorDos(1, "PromoDePaseo", "VisitaGuiada", atracciones2);

		double esperado = 6;
		double obtenido = promoDePaseo.getPrecio();

		assertEquals(esperado, obtenido, 0);
	}

	@Test
	public void calcularTiempoDePromocionTresPorDos() {
		Atraccion a1 = new Atraccion(1, "LaPlantaNuclear", 3, 2, 4, "VisitaGuiada");
		Atraccion a2 = new Atraccion(2, "PenitenciariaDeSpringfield", 2, 4, 4, "VisitaGuiada");
		Atraccion a3 = new Atraccion(3, "MuseoJeremiasSpringfield", 1, 1, 2, "VisitaGuiada");
		Atraccion[] atracciones2 = { a1, a2, a3 };
		Promocion promoDePaseo = new PromocionTresPorDos(1, "PromoDePaseo", "VisitaGuiada", atracciones2);

		int esperado = 6;
		int obtenido = promoDePaseo.getDuracionEnHoras();

		assertEquals(esperado, obtenido);
	}

	@Test
	public void verificarSiTieneCupo() {
		Atraccion a1 = new Atraccion(1, "ElMonorriel", 1, 3, 1, "Aventura");
		Atraccion a2 = new Atraccion(2, "LaFabricaDeCajas", 3, 2, 5, "Aventura");
		Atraccion[] atracciones = { a1, a2 };
		Promocion promoAventura = new PromocionAbsoluta(1, "PromoAventura", "Aventura", atracciones);

		assertTrue(promoAventura.tieneCupoDisponible());
	}

	@Test
	public void verificarSiRestaCupoDeAtracciones() {
		Atraccion a1 = new Atraccion(1, "ElMonorriel", 1, 3, 1, "Aventura");
		Atraccion a2 = new Atraccion(2, "LaFabricaDeCajas", 3, 2, 5, "Aventura");
		Atraccion[] atracciones = { a1, a2 };
		Promocion promoAventura = new PromocionAbsoluta(1, "PromoAventura", "Aventura", atracciones);

		promoAventura.restarCupo();
		int esperado = 0;
		int obtenido = a1.getCupoDisponible();

		int esperado2 = 4;
		int obtenido2 = a2.getCupoDisponible();

		assertEquals(esperado, obtenido);
		assertEquals(esperado2, obtenido2);
	}

	@Test
	public void calcularSiTieneCupo() {
		Atraccion a1 = new Atraccion(1, "KrustyBurguer", 2, 4, 1, "Gastronomia");
		Atraccion a2 = new Atraccion(2, "TabernaDeMoe", 4, 6, 4, "Gastronomia");
		Atraccion[] atracciones = { a1, a2 };
		Promocion promoGastronomica = new PromocionPorcentual(1, "PromoGastronomica", "Gastronomia", atracciones);

		a1.restarCupo();

		assertFalse(promoGastronomica.tieneCupoDisponible());
	}

}
