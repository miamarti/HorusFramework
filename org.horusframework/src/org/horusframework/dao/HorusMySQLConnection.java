package org.horusframework.dao;

import java.sql.Connection;

import org.horusframework.annotation.HorusConnectionParameters;

/**
 * @author miamarti
 *
 */
public class HorusMySQLConnection {

	/**
	 * @return
	 */
	public static Connection getConnection(HorusConnectionParameters stringConnection) {
		return HorusMySQLDriveConnection.getInstance.getConnection(stringConnection);
	}
}