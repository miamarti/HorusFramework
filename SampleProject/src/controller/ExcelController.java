package controller;

import javax.servlet.annotation.WebServlet;
import org.horusframework.annotation.HorusDomainPackage;
import org.horusframework.controller.HorusFrontController;

/**
 * @author horusframework.org
 *
 */
@WebServlet(value = "/ec/*")
@HorusDomainPackage(name = "service.excel", alias = "XLS")
public class ExcelController extends HorusFrontController {
	private static final long serialVersionUID = 1L;
}
