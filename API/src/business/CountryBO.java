package business;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.horusframework.business.HorusRsToJSON;

import dao.APIBaseDAO;

/**
 * @author miamarti
 *
 */
public class CountryBO extends HorusRsToJSON {

	/**
	 * @param request
	 * @param response
	 * @param uri
	 */
	static public void getList(HttpServletRequest request, HttpServletResponse response, String[] uri) {
		getWriter.write(response, APIBaseDAO.CountryDAO.getInstance.getLista());
	}
}
