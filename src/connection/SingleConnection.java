package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingleConnection {
	
	private static String url = "jdbc:postgresql://ec2-54-225-72-238.compute-1.amazonaws.com\r\n" + 
			":5432/dejm1o7e3q2c45";
	private static String user = "teeonzhefzzvqm";
	private static String pwd = "a48abe33ce3e5dd598c3251654b8095ab191c0de438787c2e5c0ab5b6e85ea8b";
	
	private static Connection connection = null;
	
	static{
		Connect();
	}
	
	private SingleConnection() {
		Connect();
	}
	
	private static void Connect() {
		if(connection == null) {
			try {
				Class.forName("org.postgresql.Driver"); //carrega o driver do banco de dados
				connection = DriverManager.getConnection(url,user,pwd);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static Connection getConnection() {		
		return connection;
	}
	
}
