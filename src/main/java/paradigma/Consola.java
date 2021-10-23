package paradigma;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Scanner;
import dao.ItinerarioDAO;

public class Consola {
	private static Scanner teclado = new Scanner(System.in);
	private static ItinerarioDAO itinerario = new ItinerarioDAO();
	private static ParqueAvSiempreViva parque = new ParqueAvSiempreViva();
	
	public static void bienvenidaAlParque() throws SQLException {
		LinkedList<Usuario> usuarios = parque.getUsuarios();
		LinkedList<Sugerencia> sugerencias = parque.getSugerencias();
		itinerario.crearTablaItinerario();
		for (Usuario usuario : usuarios) {
			parque.ordenarPorPreferencia(sugerencias, usuario.getAtraccionPreferida());
			System.out.println("¡Hola " + usuario.getNombre()
					+ "! Bienvenide al Parque Av. Siempre Viva, ¡Donde nada puede MALIR SAL!" + "\n");
			System.out.println("Tu dinero disponible es de " + usuario.getPresupuesto() + " fichas de Tomy y Daly"
					+ "\nTu tiempo disponible para disfrutar en el parque es de " + usuario.getTiempoDisponibleEnHoras()
					+ " horas\n");
			System.out.println("Tenemos para ofrecerte las siguientes actividades:\n");
			for (Sugerencia sugerencia : sugerencias) {
				if (usuario.puedeComprarSugerencia(sugerencia) && sugerencia.tieneCupoDisponible()
						&& !usuario.getItinerario()
								.contains(sugerencia.getNombre() + ", Precio: " + sugerencia.getPrecio()
										+ " fichas de Tomy y Daly" + ", Duracion: " + sugerencia.getDuracionEnHoras()
										+ " horas")) {

					System.out.println(sugerencia.getNombre() + ", Precio: " + sugerencia.getPrecio()
							+ " fichas de Tomy y Daly" + ", Duracion: " + sugerencia.getDuracionEnHoras() + " horas");
					if (sugerencia.esPromocion()) {
						System.out.println("Que incluye las siguientes atracciones:");
						Promocion auxPromocion = (Promocion) sugerencia;
						for (Atraccion atraccionDePromocion : auxPromocion.getAtracciones()) {
							System.out.println(atraccionDePromocion.getNombre());
						}
					}

					Consola.comprarSugerencia(usuario, sugerencia);
				}
			}
			
			System.out.println("\n\nTotal de fichas gastadas: " + usuario.getCostoTotalItinerario()
					+ "\nTiempo necesario: " + usuario.getTiempoTotalItinerario());
			String itinerarioString = "";
			for (String itinerario : usuario.getItinerario()) {
				itinerarioString += itinerario;
			}
			System.out.println(itinerarioString + "\n");
			System.out.println("Gracias por su compra, ¡VUELVA PRONTOS!\n\n\n");
		}

		teclado.close();
	}

	public static void comprarSugerencia(Usuario usuario, Sugerencia sugerencia) throws SQLException {
		System.out.println("\n¿Te gustaria hacer esta actividad? Si/No");
		char respuesta;
		respuesta = teclado.nextLine().charAt(0);
		if (respuesta == 'S' || respuesta == 's') {
			usuario.agregarSugerenciaAlItinerario(sugerencia);
			usuario.restarPresupuestoYtiempo(sugerencia.getPrecio(), sugerencia.getDuracionEnHoras());
			sugerencia.restarCupo();
			itinerario.insert(usuario, sugerencia);
		}
	}

}