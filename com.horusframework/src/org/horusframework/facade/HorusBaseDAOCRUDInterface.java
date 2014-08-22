package org.horusframework.facade;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author miamarti
 *
 */
public interface HorusBaseDAOCRUDInterface {
	/**
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	ResultSet getId(String id) throws SQLException;

	/**
	 * @param id
	 * @param idName
	 * @return
	 * @throws SQLException
	 */
	ResultSet getByWhere(String colName, String value) throws SQLException;

	/**
	 * @param args
	 * @return
	 * @throws SQLException
	 */
	ResultSet getLista(String... args) throws SQLException;

	/**
	 * @param request
	 * @param args
	 * @return
	 * @throws SQLException
	 */
	int[] setData(HttpServletRequest request, String... args) throws SQLException;

	/**
	 * @param request
	 * @param args
	 * @return
	 * @throws SQLException
	 */
	int[] setRow(HttpServletRequest request, String... args) throws SQLException;

	/**
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	Boolean delData(String id) throws SQLException;
}
