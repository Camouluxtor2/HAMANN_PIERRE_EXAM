package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;

public class ConnectionDAO {

	private String url = "jdbc:mysql://localhost:3306/exam_test?serverTimezone=" + TimeZone.getDefault().getID();
	private String util = "root";
	private String mdp = "";

	private static Connection connection;

	private ConnectionDAO() {
		try {
			connection = DriverManager.getConnection(url, util, mdp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getInstance() {
		if (connection == null) {
			new ConnectionDAO();
		}
		return connection;
	}

}
