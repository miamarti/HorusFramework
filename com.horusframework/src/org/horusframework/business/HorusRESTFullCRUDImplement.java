package org.horusframework.business;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.horusframework.facade.HorusBaseDAOCRUDInterface;
import org.horusframework.facade.HorusRESTFullCRUDInterface;
import org.json.simple.JSONValue;

/**
 * @author miamarti
 *
 */
public abstract class HorusRESTFullCRUDImplement implements HorusRESTFullCRUDInterface {
	private Map<String, String> content;
	private List<Map<String, String>> data;
	private String jsonString;
	private ResultSet rs;

	/**
	 * @param type
	 * @return
	 */
	protected boolean getAccess(String type) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * business.RESTFullCRUDBean#doGet(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, java.lang.String[],
	 * dao.BaseDAOCRUDBean)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response, String[] uri, HorusBaseDAOCRUDInterface basedao) {
		try {
			content = new Hashtable<String, String>();
			data = new ArrayList<Map<String, String>>();

			if (uri.length > 4) {
				switch (uri[4]) {
				case "id":
					rs = basedao.getId(uri[5]);
					break;
				case "where":
					rs = basedao.getByWhere(uri[5], uri[6]);
					break;
				default:
					if (uri.length > 6) {
						rs = basedao.getLista(uri[4], uri[5], uri[6]);
					} else {
						if (uri.length > 5) {
							rs = basedao.getLista(uri[4], uri[5]);
						} else {
							rs = basedao.getLista(uri[4]);
						}
					}
					break;
				}
			} else {
				rs = basedao.getLista();
			}

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * business.RESTFullCRUDBean#doDelete(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, java.lang.String[],
	 * dao.BaseDAOCRUDBean)
	 */
	public void doDelete(HttpServletRequest request, HttpServletResponse response, String[] uri, HorusBaseDAOCRUDInterface basedao) {
		try {
			Boolean insertData = basedao.delData(uri[4]);
			response.setContentType("application/json;charset=UTF-8");
			if (insertData) {
				response.getWriter().println("{\"action\":\"true\"}");
			} else {
				response.getWriter().println("{\"action\":\"false\"}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * business.RESTFullCRUDBean#doPost(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, java.lang.String[],
	 * dao.BaseDAOCRUDBean)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response, String[] uri, HorusBaseDAOCRUDInterface basedao) {
		try {
			int[] insertData = basedao.setData(request);
			response.setContentType("application/json;charset=UTF-8");
			if (insertData[0] == 1) {
				response.getWriter().println("{\"action\":\"true\",\"success\":\"" + insertData[1] + "\"}");
			} else {
				response.getWriter().println("{\"action\":\"false\"}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * business.RESTFullCRUDBean#doPut(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, java.lang.String[],
	 * dao.BaseDAOCRUDBean)
	 */
	public void doPut(HttpServletRequest request, HttpServletResponse response, String[] uri, HorusBaseDAOCRUDInterface basedao) {
		try {
			int[] insertData = { 0 };
			if (uri[4].equals("setRow")) {
				insertData = basedao.setRow(request, uri[5], uri[6], uri[7]);
				response.setContentType("application/json;charset=UTF-8");
			} else {
				insertData = basedao.setData(request, uri[4]);
				response.setContentType("application/json;charset=UTF-8");
			}

			if (insertData[0] == 1) {
				response.getWriter().println("{\"action\":\"true\",\"success\":\"" + insertData[1] + "\"}");
			} else {
				response.getWriter().println("{\"action\":\"false\"}");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
