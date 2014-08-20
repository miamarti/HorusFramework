package controller;

import javax.servlet.annotation.WebServlet;

import com.horusframework.annotation.HorusDomainPackage;
import com.horusframework.controller.HorusFrontController;

/**
 * @author miamarti
 *
 */
@WebServlet(value = "/ec/*")
@HorusDomainPackage(name = "service.excel")
public class ExcelController extends HorusFrontController {
	private static final long serialVersionUID = 1L;
}
