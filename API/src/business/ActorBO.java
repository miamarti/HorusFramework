package business;

import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

import model.ActorBean;

import org.json.simple.JSONObject;

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
	public ResultSet getList(HttpServletRequest request, String[] uri) {
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
	@SuppressWarnings("unchecked")
	public JSONObject setNew(HttpServletRequest request, String[] uri) {
		JSONObject json = new JSONObject();
		if (access) {
			ActorBean actor = new ActorBean();
			actor.setFirstName(request.getParameter("firstName"));
			actor.setLastName(request.getParameter("lastName"));
			json.put("action", APIBaseDAO.ActorDAO.getInstance.setActor(actor).toString());
			return json;
		} else {
			json.put("action", false);
			return json;
		}
	}

	/**
	 * @param request
	 * @param response
	 * @param uri
	 */
	@SuppressWarnings("unchecked")
	public JSONObject setUpdate(HttpServletRequest request, String[] uri) {
		// TODO: Here you implement your method and everything :)
		JSONObject json = new JSONObject();
		if (access) {
			json.put("action", true);
			return json;
		} else {
			json.put("action", false);
			return json;
		}
	}

	/**
	 * @param request
	 * @param response
	 * @param uri
	 */
	@SuppressWarnings("unchecked")
	public JSONObject setDelete(HttpServletRequest request, String[] uri) {
		// TODO: Here you implement your method and everything :)
		JSONObject json = new JSONObject();
		if (access) {
			json.put("action", true);
			return json;
		} else {
			json.put("action", false);
			return json;
		}
	}
}
