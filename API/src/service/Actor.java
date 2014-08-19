package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.ActorBO;

import com.horusframework.annotation.HorusAccessId;
import com.horusframework.facade.HorusRESTFullInterface;

@HorusAccessId("33")
public class Actor extends ActorBO implements HorusRESTFullInterface {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response, String[] uri) {
		getList(request, response, uri);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response, String[] uri) {
		setNew(request, response, uri);
	}

	@Override
	public void doPut(HttpServletRequest request, HttpServletResponse response, String[] uri) {
		setUpdate(request, response, uri);
	}

	@Override
	public void doDelete(HttpServletRequest request, HttpServletResponse response, String[] uri) {
		setDelete(request, response, uri);
	}
}