package pruebasModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import model.Atraccion;
import model.Usuario;

public class UsuarioTest {

	Usuario u1;
	Atraccion a1;
	Atraccion a2;

	@Before
	public void setUp() {
		u1 = new Usuario(1,"Homero", 13, 20, "Gastronomia");
		a1 = new Atraccion(1,"ElMonorriel", 1, 3, 5, "Aventura");
		a2 = new Atraccion(2,"LaFabricaDeCajas", 15, 2, 5, "Aventura");
	}

	@Test
	public void puedeComprarSugerencia() {
		assertTrue(u1.puedeComprarSugerencia(a1));
	}

	@Test
	public void NopuedeComprarSugerencia() {
		assertFalse(u1.puedeComprarSugerencia(a2));
	}

	@Test
	public void restarPresupuestoYtiempo() {
		u1.restarPresupuestoYtiempo(a1.getPrecio(), a1.getDuracionEnHoras());

		double esperado = 17;
		double obtenido = u1.getPresupuesto();

		assertEquals(esperado, obtenido, 0);

		int esperado2 = 12;
		int obtenido2 = u1.getTiempoDisponibleEnHoras();

		assertEquals(esperado2, obtenido2);	
	}

	@Test
	public void CorroborarSiAgregaSugerenciaAlItinerario() {
		u1.agregarSugerenciaAlItinerario(a1);

		assertTrue(u1.getItinerario().contains(a1.getNombre() + ", Precio: " + a1.getPrecio() + " fichas de Tomy y Daly"+ ", Duracion: " + a1.getDuracionEnHoras() + " horas"));

	}
	
	@Test
	public void CorroborarSiCuandoAgregoASugerenciaDescuentaTiempoYFichas() {
		u1.agregarSugerenciaAlItinerario(a1);
	
		int esperado = 1;
		int obtenido = u1.getTiempoTotalItinerario();

		assertEquals(esperado, obtenido);	

		double esperado2 = 3;
		double obtenido2 = u1.getCostoTotalItinerario();

		assertEquals(esperado2, obtenido2, 0);	
	}

}
