package com.kitri.util.db;

import java.sql.*;

import javax.naming.*;
import javax.sql.DataSource;

public class DBConnection {
	
//	static {
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public static Connection getConnection() throws SQLException {
//		Connection conn = null;
//		conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.29:1521:orcl", "kitri", "kitri");
//		return conn;
//	}
	
	public static Connection getConnection() throws SQLException {
		DataSource ds = null;
		try {
			Context ictx = new InitialContext();
			Context ctx = (Context) ictx.lookup("java:comp/env");
			ds = (DataSource) ctx.lookup("jdbc/kitri");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return ds.getConnection();
	}

}
