package model;

import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;

import dao.AtraccionDAO;
import dao.PromocionDAO;
import dao.UsuarioDAO;

public class ParqueAvSiempreViva {

	private static LinkedList<Atraccion> atracciones = new LinkedList<Atraccion>();
	private static LinkedList<Promocion> promociones = new LinkedList<Promocion>();
	private static LinkedList<Usuario> usuarios = new LinkedList<Usuario>();
	private static LinkedList<Sugerencia> sugerencias = new LinkedList<Sugerencia>();

	public static LinkedList<Atraccion> getAtracciones() throws SQLException {
		AtraccionDAO atraccion = new AtraccionDAO();
		return atracciones = atraccion.leerAtracciones();
	}

	public static LinkedList<Promocion> getPromociones() throws SQLException {
		PromocionDAO promocion = new PromocionDAO();
		return promociones = promocion.leerPromociones(atracciones);
	}

	public static LinkedList<Usuario> getUsuarios() throws SQLException {
		UsuarioDAO usuario = new UsuarioDAO();
		return usuarios = usuario.leerUsuarios();
	}

	public static void ordenarPorPreferencia(LinkedList<Sugerencia> sugerencias, String tipo) {
		Collections.sort(sugerencias, new OrdenarParaSugerir(tipo));
	}

	public static LinkedList<Sugerencia> getSugerencias() {
		for(Atraccion atr : atracciones) {
			if(!sugerencias.contains(atr)) {
				sugerencias.add(atr);
			}
		}
		for(Promocion promo : promociones) {
			if(!sugerencias.contains(promo)) {
				sugerencias.add(promo);
			}
		}
		return sugerencias;
	}

}
