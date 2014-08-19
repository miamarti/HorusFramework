package business;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ActorBean;

import com.horusframework.annotation.HorusAccessId;
import com.horusframework.business.HorusToJSON;

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
	public void getList(HttpServletRequest request, HttpServletResponse response, String[] uri) {
		if (access) {
			HorusToJSON.getWriter.write(response, APIBaseDAO.ActorDAO.getInstance.getLista());
		} else {
			HorusToJSON.getWriter.write(response, "{\"action\":false}");
		}
	}

	/**
	 * @param request
	 * @param response
	 * @param uri
	 */
	public void setNew(HttpServletRequest request, HttpServletResponse response, String[] uri) {
		if (access) {
			ActorBean actor = new ActorBean();
			actor.setFirstName(request.getParameter("firstName"));
			actor.setLastName(request.getParameter("lastName"));
			HorusToJSON.getWriter.write(response, "{\"action\":" + APIBaseDAO.ActorDAO.getInstance.setActor(actor).toString() + "}");
		} else {
			HorusToJSON.getWriter.write(response, "{\"action\":false}");
		}
	}

	/**
	 * @param request
	 * @param response
	 * @param uri
	 */
	public void setUpdate(HttpServletRequest request, HttpServletResponse response, String[] uri) {
		// TODO: Here you implement your method and everything :)
		if (access) {
			HorusToJSON.getWriter.write(response, "{\"action\":true}");
		} else {
			HorusToJSON.getWriter.write(response, "{\"action\":false}");
		}
	}

	/**
	 * @param request
	 * @param response
	 * @param uri
	 */
	public void setDelete(HttpServletRequest request, HttpServletResponse response, String[] uri) {
		// TODO: Here you implement your method and everything :)
		if (access) {
			HorusToJSON.getWriter.write(response, "{\"action\":true}");
		} else {
			HorusToJSON.getWriter.write(response, "{\"action\":false}");
		}
	}
}
