package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.horusframework.annotation.HorusBaseDAO;
import com.horusframework.business.RESTFullCRUDImplement;

import dao.APIBaseDAO;

/**
 * @author miamarti
 *
 */
@HorusBaseDAO(APIBaseDAO.CityDAO.class)
public class City extends RESTFullCRUDImplement {

	public void doGet(HttpServletRequest request, HttpServletResponse response, String[] uri) throws Exception {
		super.doGet(request, response, uri);
	}

	public void doDelete(HttpServletRequest request, HttpServletResponse response, String[] uri) throws Exception {
		super.doDelete(request, response, uri);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response, String[] uri) throws Exception {
		super.doPost(request, response, uri);
	}

	public void doPut(HttpServletRequest request, HttpServletResponse response, String[] uri) throws Exception {
		super.doPut(request, response, uri);
	}
}