package com.horusframework.facade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author miamarti
 * 
 */
public interface HorusRESTFullCRUDInterface {

	/**
	 * Send the request using HTTP GET
	 * 
	 * @param request
	 * @param response
	 * @param uri
	 * @param basedao
	 * @since To return all records: /FrontController/ + screenName <br>
	 *        To filter a specific id: /FrontController/ + screenName + /id/ +
	 *        idNumbem <br>
	 *        To filter a column specifies: /FrontController/ + screenName +
	 *        /where/ + colName + / + valueFilter <br>
	 *        To select a limit: /FrontController/ + screenName + / +
	 *        startNumber + / + endNumber <br>
	 *        Selecting and seeking out value: /FrontController/ + screenName +
	 *        / + startNumber + / + endNumber + / + searchedValue
	 * @see http://api.jquery.com/jQuery.ajax/
	 * @see https://en.wikipedia.org/wiki/Representational_state_transfer
	 */
	void doGet(HttpServletRequest request, HttpServletResponse response, String[] uri, HorusBaseDAOCRUDInterface basedao);

	/**
	 * Send the request using HTTP POST
	 * 
	 * @param request
	 * @param response
	 * @param uri
	 * @param basedao
	 * @see http://api.jquery.com/jQuery.ajax/
	 * @see https://en.wikipedia.org/wiki/Representational_state_transfer
	 */
	void doPost(HttpServletRequest request, HttpServletResponse response, String[] uri, HorusBaseDAOCRUDInterface basedao);

	/**
	 * Send the request using HTTP PUT
	 * 
	 * @param request
	 * @param response
	 * @param uri
	 * @param basedao
	 * @since /FrontController/ + idLine + / + urlGetValues
	 * @see http://api.jquery.com/jQuery.ajax/
	 * @see https://en.wikipedia.org/wiki/Representational_state_transfer
	 */
	void doPut(HttpServletRequest request, HttpServletResponse response, String[] uri, HorusBaseDAOCRUDInterface basedao);

	/**
	 * Send the request using HTTP DELETE
	 * 
	 * @param request
	 * @param response
	 * @param uri
	 * @param basedao
	 * @since /FrontController/ + screenName + / + idLine
	 * @see http://api.jquery.com/jQuery.ajax/
	 * @see https://en.wikipedia.org/wiki/Representational_state_transfer
	 */
	void doDelete(HttpServletRequest request, HttpServletResponse response, String[] uri, HorusBaseDAOCRUDInterface basedao);
}
