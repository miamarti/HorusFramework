package service.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.horusframework.business.HorusToJSON;
import org.horusframework.facade.HorusRESTFullInterface;

import business.TotpBO;

public class Totp extends TotpBO implements HorusRESTFullInterface {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response, String[] uri) {
		try {
			response.setContentType("image/jpeg");
			ServletOutputStream out;
			out = response.getOutputStream();
			FileInputStream fin = getProgramming((request.getServletContext()).getRealPath("/WEB-INF"));

			BufferedInputStream bin = new BufferedInputStream(fin);
			BufferedOutputStream bout = new BufferedOutputStream(out);
			int ch = 0;
			while ((ch = bin.read()) != -1) {
				bout.write(ch);
			}

			bin.close();
			fin.close();
			bout.close();
			out.close();

			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response, String[] uri) {
		HorusToJSON.getWriter.write(response, valiCode(Long.parseLong(uri[4])));
	}

	@Override
	public void doPut(HttpServletRequest request, HttpServletResponse response, String[] uri) {
	}

	@Override
	public void doDelete(HttpServletRequest request, HttpServletResponse response, String[] uri) {
	}

}
