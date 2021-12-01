package ttm.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {

private static Connection connection;
	
	public static Connection getConnection() throws SQLException {
		if (connection == null) {
			connection = DriverManager.getConnection("jdbc:sqlite:database/ttm_db.db");
		}
		return connection;
	}
}
