package com.bj;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet({"/home", "/answer"})
public class MyServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cp = request.getContextPath();
		String uri = request.getRequestURI();
		
		if(uri.equals(cp + "/home")) {
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String cp = request.getContextPath();
		String uri = request.getRequestURI();
		
	    if(uri.equals(cp+"/answer")) {
		int num1 = Integer.parseInt(request.getParameter("num1"));
		int num2 = Integer.parseInt( request.getParameter("num2"));
		String operator = request.getParameter("operator");
		int ans = 0;
	
		switch(operator) {
			case "+" -> ans = num1 + num2;
			case "-" -> ans = num1 - num2;
			case "*" -> ans = num1 * num2;
			case "/" -> ans = num1 / num2;
	}
	
	session.setAttribute("ans", ans);
	
	RequestDispatcher rd = request.getRequestDispatcher("/NewFile.jsp");
	rd.forward(request, response);
	}
	}
}
