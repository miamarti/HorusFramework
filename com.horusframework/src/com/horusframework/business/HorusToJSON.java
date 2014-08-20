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

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 * @author miamarti
 *
 */
public class HorusToJSON {

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
		static public void write(HttpServletResponse response, Object rs) {
			Map<String, String> content = new Hashtable<String, String>();
			List<Map<String, String>> data = new ArrayList<Map<String, String>>();
			try {
				ResultSetMetaData rsMetaData = ((ResultSet) rs).getMetaData();
				int numberOfColumns = rsMetaData.getColumnCount();
				while (((ResultSet) rs).next()) {
					for (int i = 1; i <= numberOfColumns; i++) {
						String ColumnName = rsMetaData.getColumnName(i);
						content.put(ColumnName, (((ResultSet) rs).getString(ColumnName) != null ? ((ResultSet) rs).getString(ColumnName).trim() : ""));
					}
					data.add(new Hashtable<String, String>(content));
					content.clear();
				}
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().println(JSONValue.toJSONString(data));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		static public void write(HttpServletResponse response, String jsonString) {
			try {
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().println(jsonString);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		static public void write(HttpServletResponse response, JSONObject jsonString) {
			try {
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().println(jsonString.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
