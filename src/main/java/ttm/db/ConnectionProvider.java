package ttm.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {

private static Connection connection;
	
	public static Connection getConnection() throws SQLException {
		
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		}
		String dbAbsoluteUrl = "jdbc:sqlite:C:/Users/Sergito/sergioNoir/TurismoTierraMediaWeb/src/main/database/ttm_db.db";
		//String dbAbsoluteUrl = "jdbc:sqlite:/Users/ntbki5/eclipse-workspace/TurismoTierraMediaWeb/src/main/database/ttm_db.db";
		//String dbAbsoluteUrl = "jdbc:sqlite:/Users/Pika/eclipse-workspace/ttm_web_voidteam/src/main/database/ttm_db.db";
		
		if (connection == null) {
			connection = DriverManager.getConnection(dbAbsoluteUrl);
		}
		
		return connection;
	}
}
 