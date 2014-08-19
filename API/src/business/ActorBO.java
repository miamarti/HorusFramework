package business;

import javax.servlet.http.HttpServletRequest;

import model.ActorBean;

import com.horusframework.annotation.HorusAccessId;

import dao.APIBaseDAO;

/**
 * @author miamarti
 *
 */
public class ActorBO {
	// TODO: Here you implement your validation layer by checking the User access
	Boolean access = ItsValidationLayerBO.getInstance.valid(this.getClass().getAnnotation(HorusAccessId.class).value());

	/**
	 * @param request
	 * @param response
	 * @param uri
	 */
	public Object getList(HttpServletRequest request, String[] uri) {
		if (access) {
			return APIBaseDAO.ActorDAO.getInstance.getLista();
		} else {
			return null;
		}
	}

	/**
	 * @param request
	 * @param response
	 * @param uri
	 */
	public String setNew(HttpServletRequest request, String[] uri) {
		if (access) {
			ActorBean actor = new ActorBean();
			actor.setFirstName(request.getParameter("firstName"));
			actor.setLastName(request.getParameter("lastName"));
			return "{\"action\":" + APIBaseDAO.ActorDAO.getInstance.setActor(actor).toString() + "}";
		} else {
			return "{\"action\":false}";
		}
	}

	/**
	 * @param request
	 * @param response
	 * @param uri
	 */
	public String setUpdate(HttpServletRequest request, String[] uri) {
		// TODO: Here you implement your method and everything :)
		if (access) {
			return "{\"action\":true}";
		} else {
			return "{\"action\":false}";
		}
	}

	/**
	 * @param request
	 * @param response
	 * @param uri
	 */
	public String setDelete(HttpServletRequest request, String[] uri) {
		// TODO: Here you implement your method and everything :)
		if (access) {
			return "{\"action\":true}";
		} else {
			return "{\"action\":false}";
		}
	}
}
