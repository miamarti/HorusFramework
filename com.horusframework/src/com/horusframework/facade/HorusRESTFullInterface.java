package com.horusframework.facade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author miamarti
 *
 */
public interface HorusRESTFullInterface {

	/**
	 * List all data
	 * 
	 * @param request
	 * @param response
	 * @param uri
	 */
	void doGet(HttpServletRequest request, HttpServletResponse response, String[] uri);

	/**
	 * Add new data
	 * 
	 * @param request
	 * @param response
	 * @param uri
	 */
	void doPost(HttpServletRequest request, HttpServletResponse response, String[] uri);

	/**
	 * Update data
	 * 
	 * @param request
	 * @param response
	 * @param uri
	 */
	void doPut(HttpServletRequest request, HttpServletResponse response, String[] uri);

	/**
	 * Delete or cancel data
	 * 
	 * @param request
	 * @param response
	 * @param uri
	 */
	void doDelete(HttpServletRequest request, HttpServletResponse response, String[] uri);
}
