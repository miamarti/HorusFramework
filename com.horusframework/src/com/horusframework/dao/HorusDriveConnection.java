package com.horusframework.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.horusframework.annotation.HorusConnectionParameters;

public enum HorusDriveConnection {
	getInstance;

	private static Connection connection = null;
	private static Boolean reconect = true;
	private static HorusConnectionParameters stringConnection = null;

	HorusDriveConnection() {
	}

	private static final void StartConnection() {
		try {
			Class.forName(stringConnection.driverName());
			HorusDriveConnection.connection = DriverManager.getConnection(("jdbc:" + stringConnection.jdbc() + "://" + stringConnection.host() + ":" + stringConnection.port() + "/" + stringConnection.database()), stringConnection.user(), stringConnection.password());
			HorusDriveConnection.reconect = true;
		} catch (Exception ex) {
			System.out.println("Could not connect - StartConnection (). Let's try again!");
			ex.printStackTrace();
			StartConnection();
		}
	}

	public Connection getConnection(HorusConnectionParameters stringConnection) {
		try {
			if (HorusDriveConnection.connection == null) {
				HorusDriveConnection.stringConnection = stringConnection;
				StartConnection();
			} else {
				if (!HorusDriveConnection.connection.isValid(0)) {
					if (HorusDriveConnection.reconect) {
						HorusDriveConnection.reconect = false;
						StartConnection();
						System.out.println("His connection was reset by TimeOut ...");
					}
				}
			}
			return HorusDriveConnection.connection;
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
		HorusDriveConnection.stringConnection = stringConnection;
	}
}
