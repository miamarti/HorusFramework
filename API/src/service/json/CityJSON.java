package service.json;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.horusframework.annotation.HorusBaseDAO;
import org.horusframework.business.RESTFullCRUDImplement;

import dao.APIBaseDAO;

/**
 * @author horusframework.org
 *
 */
@HorusBaseDAO(APIBaseDAO.CityDAO.class)
public class CityJSON extends RESTFullCRUDImplement {

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