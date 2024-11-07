package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtils {
	public static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/hackathon";
	public static final String DB_USER = "KD1_86654_Somesh";
	public static final String DB_PASSWORD = "manager";
	
	static {
		  //load and register driver
		  
		  try {
			  Class.forName(DB_DRIVER);
			  
		  } catch (ClassNotFoundException e) {
			// TODO: handle exception
			  e.printStackTrace();
			  System.exit(1);
		}
	  }
	
	public static Connection getConnection() throws Exception {
		Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		return con;
	}
}

