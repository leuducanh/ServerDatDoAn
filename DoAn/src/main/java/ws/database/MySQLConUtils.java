package ws.database;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConUtils {
	public static Connection getMySQLConnection(){
		String host = "localhost";
		String dbName = "dat_do_an";
		String username = "root";
		String password = "Leu1234567";
		
		return getMySQLConnection(host, dbName, username, password);
	}
	
	public static Connection getMySQLConnection(String host,String dbName,String username,String password){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String connectionUrl = "jdbc:mysql://" + host + ":3306/" + dbName;
		Connection con = null;
		try {
			con = DriverManager.getConnection(connectionUrl,username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}
}
