package business;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ActorBean;

import com.horusframework.business.HorusRsToJSON;

import dao.APIBaseDAO;

/**
 * @author miamarti
 *
 */
public class ActorBO extends HorusRsToJSON {

	/**
	 * @param request
	 * @param response
	 * @param uri
	 */
	public static void getList(HttpServletRequest request, HttpServletResponse response, String[] uri) {
		getWriter.write(response, APIBaseDAO.ActorDAO.getInstance.getLista());
	}

	public static void setNew(HttpServletRequest request, HttpServletResponse response, String[] uri) {
		ActorBean actor = new ActorBean();
		actor.setFirstName(request.getParameter("firstName"));
		actor.setLastName(request.getParameter("lastName"));
		getWriter.write(response, "{\"action\":" + APIBaseDAO.ActorDAO.getInstance.setActor(actor).toString() + "}");
	}
}
