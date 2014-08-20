package service.excel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.ActorBO;

import com.horusframework.annotation.HorusAccessId;
import com.horusframework.business.HorusToXLS;

/**
 * @author miamarti
 *
 */
@HorusAccessId("33")
public class Actor extends ActorBO {

	/**
	 * @param request
	 * @param response
	 * @param uri
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response, String[] uri) {
		HorusToXLS.getWriter.write(response, getList(request, uri));
	}
}