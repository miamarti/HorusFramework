package org.horusframework.dao;

import java.sql.Connection;

import org.horusframework.annotation.HorusConnectionParameters;

/**
 * @author miamarti
 *
 */
public class HorusOracleConnection {

	/**
	 * @return
	 */
	public static Connection getConnection(HorusConnectionParameters stringConnection) {
		return (new HorusOracleDriveConnection()).getConnection(stringConnection);
	}
}