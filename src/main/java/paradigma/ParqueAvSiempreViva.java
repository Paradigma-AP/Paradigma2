package paradigma;

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

	public LinkedList<Atraccion> getAtracciones() throws SQLException {
		AtraccionDAO atraccion = new AtraccionDAO();
		return atracciones = atraccion.leerAtracciones();
	}

	public LinkedList<Promocion> getPromociones() throws SQLException {
		PromocionDAO promocion = new PromocionDAO();
		return promociones = promocion.leerPromociones(atracciones);
	}

	public LinkedList<Usuario> getUsuarios() throws SQLException {
		UsuarioDAO usuario = new UsuarioDAO();
		return usuarios = usuario.leerUsuarios();
	}

	public void ordenarPorPreferencia(LinkedList<Sugerencia> sugerencias, String tipo) {
		Collections.sort(sugerencias, new OrdenarParaSugerir(tipo));
	}

	public LinkedList<Sugerencia> getSugerencias() {
		sugerencias.addAll(atracciones);
		sugerencias.addAll(promociones);
		return sugerencias;
	}

}
