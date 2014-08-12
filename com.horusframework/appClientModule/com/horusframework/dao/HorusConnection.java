package com.horusframework.dao;

import java.sql.Connection;

import com.horusframework.annotation.HorusConnectionParameters;

/**
 * @author miamarti
 *
 */
public class HorusConnection {

	/**
	 * @return
	 */
	public static Connection getConnection(HorusConnectionParameters stringConnection) {
		return HorusDriveConnection.getInstance.getConnection(stringConnection);
	}
}