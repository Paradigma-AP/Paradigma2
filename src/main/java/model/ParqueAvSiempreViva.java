package model;

import java.util.Collections;
import java.util.LinkedList;

import dao.AtraccionDAO;
import dao.PromocionDAO;
import dao.UsuarioDAO;

public class ParqueAvSiempreViva {

	private static LinkedList<Atraccion> atracciones;
	private static LinkedList<Promocion> promociones;
	private static LinkedList<Usuario> usuarios;
	private static LinkedList<Sugerencia> sugerencias;

	public static LinkedList<Atraccion> getAtracciones() {
		if (atracciones == null) {
			AtraccionDAO atraccion = new AtraccionDAO();
			atracciones = atraccion.leerAtracciones();
		}
		return atracciones;
	}

	public static LinkedList<Promocion> getPromociones() {
		if (promociones == null) {
			PromocionDAO promocion = new PromocionDAO();
			promociones = promocion.leerPromociones(atracciones);
		}
		return promociones;
	}

	public static LinkedList<Usuario> getUsuarios() {
		if (usuarios == null) {
			UsuarioDAO usuario = new UsuarioDAO();
			usuarios = usuario.leerUsuarios();
		}
		return usuarios;
	}

	public static void ordenarPorPreferencia(LinkedList<Sugerencia> sugerencias, String tipo) {
		Collections.sort(sugerencias, new OrdenarParaSugerir(tipo));
	}

	public static LinkedList<Sugerencia> getSugerencias() {
		if (sugerencias == null) {
			sugerencias = new LinkedList<Sugerencia>();
		}
		if (sugerencias.isEmpty()) {
			sugerencias.addAll(atracciones);
			sugerencias.addAll(promociones);
		}
		return sugerencias;
	}

}
