package AkshayDB;

import java.sql.ResultSet; 
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;

public class JDBCHelper {
	public static Connection getConnection() {
		
		
		try {
			Connection con = DriverManager.getConnection(Constants.URL, Constants.UID, Constants.PASS);
			return con;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
//	another method to close resources
	public static void close(ResultSet x) {
		if(x!=null)
			try {
				x.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
	}
	
	public static void close(Statement x) {
		if(x!=null)
			try {
				x.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
	}
	
	public static void close(Connection x) {
		if(x!=null)
			try {
				x.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
	}
}
