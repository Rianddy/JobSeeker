package function.index;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectingToMysql {

	public Connection Get_Connection() throws Exception {
		try {
			String connectionURL = "jdbc:mysql://"+"127.0.0.1"+":"+"3306"+"/"+"JobSeeker";
			Connection connection = null;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionURL, "root",
					"root");
			return connection;
		} catch (SQLException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}
	public Connection Get_Connection(String databaseName, String username, String password, String server, String port) throws Exception {
		try {
			String connectionURL = "jdbc:mysql://"+server+":"+port+"/"+databaseName;
			Connection connection = null;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionURL, username,
					password);
			return connection;
		} catch (SQLException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}
}
