package controller;

import javax.servlet.annotation.WebServlet;

import com.horusframework.annotation.HorusDomainPackage;
import com.horusframework.controller.HorusFrontController;

/**
 * @author miamarti
 *
 */
@WebServlet(value = "/fc/*")
@HorusDomainPackage(name = "service")
public class FrontController extends HorusFrontController {
	private static final long serialVersionUID = 1L;
}
