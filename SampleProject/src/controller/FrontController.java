package controller;

import javax.servlet.annotation.WebServlet;

import org.horusframework.annotation.HorusDomainPackage;
import org.horusframework.controller.HorusFrontController;

/**
 * @author horusframework.org
 *
 */
@WebServlet(value = "/fc/*")
@HorusDomainPackage(name = "service.json", alias = "JSON")
public class FrontController extends HorusFrontController {
	private static final long serialVersionUID = 1L;
}