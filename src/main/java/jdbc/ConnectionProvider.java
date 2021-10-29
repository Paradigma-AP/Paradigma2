package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

import dao.MissingDataException;

public class ConnectionProvider {

	private static String url = "jdbc:sqlite:Paradigma.db";
	private static Connection connection;

	public static Connection getConnection() {
		try {
			if (connection == null) {
				connection = DriverManager.getConnection(url);
			}
			return connection;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
}
