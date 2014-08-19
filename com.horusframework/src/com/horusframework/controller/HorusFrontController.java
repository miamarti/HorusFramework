package com.horusframework.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.horusframework.annotation.HorusDomainPackage;

/**
 * @author miamarti
 *
 */
public class HorusFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) {
		try {
			if (this.getClass().isAnnotationPresent(HorusDomainPackage.class)) {
				Class.forName((this.getClass().getAnnotation(HorusDomainPackage.class)).name() + "." + request.getRequestURI().split("/")[3] + (this.getClass().getAnnotation(HorusDomainPackage.class)).alias()).getDeclaredMethod((request.getMethod().equals("GET") ? "doGet" : (request.getMethod().equals("POST") ? "doPost" : (request.getMethod().equals("DELETE") ? "doDelete" : (request.getMethod().equals("PUT") ? "doPut" : request.getMethod())))), HttpServletRequest.class, HttpServletResponse.class, String[].class).invoke(Class.forName((this.getClass().getAnnotation(HorusDomainPackage.class)).name() + "." + request.getRequestURI().split("/")[3] + (this.getClass().getAnnotation(HorusDomainPackage.class)).alias()).newInstance(), request, response, request.getRequestURI().split("/"));
			} else {
				System.out.println("Please inform the field of packets through the annotation 'DomainPackage'");
			}
		} catch (Exception e) {
			response.setStatus(501);
		}
	}
}
