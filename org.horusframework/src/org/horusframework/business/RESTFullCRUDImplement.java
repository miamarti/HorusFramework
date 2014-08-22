package org.horusframework.business;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.horusframework.annotation.HorusAccessId;
import org.horusframework.annotation.HorusBaseDAO;
import org.horusframework.facade.HorusBaseDAOCRUDInterface;

/**
 * @author miamarti
 *
 */
public class RESTFullCRUDImplement extends HorusRESTFullCRUDImplement {

	/**
	 * @param type
	 * @return
	 */
	protected boolean getAccess(String type) {
		return true;
	}

	/**
	 * @param request
	 * @param response
	 * @param uri
	 * @throws Exception
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response, String[] uri) throws Exception {
		if (this.getClass().isAnnotationPresent(HorusAccessId.class)) {
			if (getAccess("aces_r")) {
				super.doGet(request, response, uri, (HorusBaseDAOCRUDInterface) ((this.getClass().getAnnotation(HorusBaseDAO.class)).value()).getField("getInstance").get("getInstance"));
			} else {
				response.setStatus(403);
			}
		} else {
			super.doGet(request, response, uri, (HorusBaseDAOCRUDInterface) ((this.getClass().getAnnotation(HorusBaseDAO.class)).value()).getField("getInstance").get("getInstance"));
		}
	}

	/**
	 * @param request
	 * @param response
	 * @param uri
	 * @throws Exception
	 */
	public void doDelete(HttpServletRequest request, HttpServletResponse response, String[] uri) throws Exception {
		if (this.getClass().isAnnotationPresent(HorusAccessId.class)) {
			if (getAccess("aces_d")) {
				super.doDelete(request, response, uri, (HorusBaseDAOCRUDInterface) ((this.getClass().getAnnotation(HorusBaseDAO.class)).value()).getField("getInstance").get("getInstance"));
			} else {
				response.setStatus(405);
			}
		} else {
			super.doDelete(request, response, uri, (HorusBaseDAOCRUDInterface) ((this.getClass().getAnnotation(HorusBaseDAO.class)).value()).getField("getInstance").get("getInstance"));
		}
	}

	/**
	 * @param request
	 * @param response
	 * @param uri
	 * @throws Exception
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response, String[] uri) throws Exception {
		if (this.getClass().isAnnotationPresent(HorusAccessId.class)) {
			if (getAccess("aces_c")) {
				super.doPost(request, response, uri, (HorusBaseDAOCRUDInterface) ((this.getClass().getAnnotation(HorusBaseDAO.class)).value()).getField("getInstance").get("getInstance"));
			} else {
				response.setStatus(405);
			}
		} else {
			super.doPost(request, response, uri, (HorusBaseDAOCRUDInterface) ((this.getClass().getAnnotation(HorusBaseDAO.class)).value()).getField("getInstance").get("getInstance"));
		}
	}

	/**
	 * @param request
	 * @param response
	 * @param uri
	 * @throws Exception
	 */
	public void doPut(HttpServletRequest request, HttpServletResponse response, String[] uri) throws Exception {
		if (this.getClass().isAnnotationPresent(HorusAccessId.class)) {
			if (getAccess("aces_u")) {
				super.doPut(request, response, uri, (HorusBaseDAOCRUDInterface) ((this.getClass().getAnnotation(HorusBaseDAO.class)).value()).getField("getInstance").get("getInstance"));
			} else {
				response.setStatus(405);
			}
		} else {
			super.doPut(request, response, uri, (HorusBaseDAOCRUDInterface) ((this.getClass().getAnnotation(HorusBaseDAO.class)).value()).getField("getInstance").get("getInstance"));
		}
	}
}
