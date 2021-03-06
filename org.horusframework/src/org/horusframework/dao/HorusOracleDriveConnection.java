package org.horusframework.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;

import org.horusframework.annotation.HorusConnectionParameters;

import oracle.jdbc.pool.OracleDataSource;

public class HorusOracleDriveConnection {

	private Connection connection = null;
	private static Boolean reconect = true;
	private static HorusConnectionParameters stringConnection = null;

	HorusOracleDriveConnection() {
	}

	private void StartConnection() {
		try {
			Locale.setDefault(new Locale(stringConnection.language(), stringConnection.country()));
			OracleDataSource ods = new OracleDataSource();
			ods.setURL("jdbc:oracle:thin:@" + stringConnection.host() + ":" + stringConnection.port() + ":" + stringConnection.database());
			ods.setUser(stringConnection.user());
			ods.setPassword(stringConnection.password());
			connection = ods.getConnection();
			connection.setAutoCommit(stringConnection.autoCommit());
		} catch (SQLException e) {
			System.out.println("Could not connect - StartConnection (). Let's try again!");
			e.printStackTrace();
			StartConnection();
		}
	}

	public Connection getConnection(HorusConnectionParameters stringConnection) {
		try {
			if (connection == null) {
				HorusOracleDriveConnection.stringConnection = stringConnection;
				StartConnection();
			} else {
				if (!connection.isValid(0)) {
					if (HorusOracleDriveConnection.reconect) {
						HorusOracleDriveConnection.reconect = false;
						StartConnection();
						System.out.println("His connection was reset by TimeOut ...");
					}
				}
			}
			return connection;
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
		HorusOracleDriveConnection.stringConnection = stringConnection;
	}
}
