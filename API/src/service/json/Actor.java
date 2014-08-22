package service.json;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.horusframework.annotation.HorusAccessId;
import org.horusframework.business.HorusToJSON;
import org.horusframework.facade.HorusRESTFullInterface;

import business.ActorBO;

/**
 * @author miamarti
 *
 */
@HorusAccessId("33")
public class Actor extends ActorBO implements HorusRESTFullInterface {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response, String[] uri) {
		HorusToJSON.getWriter.write(response, getList(request, uri));
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response, String[] uri) {
		HorusToJSON.getWriter.write(response, setNew(request, uri));
	}

	@Override
	public void doPut(HttpServletRequest request, HttpServletResponse response, String[] uri) {
		HorusToJSON.getWriter.write(response, setUpdate(request, uri));
	}

	@Override
	public void doDelete(HttpServletRequest request, HttpServletResponse response, String[] uri) {
		HorusToJSON.getWriter.write(response, setDelete(request, uri));
	}
}