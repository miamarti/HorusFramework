package com.horusframework.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.horusframework.annotation.HorusConnectionParameters;

public enum HorusMySQLDriveConnection {
	getInstance;

	private static Connection connection = null;
	private static Boolean reconect = true;
	private static HorusConnectionParameters stringConnection = null;

	HorusMySQLDriveConnection() {
	}

	private static final void StartConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			HorusMySQLDriveConnection.connection = DriverManager.getConnection(("jdbc:mysql://" + stringConnection.host() + ":" + stringConnection.port() + "/" + stringConnection.database()), stringConnection.user(), stringConnection.password());
			HorusMySQLDriveConnection.reconect = true;
		} catch (Exception ex) {
			System.out.println("Could not connect - StartConnection (). Let's try again!");
			ex.printStackTrace();
			StartConnection();
		}
	}

	public Connection getConnection(HorusConnectionParameters stringConnection) {
		try {
			if (HorusMySQLDriveConnection.connection == null) {
				HorusMySQLDriveConnection.stringConnection = stringConnection;
				StartConnection();
			} else {
				if (!HorusMySQLDriveConnection.connection.isValid(0)) {
					if (HorusMySQLDriveConnection.reconect) {
						HorusMySQLDriveConnection.reconect = false;
						StartConnection();
						System.out.println("His connection was reset by TimeOut ...");
					}
				}
			}
			return HorusMySQLDriveConnection.connection;
		} catch (SQLException e) {
			System.out.println("getConnection does not respond because not finding this connection");
			e.printStackTrace();
			return this.getConnection(stringConnection);
		}
	}

	/**
	 * @throws SQLException
	 */
	public void close() throws SQLException {
		try {
			connection.close();
		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public HorusConnectionParameters getStringConnection() {
		return stringConnection;
	}

	public static final void setStringConnection(HorusConnectionParameters stringConnection) {
		HorusMySQLDriveConnection.stringConnection = stringConnection;
	}
}
