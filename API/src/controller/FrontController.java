package controller;

import javax.servlet.annotation.WebServlet;

import org.horusframework.annotation.HorusDomainPackage;
import org.horusframework.controller.HorusFrontController;

/**
 * @author miamarti
 *
 */
@WebServlet(value = "/fc/*")
@HorusDomainPackage(name = "service.json")
public class FrontController extends HorusFrontController {
	private static final long serialVersionUID = 1L;
}
