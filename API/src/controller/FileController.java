package controller;

import javax.servlet.annotation.WebServlet;

import org.horusframework.annotation.HorusDomainPackage;
import org.horusframework.controller.HorusFrontController;

/**
 * @author horusframework.org
 *
 */
@WebServlet(value = "/file/*")
@HorusDomainPackage(name = "service.file")
public class FileController extends HorusFrontController {
	private static final long serialVersionUID = 1L;
}