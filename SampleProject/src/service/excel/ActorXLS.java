package service.excel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.horusframework.annotation.HorusAccessId;
import org.horusframework.business.HorusToXLS;

import business.ActorBO;

/**
 * @author horusframework.org
 *
 */
@HorusAccessId("33")
public class ActorXLS extends ActorBO {

	/**
	 * @param request
	 * @param response
	 * @param uri
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response, String[] uri) {
		HorusToXLS.getWriter.write(response, getList(request, uri));
	}
}