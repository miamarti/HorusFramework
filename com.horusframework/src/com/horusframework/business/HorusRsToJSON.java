package com.horusframework.business;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONValue;

/**
 * @author miamarti
 *
 */
public class HorusRsToJSON {

	/**
	 * @author miamarti
	 *
	 */
	static public class getWriter {

		/**
		 * @param response
		 * @param rs
		 * @throws SQLException
		 */
		static public void write(HttpServletResponse response, ResultSet rs) {
			Map<String, String> content = new Hashtable<String, String>();
			List<Map<String, String>> data = new ArrayList<Map<String, String>>();
			String jsonString;
			try {
				ResultSetMetaData rsMetaData = rs.getMetaData();
				int numberOfColumns = rsMetaData.getColumnCount();
				while (rs.next()) {
					for (int i = 1; i <= numberOfColumns; i++) {
						String ColumnName = rsMetaData.getColumnName(i);
						content.put(ColumnName, (rs.getString(ColumnName) != null ? rs.getString(ColumnName).trim() : ""));
					}
					data.add(new Hashtable<String, String>(content));
					content.clear();
				}
				jsonString = JSONValue.toJSONString(data);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().println(jsonString);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		static public void write(HttpServletResponse response, String jsonString) {
			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().println(jsonString);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
